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

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        ui.printInvalidCommandMessage();
    }
}
