package poly.stu;

import java.util.ArrayList;
/**

 * @author Moisés Lora Pérez, mal3941@rit.edu
 */
public class PolyRoot {

    public static final double EPSILON = 0.0001	;
    public static final double INITIAL_GUESS = 0.1;
    public static final int MAX_ITERATIONS = 100;

    public static double computeRoot(ArrayList<Integer> poly){
        /**
         * Computes the root using a while loop that runs while the result is bigger than EPSILON and the number of
         * iterations is smaller than MAX_ITERATION. While the loop runs, it increases the number of iterations and
         * assigns the result of newtons method to the x1 variable. Then it evaluates x1 and assigns that value to result,
         * and x0 is assigned to the current value of x1. When the loop breaks, the value of the root is returned, x0.
         *
         */

        PolyEval.evaluate(poly,INITIAL_GUESS);
        double x0 = INITIAL_GUESS;
        double result = PolyEval.evaluate(poly,x0);
        int itcounter = 0;

        while (Math.abs(result) > EPSILON && itcounter < MAX_ITERATIONS){
            itcounter++;
            double x1 = x0 - PolyEval.evaluate(poly,x0)/ PolyEval.evaluate(PolyDerive.computeDerivative(poly),x0);
            result = PolyEval.evaluate(poly,x1);
            x0 = x1;

        }
        return x0;

    }


}
