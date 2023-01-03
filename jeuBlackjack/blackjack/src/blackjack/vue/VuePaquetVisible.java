package blackjack.vue;


import java.awt.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import blackjack.model.Hand;
import blackjack.model.Player;
import blackjack.model.PlayerHand;
import carte.model.*;



/**
 * Classe dédiée à l'affichage de toute main qu'elle appartienne au croupier ou à un joueur
 *
 */
public class VuePaquetVisible extends VuePaquet{
	
	private static final long serialVersionUID = 1L;
	public Dimension screenSize;
	public Paquet paquet;
	public Paquet paquet2;
	BufferedImage[] allImages;
	public boolean hideCardDealer ; 
	public String typeOfPlayer = "playerSimple" ; 
	public boolean doublePlay = true ; 
	public boolean doublePlay2 = true ; 
	public int indicationPlayer; 
	

	
	
	
	
	

	
	public VuePaquetVisible(Paquet paquet) {
		this.paquet = paquet;
		this.paquet2 = new Paquet();
	    this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.hideCardDealer = true;
		this.indicationPlayer = 1; 

		
		
	}
	
	public VuePaquetVisible(Paquet paquet, Paquet paquet2) {
		this.paquet = paquet;
		this.paquet2 = paquet2;		
	    this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.hideCardDealer = true;
		this.indicationPlayer = 1; 
		
		
	
	}
	
	public VuePaquetVisible() {
		this.paquet = new Paquet();
		this.paquet2 = new Paquet();
	    this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.hideCardDealer = true;
		this.indicationPlayer = 1; 
	}
	
