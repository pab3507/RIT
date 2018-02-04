package Pieces; /**
 * Piece.java
 *
 * Version:
 *   $Id: Piece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: Piece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

/**
 * This is an abstract class representing any piece that
 * know about it's color and possible moves and captures
 *
 * @author
 *
 */

import Game.Board;

import java.util.*;
import java.awt.*;

public abstract class Piece {
	
    private Color color; // the color of the piece

    private Integer position; // The position of the piece on the board

      
   /**
    * The constructor for this piece
    * 
    * @param c - the color for this piece
    */
   public Piece( Color c, Integer position) {

	   // set the color
	   this.color = c;
       this.position = position;

   }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
    * The method which is abstract
    * 
    * @return the type of the piece
    */
   abstract public int getType();
   
   /**
    * This method returns the color of this piece
    * 
    * @return the color for this piece
    */
   public Color getColor() {
  
	   return color;
   }

    abstract public KingPiece kingThisPiece();

    abstract public boolean moveIsValid();

    abstract public Vector checkForJumps(Board board);

    protected int getRowPos(){
        return (position)/8;
    }
    protected int getColPos(){

        return (position)%8;
    }


}// Piece
