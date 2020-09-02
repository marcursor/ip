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
                break;
            } else if (userInput.equals("list")) {
                // prints the task list if command "list" is given
                System.out.println("____________________________________________________________");
                System.out.println(String.format("Here is your list of %s:", (taskCount == 1) ? "task":"tasks"));
                for (int i = 0; i < taskCount; i++) {
                    System.out.print((i+1) + ".");
                    System.out.println(taskList[i]);
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
            } else if (userInput.contains("todo")) {
                // adds input as a "Todo" task to the task list
                int todoPosition = userInput.indexOf("todo");
                String todoTask = userInput.substring(todoPosition + 5);
                Todo newTaskToAdd = new Todo(todoTask);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("I have added the following to your list of tasks:");
                System.out.println(newTaskToAdd);
                System.out.println(String.format("You now have %d %s in the list.", taskCount, (taskCount == 1) ? "task":"tasks"));
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("deadline")) {
                // adds input as a "Deadline" task to the task list
                int deadlinePosition = userInput.indexOf("deadline");
                int byPosition = userInput.indexOf("/by");
                String deadlineTask = userInput.substring(deadlinePosition + 9, byPosition);
                String deadline = userInput.substring(byPosition + 4);
                Deadline newTaskToAdd = new Deadline(deadlineTask, deadline);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("I have added the following to your list of tasks:");
                System.out.println(newTaskToAdd);
                System.out.println(String.format("You now have %d %s in the list.", taskCount, (taskCount == 1) ? "task":"tasks"));
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("event")) {
                // adds input as an "Event" task to the task list
                int eventPosition = userInput.indexOf("event");
                int atPosition = userInput.indexOf("/at");
                String eventTask = userInput.substring(eventPosition + 6, atPosition);
                String eventDate = userInput.substring(atPosition + 4);
                Event newTaskToAdd = new Event(eventTask, eventDate);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("I have added the following to your list of tasks:");
                System.out.println(newTaskToAdd);
                System.out.println(String.format("You now have %d %s in the list.", taskCount, (taskCount == 1) ? "task":"tasks"));
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
