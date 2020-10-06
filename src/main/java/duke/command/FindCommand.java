package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.MissingDescriptionException;
import duke.exception.NoMatchingTasksException;

/**
 * Represents a command to find tasks matching a given keyword in the tasksList.
 */
public class FindCommand extends Command {
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Finds all tasks in the tasksList that contain the given keyword.
     *
     * @param storage is not used in this command
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
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
