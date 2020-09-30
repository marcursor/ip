package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.MissingDescriptionException;
import duke.exception.NoMatchingTasksException;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String keyword = fullCommand.replaceFirst("find", "").trim();

        try {
            TasksList foundTasks = new TasksList(tasks.findMatchingTasksInList(keyword));
            ui.printList(foundTasks);
        } catch (MissingDescriptionException e) {
            ui.printMissingKeywordMessage();
        } catch (NoMatchingTasksException e) {
            ui.printNoMatchingTasksMessage();
        }
    }
}
