package com.ups.adrianetanais.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class IntentPuzzle extends ActionBarActivity {

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int difficulte =  i.getIntExtra("DIFFICULTE",1);
        int idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        PlateauPuzzle plateau = new PlateauPuzzle(this,difficulte,idImage);
        setContentView(plateau);
    }

}
