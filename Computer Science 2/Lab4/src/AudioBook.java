/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class AudioBook extends Book {

    private final int numDiscs; //variable for the number of discs

    public AudioBook(String author, int cost, Medium medium, String title, int numDiscs) {
        /**
         *  Constructor for audiobook that inherits the book class states and adds a state for the number of discs.
         */
        super(author, cost, medium, title);
        this.numDiscs = numDiscs;
    }

    @Override
    public String getMedium(){
        /**
         * @return: Get medium returns the medium format from the book class + the number of discs.
         */

        return super.getMedium() + ": " +getNumDiscs() + " discs.";
    }

    @Override
    public String toString() {
        /**
         * @return: returns the bookclass toString method + text + the number of discs + text.
         */
        return super.toString() + "Audio: "+ numDiscs +
                " discs.";
    }

    public int getNumDiscs() {
        /**
         * @return: Returns the number of discs
         */
        return numDiscs;
    }

    @Override
    public boolean isForSale() {
        /**
         * @return: returns false because AudioBook never go for sale.
         */
        return false;
    }
}