	/**
	 *Methode qui represente les diffrerentes cartes
	 */
	@Override
	  public void paint(Graphics g) {
		
		  super.paintComponent(g);
		  Graphics2D g2d = (Graphics2D) g;
		  g2d.setFont(new Font("P", Font.PLAIN, 28));
		  g2d.setColor(new Color(255, 255, 255	));
		  Rectangle bounds = new Rectangle(0,this.getHeight()-100, 70, 110 );	  
		  Rectangle bounds2 = new Rectangle(this.getWidth()-100,this.getHeight()-100, 70, 110);
		  System.out.print(this.getHeight() );



			  switch(typeOfPlayer){
			  case "playerSimple":
				  for(int i=0; i<this.paquet.getPaquetSize(); i++) {
					  Carte c = this.paquet.getPaquet().get(i);
					  if (i==0){
						  try{
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);} 
						  catch (IOException e) {
							  e.printStackTrace();}
						  bounds.translate(0, -110);
						  if(indicationPlayer == 1)
						  {
							  g2d.drawString("P", 75,400);
						  }
						  
						  
					  }
					  else if (i==1){
						  try {
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
						  } 
						  catch (IOException e) {
							  e.printStackTrace();
						  } 
						  bounds.translate(10, -70);

					  }	  
					  else {
						  if(doublePlay == false )
						  {
							  try {
								  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
								  g2d.drawImage(this.transformImage(filename.getAbsolutePath()),(int)bounds.getX(), (int)bounds.getY(),110,70,this);
							  } 
							  catch (IOException e) {
								  e.printStackTrace();
							  } 
						  }
						  else {
							  try {
								  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
								  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
							  } 
							  catch (IOException e) {
								  e.printStackTrace();
							  } 
							  bounds.translate(30, -50);
						  }
					  }
				  }  	  
			  break;
			  case "Dealer":
				  for(int i=0; i<this.paquet.getPaquetSize(); i++) {
					  Carte c = this.paquet.getPaquet().get(i);
					  if (i==0){
						  try {
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
						  } 
						  catch (IOException e) {
							  e.printStackTrace();
						  }	
						  bounds.translate(100, 0);	  
					  }
					  else if (i==1){
						  if(!this.hideCardDealer){
							  try {
								  File filenamehide = new File("livraison/blackjack/src/PNG/Medium/ARRIERE.png"		  	 );
								  g2d.drawImage(this.transformImage(filenamehide.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
							  } 
							  catch (IOException e) {
								  e.printStackTrace();
							  }
							  bounds.translate(30, -50);	  
						  }
						  else{
							  try {
								  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
								  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
							  } 
							  catch (IOException e) {
								  e.printStackTrace();
							  }
							  bounds.translate(30,-50);	
						  }  					  
					  }			  
					  else{
						  try {
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null); 		
						  } 
						  catch (IOException e) {
							  e.printStackTrace();
						  }  
						  bounds.translate(40, -20);	   
					  }
				  }
				  break;
			  case "playerSplit":
				  for(int i=0; i<this.paquet.getPaquetSize(); i++) {
					  Carte c = this.paquet.getPaquet().get(i);

					  if (i==0){
						  try{
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null); }
						  catch (IOException e) {
							  e.printStackTrace();}
						  bounds.translate(0, -110);
						  if(indicationPlayer == 1)
						  {
							  g2d.drawString("P", 75,400);
						  }

					  }
				  else if (i==1){
					  try {
						  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
						  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);}		  
					  catch (IOException e) {
						  e.printStackTrace();
					  }
					  bounds.translate(30,-50);	
				  }  			  
				  else{
					  if(doublePlay == false )
					  {
						  try {
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()),(int)bounds.getX(), (int)bounds.getY(),110,70,this);
						  } 
						  catch (IOException e) {
							  e.printStackTrace();
						  } 
					  }
					  else {
						  try {
							  File filename = new File("livraison/blackjack/src/PNG/Medium/" +c.getValeur() + "_" + c.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
						  } 
						  catch (IOException e) {
							  e.printStackTrace();
						  } 
						  bounds.translate(30, -50);
					  }
				  }	  
				  }
			  }
			  for(int i=0; i<this.paquet2.getPaquetSize(); i++) {
				  Carte c2 = this.paquet2.getPaquet().get(i);
					  if (i==0){
						  try{
							  File filename2 = new File("livraison/blackjack/src/PNG/Medium/" +c2.getValeur() + "_" + c2.getCouleur() +".png"		  	 );
							  g2d.drawImage(this.transformImage(filename2.getAbsolutePath()), (int)bounds2.getX(), (int)bounds2.getY(),null);} 
						  catch (IOException e) {
							  e.printStackTrace();}	 
						  bounds2.translate(0, -110);
						  if(indicationPlayer == 2)
						  {
							  g2d.drawString("P", 300,400);
						  }
					  }

					  else if (i==1){
							  try {
								  File filename2 = new File("livraison/blackjack/src/PNG/Medium/" +c2.getValeur() + "_" + c2.getCouleur() +".png"		  	 );
								  g2d.drawImage(this.transformImage(filename2.getAbsolutePath()), (int)bounds2.getX(), (int)bounds2.getY(),null);
							  } 
							  catch (IOException e) {
								  e.printStackTrace();
							  }
							  bounds2.translate(-60, -50);
					  }		 
					  else{
							  if(doublePlay2 == false )
							  {
								  try {

									  File filename2 = new File("livraison/blackjack/src/PNG/Medium/" +c2.getValeur() + "_" + c2.getCouleur() +".png"		  	 );
									  g2d.drawImage(this.transformImage(filename2.getAbsolutePath()),(int)bounds2.getX(), (int)bounds2.getY(),110,70,this);

								  			} 
								  	catch (IOException e) {
								  		e.printStackTrace();
								  	} 
							  }
							  else {
								  try {
									  File filename2 = new File("livraison/blackjack/src/PNG/Medium/" +c2.getValeur() + "_" + c2.getCouleur() +".png"		  	 );
									  g2d.drawImage(this.transformImage(filename2.getAbsolutePath()), (int)bounds2.getX(), (int)bounds2.getY(),null);
								  			} 
								  	catch (IOException e) {
								  		e.printStackTrace();
								  	} 
								  bounds2.translate(-30, -50);
							  }}	  
					  }
				  } 

		  
	
	private void File(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension setSize() {
		// TODO Auto-generated method stub
		return this.screenSize;
	}
	


}
