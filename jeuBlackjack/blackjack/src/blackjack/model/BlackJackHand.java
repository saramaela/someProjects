package blackjack.model;

import carte.model.*;

/**
 * Classe de gestion de l'aspect blackJack des mains
 *
 */
/**
 * @author kafuata
 *
 */
/**
 * @author kafuata
 *
 */
abstract class BlackJackHand implements Hand{
	
	private Paquet paquet;
	
	/**
	 * Constructeur qui produit un paquet de cartes
	 * 
	 */
	public BlackJackHand() {
		this.paquet = new Paquet(); 
	}
	
	/**
	 *Methode qui retourne une main
	 */
	public final Paquet getHand() {
		return this.paquet;
	}
	/**
	 *Methode qui ajoute une carte dans un paquet
	 *@param c une Carte d'un paquet de cartes
	 */
	public final void add(Carte c) {
		this.paquet.ajoutCarteAbove(c);
	}
	/**
	 *Methode qui renvoie la tailld'un paquet de carte
	 */
	public final int getSize() {
		return this.paquet.getPaquetSize();
	}
	
	/**
	 *Methode qui count si le total des cartes fait blackjack ou pas
	 */
	public final int count() {
		int nbOfAs = 0;
		int count = 0;
		
		for (int i = 0; i < this.paquet.getPaquetSize(); i++) {
			count += this.paquet.getCardAt(i).getValeur().valeur;
			if (this.paquet.getCardAt(i).getValeur() == Factory.Valeur.AS)
				nbOfAs += 1;
		}
		while (count > 21) {
			if (nbOfAs == 0) {
				break;
			}
			count -= 10;
			nbOfAs -= 1;
		}
		
		return count;
	}
	
	/**
	 *Methode qui renvoie boolean si la main est blackJack ou pas
	 */
	public final boolean isBlackjack() {
		return (this.getSize() == 2 && this.count() == 21) ? true : false;
	}
	
	public abstract boolean isEnd();
}
