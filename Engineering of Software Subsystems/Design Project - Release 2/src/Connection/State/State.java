package Connection.State;
import UI.Commands.Command;
import java.util.HashMap;

/**
 * The Interface for the states in the command pattern
 * Language: Java 1.8 Level 8
 * Created: 11/4/17
 * Revisited: 11/5/17
 * Revisited: 11/6/17
 * @author: Stephen Cook(sjc5897@g.rit.edu)
 */
public interface State {
    String[] FlightQuery(String[] args);
    String[] createReservation(String[] args);
    String[] deleteReservation (String[] args);
    String[] airportInfo(String[] args);
    String[] retrieveReservation(String[] args);
    String[] airportServer(String[] args);
    String[] undo(String[] args);
    String[] redo(String[] args);
    String[] disconnect(String[] args);

    void saveState();
}
