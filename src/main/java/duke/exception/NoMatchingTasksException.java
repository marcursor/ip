package duke.exception;

public class NoMatchingTasksException extends DukeException {
    @Override
    public String getMessage() {
        return "I regret to inform you that I could find no tasks matching that keyword.";
    }
}
