package com.ups.adrianetanais.puzzlegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Adrian on 18/03/2015.
 */
public class ViewThread extends Thread {
    private PlateauSurfaceView plateau;
    private SurfaceHolder mHolder;
    private boolean mRun = false;

    public ViewThread(PlateauSurfaceView panel) {
        plateau = panel;
        mHolder = plateau.getHolder();
    }

    public void setRunning(boolean run) {
        mRun = run;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        while (mRun) {
            canvas = mHolder.lockCanvas();
            if (canvas != null) {
                plateau.doDraw(canvas);
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
