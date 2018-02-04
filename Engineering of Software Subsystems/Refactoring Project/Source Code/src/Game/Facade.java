package Game; /**
 * Facade.java
 * <p>
 * Version
 * $Id: Facade.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * <p>
 * Revisions:
 * $Log: Facade.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 */

import Players.Player;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 * An interface between the GUI and the kernel classes in a checkers game.
 *
 * @author
 */

public class Facade extends Component {

    private final int LOCALGAME = 10000;
    private final int HOSTGAME = 20000;
    private final int CLIENTGAME = 30000;

    public final String UPDATE = "UPDATE";
    public final String PLAYER_SWITCH = "switch";

    private Driver theDriver;
    private Board theBoard;
    private Player passivePlayer;
    private Player activePlayer;

    private int startSpace = 99; // Starting space for current move
    private int endSpace = 99; // Ending space for current move

    // The numbers associated with the timer
    private int timer = 999;

    private ActionListener actionListener;

    /**
     * Constructor for the facade.  Initializes the data members.
     *
     * @param newBoard  Board  object Facade will manipulate.
     * @param newDriver Driver object that will communicate
     *                  with the Facade.
     */
    public Facade(Board newBoard, Driver newDriver) {

        theBoard = newBoard;
        theDriver = newDriver;

    }

    public int getLOCALGAME(){
        return this.LOCALGAME;
    }

    public int getCLIENTGAME(){
        return this.CLIENTGAME;
    }

    public int getHOSTGAME(){
        return this.HOSTGAME;
    }

    public Driver getTheDriver() {
        return theDriver;
    }

    /**
     * Return an int indicating which player's turn it is.
     * ( e.g. 1 for player 1 )
     *
     * @return int   The number of the player whose turn it is.
     * @pre game is in progress
     */
    public int whosTurn() {

        // Return the integer value of the activePlayer object
        int turn;
        turn = activePlayer.getNumber();

        return turn;
    }

    /**
     * Set which players turn it is.
     *
     * @param active  The active player
     * @param passive The passive player
     */
    public void setPlayerModes(Player active, Player passive) {

        activePlayer = active;
        passivePlayer = passive;

        // Tell GUI to UPDATE
        generateActionPerformed(UPDATE);
    }

    /**
     * This method should be called to select a space on the board,
     * either as the starting point or the ending point for a move.
     * The Facade will interpret this selection and send a move on to
     * the kernel when two spaces have been selected.
     *
     * @param space an int indicating which space to move to,
     *              according to the standard checkers numbering
     *              scheme, left to right and top to bottom.
     */
    public void selectSpace(int space) {

        // When button is clicked, take info from the GUI
        if (startSpace == 99) {

            // Set startSpace to space
            startSpace = space;

        } else if (endSpace == 99) {
            if (space == startSpace) {

                // Viewed as un-selecting the space selected
                // Set startSpace to predetermined unselected value
                startSpace = 99;

            } else {
                // The endSpace will be set to space
                endSpace = space;
                makeLocalMove();
            }
        }

        generateActionPerformed("UPDATE");

    }

    /**
     * Send a move on to the kernel, i.e. call makeMove() in
     * the LocalPlayer and inform it whose turn it is.
     *
     * @pre startSpace is defined
     * @pre endSpace is defined
     */
    private void makeLocalMove() {

        //make sure startSpace and endSpace are defined
        if (startSpace != 99 && endSpace != 99) {
            // Takes the information of a move and calls makeMove()
            // in a localPlayer
            boolean result = activePlayer.makeMove(startSpace, endSpace);
        }

        // Reset startSpace and endSpace to 99
        startSpace = 99;
        endSpace = 99;

    }

    /**
     * Tell the kernel that the user has quit/resigned the game
     * or quit the program
     */
    public void pressQuit() {

        // Alert players and the kernel that one person
        // has quit calls quitGame() for both players
        theDriver.endInQuit(activePlayer);
    }

    /**
     * Tell the kernel that the user has requested a draw.
     */
    public void pressDraw() {

        // Alerts both players and the kernel that one person
        // has offered a draw calls offerDraw() on both players
        activePlayer.offerDraw(activePlayer);
    }

    /**
     * Tell the kernel that the user has accepted a draw.
     */
    public void pressAcceptDraw() {

        //calls acceptDraw() in teh driver
        theDriver.endInDraw(activePlayer);
    }

    /**
     * Given a player number, returns the name associated
     * with that number.
     *
     * @param playerNum the number of a player
     * @return string    the name associated with playerNum
     * @pre playerNum is a valid player number
     */
    public String getPlayerName(int playerNum) {
        String retString = null;

        try {
            // Checks to see that playerNum is valid
            if (playerNum == 1 || playerNum == 2) {
                // checks both Players objects to see which one is
                // associated with the legal number returns the name of
                // the player asscociated with the number
                if (activePlayer.getNumber() == playerNum) {
                    retString = activePlayer.getName();
                } else {
                    retString = passivePlayer.getName();
                }
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

            // If playerNum is illegal an exception will be thrown
        }

        return retString;
    }

    /**
     * Tell the kernel to associate the given name with the
     * given player number.
     *
     * @param playerNum the number of a player
     * @param name      the name that player should be given
     * @pre playerNum is a valid player number
     */
    public void setPlayerName(int playerNum, String name) {
        theDriver.setPlayerName(playerNum, name);
    }


    /**
     * Tell the kernel to set a time limit for each turn.  The time
     * limit, i.e. the amount of time a player has during his turn
     * before he is given a time warning, is specified by the parameter
     * called time, in minutes.
     * <p>
     * Tell the kernel to set a time limit for each turn.   The warning
     * time, i.e. the amount of time a player has during his turn after
     * he is given a time warning, is specified by the parameter called
     * time, in minutes.
     *
     * @param time the time limit for each turn, in seconds.
     * @pre 10 <= time <= 300.
     */
    public boolean setTimer(int time, int warning) {
        // Checks to see that time is in between the necessary frame
        // Sets time(class variable) to time(param value)
        if ((time == -1) || ((time >= 10 || time <= 300)
                && (warning >= 10 || warning <= 300))) {

            timer = time;
            theDriver.setTimer(time, warning);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Display to local players that the game has ended with
     * the message provided.
     *
     * @param message
     * @post the game ends
     */
    public void showEndGame(String message) {
        //make sure game is over
        //calls endGame in driver object
        theDriver.endGame(message);
    }

    /**
     * Returns the timer value, how long each player get to take a turn
     *
     * @return the amount of time each player has for a turn
     * @pre there has been a timer set for the current game
     */
    public int getTimer() {
        int retval = 0;

        // Makes sure there is a timer for this game
        if (timer != 999) {
            retval = timer;
        }

        // Returns the timer value (clas variable: time )
        return retval;
    }

    /**
     * Adds an action listener to the facade
     */
    public void addActionListener(ActionListener a) {
        actionListener = AWTEventMulticaster.add(actionListener, a);
        //Adds an action listener to the facade
    }

    /**
     * Notifies everything of the state of the board
     *
     * @return a Board object which is the state of the board
     */
    public Board stateOfBoard() {
        // Return the board so GUI can go through and UPDATE itself
        return theBoard;
    }

    /**
     * Call the driver and begin the game.
     */
    public void startGame() {
        theDriver.startGame();
    }

    /**
     * Generates an action. This is inhereted from Componen
     */
    private void generateActionPerformed(String command) {

        if (actionListener != null) {
            actionListener.actionPerformed(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
            // Fires an event associated with timer, or move made on GUI
        }
    }

}// Facade.java