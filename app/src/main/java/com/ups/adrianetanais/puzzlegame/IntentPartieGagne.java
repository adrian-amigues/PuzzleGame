package com.ups.adrianetanais.puzzlegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Anaïs on 21/03/2015.
 */
public class IntentPartieGagne extends Activity {
    //objet qui vas utiliser le service vibrator
    Vibrator vibreur;
    TextView sContenu;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_partie_finie);
        //intanciation de l'objet levibreur
        vibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Vibreur pas ok, implementer l'affichage de l'image gagnante


        Button btsauvegarde = (Button) findViewById(R.id.buttonMenu);
//        vibreur.vibrate(100);
        btsauvegarde.setOnCreateContextMenuListener(this);
        //action sur le clique du bouton
        btsauvegarde.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IntentPartieGagne.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
