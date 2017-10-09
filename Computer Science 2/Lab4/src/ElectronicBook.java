/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class ElectronicBook extends Book{

    private final String theURL; //variable for theURL

    public ElectronicBook(String author, int cost, Medium medium, String title, String theURL) {
        /**
         * Constructor for Electronic Book that inherits the book class states and adds a state for theURL.
         */
        super(author, cost, medium, title);
        this.theURL = theURL; //instantiation of URL
    }

    public String getTheURL() {
        /**
         * @return: theURL is returned.
         */
        return theURL;
    }

    @Override
    public String getMedium() {
        /**
         * @return: getMedium returns the medium format from the book class + the URL.
         */
        return super.getMedium() + " " + getTheURL();
    }
    @Override
    public String toString() {
        /**
         * @return: returns the bookclass toString method + text + theURL
         */
        return super.toString() + "Electronic from "+ theURL;
    }

    @Override
    public boolean isForSale() {
        /**
         * @return: returns false because ElectronicBooks never go for sale.
         */
        return false;
    }
}
