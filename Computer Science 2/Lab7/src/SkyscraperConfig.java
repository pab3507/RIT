import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Represents a single configuration in the skyscraper puzzle.
 *
 * @author tjb T.J. Borrelli
 * @author Moisés Lora Pérez mal3941@rit.edu
 */
public class SkyscraperConfig implements Configuration {
    /** empty cell value */
    public final static int EMPTY = 0;

    /** empty cell value display */
    public final static char EMPTY_CELL = '.';
    private ArrayList<Integer> lookNS = new ArrayList<>();
    private ArrayList<Integer> lookEW = new ArrayList<>();
    private ArrayList<Integer> lookSN = new ArrayList<>();
    private ArrayList<Integer> lookWE = new ArrayList<>();
    private int [][] board;
    private int DIM;
    private int [] current;
    private int [] previous;
    private int row;
    private int col;
    private int canSee = 0;
    private int lastSaw = EMPTY;

    /**
     * Constructor
     *
     * @param filename the filename
     *  <p>
     *  Read the board file.  It is organized as follows:
     *  DIM     # square DIMension of board (1-9)
     *  lookNS   # DIM values (1-DIM) left to right
     *  lookEW   # DIM values (1-DIM) top to bottom
     *  lookSN   # DIM values (1-DIM) left to right
     *  lookWE   # DIM values (1-DIM) top to bottom
     *  row 1 values    # 0 for empty, (1-DIM) otherwise
     *  row 2 values    # 0 for empty, (1-DIM) otherwise
     *  ...
     *
     * @throws FileNotFoundException if file not found
     */
    SkyscraperConfig(String filename) throws FileNotFoundException {

        Scanner f = new Scanner(new File(filename));

        this.DIM =  f.nextInt();
        this.board = new int[DIM][DIM];
        for(int i = 0; i<DIM;i++){
            lookNS.add(f.nextInt());
        }
        for(int i = 0; i<DIM;i++){
            lookEW.add(f.nextInt());
        }
        for(int i = 0; i<DIM;i++){
            lookSN.add(f.nextInt());
        }
        for(int i = 0; i<DIM;i++){
            lookWE.add(f.nextInt());
        }

        for(int r = 0; r < DIM; r++ ){
            for(int c = 0; c < DIM; c++){
                board[r][c] = f.nextInt();
            }
        }
        while(true){
            if ((row == DIM && col == 0) || board[row][col] == EMPTY) {
                break;
            } else {
                col +=1;
                if(col >= DIM){
                    row+=1;
                    col = 0;
                }
            }
        }
        this.current = new int[2];
        this.previous = new int[2];
        this.current[0] = row;
        this.current[1] = col;
        // close the input file
        f.close();
    }

    /**
     * Copy constructor
     *
     * @param copy SkyscraperConfig instance
     */
    public SkyscraperConfig(SkyscraperConfig copy) {
        this.DIM = copy.DIM;
        this.board = new int[copy.DIM][copy.DIM];
        this.lookNS = copy.lookNS;
        this.lookEW = copy.lookEW;
        this.lookSN = copy.lookSN;
        this.lookWE = copy.lookWE;
        this.previous = copy.previous;
        this.current = copy.current;
        for(int row = 0; row < copy.DIM; row++){
            System.arraycopy(copy.board[row], 0, this.board[row],0, copy.DIM);
        }
        System.arraycopy(copy.current,0,this.current,0,2);
        System.arraycopy(copy.previous,0,this.previous,0,2);
    }


    @Override
    public boolean isGoal() {
        return (current[0] >= DIM && current[1] >= 0);
    }

