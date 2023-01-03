package View;

import Model.*;
import Control.*;

import Model.Board;
import java.awt.BorderLayout;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Cette classe permet d'afficher la fenêtre avec le Taquin
 */
public class TaquinGUI extends JFrame {

    private Board board;
    public JPanel repBoard;
    public int nbLignes;
    public int nbColonnes;
    JButton numberVersion = new JButton("Taquin Puzzle Chiffre");
    JButton imageVersion = new JButton("Taquin Puzzle Image");
    JTextField formatBoardLines;
    JTextField formatBoardColmuns;
    /**
     * Constructeur qui gère la fenêtre de présentation du jeu
     * @param board
     * @throws IOException
    */
    public TaquinGUI(Board board) throws IOException {

        super("JEU DE TAQUIN réalisé par Marta, Romain, Sara et Xue");
        this.board = board;
        
        

        
        Font buttonFont = new Font("Chilanka", Font.BOLD, 22);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255,255,102));
        Border blackline = BorderFactory.createLineBorder(Color.black,3);
        ImageIcon background = new ImageIcon("lib/background.jpg");
        JLabel back = new JLabel(background);
        JLabel bienvenue = new JLabel("Bienvenue!", SwingConstants.CENTER);
        this.setContentPane(back);
        Container contentPane = this.getContentPane();


        /*Bouton permettant d'accéder à la version taquin avec des chiffres*/
        numberVersion.addActionListener(e -> {
            try {
                
                new GUI(board, 0);
                this.dispose();
                

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        /*Bouton permettant d'accéder à la version taquin avec des images*/
        imageVersion.addActionListener(e -> {
            try {
                new GUI(board, 1);
                this.dispose();
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
        });
        bienvenue.setFont(new Font("Chilanka", Font.BOLD, 45));
        //bienvenue.setForeground(Color.BLUE);
        numberVersion.setFont(buttonFont);
        imageVersion.setFont(buttonFont);
        numberVersion.setBackground(Color.PINK);
        numberVersion.setBorder(blackline);
        imageVersion.setBorder(blackline);
        imageVersion.setBackground(new Color(102,102,255));
        GridLayout grid = new GridLayout(3, 3);
        grid.setVgap(20);
        buttonPanel.setLayout(grid);
        //buttonPanel.setBorder(blackline);
        buttonPanel.setOpaque(false);
        buttonPanel.add(bienvenue);
        buttonPanel.add(numberVersion);
        buttonPanel.add(imageVersion);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(400,300));
        contentPane.add(buttonPanel);
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

         
    }

  
}