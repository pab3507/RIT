import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class Store {

    /** name of the store */
    private String name;

    /** inventory list of book objects */
    private List<Book> inventory;

    /** input processor for customer inquiries */
    private Scanner in;

    /** rental price for non-salable items */
    private double rentalPrice;

    /** search code for title searches */
    private final static char TITLE_SEARCH = 't';

    /** search code for author searches */
    private final static char AUTHOR_SEARCH = 'a';

    /** search code for medium format searches */
    private final static char MEDIA_SEARCH = 'm';

    /** SCS: the search code set supports the user input validation. */
    private final static char[] SCS 
                          = { TITLE_SEARCH , AUTHOR_SEARCH , MEDIA_SEARCH } ; 

    /** CODES is used to validate a user's code input. */
    private final static String CODES = new String( SCS );


    /**
     * Store default constructor initializes all its instance fields.
     */
    public Store() {
        this.name = "Barney's Books 'N' Bytes";
        this.inventory = new ArrayList<Book>();
        this.in = new Scanner( System.in );
        this.rentalPrice = 3.95;              // an internal initialization 
    }

    /**
     * @param aBook the book to add to the store inventory
     */
    public void addBook( Book aBook ) {
        inventory.add( aBook );
    }

    /**
     * fillInventory loads the inventory from a named, formatted file.
     *
     * @param filename source of the inventory content descriptions.
     * @throws FileNotFoundException if filename is invalid.
     */
    public void fillInventory( String filename ) throws FileNotFoundException {

        File file = new File( filename );
        Scanner in = new Scanner( file );    // a local, one-shot scanner.

        // parse each line in the input file
        while ( in.hasNextLine() ) {

            String line = in.nextLine();

            Book book = produceBook( line );
            if ( book != null ) {
                this.addBook( book );
            }
        }
    }

    /**
     * produceBook consumes a line of text and produces a Book instance.
     * produceBook expects the text to have specific tilde-delimited fields.
     *
     * @param line of text containing a single inventory item description
     * @return a valid Book instance or null if code detected errors in text
     * <p>
     * Expected File Format: An underscore (_) is the
     * field delimiter for the text.
     * </p>
     * <p>
     * TITLE_AUTHOR_COST[_medium format information]
     * </p>
     * <p>
     * The TITLE and AUTHOR are required, standard strings.
     * </p>
     * <p>
     * The COST is in whole US cents. For example, 4993 means a cost of $49.93.
     * A zero COST indicates that it cost the store nothing; it is not a price.
     * </p>
     * <p>
     * The last field, medium format information is optional and may be absent.
     * The medium format information depends on the particular kind of book.
     * </p>
     * <pre>
     * (empty)     means a paperback book medium.
     * cloth       means a hardcover book medium.
     * leather     means a hardcover book medium.
     * ...://...   means an electronic book medium (from the URI format).
     * (integer)   means an audio-book medium; the value is the number of discs.
     * </pre>
     * <p>
     * Example:
     * </p>
     * <pre>
     * Ulysses_James Joyce_895
     * Ulysses_James Joyce_3195_leather
     * Ulysses_James Joyce_2395_cloth
     * Ulysses_James Joyce_0_http://www.amazon.com
     * Sometimes a Great Notion_Ken Kesey_1295_3
     * </pre>
     */
    public Book produceBook(String line ) {
        /**
         * @param String line: This variable helps produce the book line.
         * @return: The new created booktype is returned in each if statement except on the check for the empty string
         * where it returns a null.
         */

        // Check for the empty string
        if (line.isEmpty()){
            return null;
        }
        /* Array of strings split by underscore character. */
        String [] bookline = line.split("_");

        if (bookline.length<4) //Checks if array is empty at position 4 and returns paperbook.
        {
            return new PaperbackBook(bookline[1], Integer.parseInt(bookline[2]), Medium.Paperback, bookline[0]);
        }
        else if (bookline[3].equals("cloth") || bookline[3].equals("leather")){ //checks position 3 for keyword
            return new HardcoverBook(bookline[1], Integer.parseInt(bookline[2]), Medium.Hardcover, bookline[0], bookline[3]);
        }
        else if (bookline[3].contains("http://")) { //checks position 3 for url header
            return new ElectronicBook(bookline[1], Integer.parseInt(bookline[2]), Medium.Electronic, bookline[0], bookline[3]);
        }
        else{ //else case for audiobook.
            return new AudioBook(bookline[1], Integer.parseInt(bookline[2]), Medium.Audio, bookline[0], Integer.parseInt(bookline[3]));
        }

    }

    /**
     * getMarkup returns the cost multiplier for price production.
     * @return the cost multiplier for price production.
     */
    public double getMarkup() {
        return 2.0;                  // this is a constant.
    }

    /**
     * print the entire store inventory to standard output.
     */
    public void printInventory() {
        System.out.println( "\nThe inventory of " + this.name + ": " );
        int item = 1;
        for ( Book book : this.inventory ) {
            System.out.println( item + ". " + book );
            item += 1;
        }
        System.out.println();
    }

    /**
     * list the inventory's titles for a partial text string.
     * @param bk the book under consideration.
     * @param code the code identifying the attribute to search
     * @return text of field to search
     */
    private static String selectField( Book bk, char code ) {
        String result = null;
        // init to a default
        if ( code == TITLE_SEARCH ) {
            result = bk.getTitle().toLowerCase();
        } else if ( code == AUTHOR_SEARCH ) {
            result = bk.getAuthor().toLowerCase();
        } else if ( code == MEDIA_SEARCH ) {
            result = bk.getMedium().toLowerCase();
        }
        return result;
    }

    /**
     * list the inventory's titles for a partial text string.
     * @param partText the text to find in book titles.
     * @param code indicates which field of book in which to search.
     * @return list of Book instances that matched the query text.
     */
    private ArrayList<Book> listMatching( String partText, char code ) {

        ArrayList<Book> foundItems = new ArrayList<Book>();
        System.out.println();
        for ( Book book : this.inventory ) {
            String field = selectField( book, code );
            if ( field != null ) {
                if ( field.contains( partText ) ) {
                    foundItems.add( book );
                }
            }
        }
        return foundItems;
    }

    /**
     * offerPurchase offers the user a choice from the list of books found.
     * If the list is empty, the method prints the message
     * <BR>Sorry. No matches were found.
     * <BR>and returns.
     * @param bookList the list of books found by a search
     */
    private void offerPurchase( ArrayList<Book> bookList ) {

        if ( bookList.size() == 0 ) {
            System.out.println( "Sorry. No matches were found." );
        } else {
            for ( int j = 1; j <= bookList.size(); ++j ) {
                Book book = bookList.get( j - 1 );
                System.out.print( j + ". " + book.getTitle()
                                  + "\n  " + book.getMedium() );
                // print the marked-up sale price in $ dd.cc format
                System.out.printf( " $ %3.2f\n"
                                 , (book.getCost() * this.getMarkup()) );
            }
            System.out.println();
            System.out.print( "Which would you like to buy(ENTER to quit): " );
            String content = this.in.nextLine().trim().toLowerCase() ;
            if ( ! content.isEmpty() ) {
                Scanner sc = new Scanner( content );  // to scan for a number
                if ( ! sc.hasNextInt() ) {
                    System.out.println(
                               "\nSorry. You needed to enter an integer." );
                    return;
                }
                int j = new Integer( content );
                if ( j < 1 || j > bookList.size() ) {
                    System.out.println(
                               "\nSorry. Your choice was out of range." );
                } else {
                    Book book = bookList.get( j - 1 );
                    String title = book.getTitle();
                    if ( book.isForSale() ) {
                        double price = book.getCost() * this.getMarkup();
                        System.out.printf( "The price of %s is $ %3.2f\n"
                                         , title, price );
                    } else {
                        System.out.println( "\nSorry. " + title 
                                   + "\n\t" + book.getMedium()
                                   + " is not for sale."
                                   + "\n\tThe rental price is $ "
                                   + this.rentalPrice + " per week." );
                    }
                    System.out.println();
                }
            }
        }
        return;
    }

    /**
     * offerSearch offers the user a way to search by title, author, or medium.
     * offerSearch enters an open-ended user interaction loop to let
     * a person enter search queries and possibly ask to buy a book.
     * <p>
     * Based on the search code entered ('t', 'a', or 'm'), the search
     * scans the inventory for values matching an entered phrase.
     * </p>
     * <p>
     * The matching process is case-insensitive.
     * The user may enter a blank line or a 'q' to quit.
     * </p>
     * <p>
     * If there are any matches, the user has the chance to choose
     * which one to buy.
     * </p>
     */
    public void offerSearch() {

        do {
            System.out.println();
            System.out.print( "Enter one of these one-letter codes: " +
                              TITLE_SEARCH + "(title), " +
                              AUTHOR_SEARCH + "(author), " +
                              MEDIA_SEARCH + "(medium),\n" +
                              "and a portion of the desired text. " +
                              "Or enter a blank line to quit.\n" +
	                      "(Note: medium is paper, hard, audio, " +
                              "or electronic).\n" +
                              "How would you like to search? " );

            String content = this.in.nextLine().trim().toLowerCase() ;
            if ( content.isEmpty() 
              || content.charAt( 0 ) == 'q' ) {
                System.out.println( "\nSearch again any time!\n" );
                break;
            } else {
                char code = content.charAt( 0 ) ;
                if ( Store.CODES.indexOf( code ) != -1 ) {

                    ArrayList<Book> booksFound =
                          listMatching( content.substring( 1 ).trim(), code );

                    offerPurchase( booksFound );

                } else {
                    System.out.println( "Sorry. but " + code 
                                      + " is not a valid search code." );
                }
            }
        } while ( true );
    }

    /**
     * main method
     * @param args must contain the name of the file that holds the inventory.
     * @throws FileNotFoundException if the file is not found
     */
    public static void main( String[] args ) throws FileNotFoundException {

        if ( args.length != 1 ) {
            System.out.println( "Usage: java Store filename" );

        } else {
            Store store = new Store();

            // read the inventory, which may throw a FileNotFoundException.
            store.fillInventory( args[0] );

            // print the inventory.
            store.printInventory();

            // let customer search for books matching a search phrase.
            // If they find matching books, then offer them for purchase.
            store.offerSearch();

        }
    }

}
