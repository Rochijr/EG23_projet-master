package com.duolight.projeteg23.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duolight.projeteg23.R;
import com.duolight.projeteg23.model.Armee;
import com.duolight.projeteg23.model.Soldats;

import java.util.Arrays;

public class RepartitionPoints extends AppCompatActivity {

    private static final String SHARED_PREF_JOUEUR_1_INFO = "SHARED_PREF_JOUEUR_1_INFO"; // nom du fichier
    private static final String SHARED_PREF_JOUEUR_1_INFO_KEY = "SHARED_PREF_JOUEUR_1_INFO_KEY";//Key
    private TextView mBranche;
    private int points;

    private TextView mPointsRestants;
    // Création de text View pour les caractéristiques des soldats de l'armée
    private TextView mForceChef;
    private TextView mDexteriteChef;
    private TextView mResistanceChef;
    private TextView mConstitutionChef;
    private TextView mInitiativeChef;

    private TextView mForceElite;
    private TextView mDexteriteElite;
    private TextView mResistanceElite;
    private TextView mConstitutionElite;
    private TextView mInitiativeElite;

    private TextView mForceAlpha;
    private TextView mDexteriteAlpha;
    private TextView mResistanceAlpha;
    private TextView mConstitutionAlpha;
    private TextView mInitiativeAlpha;

    private TextView mForceBravo;
    private TextView mDexteriteBravo;
    private TextView mResistanceBravo;
    private TextView mConstitutionBravo;
    private TextView mInitiativeBravo;

    private TextView mForceCharlie;
    private TextView mDexteriteCharlie;
    private TextView mResistanceCharlie;
    private TextView mConstitutionCharlie;
    private TextView mInitiativeCharlie;

    Armee armeeDeBase = generateArmee(1);

    int[] caracteristiqueChef;
    int[] caracteristiqueElite;
    int[] caracteristiqueAlpha;
    int[] caracteristiqueBravo;
    int[] caracteristiqueCharlie;

    //Creation des TextView de la popUp
    private TextView mPopUp_title;
    private TextView mPopUp_force;
    private TextView mPopUp_dexterite;
    private TextView mPopUp_resistance;
    private TextView mPopUp_constitution;
    private TextView mPopUp_initiative;
    private TextView mPopUp_offensive;

    private Button mForceMoins;
    private Button mForcePlus;
    private Button mDexteriteMoins;
    private Button mDexteritePlus;
    private Button mResistanceMoins;
    private Button mResistancePlus;
    private Button mConstitutionMoins;
    private Button mConstitutionPlus;
    private Button mInitiativeMoins;
    private Button mInitiativePlus;

