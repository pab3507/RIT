package ptui;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Part of SoltrChess project.
 * Created 11 2015
 *
 * @author James Heliotis
 */
public class SoltrChessPTUI implements Observer {

    private ChessModel model;

    public SoltrChessPTUI(String fileName) {
        this.model = new ChessModel(fileName);
        initializeView();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("[move,new,restart,hint,solve,quit]> ");
            String userInput = input.nextLine(); //if input is incorrect, re ask again
            model.evaluate(userInput);
        }
    }

    public void initializeView() {
        this.model.addObserver(this);
        update(this.model, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        model.printBoard();
        if (model.isGoal()) {
            System.out.println("You won, Congrats!!");
            System.exit(0);
        }
    }
}
