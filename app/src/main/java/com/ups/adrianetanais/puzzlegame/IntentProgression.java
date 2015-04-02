package com.ups.adrianetanais.puzzlegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anaïs on 23/03/2015.
 */
public class IntentProgression extends Activity {

    public static final String FILENAME = "progression.txt";
    public static final int NUMBER_OF_DIFFICULTIES = 3;
    public static final int NUMBER_OF_PUZZLES = 3;
    public static final int NORMAL_NUMBER_OF_BYTES = NUMBER_OF_DIFFICULTIES * NUMBER_OF_PUZZLES;

    private ArrayList <Integer> puzzlesDone = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_progression);

        if (!fileIsValid()) {
            recreateFile();
        }

        Intent i = getIntent();
        final int idImage = i.getIntExtra("IMAGE", -1);
        final int difficulte = i.getIntExtra("DIFFICULTE",1);

        updatePuzzlesDone(numeroTableauProgression(idImage, difficulte));
        for (int image : puzzlesDone)
            ajoutNiveau(image);

        Button progressionButton = (Button) findViewById(R.id.buttonMenu);
        progressionButton.setOnCreateContextMenuListener(this);
        //action sur le clique du bouton
        progressionButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IntentProgression.this, MainActivity.class);
                startActivity(i);
                onPause();

            }
        });
    }

    /**
     * Met à jour la liste des puzzles terminés avec le fichier de sauvegarde de la progression
     * et le dèrnier puzzle terminé.
     * @param newPuzzleDone
     */
    private void updatePuzzlesDone(int newPuzzleDone) {
        FileInputStream fis;
        try {
            fis = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Crash: openFileInput failed");
        }
        int currentByte;
        int byteCount = 0;
        try {
            while((currentByte = fis.read()) != -1) {
                if (newPuzzleDone != -1 && byteCount+1 == newPuzzleDone) {
                    puzzlesDone.add(newPuzzleDone);
                } else if (currentByte == '1') {
                    puzzlesDone.add(byteCount + 1);
                }
                byteCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updatePuzzlesDoneFile();
    }

    /**
     * Met à jour le fichier de sauvegarde de la progression avec la liste des puzzles terminés.
     */
    private void updatePuzzlesDoneFile() {
        FileOutputStream fos;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (int i = 0; i < NORMAL_NUMBER_OF_BYTES; i++) {
                if (puzzlesDone.contains(i+1)) {
                    fos.write('1');
                } else {
                    fos.write('0');
                }
            }
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Vérifie que le fichier de progression soit d'un format valide.
     * @return vrai si le fichier est valide
     */
    private boolean fileIsValid() {
        FileInputStream fis;
        try {
            fis = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            // On crée le fichier
            FileOutputStream fos;
            System.out.println("Creating progression file !");
            try {
                fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return false;
        }
        int byteCount = 0;
        int currentByte;
        try {
            while ((currentByte = fis.read()) != -1) {
                if (currentByte != '0' && currentByte != '1') {
                    return false;
                } else if (byteCount > NORMAL_NUMBER_OF_BYTES) {
                    return false;
                } else {
                    byteCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (byteCount == NORMAL_NUMBER_OF_BYTES);
    }

    /**
     * Recrée le fichier de progression avec tous les puzzles non terminés.
     */
    private void recreateFile() {
        FileOutputStream fos;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (int i = 0; i < NORMAL_NUMBER_OF_BYTES; i++) {
                fos.write('0');
            }
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Sélectionne l'ImageView à mettre à jour en fonction du puzzle terminé et de la difficulté.
     * @param niveauEffectue
     */
    public void ajoutNiveau (int niveauEffectue){
        ImageView image = null;
        switch (niveauEffectue) {
            case 1 :
                image = (ImageView) findViewById (R.id.facileDesert);
                break;
            case 2 :
                image = (ImageView) findViewById (R.id.moyenDesert);
                break;
            case 3 :
                image = (ImageView) findViewById (R.id.difficileDesert);
                break;
            case 4 :
                image = (ImageView) findViewById (R.id.facileMer);
                break;
            case 5 :
                image = (ImageView) findViewById (R.id.moyenMer);
                break;
            case 6 :
                image = (ImageView) findViewById (R.id.difficileMer);
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

    /**
     * Retourne le numéro correspondant à l'image associé à sa difficulté
     * Pour 3 images et 3 difficultés, sera entre 1 et 9
     * @param idImage
     * @param niveau
     * @return
     */
     public int numeroTableauProgression (int idImage, int niveau){
         if (idImage == R.drawable.desert)
             return niveau;
         else if (idImage == R.drawable.mer)
             return niveau + 3;
         else if (idImage == R.drawable.montagnes1)
             return niveau + 6;
         else
             return -1;
    }
}
