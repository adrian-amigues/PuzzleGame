package com.ups.adrianetanais.puzzlegame;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class IntentPuzzle extends ActionBarActivity implements SensorEventListener {

    private static final int SHAKE_THRESHOLD = 400;
    private SensorManager sensorManager;
    private float lastX, lastY, lastZ;
    private long lastUpdate = -1;
    private long lastShake = -1;
    public int CHOOSE_BUTTON_REQUEST = 0;
    private int difficulte;
    private int idImage;
    private boolean aleatoire;
    private PlateauPuzzle plateau;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        this.difficulte =  i.getIntExtra("DIFFICULTE",1);
        this.idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        this.aleatoire = i.getBooleanExtra("ALEATOIRE",false);
        //Crée le plateau avec l'image, la difficulté et le placement aleatoire
        plateau = new PlateauPuzzle(this,this.difficulte,this.idImage,this.aleatoire);
        setContentView(plateau);
    }

    protected void onResume() {
        super.onResume();
        if (sensorManager == null) {
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            boolean accelSupported = sensorManager.registerListener(this
                    , sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                    , SensorManager.SENSOR_DELAY_GAME);
            if (!accelSupported) {
                System.out.println("Acceleration non supportée");
                sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            }
        }
    }

    protected void onPause() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            sensorManager = null;
        }
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x, y, z;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            long diffTime = curTime - lastUpdate;
            if (diffTime > 100) {
                lastUpdate = curTime;

                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

                float speed = Math.abs(x+y+z - lastX - lastY - lastZ) / diffTime * 1000;
                if (speed > SHAKE_THRESHOLD && (curTime - lastShake) > 400) {
                    lastShake = System.currentTimeMillis();
                    plateau.toggleShowPuzzleFinished();
                }
                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == this.RESULT_CANCELED) {
                // On affiche le bouton qui a été choisi
                plateau = new PlateauPuzzle(this,this.difficulte,this.idImage,this.aleatoire);
                setContentView(plateau);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
