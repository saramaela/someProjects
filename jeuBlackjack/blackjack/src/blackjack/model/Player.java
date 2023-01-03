package blackjack.model;

import java.util.ArrayList;

import carte.model.Carte;

/**
 * Classe qui gère les mains joueurs qu'elles appartiennent à un bot ou non
 *
 */
public class Player {
	private ArrayList<PlayerHand> hands;
	private double balance;
	private boolean isBot = false;
	
	public Player(int balance) {
		this.hands = new ArrayList<>();
		this.balance = balance;
	}
	public Player(int balance, boolean isBot) {
		this(balance);
		this.isBot = true;
	}
	
	/**
	 * Methode qui permet au joueur de joueur tout en pariant une somme voulue
	 * @param bet la somme qu'il veut parier
	 * @param game  le lancement d'une main  dans le jeu
	 */
	public void openHand(int bet, Game game) {
		if (this.isBettable(bet)) {
			this.hands.add(new PlayerHand(bet, this, game));
			this.balance -= bet;
		}
	}
	
	
	/**
	 * Methode qui permet de jouer le meilleur mouvement possible (methodde spéiale bot)
	 * @param hand 
	 * @param dealerCard
	 */
	public void playBestMove (PlayerHand hand, Carte dealerCard) {
		BlackJackStrategy.move bestMove = BlackJackStrategy.getBestMove(hand, dealerCard);
		switch (bestMove) {
			case STAY: 
				hand.stay();
				System.out.print("Stay");
				break;
			case HIT:
				hand.hit();
				System.out.print("hit");
				break;
			case DOUBLE:
				if (hand.canDouble()) {
					hand.makeDouble();
					System.out.print("double");
				} else {
					hand.hit();
					System.out.print("hit");
				}
				break;
			case SPLIT: 
				hand.split();
				System.out.print("Split");
				break;
		}
	}
	
	/**
	 * Ajouter une main à un joueur
	 * @param hand main à ajouter
	 */
	public void addHand(PlayerHand hand) {
		this.hands.add(hand);
	}
	/**
	 * Méthode pour vider les main du joueur
	 */
	public void clearHands() {
		this.hands.clear();
	}
	
	/**
	 * Methode qui determine si le joueur dispose de la somme qu'il veut parier 
	 * @param value somme qui veut être pariée
	 * @return bool true si pariable, false sinon
	 */
	public boolean isBettable(int value) {
		if (this.balance >= value && this.balance != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui retourne les mains du joueur
	 * @return hands, ensemble des mains du joueur
	 */
	public ArrayList<PlayerHand> getHands(){
		return this.hands;
	}
	/**
	 * Methode qui retourne la somme dont dispose le joueur
	 * @return balance argent du joueur
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Methode qui ajoute une certaine somme à la somme dont dispose deja le joueur
	 * @param f somme à ajouter
	 */
	public void addToBalance(double f) {
		this.balance += f;
	}
	/**
	 * methode qui determine si le jeour est un bot
	 * @return bool true s'il s'agit d'un bot et false sinon
	 */
	public boolean isBot() {
		return this.isBot;
	}
}
