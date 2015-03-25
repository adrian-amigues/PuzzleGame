package com.ups.adrianetanais.puzzlegame;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class IntentPuzzle extends ActionBarActivity implements SensorEventListener {

    private static final int SHAKE_THRESHOLD = 600;
    private SensorManager sensorManager;
    private float x, y, z;
    private float lastX, lastY, lastZ;
    private long lastUpdate = -1;
    private long lastShake = -1;
    private boolean isShaked = false;

    private PlateauPuzzle plateau;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int difficulte =  i.getIntExtra("DIFFICULTE",1);
        int idImage = i.getIntExtra("IMAGE",R.drawable.montagnes1);
        plateau = new PlateauPuzzle(this,difficulte,idImage);
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
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            long diffTime = curTime - lastUpdate;
            if (diffTime > 100) {
                lastUpdate = curTime;

                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

                float speed = Math.abs(x+y+z - lastX - lastY - lastZ) / diffTime * 1000;
                if (speed > SHAKE_THRESHOLD && (curTime - lastShake) > 200) {
                    // On a bien secoué l'écran
                    isShaked = true;
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
