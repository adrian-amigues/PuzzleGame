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

//        printToFile();
        printFileContent();
        if (!fileIsValid()) {
            System.out.println("File is not valid");
            recreateFile();
        } else {
            System.out.println("File is valid");
        }
        printFileContent();

        Intent i = getIntent();
        final int idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        final int difficulte = i.getIntExtra("DIFFICULTE",1);

        System.out.println("Updating puzzles");
        updatePuzzlesDone(numeroTableauProgression(idImage, difficulte));
        printFileContent();

        for (int image : puzzlesDone)
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
                if (byteCount+1 == newPuzzleDone) {
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

    private boolean fileIsValid() {
        FileInputStream fis;
        try {
            fis = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Crash: openFileInput failed");
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

//    private void printToFile() {
//        String str = "010000110";
//        try {
//            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//            fos.write(str.getBytes());
//            fos.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

    private void printFileContent() {
        FileInputStream fis;
        try {
            fis = openFileInput(FILENAME);
            int currentByte;
            System.out.println("**** Printing file content ****");
            while ((currentByte = fis.read()) != -1) {
                System.out.print((char) currentByte);
            }
            System.out.println("");
            fis.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
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
