package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Anaïs on 18/03/2015.
 */
public class Image  extends View {
    private Drawable mExampleDrawable;
    protected Bitmap myPict1;
    protected Coordonnees coordonnees;

    public Image (/*String imageName,*/ float xCourant, float yCourant, float xFinal, float yFinal, Context context){
       super(context);
       myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
       coordonnees = new Coordonnees(xCourant,yCourant, xCourant + myPict1.getWidth(),yCourant + myPict1.getHeight(), xFinal, yFinal);
    }

    public Image(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Image(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(myPict1, coordonnees.getxHautGauche(), coordonnees.getyHautGauche(), null);

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
