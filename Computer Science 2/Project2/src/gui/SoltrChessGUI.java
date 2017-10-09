/**
 * Part of SoltrChessLayout project.
 * Created 10 2015
 *
 * @author James Heliotis
 */

package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.ChessModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static javafx.geometry.Pos.CENTER;

/**
 * A miniature chess board
 *
 * @author James Heliotis
 */
public class SoltrChessGUI extends Application implements Observer {
    /**
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the UI
     */

    //private StackPane[][] board; // Array of Buttons
    //private int cols; // Columns reference
    //private int rows; // Rows reference
    private ChessModel model;
    private static String filename;
    private Button NewGame = new Button("NewGame");
    private Button Restart = new Button("Restart");
    private Button Hint = new Button("Hint");
    private Button Solve = new Button("Solve");
    private Label textLabel = new Label("Game File: " + filename);
    private GridPane ChessGrid = new GridPane();
    private ArrayList<Button> ButtonsList = new ArrayList<>();
    private BorderPane ChessBP = new BorderPane();
    private HashMap<Integer, Image> ImageHashMap = new HashMap<>();


    @Override
    public void init() throws Exception {
        model = new ChessModel("");
        model.addObserver(this);
        HBox BottomHB = new HBox(150);
        HBox Buttons = new HBox();

        Buttons.setAlignment(CENTER);
        BottomHB.setAlignment(CENTER);
        HBox.setHgrow(NewGame, Priority.ALWAYS);
        HBox.setHgrow(Restart, Priority.ALWAYS);
        HBox.setHgrow(Hint, Priority.ALWAYS);
        HBox.setHgrow(Solve, Priority.ALWAYS);
        Buttons.getChildren().addAll(NewGame, Restart, Hint, Solve);
        BottomHB.getChildren().addAll(Buttons);
        ChessBP.setBottom(BottomHB);
        ChessBP.setCenter(ChessGrid);
        Image bishop = new Image(getClass().getResourceAsStream("../gui/resources/bishop.png"));
        ImageHashMap.put(0, bishop);
        Image king = new Image(getClass().getResourceAsStream("../gui/resources/king.png"));
        ImageHashMap.put(1, king);
        Image knight = new Image(getClass().getResourceAsStream("../gui/resources/knight.png"));
        ImageHashMap.put(2, knight);
        Image pawn = new Image(getClass().getResourceAsStream("../gui/resources/pawn.png"));
        ImageHashMap.put(3, pawn);
        Image queen = new Image(getClass().getResourceAsStream("../gui/resources/queen.png"));
        ImageHashMap.put(4, queen);
        Image rook = new Image(getClass().getResourceAsStream("../gui/resources/rook.png"));
        ImageHashMap.put(5, rook);
        BackgroundImage blue = new BackgroundImage(new Image(getClass().getResource("../gui/resources/dark.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background blueBG = new Background(blue);
        BackgroundImage white = new BackgroundImage(new Image(getClass().getResource("../gui/resources/white.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background whiteBG = new Background(white);
        boolean checker = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button piece = new Button();

                if (checker)
                    piece.setBackground(whiteBG);
                checker = false;
                piece.setBackground(blueBG);
                ButtonsList.add(piece);
                ChessGrid.add(piece, i, j);
            }
        }
        update(this.model, null);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene ChessScene = new Scene(ChessBP);
        primaryStage.setScene(ChessScene);
            /*
            for (Button b: ButtonsList){
                try {
                    b.setOnAction(Event -> model.tryToTake(ButtonsList.indexOf(b)));
                }
                catch (Exception e){
                    System.out.println("I can't seem to fix this error");
                }
            }
            */
        NewGame.setOnAction(Event -> model.newGame(filename));
        Restart.setOnAction(Event -> model.reset());
        Hint.setOnAction(Event -> model.hint());
        //Solve.setOnAction(Event-> model.solve();
        primaryStage.setTitle("Solitaire Chess");
        primaryStage.titleProperty();
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(550);
        primaryStage.setMaxWidth(568);
        primaryStage.setMaxHeight(600);
        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }



    public static void main(String[] args) {
        //filename = args[1];
        Application.launch(args);

    }
}
