package com.duolight.projeteg23.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duolight.projeteg23.R;

public class DidacticielGeneral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didacticiel_general);

        final TextView croix = (TextView) findViewById(R.id.didacticielG_croix);
        croix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                // Création d’une activité associée à l’exécution de MaGestionListe.class
                Intent intent = new Intent(DidacticielGeneral.this, MainActivity.class);
                // Exécution de l’activité : ouverture de la fenêtre
                finish();
                startActivity(intent);
            }
        });
        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                pageSuivanteDidacticiel();
            }
        });

        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                pagePrecedenteDidacticiel();
            }
        });
    }
    public void pageSuivanteDidacticiel() {
        // Création d’une activité associée à l’exécution de MaGestionListe.class
        Intent intent = new Intent(DidacticielGeneral.this, Didacticiel.class);
        // Exécution de l’activité : ouverture de la fenêtre
        startActivity(intent);
    }
    public void pagePrecedenteDidacticiel() {
        // Création d’une activité associée à l’exécution de MaGestionListe.class
        Intent intent = new Intent(DidacticielGeneral.this, Didacticiel_2.class);
        // Exécution de l’activité : ouverture de la fenêtre
        startActivity(intent);
    }
}