package poly.stu;

import java.util.ArrayList;
/**

 * @author Moisés Lora Pérez, mal3941@rit.edu
 */
public class PolyDerive {
    /**
     *
     * @param poly Arraylist of coefficients of the polinomial
     * @return the derivative of the polinomial
     */
    public static ArrayList<Integer> computeDerivative(ArrayList<Integer> poly){
        /**
         * new arraylist for the derivative.
         * variable for the power of the polinomial.
         * For loop calculates the derivative using the power rule and adds it result to the new arraylist.
         * return: arraylist containing the derivative.
         */
        ArrayList<Integer> Derivatives = new ArrayList<Integer>();
        int power = 0;
        int result = 0;
        for(int coefficient:poly){
            if (power == 0){
                power++;
                continue;
            }
            result = coefficient * (power);
            power++;
            Derivatives.add(result);
        }
        return Derivatives;
    }
}
