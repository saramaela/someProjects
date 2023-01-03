/**
 *
 * @author 22012535
 */
package carte.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Classe de creation d'un paquet de cartes et sa gestion
 *
 */
public class Paquet {
    
    public ArrayList<Carte> paquet;

	public Paquet(ArrayList<Carte> paquet) {
		
		this.paquet = paquet;
	}
    
	public Paquet() {
		
		this.paquet = new ArrayList<Carte>();
	}
    
	/**
	 * Methode qui ajoute une carte au-dessus du paquet
	 * @param c1 carte ajoutée
	 */
	public void ajoutCarteAbove(Carte c1) {
		this.paquet.add(c1);
	}
    
	/**
	 * Methode qui ajoute une carte en-dessous du paquet
	 * @param c carte ajoutée
	 */
	 
	public void ajoutCarteBelow(Carte c) {
		this.paquet.add(0,c);
	}
	
	/**
	 * Methode qui retourne une carte aléatoire
	 * @return carte alétoire
	 */
	public Carte getRandomCard( ) {
		
		int index = (int)(Math.random() * this.paquet.size());
		return this.getCardAt(index);
	}

	/**
	 * Methode qui renvoie la carte d'un index precis
	 * @param i index
	 * @return carte 
	 */
	public Carte getCardAt(int i) {
		return this.paquet.get(i);
	}
    
	/**
	 * Methode qui mélange le paquet
	 */
	public void shuffleGame() {
		Collections.shuffle(this.paquet);
	}
	
	/**
	 * Methode qui coupe le paquet
	 * @return paquet paquet coupé
	 */
	public Paquet couperPaquet() {
		
		int index =  (int) ((Math.random()*((this.paquet.size()-3) - 3)) + 3);
		Paquet newPaquet = new Paquet(new ArrayList<Carte>(this.paquet.subList(index, this.paquet.size())));
		this.paquet = new ArrayList<Carte>(this.paquet.subList(0, index));
		
		return newPaquet;	
	}
	
	/**
	 * Methode qui retourne le paquet de cartes
	 * @return paquet pequet de cartes
	 */
	public ArrayList<Carte> getPaquet() {
		return this.paquet;
	}
	
	/**
	 * Methode qui pop la premiere carte et la renvoie
	 * @return carte carte enlevée
	 */
	public Carte removeFirstCard() {
		return this.removeCarte(0);
	}
	
	/**
	 * Methode qui enleve une carte à un index du paquet
	 * @param i index
	 * @return carte carte enlevée
	 */
	public Carte removeCarte(int i) {
		return this.paquet.remove(i);
	}
	
	/**
	 * Methode qui retourne la taille du paquet
	 * @return taille du paquet
	 */
	public int getPaquetSize() {
		return this.paquet.size();
	}
	
	/**
	 * Methode qui distribue les cartes du paquet en brulant la premiere
	 * @param p paquet
	 */
	public void distribuer(Paquet p) {
		p.ajoutCarteAbove(this.removeFirstCard());
	}

	/**
	 *Methode qui renvoie la version toString du paquet
	 */
	@Override
	public String toString() {
		String res = "";
		for(int i=0; i<this.paquet.size(); i++) {
			res+= this.paquet.get(i);
			res+= ", ";
		}
		return res;
	}
	

	
    
}
