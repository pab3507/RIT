import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Reservation {
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private Itinerary itinerary;
    private String passenger;

    /**
     * Creates a new {@link Reservation} object.
     * @param itinerary The {@link Itinerary} to store in the reservation.
     * @param passenger The passenger the reservation is for.
     */
    public Reservation(Itinerary itinerary, String passenger) {
        this.itinerary = itinerary;
        this.passenger = passenger;
    }

    /**
     * @return The itinerary for this reservation.
     */
    public Itinerary getItinerary() {
        return itinerary;
    }

    /**
     * @return The passenger this reservation is for.
     */
    public String getPassenger() {
        return passenger;
    }

    /**
     * Gets the reservations matching the specified criteria.
     * @param passenger The passenger to match on. May not be null.
     * @param origin The origin airport to match on. May be null.
     * @param destination The destination airport to match on. May be null.
     * @return A list of matching reservations, if any.
     * @throws IllegalArgumentException {@code passenger} is {@code null}.
     */
    public static ArrayList<Reservation> retrieveReservation(String passenger, String origin, String destination) {
        if(passenger == null) {
            throw new IllegalArgumentException("Passenger must not be null");
        }

        Stream<Reservation> matches = reservations.stream()
                .filter(r -> r.passenger.equals(passenger));

        if(origin != null && !origin.equals("")) matches = matches.filter(r -> r.itinerary.getOrigin().equals(origin));
        if(destination != null && !origin.equals("")) matches = matches.filter(r -> r.itinerary.getDestination().equals
                (destination));

        return matches.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Creates and stores a new reservation, provided one does not already exist in the system.
     * @param itinerary The itinerary to store with this reservation.
     * @param passenger The passenger making the reservation.
     * @return A reservation object corresponding to the stored reservation.
     * @throws IllegalArgumentException A reservation with the same passenger and origin/destination pair already
     * exists.
     */
    public static Reservation createReservation(Itinerary itinerary, String passenger) {
        Reservation reservation = new Reservation(itinerary, passenger);

        if(reservations.indexOf(reservation) == -1) {
            reservations.add(reservation);
            return reservation;
        } else {
            throw new IllegalArgumentException(String.format(
                    "%s has already created a reservation between %s and %s.",
                    passenger,
                    itinerary.getOrigin(),
                    itinerary.getDestination()));
        }
    }

    /**
     * Deletes a reservation based on the passed parameters.
     * @param passenger The passenger that made the reservation.
     * @param origin The origin airport for the reservation.
     * @param destination The destination airport for the reservation.
     * @return The deleted reservation, or null if no reservation matched the criteria.
     * @throws IllegalArgumentException One of the passed parameters were null, or they matched more than one
     * reservation.
     */
    public static Reservation deleteReservation(String passenger, String origin, String destination) {
        if(passenger == null || origin == null || destination == null) {
            throw new IllegalArgumentException("All parameters must not be null.");
        }

        ArrayList<Reservation> matches = retrieveReservation(passenger, origin, destination);

        if(matches.size() == 1) {
            reservations.remove(matches.get(0));
            return matches.get(0);
        } else {
            throw new IllegalArgumentException("Provided parameters match multiple reservations.");
        }
    }

    /**
     * Determines equality between two {@link Reservation} instances.
     * @param obj The object to compare to the Reservation instance.
     * @return Whether or not the two objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Reservation)) {
            return false;
        }

        Reservation other = (Reservation)obj;
        return this.passenger.equals(other.passenger)
                && this.itinerary.getOrigin().equals(other.itinerary.getOrigin())
                && this.itinerary.getDestination().equals(other.itinerary.getDestination());
    }
}
