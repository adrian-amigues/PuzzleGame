package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlateauPuzzle extends View {
    private Drawable mExampleDrawable;
    private List<ImagePuzzle> imageList = new LinkedList<>();
    private ImagePuzzle imageSelectionnee = null;
//    private Bitmap myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
//    private Bitmap myPict2 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2);

    private int mX;
    private int mY;
    private int decalageX;
    private int decalageY;

    public PlateauPuzzle(Context context) {
        super(context);
        ImagePuzzle image1 = new ImagePuzzle(BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1), 0, 0, true,0,0);
        ImagePuzzle image2 = new ImagePuzzle(BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2), image1.getBitmap().getWidth() - 100, 0,false,image1.getX() + image1.getBitmap().getWidth(), image1.getY());

        imageList.add(image1);
        imageList.add(image2);
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
        for (Iterator<ImagePuzzle> iter = ((LinkedList<ImagePuzzle>) imageList).descendingIterator(); iter.hasNext(); ) {
            ImagePuzzle currentImage = iter.next();
            canvas.drawBitmap(currentImage.getBitmap(), currentImage.getX(), currentImage.getY(), null);
        }

        // Draw the example drawable.
        if (mExampleDrawable != null) {
            mExampleDrawable.draw(canvas);
        }
        drawFinallyRectangle(canvas);
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

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                mX = (int)event.getX();
                mY = (int)event.getY();
                imageSelectionnee = recupererImageALaPosition(mX, mY);
                if (imageSelectionnee == null)
                    return false;
                if (!imageSelectionnee.isFixed ()) {
                    decalageX = mX - imageSelectionnee.getX();
                    decalageY = mY - imageSelectionnee.getY();

                    /* On place l'élément en tête de liste pour qu'ils soient déssinés dans l'ordre
                        par la suite
                     */
                    imageList.remove(imageSelectionnee);
                    imageList.add(0, imageSelectionnee);
                    imageSelectionnee.setFixed(isGoodPlace(imageSelectionnee));
                }
                return true;
            case (MotionEvent.ACTION_MOVE) :
                if (imageSelectionnee != null && !imageSelectionnee.isFixed()) {
                    mX = (int)event.getX();
                    mY = (int)event.getY();
                    imageSelectionnee.setX(mX - decalageX);
                    imageSelectionnee.setY(mY - decalageY);
                    invalidate();
                   // imageSelectionnee.setFixed(isGoodPlace(imageSelectionnee));
                }
                return true;
            case (MotionEvent.ACTION_UP) :
                if (imageSelectionnee != null && !imageSelectionnee.isFixed()) {
                    mX = (int)event.getX();
                    mY = (int)event.getY();
                    imageSelectionnee.setX(mX - decalageX);
                    imageSelectionnee.setY(mY - decalageY);
                    invalidate();
                    imageSelectionnee.setFixed(isGoodPlace(imageSelectionnee));
                    imageSelectionnee = null;
                }
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public boolean isGoodPlace(ImagePuzzle image){
        if (image.getX() > image.getXFinal() - 10 && image.getX() < image.getXFinal() + 10)
            if (image.getY() > image.getYFinal() - 10 && image.getY() < image.getYFinal() + 10){
                image.setX(image.getXFinal());
                image.setY(image.getYFinal());
                return true;
            }
        return  false;
    }
    public void drawFinallyRectangle(Canvas canvas){
        boolean finish = true;
        for(ImagePuzzle image : imageList){
            if (!image.isFixed())
                finish = false;
        }
        if (finish) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setTextSize(80);
            canvas.drawText("Bravoooo !!!", 350, 350, paint);
            if (mExampleDrawable != null)
                mExampleDrawable.draw(canvas);
        }
    }

    public ImagePuzzle recupererImageALaPosition(int x, int y) {
        for (ImagePuzzle image : imageList) {
            if (x > image.getX() && x < image.getX() + image.getBitmap().getWidth()
                    && y > image.getY() && y < image.getY() + image.getBitmap().getHeight()) {
                return image;
            }
        }
        return null;
    }
}






















