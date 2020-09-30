package duke.exception;

public class InvalidExecutionException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh my, this should have been handled by a child command.";
    }
}
