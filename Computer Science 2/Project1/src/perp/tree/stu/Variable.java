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
 * The ExpressionNode for a simple variable
 */
public class Variable implements ExpressionNode {

    private String name;

    /**
     * Set the name of this new Variable.
     * @param name the name of this variable
     */
    public Variable(String name){
        this.name = name;
    }

    /**
     * Evaluate a variable by fetching its value
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this variable's current value in the symbol table
     */
    @Override
    public int evaluate(SymbolTable symTab) {
        return symTab.get(name);
    }

    /**
     * Print on standard output the Variable's name.
     */
    @Override
    public void infixDisplay() {
        System.out.println(name);

    }

    /**
     * Emit a LOAD instruction that pushes the Variable's value onto the stack.
     * @return a list containing a single LOAD instruction
     */
    @Override
    public List<Machine.Instruction> emit() {

        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.add(new Machine.Load(name));
        return InstructionList;
    }
}
