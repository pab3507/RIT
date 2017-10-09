package backtracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by liam on 11/22/16.
 */
public class ChessConfig implements Configuration{
    //Using constants for the pieces for more readability
    private final String KNIGHT = "N";
    private final String KING = "K";
    private final String QUEEN = "Q";
    private final String ROOK = "R";
    private final String PAWN = "P";
    private final String BISHOP = "B";
    private final String EMPTY_CELL = "-";

    //The board is going to be a collection of Strings
    private String[][] board;

    private int DIM;
    //private int onBoard;
    private int depth; //0 is original, the rest indicate how many "generations" old the config is
    private ChessConfig predecessor; //Used to establish a chain of configurations for hints and step display

    /**
     * Constructor for file input
     * @param filename
     * @throws FileNotFoundException
     */
    public ChessConfig(String filename) throws FileNotFoundException {
        Scanner f = new Scanner(new File(filename));

        depth = 0;
        predecessor = null;
        DIM = 4;
        board = new String[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.board[i][j] = f.next();
                if (!this.board[i][j].equals(EMPTY_CELL)){
                    //onBoard ++;
                }
            }
        }
        // close the input file
        f.close();
    }

    /**
     * Constructor for deep copying
     * @param config
     */
    public ChessConfig(ChessConfig config){
        this.depth = config.depth +1;
        this.predecessor = config;
        this.DIM = config.DIM;
        board = new String[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.board[i][j] = config.board[i][j];
            }
        }
        //this.onBoard = config.onBoard;
    }


    @Override
    public Collection<Configuration> getSuccessors() {
        Collection<Configuration> toReturn = new ArrayList<Configuration>();

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (!board[i][j].equals(EMPTY_CELL)) {
                    switch (board[i][j]) {
                        //If the piece is a knight
                        case KNIGHT: {
                            int[][] possibleMoves = new int[8][2];
                            //Up 2 and right
                            possibleMoves[0][0] = i + 1;
                            possibleMoves[0][1] = j + 2;
                            //Up 2 and left
                            possibleMoves[1][0] = i - 1;
                            possibleMoves[1][1] = j + 2;
                            //Down 2 and right
                            possibleMoves[2][0] = i + 1;
                            possibleMoves[2][1] = j - 2;
                            //Down 2 and Left
                            possibleMoves[3][0] = i - 1;
                            possibleMoves[3][1] = j - 2;
                            //Right 2 and up
                            possibleMoves[4][0] = i + 2;
                            possibleMoves[4][1] = j + 1;
                            //Right 2 and Down
                            possibleMoves[5][0] = i + 2;
                            possibleMoves[5][1] = j - 1;
                            //Left 2 and up
                            possibleMoves[6][0] = i - 2;
                            possibleMoves[6][1] = j + 1;
                            //Left 2 and down
                            possibleMoves[7][0] = i - 2;
                            possibleMoves[7][1] = j - 1;
                            for (int k = 0; k < possibleMoves.length; k++) {
                                //If the move is in bounds
                                if (possibleMoves[k][0] >= 0 && possibleMoves[k][0] < DIM &&
                                        possibleMoves[k][1] >= 0 && possibleMoves[k][1] < DIM) {
                                    //If the move will actually take a piece
                                    if (!board[possibleMoves[k][0]][possibleMoves[k][1]].equals(EMPTY_CELL)) {
                                        toReturn.add(generateSuccessor(i,j, possibleMoves[k][0],
                                                possibleMoves[k][1], KNIGHT));
                                    }
                                }
                            }
                            break;
                        }

                        //If the piece is a pawn
                        case PAWN: {
                            int[][] possibleMoves = new int[2][2];
                            //Up and right
                            possibleMoves[0][0] = i-1;
                            possibleMoves[0][1] = j+1;
                            //Up and Left
                            possibleMoves[1][0] = i-1;
                            possibleMoves[1][1] = j-1;

                            for (int k = 0; k < possibleMoves.length; k++) {
                                //If the move is in bounds
                                if (possibleMoves[k][0] >= 0 && possibleMoves[k][0] < DIM &&
                                        possibleMoves[k][1] >= 0 && possibleMoves[k][1] < DIM) {
                                    //If the move will actually take a piece
                                    if (!board[possibleMoves[k][0]][possibleMoves[k][1]].equals(EMPTY_CELL)) {
                                        toReturn.add(generateSuccessor(i,j, possibleMoves[k][0],
                                                possibleMoves[k][1], PAWN));
                                    }
                                }
                            }
                            break;
                        }
                        //If the piece is a king
                        case KING: {
                            int[][] possibleMoves = new int[8][2];
                            //Directly up
                            possibleMoves[0][0] = i;
                            possibleMoves[0][1] = j + 1;
                            //Up and Right
                            possibleMoves[1][0] = i + 1;
                            possibleMoves[1][1] = j + 1;
                            //Directly Right
                            possibleMoves[2][0] = i + 1;
                            possibleMoves[2][1] = j;
                            //Down and Right
                            possibleMoves[3][0] = i + 1;
                            possibleMoves[3][1] = j - 1;
                            //Directly Down
                            possibleMoves[4][0] = i;
                            possibleMoves[4][1] = j - 1;
                            //Down and Left
                            possibleMoves[5][0] = i - 1;
                            possibleMoves[5][1] = j - 1;
                            //Directly Left
                            possibleMoves[6][0] = i - 1;
                            possibleMoves[6][1] = j;
                            //Up and left
                            possibleMoves[7][0] = i - 1;
                            possibleMoves[7][1] = j + 1;
                            //For each possible, valid move, generate a successor
                            for (int k = 0; k < possibleMoves.length; k++) {
                                //If the move is in bounds
                                if (possibleMoves[k][0] >= 0 && possibleMoves[k][0] < DIM &&
                                        possibleMoves[k][1] >= 1 && possibleMoves[k][1] < DIM) {
                                    //If the move will actually take a piece
                                    if (!board[possibleMoves[k][0]][possibleMoves[k][1]].equals(EMPTY_CELL)) {
                                        toReturn.add(generateSuccessor(i,j, possibleMoves[k][0],
                                                possibleMoves[k][1], KING));
                                    }
                                }
                            }
                            break;
                        }
                        //If the piece is a Queen
                        case QUEEN: {
                            //This is a bit trickier because there is not a set number of possible spaces
                            //that a queen can move
                            int k = 1; //This is just a counter
                            //Booleans are to prevent jumping
                            boolean downTake = false;
                            boolean upTake = false;
                            boolean rightTake = false;
                            boolean leftTake = false;
                            boolean diag1 = false;
                            boolean diag2 = false;
                            boolean diag3 = false;
                            boolean diag4 = false;
                            while (k < DIM) {
                                //Moving directly up/down or left/right (this is exactly what a rook would do)
                                if (i+k < DIM){
                                    if(!board[i+k][j].equals(EMPTY_CELL) && !downTake){
                                        downTake = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j,QUEEN));
                                    }
                                }
                                if (i-k >= 0){
                                    if(!board[i-k][j].equals(EMPTY_CELL) && !upTake){
                                        upTake = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j,QUEEN));
                                    }
                                }
                                if (j+k < DIM){
                                    if(!board[i][j+k].equals(EMPTY_CELL) && !rightTake){
                                        rightTake = true;
                                        toReturn.add(generateSuccessor(i,j,i,j+k, QUEEN));
                                    }
                                }
                                if(j-k >= 0){
                                    if(!board[i][j-k].equals(EMPTY_CELL) && !leftTake){
                                        leftTake = true;
                                        toReturn.add(generateSuccessor(i,j,i,j-k, QUEEN));
                                    }
                                }
                                //Moving Diagonally (this is what a bishop would do
                                if (i+k < DIM && j+k < DIM) {
                                    if (!board[i+k][j+k].equals(EMPTY_CELL) && !diag1){
                                        diag1 = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j+k,QUEEN));
                                    }
                                }
                                if (i-k >= 0 && j+k < DIM) {
                                    if (!board[i-k][j+k].equals(EMPTY_CELL) && !diag2){
                                        diag2 = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j+k,QUEEN));
                                    }
                                }
                                if (i+k < DIM && j-k >= 0) {
                                    if (!board[i+k][j-k].equals(EMPTY_CELL) && !diag3){
                                        diag3 = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j-k,QUEEN));
                                    }
                                }
                                if (i-k >= 0 && j-k >= 0) {
                                    if (!board[i-k][j-k].equals(EMPTY_CELL) && !diag4){
                                        diag4 = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j-k,QUEEN));
                                    }
                                }
                                k++;
                            }
                            break;
                        }
                        //If the piece is a rook
                        case ROOK: {
                            int k = 1;
                            boolean downTake = false;
                            boolean upTake = false;
                            boolean rightTake = false;
                            boolean leftTake = false;
                            while (k < DIM) {
                                if (i+k < DIM){
                                    if(!board[i+k][j].equals(EMPTY_CELL) && !downTake){
                                        downTake = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j,ROOK));
                                    }
                                }
                                if (i-k >= 0){
                                    if(!board[i-k][j].equals(EMPTY_CELL) && !upTake){
                                        upTake = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j,ROOK));
                                    }
                                }
                                if (j+k < DIM){
                                    if(!board[i][j+k].equals(EMPTY_CELL) && !rightTake){
                                        rightTake = true;
                                        toReturn.add(generateSuccessor(i,j,i,j+k, ROOK));
                                    }
                                }
                                if(j-k >= 0){
                                    if(!board[i][j-k].equals(EMPTY_CELL) && !leftTake){
                                        leftTake = true;
                                        toReturn.add(generateSuccessor(i,j,i,j-k, ROOK));
                                    }
                                }
                                k++;
                            }
                            break;
                        }
                        //If the piece is a bishop
                        case BISHOP: {
                            int k = 1;
                            boolean diag1 = false;
                            boolean diag2 = false;
                            boolean diag3 = false;
                            boolean diag4 = false;
                            while (k < DIM){
                                if (i+k < DIM && j+k < DIM) {
                                    if (!board[i+k][j+k].equals(EMPTY_CELL) && !diag1){
                                        diag1 = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j+k,BISHOP));
                                    }
                                }
                                if (i-k >= 0 && j+k < DIM) {
                                    if (!board[i-k][j+k].equals(EMPTY_CELL) && !diag2){
                                        diag2 = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j+k,BISHOP));
                                    }
                                }
                                if (i+k < DIM && j-k >= 0) {
                                    if (!board[i+k][j-k].equals(EMPTY_CELL) && !diag3){
                                        diag3 = true;
                                        toReturn.add(generateSuccessor(i,j,i+k,j-k,BISHOP));
                                    }
                                }
                                if (i-k >= 0 && j-k >= 0) {
                                    if (!board[i-k][j-k].equals(EMPTY_CELL) && !diag4){
                                        diag4 = true;
                                        toReturn.add(generateSuccessor(i,j,i-k,j-k,BISHOP));
                                    }
                                }
                                k++;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (toReturn.equals(new ArrayList<>())){
            return new ArrayList<>();
        }
        return toReturn;
    }

    /**
     * A quick method to replace a heavily used block in getSuccessors
     * This is for clarity, but not functionally necessary
     * @param initi
     * @param initj
     * @param fini
     * @param finj
     * @param piece
     * @return the succeeding configuration
     */
    private ChessConfig generateSuccessor(int initi, int initj, int fini, int finj, String piece){
        ChessConfig quickConfig = new ChessConfig(this);
        quickConfig.board[initi][initj] = EMPTY_CELL;
        quickConfig.board[fini][finj] = piece;
        return quickConfig;
    }
    @Override
    public boolean isValid() { //I'm not sure how an invalid configuration would be generated by getSuccessors()
        //The problem is that the only condition needed is for a piece to be taken, which is way easier to check
        //when you have both configurations like in getSuccessors
        return true;
    }

    /**
     * To String
     * @return the String representation of the configuration
     */
    public String toString(){
        String toReturn = "";
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                toReturn += board[i][j];
                toReturn += " ";
            }
            toReturn +="\n";
        }
        return toReturn;
    }

    public boolean isGoal(){
        int count = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (!board[i][j].equals(EMPTY_CELL)){
                    count += 1;
                }
            }
        }
        return (count == 1);
    }

    public boolean hasPred(){
        return this.predecessor != null;
    }

    public ChessConfig getPred(){return this.predecessor;}

    public void editSpace(int i, int j, String chngeTo){
        this.board[i][j] = chngeTo;
    }

    public String[][] getBoard(){return this.board;}
}
