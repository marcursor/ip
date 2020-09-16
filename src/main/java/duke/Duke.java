package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        printWelcomeMessage();

        handleUserInput();

        printGoodbyeMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");
    }

    public static void handleUserInput() {
        String userInput;
        String[] processedInput;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<>();

        while (taskManager.getIsRunning()) {
            userInput = in.nextLine();
            try {
                processedInput = taskManager.processInput(userInput);
                taskManager.runCommand(processedInput, taskList);
            } catch (InvalidCommandException e) {
                printInvalidCommandMessage();
            }

        }
    }

    public static void printInvalidCommandMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("My humblest apologies, I do not know what that means.");
        System.out.println("____________________________________________________________");
    }

    public static void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }


}
