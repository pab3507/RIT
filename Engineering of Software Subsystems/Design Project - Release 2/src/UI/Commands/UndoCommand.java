package UI.Commands;

import Connection.Session;

import java.util.Stack;

/**
 * This command holds the undo logic
 */
public class UndoCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        Stack<Command> redoStack = Session.getConnectionHashMap().get(Integer.parseInt(args[0])).getRedoStack();
        Stack<Command> undoStack = Session.getConnectionHashMap().get(Integer.parseInt(args[0])).getUndoStack();

        String[] ret;
        if(!undoStack.empty()) {
            Command cmd = undoStack.pop();
            ret = cmd.undo();
            redoStack.push(cmd);
        } else {
            ret = new String[0];
        }

        return ret;
    }
}
