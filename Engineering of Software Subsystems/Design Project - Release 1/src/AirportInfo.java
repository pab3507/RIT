

import java.io.*;
import java.lang.*;
import java.util.*;
import java.lang.String;

/** Function: This class is reads in three files and makes an arraylist of airport name
 *            weather, temperature, and delay time. It also checks to see if an airport
 *            code is valid
 * Created: 10/17/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/

public class AirportInfo {

    private String airportcode;
    private String airportname;
//    private String weather;
//    private int temperature;
    private int delay;
    public ArrayList<String> weathertemp = new ArrayList<>();
    public ArrayList<String> weathers = new ArrayList<>();
    private int looper = 0;

//
//    public AirportInfo(String airportcode) {
//        this.airportcode = airportcode;
//    }
//
//    public AirportInfo(ArrayList weathertemp) {
//        this.weathertemp = weathertemp;
//    }

    public AirportInfo(String airportcode, String airportname, ArrayList weathers, ArrayList weathertemp, int delay) {
        this.airportcode = airportcode;
        this.airportname = airportname;
        this.weathers = weathers;
        this.weathertemp = weathertemp;
        this.delay = delay;
    }

//    public AirportInfo(String weather, int temperature) {
//        this.weather = weather;
//        this.temperature = temperature;
//    }

    /**
     * Function: Checks to see that the airport code entered by the client is
     *           is a valid airport code
     * Parameters: (String airport code): airport code that is entered by client
     * Return: boolean true/false: returns boolean of the validity of airport code
     **/
    public static boolean IsAirportCodeValid(String airportcode){
        try{
            File f1 = new File("data/airports");
            Scanner airportfile = new Scanner(f1);

            while (airportfile.hasNextLine()){
                String l1 = airportfile.nextLine();
                String[] airportfields = l1.split(",");

                if (airportfields[0].equals(airportcode)){
                    return true;
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Function: Reads in three files and makes an arraylist of airport name
     *            weather, temperature, and delay time, then creates a string
     *            to output the result of the query
     * Parameters: (String[] args): airport code passes in as argument from AFRS
     * Return: String : String of airport name weather, temperature, and delay time
     *                  corresponding to a particular airport
     **/
//    public String getAirportInformation(String airportcode, int L) {
//        try {
//
//            ArrayList<AirportInfo> airports = new ArrayList<AirportInfo>();
//            ArrayList<AirportInfo> weathers = new ArrayList<AirportInfo>();
//
//
//            File f1 = new File("data/airports");
//            Scanner airportfile = new Scanner(f1);
//
//            File f2 = new File("data/weather");
//            Scanner weatherfile = new Scanner(f2);
//
//            File f3 = new File("data/delay");
//            Scanner delayfile = new Scanner(f3);
//
//            while (airportfile.hasNextLine() && weatherfile.hasNextLine() && delayfile.hasNextLine()) {
//
//                String l1 = airportfile.nextLine();
//                String[] airportfields = l1.split(",");
//
//                String l2 = weatherfile.nextLine();
//                String[] weatherfields = l2.split(",");
//
//                String l3 = delayfile.nextLine();
//                String[] delayfields = l3.split(",");
//
//                int weatherlength = weatherfields.length;
//
//                for (int j = 1; j < weatherlength - 1; j = j + 2) {
//                    weathertemp.add(new AirportInfo(weatherfields[j], Integer.valueOf(weatherfields[j + 1])));
//                }
//
//                weathers.add(new AirportInfo(weathertemp)); //creates an arraylist of arraylists of each weather,
//                                                            //temperature pair coordinating with an airport code
//                airports.add(new AirportInfo(airportfields[0], airportfields[1], weathers, Integer.valueOf(delayfields[1])));
//            }
//
//            int airportinfoLength = airports.size();
//            Iterator iterator = weathertemp.iterator();
//
//            for (int i = 0; i < airportinfoLength; i++) {
//                if (airports.get(i).airportcode.equals(airportcode)) {
//                    airportcode = airports.get(i).airportcode;
//                    airportname = airports.get(i).airportname;
//                    while (iterator.hasNext()){
//                        weathertemp = airports.get(i).weathers.get(i).weathertemp;
//                        temperature = airports.get(i).weathers.get(i).weathertemp.get(looper).temperature;
//                    }
//
//
//                    delay = airports.get(i).delay;
//
//                }
//
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return airportcode + ", " + airportname + ", " + weather + ", " + temperature + ", " + delay;
//    }
    public String getName(){
        return this.airportname;
    }
    public int getDelay(){
        return this.delay;
    }
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

}

