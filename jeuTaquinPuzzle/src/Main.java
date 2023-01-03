package src;
import Model.*;
import Control.*;
import View.*;

import java.io.IOException;
import java.util.*;
/**
 * Le main abrite la version console du jeu et permet de lancer le jeu en version console (version 1) ou en version graphique (version 2)
 */ 
 public class Main {
    public static void main(String[] args) throws IOException{

      Scanner monObj = new Scanner(System.in);

      String monChoix = "0";

      while (!(monChoix.equals("1") && monChoix.equals("2") )) {

          // Create a Scanner object

          System.out.println("Veuillez choisir la version en ligne de commande (1)");
          System.out.println("ou la version graphique (2)");

          monChoix = monObj.nextLine();  // Read user input

          if (monChoix.equals("1")) {

              System.out.println(" Veuillez choisir votre format de jeu. Combien de lignes souhaitez vous ?");
              String nbLignes = monObj.nextLine();

              System.out.println("Combien de colonnes souhaitez vous ?");
              String nbColonnes = monObj.nextLine();

              Board board = new Board(Integer.parseInt(nbLignes),Integer.parseInt(nbColonnes));
              board.removeOneElement(Integer.parseInt(nbLignes)-1,Integer.parseInt(nbColonnes)-1);

              String niveau = "0";
              while (!(niveau.equals("1") && niveau.equals("2")&& niveau.equals("3"))) {

                System.out.println("choisiez le niveau vous souhaitez: 1(niveau facile), 2(niveau moyenne), 3(niveau difficile)");
                niveau = monObj.nextLine();

                if(Integer.parseInt(niveau) ==1){

                  board.shuffle(14);
                  break;

                }
                else if(Integer.parseInt(niveau) ==2){

                  board.shuffle(17);
                  break;

                }
                else if(Integer.parseInt(niveau) ==3){

                  board.shuffle(20);
                  break;

                }
                else {
                  System.out.println("Désolé, ce niveau n'existe pas! Veuillez choisir à nouveau: 1(niveau simple), 2(niveau moyenne), 3(niveau difficile)");
                   niveau = monObj.nextLine();
                }
              }

              System.out.println("Voici votre tableau\n"+board);

              while(!(board.hasWon())) {

                System.out.println("Choisiez le direction de deplacement: U(up), D(down), L(left), et R(right).");
                String direction = monObj.nextLine();

                switch(direction) {

                  case "U":
                    board.exchange(Board.Direction.Up);
                    break;

                  case "D":
                    board.exchange(Board.Direction.Down);
                    break;

                  case "L":
                    board.exchange(Board.Direction.Left);
                    break;

                  case "R":
                    board.exchange(Board.Direction.Right);
                    break;

                  default:
                    System.out.println("Cette direction n'existe pas.");
                    break;
                  }
                  System.out.println(board.toString());
                  if(board.hasWon()){
                    System.out.println("Felcitations, vous avez gagné :)");
                  }
              }
            }

          else if(monChoix.equals("2")) {
                  
            Board board = new Board(3,3);
            board.removeOneElement(2,2);

            board.ajoutEcouteur(new EcouteurModele(){
              @Override
              public void modeleMisAJour (Object source){
                System.out.println("Alerte changement!\n" + board.toString());
                      }
              });
              new TaquinGUI(board);
              //break;
            }
          else {
              System.out.println("Saisie incorrecte, veuillez réessayer!");
              monChoix = monObj.nextLine();
          }
        }
  }
}


