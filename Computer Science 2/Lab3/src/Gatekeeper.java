import java.util.Scanner;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */

public class Gatekeeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //New Scanner variable
        HeapQueue<Patron> line = new HeapQueue<>(); // New HeapQueue Variable
        int option; // variable that receives input
        String name; // variable stores in name
        int coolness; // variable that takes in coolness
        Boolean regularity; // variable stores a boolean for regularity
        /** Do While loop asks for the options and stores user's input into variables. Option 1 takes in those values and
         *stores in variables which then go into a new Patron object. Then this object gets inserted into the line
         * (HeapQueue).Option 2 checks for an empty queue and prints accordingly. It then dequeues someone and prints
         * the corresponding person in line with it's corresponding regular status. Option 3 exits the program.
         *
         *
        **/
        do {
            System.out.print("\nSelect one of the options below: \n" + "1: Add a patron to the queue\n" + "2: Admit the " +
                    "highest priority patron\n" + "3: Close for the night (Quit)\n");
            System.out.print("\nYour Choice: ");
            option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                System.out.print("\nWhat's the name?: ");
                name = input.nextLine();
                System.out.print("What's the coolness factor? (1-10): ");
                coolness = input.nextInt();
                System.out.print("Regular? (y/n): ");
                regularity = input.next().equalsIgnoreCase("y");
                Patron newPatron = new Patron(name, coolness, regularity);
                line.insert(newPatron);
            } else if (option == 2) {
                if(line.isEmpty()){
                    System.out.println("\nThe queue is empty.");
                }
                else{
                    Patron nPatron = line.dequeue();
                    if(nPatron.isRegular()){
                        System.out.println("\nRegular " + nPatron.getName()+ " with coolness factor " + nPatron.getCoolness() +
                                " gets in!!!");
                    }
                    else{
                        System.out.println("\n" + nPatron.getName()+ " with coolness factor " + nPatron.getCoolness() +
                                " gets in!!!");
                    }
                }

            }
        }
            while(option!=3);
        {
                System.exit(0);
            }


        }
}
