import java.util.Optional;

public class CreateReservationCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        if(args == null || args.length != 2) {
            return new String[]{"error,unknown request"};
        }

        int id = Integer.valueOf(args[0]);
        String passenger = args[1];

        Optional<Itinerary> itinerary = FlightQuery.getItineraries().stream()
                .filter(i -> i.getId() == id)
                .findFirst();

        if(itinerary.isPresent()) {
            try {
                Reservation.createReservation(itinerary.get(), passenger);
            } catch(IllegalArgumentException e) {
                return new String[] {"error,duplicate reservation"};
            }
            return new String[] {"reserve,successful"};
        } else {
            return new String[] {"error,invalid id"};
        }
    }
}
