package Itinerary;

import java.util.ArrayList;

/**
 * Represents an created Itinerary.Itinerary
 * Implements the Itinerary.ItineraryInterface and is a composite in the Composite pattern
 * Language: Java 1.8 Level 8
 * Created 10/11/2017
 * Revistited: 10/12/2017
 *
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class Itinerary implements ItineraryInterface {
    private String origin;
    private String destination;
    private ArrayList<FlightInfo> flights = new ArrayList<>();
    private int id;


    /**
     * This is the constructor of the Itinerary.Itinerary Interface
     *
     * @param origin      : String : The origin airport for the Itinerary.Itinerary
     * @param destination : String: The Destination of the Itinerary.Itinerary
     */
    public Itinerary(String origin, String destination, int id) {
        this.origin = origin;
        this.destination = destination;
        this.id = id;
    }

    /**
     * Adds a flight to itinerary
     *
     * @param flight : Itinerary.Itinerary Interface : The flight being added
     */
    public void addFlight(FlightInfo flight) {
        this.flights.add(flight);
    }

    /**
     * Removes flight from an itinerary
     *
     * @param flight : Itinerary.ItineraryInterface: The flight being removed
     */
    public void removeFlight(FlightInfo flight) {
        this.flights.remove(flight);
    }

    /**
     * Gets the departure time of the Itinerary.Itinerary by getting the departure of the first flight
     *
     * @return String : The departure time of the Itinerary.Itinerary
     */
    public String getDepartureTime() {
        return this.flights.get(0).getDepartureTime();
    }

    /**
     * Gets the arrival time of the Itinerary.Itinerary by getting the arrival time of the last flight
     *
     * @return String : The arrival time of the last flight
     */
    public String getArrivalTime() {
        return this.flights.get(flights.size() - 1).getArrivalTime();
    }

    /**
     * gets the origin of the itinerary
     *
     * @return the origin
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * gets the destination of the itinerary
     *
     * @return the destination
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * gets the airfare by getting the sum of individual flight sums
     *
     * @return the total airfare
     */
    public int getAirfare() {
        int a = 0;
        for(ItineraryInterface f : this.flights) {
            a = a + f.getAirfare();
        }
        return a;
    }

    /**
     * returns the flight path for this itinerary
     *
     * @return the flight path
     */
    public ArrayList<FlightInfo> getFlights()

    {
        return this.flights;
    }

    /**
     * returns the id number of this itinerary
     *
     * @return the id number
     */
    public int getId() {
        return this.id;
    }

    /**
     * converts the time of the itinerary to 24-hour to make comparison easier
     *
     * @param type the opition we are converting: destination time or arrival time
     * @return
     */
    public int TimeConvert(String type) {
        //If the type = d we are converting the departure time
        if(type.equals("d")) {
            String[] parts1 = this.getDepartureTime().split(":");
            int a = Integer.parseInt(parts1[0]) * 100;
            //gets the hours and converts to an integer
            String[] parts2 = parts1[1].split("");
            //gets the 10s of minutes and adds to the hours
            a = a + (Integer.parseInt(parts2[0]) * 10);
            //adds the 1s of minutes
            a = a + (Integer.parseInt(parts2[1]));
            //if in pm but not hour 12
            if(parts2[2].equals("p") && a <= 1159) {
                //we add 1200 to the 24 hour clock
                a += 1200;
            }
            return a;
        } else {
            //if not d we assume a
            String[] parts1 = this.getArrivalTime().split(":");
            int a = Integer.parseInt(parts1[0]) * 100;
            String[] parts2 = parts1[1].split("");
            a = a + (Integer.parseInt(parts2[0]) * 10);
            a = a + (Integer.parseInt(parts2[1]));
            if(parts2[2].equals("p") && a <= 1159) {
                a += 1200;
            }
            return a;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s,%s", getAirfare(), flights.size()));
        for(FlightInfo f : flights) {
            sb.append(String.format(
                    ",%s,%s,%s,%s,%s",
                    f.getFlightNumber(),
                    f.getOrigin(),
                    f.getDepartureTime(),
                    f.getDestination(),
                    f.getArrivalTime()));
        }

        return sb.toString();
    }
}
