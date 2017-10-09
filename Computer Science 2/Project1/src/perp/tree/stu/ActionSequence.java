package perp.tree.stu;

import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ActionNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Sean Strout
 * Language: Java 8
 */

/**
 * An ActionNode used to string actions together into a sequence. An ActionSequence contains an ordered sequence of ActionNodes.
 */
public class ActionSequence implements ActionNode {

    private ArrayList<ActionNode> actionNodeArrayList = new ArrayList<>();

    /**
     * Initialize this instance as an empty sequence of ActionNode children.
     */
    public ActionSequence(){
    }

    /**
     * Add a child of this ActionSequence node.
     * @param newNode the node representing the action that will execute last
     */
    public void addAction(ActionNode newNode){
        actionNodeArrayList.add(newNode);
    }

    /**
     * Execute each ActionNode in this object, from first-added to last-added.
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(SymbolTable symTab) {
        for (ActionNode index : actionNodeArrayList){
            index.execute(symTab);
        }
    }


    @Override
    public void infixDisplay() {
        for (ActionNode index : actionNodeArrayList){
            index.infixDisplay();
        }
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> InstructionList = new LinkedList<>();
        for(ActionNode Node : actionNodeArrayList){
            InstructionList.addAll(Node.emit());
        }
        return InstructionList;
    }
}
