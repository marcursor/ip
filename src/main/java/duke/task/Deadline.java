package duke.task;

import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;

public class Deadline extends Task {
    public String by;

    public Deadline(String description, String by) throws MissingDescriptionException, MissingDateException {
        super(description);
        if (by.isBlank()) {
            throw new MissingDateException();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + "(by: " + by + ")";
    }
}
