package UI.Commands;
import Connection.Session;
import Reservation.*;

public class DeleteReservationCommand implements Command {
    private Reservation reservation;

    public DeleteReservationCommand() {}

    private DeleteReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String[] undo() {
        Reservation.addReservation(reservation);
        return new String[]{String.format(
                "undo,delete,%s,%s",
                reservation.getPassenger(),
                reservation.getItinerary().toString())};
    }

    @Override
    public String[] redo() {
        Reservation.removeReservation(reservation);
        return new String[]{String.format(
                "redo,delete,%s,%s",
                reservation.getPassenger(),
                reservation.getItinerary().toString())};
    }

    @Override
    public String[] execute(String[] args) {
        if(args == null || args.length != 4) {
            return new String[]{"error,unknown request"};
        }

        try {
            DeleteReservationCommand cmd = new DeleteReservationCommand(Reservation.deleteReservation(args[1],
                    args[2], args[3]));
            Session session = Session.getConnectionHashMap().get(Integer.parseInt(args[0]));
            session.getUndoStack().push(cmd);
        } catch(IllegalArgumentException e) {
            return new String[] {"error,reservation not found"};
        }

        return new String[] {"delete,successful"};
    }
}
