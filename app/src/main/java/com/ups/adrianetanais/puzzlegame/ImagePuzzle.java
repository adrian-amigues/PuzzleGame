package com.ups.adrianetanais.puzzlegame;

import android.graphics.Bitmap;


public class ImagePuzzle {
    protected int x;
    protected int y;
    protected Bitmap bitmap;
    protected boolean fixed;
    protected int xFinal;
    protected int yFinal;

    ImagePuzzle(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.x = 0;
        this.y = 0;
    }
    ImagePuzzle(Bitmap bitmap, int x, int y, boolean isFixed, int xFinal, int yFinal) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.fixed = isFixed;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
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

    public boolean isFixed(){
        return this.fixed;
    }
    public void setFixed (boolean bool){
        this.fixed = bool;
    }
    public int getYFinal() {
        return yFinal;
    }

    public int getXFinal() {
        return xFinal;
    }
}
