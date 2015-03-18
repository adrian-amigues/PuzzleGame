package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
}
