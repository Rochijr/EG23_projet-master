package com.duolight.projeteg23.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duolight.projeteg23.R;


public class DeploiementArmee extends AppCompatActivity implements View.OnClickListener {
    private static final String SHARED_PREF_JOUEUR_1_INFO = "SHARED_PREF_JOUEUR_1_INFO"; // nom du fichier
    private static final String SHARED_PREF_JOUEUR_1_INFO_KEY = "SHARED_PREF_JOUEUR_1_INFO_KEY"; // Key


    private static final String SHARED_PREF_NOMBRE_DE_TOUR = "SHARED_PREF_NOMBRE_DE_TOUR"; // nom du fichier
    private static final String SHARED_PREF_NOMBRE_DE_TOUR_KEY = "SHARED_PREF_NOMBRE_DE_TOUR_KEY"; // Key

    private TextView mBranche;
    private ImageView next;

    private static final String VIEW_TAG1 = "Chef de guerre";
    private static final String VIEW_TAG2 = "Soldat d'élite";
    private static final String VIEW_TAG3 = "Alpha";
    private static final String VIEW_TAG4 = "Bravo";
    private static final String VIEW_TAG5 = "Charlie";

    private int CdG_restant;
    private int SE_restant;
    private int alpha_restant;
    private int bravo_restant;
    private int charlie_restant;


