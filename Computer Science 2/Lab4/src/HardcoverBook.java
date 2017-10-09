/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class HardcoverBook extends Book{

    private final String coverMaterial; //variable for the cover material

    public HardcoverBook(String author, int cost, Medium medium, String title, String coverMaterial) {
        /**
         * Constructor for HardCover Book that inherits the book class states and adds a state for cover material.
         */
        super(author, cost, medium, title);
        this.coverMaterial = coverMaterial;
    }

    public String getMedium() {
        /**
         * @return: getMedium returns the medium format from the book class + the cover material.
         */
        return super.getMedium() + getCoverMaterial() + ".";
    }

    @Override
    public String toString() {
        /**
         * @return: returns the bookclass toString method + the cover material + a period.
         */
        return super.toString() + coverMaterial + ".";
    }

    public String getCoverMaterial() {
        /**
         * @return: returns the coverMaterial variable.
         */
        return coverMaterial;
    }

    @Override
    public boolean isForSale() {
        /**
         * @return: returns true because Hardcover Books do go for sale.
         */
        return true;
    }
}
