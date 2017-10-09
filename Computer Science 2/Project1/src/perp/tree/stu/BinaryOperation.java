package perp.tree.stu;

import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ExpressionNode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */

/**
 * A calculation represented by a binary operator and its two operands.
 */
public class BinaryOperation implements ExpressionNode {

    private String operator; // the string representation of the operation
    private ExpressionNode leftChild; // the left operand
    private ExpressionNode rightChild; // the right operand
    public static final String ADD = "+"; // The operator symbol used for addition
    public static final String SUB = "-"; // The operator symbol used for subtraction
    public static final String DIV = "/"; // The operator symbol used for addition
    public static final String MUL = "*"; //The operator symbol used for multiplication
    public static final Collection<String> Operators = null; //Container of all legal binary operators, for use by parsers

    /**
     * Create a new BinaryOperation node.
     * @param operator the string representation of the operation
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        operator = this.operator;
        leftChild = this.leftChild;
        rightChild = this.rightChild;
    }

    /**
     * Compute the result of evaluating both operands and applying the operator to them.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return
     */
    @Override
    public int evaluate(SymbolTable symTab) {
            if(operator.equals(ADD)){
                return (leftChild.evaluate(symTab) + rightChild.evaluate(symTab));
            }
            if(operator.equals(SUB)){
                return (leftChild.evaluate(symTab) - rightChild.evaluate(symTab));
            }
            if(operator.equals(DIV)){
                return (leftChild.evaluate(symTab) / rightChild.evaluate(symTab));
            }
            if(operator.equals(MUL)){
                return (leftChild.evaluate(symTab) * rightChild.evaluate(symTab));
            }
        return 0;
        }

    /**
     * Print, on standard output, the infixDisplay of the two child nodes separated by the operator and surrounded by parentheses. Blanks are inserted throughout.
     */
    @Override
    public void infixDisplay() {
        System.out.println("(" + leftChild + operator + rightChild + ")");
    }

    /**
     * Emit the Machine instructions necessary to perform the computation of this BinaryOperation. The operator itself is realized by an instruction that pops two values off the stack, applies the operator, and pushes the answer.
     * @return
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.addAll(leftChild.emit());
        InstructionList.addAll(rightChild.emit());
        if(operator.equals(ADD)){
            InstructionList.add(new Machine.Add());
        }
        else if(operator.equals(DIV)){
            InstructionList.add(new Machine.Division());
        }
        else if(operator.equals(MUL)){
            InstructionList.add(new Machine.Multiplication());
        }
        else{
            InstructionList.add(new Machine.Subtraction());
        }
        return InstructionList;
    }
}
