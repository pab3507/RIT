package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Card;
import model.ConcentrationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static javafx.geometry.Pos.*;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */


public class ConcentrationGUI extends Application implements Observer {

    /**
     * All elements of the GUI required to be global are created here, in
     * addition ArrayLists for buttons are created.
     */
    private ArrayList<Button> ButtonsList = new ArrayList<>();
    private ArrayList<Button> CheatButtons = new ArrayList<>();
    private ConcentrationModel model;
    private GridPane grid = new GridPane();
    private GridPane cheatGrid = new GridPane();
    private Button Reset = new Button("Reset");
    private Button Undo = new Button("Undo");
    private Button Cheat = new Button("Cheat");
    private Label Count = new Label("0 Moves");
    private BorderPane ConcentrationBP = new BorderPane();
    private HashMap<Integer, Image> imageHashMap = new HashMap<>();
    private Label TopLabel = new Label();

    /**
     * All events are created here and stages are started
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene ConcentrationScene = new Scene(ConcentrationBP);
        primaryStage.setScene(ConcentrationScene);

        for (Button b: ButtonsList){
            try {
                b.setOnAction(Event -> model.selectCard(ButtonsList.indexOf(b)));
            }
            catch (Exception e){
                System.out.println("Error");
            }
        }
        Reset.setOnAction(Event-> model.reset());
        Undo.setOnAction(Event-> model.undo());
        Cheat.setOnAction(Event-> model.cheat());
        Cheat.setOnAction(Event -> {

                model.cheat();
                ArrayList<Card> cheatCards = model.getCheat();
                for(int row =0; row<4; row++) {
                    for (int column = 0; column < 4; column++) {
                        Button cheatdb = new Button();

                        CheatButtons.add(cheatdb);

                        cheatGrid.add(cheatdb, row, column);
                    }
                }
                for (Card cheat : cheatCards) {
                    Button cButton = CheatButtons.get(cheatCards.indexOf(cheat));
                    ImageView cheatImg = new ImageView(imageHashMap.get(cheat.getNumber()));
                    cButton.setGraphic(cheatImg);
                }
                Stage cheatStage = new Stage();
                BorderPane cheatPane = new BorderPane();
                cheatPane.setCenter(cheatGrid);
                Scene cheatScene = new Scene(cheatPane);
                cheatStage.setTitle("Cheater");
                cheatStage.setScene(cheatScene);
                cheatStage.show();
            }

        );
        primaryStage.setTitle("Concentration");
        primaryStage.titleProperty();
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(550);
        primaryStage.setMaxWidth(568);
        primaryStage.setMaxHeight(600);
        primaryStage.show();

    }

    /**
     * GUI elements initialized
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        System.out.println("init: Initialize and connect to model!");
        model = new ConcentrationModel();
        model.addObserver(this);

        ConcentrationBP.setTop(TopLabel);
        HBox BottomHB = new HBox(150);
        HBox Buttons = new HBox();

        Buttons.setAlignment(CENTER_RIGHT);
        Count.setAlignment(CENTER_RIGHT);
        BottomHB.setAlignment(CENTER_RIGHT);

        HBox.setHgrow(Count, Priority.ALWAYS);
        HBox.setHgrow(Reset, Priority.ALWAYS);
        HBox.setHgrow(Undo, Priority.ALWAYS);
        HBox.setHgrow(Cheat, Priority.ALWAYS);
        Buttons.getChildren().addAll(Reset,Undo,Cheat);
        BottomHB.getChildren().addAll(Buttons,Count);
        ConcentrationBP.setBottom(BottomHB);
        ConcentrationBP.setCenter(grid);
        int count = 0;
        for(int r =-1; r<=7; r++){
            Image dbimg = new Image(getClass().getResourceAsStream("resources/db"+ String.valueOf(r) + ".png"));
            imageHashMap.put(r,dbimg);
        }
        for(int i =0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                Button db = new Button();
                ButtonsList.add(db);
                //CheatButtons.add(db);
                grid.add(db, i, j);
                //cheatGrid.add(db, i, j);
            }
        }
        update(this.model, null);
    }

    /**
     * model is updated
     */
    @Override
    public void update(Observable o, Object arg) {
        int count=0;
        for (Button b: ButtonsList){
            ImageView img = new ImageView(imageHashMap.get(model.getCards().get(count).getNumber()));
            img.setSmooth(true);
            img.setCache(true);
            b.setGraphic(img);
            count++;
        }

        //if(arg !=null && arg.equals("cheat")){
        Count.setText(String.valueOf(model.getMoveCount())+" Moves");
        int check = model.howManyCardsUp();
        switch (check) {
            case 0:
                TopLabel.setText("Select the first card.");
                break;
            case 1:
                TopLabel.setText("Select the second card.");
                break;
            case 2:
                TopLabel.setText("No Match: Undo or select a card.");
                break;
        }
        boolean up = false;
        ArrayList<Card> faces = model.getCards();
        int c = 0;
        boolean win = false;
        for (Card f : faces){
            if(f.isFaceUp()){
                up = true;
                c++;
            }
            else{
                up = false;
            }
        }
        if(c == 8){
            win = true;
        }
        if(win){
            TopLabel.setText("YOU WIN!");
        }

    }



    public static void main(String[] args) {
        Application.launch(args);

    }
}
