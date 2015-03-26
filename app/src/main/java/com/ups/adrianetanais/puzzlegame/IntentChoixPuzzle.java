package com.ups.adrianetanais.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class IntentChoixPuzzle extends ActionBarActivity {

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_choix_puzzle);

        //On recupere le choix du niveau
        Intent i = getIntent();
        final int difficulte =  i.getIntExtra("DIFFICULTE",1);
        final boolean aleatoire = i.getBooleanExtra("ALEATOIRE",false);

        //On recupere le puzzle selectionne
        ImageButton choixMer = (ImageButton) findViewById(R.id.buttonMer);
        choixMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(IntentChoixPuzzle.this, IntentPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", difficulte);
                secondeActivite.putExtra("IMAGE",R.drawable.mer);
                secondeActivite.putExtra("ALEATOIRE",aleatoire);
                startActivity(secondeActivite);
                finish();
            }
        });
        ImageButton choixMontagne = (ImageButton) findViewById(R.id.buttonMontagne);
        choixMontagne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(IntentChoixPuzzle.this, IntentPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", difficulte);
                secondeActivite.putExtra("IMAGE",R.drawable.montagnes1);
                secondeActivite.putExtra("ALEATOIRE",aleatoire);
                startActivity(secondeActivite);
            }
        });
        ImageButton choixDesert = (ImageButton) findViewById(R.id.buttonDesert);
        choixDesert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(IntentChoixPuzzle.this, IntentPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", difficulte);
                secondeActivite.putExtra("IMAGE",R.drawable.desert);
                secondeActivite.putExtra("ALEATOIRE",aleatoire);
                startActivity(secondeActivite);
            }
        });
    }
}
