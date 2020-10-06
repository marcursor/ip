package duke.task;

import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public String by;

    /**
     * Creates a new deadline task.
     *
     * @param description is the description of the task
     * @param by is the deadline for the task
     * @throws MissingDescriptionException if no task description is given
     * @throws MissingDateException if no by deadline is given
     */
    public Deadline(String description, String by) throws MissingDescriptionException, MissingDateException {
        super(description);
        if (by.isBlank()) {
            throw new MissingDateException();
        }
        this.by = by;
    }

    /**
     * Returns a formatted string describing the deadline task.
     *
     * @return a formatted string indicating the task's deadline type, description and deadline
     */
    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
