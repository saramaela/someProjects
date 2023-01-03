/*


 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 22012535
 * 
 */
package blackjack;
import java.util.ArrayList;

import blackjack.controller.Controller;
import blackjack.model.Game;
import blackjack.model.Player;
import blackjack.vue.View;


public class MainClass {
    
	
        public static void main(String[] args) {

        	Player player1 = new Player(500);
        	Player player2 = new Player(5000000);

        	
    		ArrayList<Player> joueurs = new ArrayList<>();
    		joueurs.add(player1);
    		joueurs.add(player2);


        	

            Controller control = new Controller(joueurs);
            control.displayView();            
            



       // System.out.println(c1.toString());
    }
    
}
