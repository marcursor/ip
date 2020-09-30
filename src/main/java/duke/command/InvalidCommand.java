package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        ui.printInvalidCommandMessage();
    }
}
