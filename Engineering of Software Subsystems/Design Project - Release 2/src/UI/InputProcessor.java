package UI;

import Connection.Session;
import Connection.State.NotConnected;
import UI.Commands.Command;
import UI.Commands.ConnectionCommand;

import java.util.ArrayList;
import java.util.Collections;

class InputProcessor {
    private static final ArrayList<String> parts = new ArrayList<>();
    private static final Command connectCommand = new ConnectionCommand();

    static {
        Session.addConnection(0, new NotConnected());
    }

    static String[] handleInput(String input) {
        Collections.addAll(parts, input.split(","));
        String[] response;

        if(parts.get(parts.size() - 1).endsWith(";")) {
            String last = parts.get(parts.size() - 1);
            parts.set(
                    parts.size() - 1,
                    last.substring(0, last.length() - 1));
            if(parts.get(parts.size() - 1).equals("")) {
                parts.remove(parts.size() - 1);
            }
            String[] params = new String[parts.size() - 1];
            String command;
            if(parts.size() == 1) {
                command = parts.remove(0);
            } else {
                command = parts.remove(1);
            }
            parts.toArray(params);

            if(command.equals("connect")) {
                response = connectCommand.execute(params);
            } else if(command.equals("info")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response =
                            Session.getConnectionHashMap().get(Integer.parseInt(params[0])).FlightQuery(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).FlightQuery(params);
                }
            } else if(command.equals("disconnect")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response =
                            Session.getConnectionHashMap().get(Integer.parseInt(params[0])).disconnect(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).disconnect(params);
                }
            } else if(command.equals("reserve")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .createReservation(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).createReservation(params);
                }
            } else if(command.equals("retrieve")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .retrieveReservation(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).retrieveReservation(params);
                }
            } else if(command.equals("delete")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .deleteReservation(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).deleteReservation(params);
                }
            } else if(command.equals("airport")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .airportInformation(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).airportInformation(params);
                }
            } else if(command.equals("server")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .airportServer(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).airportInformation(params);
                }
            } else if(command.equals("undo")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .undo(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).undo(params);
                }
            } else if(command.equals("redo")) {
                if(Session.getConnectionHashMap().containsKey(Integer.parseInt(params[0]))) {
                    response = Session.getConnectionHashMap().get(Integer.parseInt(params[0]))
                            .redo(params);
                } else {
                    response = Session.getConnectionHashMap().get(0).redo(params);
                }
            } else {
                response = new String[]{"error,unknown command"};
            }

            parts.clear();
            return response;
        } else {
            return new String[]{"partial-request"};
        }
    }
}
