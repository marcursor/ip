package duke.exception;

public class InvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return "My humblest apologies, I don't understand what that means.";
    }
}
