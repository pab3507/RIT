/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Human {

    private Scanner play; //Scanner variable that reads the players stand decision.
    private ArrayList<Card> HumanHand; //New ArrayList using the Card class that stores the human's hand of cards
    public Human(Scanner in){
        this.play = in; //instantiation of the scanner
        this.HumanHand = new ArrayList<Card>(); //instantiation of the array list
    }
    public boolean stand(){
        /**
         * Method reads in the input and returns a boolean depending if the input is the desired.
         */
        System.out.println("Would you like to stand? || Enter 'y' for yes or 'n' for no. ");
        return this.play.nextLine().equalsIgnoreCase("y");
    }

    public void addCard(Card c){
        /**
         * Adds a card c to the human's hand by simply using the built in add method from ArrayList.
         */
        HumanHand.add(c);
    }
    public void printHand(){
        /**
         * This method loops through the cards in the array list and prints out them using the Card class toString method.
         */
        for (Card card: HumanHand){
            System.out.println(card.toString());
        }
    }
    public void newHand(){
        /**
         * Clears the human's hand by simply using the built in clear method from ArrayList.
         */
        HumanHand.clear();
    }
    public int value(){
        /**
         * Calculates the hand's value depending whether it's a flush or pair, and then doing the corresponding operations
         * and returns it values.
         */
        Card card1 = HumanHand.get(0);
        Card card2 = HumanHand.get(1);
        int value = 0;
        if (card1.getRank() == card2.getRank()){
            value += 1000;
        }
        if (card1.getSuit() == card2.getSuit()){
            value += 500;
        }
        if (card1.value() >= card2.value()){
            value += card1.value()*14+card2.value();
        }
        if (card1.value() < card2.value()){
            value += card2.value()*14+card1.value();
        }
        return value;
    }

    public int compareTo(Computer computer){
        /**
         * This method returns an integer value for the operation between the human's hand value and the computer's.
         */
        return this.value() - computer.value();
    }

}
