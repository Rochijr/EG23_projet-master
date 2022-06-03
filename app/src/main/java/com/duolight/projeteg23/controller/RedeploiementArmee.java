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
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duolight.projeteg23.R;

public class RedeploiementArmee extends AppCompatActivity {
    private static final String VIEW_TAG1 = "Chef de guerre";
    private static final String VIEW_TAG2 = "Soldat d'élite";
    private static final String VIEW_TAG3 = "Alpha";
    private static final String VIEW_TAG4 = "Bravo";
    private static final String VIEW_TAG5 = "Charlie";

    //compte le nombre de soldat par zone
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
        setContentView(R.layout.activity_redeploiement_armee);

        Intent intent = getIntent();
        int []soldatsZone1 = intent.getIntArrayExtra("SOLDATS_ZONE1");
        int []soldatsZone2 = intent.getIntArrayExtra("SOLDATS_ZONE2");
        int []soldatsZone3 = intent.getIntArrayExtra("SOLDATS_ZONE3");
        int []soldatsZone4 = intent.getIntArrayExtra("SOLDATS_ZONE4");
        int []soldatsZone5 = intent.getIntArrayExtra("SOLDATS_ZONE5");
        int []controleZone = intent.getIntArrayExtra("CONTROLE_ZONE");
        compteurSoldatsZone1 = soldatsZone1;
        compteurSoldatsZone2 = soldatsZone2;
        compteurSoldatsZone3 = soldatsZone3;
        compteurSoldatsZone4 = soldatsZone4;
        compteurSoldatsZone5 = soldatsZone5;

        ImageView next = findViewById(R.id.arrow_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                // Création d’une activité associée à l’exécution de MaGestionListe.class
                Intent intent = new Intent(RedeploiementArmee.this, Combat.class);
                intent.putExtra("SOLDATS_ZONE1", compteurSoldatsZone1);
                intent.putExtra("SOLDATS_ZONE2", compteurSoldatsZone2);
                intent.putExtra("SOLDATS_ZONE3", compteurSoldatsZone3);
                intent.putExtra("SOLDATS_ZONE4", compteurSoldatsZone4);
                intent.putExtra("SOLDATS_ZONE5", compteurSoldatsZone5);
                intent.putExtra("CONTROLE_ZONE", controleZone);

                // Exécution de l’activité : ouverture de la fenêtre
                startActivity(intent);
            }
        });

        // Create new views to drags
        LinearLayout groupe1 = (LinearLayout) findViewById(R.id.armee_CdG);
        LinearLayout groupe2 = (LinearLayout) findViewById(R.id.armee_SE);
        LinearLayout groupe3 = (LinearLayout) findViewById(R.id.armee_Alpha);
        LinearLayout groupe4 = (LinearLayout) findViewById(R.id.armee_Bravo);
        LinearLayout groupe5 = (LinearLayout) findViewById(R.id.armee_Charlie);

        // Create views to drop to
        LinearLayout zone1 = (LinearLayout) findViewById(R.id.zone1);
        LinearLayout zone2 = (LinearLayout) findViewById(R.id.zone2);
        LinearLayout zone3 = (LinearLayout) findViewById(R.id.zone3);
        LinearLayout zone4 = (LinearLayout) findViewById(R.id.zone4);
        LinearLayout zone5 = (LinearLayout) findViewById(R.id.zone5);

        // Set tags.
        groupe1.setTag(VIEW_TAG1);
        groupe2.setTag(VIEW_TAG2);
        groupe3.setTag(VIEW_TAG3);
        groupe4.setTag(VIEW_TAG4);
        groupe5.setTag(VIEW_TAG5);

        groupe1.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
            View.DragShadowBuilder myShadow = new RedeploiementArmee.MyDragShadowBuilder(groupe1);
            v.startDragAndDrop(dragData, myShadow,null,0);
            return true;
        });
        groupe2.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
            View.DragShadowBuilder myShadow = new RedeploiementArmee.MyDragShadowBuilder(groupe1);
            v.startDragAndDrop(dragData, myShadow,null,0);
            return true;
        });
        groupe3.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
            View.DragShadowBuilder myShadow = new RedeploiementArmee.MyDragShadowBuilder(groupe1);
            v.startDragAndDrop(dragData, myShadow,null,0);
            return true;
        });
        groupe4.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
            View.DragShadowBuilder myShadow = new RedeploiementArmee.MyDragShadowBuilder(groupe1);
            v.startDragAndDrop(dragData, myShadow,null,0);
            return true;
        });
        groupe5.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
            View.DragShadowBuilder myShadow = new RedeploiementArmee.MyDragShadowBuilder(groupe1);
            v.startDragAndDrop(dragData, myShadow,null,0);
            return true;
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
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(1, 1);
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(1, 2);
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(1, 3);
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(1, 4);
                    }
                    ((LinearLayout)v).addView(imageView, layoutParams);
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
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(2, 1);
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(2, 2);
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(2, 3);
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(2, 4);
                    }
                    ((LinearLayout)v).addView(imageView);

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
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(3, 1);
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(3, 2);
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(3, 3);
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(3, 4);
                    }
                    ((LinearLayout)v).addView(imageView);

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
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(4, 1);
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(4, 2);
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(4, 3);
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(4, 4);
                    }
                    ((LinearLayout)v).addView(imageView);

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
                    } else if (dragData.equals(groupe2.getTag())) {
                        imageView.setImageResource(R.drawable.logo_elite);
                        addSoldat(5, 1);
                    } else if (dragData.equals(groupe3.getTag())) {
                        imageView.setImageResource(R.drawable.logo_alpha);
                        addSoldat(5, 2);
                    } else if (dragData.equals(groupe4.getTag())) {
                        imageView.setImageResource(R.drawable.logo_bravo);
                        addSoldat(5, 3);
                    } else if (dragData.equals(groupe5.getTag())) {
                        imageView.setImageResource(R.drawable.logo_charlie);
                        addSoldat(5, 4);
                    }
                    ((LinearLayout)v).addView(imageView);
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
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
        private static Drawable shadow;

        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            int width, height;
            width = getView().getWidth() / 2;
            height = getView().getHeight() / 2;

            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow (Canvas canvas) {
            shadow.draw(canvas);
        }
    }
}