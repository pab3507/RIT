package Itinerary;

/**
 * This is the interface used by the itinerary to implement the composite pattern
 * Implemented by Itinerary.FlightInfo and Itinerary.Itinerary and acts as the Component in the Composite pattern
 * Language: Java 1.8 Level 8
 * Created 10/11/2017
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public interface ItineraryInterface {
    String getOrigin();
    String getDestination();
    String getArrivalTime();
    String getDepartureTime();
    int getAirfare();
}
