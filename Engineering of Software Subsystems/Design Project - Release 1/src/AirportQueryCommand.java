import java.io.*;
import java.util.*;
import java.lang.*;

/** Function: This class is a command in the command pattern for the airport query
 *            Acts as a concCommand in the command pattern
 * Created: 10/17/2017
 * Language Java 1.8 Level 8
 * @author Niharika Reddy (nxr4929@g.rit.edu)
 **/
public class AirportQueryCommand implements Command {

    private int looper;
    private HashMap<String,AirportInfo> airportInfoHashMap = new HashMap<>();

    public AirportQueryCommand() {
        looper = 0;
    }

    /**
     * Function: Execute method to either raise an error or to return airport information
     *           to AFRS
     * Parameters: (String[] args): airport code passes in as argument from AFRS
     * Return: String []: either error message, or string of airport information
     **/
    @Override
    public String[] execute(String[] args) {

        String airportcode = args[0].toUpperCase();
        if (this.airportInfoHashMap.size() == 0) {
            try {
                File f1 = new File("data/airports");
                Scanner airportfile = new Scanner(f1);

                File f2 = new File("data/weather");
                Scanner weatherfile = new Scanner(f2);

                File f3 = new File("data/delay");
                Scanner delayfile = new Scanner(f3);

                while (airportfile.hasNextLine() && weatherfile.hasNextLine() && delayfile.hasNextLine()) {
                    ArrayList<String> weather = new ArrayList<>();
                    ArrayList<String> temp = new ArrayList<>();
                    String l1 = airportfile.nextLine();
                    String[] airportfields = l1.split(",");

                    String l2 = weatherfile.nextLine();
                    String[] weatherfields = l2.split(",");
                    int a = 0;
                    for (String i : weatherfields) {
                        if (a != 0) {
                            if (a % 2 == 0) {
                                temp.add(i);
                            } else {
                                weather.add(i);
                            }
                        }

                        a++;
                    }

                    String l3 = delayfile.nextLine();
                    String[] delayfields = l3.split(",");

                    airportInfoHashMap.put(airportfields[0], new AirportInfo(airportfields[0], airportfields[1],
                            weather, temp, Integer.parseInt(delayfields[1])));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (args.length < 1 || args.length > 1) {
            String r = "error,unknown request";
            return r.split("\n");
        }

        if (!airportInfoHashMap.containsKey(args[0])) {
            String t = "error,unknown airport";
            return t.split("\n");
        } else {
//            System.out.println("-----------------------------------");
            AirportInfo a = (airportInfoHashMap.get(args[0]));
            String s = "airport," + a.getName() + "," + a.getWeather(looper) + "," + String.valueOf(a.getDelay()) + '\n';
            looper++;
            return s.split("\n");
        }

    }
}


