package View;

import Model.Board;
import Control.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Classe qui permet d'afficher le plateau dans un JPanel
 */
public class ViewBoard extends JPanel implements MouseListener, EcouteurModele {
    private Board board;
    int dimX;
    int dimY;
    Boolean wonGame;
    BufferedImage[] allImages;
    public int version;
    Image fondBlanc = ImageIO.read(new File("lib/fondBlanc.jpg"));
    Image winner = ImageIO.read(new File("lib/winner.jpg"));
    static int nbMoves = 0;
    static JTextArea nbLabelMoves = new JTextArea("Mouvements effectués : ");

    /**
     * Construit un plateau de jeu avec nbLignes lignes et nbColumns colonnes
     * @param board
     * @param version
     * @throws IOException
     */
    public ViewBoard(Board board, int version) throws IOException {

        this.board = board;
        this.version = version;
        this.board.ajoutEcouteur(this);
        this.addMouseListener(this);

        allImages = slicingImage("lib/spongebob.jpg", this.board.getLines(), this.board.getColumns());

        setBackground(new Color(246, 215, 189));

    }

    /**
     * Met à jour le plateau de jeu
     */
    @Override
    public void modeleMisAJour(Object source) {
        nbLabelMoves.setEditable(true);
        repaint();
        nbMoves ++;
        nbLabelMoves.setText("Mouvements effectués : " + String.valueOf(nbMoves));
        nbLabelMoves.setEditable(false);
        System.out.println("Moves " + nbMoves);
    }
    

    /**
     * Découpe l'image en fonction du nombre de lignes et de colonnes choisis
     * @param fileName nom de l'image à découper en morceaux
     * @param rows     le nombre de lignes de notre grille
     * @param cols     le nombre de colonnes de notre grille
     * @return un tableau de morceaux d'images
     * @throws IOException
     */

    public static BufferedImage[] slicingImage(String fileName, int rows, int cols) throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); // reading the image file

        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; // Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                // Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();

                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
            }
        }
        return imgs;
    }

    /**
     * Dessine le plateau de jeu
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        Font usedFont = new Font("Serif", Font.PLAIN, dimX / 3);
        ArrayList<Integer> listDimX = new ArrayList<Integer>();
        ArrayList<Integer> listDimY = new ArrayList<Integer>();

        dimX = (int) this.getSize().getWidth() / board.getLines();
        dimY = (int) this.getSize().getHeight() / board.getColumns();

        if (this.board.hasWon()&& nbMoves > 1) {
            System.out.println("C'EST WIN");
            g.drawImage(winner, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), this);            
        }

        if (this.version == 1) {
            /* Remplissage du taquin avec les morceaux d'images */
            g.setColor(Color.WHITE);
            int countImage;
            for (int i = 0; i < this.board.getLines(); i++) {

                for (int j = 0; j < this.board.getColumns(); j++) {
                    try {
                        countImage = Integer.parseInt(this.board.toStringValue(i, j)) - 1;

                        g.drawImage(allImages[countImage], j * dimX, i * dimY, dimX, dimY, this);

                    } catch (Exception e) {
                        g.drawImage(fondBlanc, j * dimX, i * dimY, dimX, dimY, this);
                    }

                }
            }
        }

        g.setFont(usedFont);
        /* Dessin de la grille du taquin */
        for (int i = 0; i < this.board.getLines(); i++) {
            g.drawLine(x, 0, x, (int) this.getSize().getHeight());
            listDimX.add(x);

            x += dimX;
        }
        for (int j = 0; j < this.board.getColumns(); j++) {
            g.drawLine(0, y, (int) this.getSize().getWidth(), y);
            listDimY.add(y);
            y += dimY;
        }

        if (version == 0) {
            for (int i = 0; i < this.board.getLines(); i++) {
                for (int j = 0; j < this.board.getColumns(); j++) {

                    g.drawString(this.board.toStringValue(j, i), listDimX.get(i) + (dimX / 2),
                            listDimY.get(j) + (dimY / 2));

                }
            }
        }
    }

    /**
     * Méthode événementielle qui permet de capter les clics souris et de déplacer les cases en fonction des clics sur le plateau
     */
    @Override
    public void mousePressed(MouseEvent e) {
        /* c'est au clic souris que le modele est mis à jour */
        int posX = e.getY() / dimY;
        int posY = e.getX() / dimX;
        if (this.board.getEmptyCase().getX() == posX) {
            // Mouvement vers le bas de la case vide
            if (posY == (this.board.getEmptyCase().getY() + 1)) {
                this.board.exchange(Board.Direction.Right);
                System.out.println("DONE\n");
            }
            // Mouvement vers le haut de la case vide
            if (posY == (this.board.getEmptyCase().getY() - 1)) {
                this.board.exchange(Board.Direction.Left);
                System.out.println("DONE\n");
            }
        }
        if (this.board.getEmptyCase().getY() == posY) {
            // Mouvement vers la droite selon le dessin
            if (posX == (this.board.getEmptyCase().getX() + 1)) {
                this.board.exchange(Board.Direction.Down);
                System.out.println("DONE\n");
            }
            // Mouvement vers la gauche selon le dessin
            if (posX == (this.board.getEmptyCase().getX() - 1)) {
                this.board.exchange(Board.Direction.Up);
                System.out.println("DONE\n");
            }
        }
    }
    
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}
