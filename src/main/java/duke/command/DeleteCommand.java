package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidTaskException;

/**
 * Represents a command to delete a task in the tasksList.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes a given task from the tasksList.
     * The task to be deleted is indicated by an integer corresponding to its index in the list.
     * Saves the updated tasksList to an external txt file after deleting the task.
     *
     * @param storage is the object saving the tasks to an external txt file
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String commandDescription = fullCommand.replace("delete", "").trim();

        try {
            int taskToDeleteIndex = Integer.parseInt(commandDescription) - 1;

            tasks.deleteTaskFromList(taskToDeleteIndex, ui);
            storage.saveTasksListToFile(tasks.tasksList);
        } catch (InvalidTaskException e) {
            ui.printInvalidTaskIndexMessage();
        } catch (NumberFormatException e) {
            ui.printInvalidTaskIndexMessage();
        }
    }
}
