
import poly.stu.PolyDerive;
import poly.stu.PolyEval;
//import poly.stu.PolyRoot;

import poly.stu.PolyRoot;
import poly.stu.PolyString;

import java.util.ArrayList;
import java.util.Scanner;

/**

 * @author Moisés Lora Pérez, mal3941@rit.edu
 */
public class PolyMain {    
    /**
     * The main method:
     * <p>
     * <pre>
     * 1. reads the polynomial from the command line and displays it.
     * 2. prompts the user for a value of x.
     * 3. evaluates the polynomial with the supplied value of x and
     * displays the result of the evaluation.
     * 4. compute/display the derivative of the polynomial.
     * 5. compute/display the root of the polynomial, using Newton's method,
     * if one exists.
     * </pre>
     * </p>
     * 
     * @param args The polynomial, in reverse order of terms, whose
     *  coefficients are integers.
     */
    public static void main(String[] args) {
        // check for one or more command line arguments
        if (args.length == 0) { 
            System.out.println("Usage: java Poly term0 ...");
            return;
        }

        // read the polynomial in from the command line
        ArrayList<Integer> poly = new ArrayList<Integer>();
        for (String val : args) {
            poly.add(Integer.parseInt(val));
        }
        
        // pretty print the polynomial
        System.out.println("f(x) = " + PolyString.getString(poly));

        // get a value for x and evaluate the polynomial with it
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x: ");
        double x = in.nextDouble();
        System.out.println("f(" + x + ") = " + PolyEval.evaluate(poly, x));
        in.close();



        // get the derivative of the polynomial and pretty print it
        ArrayList<Integer> deriv = PolyDerive.computeDerivative(poly);
        System.out.println("f'(x) = " + PolyString.getString(deriv));


        if (PolyEval.isZero(deriv)) {
            System.out.println("Root: none exist");
        } else {
            System.out.println("Root: " + PolyRoot.computeRoot(poly));
        }

    }
}
