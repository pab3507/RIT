package poly.stu;

import java.util.ArrayList;
/**

 * @author Moisés Lora Pérez, mal3941@rit.edu
 */
public class PolyEval {
    public static double evaluate(ArrayList<Integer> poly, double x){
        /**
         * for loops through the polynomials arraylist and adds the result of the evaluation to a variable
         */

        int power = 0;
        double result = 0;
        for(int coefficient:poly){
            result += coefficient * Math.pow(x,power);
            power++;
        }
        return result;

    }
    public static boolean isZero(ArrayList<Integer> poly){
        /**
         *  for loops through the polynomials arraylist and checks if the sum of the coefficients is zero to check
         *  whether it's a zero polynomial.
         */
        int sumres = 0;
        boolean isZeroP = false;
        for(int coefficient:poly){
            sumres += coefficient;
            if (sumres == 0) {
                isZeroP = true;
            }
        }
        return isZeroP;
    }
}