    /**
     * isValid() - checks if current config is valid
     *
     * @returns true if config is valid, false otherwise
     */
    @Override
    public boolean isValid() {


        row = previous[0];
        col = previous[1];
        HashSet<Integer> legalValsRow = new HashSet<>();
        for (int val = 1; val < DIM+1; val++){
            //legalValsRow.add(val);
        }
        for (int r = 0; r < DIM; r++) {
            if (board[r][col] == EMPTY) continue;
            else if (!legalValsRow.contains(board[r][col])) return false;
            else {
                legalValsRow.remove(board[r][col]);
            }
        }
        HashSet<Integer> legalValsCol = new HashSet<>();

        for (int val = 1; val < DIM+1; val++) //legalValsCol.add(val);
        for (int c = 0; c < DIM; c++)
            if (board[row][c] == EMPTY) continue;
            else if (!legalValsCol.contains(board[row][c])) return false;
            else {
                legalValsCol.remove(board[row][c]);
            }

        canSee = 0;
        lastSaw = EMPTY;
        //check N


        for (int r =0; r<row+1; r++){
            if (board[r][col] >= lastSaw){
                canSee+=1;
                lastSaw = board[r][col];

            }
        }
        if ((canSee > lookNS.get(col)) || (((row == (DIM - 1)) && (lookNS.get(col) != canSee)))){
                return false;
            }

        //check S
        if(row == (DIM-1)){
            canSee = 0;
            lastSaw = EMPTY;
            for (int r = row; r>=0; r--){
                if(board[r][col] >= lastSaw){
                    canSee+=1;
                    lastSaw = board[r][col];
                }
            }
            if ((canSee!= lookSN.get(col))){
                return false;
            }
        }
        //check W
        canSee = 0;
        lastSaw = EMPTY;

        for (int c =0; c<col+1; c++){
            if (board[row][c] >= lastSaw){
                canSee+=1;
                lastSaw = board[row][c];

            }
        }
        if ((canSee > lookWE.get(row)) || (((col == (DIM - 1)) && (canSee != lookWE.get(row))))){
            return false;
        }

        //check E
        if(col == (DIM-1)){
            canSee = 0;
            lastSaw = EMPTY;
            for (int c = col; c>=0; c--){
                if(board[row][c] >= lastSaw){
                    canSee+=1;
                    lastSaw = board[row][c];
                }
            }
            if ((canSee!= lookEW.get(row))){
                return false;
            }
        }

        return true;
    }
    /**
     * getSuccessors
     *
     * @returns Collection of Configurations
     */
    @Override
    public Collection<Configuration> getSuccessors() {

        Collection<Configuration> successors = new ArrayList<>();

        if(board[current[0]][current[1]] != EMPTY){
            SkyscraperConfig successor = new SkyscraperConfig(this);
            successor.previous = successor.current;
            row = successor.current[0];
            col = successor.current[1];
            col += 1;
            if(col == DIM){
                row +=1;
                col = 0;
            }
            row = successor.current[0];
            col = successor.current[1];
            successors.add(successor);
        }
        else{
            for(int val = 1;  val < DIM+1;  val++) {
                SkyscraperConfig successor = new SkyscraperConfig(this);
                row = successor.current[0];
                col = successor.current[1];
                successor.board[row][col] = val;
                successor.previous = successor.current;
                col+=1;
                if(col == DIM){
                    row +=1;
                    col = 0;
                }
                row = successor.current[0];
                col = successor.current[1];
                successors.add(successor);
                }
            }
        return successors;
    }

    /**
     * toString() method
     *
     * @return String representing configuration board & grid w/ look values.
     * The format of the output for the problem solving initial config is:
     *
     *   1 2 4 2
     *   --------
     * 1|. . . .|3
     * 2|. . . .|3
     * 3|. . . .|1
     * 3|. . . .|2
     *   --------
     *   4 2 1 2
     */
    @Override
    public String toString() {
            String result = "";
            result += "\n  ";
            for(int i : lookNS){
                result += i + " ";
            }
            result += "\n  ";
            for(int count = 0 ; count < DIM*2-1; count++ ){
                result += "-";
            }
            result += "\n";
            for(int x = 0; x < DIM; x ++){
                result += lookWE.get(x) + "|";
                for (int y = 0; y < DIM; y++){
                    if(board[x][y] == SkyscraperConfig.EMPTY){
                        result += EMPTY_CELL;
                    }
                    else{
                        result += board[x][y];
                    }
                    if(y != DIM-1){
                        result += " ";
                    }
                }
                result += "|" + lookEW.get(x) + "\n";

            }
            result += "  ";
            for(int count = 0 ; count < DIM*2-1; count++ ){
                result += "-";
            }
            result += "\n  ";
            for(int i : lookSN){
                result += i + " ";
            }
            return result;
        }
    }

