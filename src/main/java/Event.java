public class Event extends Task {
    protected String at;

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
