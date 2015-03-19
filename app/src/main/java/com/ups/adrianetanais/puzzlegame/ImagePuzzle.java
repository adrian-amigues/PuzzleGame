package com.ups.adrianetanais.puzzlegame;

import android.graphics.Bitmap;


public class ImagePuzzle {
    protected int x;
    protected int y;
    protected Bitmap bitmap;

    ImagePuzzle(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.x = 0;
        this.y = 0;
    }
    ImagePuzzle(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
