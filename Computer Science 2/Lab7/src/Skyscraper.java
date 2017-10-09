import javafx.util.Pair;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Tha main Skyscraper class is run as:
 *  $java Skyscraper [filename] [debug]
 *       [filename]: The name of the board file
 *       [debug]: true or false for debug output
 *
 *  @author Sean Strout
 *  @author TJ Borelli
 */
public class Skyscraper {
    /** 
     * The main program.
     * @param args command line arguments
     * @throws FileNotFoundException if file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
   
        boolean debug = false;  
        File file;
        String filename; 
        Scanner in;

        // if no command line arguments prompt for filename and 
        // set debug to false
        if ( args.length == 0 ) {
            in = new Scanner(System.in);
            System.out.print("Enter board file: ");
            filename = in.next();
            debug = false;
        }

        // if one command line argument specified, it is the filename,
        // set debug to false
        else if ( args.length == 1 ) {
            filename = args[0];
            debug = false;    
        }
    
        // otherwise, use the command line arguments
        else if (args.length == 2 ) {
            filename = args[0];
            debug = args[1].equals("true");
        }

        // incorrect number of command line arguments
        else {
            System.out.println("Usage: java Skyscraper [filename] [debug]");
            System.out.println("  [filename]: The name of the board file");
            System.out.println("  [debug]: True or False for debug output");
            return;
        }
   
        System.out.println("Filename: " + filename);
        System.out.println("Debug on: " + debug + "\n");

        // pass scanner object to constructor to read initial board
        SkyscraperConfig initConfig = new SkyscraperConfig(filename);

        // display initial configuration
        System.out.println("Initial Config:");
        System.out.println(initConfig);

        // create the backtracker with the debug flag
        Backtracker bt = new Backtracker(debug);

        // start the clock
        double start = System.currentTimeMillis();

        // solve the puzzle
        Optional<Configuration> solution = bt.solve(initConfig);

        if (debug) {
            for (Pair<Configuration, String> pair : bt.getConfigLog()) {
                System.out.println(pair.getValue());
                System.out.println("Config: " + pair.getKey());
            }
        }
     
        // display the solution, if one exists
        if (solution.isPresent()) {
            System.out.println("Solution:\n" + solution.get());
        } else {
            System.out.println("No solution");
        }

        // compute the elapsed time
        System.out.println("Elapsed time: " +
                (System.currentTimeMillis() - start)/1000.0 + " seconds.");
    }
} 
