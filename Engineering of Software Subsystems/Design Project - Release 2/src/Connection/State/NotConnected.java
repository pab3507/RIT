package Connection.State;

/**
 * This class is used to represent the not connected state a session
* This acts as a concrete state of the state pattern
 * Language: Java 1.8 Level 8
 * Created: 11/4/17
 * Revisited: 11/5/17
 * Revisited: 11/6/17
 * @author Stephen Cook (sjc5897@g.rit.edu)
*/
public class NotConnected implements State {
    /**
     * Has the logic for a not connected session for querying flight
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] FlightQuery(String[] args) {
        return new String[]{"error,invalid connection"};
    }
    /**
     * Has the logic for a not connected session for creating a reservation
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] createReservation(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for deleting a reservation
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] deleteReservation(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for getting airport info
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] airportInfo(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for retrieving a reservation
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] retrieveReservation(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for selecting airport server
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] airportServer(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for undoing a command
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] undo(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for redoing a command
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] redo(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    /**
     * Has the logic for a not connected session for disconnection
     * @param args The string array of arguments
     * @return  The string array for the response that announces that the connection isn't valid
     */
    @Override
    public String[] disconnect(String[] args) {
        return new String[]{"error,invalid connection"};
    }

    @Override
    public void saveState() {}
}
