package UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

public class GUI extends Application {
    private Button createTab = new Button();
    private TabPane tabPane = new TabPane();
    private TextField input = new TextField();
    private HBox inputFields = new HBox();
    private HashMap<String, TextArea> displays = new HashMap<>();
    private String currentID;
    private boolean partialRequest;

    public static void main(String[] args) {
        Application.launch(args);
    }
    /**
     * The start method sets up the scene with is corresponding border pane, text fields, and the Create New Connection
     * button, which creates a Tab pane containing new connections.
     * @param primaryStage the stage to be initiated
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AFRS");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);
        BorderPane borderPane = new BorderPane();
        createTab.setOnAction(event -> createConnection());
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        createTab.setText("Create New Connection");
        borderPane.setTop(createTab);
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        input.setMinWidth(750);
        Button ok = new Button();
        ok.setText(" Submit ");
        inputFields.getChildren().addAll(input, ok);
        input.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                onTextInput();
            }
        });
        ok.setOnAction(event -> onTextInput());
        inputFields.setVisible(false);
        borderPane.setBottom(inputFields);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * Creates a new tab containing a new connection, and a text area to be populated with the server responses.
     */
    private void createConnection() {
        String connectionID = InputProcessor.handleInput("connect;")[0].split(",")[1];
        Tab tab = new Tab();
        tab.setText("Connection " + connectionID);
        tab.setId(connectionID);
        inputFields.setVisible(true);
        TextArea display = new TextArea();
        displays.put(connectionID, display);
        tab.setContent(display);
        tab.setOnSelectionChanged(event -> onTabSelectionChanged(tab));
        tab.setOnClosed(event -> onTabClosed(tab));
        tabPane.getTabs().add(tab);
    }

    /**
     * Takes in a tab and disconnects it.
     */
    private void onTabClosed(Tab tab) {
        InputProcessor.handleInput(tab.getId() + ",disconnect");
    }

    /**
     * Updates the current tab id depending on tab selection.
     */
    private void onTabSelectionChanged(Tab tab) {
        if(tab.isSelected()) {
            currentID = tab.getId();
        }
    }

    /**
     * Updates the connection with the corresponding server responses.
     */
    private String onTextInput() {
        String text = this.input.getText();
        this.input.setText("");
        String prefix = partialRequest ? "" : currentID + ",";
        String[] response = InputProcessor.handleInput(prefix + text);
        partialRequest = response[0].equals("partial-request");
        displays.get(currentID).setText(
                displays.get(currentID).getText() + String.join("\n", response) + "\n"
        );
        return text;
    }
}
