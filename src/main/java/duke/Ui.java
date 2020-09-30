package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return the string input by the user
     */
    public String handleUserInput() {
        return in.nextLine();
    }

    public void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        printDividerLine();
    }

    public void printTasksListsLoadedMessage() {
        printDividerLine();
        System.out.println("Existing tasklist loaded.");
    }

    public void printLoadingErrorMessage() {
        printDividerLine();
        System.out.println("No existing tasklist found, new tasklist created.");
    }

    public void printInvalidCommandMessage() {
        System.out.println("My humblest apologies, I do not know what that means.");
    }

    public void printGoodbyeMessage() {
        System.out.println("Very well. Goodbye.");
    }

    public void printTaskAddedMessage(int arraySize, Task newTaskToAdd) {
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
    }

    public void printTaskCompletedMessage(Task doneTask) {
        System.out.println("I have noted the completion of this task:");
        System.out.printf("  [%s] %s%n", doneTask.getStatusIcon(), doneTask.description);
    }

    public void printTaskDeletedMessage(int arraySize, Task deletedTask) {
        System.out.println("I have removed the following from your list of tasks:");
        System.out.println(deletedTask);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
    }

    public void printList(TasksList tasks) {
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.arraySize; i++) {
            System.out.print((i+1) + ".");
            System.out.println(tasks.tasksList.get(i));
        }
    }

    public void printInvalidTaskCompleteMessage() {
        System.out.println("That task is not on the list.");
    }

    public void printMissingTodoDescriptionMessage() {
        System.out.println("Pardon me, but the description of a todo task cannot be empty.");
    }

    public void printMissingDescriptionOrDateMessage() {
        System.out.println("Excuse me sir, but you seem to have excluded the description or date.");
    }

    public void printMissingDeadlineDescriptionMessage() {
        System.out.println("Pardon me, but the description of a deadline task cannot be empty.");
    }

    public void printMissingDeadlineDateMessage() {
        System.out.println("My humblest apologies, but there is no date for the deadline.");
    }

    public void printMissingEventDescriptionMessage() {
        System.out.println("My humblest apologies, but the event is missing a date.");
    }

    public void printMissingEventDateMessage() {
        System.out.println("Pardon me, but the description of an event cannot be empty.");
    }

    public void printMissingKeywordMessage() {
        System.out.println("My humblest apologies, but I need a keyword to find matching tasks.");
    }

    public void printNoMatchingTasksMessage() {
        System.out.println("I regret to inform you that I could not find any tasks matching that keyword.");
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
