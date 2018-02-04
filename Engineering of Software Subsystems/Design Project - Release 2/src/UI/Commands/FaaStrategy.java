package UI.Commands;

import AirportInfo.AirportInfo;

/**
 * This defines the method for the faa server strategy.
 * Language: Java 1.8 level 8
 * Created: 11/6/17
 * @author Stephen Cook(sjc5897.rit.edu)
 */
public class FaaStrategy implements ServerStrategy {
    public String[] run(String[] args) {
        AirportInfo a = new AirportInfo();
        return a.request(args[1]);
    }
}
