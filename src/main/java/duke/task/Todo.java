package duke.task;

import duke.exception.MissingDescriptionException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a new todo task.
     *
     * @param description is the description of the task
     * @throws MissingDescriptionException if no description is given
     */
    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    /**
     * Returns a formatted string describing the todo task.
     *
     * @return a formatted string indicating the task's todo type and description
     */
    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
