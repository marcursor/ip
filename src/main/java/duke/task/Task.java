package duke.task;

import duke.exception.MissingDescriptionException;

public class Task {
    public String description;
    public boolean isDone;


    public Task(String description) throws MissingDescriptionException {
        if (description.isBlank()) {
            throw new MissingDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (String.format("[%s] %s", getStatusIcon(), description));
    }

}
