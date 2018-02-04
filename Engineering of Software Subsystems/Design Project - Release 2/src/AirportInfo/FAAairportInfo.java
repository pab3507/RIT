package AirportInfo;

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.util.*;

/** Function: This class functions as a RESTful proxy to get airport information from the FAA web service.
 *            It maintains a reference that allows the Proxy to access the RealSubject. Since both AirportInfo and
 *            FAAairportInfo implement the AirportProxy interface, the Proxy can be substituted for the RealSubject.
 *            The FAAairportInfo class, in a way, controls access to AirportInfo [RealSubject] and is responsible for
 *            the creation or deletion of a request to the FAA web service. This class pulls from the FAA web service and
 *            converts the xml file into strings to store in an array.
 * Created: 11/07/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/
public class FAAairportInfo implements AirportProxy {

    public String[] FAAResponse = null;
    private String airportcode;


    public FAAairportInfo(String airportcode){

        try{

            this.airportcode = airportcode;
            FAAResponse = new String [4];
            String url = "http://services.faa.gov/airport/status/" + airportcode +"?format=application/xml";

            // Create a URL and open a connection
            URL AirportURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) AirportURL.openConnection();


            // Set the HTTP Request type method to GET (Default: GET)
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }

            // Converts XML file to string
            String response = resp.toString();


            // Pulls out the name, weather, temperature, and delay from the string and stores it in the respective variables
            String[] airportnameparts = response.split("<Name>");
            String[] airportname = (airportnameparts[1].trim().split("<"));
            this.FAAResponse[0] = (airportname[0].trim());

            String[] weatherparts = response.split("<Weather>");
            String[] weath = (weatherparts[2].trim().split("<"));
            this.FAAResponse[1] = (weath[0].trim());

            String[] temperatureparts = response.split("<Temp>");
            String[] temperature = (temperatureparts[1].trim().split("<"));
            this.FAAResponse[2] = (temperature[0].trim());

            String[] delayparts = response.split("<AvgDelay>");
            String[] delay = (delayparts[1].trim().split("<"));
            if (delay[0].trim().equals("")){
                this.FAAResponse[3] = "0 hours 0 minutes";
            }
            else{
                this.FAAResponse[3] = (delay[0].trim());
            }



            // Closes the connection
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // returns the string array of information from the FAA server
    public String[] request(String airportcode) throws RemoteException{
        return FAAResponse;
    }

}
