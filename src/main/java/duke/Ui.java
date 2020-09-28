package duke;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private static final Parser parser = new Parser();

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public static void handleUserInput() {
        String userInput;
        String[] processedInput;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasksList = new ArrayList<>();

        while (parser.getIsRunning()) {
            userInput = in.nextLine();
            try {
                processedInput = parser.processInput(userInput);
                parser.runCommand(processedInput, tasksList);
            } catch (InvalidCommandException e) {
                printInvalidCommandMessage();
            }

        }
    }

    public static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");
    }

    public void printTasksListsLoadedMessage() {
        System.out.println("Existing tasklist loaded.");
        System.out.println("____________________________________________________________");
    }

    public void printLoadingErrorMessage() {

    }

    public static void printFileNotFoundMessage() {
        System.out.println("No existing tasklist found");
        System.out.println("____________________________________________________________");
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
