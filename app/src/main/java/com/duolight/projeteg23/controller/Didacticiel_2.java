package com.duolight.projeteg23.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duolight.projeteg23.R;
import com.duolight.projeteg23.model.Armee;
import com.duolight.projeteg23.model.Soldats;

import java.util.Arrays;

public class Didacticiel_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didacticiel2);

        final Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                pageSuivanteDidacticiel();
            }
        });

        final Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                pagePrecedenteDidacticiel();
            }
        });
        final TextView croix = (TextView) findViewById(R.id.didacticiel_2_croix2);
        croix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fonction déclenchée sur le clic du bouton
                Intent intent = new Intent(Didacticiel_2.this, MainActivity.class);
                // Exécution de l’activité : ouverture de la fenêtre
                finish();
                startActivity(intent);
            }
        });
    }

    public void pageSuivanteDidacticiel() {
        // Création d’une activité associée à l’exécution de MaGestionListe.class
        Intent intent = new Intent(Didacticiel_2.this, DidacticielGeneral.class);
        // Exécution de l’activité : ouverture de la fenêtre
        startActivity(intent);
    }
    public void pagePrecedenteDidacticiel() {
        // Création d’une activité associée à l’exécution de MaGestionListe.class
        Intent intent = new Intent(Didacticiel_2.this, Didacticiel.class);
        // Exécution de l’activité : ouverture de la fenêtre
        startActivity(intent);
    }
}