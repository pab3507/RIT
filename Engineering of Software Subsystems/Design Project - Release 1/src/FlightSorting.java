import java.util.ArrayList;

/**
 * This is the interface for the sorting of the flightQuery
 * This acts as the Strategy Object for the Strategy Pattern
 * Implemented by DepartureSort, ArrivalSort, AirfareSort
 * Language: Java 1.8 level 8
 * Created: 10/12/2017
 * @author Stephen Cook(sjc5897@g.rit.edu
 */
public interface FlightSorting {
    public ArrayList<Itinerary> sort(ArrayList<Itinerary> itineraries);
}
