package Reservation;

import Itinerary.Itinerary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Reservation {
    private static ArrayList<Reservation> reservations;
    private Itinerary itinerary;
    private String passenger;
    private static final String RESERVATIONS_PATH = "data/reservations";

    static {
        try(FileReader reader = new FileReader(RESERVATIONS_PATH)) {
            loadReservations(reader);
        } catch(IOException e) {
            // file does not exist
            reservations = new ArrayList<Reservation>();
        }
    }

    /**
     * Creates an empty {@link Reservation}, used for JSON deserialization.
     */
    public Reservation() {

    }

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
     * @param origin The origin airport for the reservation.@param destination The destination airport for the reservation.
     *
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
     * Adds a reservation to the reservation list
     * @param reservation The reservation to add.
     */
    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Removes a reservation from the reservation list.
     * @param reservation The reservation to remove.
     */
    public static void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    /**
     * Load persisted reservations from the stream represented by reader.
     * @param reader The reader to load the reservations from.
     */
    public static void loadReservations(Reader reader) {
        Reservation[] temp = new Gson().fromJson(reader, Reservation[].class);
        reservations = new ArrayList<Reservation>(Arrays.asList(temp));
    }

    /**
     * Saves reservations to disk.
     * @throws IOException If an IO issue happens.
     */
    public static void saveReservations() throws IOException {
        String json = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(reservations);
        try(FileWriter writer = new FileWriter(RESERVATIONS_PATH)) {
            writer.write(json);
        }
    }

    /**
     * Determines equality between two {@link Reservation} instances.
     * @param obj The object to compare to the Reservation.Reservation instance.
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
