package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidExecutionException;

/**
 * Represents an executable command.
 */
public class Command {
    protected String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TasksList tasks, Ui ui) throws InvalidExecutionException {
        throw new InvalidExecutionException();
    }
}
