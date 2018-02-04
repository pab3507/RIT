package FlightQuery.Sorting;

import java.util.ArrayList;

import FlightQuery.FlightQuery;
import Itinerary.*;

/**
 * This is the interface for the sorting of the flightQuery
 * This acts as the Strategy Object for the Strategy Pattern
 * Implemented by FlightQuery.Sorting.DepartureSort, FlightQuery.Sorting.ArrivalSort, FlightQuery.Sorting.AirfareSort
 * Language: Java 1.8 level 8
 * Created: 10/12/2017
 * @author Stephen Cook(sjc5897@g.rit.edu
 */
public interface FlightSorting {
    public ArrayList<Itinerary> sort(ArrayList<Itinerary> itineraries);
}
