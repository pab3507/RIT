package perp.tree.stu;

import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ExpressionNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */

/**
 * A calculation represented by a unary operator and its operand.
 */
public class UnaryOperation implements ExpressionNode {

    public static final String NEG = "_"; //arithmetic negation operator
    public static List<String> OPERATORS; //Container of all legal unary operators, for use by parsers
    public static final String SQRT = "#"; //square root operator
    private String operator;
    private ExpressionNode expr;

    /**
     *Create a new UnaryOperation node.
     * @param operator the string rep. of the operation
     * @param expr the operand
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

    /**
     * Compute the result of evaluating the expression and applying the operator to it.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(SymbolTable symTab) {
        if(operator.equals(NEG)){
             return (expr.evaluate(symTab) * -1);
        }
        if(operator.equals(SQRT)){
             return (int)(Math.sqrt(expr.evaluate(symTab)));
         }
        return 0;
    }

    /**
     * Print, on standard output, the infixDisplay of the child nodes preceded by the operator and without
     * an intervening blank.
     */
    @Override
    public void infixDisplay() {
        System.out.println(operator + expr);
        expr.infixDisplay();
    }

    /**
     * Emit the Machine instructions necessary to perform the computation of this UnaryOperation. The operator itself is realized by an instruction that pops a value off the stack, applies the operator, and pushes the answer.
     * @return a list containing instructions for the expression and the instruction to perform the operation
     */
    @Override
    public List<Machine.Instruction> emit() {

        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.addAll(expr.emit());
        if(operator.equals(NEG)){
            InstructionList.add(new Machine.Negation());
        }
        else{
            InstructionList.add(new Machine.SquareRoot());
        }
        return InstructionList;
    }
}
