package com.ups.adrianetanais.puzzlegame;

import android.content.Context;
import android.content.Intent;
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
import java.util.Random;

public class PlateauPuzzle extends View {
    public static final int BORDER_SIZE = 2;

    private Bitmap puzzleBitmap;
    private List<ImagePuzzle> imageList = new LinkedList<>();
    private ImagePuzzle imageSelectionnee = null;

    private int mX;
    private int mY;
    private int decalageX;
    private int decalageY;
    private int piecesToPlace=8;
    private int difficulte;
    private int idImage;

    public PlateauPuzzle(Context context,int difficulte, int idImage) {
        super(context);
        this.difficulte = difficulte;
        this.idImage = idImage;
        switch (difficulte) {
            case 1 :
                decouperImage(BitmapFactory.decodeResource(getResources(), idImage));
                break;
            case 2 :
                decouperImage(BitmapFactory.decodeResource(getResources(), idImage));
                break;
            case 3 :
                decouperImage(BitmapFactory.decodeResource(getResources(), idImage));
                break;
        }
        imageList.get(0).setFixed(true);

        // Hardcoded, à modifier
        piecesToPlace = 8;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (puzzleBitmap != null) {
            resizeImageToFitScreen(w, h);

            decouperImage(puzzleBitmap);
            ImagePuzzle fixedPiece = imageList.get(0);
            fixedPiece.setX(0);
            fixedPiece.setY(0);
            fixedPiece.setFixed(true);
            putImageOnTheBack(fixedPiece);
        }
    }

    private void resizeImageToFitScreen(int viewWidth, int viewHeight) {
        viewWidth = viewWidth - BORDER_SIZE; /* BORDER_SIZE * 2 laissait une ligne blance */
        viewHeight = viewHeight - BORDER_SIZE;
        int imageWidth = puzzleBitmap.getWidth();
        int imageHeight = puzzleBitmap.getHeight();
        float widthRatio = (float)viewWidth / imageWidth;
        float heightRatio = (float)viewHeight / imageHeight;

        if (widthRatio <= heightRatio) {
            int newHeight = (int) (imageHeight * widthRatio);
            puzzleBitmap = Bitmap.createScaledBitmap(puzzleBitmap, viewWidth, newHeight, true);
        }
        else {
            int newWidth = (int) (imageWidth * heightRatio);
            puzzleBitmap = Bitmap.createScaledBitmap(puzzleBitmap, newWidth, viewHeight, true);
        }
    }

    private void decouperImage(Bitmap sourceImage) {
        int nbRow;
        int nbCol;
        if (difficulte == 1) {
            nbRow = 3;
            nbCol = 3;
        }
        else {
            nbRow = 4;
            nbCol = 4;
        }

        Random random = new Random();
        boolean putPiecesRandomly = false; /* pourrait être une option ?.. */
        int randomX;
        int randomY;
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        Bitmap imagePiece;
        for (int i=0; i < nbRow; i++) {
            for (int j=0; j < nbCol; j++) {
                imagePiece = Bitmap.createBitmap(sourceImage, imageWidth/nbCol*j, imageHeight/nbRow*i, imageWidth/nbCol, imageHeight/nbRow);
                if (putPiecesRandomly) {
                    randomX = random.nextInt(getWidth() - imageWidth / nbCol);
                    randomY = random.nextInt(getHeight() - imageHeight / nbRow);
                    imageList.add(new ImagePuzzle(imagePiece, randomX, randomY, imageWidth / nbCol * j, imageHeight / nbRow * i));
                } else {
                    imageList.add(new ImagePuzzle(imagePiece, i*200 + j*50, i*200 + j*50, imageWidth / nbCol * j, imageHeight / nbRow * i));
                }
            }
        }
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
        if (puzzleIsFinished()){
            System.out.println("Partie Finie");
            Intent i = new Intent(this.getContext(),IntentPartieGagne.class);
            i.putExtra("IMAGE",idImage);
            this.getContext().startActivity(i);
        }
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

                    putImageOnTheFront(imageSelectionnee);
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
                        putImageOnTheBack(imageSelectionnee);
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

    private void putImageOnTheFront(ImagePuzzle image) {
        imageList.remove(image);
        imageList.add(0, image);
    }

    private void putImageOnTheBack(ImagePuzzle image) {
        imageList.remove(image);
        imageList.add(image);
    }

    private ImagePuzzle getImageAt(int x, int y) {
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






















