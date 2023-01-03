package blackjack.model;

import carte.model.Paquet;
import carte.model.Factory;

/**
 *Classe qui permet la gestion du paquet de pioche du jeu
 *
 */
public class Deck {
	private Paquet deck;
	
	public Deck() {
		this.deck = Factory.createPaquetBlackjack();
		this.shuffle();
		this.burnFirstCard();
	}
	
	/**
	 * Methoque qui vérifie si la pioche est vide ou non
	 * @return  bool true si le paquet est vide et false sinon
	 */
	public boolean isEmpty() {
		return this.deck.getPaquetSize() < 1;
	}
	/**
	 * Methode qui mélange la pioche
	 */
	public void shuffle() {
		this.deck.shuffleGame();
	}
	/**
	 * Methode qui brule la premiere carte de la pioche
	 */
	public void burnFirstCard() {
		this.deck.removeFirstCard();
	}
	/**
	 * Méthode qui permet la distribution des cartes à une main
	 * @param hand
	 */
	public void distribute(Hand hand) {
		this.deck.distribuer(hand.getHand());
	}
	
	/**
	 * Methode qui retourne la pioche
	 * @return deck la pioche
	 */
	public Paquet getDeck() {
		return this.deck ; 
	}
	
	/**
	 * Methode qui retourne la taille de la pioche
	 * @return la taille de la pioche
	 */
	public int getSize() {
		return this.deck.getPaquetSize() ; 
	}
}
