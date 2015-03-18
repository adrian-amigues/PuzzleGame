package com.ups.adrianetanais.puzzlegame;

/**
 * Created by Anaïs on 18/03/2015.
 */
public class Coordonnees {

    protected float xHautGauche; //Position courante en haut a gauche
    protected float yHautGauche;
    protected float xBasDroit; //Position courante en bas à droite
    protected float yBasDroit;
    protected float xFinal; //Position finale de l'angle en haut gauche
    protected float yFinal;


    public Coordonnees(float xHautGauche,float yHautGauche, float xBasDroit, float yBasDroit, float xFinal,float yFinal){
        this.xHautGauche = xHautGauche;
        this.yHautGauche = yHautGauche;
        this.xBasDroit = xBasDroit;
        this.yBasDroit = yBasDroit;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
    }

    public boolean isInImage(float x, float y){
        if (x >= xHautGauche && x <= xBasDroit)
            if (y >= yHautGauche && y <= yBasDroit)
                return true;
        return false;
    }

    public float getyHautGauche() {
        return yHautGauche;
    }

    public void setyHautGauche(float yHautGauche) {
        this.yHautGauche = yHautGauche;
    }


    public float getxBasDroit() {
        return xBasDroit;
    }

    public void setxBasDroit(float xBasDroit) {
        this.xBasDroit = xBasDroit;
    }

    public float getyBasDroit() {
        return yBasDroit;
    }

    public void setyBasDroit(float yBasDroit) {
        this.yBasDroit = yBasDroit;
    }

    public float getxHautGauche() {
        return xHautGauche;
    }

    public void setxHautGauche(float xHautGauche) {
        this.xHautGauche = xHautGauche;
    }

    public float getyFinal() {
        return yFinal;
    }
    public float getxFinal() {
        return xFinal;
    }
}

