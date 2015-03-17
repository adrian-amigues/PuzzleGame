package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class PlateauPuzzle extends View {
    private static final String DEBUG_TAG = "PlateauPuzzle";
    private Drawable mExampleDrawable;
    Bitmap myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
//    Bitmap myPict2 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2);
//    ImageView myPict2 = new Im


    public PlateauPuzzle(Context context) {
        super(context);
    }

    public PlateauPuzzle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlateauPuzzle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(myPict1, getLeft(), getTop(), null);
//        canvas.drawBitmap(myPict2, getLeft() + getWidth()/3, getTop() + myPict2.getHeight() + 20, null);

        // Draw the example drawable.
        if (mExampleDrawable != null) {
            mExampleDrawable.draw(canvas);
        }
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
