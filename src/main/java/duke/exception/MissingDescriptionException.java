package duke.exception;

public class MissingDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return "That task is missing a description, my good sir.";
    }
}
