package GUIElements;/*
 * FirstScreen.java
 *
 *  * Version:
 *   $Id: FirstScreen.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: FirstScreen.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */

import Game.Facade;
import GameTypeState.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author
 */

public class FirstScreen extends JFrame implements ActionListener {

    private Facade theFacade;
    private GameStateContext gameStateContext;

    // Variables declaration - do not modify
    private JRadioButton LocalGameButton = new JRadioButton("Local game");
    private JRadioButton HostGameButton = new JRadioButton("Host game");
    private JRadioButton JoinGameButton = new JRadioButton("Join game");
    private JTextField IPField = new JTextField("IP address goes here");
    private JLabel IPLabel = new JLabel("IP address:");
    private JButton OKButton = new JButton("OK");
    private JButton CancelButton = new JButton("Cancel");
    private JLabel IPExampleLabel = new JLabel("Ex: 123.456.789.123");
    private ButtonGroup gameModes = new ButtonGroup();
    // End of variables declaration

    private final Color background = new Color(204,204,204);
    private final Color background2 = new Color(212,208,200);

    /**
     * Creates new form FirstScreen
     *
     * @param facade a facade object for the GUI to interact with
     */

    public FirstScreen(Facade facade) {

        super("First screen");
        theFacade = facade;
        gameStateContext = new GameStateContext(this);

        initComponents();
        pack();
    }

    /**
     * Generates the grid bag constraints
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a GBC with set x and y coordinates
     */
    private GridBagConstraints setGridBagConstraint(int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    /**
     * Method to add listeners
     */
    private void addListeners(){
        LocalGameButton.addActionListener(this);
        HostGameButton.addActionListener(this);
        JoinGameButton.addActionListener(this);
        IPField.addActionListener(this);
        OKButton.addActionListener(this);
        CancelButton.addActionListener(this);
    }

    /**
     * Set the background, foreground, and names for a component
     * @param component the component to be modified
     * @param name name to give the component
     * @param bg background color
     * @param fg foreground color
     */
    private void setBackgroundAndName(Component component, String name, Color bg, Color fg){
        component.setForeground(fg);
        component.setBackground(bg);
        component.setName(name);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */

    private void initComponents() {
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        addWindowListener(new java.awt.event.WindowAdapter() {
                              public void windowClosing(java.awt.event.WindowEvent evt) {
                                  System.exit(0);
                              }
                          }
        );

        gameModes.add(LocalGameButton);
        gameModes.add(HostGameButton);
        gameModes.add(JoinGameButton);

        LocalGameButton.setActionCommand("local");
        LocalGameButton.setSelected(true);
        getContentPane().add(LocalGameButton, setGridBagConstraint(1,0));

        HostGameButton.setActionCommand("host");
        getContentPane().add(HostGameButton, setGridBagConstraint(1,1));

        JoinGameButton.setActionCommand("join");
        getContentPane().add(JoinGameButton, setGridBagConstraint(1,2));

        setBackgroundAndName(IPField,"textfield5",Color.white,Color.black);
        IPField.setEnabled(false);
        getContentPane().add(IPField, setGridBagConstraint(2,3));

        setBackgroundAndName(IPLabel,"label10",background,Color.black);
        getContentPane().add(IPLabel, setGridBagConstraint(1,3));

        OKButton.setActionCommand("ok");
        setBackgroundAndName(OKButton, "button6", background2,Color.black);

        gridBagConstraints1 = setGridBagConstraint(2,5);
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        getContentPane().add(OKButton, gridBagConstraints1);

        CancelButton.setActionCommand("cancel");
        setBackgroundAndName(CancelButton, "button7", background2, Color.black);

        gridBagConstraints1 = setGridBagConstraint(3,5);
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        getContentPane().add(CancelButton, gridBagConstraints1);

        setBackgroundAndName(IPExampleLabel, "label11", background, Color.black);
        getContentPane().add(IPExampleLabel, setGridBagConstraint(2,4));

        addListeners();
    }

    /**
     * This takes care of when an action takes place. It will check the
     * action command of all components and then decide what needs to be done.
     *
     * @param e the event that has been fired
     */

    public void actionPerformed(ActionEvent e) {
        gameStateContext.doAction(e.getActionCommand());
        gameStateContext.setIPField();
    }

    public Facade getTheFacade() {
        return theFacade;
    }

    public JTextField getIPField() {
        return IPField;
    }
} //FirstScreen.java
