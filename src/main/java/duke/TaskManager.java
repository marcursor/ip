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

public class TaskManager {
    private boolean isRunning;

    public TaskManager() {
        this.isRunning = true;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    private void stopRunningDuke() {
        isRunning = false;
    }

    public String[] processInput(String userInput) {
        return userInput.split(" ",2);
    }

    public void runCommand(String[] inputStringArray, ArrayList<Task> taskList) throws InvalidCommandException {
        String commandType = inputStringArray[0].toLowerCase();
        String commandDescription = "";
        Task newTaskToAdd;
        int arraySize = taskList.size();

        if (inputStringArray.length > 1) {
            commandDescription = inputStringArray[1];
        }

        switch (commandType) {
        case "bye":
            // exit program
            exitProgram();
            break;
        case "todo":
            // add a todo task
            try {
                newTaskToAdd = new Todo(commandDescription);
                addTaskToList(taskList, newTaskToAdd);
                FileManager.saveTaskListToFile(taskList);
            } catch (MissingDescriptionException e) {
                printMissingTodoDescriptionMessage();
            } catch (IOException e) {
                printIOExceptionMessage();
            }
            break;
        case "deadline":
            // add a deadline task
            try {
                String[] deadlineString = readTaskInput(commandType, commandDescription);
                String deadlineTask = deadlineString[0];
                String deadlineDate = deadlineString[1];

                newTaskToAdd = new Deadline(deadlineTask, deadlineDate);
                addTaskToList(taskList, newTaskToAdd);
                FileManager.saveTaskListToFile(taskList);
            } catch (MissingDescriptionOrDateException e) {
                printMissingDescriptionOrDateMessage();
            } catch (MissingDescriptionException e) {
                printMissingDeadlineDescriptionMessage();
            } catch (MissingDateException e) {
                printMissingDeadlineDateMessage();
            } catch (IOException e) {
                printIOExceptionMessage();
            }
            break;
        case "event":
            // add an event task
            try {
                String[] eventString = readTaskInput(commandType, commandDescription);
                String eventTask = eventString[0];
                String eventDate = eventString[1];

                newTaskToAdd = new Event(eventTask, eventDate);
                addTaskToList(taskList, newTaskToAdd);
                FileManager.saveTaskListToFile(taskList);
            } catch (MissingDescriptionOrDateException e) {
                printMissingDescriptionOrDateMessage();
            } catch (MissingDateException e) {
                printMissingEventDescriptionMessage();
            } catch (MissingDescriptionException e) {
                printMissingEventDateMessage();
            } catch (IOException e) {
                printIOExceptionMessage();
            }
            break;
        case "done":
            int doneTaskIndex = Integer.parseInt(commandDescription) - 1;

            try {
                markTaskAsDone(taskList, doneTaskIndex);
                FileManager.saveTaskListToFile(taskList);
            } catch (InvalidTaskException e) {
                printInvalidTaskCompleteMessage();
            } catch (IOException e) {
                printIOExceptionMessage();
            }
            break;
        case "delete":
            int taskToDeleteIndex = Integer.parseInt(commandDescription) - 1;

            deleteTaskFromList(taskList, taskToDeleteIndex);
            try {
                FileManager.saveTaskListToFile(taskList);
            } catch (IOException e) {
                printIOExceptionMessage();
            }
            break;
        case "list":
            // list the current tasks
            printList(taskList, arraySize);
            break;
        default:
            // unknown command error
            throw new InvalidCommandException();
        }
    }

    public void exitProgram() {
        stopRunningDuke();
    }

    public void addTaskToList(ArrayList<Task> taskList, Task newTaskToAdd) {
        int arraySize;

        taskList.add(newTaskToAdd);
        arraySize = taskList.size();
        printTaskAddedMessage(arraySize, newTaskToAdd);
    }

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

    public void markTaskAsDone(ArrayList<Task> taskList, int doneTaskIndex) throws InvalidTaskException {
        if (doneTaskIndex < taskList.size()) {
            Task doneTask = taskList.get(doneTaskIndex);
            doneTask.markAsDone();
            printTaskCompletedMessage(doneTask);
        } else {
            throw new InvalidTaskException();
        }
    }

    public void deleteTaskFromList(ArrayList<Task> taskList, Integer taskToDeleteIndex) {
        int arraySize;

        Task deletedTask = taskList.get(taskToDeleteIndex);
        taskList.remove(deletedTask);
        arraySize = taskList.size();
        printTaskDeletedMessage(arraySize, deletedTask);
    }

    public void printList(ArrayList<Task> taskList, int arraySize) {
        System.out.println("____________________________________________________________");
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < arraySize; i++) {
            System.out.print((i+1) + ".");
            System.out.println(taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void printTaskAddedMessage(int arraySize, Task newTaskToAdd) {
        System.out.println("____________________________________________________________");
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public void printTaskDeletedMessage(int arraySize, Task deletedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have removed the following from your list of tasks:");
        System.out.println(deletedTask);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public void printTaskCompletedMessage(Task doneTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have noted the completion of this task: ");
        System.out.printf("  [%s] %s%n", doneTask.getStatusIcon(), doneTask.description);
        System.out.println("____________________________________________________________");
    }

    public void printInvalidTaskCompleteMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("That task is not on the list.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingTodoDescriptionMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Pardon me, but the description of a todo task cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public static void printMissingDescriptionOrDateMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Excuse me sir, but you seem to have excluded the description or date.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingDeadlineDescriptionMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Pardon me, but the description of a deadline task cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingDeadlineDateMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("My humblest apologies, but there is no date for the deadline.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingEventDescriptionMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("My humblest apologies, but the event is missing a date.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingEventDateMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Pardon me, but the description of an event cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printIOExceptionMessage() {
        System.out.println("IO exception encountered when saving task list to file.");
    }
}
