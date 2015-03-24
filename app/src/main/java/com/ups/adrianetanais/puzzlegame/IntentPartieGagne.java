package com.ups.adrianetanais.puzzlegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntentPartieGagne extends Activity {
    //objet qui vas utiliser le service vibrator
    Vibrator vibreur;
    TextView sContenu;
    private MediaPlayer mPlayer = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_partie_finie);

        vibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibreur.vibrate(1000);

        //Recuperer l'image et le niveau
        Intent i = getIntent();
        final int idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        final int difficulte = i.getIntExtra("DIFFICULTE",1);
        ImageView image = (ImageView)findViewById(R.id.imageFin);
        image.setImageResource(idImage);

        //Jouer le son
        playSound(R.raw.mario);

        Button btsauvegarde = (Button) findViewById(R.id.buttonProgression);
        btsauvegarde.setOnCreateContextMenuListener(this);
        //action sur le clique du bouton
        btsauvegarde.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IntentPartieGagne.this, IntentProgression.class);
                i.putExtra("IMAGE",idImage);
                i.putExtra("DIFFICULTE",difficulte);
                startActivity(i);
                finish();
            }
        });
    }
    private void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId);
        mPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
}
