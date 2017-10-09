package model;


import backtracking.Backtracker;
import backtracking.ChessConfig;
import backtracking.Configuration;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * Created by liam on 11/26/16.
 */
public class ChessModel extends Observable {
    public static final int BOARD_SIZE = 4;
    //private ArrayList<String> undoStack;
    private ArrayList<Piece> pieces;
    private ChessConfig currentState;
    private String origFile;
    private boolean waiting; //Waiting for a second piece to be clicked (to take with the first clicked)
    private Piece taker;

    public ChessModel(String filename){
        if (filename.equals("")){
            this.pieces = new ArrayList<>();
            announce("update");
            return;
        }
        this.pieces = new ArrayList<>();
        //this.undoStack = new ArrayList<>();
        this.origFile = filename;
        try {
            this.currentState = new ChessConfig(filename);
            String[][] board = this.currentState.getBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(!board[i][j].equals("-")){
                        pieces.add(new Piece(board[i][j], i, j, 4));
                    }
                }
            }
        } catch (FileNotFoundException f){
            this.pieces = new ArrayList<>();
            this.origFile = null;

        }


    }

    public ArrayList<Piece> getPieces(){
        return this.pieces;
    }

    public void reset(){
        this.pieces = new ArrayList<>();
        this.waiting = false;
        //this.undoStack = new ArrayList<>();
        try {
            this.currentState = new ChessConfig(origFile);
            String[][] board = this.currentState.getBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(!board[i][j].equals("-")){
                        pieces.add(new Piece(board[i][j], i, j, 4));
                    }
                }
            }
            this.announce("reset");
        } catch (FileNotFoundException f){
            System.out.println("ERROR: Unable to find input file");
        }
    }

    public void callNewGame(){
        announce("newGame");
    }

    public void newGame(String filename){
        if (filename.equals("")){
            this.pieces = new ArrayList<>();
            announce("update");
            return;
        }
        this.pieces = new ArrayList<>();
        //this.undoStack = new ArrayList<>();
        this.origFile = filename;
        try {
            this.currentState = new ChessConfig(filename);
            String[][] board = this.currentState.getBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(!board[i][j].equals("-")){
                        pieces.add(new Piece(board[i][j], i, j, 4));
                    }
                }
            }
        } catch (FileNotFoundException f){
            System.out.println("ERROR: Unable to find input file");
        }
        announce("update");
    }

    public boolean canMove(Piece p, int fini, int finj){
        int ivector = fini - p.i;
        int jvector = finj - p.j;
        for (int i = 0; i < p.possibleMoves.length; i++) {
            if (p.possibleMoves[i][0] == ivector && p.possibleMoves[i][1] == jvector){
                return true;
            }
        }
        return false;
    }

    public void tryToTake(int iplace, int jplace){
        if (!waiting){
            for(Piece p : this.pieces){
                if (p.i == iplace && p.j == jplace){
                    this.taker = p;
                    waiting = true;
                    return;
                }
            }
        }
        else {
            for (Piece p : this.pieces){
                if (p.i == iplace && p.j == jplace && canMove(taker, iplace, jplace)){
                    if ((taker.i == iplace && taker.j == jplace)) {
                        break;
                    }
                    p.taken = true;
                    currentState.editSpace(taker.i, taker.j, "-");
                    currentState.editSpace(p.i, p.j, taker.type);
                    taker.i = p.i;
                    taker.j = p.j;
                    p.i = -1;
                    p.j = -1;
                    announce("taken");
                }
            }
            this.waiting = false;
            return;
        }
    }

    public void hint(){
        announce("hint");
    }

    public ArrayList<Piece> gethint(){
        Backtracker myBacktrack = new Backtracker();
        ArrayList<Configuration> configs = myBacktrack.solveWithPath(currentState);
        ChessConfig hintConfig = (ChessConfig) configs.get(0);
        String[][] board = hintConfig.getBoard();
        ArrayList<Piece> toReturn = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(!board[i][j].equals("-")){
                    toReturn.add(new Piece(board[i][j], i, j, 4));
                }
            }
        }
        return toReturn;
    }

    public String getPTHint(){
        Backtracker myBacktrack = new Backtracker();
        ArrayList<Configuration> configs = myBacktrack.solveWithPath(currentState);
        ChessConfig hintConfig = (ChessConfig) configs.get(0);
        return hintConfig.toString();
    }

    public void announce(String args){
        this.setChanged();
        notifyObservers(args);
    }

    public void printBoard(){
        String out = currentState.toString();
        System.out.println(out);
    }

    public boolean isGoal(){
        boolean goal = true;
        for (Piece p : pieces){
            if (!p.taken){
                goal = false;
            }
        }
        return goal;
    }

    public ArrayList<String> solvePT(){
        Backtracker myBacktrack = new Backtracker();
        ArrayList<Configuration> configs = myBacktrack.solveWithPath(currentState);
        ArrayList<String> toReturn = new ArrayList<>();
        for (Configuration c : configs){
            toReturn.add(c.toString());
        }
        return toReturn;
    }

    public void evaluate(String arg){
        Scanner input = new Scanner(System.in);
        switch (arg){
            case "move": {
                System.out.println("Source row: ");
                int srci = input.nextInt();
                System.out.println("Source col: ");
                int srcj = input.nextInt();
                System.out.println("Destination row: ");
                int desti = input.nextInt();
                System.out.println("Destination col: ");
                int destj = input.nextInt();
                tryToTake(srci, srcj);
                tryToTake(desti, destj);
                System.out.println();
                printBoard();
                break;
            }
            case "new": {
                System.out.println("Input file: ");
                String game = input.nextLine();
                newGame("data/"+game);
                System.out.println();
                printBoard();
                break;
            }
            case "restart": {
                reset();
                System.out.println();
                printBoard();
                break;
            }
            case "hint": {
                System.out.println(getPTHint());
                break;
            }
            case "solve": {
                ArrayList<String> Solutions = solvePT();
                int i = 1;
                for (String s : solvePT()){
                    System.out.println("Step " + i + ": ");
                    System.out.println(s);
                    i++;
                }
                break;
            }

            case "quit": {
                System.exit(0);
            }



        }
    }
    /*
    Hey Moises,
    Read this.
    You need to have the button call the tryToTake() method whenever it is clicked, it works as follows:
    If a first piece hasn't been selected, select this piece (it is named taker, the piece that takes the second)
    If a first piece has been selected, then take this piece if possible, if not, clear the first piece (this is
    to prevent a user from clicking on a piece that can't make a move and getting stuck)

    Have the hint button call hint(), then use update call getHint() and make a new window with that move

    Have the reset button (if there is one) reset and then update as normal

    If you need anything or have any questions, let me know!
     */

}
