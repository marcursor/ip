package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidTaskException;

/**
 * Deletes a task in the tasksList.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

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
