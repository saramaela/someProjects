package Model;

import java.util.*;

/**
 * Clase qui permet de gérer une case du plateau de jeu
 */
public class Element {

    public int x;
    public int y;
    public Integer valeur;
    //public final int xFinal;
    //public final int yFinal;
    public final Integer valeurInit;

    /**
     * Construit une case du plateau de jeu
     * @param x
     * @param y
     * @param valeur
     */
    public Element(int x, int y, Integer valeur) {

        this.x = x;
        this.y = y;
        this.valeur = valeur;
        this.valeurInit = valeur;
        //this.xFinal = x;
        //this.yFinal = y;
    }

    /**
     * Donne la valeur de la case
     * @return
     */
    public Integer getValeur() {
        return this.valeur;
    }

    /**
     * Donne la position X de la case
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Donne la position Y de la case
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Met a newX la position X de la case
     * @param newX
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Met a newY la position Y de la case
     * @param newY
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Met a newValeur la valeur de la case
     * @param newValeur
     */
     public void setValeur(int newValeur) {
        this.valeur = newValeur;
    }

    /**
     * Permet de savoir si la case est vide
     * @return
     */
    public boolean isEmpty() {
        return this.valeur == null;
    }

    /**
     * Affiche la valeur de la case
     */
    @Override
    public String toString() {
        return "" + this.valeur;
    }
}