package duke.exception;

public class MissingDescriptionOrDateException extends DukeException {
    @Override
    public String getMessage() {
        return "Ahem, you've neglected to provide either a description of the task or a date.";
    }
}