    // Compte le nombre de soldats par zone
    private static int [] compteurSoldatsZone1=new int[5];
    private static int [] compteurSoldatsZone2=new int[5];
    private static int [] compteurSoldatsZone3=new int[5];
    private static int [] compteurSoldatsZone4=new int[5];
    private static int [] compteurSoldatsZone5=new int[5];
    private static int [] controleZone=new int[6];


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deploiement_armee);
        mBranche=findViewById(R.id.joueur1);
        String brancheJoueur1 = getSharedPreferences(SHARED_PREF_JOUEUR_1_INFO,MODE_PRIVATE).getString(SHARED_PREF_JOUEUR_1_INFO_KEY,null);
        mBranche.setText("Joueur 1 : " + brancheJoueur1);

        // On réinitialise la page
        CdG_restant = 1;
        SE_restant = 4;
        alpha_restant = 5;
        bravo_restant = 5;
        charlie_restant = 5;

        for (int i=0; i<5; i++) {
            compteurSoldatsZone1[i] = 0;
            compteurSoldatsZone2[i] = 0;
            compteurSoldatsZone3[i] = 0;
            compteurSoldatsZone4[i] = 0;
            compteurSoldatsZone5[i] = 0;
        }

        // Remettre invisible certaines vues.
        TextView tv = findViewById(R.id.deploiement_zone1SoldatText1);
        tv.setVisibility(View.INVISIBLE);
        tv = findViewById(R.id.deploiement_zone1SoldatText2);
        tv.setVisibility(View.INVISIBLE);
        tv = findViewById(R.id.deploiement_zone1SoldatText3);
        tv.setVisibility(View.INVISIBLE);
        tv = findViewById(R.id.deploiement_zone1SoldatText4);
        tv.setVisibility(View.INVISIBLE);
        tv = findViewById(R.id.deploiement_zone1SoldatText5);
        tv.setVisibility(View.INVISIBLE);

        // initialisation du nombre de tour
        getSharedPreferences(SHARED_PREF_NOMBRE_DE_TOUR, MODE_PRIVATE)
                .edit()
                .putInt("SHARED_PREF_NOMBRE_DE_TOUR_KEY", 0)
                .apply();

        next = findViewById(R.id.arrow_right);
        next.setOnClickListener(this);

        // Create new views to drags
        LinearLayout groupe1 = (LinearLayout) findViewById(R.id.armee_CdG);
        LinearLayout groupe2 = (LinearLayout) findViewById(R.id.armee_SE);
        LinearLayout groupe3 = (LinearLayout) findViewById(R.id.armee_Alpha);
        LinearLayout groupe4 = (LinearLayout) findViewById(R.id.armee_Bravo);
        LinearLayout groupe5 = (LinearLayout) findViewById(R.id.armee_Charlie);

        // Create views to drop to
        LinearLayout zone1 = (LinearLayout) findViewById(R.id.deploiement_zone1);
        LinearLayout zone2 = (LinearLayout) findViewById(R.id.deploiement_zone2);
        LinearLayout zone3 = (LinearLayout) findViewById(R.id.deploiement_zone3);
        LinearLayout zone4 = (LinearLayout) findViewById(R.id.deploiement_zone4);
        LinearLayout zone5 = (LinearLayout) findViewById(R.id.deploiement_zone5);

        // Set tags.
        groupe1.setTag(VIEW_TAG1);
        groupe2.setTag(VIEW_TAG2);
        groupe3.setTag(VIEW_TAG3);
        groupe4.setTag(VIEW_TAG4);
        groupe5.setTag(VIEW_TAG5);

        groupe1.setOnLongClickListener(v -> {
            if(CdG_restant != 0) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new DeploiementArmee.MyDragShadowBuilder(groupe1);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else {
                Toast.makeText(this, "Votre chef de guerre est déjà déployé.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        groupe2.setOnLongClickListener(v -> {
            if(SE_restant != 0) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new DeploiementArmee.MyDragShadowBuilder(groupe2);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else {
                Toast.makeText(this, "Vos soldats d'élites sont déjà déployés.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        groupe3.setOnLongClickListener(v -> {
            if(alpha_restant != 0) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new DeploiementArmee.MyDragShadowBuilder(groupe3);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else {
                Toast.makeText(this, "Vos membres du groupe Alpha sont déjà déployés.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        groupe4.setOnLongClickListener(v -> {
            if(bravo_restant != 0) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new DeploiementArmee.MyDragShadowBuilder(groupe4);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else {
                Toast.makeText(this, "Vos membres du groupe Bravo sont déjà déployés.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        groupe5.setOnLongClickListener(v -> {
            if(charlie_restant != 0) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new DeploiementArmee.MyDragShadowBuilder(groupe5);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else {
                Toast.makeText(this, "Vos membres du groupe Charlie sont déjà déployés.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        zone1.setOnDragListener((v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = e.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(this, "Vous avez déplacé un " + dragData, Toast.LENGTH_SHORT).show();
                    v.setBackgroundResource(R.drawable.border_reserve);
                    ImageView imageView = new ImageView(this);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

                    if(dragData.equals(groupe1.getTag())) {
                        imageView.setImageResource(R.drawable.logo_chefdeguerre);
                        addSoldat(1, 0);
                        CdG_restant--;
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(1, 1);
                        SE_restant--;
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(1, 2);
                        alpha_restant--;
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(1, 3);
                        bravo_restant--;
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(1, 4);
                        charlie_restant--;
                    }
                    //((LinearLayout)v).addView(imageView, layoutParams);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.border_reserve);
                    v.invalidate();
                    return true;

                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }
            return false;
        });
        zone2.setOnDragListener((v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = e.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(this, "Vous avez déplacé un " + dragData, Toast.LENGTH_SHORT).show();
                    v.setBackgroundResource(R.drawable.border_reserve);
                    ImageView imageView = new ImageView(this);

                    if(dragData.equals(groupe1.getTag())) {
                        imageView.setImageResource(R.drawable.logo_chefdeguerre);
                        addSoldat(2, 0);
                        CdG_restant--;
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(2, 1);
                        SE_restant--;
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(2, 2);
                        alpha_restant--;
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(2, 3);
                        bravo_restant--;
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(2, 4);
                        charlie_restant--;
                    }
                    //((LinearLayout)v).addView(imageView);

                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.border_reserve);
                    v.invalidate();
                    return true;

                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }
            return false;
        });
        zone3.setOnDragListener((v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = e.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(this, "Vous avez déplacé un " + dragData, Toast.LENGTH_SHORT).show();
                    v.setBackgroundResource(R.drawable.border_reserve);
                    ImageView imageView = new ImageView(this);

                    if(dragData.equals(groupe1.getTag())) {
                        imageView.setImageResource(R.drawable.logo_chefdeguerre);
                        addSoldat(3, 0);
                        CdG_restant--;
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(3, 1);
                        SE_restant--;
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(3, 2);
                        alpha_restant--;
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(3, 3);
                        bravo_restant--;
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(3, 4);
                        charlie_restant--;
                    }
                    //((LinearLayout)v).addView(imageView);

                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.border_reserve);
                    v.invalidate();
                    return true;

                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }
            return false;
        });
        zone4.setOnDragListener((v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = e.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(this, "Vous avez déplacé un " + dragData, Toast.LENGTH_SHORT).show();
                    v.setBackgroundResource(R.drawable.border_reserve);
                    ImageView imageView = new ImageView(this);

                    if(dragData.equals(groupe1.getTag())) {
                        imageView.setImageResource(R.drawable.logo_chefdeguerre);
                        addSoldat(4, 0);
                        CdG_restant--;
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(4, 1);
                        SE_restant--;
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(4, 2);
                        alpha_restant--;
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(4, 3);
                        bravo_restant--;
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(4, 4);
                        charlie_restant--;
                    }
                    //((LinearLayout)v).addView(imageView);

                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.border_reserve);
                    v.invalidate();
                    return true;

                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }
            return false;
        });
        zone5.setOnDragListener((v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = e.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    Toast.makeText(this, "Vous avez déplacé un " + dragData, Toast.LENGTH_SHORT).show();
                    v.setBackgroundResource(R.drawable.border_reserve);
                    ImageView imageView = new ImageView(this);

                    if(dragData.equals(groupe1.getTag())) {
                        imageView.setImageResource(R.drawable.logo_chefdeguerre);
                        addSoldat(5, 0);
                        CdG_restant--;
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(5, 1);
                        SE_restant--;
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(5, 2);
                        alpha_restant--;
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(5, 3);
                        bravo_restant--;
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(5, 4);
                        charlie_restant--;
                    }
                    //((LinearLayout)v).addView(imageView);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.border_reserve);
                    v.invalidate();
                    return true;

                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }
            return false;
        });
    }
    private void addSoldat(Integer zone, Integer groupe) {
        switch (zone){
            case 1:
                compteurSoldatsZone1[groupe]++;
                break;
            case 2:
                compteurSoldatsZone2[groupe]++;
                break;
            case 3:
                compteurSoldatsZone3[groupe]++;
                break;
            case 4:
                compteurSoldatsZone4[groupe]++;
                break;
            case 5:
                compteurSoldatsZone5[groupe]++;
                break;
            default:
                Toast.makeText(this, "Erreur : le soldat n'a pas été ajouté au compteur", Toast.LENGTH_LONG).show();
                break;
        }
        updateSoldats();
    }

    private void updateSoldats() {
        for (int i=0; i<5; i++) {
            if (compteurSoldatsZone1[i] != 0) {
                switch (i+1) {
                    case 1:
                        View frameLayout1 = findViewById(R.id.deploiement_zone1Soldat1);
                        TextView textView1 = findViewById(R.id.deploiement_zone1SoldatText1);
                        frameLayout1.setVisibility(View.VISIBLE);
                        accumulerSoldats(1, i, textView1);
                        break;
                    case 2:
                        View frameLayout2 = findViewById(R.id.deploiement_zone1Soldat2);
                        TextView textView2 = findViewById(R.id.deploiement_zone1SoldatText2);
                        frameLayout2.setVisibility(View.VISIBLE);
                        accumulerSoldats(1, i, textView2);
                        break;
                    case 3:
                        View frameLayout3 = findViewById(R.id.deploiement_zone1Soldat3);
                        TextView textView3 = findViewById(R.id.deploiement_zone1SoldatText3);
                        frameLayout3.setVisibility(View.VISIBLE);
                        accumulerSoldats(1, i, textView3);
                        break;
                    case 4:
                        View frameLayout4 = findViewById(R.id.deploiement_zone1Soldat4);
                        TextView textView4 = findViewById(R.id.deploiement_zone1SoldatText4);
                        frameLayout4.setVisibility(View.VISIBLE);
                        accumulerSoldats(1, i, textView4);
                        break;
                    case 5:
                        View frameLayout5 = findViewById(R.id.deploiement_zone1Soldat5);
                        TextView textView5 = findViewById(R.id.deploiement_zone1SoldatText5);
                        frameLayout5.setVisibility(View.VISIBLE);
                        accumulerSoldats(1, i, textView5);
                        break;
                }
            } else if (compteurSoldatsZone2[i] != 0) {
                switch (i+1) {
                    case 1:
                        View frameLayout1 = findViewById(R.id.deploiement_zone2Soldat1);
                        TextView textView1 = findViewById(R.id.deploiement_zone2SoldatText1);
                        frameLayout1.setVisibility(View.VISIBLE);
                        textView1.setText("1");
                        break;
                    case 2:
                        View frameLayout2 = findViewById(R.id.deploiement_zone2Soldat2);
                        TextView textView2 = findViewById(R.id.deploiement_zone2SoldatText2);
                        frameLayout2.setVisibility(View.VISIBLE);
                        textView2.setText("1");
                        break;
                    case 3:
                        View frameLayout3 = findViewById(R.id.deploiement_zone2Soldat3);
                        TextView textView3 = findViewById(R.id.deploiement_zone2SoldatText3);
                        frameLayout3.setVisibility(View.VISIBLE);
                        textView3.setText("1");
                        break;
                    case 4:
                        View frameLayout4 = findViewById(R.id.deploiement_zone2Soldat4);
                        TextView textView4 = findViewById(R.id.deploiement_zone2SoldatText4);
                        frameLayout4.setVisibility(View.VISIBLE);
                        textView4.setText("1");
                        break;
                    case 5:
                        View frameLayout5 = findViewById(R.id.deploiement_zone2Soldat5);
                        TextView textView5 = findViewById(R.id.deploiement_zone2SoldatText5);
                        frameLayout5.setVisibility(View.VISIBLE);
                        textView5.setText("1");
                        break;
                }
            } else if (compteurSoldatsZone2[i] != 0) {
                switch (i+1) {
                    case 1:
                        View frameLayout1 = findViewById(R.id.deploiement_zone3Soldat1);
                        TextView textView1 = findViewById(R.id.deploiement_zone3SoldatText1);
                        frameLayout1.setVisibility(View.VISIBLE);
                        textView1.setText(+i);
                        break;
                    case 2:
                        View frameLayout2 = findViewById(R.id.deploiement_zone3Soldat2);
                        TextView textView2 = findViewById(R.id.deploiement_zone3SoldatText2);
                        frameLayout2.setVisibility(View.VISIBLE);
                        textView2.setText(+i);
                        break;
                    case 3:
                        View frameLayout3 = findViewById(R.id.deploiement_zone3Soldat3);
                        TextView textView3 = findViewById(R.id.deploiement_zone3SoldatText3);
                        frameLayout3.setVisibility(View.VISIBLE);
                        textView3.setText(+i);
                        break;
                    case 4:
                        View frameLayout4 = findViewById(R.id.deploiement_zone3Soldat4);
                        TextView textView4 = findViewById(R.id.deploiement_zone3SoldatText4);
                        frameLayout4.setVisibility(View.VISIBLE);
                        textView4.setText(+i);
                        break;
                    case 5:
                        View frameLayout5 = findViewById(R.id.deploiement_zone3Soldat5);
                        TextView textView5 = findViewById(R.id.deploiement_zone3SoldatText5);
                        frameLayout5.setVisibility(View.VISIBLE);
                        textView5.setText(+i);
                        break;
                }
            } else if (compteurSoldatsZone2[i] != 0) {
                switch (i+1) {
                    case 1:
                        View frameLayout1 = findViewById(R.id.deploiement_zone4Soldat1);
                        TextView textView1 = findViewById(R.id.deploiement_zone4SoldatText1);
                        frameLayout1.setVisibility(View.VISIBLE);
                        textView1.setText(+i);
                        break;
                    case 2:
                        View frameLayout2 = findViewById(R.id.deploiement_zone4Soldat2);
                        TextView textView2 = findViewById(R.id.deploiement_zone4SoldatText2);
                        frameLayout2.setVisibility(View.VISIBLE);
                        textView2.setText(+i);
                        break;
                    case 3:
                        View frameLayout3 = findViewById(R.id.deploiement_zone4Soldat3);
                        TextView textView3 = findViewById(R.id.deploiement_zone4SoldatText3);
                        frameLayout3.setVisibility(View.VISIBLE);
                        textView3.setText(+i);
                        break;
                    case 4:
                        View frameLayout4 = findViewById(R.id.deploiement_zone4Soldat4);
                        TextView textView4 = findViewById(R.id.deploiement_zone4SoldatText4);
                        frameLayout4.setVisibility(View.VISIBLE);
                        textView4.setText(+i);
                        break;
                    case 5:
                        View frameLayout5 = findViewById(R.id.deploiement_zone4Soldat5);
                        TextView textView5 = findViewById(R.id.deploiement_zone4SoldatText5);
                        frameLayout5.setVisibility(View.VISIBLE);
                        textView5.setText(+i);
                        break;
                }
            } else if (compteurSoldatsZone2[i] != 0) {
                switch (i+1) {
                    case 1:
                        View frameLayout1 = findViewById(R.id.deploiement_zone5Soldat1);
                        TextView textView1 = findViewById(R.id.deploiement_zone5SoldatText1);
                        frameLayout1.setVisibility(View.VISIBLE);
                        textView1.setText(+i);
                        break;
                    case 2:
                        View frameLayout2 = findViewById(R.id.deploiement_zone5Soldat2);
                        TextView textView2 = findViewById(R.id.deploiement_zone5SoldatText2);
                        frameLayout2.setVisibility(View.VISIBLE);
                        textView2.setText(+i);
                        break;
                    case 3:
                        View frameLayout3 = findViewById(R.id.deploiement_zone5Soldat3);
                        TextView textView3 = findViewById(R.id.deploiement_zone5SoldatText3);
                        frameLayout3.setVisibility(View.VISIBLE);
                        textView3.setText(+i);
                        break;
                    case 4:
                        View frameLayout4 = findViewById(R.id.deploiement_zone5Soldat4);
                        TextView textView4 = findViewById(R.id.deploiement_zone5SoldatText4);
                        frameLayout4.setVisibility(View.VISIBLE);
                        textView4.setText(+i);
                        break;
                    case 5:
                        View frameLayout5 = findViewById(R.id.deploiement_zone5Soldat5);
                        TextView textView5 = findViewById(R.id.deploiement_zone5SoldatText5);
                        frameLayout5.setVisibility(View.VISIBLE);
                        textView5.setText(+i);
                        break;
                }
            }
        }
    }

    private void accumulerSoldats(int zone, int groupe, TextView tv) {
        switch (zone) {
            case 1:
                switch (compteurSoldatsZone1[groupe]) {
                    case 1:
                        tv.setText("1");
                        break;
                    case 2:
                        tv.setText("2");
                        break;
                    case 3:
                        tv.setText("3");
                        break;
                    case 4:
                        tv.setText("4");
                        break;
                    case 5:
                        tv.setText("5");
                        break;
                }
                break;
            case 2:
                switch (compteurSoldatsZone2[groupe]) {
                    case 1:
                        tv.setText("1");
                        break;
                    case 2:
                        tv.setText("2");
                        break;
                    case 3:
                        tv.setText("3");
                        break;
                    case 4:
                        tv.setText("4");
                        break;
                    case 5:
                        tv.setText("5");
                        break;
                }
                break;
            case 3:
                switch (compteurSoldatsZone3[groupe]) {
                    case 1:
                        tv.setText("1");
                        break;
                    case 2:
                        tv.setText("2");
                        break;
                    case 3:
                        tv.setText("3");
                        break;
                    case 4:
                        tv.setText("4");
                        break;
                    case 5:
                        tv.setText("5");
                        break;
                }
                break;
            case 4:
                switch (compteurSoldatsZone4[groupe]) {
                    case 1:
                        tv.setText("1");
                        break;
                    case 2:
                        tv.setText("2");
                        break;
                    case 3:
                        tv.setText("3");
                        break;
                    case 4:
                        tv.setText("4");
                        break;
                    case 5:
                        tv.setText("5");
                        break;
                }
                break;
            case 5:
                switch (compteurSoldatsZone5[groupe]) {
                    case 1:
                        tv.setText("1");
                        break;
                    case 2:
                        tv.setText("2");
                        break;
                    case 3:
                        tv.setText("3");
                        break;
                    case 4:
                        tv.setText("4");
                        break;
                    case 5:
                        tv.setText("5");
                        break;
                }
                break;
        }
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
        // The drag shadow image, defined as a drawable object.
        private static Drawable shadow;

        // Constructor
        public MyDragShadowBuilder(View v) {
            // Stores the View parameter.
            super(v);
            // Creates a draggable image that fills the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point
        // back to the system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            // Defines local variables
            int width, height;
            // Set the width of the shadow to half the width of the original View.
            width = getView().getWidth() / 2;
            // Set the height of the shadow to half the height of the original View.
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the
            // same as the Canvas that the system provides. As a result, the drag shadow
            // fills the Canvas.
            shadow.setBounds(0, 0, width, height);

            // Set the size parameter's width and height values. These get back to the
            // system through the size parameter.
            size.set(width, height);

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        @Override
        public void onDrawShadow (Canvas canvas) {
            // Draw the ColorDrawable on the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            pageSuivante();
        }
        else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }
    }
    public void pageSuivante() {
        // Création d’une activité associée à l’exécution de MaGestionListe.class
        Intent intent = new Intent(DeploiementArmee.this, Combat.class);
        intent.putExtra("SOLDATS_ZONE1", compteurSoldatsZone1);
        intent.putExtra("SOLDATS_ZONE2", compteurSoldatsZone2);
        intent.putExtra("SOLDATS_ZONE3", compteurSoldatsZone3);
        intent.putExtra("SOLDATS_ZONE4", compteurSoldatsZone4);
        intent.putExtra("SOLDATS_ZONE5", compteurSoldatsZone5);
        intent.putExtra("CONTROLE_ZONE", controleZone);
      
        // Exécution de l’activité : ouverture de la fenêtre
        startActivity(intent);
    }
}