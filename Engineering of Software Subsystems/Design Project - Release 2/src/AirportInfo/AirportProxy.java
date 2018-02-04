package AirportInfo;

import java.rmi.*;
import java.util.*;

/** Function: This interface is the Subject in the proxy pattern and is implemented by both AirportInfo and FAAairportInfo
 *            allows for the creation of a remote proxy in order to retrieve information from the FAA server
 * Created: 11/07/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/
public interface AirportProxy extends Remote {

    String [] request(String airportCode) throws RemoteException;

}
