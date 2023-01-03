package blackjack.vue;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import blackjack.model.Game;


/**
 * Classe qui gère la vue du jeu
 *
 */
public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	
	public VuePaquetCache viewDeck;
	public VuePaquetVisible viewPlayer;
	public VuePaquetVisible viewPlayer2;
	public VuePaquetVisible viewPlayer3;
	public VuePaquetVisible viewDealer;
	public JPanel panelJoueur;

	public BufferedImage myPicture;
	
	
	private JButton buttonSplit  = new JButton("Split");
	private JButton buttonHit = new JButton("Hit");
	private JButton buttonStand  = new JButton("Stand");
	private JButton buttonDouble  = new JButton("Double");

	private JTextField bet = new JTextField("Entrez le mise") ;


	private JButton boutonField = new JButton ("Appliquer votre mise") ; 
	private JLabel balance = new JLabel() ; 
	private JLabel P = new JLabel("P");
	
	
	public View(Game game) {
		

		this.setTitle("BLACKJACK");
		Container contentPane = this.getContentPane();
		Font font = new Font("Monospace", Font.PLAIN, 20);
		
		panelJoueur = new JPanel();

		viewPlayer= new VuePaquetVisible();	
		viewPlayer.setBackground(new Color(0,102,0));
		viewPlayer.setBounds(300,100,400,400);
		viewPlayer.add(P);
		P.setBackground(new Color(0,102,255));
		
		viewPlayer2 = new VuePaquetVisible();
		viewPlayer2.setBackground(new Color(0,102,0));
		viewPlayer2.setBounds(100,100,300,300);
		viewPlayer2.setPreferredSize(new Dimension(400,400));
		viewPlayer.setPreferredSize(new Dimension(400,400));
		



		
		panelJoueur.setLayout(new GridLayout());
		panelJoueur.setBackground(Color.blue);

		panelJoueur.add(viewPlayer);

		
		



		
		viewDealer = new VuePaquetVisible();
		viewDealer.setBackground(new Color(0,102,0));
		viewDealer.setPreferredSize(new Dimension(700,200));
	
		

		viewDeck = new VuePaquetCache();
		viewDeck.setPreferredSize(new Dimension(300,250));
		viewDeck.setBackground(new Color(0,102,0));

		balance.setForeground(Color.white);

		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(50, 20, 20, 20));
		buttonHit.setFont(font);
		buttonStand.setFont(font);
		buttonSplit.setFont(font);
		bet.setFont(font);
		boutonField.setFont(font);
		balance.setFont(font);
		buttonHit.setFont(font);
		buttonDouble.setFont(font);
		
		panel.add(buttonHit);
		panel.add(buttonStand);
		panel.add(buttonSplit);
		panel.add(buttonDouble);
		panel.add(bet);
		panel.add(boutonField);
		panel.add(balance);
		
		panel.setPreferredSize(new Dimension(100,180));
		panel.setBackground(Color.black);
		
		JPanel dealer = new JPanel();
		JPanel text = new JPanel();
		
		
		
		try {
			this.myPicture = ImageIO.read(new File("livraison/blackjack/src/PNG/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ImageIcon imageIcon = new ImageIcon(this.myPicture);
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(300, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back

		JLabel picLabel = new JLabel(imageIcon);
		picLabel.setSize(120,120);
		
		text.setLayout(new BorderLayout());
		text.add(picLabel, BorderLayout.CENTER);
		text.setPreferredSize(new Dimension(120,120));
		text.setBackground(new Color(0,102,0));
		dealer.setLayout(new BorderLayout());
		dealer.add(viewDealer , BorderLayout.WEST);	
		dealer.add(viewDeck , BorderLayout.EAST);
		panelJoueur.add(text);
		panelJoueur.add(viewPlayer2);
		contentPane.add(dealer , BorderLayout.NORTH);	
		

		contentPane.add(panel , BorderLayout.SOUTH);
		
		contentPane.add(panelJoueur, BorderLayout.CENTER);	


		//Option d'affichage de notre JFrame
		this.setSize(1000, 1000);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//centre la fenetre
		this.setVisible(true);// rend visible la fenetre
		this.setLayout(new BorderLayout());	
		//this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ferme la fentre et le programme 
	}
	
	
	
	//Tout les getters et setters necessaire pour manipuler dans le main
	


	//Getter and Setter 
	
	
	
	/**
	 * Methode qui renvoie le Jpanel des cartes de la pioche
	 * @return viewDeck  Jpanel
	 */
	public VuePaquetCache getVuePaquetCache() {
		return viewDeck;
	}

	
	/**
	 * Methode qui renvoie le Jpanel des cartes des mains joueurs
	 * @return viewPlayer Jpanel
	 */
	public VuePaquetVisible getVuePaquetJoueur() {
		return viewPlayer;
	}
	

	/**
	 * Methode qui retourne un bouton pour lancer un nouveau coup
	 * @return buttonHit Jbutton
	 */
	public JButton getJButtonHit() {
		return buttonHit;
	}
	
	/**
	 * Methode qui retourne un bouton pour diviser la main
	 * @return buttonSplit Jbutton
	 */
	public JButton getJButtonSplit() {
		return buttonSplit;
	}
	
	/**
	 * Methode qui retourne un bouton pour choucher ses cartes
	 * @return buttonstand Jbutton
	 */
	public JButton getJButtonStand() {
		return buttonStand;
	}
	
	/**
	 * Methode qui retourne un bouton pour faire un double de son jeu
	 * @return buttonDouble Jbutton
	 */
	public JButton getJButtonDouble() {
		return buttonDouble;
	}
	
	/**
	 * Methode qui retourne un bouton pour appliquer sa mise
	 * @return buttonField Jbutton
	 */
	public JButton getJButtonField() {
		return boutonField;
	}
	
	/**
	 * Methode qui paramètre un champ de texte pour ajouter sa mise
	 * @param string mise du joueur
	 * 
	 */
	public void SetField(String string)
	{
		bet.setText(string);
	}
	
	/**
	 * Methode qui retourne un texte: la mise du joueur
	 * @return bet JTextField
	 */
	public JTextField getJTextField() {
		return bet;
	}
	
	/**
	 * Methode pour modifier le solde du joueur
	 * @param balancePlayer solde du joueur
	 */
	public void SetJlabelBalance(String balancePlayer){
		balance.setText("Balance : " +  balancePlayer );
	}
	
	/**
	 * Methode qui retourne un Label permettant d'indiquer la main active
	 * @return p Jlabel
	 */
	public JLabel getP()
	{
		return P;
	}
	
	



		

}

	

