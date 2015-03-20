package com.ups.adrianetanais.puzzlegame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class IntentPuzzle extends ActionBarActivity {
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlateauPuzzle plateau = new PlateauPuzzle(this);
        setContentView(plateau);
    }
}
