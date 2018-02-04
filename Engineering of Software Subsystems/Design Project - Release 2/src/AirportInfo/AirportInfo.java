package AirportInfo;

import java.io.*;
import java.lang.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.rmi.RemoteException;
import java.util.*;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.URL;



/** Function: This class reads in three files for the local test service and makes a hashmap with the airport code as
 *            the key and the value contains name, weather, temperature, and delay time. Also has the functionality to
 *            check to see if a airport code is valid. The AirportInfo class also acts as a RealSubject for the
 *            FAAairportInfo proxy, there is a request() method to request the FAA server information from the proxy
 * Created: 11/07/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/

public class AirportInfo implements AirportProxy{
    private static HashMap<String,AirportInfo> airportInfoHashMap ;
    private String airportcode;
    private String airportname;
    private int connection;
    private int delay;
    public ArrayList<String> weathertemp = new ArrayList<>();
    public ArrayList<String> weathers = new ArrayList<>();

    public AirportInfo (String airportcode){
        this.airportcode = airportcode;
    }

    public AirportInfo(String airportcode, String airportname, ArrayList weathers, ArrayList weathertemp, int delay,int connection) {
        this.airportcode = airportcode;
        this.airportname = airportname;
        this.weathers = weathers;
        this.weathertemp = weathertemp;
        this.delay = delay;
        this.connection = connection;

    }
    public AirportInfo() {
        if (this.airportInfoHashMap == null) {
            try {


                this.airportInfoHashMap = new HashMap<>();
                File f1 = new File("data/airports");
                Scanner airportfile = new Scanner(f1);

                File f2 = new File("data/weather");
                Scanner weatherfile = new Scanner(f2);

                File f3 = new File("data/delay");
                Scanner delayfile = new Scanner(f3);

                File f4 = new File("data/connection");
                Scanner connectionfile = new Scanner(f3);

                while (airportfile.hasNextLine() && weatherfile.hasNextLine() && delayfile.hasNextLine() &&
                        connectionfile.hasNextLine()) {
                    ArrayList<String> weather = new ArrayList<>();
                    ArrayList<String> temp = new ArrayList<>();
                    String l1 = airportfile.nextLine();
                    String[] airportfields = l1.split(",");

                    String l2 = weatherfile.nextLine();
                    String[] weatherfields = l2.split(",");
                    int a = 0;
                    for (String i : weatherfields) {
                        if (a != 0) {
                            if (a % 2 == 0) {
                                temp.add(i);
                            } else {
                                weather.add(i);
                            }
                        }

                        a++;
                    }

                    String l3 = delayfile.nextLine();
                    String[] delayfields = l3.split(",");
                    String l4 = connectionfile.nextLine();
                    String[] connectionFields = l4.split(",");

                    airportInfoHashMap.put(airportfields[0], new AirportInfo(airportfields[0], airportfields[1],
                            weather, temp, Integer.parseInt(delayfields[1]), Integer.parseInt(delayfields[1])));

                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * Function: Checks to see that the airport code entered by the client is
     *           is a valid airport code
     * Parameters: (String airportcode): airport code that is entered by client
     * Return: boolean true/false: returns boolean of the validity of airport code
     **/
    public static boolean IsAirportCodeValid(String airportcode){
        return airportInfoHashMap.containsKey(airportcode);
    }

    public String getName(){
        return this.airportname;
    }
    public int getDelay(){
        return this.delay;
    }

    /**
     * Function: Loops through the arraylist of weather and temperature pairs associated with an airportcode and outputs
     *           a single weather and temperature
     * Parameters: (int looper): looper variable to keep track of iterations
     * Return: String: returns a string containing a single weather and temperature
     **/
    public String getWeather(int looper){
        int index = 0;
        if(looper == 0){
            index = looper;
        }
        else {
            index = looper % weathers.size();
        }
        String weather = weathers.get(index);
        String temp = weathertemp.get(index);
        return weather + "," + temp;

    }
    public int getConnection(){


        return this.connection;
    }

    public static HashMap<String,AirportInfo> getAirportInfo(){
        return airportInfoHashMap;
    }



        /**
         * Function: Calls the proxy class (FAAairportInfo) in order to pull the xml information from the FAA server, this
         *           class takes the information passed in from FAAairportInfo and creates a string arrray
         * Parameters: (none): N/A
         * Return: String []: returns a string array of the airport information retreived from the FAA server
         **/
        public String [] request (String airportcode){
            try{
                String[] response = null;
                String airportString = "airport, ";
                int i = 0;

                FAAairportInfo f = new FAAairportInfo(airportcode);
                response = f.request(airportcode);

                while (i < response.length - 1){
                    airportString = airportString + response[i] + ", ";
                    i++;
                }

                airportString = airportString + response[i];

                return airportString.split("\n");

            } catch (RemoteException e) {
                return ("Server Session: Unsuccessful").split("\n");
            }

        }
    }
