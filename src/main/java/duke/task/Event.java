package duke.task;

import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public String at;

    /**
     * Creates a new event task.
     *
     * @param description is the description of the event
     * @param at is the date or time when the event occurs
     * @throws MissingDateException if no at date is given
     * @throws MissingDescriptionException if no event description is given
     */
    public Event(String description, String at) throws MissingDateException, MissingDescriptionException {
        super(description);
        if (at.isBlank()) {
            throw new MissingDateException();
        }
        this.at = at;
    }

    /**
     * Returns a formatted string describing the event task.
     *
     * @return a formatted string indicating the task's event type, description and event date
     */
    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + at + ")";
    }
}
