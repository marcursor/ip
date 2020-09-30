package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.InvalidCommandException;


/**
 *
 */
public class Parser {
    public Parser() {
    }

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
            // add a todo, deadline or event task
            return new AddCommand(userInput);
        case "done":
            return new DoneCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        case "list":
            // list the current tasks
            return new ListCommand(userInput);
        case "find":
            return new FindCommand(userInput);
        default:
            // unknown command error
            throw new InvalidCommandException();
        }
    }
}
