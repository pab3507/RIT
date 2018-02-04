package Pieces; /**
 * SinglePiece.java
 *
 * Version:
 *   $Id: SinglePiece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: SinglePiece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

import Game.Board;

import java.awt.*;
import java.util.Vector;

/**
 * This is a class representing a single piece (a piece that has not been
 * kinged yet)
 *
 * @author
 *
 */
public class SinglePiece extends Piece {
	
	
   private static int SINGLE = 0; // this is a single type
   private int type; // the type of the piece
   
   /**
    * This constructor creates a single piece checker object
    * 
    * @param c - the color of this single piece
    */
   public SinglePiece( Color c, Integer position) {
 
	    super(c, position);
		type = SINGLE;
   }
   
   /**
    * This method returns that the type of the checker is single
    * 
    * @return type which is 0 for single
    */
   public int getType() {
 
	   return type;
   }

    @Override
    public boolean moveIsValid() {



        return false;
    }

    @Override
    public Vector<Integer> checkForJumps(Board board) {
        Vector<Integer> possibleJumps = new Vector<>();

        int direction;// Variable representing the direction that the checker is moving in. +1 goes from bottom to top
        // and -1 goes from top to bottom. Direction is determined by the color of the piece.
        if (getColor() == Color.blue){
            direction = 1;
        }
        else {
            direction = -1;
        }
        int midRowPos = getRowPos() + direction;// The row of the board the checker will jump over, if a jump is possible.
        int midColPos1 = getColPos() + 1;// The two columns that might be jumped over.
        int midColPos2 = getColPos() - 1;
        int endRowPos = getRowPos() + direction*2;// The row the piece will end up in if it jumps.
        int endColPos1 = midColPos1 + 1;// The two columns that the piece could potentially end up in if it jumps.
        int endColPos2 = midColPos2 - 1;

        // Check that the middle and end rows are in the bounds of the board.
        if(inRange(midRowPos) && inRange(endRowPos)){
            // Check that the columns are within the bounds of the board.
            if (inRange(midColPos1) && inRange(endColPos1)){

                int midPosition1 = ((midRowPos)*8 + midColPos1);// The position of the jumped piece
                int endPosition1 = ((endRowPos)*8 + endColPos1);// The final position of the piece if jump occurs

                if (-1 < midPosition1 && midPosition1 < 65 && -1 < endPosition1 && endPosition1 < 65){
                    if(board.occupied(midPosition1) && board.colorAt(midPosition1) != getColor()
                            && !board.occupied(endPosition1)){
                        possibleJumps.add(endPosition1);
                    }
                }
            }
            if (inRange(midColPos2) && inRange(endColPos2)){
                int midPosition2 = ((midRowPos)*8 + midColPos2);
                int endPosition2 = ((endRowPos)*8 + endColPos2);

                if (-1 < midPosition2 && midPosition2 < 65 && -1 < endPosition2 && endPosition2 < 65){
                    if(board.occupied(midPosition2) && board.colorAt(midPosition2) != getColor()
                            && !board.occupied(endPosition2)){
                        possibleJumps.add(endPosition2);
                    }
                }
            }
        }

        return possibleJumps;
    }

    @Override
    public KingPiece kingThisPiece() {
        return new KingPiece(super.getColor(), this);
    }

    private boolean inRange(int i){
        return -1 < i && i < 8;
    }

}// SinglePiece
