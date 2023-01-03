package blackjack.model;

import java.util.ArrayList;
import carte.model.*;

import carte.model.Paquet;

/**
 * Classe qui gère le déroulement du jeu
 *
 */
public class Game {
	private ArrayList<Player> players;
	private DealerHand hand;
	private Deck deck;
	
	public Game(ArrayList<Player> players) {
		this.players = players;
		this.deck = new Deck();
	}
	
	/**
	 * Methode qui lance le début d'un tour
	 */
	public void startRound() {
		this.hand = new DealerHand();
		this.initialDistribute();
	}
	
	/**
	 * Methode qui determine si les mains ont encore des cartes
	 * @return bool 
	 */
	public boolean allHandFinish() {
		for (int i = 0; i < this.getAllPlayersHand().size(); i++) {
			if (this.getAllPlayersHand().get(i).isEnd() == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Methode qui permet de terminer un round
	 */
	public void endRound() {
		/***** LE CROUPIER TIRE JUSQU'A 17 *****/
		while (!this.hand.isEnd()) {
			this.distribute(this.hand);
		}
		/****** LE CROUPIER PAYE TOUS LES JOUEURS EN FONCTION DES MAINS ******/
		for (int i = 0; i < this.getAllPlayersHand().size(); i++) {
			this.payHand(this.getAllPlayersHand().get(i));
		}
		/**** ON SUPPRIME LES MAINS DE CHAQUE JOUEUR *****/
		for (Player p : this.players)
			p.clearHands();
	}
	
	/**
	 * Methode qui permet de payer un joueur
	 * @param pHand ensemble de mains du joueur
	 */
	public void payHand(PlayerHand pHand) {
		if (pHand.count() > 21) {
			pHand.pay(0);
		}
	
		if (this.hand.isBlackjack()) {
			if (pHand.isBlackjack()) {
				pHand.pay(1);
			} else {
				pHand.pay(0);
			}
		}
		if (this.hand.count() <= 21 && pHand.count() <= 21) {
			if (this.hand.count() == pHand.count()) {
				pHand.pay(1);
			} else if (this.hand.count() > pHand.count()) {
				pHand.pay(0);
			} else {
				if (pHand.isBlackjack())
					pHand.pay(2.5);
				else
					pHand.pay(2);
			}
		}
		if (this.hand.count() > 21 && pHand.count() <= 21) {
			if (pHand.isBlackjack())
				pHand.pay(2.5);
			else
				pHand.pay(2);
		}
	}
	
	/**
	 * Methode qui renvoie toutes les mains du joueur
	 * @return res les mains du joueur
	 */
	public ArrayList<PlayerHand> getAllPlayersHand(){
		ArrayList<PlayerHand> res = new ArrayList<>();
		for (Player p : this.players)
			for (PlayerHand hand : p.getHands())
				res.add(hand);
		return res;
	}
	
	/**
	 * Methode qui fait la première distributiond dans le jeu
	 */
	public void initialDistribute() {
		this.deck.burnFirstCard();
		for (int i = 0; i < 2; i++) {
			for (PlayerHand hand : this.getAllPlayersHand())
				this.distribute(hand);
			this.distribute(this.hand);
		}
	}
	/**
	 * Methode qui distribue des cartes à une main
	 * @param hand une main de joueur
	 */
	public void distribute(Hand hand) {
		if (this.deck.isEmpty()) {
			this.deck = new Deck();
			this.deck.distribute(hand);
		}
		this.deck.distribute(hand);
	}
	
	/**
	 * Méthode qui retourne la pioche
	 * @return deck, la pioche du jeu
	 */
	public Paquet getDeck() {
		return this.deck.getDeck() ; 
	}
	
	
	/**
	 * Méthode qui retourne la main du croupier
	 * @return hand la main du croupier
	 */
	public DealerHand getHand() {
		return this.hand;
	}

	

	/**
	 * Méthode qui retourne la liste des mains du joueur
	 * @return players, liste des mains du joueur
	 */
	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}


	public Carte getVisibleCard() {
		return this.hand.getHand().getCardAt(1);
	}

}
