package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

    public PlateauPuzzle(Context context) {
        super(context);
        ImagePuzzle image1 = new ImagePuzzle(BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1), 0, 0);
        ImagePuzzle image2 = new ImagePuzzle(BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2), image1.getBitmap().getWidth() - 100, 0);

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
        for (ImagePuzzle image : imageList) {
            canvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(), null);
        }
        for (Iterator<ImagePuzzle> iter = ((LinkedList<ImagePuzzle>) imageList).descendingIterator(); iter.hasNext(); ) {
            ImagePuzzle currentImage = iter.next();
            canvas.drawBitmap(currentImage.getBitmap(), currentImage.getX(), currentImage.getY(), null);
        }


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

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                imageSelectionnee = recupererImageALaPosition((int)event.getX(), (int)event.getY());
                /* On place l'élément en tête de liste pour qu'ils soient déssinés dans l'ordre
                    par la suite
                 */
                imageList.remove(imageSelectionnee);
                imageList.add(0, imageSelectionnee);
                return true;
            case (MotionEvent.ACTION_MOVE) :
                if (imageSelectionnee != null) {
                    mX = (int)event.getX();
                    mY = (int)event.getY();
                    imageSelectionnee.setX(mX - imageSelectionnee.getBitmap().getWidth() / 2);
                    imageSelectionnee.setY(mY - imageSelectionnee.getBitmap().getHeight() / 2);
                    invalidate();
                }
                return true;
            case (MotionEvent.ACTION_UP) :
                if (imageSelectionnee != null) {
                    mX = (int)event.getX();
                    mY = (int)event.getY();
                    imageSelectionnee.setX(mX - imageSelectionnee.getBitmap().getWidth() / 2);
                    imageSelectionnee.setY(mY - imageSelectionnee.getBitmap().getHeight() / 2);
                    invalidate();
                    imageSelectionnee = null;
                }
                return true;
            default :
                return super.onTouchEvent(event);
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






















