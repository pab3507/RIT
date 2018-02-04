package UI.Commands;

import java.util.Optional;

import Connection.Session;
import Reservation.*;
import Itinerary.*;

public class CreateReservationCommand implements Command {
    private Reservation reservation;

    public CreateReservationCommand() {}

    private CreateReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Undoes the creation of a reservation, removing it from the store.
     * @return A message indicating success.
     */
    @Override
    public String[] undo() {
        Reservation.removeReservation(reservation);
        return new String[]{String.format(
                "undo,reserve,%s,%s",
                reservation.getPassenger(),
                reservation.getItinerary().toString())};
    }

    /**
     * Restores an undone reservation creation.
     * @return A message indicating success.
     */
    @Override
    public String[] redo() {
        Reservation.addReservation(reservation);
        return new String[]{String.format(
                "redo,reserve,%s,%s",
                reservation.getPassenger(),
                reservation.getItinerary().toString())};
    }

    @Override
    public String[] execute(String[] args) {
        if(args == null || args.length != 3) {
            return new String[]{"error,unknown request"};
        }
        Session thisSession = Session.getConnectionHashMap().get(Integer.parseInt(args[0]));

        int id = Integer.valueOf(args[1]);
        String passenger = args[2];

        if(thisSession.getLastQueried() != null) {
            Optional<Itinerary> itinerary = thisSession.getLastQueried().stream()
                    .filter(i -> i.getId() == id)
                    .findFirst();

            if(itinerary.isPresent()) {
                try {
                    CreateReservationCommand cmd = new CreateReservationCommand(Reservation.createReservation
                            (itinerary.get(), passenger));
                    thisSession.getUndoStack().push(cmd);
                } catch(IllegalArgumentException e) {
                    return new String[]{"error,duplicate reservation"};
                }
                return new String[]{"reserve,successful"};
            } else {
                return new String[]{"error,invalid id"};
            }
        } else {
            return new String[]{"error, please query flights first"};
        }


    }
}
