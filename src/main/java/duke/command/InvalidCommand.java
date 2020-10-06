package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

/**
 * Represents a command not recognised by the Duke program.
 */
public class InvalidCommand extends Command {
    public InvalidCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Prints a message to inform the user if an invalid command was given as input.
     *
     * @param storage is not used in this command
     * @param tasks is not used in this command
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        ui.printInvalidCommandMessage();
    }
}
