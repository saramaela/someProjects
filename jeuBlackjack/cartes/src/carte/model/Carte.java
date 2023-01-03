/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 22012535
 */
package carte.model;

import carte.model.Factory.Couleur;
import carte.model.Factory.Valeur;

/**
 * Classe de representation d'une carte
 *
 */
public class Carte {
    
    protected Valeur valeur;
    protected Couleur couleur;

    public Carte(Valeur valeur, Couleur couleur) {
        this.valeur = valeur;
        this.couleur = couleur;
    }

    /**
     * Methode qui retourne la valeur d'une carte
     * @return valeur valeur de la carte
     */
    public Valeur getValeur() {
        return this.valeur;
    }

    /**
     * Methode qui retourne la couleur d'une carte
     * @return couleur couleur de la carte
     */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Methode qui parametre la valeur d'une carte
     * @param valeur valeur de la carte
     */
    public void setValeur(Valeur valeur) {
        this.valeur = valeur;
    }

    /**
     * Methode qui parametre la couleur d'une carte
     * @param couleur couleur de la carte
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     *Methode qui renvoie la version String d'une carte
     */
    @Override
    public String toString() {
        return "carte("  + this.valeur + ", " + this.couleur + ')';
    }  
    
}
