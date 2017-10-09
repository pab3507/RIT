/**

 * @author Moisés Lora Pérez, mal3941@rit.edu
 */
import turtle.Turtle;
import turtle.StdDraw;
public class HTree {
    /**
     * Program contains the correct implementation of the Python Htree code converted to the correct Java Syntax.
     */
    private static int MAX_SEGMENT_LENGTH = 1024;

    public static Turtle init(double length, double depth){
        Turtle myTurtle = new Turtle(0,0,0);
        myTurtle.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
        myTurtle.setCanvasTitle("H-Tree, depth: " + (depth));
        return myTurtle;
    }
    public static void drawHTree(Turtle myTurtle, double length, double depth) {
        if (depth > 0) {
            myTurtle.goForward(length / 2);
            myTurtle.turnLeft(90);
            myTurtle.goForward(length / 2);
            myTurtle.turnRight(90);

            drawHTree(myTurtle, length / 2, depth - 1);
            myTurtle.turnRight(90);
            myTurtle.goForward(length);
            myTurtle.turnLeft(90);
            drawHTree(myTurtle, length / 2, depth - 1);

            myTurtle.turnLeft(90);
            myTurtle.goForward(length / 2);
            myTurtle.turnLeft(90);
            myTurtle.goForward(length);
            myTurtle.turnRight(90);
            myTurtle.goForward(length / 2);
            myTurtle.turnRight(90);

            drawHTree(myTurtle, length / 2, depth - 1);

            myTurtle.turnRight(90);
            myTurtle.goForward(length);
            myTurtle.turnLeft(90);

            drawHTree(myTurtle, length / 2, depth - 1);

            myTurtle.turnLeft(90);
            myTurtle.goForward(length / 2);
            myTurtle.turnRight(90);
            myTurtle.goForward(length / 2);
        }
    }
    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("Usage: java HTree #");
        }
        else {
            int depth = Integer.parseInt(args[0]);
            if (depth < 0){
                System.out.println("The depth must be greater than or equal to 0");
            }
            else{
                Turtle myTurtle = init(MAX_SEGMENT_LENGTH, depth);
                drawHTree(myTurtle, MAX_SEGMENT_LENGTH, depth);
            }
    }
    }

}