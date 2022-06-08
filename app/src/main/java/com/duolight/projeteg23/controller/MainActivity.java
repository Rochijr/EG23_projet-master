package com.duolight.projeteg23.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.duolight.projeteg23.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Termine l'activité avec le bouton quitter de la fin de partie
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        final TextView nouvellePartie = (TextView) findViewById(R.id.main_activity_nouvelle_partie);
        nouvellePartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                // Création d’une activité associée à l’exécution de MaGestionListe.class
                Intent intent = new Intent(MainActivity.this, ChoixProgramme.class);
                // Exécution de l’activité : ouverture de la fenêtre
                startActivity(intent);
            }
        });
        final TextView continuer = (TextView) findViewById(R.id.continuer);
        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                // Création d’une activité associée à l’exécution de MaGestionListe.class
                Intent intent = new Intent(MainActivity.this, DeploiementArmee.class);
                // Exécution de l’activité : ouverture de la fenêtre
                startActivity(intent);
            }
        });

        final TextView quitter = (TextView) findViewById(R.id.textView4);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
        final TextView didacticiel = (TextView) findViewById(R.id.textView3);
        didacticiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                // Création d’une activité associée à l’exécution de MaGestionListe.class
                Intent intent = new Intent(MainActivity.this, DidacticielGeneral.class);
                // Exécution de l’activité : ouverture de la fenêtre
                startActivity(intent);
            }
        });
    }
}