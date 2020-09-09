public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws missingDescriptionException, missingDateException {
        super(description);
        if (by.isBlank()) {
            throw new missingDateException("deadline");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + "(by: " + by + ")";
    }
}
