/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class Woolie extends Thread{

    private String name;
    private int crossTime;
    private String destination;
    private TrollsBridge bridgeGuard;

    public Woolie(String name, int crossTime, String destination, TrollsBridge bridgeGuard){
        this.name = name;
        this.crossTime = crossTime;
        this.destination = destination;
        this.bridgeGuard = bridgeGuard;
    }

    public synchronized void run() {
        bridgeGuard.enterBridgePlease(this);
        System.out.println(this.name + " is starting to cross");
        for (int i = 1; i <= crossTime; i++) {
            try {
                sleep(1000);
                System.out.println(this.name + " " + i + " seconds");
            } catch (InterruptedException e) {
                System.out.println("Sleep Interrupted");
            }
        }
        bridgeGuard.leave();
        System.out.println(this.name + " leaves at " + this.destination);
    }
}


