public class DeleteReservationCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        if(args == null || args.length != 3) {
            return new String[]{"error,unknown request"};
        }

        try {
            Reservation.deleteReservation(args[0], args[1], args[2]);
        } catch(IllegalArgumentException e) {
            return new String[] {"error,reservation not found"};
        }

        return new String[] {"delete,successful"};
    }
}
