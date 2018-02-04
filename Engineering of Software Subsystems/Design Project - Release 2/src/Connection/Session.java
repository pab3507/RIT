package Connection;
import Connection.State.*;
import FlightQuery.FlightQuery;
import Itinerary.Itinerary;
import UI.Commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This is the class that is used to represent an individual Session
 * This class has two states Connected and NotConnected which changes the systems behavior
 * Language: Java 1.8 Level 8
 * Created: 11/4/17
 * Revisited: 11/5/17
 * Revisited: 11/6/17
 * @author : Stephen Cook(sjc5897@g.rit.edu)
 */
public class Session
{
    private static HashMap<Integer, Session> connectionHashMap= new HashMap<>();
    private int connectionID;
    private ArrayList<Itinerary> lastQueried;
    private int looper;
    private State currentState;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    /**
     * The constructor for the Connection
     * @param ID            The Connection ID
     * @param connection    The Connection State
     */
    private Session(Integer ID, State connection){
        this.connectionID =ID;
        this.lastQueried = new ArrayList<>();
        this.looper = 0;
        this.currentState = connection;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    /**
     * Changes the current state of the session
     * @param connection The new state of the session
     */
    public void setCurrentState(State connection){
        this.currentState = connection;
    }

    /**
     * This calls the current states flight query method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] FlightQuery(String[] args){
        return this.currentState.FlightQuery(args);
    }

    /**
     * This sets the last queried to the new last queried by the session
     * @param q The new flight query object
     */
    public void setQuery(FlightQuery q){
        this. lastQueried = q.getItineraries();
    }

    /**
     * This gets the last query
     * @return The last query
     */
    public ArrayList<Itinerary> getLastQueried(){
        return this.lastQueried;
    }

    /**
     * This calls the current states create reservation method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] createReservation(String [] args){
        return this.currentState.createReservation(args);
    }

    /**
     * This calls the current states delete reservation method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] deleteReservation(String[] args){
        return this.currentState.deleteReservation(args);
    }

    /**
     * This calls the current states retrieve reservation method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] retrieveReservation(String[] args){
        return this.currentState.retrieveReservation(args);
    }

    /**
     * This calls the current states airport info method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] airportInformation(String[] args){return this.currentState.airportInfo(args);}

    /**
     * This calls the current states airport server method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] airportServer(String[] args){
        return this.currentState.airportServer(args);
    }

    /**
     * This calls the current states disconnect method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] disconnect(String [] args){return this.currentState.disconnect(args);}

    /**
     * This method adds a new connection to the connection hash map
     * @param connectionID  The connection Id
     * @param connection    The connection state
     */
    public static void addConnection(Integer connectionID, State connection){
        Session Session = new Session(connectionID,connection);
        connectionHashMap.put(connectionID, Session);
    }

    /**
     * Gets the connection hash map
     * @return the connection hash map
     */
    public static HashMap<Integer, Session> getConnectionHashMap(){
        return connectionHashMap;
    }

    /**
     *
     * @return
     */
    public Stack<Command> getUndoStack() {return undoStack;}
    public Stack<Command> getRedoStack() {return redoStack;}

    /**
     * This calls the current states flight query method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] undo(String args[]) {return this.currentState.undo(args);}

    /**
     * This calls the current states flight query method
     * @param args The String array that represents user input
     * @return The String Array Response
     */
    public String[] redo(String[] args) {
        return this.currentState.redo(args);
    }


    /**
     * Gets the looper value for this session
     * @return The looper value
     */
    public int getLooper(){return this.looper;}

    /**
     * Adds one to the looper
     */
    public void addLooper(){this.looper = this.looper+1;}

    /**
     * Saves the current reservation list on disconnect
     */
    public void saveState(){this.currentState.saveState();}

}
