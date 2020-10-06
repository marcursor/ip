package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingDescriptionOrDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add a task to the tasksList.
 */
public class AddCommand extends Command {
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Adds the task assigned to the command to the tasksList.
     * Saves the updated tasksList to an external txt file after adding the task.
     *
     * @param storage is the object saving the tasks to an external txt file
     * @param tasks is the object containing the tasksList
     * @param ui is the object printing all necessary messages
     */
    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        String[] commandStringArray = fullCommand.split(" ",2);
        String taskType = commandStringArray[0].toLowerCase();
        String taskDescription = "";
        Task newTaskToAdd;

        if (commandStringArray.length > 1) {
            taskDescription = commandStringArray[1];
        }

        switch(taskType) {
        case "todo": // add a todo task
            try {
                newTaskToAdd = new Todo(taskDescription);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionException e) {
                ui.printMissingTaskDescriptionMessage();
            }
            break;
        case "deadline": // add a deadline task
            try {
                String[] deadlineString = parseDate(taskType, taskDescription);
                String deadlineTask = deadlineString[0];
                String deadlineDate = deadlineString[1];

                newTaskToAdd = new Deadline(deadlineTask, deadlineDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionException e) {
                ui.printMissingTaskDescriptionMessage();
            } catch (MissingDateException e) {
                ui.printMissingDateMessage();
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            }
            break;
        case "event": // add an event task
            try {
                String[] eventString = parseDate(taskType, taskDescription);
                String eventTask = eventString[0];
                String eventDate = eventString[1];

                newTaskToAdd = new Event(eventTask, eventDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDateException e) {
                ui.printMissingTaskDescriptionMessage();
            } catch (MissingDescriptionException e) {
                ui.printMissingDateMessage();
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Checks user input for deadline or event tasks to ensure it has both a description and a date.
     *
     * @param commandType string describing the task type
     * @param commandDescription string to be checked for description and date
     * @return a string array containing the task description and date
     * @throws MissingDescriptionOrDateException if either the description or date is missing
     */
    public String[] parseDate(String commandType, String commandDescription) throws MissingDescriptionOrDateException {
        String[] descriptionAndDate = new String[2];

        switch (commandType) {
        case "deadline":
            String[] deadlineString = commandDescription.split("/by");
            if (deadlineString.length < 2 ) {
                throw new MissingDescriptionOrDateException();
            }
            descriptionAndDate = deadlineString;
            break;
        case "event":
            String[] eventString = commandDescription.split("/at");
            if (eventString.length < 2 ) {
                throw new MissingDescriptionOrDateException();
            }
            descriptionAndDate = eventString;
            break;
        default:
            break;
        }

        return descriptionAndDate;
    }
}
