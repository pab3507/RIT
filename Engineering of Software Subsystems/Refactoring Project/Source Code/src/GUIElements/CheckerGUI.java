package GUIElements;/*
 * CheckerGUI.java
 * 
 * The actual board.
 *
 * Created on January 25, 2002, 2:34 PM
 * 
 * Version
 * $Id: CheckerGUI.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * 
 * Revisions
 * $Log: CheckerGUI.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 *
 */

import Game.Board;
import Game.Facade;
import Game.Rules;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.net.*;

/**
 * @author
 */

public class CheckerGUI extends JFrame implements ActionListener {

    //the facade for the game
    private static Facade theFacade; //the facade
    private Rules rules;
    private Vector possibleSquares = new Vector();  // a vector of the squares
    private int timeRemaining;  //the time remaining

    private JLabel PlayerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel timeRemainingLabel;
    private JLabel secondsLeftLabel;
    private JButton ResignButton;
    private JButton DrawButton;
    private JLabel warningLabel, whosTurnLabel;

    //the names and time left
    private String playerOnesName = "", playerTwosName = "", timeLeft = "";

    /**
     * Constructor, creates the GUI and all its components
     *
     * @param facade the facade for the GUI to interact with
     * @param name1  the first players name
     * @param name2  the second players name
     */

    public CheckerGUI(Facade facade, String name1, String name2) {

        super("Checkers");

        playerOnesName = makeShortName(name1);
        playerTwosName = makeShortName(name2);
        theFacade = facade;
        rules = new Rules(theFacade.stateOfBoard(), theFacade.getTheDriver());
        register();

        initComponents();
        pack();
        update();
    }


    /*
     * This method to make shorter name if it is longer than 7
     */
    private String makeShortName(String name) {
        if (name.length() > 7) {
            return name.substring(0, 7);
        }
        return name;
    }
    
    /*
     * This method handles setting up the timer
     */

    private void register() {
        try {
            theFacade.addActionListener(this);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form. It initializes the components
     * adds the buttons to the Vecotr of squares and adds
     * an action listener to the components
     */
    private void initComponents() {

        this.setResizable(false);

        //sets the layout and adds listener for closing window
        int y = 0;
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;
        Color oddColor = Color.white;
        Color evenColor = new Color(204, 204, 153);

        for (int i = 0; i < 64; i++) {
            JButton jButton = new JButton();
            possibleSquares.add(jButton);
            jButton.addActionListener(this);
            jButton.setPreferredSize(new Dimension(80, 80));
            jButton.setActionCommand(Integer.toString(i));

            // swap color
            if (i % 8 == 0) {
                Color temp = evenColor;
                evenColor = oddColor;
                oddColor = temp;

                // increasing the grid y of gridBagConstraint by 1
                y += 1;
            }
            if (i % 2 == 0) {
                jButton.setBackground(evenColor);
            } else {
                jButton.setBackground(oddColor);
            }

            gridBagConstraints1 = new java.awt.GridBagConstraints();
            gridBagConstraints1.gridx = i % 8;
            gridBagConstraints1.gridy = y;
            getContentPane().add(jButton, gridBagConstraints1);
        }

        PlayerOneLabel = new JLabel();
        playerTwoLabel = new JLabel();
        whosTurnLabel = new JLabel();

        warningLabel = new JLabel();
        timeRemainingLabel = new JLabel();
        secondsLeftLabel = new JLabel();

        ResignButton = new JButton();
        ResignButton.addActionListener(this);

        DrawButton = new JButton();
        DrawButton.addActionListener(this);

        //add window listener
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent evt) {
                                  exitForm(evt);
                              }
                          }
        );

