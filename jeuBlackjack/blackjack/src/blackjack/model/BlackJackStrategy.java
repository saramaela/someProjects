package blackjack.model;

import carte.model.*;

/**
 * Classe qui gère la stratégie de jeu des bots
 *
 */
public class BlackJackStrategy {
	public static enum move{
		STAY, SPLIT, DOUBLE, HIT;
	}
	
	/**** CHOOSE THE RIGHT STRATEGY TO APPLY ****/
	/**
	 * Choisit la meilleure strategie à appliquer
	 * @param hand
	 * @param dealerCard
	 * @return move le mouvement à faire
	 */
	public static move getBestMove(PlayerHand hand, Carte dealerCard) {
		if (hand.canSplit()) {
			return BlackJackStrategy.getBestMoveWithPair(hand, dealerCard);
		} else if (hand.getHand().getPaquetSize() == 2 && hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.AS) {
			return BlackJackStrategy.getBestMoveWithAnAS(hand, dealerCard, 1);
		} else if (hand.getHand().getPaquetSize() == 2 && hand.getHand().getCardAt(1).getValeur() == Factory.Valeur.AS) {
			return BlackJackStrategy.getBestMoveWithAnAS(hand, dealerCard, 0);
		}
		return BlackJackStrategy.getBestMoveMismatch(hand, dealerCard);
	}
	
	/***** STRATEGY TO USE IF THE PLAYER HAND IS A PAIR *****/
	/**
	 * Methode qui choisit la strategie à appliquer si la main du joueur est une paire
	 * @param hand
	 * @param dealerCard
	 * @return move le mouvement à faire
	 */
	public static move getBestMoveWithPair(PlayerHand hand, Carte dealerCard) {
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.AS || hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.HUIT) {
			return move.SPLIT;
		}
		if (hand.getHand().getCardAt(0).getValeur().valeur == 10) {
			return move.STAY;
		}
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.NEUF) {
			if (dealerCard.getValeur().valeur == 7 || dealerCard.getValeur().valeur >= 10) {
				return move.STAY;
			} else {
				return move.SPLIT;
			}
		}
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.SEPT) {
			if (dealerCard.getValeur().valeur >= 8) {
				return move.HIT;
			} else {
				return move.SPLIT;
			}
		}
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.SIX) {
			if (dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.SPLIT;
			}
		}
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.CINQ) {
			if (dealerCard.getValeur().valeur >= 10) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.getHand().getCardAt(0).getValeur() == Factory.Valeur.QUATRE) {
			if (dealerCard.getValeur().valeur <= 4 || dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.SPLIT;
			}
		}
		if (hand.getHand().getCardAt(0).getValeur().valeur <= 3) {
			if (dealerCard.getValeur().valeur >= 8) {
				return move.HIT;
			}else {
				return move.SPLIT;
			}
		}
		return move.STAY;
	}
	
	/***** STRATEGY TO USE IF THE PLAYER HAND HAD AN AS *****/
	/**
	 * Methode qui définit la strategie à appliquer si le joueur a un as
	 * @param hand
	 * @param dealerCard
	 * @param notAsIndex
	 * @return move le mouvement à faire
	 */
	
	public static move getBestMoveWithAnAS(PlayerHand hand, Carte dealerCard, int notAsIndex) {
		if (hand.getHand().getCardAt(notAsIndex).getValeur().valeur >= 8) {
			return move.STAY;
		}
		if (hand.getHand().getCardAt(notAsIndex).getValeur() == Factory.Valeur.SEPT) {
			if (dealerCard.getValeur().valeur == 2 || dealerCard.getValeur().valeur == 7 || dealerCard.getValeur().valeur == 8) {
				return move.STAY;
			} else if (dealerCard.getValeur().valeur >= 9) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.getHand().getCardAt(notAsIndex).getValeur().valeur == 6) {
			if (dealerCard.getValeur().valeur == 2 || dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.getHand().getCardAt(notAsIndex).getValeur().valeur == 4 || hand.getHand().getCardAt(notAsIndex).getValeur().valeur == 5) {
			if (dealerCard.getValeur().valeur <= 3 || dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.getHand().getCardAt(notAsIndex).getValeur().valeur == 2 || hand.getHand().getCardAt(notAsIndex).getValeur().valeur == 3) {
			if (dealerCard.getValeur().valeur <= 4 || dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		return move.STAY;
	}
	
	/***** STRATEGY TO USE IF THE PLAYER HAND IS A MISMATCH HAND *****/
	/**
	 * Methode qui définit la strategie à appliquer si le joueur a une main décalée
	 * @param hand
	 * @param dealerCard
	 * @return move le mouvement à faire
	 */
	public static move getBestMoveMismatch(PlayerHand hand, Carte dealerCard) {
		if (hand.count() >= 17) {
			return move.STAY;
		}
		if (hand.count() >= 13 && hand.count() <= 16) {
			if (dealerCard.getValeur().valeur >= 7) {
				return move.HIT;
			} else {
				return move.STAY;
			}
		}
		if (hand.count() == 12) {
			if (dealerCard.getValeur().valeur >= 7 || dealerCard.getValeur().valeur <= 3) {
				return move.HIT;
			} else {
				return move.STAY;
			}
		}
		if (hand.count() == 11) {
			if (dealerCard.getValeur() == Factory.Valeur.AS) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.count() == 10) {
			if (dealerCard.getValeur().valeur >= 10) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		if (hand.count() == 9) {
			if (dealerCard.getValeur().valeur == 2 || dealerCard.getValeur().valeur >= 6) {
				return move.HIT;
			} else {
				return move.DOUBLE;
			}
		}
		return move.HIT;
	}
	
	
}
