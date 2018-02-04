/**
 * Is a command in the command pattern for the flight query
 * Acts as a concCommand in the command pattern
 * Created: 10/15/2017
 * Language Java: 1.8 level 8
 * @author Stephen Cook (sjc5897@g.rit.edu)
 */
public class FlightQueryCommand implements Command {
    Flights f;

    public String[] execute(String[] args) {
        try {
            if (f == null) {
                f = new Flights("data/flightdata" );
            }
            if (args.length < 2 || args.length > 4) {
                String r = "error,unknown request";
                return r.split("\n");
            } else if (args.length == 2) {
                FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new DepartureSort(), 2);
                return toString(q);

            } else if (args.length == 3) {
                if (args[2].equals("departure")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new DepartureSort(), 2);
                    return toString(q);
                }
                if (args[2].equals("arrival")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new ArrivalSort(), 2);
                    return toString(q);
                }
                if (args[2].equals("airfare")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new AirfareSort(), 2);
                    return toString(q);
                } else {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new DepartureSort(), Integer.parseInt(args[2]));
                    return toString(q);
                }
            } else {
                if (args[3].equals("departure")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new DepartureSort(), Integer.parseInt(args[2]));
                    return toString(q);
                }
                if (args[3].equals("arrival")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new ArrivalSort(), Integer.parseInt(args[2]));
                    return toString(q);
                }
                if (args[3].equals("airfare")) {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new AirfareSort(), Integer.parseInt(args[2]));
                    return toString(q);
                } else {
                    FlightQuery q = new FlightQuery(args[0], args[1], this.f.getFlights(), new DepartureSort(), Integer.parseInt(args[2]));
                    return toString(q);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    private String[] toString(FlightQuery q){
        q.getSort().sort(FlightQuery.getItineraries());
        String to_str = "";
        to_str = "info" + String.valueOf(FlightQuery.getItineraries().size()) + '\n';
        if (FlightQuery.getItineraries().size() != 0){
            for (Itinerary i:FlightQuery.getItineraries()){
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
