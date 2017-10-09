/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class Patron implements Prioritizable{
    private String name;
    private int coolness;
    private boolean regular;
    public Patron(String n, int c, boolean r) {
        /**
         * Constructor for the patron class with its corresponding states.
         */
        this.name = n;
        this.coolness = c;
        this.regular = r;
    }

    public String getName() {
        //Getter for Name
        return name;
    }

    public int getCoolness() {
        //Getter for Coolness
        return coolness;
    }

    public boolean isRegular() {
        //Returns boolean if regular.
        return regular;
    }

    @Override
    public String toString() {
        /**
         * Generated toString method
         */
        return regular + name + " with coolness factor " + coolness + "gets in!!!";
    }

    @Override
    public double getPriority() {
        /**
         * Method assigns priority to an object. Regular members get an amount added to their coolness value to give them
         * more priority.
         */
        if (this.isRegular()) {
            return getCoolness() + 0.5;
        }
        else {
            return getCoolness();
        }
    }
}
