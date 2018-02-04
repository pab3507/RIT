package FlightQuery.Sorting;

import java.util.ArrayList;

import FlightQuery.FlightQuery;
import Itinerary.*;

/**
 * This is one of there sorting classes used to sort the flight queries
 * This acts as a concrete strategy in the strategy patter
 * Created: 10/14/2017
 * Language Java 1.8 Level 8
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class DepartureSort implements FlightSorting {
    public ArrayList<Itinerary> sort(ArrayList<Itinerary> itineraries){
        Itinerary temp ;
        for (int i = 1; i < itineraries.size(); i++){
            for (int j = i; j>0; j--){
                int departure1 = (itineraries.get(j).TimeConvert("d"));                                            //Converts time to 24-hour as it is easier to compare
                int departure2 = itineraries.get(j-1).TimeConvert("d");
                if (departure1 < departure2) {                                                                          //Sorts by earliest departure time
                    temp = itineraries.get(j);
                    itineraries.set(j,itineraries.get(j-1));
                    itineraries.set(j-1, temp);
                }
            }
        }
        return itineraries;
    }


}
