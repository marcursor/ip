package duke;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private boolean isRunning;

    private final Parser parser = new Parser();

    public Ui() {
        this.isRunning = true;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    private void stopRunningDuke() {
        isRunning = false;
    }

    public void exitProgram() {
        stopRunningDuke();
    }

    public void handleUserInput(TasksList tasks) {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (getIsRunning()) {
            userInput = in.nextLine();
            try {
                parser.parseInput(userInput, tasks, this);
            } catch (InvalidCommandException e) {
                printInvalidCommandMessage();
            }
        }
    }

    public void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");
    }

    public void printTasksListsLoadedMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Existing tasklist loaded.");
        System.out.println("____________________________________________________________");
    }

    public void printLoadingErrorMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("No existing tasklist found, new tasklist created.");
        System.out.println("____________________________________________________________");
    }

    public void printInvalidCommandMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("My humblest apologies, I do not know what that means.");
        System.out.println("____________________________________________________________");
    }

    public void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }

    public void printTaskAddedMessage(int arraySize, Task newTaskToAdd) {
        System.out.println("____________________________________________________________");
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public void printTaskCompletedMessage(Task doneTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have noted the completion of this task: ");
        System.out.printf("  [%s] %s%n", doneTask.getStatusIcon(), doneTask.description);
        System.out.println("____________________________________________________________");
    }

    public void printTaskDeletedMessage(int arraySize, Task deletedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have removed the following from your list of tasks:");
        System.out.println(deletedTask);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public void printList(ArrayList<Task> tasksList, int arraySize) {
        System.out.println("____________________________________________________________");
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < arraySize; i++) {
            System.out.print((i+1) + ".");
            System.out.println(tasksList.get(i));
        }
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

    public void printMissingDescriptionOrDateMessage() {
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
