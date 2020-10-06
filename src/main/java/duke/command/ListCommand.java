package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

/**
 * Represents a command to list the current tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Lists all current tasks in the tasksList.
     *
     * @param storage not used in this command
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
