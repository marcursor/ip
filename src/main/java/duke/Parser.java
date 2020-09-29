package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingDescriptionOrDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.IOException;

/**
 *
 */
public class Parser {
    public Parser() {
    }

    /**
     *
     * @param userInput
     * @return
     */
    public String[] processInput(String userInput) {
        return userInput.split(" ",2);
    }

    /**
     *
     * @param inputStringArray
     * @param tasks
     * @param ui
     * @param storage
     * @throws InvalidCommandException
     */
    public void runCommand(String[] inputStringArray, TasksList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        String commandType = inputStringArray[0].toLowerCase();
        String commandDescription = "";
        Task newTaskToAdd;
        int arraySize = tasks.arraySize;

        if (inputStringArray.length > 1) {
            commandDescription = inputStringArray[1];
        }

        switch (commandType) {
        case "bye":
            // exit program
            ui.exitProgram();
            break;
        case "todo":
            // add a todo task
            try {
                newTaskToAdd = new Todo(commandDescription);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionException e) {
                ui.printMissingTodoDescriptionMessage();
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            }
            break;
        case "deadline":
            // add a deadline task
            try {
                String[] deadlineString = readTaskInput(commandType, commandDescription);
                String deadlineTask = deadlineString[0];
                String deadlineDate = deadlineString[1];

                newTaskToAdd = new Deadline(deadlineTask, deadlineDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            } catch (MissingDescriptionException e) {
                ui.printMissingDeadlineDescriptionMessage();
            } catch (MissingDateException e) {
                ui.printMissingDeadlineDateMessage();
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            }
            break;
        case "event":
            // add an event task
            try {
                String[] eventString = readTaskInput(commandType, commandDescription);
                String eventTask = eventString[0];
                String eventDate = eventString[1];

                newTaskToAdd = new Event(eventTask, eventDate);
                tasks.addTaskToList(newTaskToAdd, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (MissingDescriptionOrDateException e) {
                ui.printMissingDescriptionOrDateMessage();
            } catch (MissingDateException e) {
                ui.printMissingEventDescriptionMessage();
            } catch (MissingDescriptionException e) {
                ui.printMissingEventDateMessage();
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            }
            break;
        case "done":
            int doneTaskIndex = Integer.parseInt(commandDescription) - 1;

            try {
                tasks.markTaskAsDone(doneTaskIndex, ui);
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (InvalidTaskException e) {
                ui.printInvalidTaskCompleteMessage();
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            }
            break;
        case "delete":
            int taskToDeleteIndex = Integer.parseInt(commandDescription) - 1;

            tasks.deleteTaskFromList(taskToDeleteIndex, ui);
            try {
                storage.saveTasksListToFile(tasks.tasksList);
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            }
            break;
        case "list":
            // list the current tasks
            ui.printList(tasks.tasksList, arraySize);
            break;
        default:
            // unknown command error
            throw new InvalidCommandException();
        }
    }

    public void parseInput(String input, TasksList tasks, Ui ui, Storage storage) throws InvalidCommandException {
            String[] processedInput = processInput(input);
            runCommand(processedInput, tasks, ui, storage);
    }

    /**
     *
     * @param commandType
     * @param commandDescription
     * @return
     * @throws MissingDescriptionOrDateException
     */
    public String[] readTaskInput(String commandType, String commandDescription) throws MissingDescriptionOrDateException {
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
