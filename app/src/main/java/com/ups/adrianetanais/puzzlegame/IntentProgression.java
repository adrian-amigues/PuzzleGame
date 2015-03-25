package com.ups.adrianetanais.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Anaïs on 23/03/2015.
 */
public class IntentProgression extends Activity {

    private ArrayList <Integer> tabImageEffectuees = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_progression);
        Intent i = getIntent();
        final int idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        final int difficulte = i.getIntExtra("DIFFICULTE",1);

        tabImageEffectuees.add(numeroTableauProgression(idImage,difficulte));
        for (int image : tabImageEffectuees)
            ajoutNiveau(image);


        Button btsauvegarde = (Button) findViewById(R.id.buttonMenu);
        btsauvegarde.setOnCreateContextMenuListener(this);
        //action sur le clique du bouton
        btsauvegarde.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IntentProgression.this, MainActivity.class);
                startActivity(i);
                onPause();

            }
        });
    }

    public void ajoutNiveau (int niveauEffectue){
        ImageView image = null;
        switch (niveauEffectue) {
            case 1 :
                image = (ImageView) findViewById (R.id.facileMer);
                break;
            case 2 :
                image = (ImageView) findViewById (R.id.moyenMer);
                break;
            case 3 :
                image = (ImageView) findViewById (R.id.difficileMer);
                break;
            case 4 :
                image = (ImageView) findViewById (R.id.facileDesert);
                break;
            case 5 :
                image = (ImageView) findViewById (R.id.moyenDesert);
                break;
            case 6 :
                image = (ImageView) findViewById (R.id.difficileDesert);
                break;
            case 7 :
                image = (ImageView) findViewById (R.id.facileMontagne);
                break;
            case 8 :
                image = (ImageView) findViewById (R.id.moyenMontagne);
                break;
            case 9 :
                image = (ImageView) findViewById (R.id.difficileMontagne);
                break;
        }
        if (image != null)
           image.setImageResource(R.drawable.vrai);
    }
     public int numeroTableauProgression (int idImage, int niveau){
        if (idImage == R.drawable.mer)
            return niveau;
        else if (idImage == R.drawable.desert)
            return niveau + 3;
        else
            return niveau + 6;
    }
}
