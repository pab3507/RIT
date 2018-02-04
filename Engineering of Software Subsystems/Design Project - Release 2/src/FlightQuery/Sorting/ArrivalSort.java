package FlightQuery.Sorting;

import java.util.ArrayList;

import FlightQuery.FlightQuery;
import Itinerary.*;
/**
 * This is one of there sorting classes used to sort the flight queries Sorting by Arrival
 * This acts as a concrete strategy in the strategy patter
 * Created: 10/14/2017
 * Language Java 1.8 Level 8
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class ArrivalSort implements FlightSorting {
    public ArrayList<Itinerary> sort(ArrayList<Itinerary> itineraries){
        Itinerary temp ;
        for (int i = 1; i < itineraries.size(); i++){
            for (int j = i; j>0; j--){
                int arrival1 = (itineraries.get(j).TimeConvert("a"));                                              //Converts time to 24-hour as it is easier to compare
                int arrival2 = itineraries.get(j-1).TimeConvert("a");
                if (arrival1 < arrival2) {                                                                              //Sorts by earliest arrival
                    temp = itineraries.get(j);
                    itineraries.set(j,itineraries.get(j-1));
                    itineraries.set(j-1, temp);
                }
            }
        }
        return itineraries;
    }

}
