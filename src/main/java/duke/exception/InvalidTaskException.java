package duke.exception;

public class InvalidTaskException extends DukeException {
    @Override
    public String getMessage() {
        return "I am sorry, but that task index is out of range.";
    }
}
