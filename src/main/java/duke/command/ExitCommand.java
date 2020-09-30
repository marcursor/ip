package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

/**
 * Saves the current tasks to an external txt file and prints the goodbye message.
 */
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
