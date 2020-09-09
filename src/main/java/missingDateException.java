public class missingDateException extends Exception {
    public String errorMessage;

    public missingDateException(String taskType) {
        this.errorMessage =
                "____________________________________________________________\n" +
                String.format("My humblest apologies, but the %s is missing a date.\n", taskType) +
                "____________________________________________________________";
    }
}
