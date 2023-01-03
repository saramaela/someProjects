package blackjack.model;

import carte.model.Paquet;
import carte.model.Carte;

/**
 * Interface de toute main du jeu que celle-ci soit celle du croupier ou du joueur
 *
 */
public interface Hand {
	
	public Paquet getHand();
	public void add(Carte c);
	public int getSize();
	public boolean isBlackjack();
	public int count();
	public boolean isEnd();
}
