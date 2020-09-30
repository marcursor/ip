package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String commandDescription = fullCommand.replace("delete", "").trim();

        int taskToDeleteIndex = Integer.parseInt(commandDescription) - 1;

        try {
            tasks.deleteTaskFromList(taskToDeleteIndex, ui);
            storage.saveTasksListToFile(tasks.tasksList);
        } catch (InvalidTaskException e) {
            ui.printInvalidTaskCompleteMessage();
        }
    }
}
