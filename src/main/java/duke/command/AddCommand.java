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
 * Adds a task to the tasksList.
 */
public class AddCommand extends Command {
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

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
        case "todo":
            // add a todo task
            try {
                newTaskToAdd = new Todo(taskDescription);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionException e) {
                ui.printMissingTodoDescriptionMessage();
            }
            break;
        case "deadline":
            // add a deadline task
            try {
                String[] deadlineString = parseDate(taskType, taskDescription);
                String deadlineTask = deadlineString[0];
                String deadlineDate = deadlineString[1];

                newTaskToAdd = new Deadline(deadlineTask, deadlineDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionException e) {
                ui.printMissingDeadlineDescriptionMessage();
            } catch (MissingDateException e) {
                ui.printMissingDeadlineDateMessage();
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            }
            break;
        case "event":
            // add an event task
            try {
                String[] eventString = parseDate(taskType, taskDescription);;
                String eventTask = eventString[0];
                String eventDate = eventString[1];

                newTaskToAdd = new Event(eventTask, eventDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDateException e) {
                ui.printMissingEventDescriptionMessage();
            } catch (MissingDescriptionException e) {
                ui.printMissingEventDateMessage();
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            }
            break;
        default:
            break;
        }
    }

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
