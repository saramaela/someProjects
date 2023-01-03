package blackjack.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import carte.model.Carte;
import carte.model.Paquet;

/**
 * Classe dédiée à l'affichage des cartes de la pioche
 *
 */
public class VuePaquetCache extends VuePaquet /*implements MouseListener*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Dimension screenSize;
	public Paquet paquet;
	public int startPaquetLarg;
	public int startPaquetLong;
	
	
	public VuePaquetCache(Paquet paquet) {
		this.paquet = paquet;
		this.startPaquetLarg = 0;
		this.startPaquetLong = 0;	
	}
	
	public VuePaquetCache() {
		this(new Paquet());
		this.startPaquetLarg = 0;
		this.startPaquetLong = 0;
	}
	

	
	/**
	 * Methode qui retourne les dimensions de l'écran
	 * @return screenSize dimension de l'ecran
	 */
	public Dimension getScreenSize() {
		return this.screenSize;
	}

	 /**
	 *Methode qui dessine la pioche 
	 */
	@Override
	  public void paint(Graphics graphics) {
		  super.paintComponent(graphics);
		  Graphics2D g2d = (Graphics2D) graphics;
		  Rectangle bounds = new Rectangle(this.startPaquetLarg+20, this.startPaquetLong+20, 103, 138);

		  for(int i=0; i<this.paquet.getPaquetSize(); i++) { 
			  if(i > 280) {
				  if(i%2 == 0) {
					  graphics.setColor(Color.white);
				  }
				  else {
					  graphics.setColor(Color.black);
				  }
				  g2d.draw(bounds);
				  bounds.translate(1, 1);	
			  }		
		  }
		  try {
			  File filename = new File("livraison/blackjack/src/PNG/Medium/ARRIERE_LARGE.png"		  	 );
			  g2d.drawImage(this.transformImage(filename.getAbsolutePath()), (int)bounds.getX(), (int)bounds.getY(),null);
		  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }

	@Override
	public Dimension setSize() {
		// TODO Auto-generated method stub
		return this.setSize();
	}


	  


	  
	
	  



}
