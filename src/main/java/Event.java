public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws missingDateException, missingDescriptionException {
        super(description);
        if (at.isBlank()) {
            throw new missingDateException("event");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + "(at: " + at + ")";
    }
}
