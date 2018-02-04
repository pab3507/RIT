package UI.Commands;

import Reservation.Reservation;

import java.io.IOException;

public class ExitCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        try {
            Reservation.saveReservations();
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save reservations.");
            System.exit(1);
        }
        System.exit(0);
        return null;
    }
}
