package duke.task;

import duke.exception.MissingDescriptionException;

/**
 * Represents a task, which is a parent of the todo, deadline and event tasks.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description is the description of the task
     * @throws MissingDescriptionException if no description is given
     */
    public Task(String description) throws MissingDescriptionException {
        if (description.isBlank()) {
            throw new MissingDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon indicating the completion status of a task.
     *
     * @return a tick icon if task is completed, or a cross icon if task is not complete
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Provides the description of the task
     *
     * @return the task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a formatted string describing the task.
     *
     * @return a formatted string indicating its completion status and description
     */
    @Override
    public String toString() {
        return (String.format("[%s] %s", getStatusIcon(), description));
    }

}
