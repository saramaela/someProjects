package blackjack;

import blackjack.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestModel {
	
	public static void main(String[] args) {
		/**** INISTIALISATION DU SCANNER ****/
		Scanner sc = new Scanner(System.in);
		
		/**** CREATION DES PLAYERS ET AJOUT DANS LA LISTE ****/
		Player p1 = new Player(500);
		Player p2 = new Player(500, true);
	
		ArrayList<Player> joueurs = new ArrayList<>();
		
		joueurs.add(p1);
		joueurs.add(p2);
		
		
		
		/**** INITIALISATION DU JEU ****/
		Game g = new Game(joueurs);
		

		/**** OUVETURE DES MAINS ****/
		p1.openHand(100, g);
		p2.openHand(100, g);
		
		/**** DEBUT DU ROUND ****/

		g.startRound();
		System.out.println("Croupier : " + g.getVisibleCard());

		
		/**** BOUCLE DE JEU ****/
		int i = 0;
		int j = g.getAllPlayersHand().size();
		while (!g.allHandFinish() && i < j) {
			PlayerHand hand = g.getAllPlayersHand().get(i);
			if (!hand.getPlayer().isBot()) {
				System.out.println("=========================");
				System.out.println("Main " + i + " : " + hand.getHand().toString());
				
				while (hand.isEnd() == false) {
					System.out.println("Action : ");
					String action = sc.nextLine();
					if (action.charAt(0) == 'S') {
						hand.split();
					}
					if (action.charAt(0) == 'H') {
						hand.hit();
					}
					if (action.charAt(0) == 'Q') {
						hand.stay();
					}
					if (action.charAt(0) == 'D') {
						hand.makeDouble();
					}
					System.out.println("Best move : " + BlackJackStrategy.getBestMove(hand, g.getHand().getHand().getCardAt(1)));
					System.out.println("Main " + i + " : " + hand.getHand().toString());
				}
			} else {
				System.out.println("=========================");
				System.out.println("Main " + i + " : " + hand.getHand().toString());
				System.out.println("Best move : " + BlackJackStrategy.getBestMove(hand, g.getHand().getHand().getCardAt(1)));
				
				while (hand.isEnd() == false) {
				hand.getPlayer().playBestMove(hand, g.getHand().getHand().getCardAt(1));

				System.out.println("Main " + i + " : " + hand.getHand().toString());
				System.out.println("Best move : " + BlackJackStrategy.getBestMove(hand, g.getVisibleCard()));
				}
			}
			
			i ++;
			j = g.getAllPlayersHand().size();			
		}
		
		/**** FIN DU ROUND ****/
		g.endRound();		
	}
	
}
