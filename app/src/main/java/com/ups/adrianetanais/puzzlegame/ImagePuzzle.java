package com.ups.adrianetanais.puzzlegame;

import android.graphics.Bitmap;


public class ImagePuzzle {
    public static final int ACCEPTANCE_MARGIN = 18;

    protected int x;
    protected int y;
    protected Bitmap bitmap;
    protected boolean fixed;
    protected int finalX;
    protected int finalY;

    ImagePuzzle(Bitmap bitmap, int x, int y, int finalX, int finalY) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.finalX = finalX;
        this.finalY = finalY;
        this.fixed = false;
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

    public boolean isFixed() {
        return this.fixed;
    }

    public void setFixed(boolean bool) {
        this.fixed = bool;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public boolean isAtTheRightPlace(int x, int y) {
        return (x > finalX - ACCEPTANCE_MARGIN && x < finalX + ACCEPTANCE_MARGIN
                && y > finalY - ACCEPTANCE_MARGIN && y < finalY + ACCEPTANCE_MARGIN);
    }

    public void setPositionToFinal() {
        x = finalX;
        y = finalY;
    }
}
























