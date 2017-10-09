import java.util.LinkedList;
import java.util.Queue;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */


public class TrollsBridge {

    private final int max; //max people allowed on the bridge
    private int numOnBridge; //current people on the bridge
    private LinkedList<Woolie> waitList = new LinkedList<>(); //waitlist for woolies
    public TrollsBridge(int max){
        this.max = max;
    }

    public synchronized void enterBridgePlease(Woolie thisWoolie){
        waitList.add(thisWoolie);//add them to waitlist
        while(this.numOnBridge >= this.max && (!waitList.get(0).equals(thisWoolie))){

            try {
                thisWoolie.wait(); //have them wait to enter
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitList.remove(); //remove them from waitlist
            this.numOnBridge++; //increase the bridge number
        }
    }

    public synchronized void leave(){
        this.numOnBridge--; //decrease bridge number
        this.notifyAll(); //notify for threads

    }

}