        PlayerOneLabel.setText("Players 1:     " + playerOnesName);
        PlayerOneLabel.setForeground(Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 4;
        getContentPane().add(PlayerOneLabel, gridBagConstraints1);

        playerTwoLabel.setText("Players 2:     " + playerTwosName);
        playerTwoLabel.setForeground(Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 9;
        gridBagConstraints1.gridwidth = 4;
        getContentPane().add(playerTwoLabel, gridBagConstraints1);

        whosTurnLabel.setText("");
        whosTurnLabel.setForeground(new Color(0, 100, 0));

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 1;
        getContentPane().add(whosTurnLabel, gridBagConstraints1);

        warningLabel.setText("");
        warningLabel.setForeground(Color.red);

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 2;
        getContentPane().add(warningLabel, gridBagConstraints1);

        timeRemainingLabel.setText("Time Remaining:");
        timeRemainingLabel.setForeground(Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 3;
        getContentPane().add(timeRemainingLabel, gridBagConstraints1);

        secondsLeftLabel.setText(timeLeft + " sec.");
        secondsLeftLabel.setForeground(Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 4;
        getContentPane().add(secondsLeftLabel, gridBagConstraints1);

        ResignButton.setActionCommand("resign");
        ResignButton.setText("Resign");

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 7;
        getContentPane().add(ResignButton, gridBagConstraints1);

        DrawButton.setActionCommand("draw");
        DrawButton.setText("Draw");

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 6;
        getContentPane().add(DrawButton, gridBagConstraints1);

    }

    /**
     * Exit the Application
     *
     * @param evt
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
        theFacade.pressQuit();
    }

    /**
     * Takes care of input from users, handles any actions performed
     *
     * @param e the event that has occured
     */

    public void actionPerformed(ActionEvent e) {
        Set<String> canSelectedSpaces = new HashSet<>(Arrays.asList("1", "3", "5", "7", "8", "10", "12", "14", "17",
                "19", "21", "23", "24", "26",
                "28", "30", "33", "35", "37", "39", "40", "42", "44", "46", "49", "51", "53", "55", "56", "58", "60",
                "62"));
        try {
            //if a square gets clicked
            if (canSelectedSpaces.contains(e.getActionCommand())) {

                //call selectSpace with the button pressed
                theFacade.selectSpace(
                        Integer.parseInt(e.getActionCommand()));

                //if draw is pressed
            } else if (e.getActionCommand().equals("draw")) {
                //does sequence of events for a draw
                theFacade.pressDraw();

                //if resign is pressed
            } else if (e.getActionCommand().equals("resign")) {
                //does sequence of events for a resign
                theFacade.pressQuit();

                //if the source came from the facade
            } else if (e.getSource().equals(theFacade)) {

                //if its a player switch event
                if ((e.getActionCommand()).equals(theFacade.PLAYER_SWITCH)) {
                    //set a new time
                    timeRemaining = theFacade.getTimer();
                    //if it is an UPDATE event
                } else if ((e.getActionCommand()).equals(theFacade.UPDATE)) {
                    //UPDATE the GUI
                    update();
                } else {
                    throw new Exception("unknown message from facade");
                }
            }
        } catch (NumberFormatException excep) {
            System.err.println(
                    "GUI exception: Error converting a string to a number");
        } catch (NullPointerException exception) {
            System.err.println("GUI exception: Null pointerException "
                    + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception except) {
            System.err.println("GUI exception: other: "
                    + except.getMessage());
            except.printStackTrace();
        }
    }


    /**
     * Updates the GUI reading the pieces in the board
     * Puts pieces in correct spaces, updates whos turn it is
     */

    private void update() {

        if (rules.checkEndConditions()) {
            theFacade.showEndGame(" ");
        }

        //the board to read information from
        Board board = theFacade.stateOfBoard();

        //go through the board
        for (int i = 1; i < board.sizeOf(); i++) {
            updatePiece(board, i);
        }

        //this code updates whose turn it is
        if (theFacade.whosTurn() == 2) {
            playerTwoLabel.setForeground(Color.red);
            PlayerOneLabel.setForeground(Color.black);
            whosTurnLabel.setText(playerTwosName + "'s turn ");
        } else if (theFacade.whosTurn() == 1) {
            PlayerOneLabel.setForeground(Color.red);
            playerTwoLabel.setForeground(Color.black);
            whosTurnLabel.setText(playerOnesName + "'s turn");
        }
    }

    /*
     * Helper method to UPDATE the image icon for JButton if there is
     * a piece there. Otherwise, show no picture
     */
    private void updatePiece(Board board, int index) {
        // if there is a piece there
        if (board.occupied(index)) {
            //check to see if color is blue
            if (board.colorAt(index) == Color.blue) {
                checkTypePieceToUpdate(board, index, "Blue");

                //check to see if the color is white
            } else if (board.colorAt(index) == Color.white) {
                checkTypePieceToUpdate(board, index, "White");
            }
        } else {
            //show no picture if there isn't a piece there
            JButton temp = (JButton) possibleSquares.get(index);
            temp.setIcon(null);
        }
    }

    /*
     * Helper method to check it is single piece or king piece to
     * UPDATE image icon properly
     */
    private void checkTypePieceToUpdate(Board board, int index, String color) {
        JButton temp = (JButton) possibleSquares.get(index);

        //if there is a single piece there
        if ((board.getPieceAt(index)).getType() == board.SINGLE) {

            //show a white single piece in that spot board
            updateImageIcon(temp, "file:" + color + "Single.gif");

            //if there is a king piece there
        } else if ((board.getPieceAt(index)).getType() == board.KING) {

            //show a white king piece in that spot board
            updateImageIcon(temp, "file:" + color + "King.gif");
        }
    }

    /*
     * Helper method to UPDATE image icon for piece
     */
    private void updateImageIcon(JButton temp, String fileName) {
        //get the picture from the web
        try {
            temp.setIcon(
                    new ImageIcon(new URL(fileName)));
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

}//checkerGUI.java
