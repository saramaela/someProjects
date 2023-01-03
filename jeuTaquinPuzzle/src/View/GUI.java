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

/**
 * Cette classe permet d'afficher le GUI, (menu principal du jeu lancé à l'éxécution du Main)
 */
public class GUI extends JFrame implements ActionListener {


    private Board board;
    public JPanel repBoard;
    public int nbLignes;
    public int nbColonnes;
    public JRadioButton easyLevel;
    public JRadioButton midLevel;
    public JRadioButton hardestLevel;
    public JTextField formatBoardLines;
    public JTextField formatBoardColumns;
    public JTextArea labelMoves;

    public int version;
    public int nbShuffle;

    /**
     * Construit un GUI avec un board par defaut 
     * @param board
     * @param version
     * @throws IOException
     */
    public GUI(Board board, int version) throws IOException {
        
        super("JEU DE TAQUIN réalisé par Marta, Romain, Sara et Xue");

        this.board = board;
        this.version = version;

        Container cp = this.getContentPane();
        JPanel subMenu = new JPanel();
        Font font = new Font("Chilanka", Font.BOLD, 22);
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        JButton shuffleButton = new JButton("Shuffle");
        JButton returnButton = new JButton("Retour");
        JButton restartButton = new JButton("Nouvelle Partie");
        ButtonGroup choiceLevel = new ButtonGroup();
        JLabel labelFormatBoard = new JLabel("Format de grille de jeu souhaité");
        JLabel labelLevelGame = new JLabel("Choissisez votre niveau de jeu");
        
        
        
        labelMoves = ViewBoard.nbLabelMoves;
        labelMoves.setEditable(false);
        formatBoardLines = new JTextField("3");
        formatBoardColumns = new JTextField("3");
        easyLevel = new JRadioButton("Niveau facile");
        easyLevel.setBackground(new Color(204,204,255));
        easyLevel.setFont(font);
        midLevel = new JRadioButton("Niveau moyen");
        midLevel.setFont(font);
        midLevel.setBackground(new Color(204,204,255));
        hardestLevel = new JRadioButton("Niveau Difficile");
        hardestLevel.setBackground(new Color(204,204,255));
        hardestLevel.setFont(font);
        choiceLevel.add(easyLevel);
        choiceLevel.add(midLevel);
        choiceLevel.add(hardestLevel);
        // subMenu.setBackground(new Color(236, 189, 144));

        if (version == 0) {
            repBoard = new ViewBoard(this.board, 0);
            

        } 
        if (version == 1) {
            repBoard = new ViewBoard(this.board, 1);
        }
        
        returnButton.addActionListener(e -> {
            dispose();
            Board newBoard = new Board(3, 3);
            newBoard.removeOneElement(2,2); 
            try {
                new TaquinGUI(newBoard);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        shuffleButton.addActionListener(this);
        restartButton.addActionListener(e -> {
            ViewBoard.nbMoves = 0;
            int newVersion = this.version;
            int lines = Integer.parseInt(formatBoardLines.getText());
            int columns = Integer.parseInt(formatBoardColumns.getText());
            System.out.println("LIGNES ET COLONES  " + lines + " " + columns);

            labelMoves.setText(" Mouvement effectués :" + ViewBoard.nbMoves );
            this.dispose();
            Board newBoard = new Board(lines, columns);
            System.out.println("NEW " + newBoard.toString());
            System.out.println("BOARD " + board.toString());
            newBoard.removeOneElement(lines - 1, columns - 1);


            newBoard.ajoutEcouteur(new EcouteurModele() {
                @Override
                public void modeleMisAJour(Object source) {
                    System.out.println("Alerte changement!\n" + newBoard.toString());
                }


            });
            
            
            try {
                new GUI(newBoard, newVersion);
                this.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        
        labelMoves.setFont(font);
        labelMoves.setBackground(new Color(204,204,255));
        //labelMoves.setEnabled(false);

        shuffleButton.setFont(font);
        shuffleButton.setBackground(new Color(153,153,255));

        returnButton.setFont(font);
        returnButton.setBackground(new Color(255,102,102));
 

        labelFormatBoard.setFont(font);
        labelLevelGame.setFont(font);
        
        restartButton.setFont(font);
        restartButton.setBackground(new Color(255,255,102));

        subMenu.setBackground(new Color(204,204,255));
        subMenu.setLayout(new GridLayout(11, 1, 20, 25));
        subMenu.add(returnButton);
        subMenu.add(labelLevelGame);
        subMenu.add(easyLevel);
        subMenu.add(midLevel);
        subMenu.add(hardestLevel);
        
        subMenu.add(shuffleButton);
        subMenu.add(labelFormatBoard);
        subMenu.add(formatBoardLines);
        subMenu.add(formatBoardColumns);
        subMenu.add(restartButton);
        subMenu.add(labelMoves);
        

        cp.setLayout(new BorderLayout());
        cp.add(repBoard, BorderLayout.CENTER);
        cp.add(subMenu, BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension((int) dimension.getWidth(), (int) dimension.getHeight()));
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DEMANDE DE SHUFFLE");
        // on ajoute 4 à chaque fois parce qu'il y 4 + n mélanges dans la méthode shuffle
        
        int beforeShuffles = ViewBoard.nbMoves;
        if (easyLevel.isSelected()) {
            this.board.shuffle(14);
            nbShuffle = 18;
            
            System.out.println("Voici---> 18 suffles");
        }
        if (midLevel.isSelected()) {
            this.board.shuffle(17);
            nbShuffle = 21;
            System.out.println("Voici---> 21 suffles");
        }
        if (hardestLevel.isSelected()) {
            this.board.shuffle(20);
            nbShuffle = 24;
            
        }
        ViewBoard.nbMoves = beforeShuffles;
        labelMoves.setText(" Mouvement effectués : " + ViewBoard.nbMoves);
       

    }

}
