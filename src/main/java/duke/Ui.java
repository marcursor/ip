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

    /**
     * Prints a line of underscores to act as a divider between separate command inputs and outputs.
     */
    public void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        printDividerLine();
    }

    /**
     * Prints a message to indicate that a tasksList has been loaded from an external txt file.
     */
    public void printTasksListsLoadedMessage() {
        printDividerLine();
        System.out.println("Existing tasklist loaded.");
    }

    /**
     * Prints a message to indicate that no external txt file containing a tasksList has been found,
     * and that a new tasksList has been created.
     */
    public void printLoadingErrorMessage() {
        printDividerLine();
        System.out.println("No existing tasklist found, new tasklist created.");
    }

    /**
     * Prints a message to indicate that an invalid command was given.
     */
    public void printInvalidCommandMessage() {
        System.out.println("My humblest apologies, I do not know what that means.");
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Very well. Goodbye.");
    }

    /** Prints a message to indicate a task has been added.
     *
     * @param arraySize is the size of the tasksList after adding the task
     * @param newTaskToAdd is the task to be added to the tasksList
     */
    public void printTaskAddedMessage(int arraySize, Task newTaskToAdd) {
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
    }

    /**
     * Prints a message to indicate a task has been marked as complete.
     *
     * @param doneTask is the task that was marked as complete
     */
    public void printTaskCompletedMessage(Task doneTask) {
        System.out.println("I have noted the completion of this task:");
        System.out.println(doneTask);
    }

    /**
     * Prints a message to indicate a task has been deleted from the tasksList.
     *
     * @param arraySize is the size of the tasksList after deleting the task
     * @param deletedTask is the task that was deleted
     */
    public void printTaskDeletedMessage(int arraySize, Task deletedTask) {
        System.out.println("I have removed the following from your list of tasks:");
        System.out.println(deletedTask);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
    }

    /**
     * Prints a list of all current tasks.
     *
     * @param tasks is the object containing the tasksList that describes all current tasks
     */
    public void printList(TasksList tasks) {
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.arraySize; i++) {
            System.out.print((i+1) + ".");
            System.out.println(tasks.tasksList.get(i));
        }
    }

    /**
     * Prints a message to indicate the task index provided is invalid.
     */
    public void printInvalidTaskIndexMessage() {
        System.out.println("I require a valid task index. Please input a number within the task list index.");
    }

    /**
     * Prints a message to indicate that the task does not have a description.
     */
    public void printMissingTaskDescriptionMessage() {
        System.out.println("Pardon me, but the description of a task cannot be empty.");
    }

    /**
     * Prints a message to indicate that the task is missing either a description or date.
     */
    public void printMissingDescriptionOrDateMessage() {
        System.out.println("Excuse me sir, but you seem to have excluded the description or date.");
    }

    /**
     * Prints a message to indicate that the task is missing a date.
     */
    public void printMissingDateMessage() {
        System.out.println("My humblest apologies, but you need to provide a date for this task.");
    }

    /**
     * Prints a message to indicate that no keyword is provided.
     */
    public void printMissingKeywordMessage() {
        System.out.println("My humblest apologies, but I need a keyword to find matching tasks.");
    }

    /**
     * Prints a message to indicate that no matching tasks were found.
     */
    public void printNoMatchingTasksMessage() {
        System.out.println("I regret to inform you that I could not find any tasks matching that keyword.");
    }

    /**
     * Prints the error message of a given error.
     *
     * @param errorMessage is the string containing the error message of a given error
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
