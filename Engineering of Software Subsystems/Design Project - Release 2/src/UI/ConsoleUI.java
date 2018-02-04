package UI;

import java.util.Scanner;

public class ConsoleUI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            for(String s : InputProcessor.handleInput(in.nextLine())) {
                System.out.println(s);
            }
        }
    }
}
