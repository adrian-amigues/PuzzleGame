package com.ups.adrianetanais.puzzlegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button choixFacile = (Button) findViewById(R.id.facile);
        choixFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(MainActivity.this, IntentChoixPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", 1);
                startActivity(secondeActivite);
            }
        });
        Button choixMoyen = (Button) findViewById(R.id.moyen);
        choixMoyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(MainActivity.this, IntentChoixPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", 2);
                startActivity(secondeActivite);
            }
        });
        Button choixDifficile = (Button) findViewById(R.id.difficile);
        choixDifficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(MainActivity.this, IntentChoixPuzzle.class);
                secondeActivite.putExtra("DIFFICULTE", 3);
                startActivity(secondeActivite);
            }
        });

        Button buttunExit = (Button)findViewById(R.id.buttonQuitter);
        buttunExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        Button buttunProgression = (Button) findViewById(R.id.buttonProgression);
        buttunProgression.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, IntentProgression.class);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_informations){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setPositiveButton("Ok", null);
            adb.setTitle("A Propos");
            adb.setMessage("Application Réalisée par Adrian Amigues et Anaïs Cannac");
            adb.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
