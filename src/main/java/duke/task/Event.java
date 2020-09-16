package duke.task;

import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;

public class Event extends Task {
    public String at;

    public Event(String description, String at) throws MissingDateException, MissingDescriptionException {
        super(description);
        if (at.isBlank()) {
            throw new MissingDateException();
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + "(at: " + at + ")";
    }
}