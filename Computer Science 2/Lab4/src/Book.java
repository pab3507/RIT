/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public abstract class Book {

    private final String author;
    private final int cost;
    private final Medium medium;
    private final String title;

    public Book(String author, int cost, Medium medium, String title) {
        this.author = author;
        this.cost = cost;
        this.medium = medium;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public double getCost() {
        return (double)cost /100;
    }

    public String getMedium() {
        return medium.toString();
    }

    public String getTitle() {
        return title;
    }

    public abstract boolean isForSale();

    @Override
    public String toString() {
        return "\""+ title + "\"." + "\n\t\t" + author + ".\n\t\t";
    }
}
