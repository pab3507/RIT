/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
import java.util.ArrayList;

public class Computer {

    private ArrayList<Card> ComputerHand; //New ArrayList using the Card class that stores the human's hand of cards

    public Computer(){
        this.ComputerHand = new ArrayList<Card>(); //instantiation of the array list
    }

    public boolean stand(){
        /**
         * This method returns a boolean depending whether the hand is a pair or flush, if not the boolean will depend
         * whether the hand's value is bigger than 179.
         */
        Card card1 = ComputerHand.get(0);
        Card card2 = ComputerHand.get(1);
        if ((card1.getRank() == card2.getRank()) || (card1.getSuit() == card2.getSuit())) {
            return true;
        }
        else{
                return this.value() > 179;
     }
    }

    public void addCard(Card c){
        /**
         * Adds a card c to the computer's hand by simply using the built in add method from ArrayList.
         */
        ComputerHand.add(c);
    }

    public void printHand(){
        /**
         * This method loops through the cards in the array list and prints out them using the Card class toString method.
         */
        for (Card card: ComputerHand){
            System.out.println(card.toString());
        }
    }

    public void newHand(){
        /**
         * Clears the computer's hand by simply using the built in clear method from ArrayList.
         */
        ComputerHand.clear();
    }

    public int value(){
        /**
         * Calculates the hand's value depending whether it's a flush or pair, and then doing the corresponding operations
         * and returns it values.
         */
        Card card1 = ComputerHand.get(0);

        Card card2 = ComputerHand.get(1);
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
        if (card2.value() > card1.value()){
            value += card2.value()*14+card1.value();
        }
        return value;
    }
}
