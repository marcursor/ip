import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isBotRunning = true;
        String enteredCommand;
        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
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
                    System.out.println(String.format("%d. [%s] %s", i + 1, taskList[i].getStatusIcon(), taskList[i].description));
                }
                System.out.println("____________________________________________________________");
            }
            // if task indicated as done, update it in the list
            else if (enteredCommand.contains("done")) {
                int donePosition = enteredCommand.indexOf("done");
                int doneTaskIndex = Integer.parseInt(enteredCommand.substring(donePosition+5));
                taskList[doneTaskIndex-1].markAsDone();

                System.out.println("____________________________________________________________");
                System.out.println("I have noted the completion of this task: ");
                System.out.println(String.format("  [%s] %s", taskList[doneTaskIndex-1].getStatusIcon(), taskList[doneTaskIndex-1].description));
                System.out.println("____________________________________________________________");
            }
            // adds commands to the task list
            else {
                Task newTaskToAdd = new Task(enteredCommand);
                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("I have added \"" + enteredCommand + "\" to your list of tasks.");
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
