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
public class Assignment implements ActionNode{

    private String ident;
    private ExpressionNode rhs;

    public Assignment(String ident, ExpressionNode rhs) {
        this.ident = ident;
        this.rhs = rhs;
    }

    @Override
    public void execute(SymbolTable symTab) {
        int result = rhs.evaluate(symTab);
        symTab.put(ident,result);
    }

    @Override
    public void infixDisplay() {
        System.out.println(ident + ":=");
        rhs.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> InstructionList = new LinkedList<Machine.Instruction>();
        InstructionList.addAll(rhs.emit());
        InstructionList.add(new Machine.Store(ident));
        return InstructionList;
    }
}
