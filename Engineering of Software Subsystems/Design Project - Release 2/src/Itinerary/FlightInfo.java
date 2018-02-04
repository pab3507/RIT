package Itinerary;

/**
 * This Class is the representation of flight information
 * It contains the Airports information, Times and airfare for the flights.txt
 * Implements the Itinerary.Itinerary Interface and is a Leaf in the composite pattern
 * Created: 10/11/2017
 * Language Java 1.8 Level 8
 * @author Stephen Cook (sjc5897@g.rit.edu)
 *
 */
public class FlightInfo implements ItineraryInterface{
    private String originAirport;
    private String destinationAirport;
    private String arrivalTime;
    private String departureTime;
    private int flightNumber;
    private int airfare;

    /**
     * This is the constructor for the flight info object
     * @param originAirport: String : The airport were the flight leaves from
     * @param destinationAirport: String : The airport that the flight goes to
     * @param arrivalTime  : String ?: The time the flight arrives at the destination
     * @param departureTime : String ?: The time the flight leaves the airport
     * @param airfare : int : The cost of a ticket
     * @param flightNumber : int : The number of the flight
     */
    public FlightInfo(String originAirport, String destinationAirport,String arrivalTime, String departureTime,
                      int airfare, int flightNumber)  {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.airfare = airfare;
        this.flightNumber = flightNumber;
    }

    /**
     * This converts the departure or arrival time to 24-hour clock
     * This is done because it makes it easier to compare two times
     * @param type This is the type of convert. d indicates departure time convert
     * @return an integer representing the time
     */
    public int TimeConvert(String type){
        if (type.equals("d")){                                                                                          //If the type = d we are converting the departure time
            String[] parts1 = this.departureTime.split(":");
            int a = Integer.parseInt(parts1[0]) * 100;                                                                  //gets the hours and converts to an integer
            String[] parts2 = parts1[1].split("");
            a = a + (Integer.parseInt(parts2[0])*10);                                                                   //gets the 10s of minutes and adds to the hours
            a = a + (Integer.parseInt(parts2[1]));                                                                      //adds the 1s of minutes
            if (parts2[2].equals("p") && a <= 1159){                                                                    //if in pm but not hour 12
                a += 1200;                                                                                              //we add 1200 to the 24 hour clock
            }
            return a;
        }
        else{                                                                                                           //if not d we assume a
            String[] parts1 = this.arrivalTime.split(":");
            int a = Integer.parseInt(parts1[0]) * 100;
            String[] parts2 = parts1[1].split("");
            a = a + (Integer.parseInt(parts2[0])*10);
            a = a + (Integer.parseInt(parts2[1]));
            if (parts2[2].equals("p") && a <= 1159){
                a += 1200;
            }
            return a;
        }}

    /**
     * gets the origin airport
     * @return the origin
     */
    public String getOrigin(){
        return this.originAirport;
    }

    /**
     * gets the destination airport
     * @return the destination
     */
    public String getDestination(){
        return this.destinationAirport;
    }

    /**
     * gets the arrival time
     * @return the arrival time
     */
    public String getArrivalTime(){
        return this.arrivalTime;
    }

    /**
     * gets the departure time
     * @return the departure time
     */
    public String getDepartureTime(){
        return this.departureTime;
    }

    /**
     * gets the flight number
     * @return the flight number
     */
    public int getFlightNumber(){
        return this.flightNumber;
    }

    /**
     * gets the airfare of the flight
     * @return the airfare
     */
    public int getAirfare(){
        return this.airfare;
    }

}

