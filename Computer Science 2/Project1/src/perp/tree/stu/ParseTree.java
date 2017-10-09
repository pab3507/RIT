package perp.tree.stu;

import perp.Errors;
import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ActionNode;
import perp.tree.ExpressionNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Operations that are done on a Perp code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author YOUR NAME HERE
 */
public class ParseTree {

    public ActionSequence ActionSequenceList = new ActionSequence();


    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
        ArrayList<String> AssignmentList = new ArrayList<>();


        if(!program.get(0).equals("@") && !program.get(0).equals(":=")){
            Errors.error("Invalid Input", null);
        }
        else{
            for(int i = 0; i < program.size(); i++){
                if(program.get(0).equals("@") || program.get(0).equals(":=")){
                    ActionNode result = parseAction(program);
                    ActionSequenceList.addAction(result);
                    AssignmentList.clear();
                }
                AssignmentList.add(i, program.get(i));
            }

            }
        }


    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
        if (program.get(0).equals(":=")){
            String pop = program.remove(0);
            return new Assignment(pop, parseExpr(program));
        }
        else if(program.get(0).equals("@")){
            program.remove(0);
            return new Print(parseExpr(program));
        }
        return null;
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {

        if(program.get(0).matches( "^[a-zA-Z].*" )) {
            return new Variable(program.get(0));
        }
        else if(program.get(0).matches( "[-+]?\\d+" )){
            return new Constant(Integer.parseInt(program.get(0)));
        }
        else if (program.get(0).contains(BinaryOperation.ADD) || program.get(0).contains(BinaryOperation.SUB) ||
                program.get(0).contains(BinaryOperation.MUL) || program.get(0).contains(BinaryOperation.DIV)) {
            //program.remove(0);
            return new BinaryOperation(program.remove(0), parseExpr(program), parseExpr(program));
        }
        else if (program.get(0).contains(UnaryOperation.NEG) || program.get(0).contains(UnaryOperation.SQRT)) {
            //program.remove(0);
            return new UnaryOperation(program.remove(0), parseExpr(program));
        }
        return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see perp.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        ActionSequenceList.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see perp.tree.ActionNode#execute(perp.SymbolTable)
     */
    public void interpret() {
        SymbolTable symTab = new SymbolTable(); ActionSequenceList.execute(symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see perp.machine.stu.Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {

        return ActionSequenceList.emit();
    }

}
