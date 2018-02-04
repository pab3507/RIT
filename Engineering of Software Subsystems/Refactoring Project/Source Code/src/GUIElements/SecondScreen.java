package GUIElements;/*
 * SecondScreen.java
 *
 * Version:
 *   $Id: SecondScreen.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: SecondScreen.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

import Game.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


/**
 * This is the second screen of options. It handles the players names and
 * options for the timer.
 *
 * @author
 */
public class SecondScreen extends JFrame
        implements ActionListener, ChangeListener {

    private Facade theFacade;
    private FirstScreen theFirst;
    private int gameType;

    // Variables declaration
    private Checkbox timedGameBox = new Checkbox("Timed game");
    private JLabel playerOneLabel = new JLabel("Players 1:");
    private JLabel playerTwoLabel = new JLabel("Players 2:");
    private JTextField playerOneField = new JTextField("Enter name");
    private JTextField playerTwoField = new JTextField("Enter name");
    private JLabel turnLengthLabel = new JLabel();
    private JLabel WarningLengthLabel = new JLabel();
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    private JSlider turnLengthField = new JSlider(10, 300, 120);
    private JSlider warningLengthField = new JSlider(10, 300, 120);
    // End of variables declaration

    private final Color background = new Color(204,204,204);
    private final Color background2 = new Color(212,208,200);
    /**
     * Creates new SecondScreen
     *
     * @param f     the facade getting passed to to set options
     * @param first the FirstScreen object that ceated this window
     * @param type  the type of game
     */

    public SecondScreen(Facade f, FirstScreen first, int type) {

        super("Second Screen");
        theFacade = f;
        theFirst = first;
        gameType = type;

        initComponents();
        pack();
    }

    /**
     * Sets the color and name of a component
     * @param component component to be changed
     * @param bg background
     * @param name name
     * @param fg foreground
     */
    private void setColorAndName(Component component, Color bg, String name, Color fg){
        component.setBackground(bg);
        component.setName(name);
        component.setForeground(fg);
    }

    /**
     * Creates gridbag and insets
     * @param x x coordinate
     * @param y y coordinate
     * @param top top inset
     * @param left left inset
     * @param bottom bottom inset
     * @param right right inset
     * @param anchor anchor color
     * @return
     */
    private GridBagConstraints createGridBagConstraints(int x, int y, int top, int left, int bottom,
                                                        int right, int anchor){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(top,left,bottom,right);
        gbc.anchor = anchor;
        return gbc;
    }

    /**
     * Adds listeners
     */
    private void addListeners(){
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        turnLengthField.addChangeListener(this);
        warningLengthField.addChangeListener(this);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */

    private void initComponents() {


        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent evt) {
                                  System.exit(0);
                              }
                          }
        );

        setColorAndName(timedGameBox,background,"timedGameBox",Color.black);
        timedGameBox.setState(true);

        gridBagConstraints1 = createGridBagConstraints(0,5,31,0,1,0,GridBagConstraints.WEST);
        gridBagConstraints1.ipadx = 7;
        gridBagConstraints1.ipady = 7;
        getContentPane().add(timedGameBox, gridBagConstraints1);

        setColorAndName(playerOneLabel,background,"playerOneLabel",Color.black);

        gridBagConstraints1 = createGridBagConstraints(0,1,5,0,0,0,GridBagConstraints.WEST);
        getContentPane().add(playerOneLabel, gridBagConstraints1);

        setColorAndName(playerTwoLabel,background,"playerTwoLabel",Color.black);

        gridBagConstraints1 = createGridBagConstraints(0,2,4,0,0,0,GridBagConstraints.WEST);
        getContentPane().add(playerTwoLabel, gridBagConstraints1);

        setColorAndName(playerOneField,Color.white,"textfield1",Color.black);

        gridBagConstraints1 = createGridBagConstraints(1,1,5,0,0,0,GridBagConstraints.WEST);
        getContentPane().add(playerOneField, gridBagConstraints1);

        setColorAndName(playerTwoField,Color.white,"textfield2",Color.black);

        gridBagConstraints1 = createGridBagConstraints(1,2,4,0,0,0,GridBagConstraints.WEST);
        getContentPane().add(playerTwoField, gridBagConstraints1);

        setColorAndName(turnLengthLabel,background,"label3",Color.black);
        turnLengthLabel.setText("Turn Length ( " + turnLengthField.getValue() + " seconds )");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(turnLengthLabel, gridBagConstraints1);

        setColorAndName(WarningLengthLabel,background,"label4",Color.black);
        WarningLengthLabel.setText("Warning Length ( " + warningLengthField.getValue() + " seconds )");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 8;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        getContentPane().add(WarningLengthLabel, gridBagConstraints1);

        okButton.setActionCommand("ok");
        setColorAndName(okButton,background2,"button1",Color.black);

        gridBagConstraints1 = createGridBagConstraints(0,11,20,0,10,12,GridBagConstraints.EAST);
        getContentPane().add(okButton, gridBagConstraints1);

        setColorAndName(cancelButton,background,"button2",Color.black);
        cancelButton.setActionCommand("cancel");

        gridBagConstraints1 = createGridBagConstraints(1,11,20,0,10,0,GridBagConstraints.WEST);
        getContentPane().add(cancelButton, gridBagConstraints1);

        turnLengthField.setName("textfield3");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 6;
        getContentPane().add(turnLengthField, gridBagConstraints1);

        warningLengthField.setName("textfield4");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 8;
        getContentPane().add(warningLengthField, gridBagConstraints1);

        addListeners();

        //determine what components should be disabled
        //depending on the game mode
        if (gameType == theFacade.getLOCALGAME()) {
        } else if (gameType == theFacade.getHOSTGAME()) {
            playerTwoLabel.setEnabled(false);
            playerTwoField.setEnabled(false);
        } else if (gameType == theFacade.getCLIENTGAME()) {
            playerOneLabel.setEnabled(false);
            playerOneField.setEnabled(false);

            timedGameBox.setEnabled(false);
            turnLengthLabel.setEnabled(false);
            WarningLengthLabel.setEnabled(false);
            turnLengthField.setEnabled(false);
            warningLengthField.setEnabled(false);
        }
    }

    /*
     * This changes the text on the labels
     *
     * @param e the change event
     *
     */
    public void stateChanged(ChangeEvent e) {

        if (e.getSource().equals(turnLengthField)) {
            turnLengthLabel.setText("Turn Length ( "
                    + turnLengthField.getValue()
                    + " seconds )");
        } else if (e.getSource().equals(warningLengthField)) {
            WarningLengthLabel.setText("Warning Length ( "
                    + warningLengthField.getValue()
                    + " seconds )");
        }
    }

    /**
     * Command that occurs when OK button is pressed
     * Sets player names and checks for valid timer input
     * Replace exception with test and decompose conditional
     */
    private void okCommand(){

        //take note of all selections and go to game startup
        if (playerOneField.isEnabled() && (playerOneField.getText()).equalsIgnoreCase("")) {
            playerOneField.setText("player1");
        }

        if (playerTwoField.isEnabled() && playerTwoField.getText().equalsIgnoreCase("")) {
            playerTwoField.setText("player2");
        }
        theFacade.setPlayerName(1, playerOneField.getText());
        theFacade.setPlayerName(2, playerTwoField.getText());

        //if a timer is desired
        if (timedGameBox.isEnabled() && timedGameBox.getState()) {
            boolean successful = theFacade.setTimer(turnLengthField.getValue(),
                        warningLengthField.getValue());
            if(!successful) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Timer value(s)",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            theFacade.setTimer(-1, -1);
        }
        //start the game
        theFacade.startGame();
        //hide this screen, make and show the GUI
        this.hide();
        CheckerGUI GUI = new CheckerGUI(theFacade, theFacade.getPlayerName(1),
                theFacade.getPlayerName(2));
        GUI.show();

    }
    /**
     * This takes care of when an action takes place. It will check the
     * action command of all components and then deicde what needs to be done.
     *
     * @param e the event fired
     */

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("ok")) {
            okCommand();

            //if they hit cancel go to the previous screen
        } else if (e.getActionCommand().equals("cancel")) {
            this.hide();
            theFirst.show();

            //handle whether or not a timer is desired
        } else if (e.getSource() instanceof Checkbox) {

            if (timedGameBox.getState()) {
                turnLengthField.setEnabled(true);
                warningLengthField.setEnabled(true);
            } else {
                turnLengthField.setEnabled(false);
                warningLengthField.setEnabled(false);
            }
        }
    }//end of actionPerformed

}//SecondScreen
