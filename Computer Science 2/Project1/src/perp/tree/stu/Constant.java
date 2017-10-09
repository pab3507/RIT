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
 * An expression node representing a constant, i.e., literal value
 */
public class Constant implements ExpressionNode {

    private int value;

    /**
     * Store the integer value in this new Constant.
     * @param value  the integer it will hold
     */
    public Constant (int value){
        this.value = value;

    }

    /**
     * Evaluate the constant
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this Constant's value
     */
    @Override
    public int evaluate(SymbolTable symTab) {
        return value;
    }

    /**
     * Print this Constant's value on standard output.
     */
    @Override
    public void infixDisplay() {
        System.out.print(value);
    }

    /**
     * Emit an instruction to push the value onto the stack.
     * @return a list containing that one instruction
     */
    @Override
    public List<Machine.Instruction> emit() {

        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.add(new Machine.pushConstant(value));
        return InstructionList;

    }
}
