package model;

import java.util.ArrayList;

/**
 * Created by liam on 11/26/16.
 */
public class Piece {
    public String type;
    public boolean taken;
    public int i;
    public int j;
    int[][] possibleMoves;

    public Piece (String s, int y, int x, int DIM){
        this.type = s;
        this.i = y;
        this.j = x;
        this.taken = false;
        switch (this.type){
            case "K": {
                possibleMoves = new int[8][2];
                //Directly up
                possibleMoves[0][0] = 0;
                possibleMoves[0][1] = 1;
                //Up and Right
                possibleMoves[1][0] = 1;
                possibleMoves[1][1] = 1;
                //Directly Right
                possibleMoves[2][0] = 1;
                possibleMoves[2][1] = 0;
                //Down and Right
                possibleMoves[3][0] = 1;
                possibleMoves[3][1] = -1;
                //Directly Down
                possibleMoves[4][0] = 0;
                possibleMoves[4][1] = -1;
                //Down and Left
                possibleMoves[5][0] = -1;
                possibleMoves[5][1] = -1;
                //Directly Left
                possibleMoves[6][0] = -1;
                possibleMoves[6][1] = 0;
                //Up and left
                possibleMoves[7][0] = -1;
                possibleMoves[7][1] = 1;
                break;
            }
            case "N": {
                possibleMoves = new int[8][2];
                //Up 2 and right
                possibleMoves[0][0] = 1;
                possibleMoves[0][1] = 2;
                //Up 2 and left
                possibleMoves[1][0] = -1;
                possibleMoves[1][1] = 2;
                //Down 2 and right
                possibleMoves[2][0] = 1;
                possibleMoves[2][1] = -2;
                //Down 2 and Left
                possibleMoves[3][0] = -1;
                possibleMoves[3][1] = -2;
                //Right 2 and up
                possibleMoves[4][0] = 2;
                possibleMoves[4][1] = 1;
                //Right 2 and Down
                possibleMoves[5][0] = 2;
                possibleMoves[5][1] = -1;
                //Left 2 and up
                possibleMoves[6][0] = -2;
                possibleMoves[6][1] = 1;
                //Left 2 and down
                possibleMoves[7][0] = -2;
                possibleMoves[7][1] = -1;
                break;
            }
            case "P": {
                possibleMoves = new int[2][2];
                //Up and right
                possibleMoves[0][0] = -1;
                possibleMoves[0][1] = 1;
                //Up and Left
                possibleMoves[1][0] = -1;
                possibleMoves[1][1] = -1;
                break;
            }
            case "Q": {
                possibleMoves = new int[(DIM)*8][2];
                for (int i = 1; i < DIM; i++) {
                    //Rook Portion
                    possibleMoves[i][0] = i;
                    possibleMoves[i][1] = 0;
                    possibleMoves[DIM-1+i][0] = -i;
                    possibleMoves[DIM-1+i][1] = 0;
                    possibleMoves[2*(DIM-1)+i][0] = 0;
                    possibleMoves[2*(DIM-1)+i][1] = i;
                    possibleMoves[3*(DIM-1)+i][0] = 0;
                    possibleMoves[3*(DIM-1)+i][1] = -i;
                    //Bishop Portion
                    possibleMoves[4*(DIM-1)+i][0] = i;
                    possibleMoves[4*(DIM-1)+i][1] = i;
                    possibleMoves[5*(DIM-1)+i][0] = -i;
                    possibleMoves[5*(DIM-1)+i][1] = i;
                    possibleMoves[6*(DIM-1)+i][0] = i;
                    possibleMoves[6*(DIM-1)+i][1] = -i;
                    possibleMoves[7*(DIM-1)+i][0] = -i;
                    possibleMoves[7*(DIM-1)+i][1] = -i;
                }
                break;
            }
            case "R" : {
                possibleMoves = new int[(DIM) * 4][2];
                for (int i = 1; i < DIM; i++) {
                    //Rook Portion
                    possibleMoves[i][0] = i;
                    possibleMoves[i][1] = 0;
                    possibleMoves[DIM - 1 + i][0] = -i;
                    possibleMoves[DIM - 1 + i][1] = 0;
                    possibleMoves[2 * (DIM - 1) + i][0] = 0;
                    possibleMoves[2 * (DIM - 1) + i][1] = i;
                    possibleMoves[3 * (DIM - 1) + i][0] = 0;
                    possibleMoves[3 * (DIM - 1) + i][1] = -i;
                }
                break;
            }
            case "B" : {
                possibleMoves = new int[(DIM)*4][2];
                for (int i = 1; i < DIM; i++) {
                    //Bishop Portion
                    possibleMoves[i][0] = i;
                    possibleMoves[i][1] = i;
                    possibleMoves[(DIM-1)+i][0] = -i;
                    possibleMoves[(DIM-1)+i][1] = i;
                    possibleMoves[2*(DIM-1)+i][0] = i;
                    possibleMoves[2*(DIM-1)+i][1] = -i;
                    possibleMoves[3*(DIM-1)+i][0] = -i;
                    possibleMoves[3*(DIM-1)+i][1] = -i;
                }
                break;
            }
        }

    }
    public String toString(){
        return this.type + " at " + this.i +  ", " + this.j;
    }

}
