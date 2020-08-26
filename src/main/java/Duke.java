import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isBotRunning = true;
        String enteredCommand;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");

        while (isBotRunning) {
            enteredCommand = in.nextLine();
            // if bye is entered, stop asking for commands and exit
            if (enteredCommand.equals("bye")) {
                isBotRunning = false;
                break;
            }
            // repeat any other commands back to the user
            System.out.println("____________________________________________________________");
            System.out.println("> " + enteredCommand);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