    private static int compteurForce=0;
    private static int compteurDexterite=0;
    private static int compteurResistance=0;
    private static int compteurConstitution=0;
    private static int compteurInitiative=0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartition_points);
         points = 400;

        //Affiche la Branche du Joueur
        mBranche=findViewById(R.id.repartition_points_joueur_branche);
        String brancheJoueur1 = getSharedPreferences(SHARED_PREF_JOUEUR_1_INFO,MODE_PRIVATE).getString(SHARED_PREF_JOUEUR_1_INFO_KEY,null);
        mBranche.setText("Joueur 1 : " + brancheJoueur1);


        mPointsRestants = findViewById(R.id.repartition_points_restants);
        //Affiche les caractérisque des Soldats de l'Armee
        mForceChef = findViewById(R.id.repartition_points_textView_Chef_force);
        mDexteriteChef= findViewById(R.id.repartition_points_textView_Chef_dexterite);
        mResistanceChef= findViewById(R.id.repartition_points_textView_Chef_resistance);
        mConstitutionChef= findViewById(R.id.repartition_points_textView_Chef_constitution);
        mInitiativeChef= findViewById(R.id.repartition_points_textView_Chef_initiative);

        mForceElite = findViewById(R.id.repartition_points_textView_Elite_force);
        mDexteriteElite = findViewById(R.id.repartition_points_textView_Elite_dexterite);
        mResistanceElite = findViewById(R.id.repartition_points_textView_Elite_resistance);
        mConstitutionElite = findViewById(R.id.repartition_points_textView_Elite_constitution);
        mInitiativeElite = findViewById(R.id.repartition_points_textView_Elite_initiative);

        mForceAlpha = findViewById(R.id.repartition_points_textView_Alpha_force);
        mDexteriteAlpha = findViewById(R.id.repartition_points_textView_Alpha_dexterite);
        mResistanceAlpha = findViewById(R.id.repartition_points_textView_Alpha_resistance);
        mConstitutionAlpha = findViewById(R.id.repartition_points_textView_Alpha_constitution);
        mInitiativeAlpha = findViewById(R.id.repartition_points_textView_Alpha_initiative);

        mForceBravo = findViewById(R.id.repartition_points_textView_Bravo_force);
        mDexteriteBravo = findViewById(R.id.repartition_points_textView_Bravo_dexterite);
        mResistanceBravo = findViewById(R.id.repartition_points_textView_Bravo_resistance);
        mConstitutionBravo = findViewById(R.id.repartition_points_textView_Bravo_constitution);
        mInitiativeBravo = findViewById(R.id.repartition_points_textView_Bravo_initiative);

        mForceCharlie = findViewById(R.id.repartition_points_textView_Charlie_force);
        mDexteriteCharlie = findViewById(R.id.repartition_points_textView_Charlie_dexterite);
        mResistanceCharlie = findViewById(R.id.repartition_points_textView_Charlie_resistance);
        mConstitutionCharlie = findViewById(R.id.repartition_points_textView_Charlie_constitution);
        mInitiativeCharlie = findViewById(R.id.repartition_points_textView_Charlie_initiative);

        caracteristiqueChef=armeeDeBase.getCaracteristiqueSoldat(0);
        caracteristiqueElite=armeeDeBase.getCaracteristiqueSoldat(1);
        caracteristiqueAlpha=armeeDeBase.getCaracteristiqueSoldat(2);
        caracteristiqueBravo=armeeDeBase.getCaracteristiqueSoldat(3);
        caracteristiqueCharlie=armeeDeBase.getCaracteristiqueSoldat(4);

        mPointsRestants.setText("Points Restants : "+points);

        mForceChef.setText("Force : "+caracteristiqueChef[1]);
        mDexteriteChef.setText("Dextérité : "+caracteristiqueChef[2]);
        mResistanceChef.setText("Résistance : "+caracteristiqueChef[3]);
        mConstitutionChef.setText("Constitution : "+caracteristiqueChef[4]);
        mInitiativeChef.setText("Initiative : "+caracteristiqueChef[5]);

        mForceElite.setText("Force : "+caracteristiqueElite[1]);
        mDexteriteElite.setText("Dextérité : "+caracteristiqueElite[2]);
        mResistanceElite.setText("Résistance : "+caracteristiqueElite[3]);
        mConstitutionElite.setText("Constitution : "+caracteristiqueElite[4]);
        mInitiativeElite.setText("Initiative : "+caracteristiqueElite[5]);

        mForceAlpha.setText("Force : "+caracteristiqueAlpha[1]);
        mDexteriteAlpha.setText("Dextérité : "+caracteristiqueAlpha[2]);
        mResistanceAlpha.setText("Résistance : "+caracteristiqueAlpha[3]);
        mConstitutionAlpha.setText("Constitution : "+caracteristiqueAlpha[4]);
        mInitiativeAlpha.setText("Initiative : "+caracteristiqueAlpha[5]);

        mForceBravo.setText("Force : "+caracteristiqueBravo[1]);
        mDexteriteBravo.setText("Dextérité : "+caracteristiqueBravo[2]);
        mResistanceBravo.setText("Résistance : "+caracteristiqueBravo[3]);
        mConstitutionBravo.setText("Constitution : "+caracteristiqueBravo[4]);
        mInitiativeBravo.setText("Initiative : "+caracteristiqueBravo[5]);

        mForceCharlie.setText("Force : "+caracteristiqueCharlie[1]);
        mDexteriteCharlie.setText("Dextérité : "+caracteristiqueCharlie[2]);
        mResistanceCharlie.setText("Résistance : "+caracteristiqueCharlie[3]);
        mConstitutionCharlie.setText("Constitution : "+caracteristiqueCharlie[4]);
        mInitiativeCharlie.setText("Initiative : "+caracteristiqueCharlie[5]);


        final ImageView mArrowRight= findViewById(R.id.repartition_points_arrow_right);
        mArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //----------------------------------------ICI changement de Page------------------------------------//
                Intent intent = new Intent(RepartitionPoints.this, Reserve.class);
                // Exécution de l’activité : ouverture de la fenêtre
                startActivity(intent);
            }
        });
        // Liaison entre l’objet graphique R.id.button et la variable listeCourseButton
        final LinearLayout mLayoutChefDeGuerre = (LinearLayout ) findViewById(R.id.repartition_points_chefDeGuerre);
        // Création d’un évènement qui attend un clic sur le bouton
        mLayoutChefDeGuerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //Appel une pop-up
                compteurForce=0;
                compteurDexterite=0;
                Soldats newSoldats = myCustomAlertDialog(0);
            }
        });
        // Liaison entre l’objet graphique R.id.button et la variable listeCourseButton
        final LinearLayout mLayoutSoldatElite = (LinearLayout ) findViewById(R.id.repartition_points_soldatElite);
        // Création d’un évènement qui attend un clic sur le bouton
        mLayoutSoldatElite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //Appel une pop-up
                initialisationCompteur();
                Soldats newSoldats = myCustomAlertDialog(1);
            }
        });
        // Liaison entre l’objet graphique R.id.button et la variable listeCourseButton
        final LinearLayout mLayoutAlpha = (LinearLayout ) findViewById(R.id.repartition_points_Alpha);
        // Création d’un évènement qui attend un clic sur le bouton
        mLayoutAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //Appel une pop-up
                initialisationCompteur();
                Soldats newSoldats = myCustomAlertDialog(2);
            }
        });
        // Liaison entre l’objet graphique R.id.button et la variable listeCourseButton
        final LinearLayout mLayoutBravo = (LinearLayout ) findViewById(R.id.repartition_points_Bravo);
        // Création d’un évènement qui attend un clic sur le bouton
        mLayoutBravo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //Appel une pop-up
                initialisationCompteur();
                Soldats newSoldats = myCustomAlertDialog(3);
            }
        });
        // Liaison entre l’objet graphique R.id.button et la variable listeCourseButton
        final LinearLayout mLayoutCharlie = (LinearLayout ) findViewById(R.id.repartition_points_Charlie);
        // Création d’un évènement qui attend un clic sur le bouton
        mLayoutCharlie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //Appel une pop-up
                initialisationCompteur();
                Soldats newSoldats = myCustomAlertDialog(4);
            }
        });

    }

    private Soldats myCustomAlertDialog(int typeSoldat){

        // récupérer le soldat
        Soldats soldat = armeeDeBase.getSoldat(typeSoldat);
        // crée la popUp
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View popUpView = getLayoutInflater().inflate(R.layout.popup, null);

        mPopUp_title = (TextView) popUpView.findViewById(R.id.popUp_title);
        mPopUp_force = (TextView) popUpView.findViewById(R.id.popUp_force);
        mPopUp_dexterite = (TextView) popUpView.findViewById(R.id.popUp_dexterite);
        mPopUp_resistance = (TextView) popUpView.findViewById(R.id.popUp_resistance);
        mPopUp_constitution = (TextView) popUpView.findViewById(R.id.popUp_constitution);
        mPopUp_initiative = (TextView) popUpView.findViewById(R.id.popUp_initiative);

        mForcePlus = (Button) popUpView.findViewById(R.id.popUp_button_force_plus);
        mForceMoins = (Button) popUpView.findViewById(R.id.popUp_button_force_moins);
        mDexteritePlus = (Button) popUpView.findViewById(R.id.popUp_button_dexterite_plus);
        mDexteriteMoins = (Button) popUpView.findViewById(R.id.popUp_button_dexterite_moins);
        mResistancePlus = (Button) popUpView.findViewById(R.id.popUp_button_resistance_plus);
        mResistanceMoins = (Button) popUpView.findViewById(R.id.popUp_button_resistance_moins);
        mConstitutionPlus = (Button) popUpView.findViewById(R.id.popUp_button_constitution_plus);
        mConstitutionMoins = (Button) popUpView.findViewById(R.id.popUp_button_constitution_moins);
        mInitiativePlus = (Button) popUpView.findViewById(R.id.popUp_button_initiative_plus);
        mInitiativeMoins = (Button) popUpView.findViewById(R.id.popUp_button_initiative_moins);

        mPopUp_title.setText(soldat.nom());
        mPopUp_force.setText("Force : " +soldat.getForce());
        mPopUp_dexterite.setText("Dexterite : " +soldat.getDexterite());
        mPopUp_resistance.setText("Resistance : " +soldat.getResistance());
        mPopUp_constitution.setText("Constitution : " +soldat.getConstitution());
        mPopUp_initiative.setText("Initiative : " +soldat.getInitiative());

        // on clic
        mForcePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //augmente la force
                if (points > 0) {
                    mPopUp_force.setText("Force : " + (soldat.getForce() + 1));
                    displayforce(typeSoldat, 1, (soldat.getForce() + 1));
                } else points = 0;
            }
        });
        mForceMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //diminue la force
                if (soldat.getForce()>0) {
                    compteurForce--;
                    mPopUp_force.setText("Force : " + (soldat.getForce() - 1));
                    displayforce(typeSoldat, -1, (soldat.getForce() - 1));
                }
            }
        });

        mDexteritePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //augmente la dextérité
                if (points > 0) {
                    mPopUp_dexterite.setText("Dexterite : " + (soldat.getDexterite() + 1));
                    displayDexterite(typeSoldat, 1, (soldat.getDexterite() + 1));
                } else points = 0;
            }
        });
        mDexteriteMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //diminue la dextérité
                if (soldat.getDexterite()>0) {
                    mPopUp_dexterite.setText("Dexterite : " +(soldat.getDexterite() - 1));
                    displayDexterite(typeSoldat,-1,(soldat.getDexterite() - 1));
                }
            }
        });

        mResistancePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //augmente la résistance
                if (points > 0) {
                    mPopUp_resistance.setText("Resistance : " +(soldat.getResistance() + 1));
                    displayResistance(typeSoldat,1,(soldat.getResistance() + 1));
                } else points = 0;
            }
        });
        mResistanceMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //diminue la résistance
                if (soldat.getResistance()>0) {
                    mPopUp_resistance.setText("Resistance : " + (soldat.getResistance() - 1));
                    displayResistance(typeSoldat, -1, (soldat.getResistance() - 1));
                }
            }
        });

        mConstitutionPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //augmente la constitution
                if (points > 0) {
                    mPopUp_constitution.setText("Constitution : " +(soldat.getConstitution() + 1));
                    displayConstitution(typeSoldat,1,(soldat.getConstitution() + 1));
                } else points = 0;
            }
        });
        mConstitutionMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //diminue la constitution
                if (soldat.getConstitution()>0) {
                    mPopUp_constitution.setText("Constitution : " + (soldat.getConstitution() - 1));
                    displayConstitution(typeSoldat, -1, (soldat.getConstitution() - 1));
                }
            }
        });

        mInitiativePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //augmente l'initiative'
                if (points > 0) {
                    mPopUp_initiative.setText("Initiative : " + (soldat.getInitiative() + 1));
                    displayInitiative(typeSoldat, 1, (soldat.getInitiative() + 1));
                } else points = 0;
            }
        });
        mInitiativeMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                //diminue l'initiative'
                if (soldat.getInitiative()>0) {
                    //compteurInitiative--;
                    //mPopUp_initiative.setText("Initiative : " + (soldat.getInitiative() + compteurInitiative));
                    //displayInitiative(typeSoldat, -1, (soldat.getInitiative() + compteurInitiative));
                    //compteurInitiative--;
                    mPopUp_initiative.setText("Initiative : " + (soldat.getInitiative() - 1));
                    displayInitiative(typeSoldat, -1, (soldat.getInitiative() - 1));
                }
            }
        });
        builder.setView(popUpView);
        builder.create();
        builder.show();
        Soldats newSoldat = new Soldats(soldat.nom(),30,compteurForce,2,2,2,2,2);
        return newSoldat;
    }

    public void displayforce(int typeSoldat, int signe, int valeur) {
        switch (typeSoldat) {
            case 0:
                caracteristiqueChef[1] = valeur;
                mForceChef.setText("Force : " + valeur);
                points-=signe;
                break;
            case 1:
                caracteristiqueElite[1] = valeur;
                mForceElite.setText("Force : " + valeur);
                points-=(4*signe);
                break;
            case 2:
                caracteristiqueAlpha[1] = valeur;
                mForceAlpha.setText("Force : " + valeur);
                points-=(5*signe);
                break;
            case 3:
                caracteristiqueBravo[1] = valeur;
                mForceBravo.setText("Force : " + valeur);
                points-=(5*signe);
                break;
            case 4:
                caracteristiqueCharlie[1] = valeur;
                mForceCharlie.setText("Force : " + valeur);
                points-=(5*signe);
                break;
        }
        mPointsRestants.setText("Points Restants : "+points);
    }
    public void displayDexterite(int typeSoldat,int signe, int valeur){
        switch (typeSoldat){
            case 0 :
                caracteristiqueChef[2] = valeur;
                mDexteriteChef.setText("Dexterite : "+valeur);
                points-=signe;
                break;
            case 1 :
                caracteristiqueElite[2] = valeur;
                mDexteriteElite.setText("Dexterite : "+valeur);
                points-=(4*signe);
                break;
            case 2 :
                caracteristiqueAlpha[2] = valeur;
                mDexteriteAlpha.setText("Dexterite : "+valeur);
                points-=(5*signe);
                break;
            case 3 :
                caracteristiqueBravo[2] = valeur;
                mDexteriteBravo.setText("Dexterite : "+valeur);
                points-=(5*signe);
                break;
            case 4 :
                caracteristiqueCharlie[2] = valeur;
                mDexteriteCharlie.setText("Dexterite : "+valeur);
                points-=(5*signe);
                break;
        }
        mPointsRestants.setText("Points Restants : "+points);
    }

    public void displayResistance(int typeSoldat,int signe, int valeur){
        switch (typeSoldat){
            case 0 :
                caracteristiqueChef[3] = valeur;
                mResistanceChef.setText("Resistance : "+valeur);
                points-=signe;
                break;
            case 1 :
                caracteristiqueElite[3] = valeur;
                mResistanceElite.setText("Resistance : "+valeur);
                points-=(4*signe);
                break;
            case 2 :
                caracteristiqueAlpha[3] = valeur;
                mResistanceAlpha.setText("Resistance : "+valeur);
                points-=(5*signe);
                break;
            case 3 :
                caracteristiqueBravo[3] = valeur;
                mResistanceBravo.setText("Resistance : "+valeur);
                points-=(5*signe);
                break;
            case 4 :
                caracteristiqueCharlie[3] = valeur;
                mResistanceCharlie.setText("Resistance : "+valeur);
                points-=(5*signe);
                break;
        }
        mPointsRestants.setText("Points Restants : "+points);
    }
    public void displayConstitution(int typeSoldat,int signe, int valeur){
        switch (typeSoldat){
            case 0 :
                caracteristiqueChef[4] = valeur;
                mConstitutionChef.setText("Constitution : "+valeur);
                points-=signe;
                break;
            case 1 :
                caracteristiqueElite[4] = valeur;
                mConstitutionElite.setText("Constitution : "+valeur);
                points-=(4*signe);
                break;
            case 2 :
                caracteristiqueAlpha[4] = valeur;
                mConstitutionAlpha.setText("Constitution : "+valeur);
                points-=(5*signe);
                break;
            case 3 :
                caracteristiqueBravo[4] = valeur;
                mConstitutionBravo.setText("Constitution : "+valeur);
                points-=(5*signe);
                break;
            case 4 :
                caracteristiqueCharlie[4] = valeur;
                mConstitutionCharlie.setText("Constitution : "+valeur);
                points-=(5*signe);
                break;
        }
        mPointsRestants.setText("Points Restants : "+points);
    }
    public void displayInitiative(int typeSoldat, int signe, int valeur){
        switch (typeSoldat){
            case 0 :
                caracteristiqueChef[5] = valeur;
                mInitiativeChef.setText("Initiative : "+ valeur);
                points-=signe;
                break;
            case 1 :
                caracteristiqueElite[5] = valeur;
                mInitiativeElite.setText("Initiative : " + valeur);
                points-=(4*signe);
                break;
            case 2 :
                caracteristiqueAlpha[5] = valeur;
                mInitiativeAlpha.setText("Initiative : " + valeur);
                points-=(5*signe);
                break;
            case 3 :
                caracteristiqueBravo[5] = valeur;
                mInitiativeBravo.setText("Initiative : " + valeur);
                points-=(5*signe);
                break;
            case 4 :
                caracteristiqueCharlie[5] = valeur;
                mInitiativeCharlie.setText("Initiative : " + valeur);
                points-=(5*signe);
                break;
        }
        mPointsRestants.setText("Points Restants : "+points);
    }

    public void initialisationCompteur(){
        compteurForce=0;
        compteurDexterite=0;
        compteurResistance=0;
        compteurConstitution=0;
        compteurInitiative=0;
    }
    public Armee generateArmee(int joueur){
        Soldats chefDeGuerre = new Soldats(
                "chef de Guerre ",
                30,
                2,
                2,
                2,
                10,
                2,
                0

        );
        Soldats soldatsElite1 = new Soldats(
                "Soldat d'élite ",
                30,
                1,
                1,
                1,
                5,
                1,
                0
        );
        Soldats soldatsAlpha1 = new Soldats(
                "Soldat Alpha ",
                30,
                0,
                0,
                0,
                0,
                0,
                0
        );
        Soldats soldatsBravo1 = new Soldats(
                "Soldat Bravo ",
                30,
                0,
                0,
                0,
                0,
                0,
                0
        );
        Soldats soldatsCharlie1 = new Soldats(
                "Soldat Charlie ",
                30,
                0,
                0,
                0,
                0,
                0,
                0
        );
        return new Armee(Arrays.asList(chefDeGuerre), Arrays.asList(soldatsElite1),
                Arrays.asList(soldatsAlpha1),Arrays.asList(soldatsBravo1),Arrays.asList(soldatsCharlie1),joueur);
    }
}