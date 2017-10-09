/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class PaperbackBook extends Book {

    public PaperbackBook(String author, int cost, Medium medium, String title) {
        /**
         * Constructor for Paperback Book that inherits the book class states.
         */
        super(author, cost, medium, title);
    }

    @Override
    public String toString() {
        /**
         * @return: returns the bookclass toString method + the medium type + a period
         */
        return super.toString() + getMedium() + ".";
    }

    @Override
    public boolean isForSale() {
        /**
         * @return: returns true because Paperback books do go for sale.
         */
        return true;
    }
}
