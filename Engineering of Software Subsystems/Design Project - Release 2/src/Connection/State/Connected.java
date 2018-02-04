package Connection.State;

import Reservation.Reservation;
import UI.Commands.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class is used to represent the connected state a session
 * This acts as a concrete state of the state pattern
 * Language: Java 1.8 Level 8
 * Created: 11/4/17
 * Revisited: 11/5/17
 * Revisited: 11/6/17
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class Connected implements State {
    HashMap<String, Command> commands = new HashMap<>();
    AirportQueryCommand airport = new AirportQueryCommand();

    /**
     * This is the custom constructor for the connected command
     * This creates the hash map of all the different command
     */
    public Connected() {
        commands.put("connect", new ConnectionCommand());
        commands.put("info", new FlightQueryCommand());
        commands.put("reserve", new CreateReservationCommand());
        commands.put("retrieve", new RetrieveReservationCommand());
        commands.put("delete", new DeleteReservationCommand());
        commands.put("exit", new ExitCommand());
        commands.put("undo", new UndoCommand());
        commands.put("redo", new RedoCommand());
        commands.put("disconnect",new DisconnectCommand());
    }

    /**
     * This calls the flight query command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] FlightQuery(String[] args) {
        return commands.get("info").execute(args);
    }

    /**
     * This calls the create reservation command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] createReservation(String[] args) {
        return commands.get("reserve").execute(args);
    }

    /**
     * This calls the delete reservation command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] deleteReservation(String[] args) {
        return commands.get("delete").execute(args);
    }

    /**
     * This calls the airport info command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] airportInfo(String[] args) {
        return airport.execute(args);
    }

    /**
     * This calls the airport server command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] airportServer(String[] args) {
        return airport.setServer(args);
    }

    /**
     * This calls the undo command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] undo(String[] args) {
        return commands.get("undo").execute(args);
    }

    /**
     * This calls the flight query command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] redo(String[] args) {
        return commands.get("redo").execute(args);
    }

    /**
     * This calls the retrieve reservation command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] retrieveReservation(String[] args) {
        return commands.get("retrieve").execute(args);
    }

    /**
     * This calls the disconnect command
     * @param args The string array of arguments
     * @return  The string array for the response
     */
    @Override
    public String[] disconnect(String[] args){
        return commands.get("disconnect").execute(args);
    }

    @Override
    public void saveState() {
        try {
            Reservation.saveReservations();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
