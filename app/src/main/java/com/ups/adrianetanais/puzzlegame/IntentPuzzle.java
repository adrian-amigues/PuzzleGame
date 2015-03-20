package com.ups.adrianetanais.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Anaïs on 20/03/2015.
 */
public class IntentPuzzle extends ActionBarActivity {
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlateauPuzzle plateau = new PlateauPuzzle(this);
        setContentView(plateau);
    }

}
