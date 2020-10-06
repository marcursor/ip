package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidTaskException;

/**
 * Marks a task in the tasksList as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Marks a given task from the tasksList as done.
     * The task to be marked done is indicated by an integer corresponding to its index in the list.
     * Saves the updated tasksList to an external txt file after marking task as done.
     *
     * @param storage is the object saving the tasks to an external txt file
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String commandDescription = fullCommand.replace("done", "").trim();

        try {
            int doneTaskIndex = Integer.parseInt(commandDescription) - 1;
            tasks.markTaskAsDone(doneTaskIndex, ui);
            storage.saveTasksListToFile(tasks.tasksList);
        } catch (InvalidTaskException e) {
            ui.printInvalidTaskIndexMessage();
        } catch (NumberFormatException e) {
            ui.printInvalidTaskIndexMessage();
        }
    }
}
