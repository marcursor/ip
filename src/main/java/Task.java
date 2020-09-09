public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) throws missingDescriptionException {
        if (description.isBlank()) {
            throw new missingDescriptionException();
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
        String message = (String.format("[%s] %s", getStatusIcon(), description));
        return message;
    }

}
