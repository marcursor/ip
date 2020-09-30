package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;


/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput the full user input string
     * @return the command based on user input
     * @throws InvalidCommandException if the input does not correspond to a valid command
     */
    public static Command parseCommand(String userInput) throws InvalidCommandException {
        String commandType;

        int index = userInput.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.
            commandType = userInput.substring(0, index).trim(); // Extract first word.
        } else {
            commandType = userInput; // Text is the first word itself.
        }

        switch (commandType) {
        case "bye":
            return new ExitCommand(userInput);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new AddCommand(userInput);
        case "done":
            return new DoneCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        case "list":
            return new ListCommand(userInput);
        case "find":
            return new FindCommand(userInput);
        default:
            return new InvalidCommand(userInput);
        }
    }
}
