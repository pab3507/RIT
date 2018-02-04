package UI.Commands;

import Connection.Session;
import Connection.State.NotConnected;

import java.util.HashMap;

/**
 * The Command to disconnect a connection
 */
public class DisconnectCommand implements Command {
    public String[] execute(String[] args) {
        int sessionID = Integer.parseInt(args[0]);
        HashMap<Integer, Session> connections = Session.getConnectionHashMap();
        if(connections.containsKey(sessionID)) {
            Session session = connections.remove(sessionID);
            session.saveState();
            session = null;
            return new String[]{sessionID + ",disconnect"};
        } else {
            return new String[] {"error,invalid connection"};
        }
    }
}
