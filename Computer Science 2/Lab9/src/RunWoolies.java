/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 * <p>
 * Note: java -enableassertions should cause Woolies to validate their side.
 * </p>
 *
 * @author Ben Steele
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class RunWoolies {

    /**
     * SIDE_ONE is Merctran.
     */
    public final static String SIDE_ONE = "Merctran";

    /**
     * SIDE_TWO is Sicstine.
     */
    public final static String SIDE_TWO = "Sicstine";

    /**
     * Command interface for collecting all the functions in this test suite.
     * Single method is Command.execute().
     */
    private interface Command {
        public void execute();
    }

    /**
     * testSuite is the list of test cases.
     */
    private static Command[] testSuite = {
            new Command() {
                public void execute() {
                    RunWoolies.test0();
                }
            },
            new Command() {
                public void execute() {
                    RunWoolies.test1();
                }
            },
            new Command() {
                public void execute() {
                    RunWoolies.test2();
                }
            },
            new Command() {
                public void execute() {
                    RunWoolies.test3();
                }
            },
    };

    /**
     * TEST_COUNT is number of test cases.
     */
    public final static int TEST_COUNT = testSuite.length;

    /**
     * test0 is Test Scenario 0, an extremely simple, non-waiting test.
     * test0 provides an example template/pattern for writing a test case.
     */
    static void test0() {

        System.out.println("Begin test0. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        // Set an optional, test delay to stagger the start of each woolie.
        int delay = 4000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 4, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                init.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        System.out.println("\n=============================== End test0.");
        return;
    }

    /**
     * test1 is Test Scenario 1, another fairly simple simulation run.
     * test1 provides another example for writing a test case.
     */
    static void test1() {

        System.out.println("Begin test1. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 2, SIDE_ONE, trollBridge),
                new Woolie("Cathy", 2, SIDE_TWO, trollBridge),
                new Woolie("Doris", 3, SIDE_TWO, trollBridge),
                new Woolie("Edith", 3, SIDE_ONE, trollBridge),
                new Woolie("Fred", 2, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                init.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }

        System.out.println("\n=============================== End test1.");
    }

    /**
     * TODO: write YOUR test case here.
     */
    static void test2() {

        System.out.println("Begin test2. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        System.out.println("TODO: write a more involved test here.");
        //
        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);
        // Set an OPTIONAL, test delay to stagger the start of each woolie.
        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Alf", 7, SIDE_ONE, trollBridge),
                new Woolie("Bev", 4, SIDE_ONE, trollBridge),
                new Woolie("Cal", 6, SIDE_TWO, trollBridge),
                new Woolie("Deb", 3, SIDE_TWO, trollBridge),
                new Woolie("Eli", 3, SIDE_ONE, trollBridge),
                new Woolie("Fay", 2, SIDE_TWO, trollBridge),
                new Woolie("Gia", 4, SIDE_TWO, trollBridge),
                new Woolie("Hal", 3, SIDE_TWO, trollBridge),
                new Woolie("Ira", 3, SIDE_TWO, trollBridge),
                new Woolie("Kim", 2, SIDE_TWO, trollBridge),
        };

        // Create the Woolies and store them in an array.
        // Run them by calling their start() method.

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                init.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test2.");
    }

    /**
     * TODO: write YOUR second test case here.
     */
    static void test3() {

        System.out.println("Begin test3. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies
        //
        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);
        // Set an OPTIONAL, test delay to stagger the start of each woolie.
        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Alf", 15, SIDE_ONE, trollBridge),
                new Woolie("Bev", 21, SIDE_ONE, trollBridge),
                new Woolie("Cal", 43, SIDE_TWO, trollBridge),
                new Woolie("Deb", 7, SIDE_TWO, trollBridge),
                new Woolie("Eli", 12, SIDE_ONE, trollBridge),
                new Woolie("Fay", 23, SIDE_TWO, trollBridge),
                new Woolie("Gia", 26, SIDE_TWO, trollBridge),
                new Woolie("Hal", 34, SIDE_TWO, trollBridge),
                new Woolie("Ira", 17, SIDE_TWO, trollBridge),
                new Woolie("Kim", 25, SIDE_TWO, trollBridge),
        };

        // Create the Woolies and store them in an array.
        // Run them by calling their start() method.

        for (int j = 0; j < peds.length; ++j) {
            //Run them by calling their start() method.
            try{
                peds[j].start();
                init.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {

                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test3.");

    }

    /**
     * Run all the tests in this test suite.
     *
     * @param args not used
     */
    public static void main(String args[]) {

        for (int j = 0; j < TEST_COUNT; ++j) {
            testSuite[j].execute();
        }
    }

}

