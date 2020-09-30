package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        storage.saveTasksListToFile(tasks.tasksList);
        ui.printGoodbyeMessage();
    }
}
