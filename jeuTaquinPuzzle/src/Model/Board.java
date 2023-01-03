package Model;

import java.util.*;

import java.lang.Math;
import Control.*;

/**
 * Classe qui permet de gérer le plateau de jeu, les déplacements, etc.
 */
public class Board extends AbstractModeleExecutable {

    /**
     * Enumeration des directions possibles
     */
    public enum Direction {
        Up(1, 0),
        Down(-1, 0),
        Left(0, 1),
        Right(0, -1);

        final public int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    protected int nbLines;
    protected int nbColumns;

    protected Element[][] board;
    protected int size;
    protected final Element[][] copyBoard;
    
    /**
     * Construit un plateau de jeu avec nbLines lignes et nbColumns colonnes
     * @param nbLines
     * @param nbColumns
     */
    public Board(int nbLines, int nbColumns) {
        this.nbLines = nbLines;
        this.nbColumns = nbColumns;

        this.board = new Element[nbLines][nbColumns];
        this.copyBoard = new Element[nbLines][nbColumns];
        Integer cpt = 1;
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                Element el = new Element(i, j, cpt);
                board[i][j] = el;

                copyBoard[i][j] = el;
                if (i == nbLines - 1 && j == nbLines - 1) {
                    copyBoard[i][j] = null;
                }
                cpt++;
            }
        }
    }

    /**
     * Affiche le plateau de jeu
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.nbLines; i++) {
            for (int j = 0; j < this.nbColumns; j++) {
                res = res + board[i][j] + "\t ";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Retourne une valeur d'une case précise du tableau en chaine de caractères
     * @param posA la position sur la ligne
     * @param posB la position sur la colonne
     * @return
     */
    public String toStringValue(int posA, int posB) {
        String res = "";
        if (board[posA][posB].isEmpty()) {
            return "_";
        }
        res += board[posA][posB];
        return res;
    }

    /**
     * Met la case vide (null) a l'indice souhaité
     * @param indexLine
     * @param indexColumn
     */
    public void removeOneElement(int indexLine, int indexColumn) {
        this.board[indexLine][indexColumn] = new Element(indexLine, indexColumn, null);
    }

    /**
     * Met une case au hasard en case vide (null)
     */
    public void removeOneElementRandom() {
        Random rand = new Random();
        int i = rand.nextInt(nbLines);
        int j = rand.nextInt(nbColumns);
        this.board[i][j] = null;
    }

    /**
     * Tire n coups aléatoires (Mélange du plateau)
     * @param n
     */
    public void shuffle(int n) {
        this.exchange(Board.Direction.Up);
        this.exchange(Board.Direction.Left);
        this.exchange(Board.Direction.Left);
        double randomNumber;

        for (int i = 0; i < n; i++) {

            randomNumber = Math.random() * 3;

            if ((int) randomNumber == 0) {
                this.exchange(Board.Direction.Down);
            }
            if ((int) randomNumber == 1) {
                this.exchange(Board.Direction.Up);
            }
            if ((int) randomNumber == 2) {
                this.exchange(Board.Direction.Right);
            } else {
                this.exchange(Board.Direction.Left);
            }
        }
        this.exchange(Board.Direction.Right);

    }

    /**
     * Determnine si la partie est gagnée
     * @return boolean
     */
    public boolean hasWon() {
       
      

        String res = "";
        for (int i = 0; i < this.nbLines; i++) {
            for (int j = 0; j < this.nbColumns; j++) {
                res = res + copyBoard[i][j] + "\t ";
            }
            res += "\n";
        }

        boolean b = false;

        ArrayList <Boolean> init = new ArrayList<>();
        for (int i = 0; i < this.nbLines; i++) {
            for (int j = 0; j < this.nbColumns; j++) {
                if (!(i == nbLines-1 && j == nbColumns-1)) {

                    if(this.board[i][j].isEmpty()){
                        b = false;
                    }
                    else if (toStringValue(i, j).equals(copyBoard[i][j].toString())) {
                        b = true;
                    }
                    else {
                        b = false;            
                    }
                } 
                else {
                    if(this.board[i][j].isEmpty()){
                        b = true;
                    
                    }
                }
                init.add(b);

            }
        }
       
        return !(init.contains(false));
    }

    /**
     * Retourne la case vide
     * @return
     */
        public Element getEmptyCase() {
            for (int i = 0; i < nbLines; i++) {
                for (int j = 0; j < nbColumns; j++) {
                    if (board[i][j].isEmpty()) {
                        return board[i][j];
                    }
                }
            }
            return null;
        }

    /**
     * Fonction de déplacement, permet de faire un échange de positions entre la case vide et la case orienté à la direction d
     * @param d
     */
    public void exchange(Direction d) {
        int emptyX = getEmptyCase().getX();
        int emptyY = getEmptyCase().getY();
        if (possibleToMove(d, emptyX, emptyY)) {
            Element tmp = board[emptyX][emptyY];
            board[emptyX][emptyY] = board[emptyX - d.x][emptyY - d.y];
            board[emptyX - d.x][emptyY - d.y] = tmp;
            tmp.setX(emptyX - d.x);
            tmp.setY(emptyY - d.y);
            exchangeChangement();
           
        }
    }

    /**
     * Retourne le nombre de lignes du plateau
     * @return
     */
    public int getLines() {
        return nbLines;
    }

    /**
     * Retourne le nombre de colonnes du plateau
     * @return
     */
    public int getColumns() {
        return nbColumns;
    }

    /**
     * Retourne si le coup est possible à jouer
     */
    public boolean possibleToMove(Direction d, int x, int y) {
        return !(((x - d.x) > (nbLines - 1) || (x - d.x) < 0) || ((y - d.y) > (nbColumns - 1) || (y - d.y) < 0));
    }
}
