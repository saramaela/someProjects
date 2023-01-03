package blackjack.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import blackjack.model.Game;
import blackjack.model.Player;
import blackjack.model.PlayerHand;
import blackjack.vue.View;
import carte.model.Paquet;

/**
 * Classe controller qui gère tous les evenements du jeu
 *
 */
public class Controller implements ActionListener, MouseListener, KeyListener {
	
	
	public View view ;
	public Game game;
	
	
	public Controller(ArrayList<Player>joueurs)
	{

		this.game = new Game(joueurs) ;
		this.view = new View(this.game);
		this.view.addMouseListener(this);
		this.view.getJButtonSplit().addActionListener(this);		
		this.view.getJButtonHit().addActionListener(this);		
		this.view.getJButtonField().addActionListener(this);	
		this.view.getJButtonDouble().addActionListener(this);		
		this.view.getJButtonStand().addActionListener(this);
		
		
		this.view.SetField("Quelle mise ?");
		view.viewDeck.paquet = game.getDeck();
		this.view.getJButtonSplit().setEnabled(false);
		this.view.getJButtonDouble().setEnabled(false);
		this.view.getJButtonHit().setEnabled(false);
		this.view.getJButtonStand().setEnabled(false);
		this.view.getJButtonDouble().setEnabled(false);

		this.view.SetJlabelBalance(String.valueOf(game.getPlayers().get(0).getBalance()));
		this.view.setFocusable(true);
		this.view.addKeyListener(this);
		JTextField bet = this.view.getJTextField();
		
		
		bet.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				bet.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub			
			}		
		});	
	}
	
	
	/**
	 * Methode qui permet l'affichage de la vue
	 */
	public void displayView() {
		this.view.setSize(1000,1000);
		this.view.setLocationRelativeTo(null);
		this.view.setVisible(true);
	}


	/**
	 *Methode qui capte et traite les differentes actions de la vue sur ses différentes composants
	 *@param e evenement de la vue
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.getJButtonField()) {			
			for (int p = 0; p<game.getPlayers().size(); p++){
				int bett = Integer.parseInt(view.getJTextField().getText()); 
					if(game.getPlayers().get(p).isBettable(bett)){
						game.getPlayers().get(p).openHand(bett, game);	
						view.SetField(String.valueOf(bett));
					}
			}
			game.startRound();		
			view.viewPlayer.doublePlay = true;
			view.viewPlayer.typeOfPlayer = "playerSimple";
			view.viewPlayer.paquet2 = new Paquet();
			view.viewPlayer2.indicationPlayer = 0;
			view.SetJlabelBalance(String.valueOf(game.getPlayers().get(0).getBalance()));

			view.viewDealer.hideCardDealer = false;
			view.viewDealer.typeOfPlayer = "Dealer";	
			Paquet dealerPaquet = game.getHand().getHand();
			Paquet paquetJoueur = game.getPlayers().get(0).getHands().get(0).getHand();
			Paquet paquetJoueur2 = game.getPlayers().get(1).getHands().get(0).getHand();
			
			view.viewDealer.paquet = dealerPaquet;
			view.viewPlayer.paquet = paquetJoueur ; 
			view.viewPlayer2.paquet = paquetJoueur2 ;

			
		}
		view.getJButtonHit().setEnabled(true);
		view.getJButtonStand().setEnabled(true);
		view.getJButtonField().setFocusable(false);
		view.getJButtonField().setEnabled(false);	
		view.repaint ();
		if(game.getPlayers().get(0).getHands().get(0).canSplit()){

			view.getJButtonSplit().setEnabled(true);
		}
		else 
		{
			view.getJButtonSplit().setEnabled(false);
		}
		if(game.getPlayers().get(0).getHands().get(0).canDouble()){
			view.getJButtonDouble().setEnabled(true);
		}
		else 
		{
			view.getJButtonDouble().setEnabled(false);
		}
		if(game.getPlayers().get(0).getHands().get(0).isBlackjack()){
			endGame();
		}
		if(e.getSource() == view.getJButtonSplit()) {
			split();
		}
		if(e.getSource() == view.getJButtonHit()){
			hit();
		}
		if(e.getSource() == view.getJButtonStand()){
			stand();
		}
		if(e.getSource() == view.getJButtonDouble()){	
			dbl();
				}
		
	}

	
	/**
	 * Methode qui permet lance l'action de coucher les cartes de la main du joueur
	 */
	public void stand(){
		if(view.viewPlayer.typeOfPlayer == "playerSimple"){			
			game.getPlayers().get(0).getHands().get(0).stay();
			view.SetJlabelBalance(String.valueOf(game.getPlayers().get(0).getBalance()));	
			endGame();
			}
		else if(view.viewPlayer.typeOfPlayer == "playerSplit"){
			view.getJButtonSplit().setEnabled(false);
			if(game.getPlayers().get(0).getHands().get(0).isEnd() == false){
				if(game.getPlayers().get(0).getHands().get(0).isBlackjack())
				{
					
				}
				game.getPlayers().get(0).getHands().get(0).stay();	
				view.viewPlayer.indicationPlayer = 2;
			}
			else {
				game.getPlayers().get(0).getHands().get(1).stay();
				endGame();
			}
		}
	}
	
	

	/**
	 * Methode qui permet lance l'action de diviser de la main du joueur
	 */
	public void split() {
		game.getPlayers().get(0).getHands().get(0).split();

		for (int p = 0; p<game.getPlayers().size(); p++){
		System.out.print(game.getPlayers().get(p).getHands());}
		view.viewPlayer.typeOfPlayer = "playerSplit";
		Paquet paquet1JoueurSplit= game.getPlayers().get(0).getHands().get(0).getHand();
		if(game.getPlayers().get(0).getHands().get(0).isBlackjack())
		{
			game.getPlayers().get(0).getHands().get(0).stay(); 
		}
		Paquet paquet2JoueurSplit = game.getPlayers().get(0).getHands().get(1).getHand();
		view.viewPlayer.paquet = paquet1JoueurSplit ; 
		view.viewPlayer.paquet2 = paquet2JoueurSplit ; 
		if(game.getPlayers().get(0).getHands().get(0).isBlackjack()){
			if(game.getPlayers().get(0).getHands().get(1).isBlackjack()){
				endGame();
			}
			else {
				view.viewPlayer.indicationPlayer = 2;
			}
		}
		else {
			view.viewPlayer.indicationPlayer = 1;
		}
		view.getJButtonSplit().setEnabled(false);
		view.repaint();
	}
	
	
	
	/**
	 * Methode qui permet lance l'action de jouer un coup 
	 */
	public void hit()
	{
		view.getJButtonDouble().setEnabled(false);
		System.out.print(game.getPlayers());
		if(view.viewPlayer.typeOfPlayer == "playerSimple"){
			game.getPlayers().get(0).getHands().get(0).hit();
			if(game.getPlayers().get(0).getHands().get(0).isEnd() == false){
				if(game.getPlayers().get(0).getHands().get(0).isBlackjack())
				{
					endGame();
				}
			}
			else if(game.getPlayers().get(0).getHands().get(0).isEnd() == true){
				//game.getPlayers().get(0).getHands().get(0).stay();
				endGame();
			}
		}
		else if(view.viewPlayer.typeOfPlayer == "playerSplit"){	

			this.view.getJButtonSplit().setEnabled(false);
			if(view.viewPlayer.indicationPlayer == 1)
			{
				view.getJButtonDouble().setEnabled(false);
				game.getPlayers().get(0).getHands().get(0).hit();
				if(game.getPlayers().get(0).getHands().get(0).isEnd() == true)
				{
					view.getJButtonSplit().setEnabled(false);
					view.getJButtonDouble().setEnabled(true);
					game.getPlayers().get(0).getHands().get(0).stay();
					view.viewPlayer.indicationPlayer = 2;
					
				}	
			}
			else if(view.viewPlayer.indicationPlayer == 2)
			{
				view.viewPlayer.doublePlay2 = true;
				game.getPlayers().get(0).getHands().get(1).hit();
				if(game.getPlayers().get(0).getHands().get(1).isBlackjack()){
					endGame();
				}
				if(game.getPlayers().get(0).getHands().get(1).isEnd() ==true){
					endGame();
					}
				}
		}
		view.repaint();
	}
				

	/**
	 * Methode qui permet lance l'action de faire un double
	 */
	public void dbl() {		
		if(view.viewPlayer.typeOfPlayer == "playerSimple"){
			view.viewPlayer.doublePlay = false;
			if(game.getPlayers().get(0).getHands().get(0).canDouble()){
				game.getPlayers().get(0).getHands().get(0).makeDouble();
				endGame();
			}

			view.repaint();
		}
		else if(view.viewPlayer.typeOfPlayer == "playerSplit"){
			view.getJButtonSplit().setEnabled(false);
			if (view.viewPlayer.indicationPlayer == 2)
			{
				view.viewPlayer.doublePlay2 = false;
				if(game.getPlayers().get(0).getHands().get(1).canDouble()){
					game.getPlayers().get(0).getHands().get(1).makeDouble();			
					}
				endGame();
			}
			else if(view.viewPlayer.indicationPlayer == 1)
			{
				view.viewPlayer.doublePlay = false;
				if(game.getPlayers().get(0).getHands().get(0).canDouble() && !game.getPlayers().get(0).getHands().get(0).isEnd()){
					game.getPlayers().get(0).getHands().get(0).makeDouble();

					}
				view.viewPlayer.indicationPlayer = 2;	
			}


		}

		view.repaint();		
	}
			
	
			


	
	
	/**
	 * Methode qui permet de modifier l'affichage si un round est terminé afin d'en préparer un autre 
	 */
	public void endGame(){
		//System.out.print(game.getPlayers().get(0).getHands());
		game.getPlayers().get(1).playBestMove(game.getPlayers().get(1).getHands().get(0), game.getHand().getHand().getCardAt(0));
		game.endRound();
		System.out.print(game.getPlayers());
		System.out.print(game.getPlayers().get(0).getHands());
		view.getJButtonHit().setEnabled(false);
		view.getJButtonStand().setEnabled(false);
		view.getJButtonDouble().setEnabled(false);
		view.getJButtonSplit().setEnabled(false);
		view.viewDealer.hideCardDealer = true;
		view.getJButtonField().setEnabled(true);
		view.repaint();	
		
		this.view.SetJlabelBalance(String.valueOf(game.getPlayers().get(0).getBalance()));	
	}

	

	/**
	 *Methode qui permet de capter les evenements claviers pour jouer
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyChar() == 'h'){
			hit();
		}
		if (k.getKeyChar() == 's'){
			split();
		}
		if (k.getKeyChar() == 't'){
			stand();
		}
		if (k.getKeyChar() == 'd'){
			dbl();
		}
	}
		
	
	
		@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

		
}



	
	







