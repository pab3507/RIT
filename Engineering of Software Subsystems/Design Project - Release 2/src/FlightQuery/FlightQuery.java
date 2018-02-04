package FlightQuery;

import java.util.HashMap;
import java.util.ArrayList;

import FlightQuery.Sorting.FlightSorting;
import Itinerary.*;
import AirportInfo.*;

/**
 * This class creates Itineraries based on flight information
 * Language: Java 1.8 Level 8
 * Created: 10/12/2017
 * Revisited: 10/14/2017
 * Revisited: 10/15/2017
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class FlightQuery {
    private ArrayList<Itinerary> itineraries;
    private FlightSorting sort;

    /**
     * This acts as the constructor for the FlightQuery.FlightQuery
     *
     * @param Origin      The Origin Airport
     * @param Destination The Destination Airport
     * @param flights     The list of flights
     * @param sort        The selected sort method
     * @param c           The max number of connections
     */
    public FlightQuery(String Origin, String Destination, HashMap<String, ArrayList<FlightInfo>> flights, FlightSorting sort, int c) {
        this.sort = sort;
        itineraries = createItineraries(Origin, Destination, flights, c);

    }

    /**
     * This method calls creates Itineraries based on valid flight paths and returns them to the constructor
     *
     * @param Origin      The origin airport
     * @param Destination The destination airport
     * @param flights     The list of flights in the system
     * @param c           The max number of connections
     * @return The Itineraries
     */
    private ArrayList<Itinerary> createItineraries(String Origin, String Destination, HashMap<String, ArrayList<FlightInfo>> flights, int c) {
        ArrayList<Itinerary> itineraries = new ArrayList<>();                                                           //creates the list of Itineraries
        ArrayList<ArrayList<FlightInfo>> flightPaths = new ArrayList<>();                                               //creates a list of flight paths
        int id = 0;                                                                                                     //initializes the id number
        flightPaths = flightPaths(Origin, Destination, flights, flightPaths, new ArrayList<>(), c, 0);                                      //gets all flight paths from the origin airport within connection limit
        for (ArrayList<FlightInfo> flightPath : flightPaths) {                                                           //now check that each flight path is valid
            if (isValid(flightPath, Origin, Destination)) {
                Itinerary newI = new Itinerary(Origin, Destination, id);                                                  //if it is valid we create a new itinerary
                id++;
                for (FlightInfo flight : flightPath) {
                    newI.addFlight(flight);
                }
                itineraries.add(newI);
            }
        }
        return itineraries;
    }

    /**
     * This method works with the createItineraries method to create flight paths
     * This method gets all the possible flight paths from the origin
     *
     * @param Origin      The Origin Airport
     * @param Destination The Destination Airport
     * @param flight      The flights in the system
     * @param flightPaths The current flight paths found
     * @param c           The max number of connections
     * @param cc          The number of connections we are on
     * @return All possible flight paths out of the origin airport
     */
    private ArrayList<ArrayList<FlightInfo>> flightPaths(String Origin, String Destination, HashMap<String, ArrayList<FlightInfo>> flight,
                                                         ArrayList<ArrayList<FlightInfo>> flightPaths, ArrayList<ArrayList<FlightInfo>> successPaths, int c, int cc) {
        if (cc > c) {                                                                                                    //Checks that we have the max amount of connections
            return successPaths;
        }
        if (flightPaths.size() == 0) {                                                                                  //if we have no flights in flightPath we add all flights out of origin
            ArrayList<FlightInfo> f = flight.get(Origin);                                                               //Now we get all flights out of the destination of the first flight in this path
            for (FlightInfo newFlight : f) {
                ArrayList<FlightInfo> newPath = new ArrayList<>();                                                      //Then we create a new path with the next flight//We add the previous path
                newPath.add(newFlight);
                if (newFlight.getDestination().equals(Destination)) {
                    successPaths.add(newPath);
                } else {

                    flightPaths.add(newPath);
                }
            }
        } else {
            ArrayList<ArrayList<FlightInfo>> tempflightPaths = new ArrayList<ArrayList<FlightInfo>>();                  //We must create a tempFlightPath to iterate through
            tempflightPaths.addAll(flightPaths);                                                                        //as we will be adding new paths to the flight path
            for (ArrayList<FlightInfo> Path : tempflightPaths) {
                String currentAirport = Path.get(Path.size() - 1).getDestination();                                     //Get the airport we are at
                ArrayList<FlightInfo> f = flight.get(currentAirport);                                                   //Now we get all flights out of the destination of the first flight in this path
                if (!currentAirport.equals(Destination)) {
                    if (f.size() > 0) {
                        for (FlightInfo newFlight : f) {
                            ArrayList<FlightInfo> newPath = new ArrayList<>();                                          //Then we create a new path with the next flight
                            newPath.addAll(Path);                                                                       //We add the previous path
                            newPath.add(newFlight);                                                                     //then the new flight
                            if (newFlight.getDestination().equals(Destination)) {
                                successPaths.add(newPath);
                            } else {
                                flightPaths.add(newPath);
                            }
                        }
                    }


                }
            }
        }
        return flightPaths(Origin, Destination, flight, flightPaths, successPaths, c, cc + 1);
    }

    /**
     * This method checks a flight path to see if it goes to the destination and is valid
     * It rejects path that go through the same airport twice
     * Doesn't start at origin or end at destination
     *
     * @param path        The flight path we are checking
     * @param Origin      the origin airport
     * @param Destination the destination airport
     * @return A boolean representing if the flight path is valid or not
     */
    private boolean isValid(ArrayList<FlightInfo> path, String Origin, String Destination) {
        HashMap<String, AirportInfo> airportinfo = AirportInfo.getAirportInfo();
        if (path.get(0).getOrigin().equals(Origin) && path.get(path.size() - 1).getDestination().equals(Destination)) {    //First we check if origin and destination is correct
            ArrayList<String> visit = new ArrayList<>();                                                                //this is to track what airports we have visited
            visit.add(Origin);
            for (int i = 1; i <= path.size() - 1; i++) {                                                                       //now we look at each flight in the path
                FlightInfo f = path.get(0);
                if (visit.contains(f.getDestination())) {                                                                //Check if we have been to this airport all ready
                    return false;                                                                                       //if yes return false
                }
                visit.add(f.getDestination());                                                                          //if not add to visit list
                Integer fArrival = f.TimeConvert("a");
                int Thisdelay = airportinfo.get(f.getOrigin()).getDelay();
                Thisdelay = Thisdelay + airportinfo.get(f.getOrigin()).getConnection();
                int min = fArrival % 100;
                int hour = fArrival - min;
                min = min + Thisdelay;
                while (min > 60) {
                    hour = hour + 100;
                    min = min - 60;
                }
                fArrival = hour + min;
                FlightInfo af = path.get(i);
                int departureF = af.TimeConvert("d");
                if (fArrival > departureF) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public ArrayList<Itinerary> getItineraries() {
        return itineraries;
    }

    public FlightSorting getSort() {
        return this.sort;
    }

}
