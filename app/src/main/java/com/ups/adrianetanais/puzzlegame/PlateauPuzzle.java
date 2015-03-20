package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlateauPuzzle extends View {
    public static final int BORDER_SIZE = 2;

    private List<ImagePuzzle> imageList = new LinkedList<>();
    private ImagePuzzle imageSelectionnee = null;

    private int mX;
    private int mY;
    private int decalageX;
    private int decalageY;
    private int piecesToPlace;

    public PlateauPuzzle(Context context) {
        super(context);
        ImagePuzzle image1 = new ImagePuzzle(
                BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1), 0, 0, 0, 0);
        image1.setFixed(true);
        ImagePuzzle image2 = new ImagePuzzle(
                BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2)
                , image1.getBitmap().getWidth() - 100, 0
                , image1.getX() + image1.getBitmap().getWidth(), 0);

        imageList.add(image1);
        imageList.add(image2);
        // Hardcoded, à modifier
        piecesToPlace = 1;
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
            drawImagePuzzle(canvas, iter.next());
        }
        if (puzzleIsFinished())
            drawFinalRectangle(canvas);
    }

    public void drawImagePuzzle(Canvas canvas, ImagePuzzle image) {
        Bitmap bitmap = image.getBitmap();
        Bitmap bitmapWithBorder = Bitmap.createBitmap(
                bitmap.getWidth() + BORDER_SIZE * 2, bitmap.getHeight() + BORDER_SIZE * 2
                , bitmap.getConfig());
        Canvas backgroundCanvas = new Canvas(bitmapWithBorder);
        backgroundCanvas.drawColor(Color.GRAY);
        backgroundCanvas.drawBitmap(image.getBitmap(), BORDER_SIZE, BORDER_SIZE, null);

        canvas.drawBitmap(bitmapWithBorder, image.getX(), image.getY(), null);
    }

    public void drawFinalRectangle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(80);
        canvas.drawText("Bravoooo !!!", 350, 350, paint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                mX = (int) event.getX();
                mY = (int) event.getY();
                imageSelectionnee = getImageAt(mX, mY);
                if (imageSelectionnee != null) {
                    decalageX = mX - imageSelectionnee.getX();
                    decalageY = mY - imageSelectionnee.getY();

                    /* On place l'image en tête de liste */
                    imageList.remove(imageSelectionnee);
                    imageList.add(0, imageSelectionnee);
                    return true;
                } else {
                    return false;
                }

            case (MotionEvent.ACTION_MOVE):
                if (imageSelectionnee != null) {
                    mX = (int) event.getX();
                    mY = (int) event.getY();
                    imageSelectionnee.setX(mX - decalageX);
                    imageSelectionnee.setY(mY - decalageY);
                    invalidate();
                }
                return true;

            case (MotionEvent.ACTION_UP):
                if (imageSelectionnee != null) {
                    mX = (int) event.getX();
                    mY = (int) event.getY();
                    imageSelectionnee.setX(mX - decalageX);
                    imageSelectionnee.setY(mY - decalageY);
                    if (imageSelectionnee.isAtTheRightPlace(imageSelectionnee.getX(), imageSelectionnee.getY())) {
                        imageSelectionnee.setPositionToFinal();
                        imageSelectionnee.setFixed(true);
                        piecesToPlace--;
                    }
                    imageSelectionnee = null;
                    invalidate();
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    public ImagePuzzle getImageAt(int x, int y) {
        for (ImagePuzzle image : imageList) {
            if (x > image.getX() && x < image.getX() + image.getBitmap().getWidth()
                    && y > image.getY() && y < image.getY() + image.getBitmap().getHeight()) {
                if (!image.isFixed())
                    return image;
                else
                    return null;
            }
        }
        return null;
    }

    public boolean puzzleIsFinished() {
        return piecesToPlace == 0;
    }
}






















