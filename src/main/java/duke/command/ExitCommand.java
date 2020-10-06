package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Returns true to represent the command is an ExitCommand.
     *
     * @return a boolean true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves the current tasksList to an external file.
     * Prints the goodbye message.
     *
     * @param storage is the object saving the tasks to an external txt file
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        storage.saveTasksListToFile(tasks.tasksList);
        ui.printGoodbyeMessage();
    }
}
