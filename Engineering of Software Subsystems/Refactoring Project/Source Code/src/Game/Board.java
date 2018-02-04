package Game;
/**
 * Board.java
 * <p>
 * Version:
 * $Id: Board.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * <p>
 * Revisions:
 * $Log: Board.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 */
import java.util.*;
import java.awt.*;

import Pieces.*;


/**
 * This class represents the board on which checkers is being played.
 * The board holds a collection of pieces.
 *
 * @author
 * @invariant all variables have valid values
 */
public class Board {

    private Piece pieces[]; // the pieces that are on the board
    public static final int SINGLE = 0;
    public static final int KING = 1;

    /**
     * This constructor creates a new board at the beginning of the game
     */

    public Board() {

        // create a array of size 64, generate piece objects and
        // put them in the correct location in the array
        // Set the values of numWhites and numBlues to 12 each
        pieces = new Piece[64];

        // create blue pieces
        pieces[1] = new SinglePiece( Color.blue, 1);
        pieces[3] = new SinglePiece( Color.blue, 3);
        pieces[5] = new SinglePiece( Color.blue, 5);
        pieces[7] = new SinglePiece( Color.blue, 7);
        pieces[8] = new SinglePiece( Color.blue, 8);
        pieces[10] = new SinglePiece( Color.blue, 10);
        pieces[12] = new SinglePiece( Color.blue, 12);
        pieces[14] = new SinglePiece( Color.blue, 14);
        pieces[17] = new SinglePiece( Color.blue, 17);
        pieces[19] = new SinglePiece( Color.blue, 19);
        pieces[21] = new SinglePiece( Color.blue, 21);
        pieces[23] = new SinglePiece( Color.blue, 23);

        // create the white pieces
        pieces[40] = new SinglePiece( Color.white, 40);
        pieces[42] = new SinglePiece( Color.white, 42);
        pieces[44] = new SinglePiece( Color.white, 44);
        pieces[46] = new SinglePiece( Color.white, 46);
        pieces[49] = new SinglePiece( Color.white, 49);
        pieces[51] = new SinglePiece( Color.white, 51);
        pieces[53] = new SinglePiece( Color.white, 53);
        pieces[55] = new SinglePiece( Color.white, 55);
        pieces[56] = new SinglePiece( Color.white, 56);
        pieces[58] = new SinglePiece( Color.white, 58);
        pieces[60] = new SinglePiece( Color.white, 60);
        pieces[62] = new SinglePiece( Color.white, 62);

    }


    /**
     * Moves the piece at the start position to the end position
     *
     * @param start - current location of the piece
     * @param end   - the position where piece is moved
     * @return -1 if there is a piece in the end position
     */
    public int movePiece(int start, int end) {


        int returnValue = 0;

        // check if the end position of the piece is occupied
        if (occupied(end)) {

            // if it is return -1
            returnValue = -1;

            // if it is not set a start position in the array to null
            // put a piece object at the end position in the array
            // that was at the start position before
        } else {

            pieces[end] = pieces[start];
            getPieceAt(end).setPosition(end);
            pieces[start] = null;
        }

        return returnValue;

    }


    /**
     * This method checks if the space on the board contains a piece
     *
     * @param space - the space that needs to be checked
     * @return true or false depending on the situation
     */
    public boolean occupied(int space) {

        boolean returnValue = true;

        // if it's in the bounds of the array,
        // return true if the space is occupied
        // false if the space is empty
        // if it's outside the bounds of the array,
        // return true
        if (space >= 1 && space <= 63 && pieces[space] == null) {
            returnValue = false;
        }

        return returnValue;
    }


    /**
     * This method removes piece at the position space
     *
     * @param space - the position of the piece to be removed
     */
    public void removePiece(int space) {

        // go to the space position in the array and set it equal to null

        pieces[space] = null;

    }


    /**
     * This method creates a king piece
     *
     * @param space - the position at which the king piece is created
     */
    public void kingPiece(int space) {

        // create a new king piece go to the space position in the array and place it there
        // if the position is not occupied

        pieces[space] = pieces[space].kingThisPiece();

    }


    /**
     * This method returns the color of the piece at a certain space
     *
     * @param space - the position of the piece on the board
     * @return the color of this piece
     */
    public Color colorAt(int space) {

        Color returnValue = null;
        // go to the space position in the array
        // check if there is a piece at that position
        // if there is none, return null
        // else return the color of the piece

        if (occupied(space)) {

            returnValue = pieces[space].getColor();

        }

        return returnValue;

    }


    /**
     * This method returns the piece at the certain position
     *
     * @param space - the space of the piece
     * @return the piece at that space
     */
    public Piece getPieceAt(int space) {

        Piece returnValue = new SinglePiece(Color.red, 1000);

        try{
            // Check if there is piece at space position .If there is none, return null
            // else return the piece at that position
            if (occupied(space)) {
                returnValue = pieces[space];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }


    /**
     * This method returns if there is a piece of color on the board
     *
     * @param color - the color of the piece
     * @return true if there is a piece of color left on the board
     * else return false
     */
    public boolean hasPieceOf(Color color) {



        // go through the whole array
        // if there is a piece of color in the array return true
        // else return false
        for (int i = 1; i < pieces.length; i++) {

            if( pieces[i] != null && pieces[i].getColor() == color ) {
                return true;
            }
        }
        return false;

    }


    /**
     * This method returns the size of the board
     *
     * @return the size of the board, always 64
     */
    public int sizeOf() {
        return 64;
    }


    /**
     * This method returns a vector containing all blue Pieces
     *
     * @return blue pieces on the board
     */
    public Vector bluePieces() {

        Vector bluePieces = new Vector();

        for (int i = 0; i < 64; i++) {
            if (occupied(i)) {
                if (pieces[i].getColor() == Color.blue) {
                    bluePieces.addElement(pieces[i]);
                }
            }
        }

        return bluePieces;

    }


    /**
     * This method returns a vector containing all white Pieces
     *
     * @return white pieces on the board
     */
    public Vector whitePieces() {

        Vector whitePieces = new Vector();

        for (int i = 0; i < 64; i++) {
            if (occupied(i)) {
                if (pieces[i].getColor() == Color.white) {
                    whitePieces.addElement(pieces[i]);
                }
            }
        }

        return whitePieces;
    }

}//Board

