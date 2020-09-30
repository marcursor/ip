package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.InvalidTaskException;

//import java.io.IOException;

public class DoneCommand extends Command {
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String commandDescription = fullCommand.replace("done", "").trim();

        int doneTaskIndex = Integer.parseInt(commandDescription) - 1;

        try {
            tasks.markTaskAsDone(doneTaskIndex, ui);
            storage.saveTasksListToFile(tasks.tasksList);
        } catch (InvalidTaskException e) {
            ui.printInvalidTaskCompleteMessage();
        }
    }
}
