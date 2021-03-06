import java.util.ArrayList;

public class RetrieveReservationCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        String passenger = null, origin =  null, destination = null;

        if(args.length == 0 || args.length > 3) {
            return new String[] {"error,unknown request"};
        }
        passenger = args[0];

        if(args.length >= 2) {
            origin = args[1];
            if(!AirportInfo.IsAirportCodeValid(origin)) {
                return new String[] {"error,unknown origin"};
            }
        }

        if(args.length == 3) {
            destination = args[2];
            if(!AirportInfo.IsAirportCodeValid(destination)) {
                return new String[] {"error,unknown destination"};
            }
        }

        ArrayList<String> text = new ArrayList<>();
        ArrayList<Reservation> reservations = Reservation.retrieveReservation(passenger, origin, destination);
        text.add(String.format("retrieve,%d", reservations.size()));
        if(reservations.size() > 0) {
            StringBuilder sb = new StringBuilder();
            reservations.forEach(r -> {
                sb.append(String.format("%d,%d", r.getItinerary().getAirfare(), r.getItinerary().getFlights().size()));
                r.getItinerary().getFlights().forEach(f -> {
                    sb.append(String.format(
                            ",%d,%s,%s,%s,%s",
                            f.getFlightNumber(),
                            f.getOrigin(),
                            f.getDepartureTime(),
                            f.getDestination(),
                            f.getArrivalTime()
                            ));
                });
                text.add(sb.toString());
                sb.setLength(0);
            });
        }

        String[] ret = new String[text.size()];
        text.toArray(ret);
        return ret;
    }
}
