/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class Card {
    /**
     * Constructor for the card class with it's corresponding states.
     * Corresponding getters created below accordingly.
     * Value method returns the value of the rank.
     * Shortname method returns the shortname of each state.
     * toString method returns the toString method of each state.
     */
        private Ranks rank;
        private Suits suit;
        public Card(Ranks rank, Suits suit){
            this.rank = rank;
            this.suit = suit;
        }

    public Ranks getRank() {
        return rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public int value(){
        return rank.getValue();
    }

    public String getShortName(){
        return rank.getShortName() + suit.getShortName() ;
    }

    @Override
    public String toString() {
        return rank.toString() + " OF " + suit.toString() ;
    }
}
