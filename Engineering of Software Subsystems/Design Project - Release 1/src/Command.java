public interface Command {
    /**
     * Runs the command with the specified string arguments.
     * @param args The arguments passed to the command.
     * @return The strings to be printed by the UI.
     */
    String[] execute(String[] args);
}
