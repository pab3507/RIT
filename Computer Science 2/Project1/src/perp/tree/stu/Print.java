package perp.tree.stu;

import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ActionNode;
import perp.tree.ExpressionNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class Print implements ActionNode {

    private ExpressionNode printee;

    public Print(ExpressionNode printee){
        this.printee = printee;
    }
    @Override
    public void execute(SymbolTable symTab) {
        System.out.println("=== " + printee.evaluate(symTab));
    }

    @Override
    public void infixDisplay() {
        System.out.print("Print");
        printee.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.addAll(printee.emit());
        InstructionList.add(new Machine.Print());
        return InstructionList;

    }
}
