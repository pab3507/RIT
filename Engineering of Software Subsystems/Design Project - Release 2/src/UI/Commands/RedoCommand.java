package UI.Commands;

import Connection.Session;

import java.util.Stack;

/**
 * This is the command used to hold the redo logic
 */
public class RedoCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        Stack<Command> redoStack = Session.getConnectionHashMap().get(Integer.parseInt(args[0])).getRedoStack();
        Stack<Command> undoStack = Session.getConnectionHashMap().get(Integer.parseInt(args[0])).getUndoStack();

        String[] ret;
        if(!redoStack.empty()) {
            Command cmd = redoStack.pop();
            ret = cmd.redo();
            undoStack.push(cmd);
        } else {
            ret = new String[0];
        }

        return ret;
    }
}
