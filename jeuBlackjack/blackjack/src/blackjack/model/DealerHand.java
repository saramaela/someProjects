package blackjack.model;

/**
 * Classe qui gère la main du croupier
 *
 */
public class DealerHand extends BlackJackHand{
	
	public DealerHand() {
		super();
	}
	
	/**
	 *Methode qui permet de savoir si le croupier a blackjack  
	 *
	 *@return bool true s'il y a blackjack et false sinon
	 */
	public boolean isEnd() {
		if (this.count() < 17) {
			return false;
		}
		return true;
	}
}
