package blackjack.model;

import carte.model.Paquet;
import carte.model.Carte;

/**
 * Classe qui gère les mouvement effectués dans les mains du joueur
 *
 */
public class PlayerHand extends BlackJackHand{
	private int bet;
	private Player player;
	private Game game;
	private boolean isEnd = false;
	private boolean isDouble = false;
	
	public PlayerHand(int bet, Player player, Game game) {
		super();
		this.player = player;
		this.game = game;
		this.bet = bet;
	}
	
	/**** BLACKJACK ACTIONS ****/
	/**
	 * Methode qui permet de diviser une main
	 */
	public void split() {
		PlayerHand newHand = new PlayerHand(this.bet, this.player, this.game);
		this.player.addToBalance(-this.bet);
		newHand.add(this.getHand().removeCarte(0));
		newHand.hit();
		this.hit();
		this.player.addHand(newHand);
	}
	/**
	 * Methode qui permet d'ajouter une carte à une main
	 */
	public void hit() {
		this.game.distribute(this);
	}
	/**
	 * Methode qui permet de doubler son pari dans le blackJack
	 */
	public void makeDouble() {
		this.player.addToBalance(-this.bet);
		this.bet *= 2;
		this.isDouble = true;
		this.hit();
		this.stay();
	}
	/**
	 * Methode qui permet de coucher ses cartes
	 */
	public void stay() {
		this.isEnd = true;
	}
		
	/**** PAY PLAYER METHOD ******/
	/**
	 * Methode qui permet de payer un joueur selon sa mise 
	 * @param f
	 */
	public void pay(double f) {
		this.player.addToBalance(this.bet * f);
	}
	
	/****** CAN METHOD *******/
	/**
	 * Methode qui permet de savoir si le joueur peut diviser sa main
	 * @return bool, true si oui et false sinon
	 */
	public boolean canSplit() {
		if (this.canDouble() && this.getHand().getPaquetSize() == 2 && this.getHand().getCardAt(0).getValeur().valeur == this.getHand().getCardAt(1).getValeur().valeur) {
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui permet de savoir si le joueur peut doubler sa mise
	 * @return bool, true si oui et false sinon
	 * 
	 */
	public boolean canDouble() {
		if (this.player.getBalance() >= this.bet && this.getHand().getPaquetSize() == 2) {
			return true;
		}
		return false;
	}
	
	/***** GETTER *****/
	/**
	 *Methode qui permet de savoir si la main est blackjack ou depasse 
	 * @return bool, true si oui et false sinon
	 */
	public boolean isEnd() {
		if(this.count() >= 21 || this.isEnd) {
			return true;
		}
		return false;
	}
	/**
	 * Methode qui permet de savoir si la joueur peut doubler sa main
	 * @return bool, true si oui et false sinon
	 */
	public boolean isDouble() {
		return this.isDouble;
	}
	/**
	 * Methode qui retourne la mise du joueur
	 * @return bet , somme pariée
	 */
	public int getBet() {
		return this.bet;
	}
	/**
	 * Methode qui retourne le joueur
	 * @return player le joueur
	 */
	public Player getPlayer() {
		return this.player;
	}
}
