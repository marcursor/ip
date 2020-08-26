import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isBotRunning = true;
        String enteredCommand;
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        int taskCount = 0;

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
            // prints the task list if command "list" is given
            else if (enteredCommand.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(String.format("%d. %s", i+1, taskList[i]));
                }
                System.out.println("____________________________________________________________");
            }
            // adds commands to the task list
            else {
                taskList[taskCount] = enteredCommand;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("> added: " + enteredCommand);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
