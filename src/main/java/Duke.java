import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isBotRunning = true;
        String userInput;
        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");

        while (isBotRunning) {
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                // if command "bye" is entered, stop asking for commands and exit
                isBotRunning = false;
                break;
            } else if (userInput.equals("list")) {
                // prints the task list if command "list" is given
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(String.format("%d. [%s] %s", i + 1, taskList[i].getStatusIcon(),
                            taskList[i].description));
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("done")) {
                // if input contains "done", find the corresponding task and update its status in the list
                int donePosition = userInput.indexOf("done");
                int doneTaskIndex = Integer.parseInt(userInput.substring(donePosition + 5));
                if (doneTaskIndex > taskCount) {
                    // if task indicated exceeds current task list, indicate the error
                    System.out.println("____________________________________________________________");
                    System.out.println("That task is not on the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                Task doneTask = taskList[doneTaskIndex - 1];
                doneTask.markAsDone();

                System.out.println("____________________________________________________________");
                System.out.println("I have noted the completion of this task: ");
                System.out.println(String.format("  [%s] %s", doneTask.getStatusIcon(), doneTask.description));
                System.out.println("____________________________________________________________");
            } else {
                // adds input as tasks to the task list
                Task newTaskToAdd = new Task(userInput);
                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("I have added \"" + userInput + "\" to your list of tasks.");
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
