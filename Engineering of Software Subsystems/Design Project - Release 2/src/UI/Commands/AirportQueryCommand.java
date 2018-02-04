package UI.Commands;

import java.rmi.RemoteException;
import java.util.*;
import java.lang.*;
import AirportInfo.*;

/** Function: This class is a command in the command pattern for the airport query
 *            Acts as a concCommand in the command pattern
 * Created: 10/17/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/
public class AirportQueryCommand implements Command {

    public ServerStrategy CurrentSta;

    public AirportQueryCommand() {{
        new AirportInfo();
        CurrentSta = new LocalStrategy();

        }
    }

    /**
     * Function: Execute method to either raise an error or to return airport information
     * to UI.ConsoleUI
     * Parameters: (String[] args): airport code passes in as argument from UI.ConsoleUI
     * Return: String []: either error message, or string of airport information
     **/
    @Override
    public String[] execute(String[] args) {

        String airportcode = args[1].toUpperCase();
        if (!AirportInfo.IsAirportCodeValid(airportcode)) {
            String t = "error,unknown airport";
            return t.split("\n");
        }
        String[] response = CurrentSta.run(args);
        return response;
    }

    /**
     * This sets the new strategy based on user input
     * @param args The user input
     * @return     The response string.
     */
    public String [] setServer(String[] args){
        if(args[1].equals("local")) {
            this.CurrentSta = new LocalStrategy();
            return new String[] {args[0]+ ",server,successful"};
        }
        if (args[1].equals("faa")){
            this.CurrentSta = new FaaStrategy();
            return new String[] {args[0]+ ",server,successful"};
        }
        else{
            return new String[] {args[0] + ",error,unknown server information"};
        }
    }
}


