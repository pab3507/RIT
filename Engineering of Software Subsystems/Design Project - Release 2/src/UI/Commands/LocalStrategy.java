package UI.Commands;

import AirportInfo.AirportInfo;
import Connection.Session;

import java.nio.channels.SeekableByteChannel;
/**
 * This defines the method for the local server strategy.
 * Language: Java 1.8 level 8
 * Created: 11/6/17
 * @author Stephen Cook(sjc5897.rit.edu)
 */
public class LocalStrategy implements ServerStrategy {

    public String[] run(String[] args){
        String airportcode = args[1];
        String s;

        if (Session.getConnectionHashMap().containsKey(Integer.parseInt(args[0]))) {
            Session thisSession = Session.getConnectionHashMap().get(Integer.parseInt(args[0]));
            int looper = thisSession.getLooper();
            Integer times = looper;
            AirportInfo a = (AirportInfo.getAirportInfo().get(airportcode));
            s = "airport," + a.getName() + "," + a.getWeather(times) + "," + String.valueOf(a.getDelay()) + '\n';
            thisSession.addLooper();
        }
        else{
            s = "error,invalid connection id";
        }
        return s.split("\n");
    }
}
