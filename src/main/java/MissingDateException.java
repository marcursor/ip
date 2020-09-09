public class MissingDateException extends Exception {
    public String errorMessage;

    public MissingDateException(String taskType) {
        this.errorMessage =
                "____________________________________________________________\n" +
                String.format("My humblest apologies, but the %s is missing a date.\n", taskType) +
                "____________________________________________________________";
    }
}
