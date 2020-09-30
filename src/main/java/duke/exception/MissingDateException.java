package duke.exception;

public class MissingDateException extends DukeException {
    @Override
    public String getMessage() {
        return "Pardon me sir, but I need a date to record for that task.";
    }
}
