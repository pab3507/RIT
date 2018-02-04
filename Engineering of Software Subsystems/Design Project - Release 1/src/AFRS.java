import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class AFRS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> parts = new ArrayList<>();
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("info", new FlightQueryCommand());
        commands.put("reserve", new CreateReservationCommand());
        commands.put("retrieve", new RetrieveReservationCommand());
        commands.put("delete", new DeleteReservationCommand());
        commands.put("airport", new AirportQueryCommand());
        commands.put("exit", new ExitCommand());

        while(in.hasNextLine()) {
            Collections.addAll(parts, in.nextLine().split(","));
            if(parts.get(parts.size() - 1).endsWith(";")) {
                if(commands.containsKey(parts.get(0))) {
                    String last = parts.get(parts.size() - 1);
                    parts.set(
                            parts.size() - 1,
                            last.substring(0, last.length() - 1));
                    if (parts.get(parts.size()-1).equals("")){
                        parts.remove(parts.size()-1);
                    }
                    String[] params = new String[parts.size() - 1];
                    parts.subList(1, parts.size()).toArray(params);
                    for(String line : commands.get(parts.get(0)).execute(params)) {
                        System.out.println(line);
                    }
                } else {
                    System.out.println("error,unknown request");
                }
                parts.clear();
            } else {
                System.out.println("partial-request");
            }
        }
    }
}
