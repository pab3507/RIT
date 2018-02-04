package UI.Commands;

import Connection.Session;
import Connection.State.Connected;

import java.util.HashMap;

/**
 * This is the command for connecting a new server
 */
public class ConnectionCommand implements Command{
    Integer connection = 0;
    public String[] execute(String[] arg){
        HashMap<Integer, Session> hash = Session.getConnectionHashMap();
        Integer connectionID = connection + 1;
        this.connection += 1;
        Session.addConnection(connectionID,new Connected());
        String response = "connected,"+connectionID;
        return response.split("\n");
    }
}
