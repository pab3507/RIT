package Itinerary;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class creates and stores all flights in the UI.ConsoleUI System
 * Language Java 1.8 level 8
 * Created 10/12/2017
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class Flights {
    private static HashMap<String,ArrayList<FlightInfo>> flights;

    public Flights(String filename){
        try {
            flights = new HashMap<>();
            File file = new File(filename);
            Scanner flightfile = new Scanner(file);
            while (flightfile.hasNextLine()) {
                String line = flightfile.nextLine();
                String[] fields = line.split(",");
                if(! flights.containsKey(fields[0])){
                    flights.put(fields[0], new ArrayList<FlightInfo>());
                }
                flights.get(fields[0]).add(new FlightInfo(fields[0], fields[1], fields[3], fields[2], Integer.valueOf(fields[5]), Integer.valueOf(fields[4])));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public static  HashMap<String, ArrayList<FlightInfo>> getFlights(){
        return flights;
    }
}
