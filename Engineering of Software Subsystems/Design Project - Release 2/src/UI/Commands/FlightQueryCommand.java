package UI.Commands;
import Connection.Session;
import Itinerary.*;
import FlightQuery.*;
import FlightQuery.Sorting.*;

/**
 * Is a command in the command pattern for the flight query
 * Acts as a concCommand in the command pattern
 * Created: 10/15/2017
 * Language Java: 1.8 level 8
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class FlightQueryCommand implements Command {

    public FlightQueryCommand(){
        try {
            if (Flights.getFlights() == null) {
                new Flights("data/flightdata" );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String[] execute(String[] args) {

            Session thisSession = Session.getConnectionHashMap().get(Integer.parseInt(args[0]));
            if (args.length < 3 || args.length > 5) {
                String r = "error,unknown request";
                return r.split("\n");
            } else if (args.length == 3) {
                FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new DepartureSort(), 2);
                thisSession.setQuery(q);
                return toString(q);

            } else if (args.length == 4) {
                if (args[3].equals("departure")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new DepartureSort(), 2);
                    thisSession.setQuery(q);
                    return toString(q);
                }
                if (args[3].equals("arrival")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new ArrivalSort(), 2);
                    thisSession.setQuery(q);
                    return toString(q);
                }
                if (args[3].equals("airfare")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new AirfareSort(), 2);
                    thisSession.setQuery(q);
                    return toString(q);
                } else {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new DepartureSort(), Integer.parseInt(args[3]));
                    thisSession.setQuery(q);
                    return toString(q);
                }
            } else {
                if (args[4].equals("departure")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new DepartureSort(), Integer.parseInt(args[3]));
                    thisSession.setQuery(q);
                    return toString(q);
                }
                if (args[4].equals("arrival")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new ArrivalSort(), Integer.parseInt(args[3]));
                    thisSession.setQuery(q);
                    return toString(q);
                }
                if (args[4].equals("airfare")) {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new AirfareSort(), Integer.parseInt(args[3]));
                    thisSession.setQuery(q);
                    return toString(q);
                } else {
                    FlightQuery q = new FlightQuery(args[1], args[2], Flights.getFlights(), new DepartureSort(), Integer.parseInt(args[3]));
                    thisSession.setQuery(q);
                    return toString(q);
                }
            }
    }
    private String[] toString(FlightQuery q){
        q.getSort().sort(q.getItineraries());
        String to_str = "";
        to_str = "info" + String.valueOf(q.getItineraries().size()) + '\n';
        if (q.getItineraries().size() != 0){
            for (Itinerary i: q.getItineraries()){
                to_str = to_str + i.getId()+ "," + i.getAirfare() + ","
                        + String.valueOf(i.getFlights().size()) + ",";
                for(FlightInfo f:i.getFlights()){
                    to_str =to_str + f.getFlightNumber() + "," + f.getOrigin() + ","
                            +f.getDepartureTime() +"," + f.getDestination() +","+f.getArrivalTime()+",";
                }
                to_str = to_str + "\n";
            }
        }
        return to_str.split("\n");

    }
}
