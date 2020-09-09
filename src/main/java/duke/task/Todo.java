package duke.task;

import duke.exception.MissingDescriptionException;

public class Todo extends Task {
    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
