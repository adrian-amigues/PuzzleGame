package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Adrian on 18/03/2015.
 */
public class PlateauSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap mBitmap;
    private ViewThread mThread;

    private int mX;
    private int mY;

    public PlateauSurfaceView(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
        getHolder().addCallback(this);
        mThread = new ViewThread(this);
    }

    public void doDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        Paint paint = new Paint();
        canvas.drawLine(33, 0, 33, 100, paint);
        paint.setColor(Color.RED); paint.setStrokeWidth(10); canvas.drawLine(56, 0, 56, 100, paint);
        paint.setColor(Color.GREEN); paint.setStrokeWidth(25);
        for (int y = 30, alpha = 255; alpha > 2; alpha >>= 1, y += 10) {
            paint.setAlpha(alpha);

            canvas.drawLine(0, y, 100, y, paint);
        }

        canvas.drawBitmap(mBitmap, mX, mY, null);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
// TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!mThread.isAlive()) {
            mThread = new ViewThread(this);
            mThread.setRunning(true);
            mThread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mThread.isAlive()) {
            mThread.setRunning(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int) event.getX() - mBitmap.getWidth() / 2;
        mY = (int) event.getY() - mBitmap.getHeight() / 2;
        return super.onTouchEvent(event);
    }
}
