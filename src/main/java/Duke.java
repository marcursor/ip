import java.util.Scanner;

public class Duke {
    // constant for number of maximum tasks
    public static final int MAXIMUM_TASKS = 100;

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[MAXIMUM_TASKS];
        int taskCount = 0;

        printWelcomeMessage();

        while (true) {
            // take in user input from command line
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                // if command "bye" is entered, stop asking for commands and exit
                break;
            } else if (userInput.equals("list")) {
                // prints the task list if command "list" is given
                printTaskList(taskList, taskCount);
            } else if (userInput.contains("done")) {
                // if input contains "done", find the corresponding task and update its status in the list
                int donePosition = userInput.indexOf("done");
                int doneTaskIndex = Integer.parseInt(userInput.substring(donePosition + 5));
                if (doneTaskIndex > taskCount) {
                    // if task indicated exceeds current task list, indicate an error
                    printInvalidTaskDoneMessage();
                    continue;
                }
                Task doneTask = taskList[doneTaskIndex - 1];
                doneTask.markAsDone();

                printTaskCompletedMessage(doneTask);
            } else if (userInput.contains("todo")) {
                // adds input as a "Todo" task to the task list
                int todoPosition = userInput.indexOf("todo");
                String todoTask = userInput.substring(todoPosition + 5);
                Task newTaskToAdd = new Todo(todoTask);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                printTaskAddedMessage(taskCount, newTaskToAdd);
            } else if (userInput.contains("deadline")) {
                // adds input as a "Deadline" task to the task list
                int deadlinePosition = userInput.indexOf("deadline");
                int byPosition = userInput.indexOf("/by");
                String deadlineTask = userInput.substring(deadlinePosition + 9, byPosition);
                String deadline = userInput.substring(byPosition + 4);
                Task newTaskToAdd = new Deadline(deadlineTask, deadline);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                printTaskAddedMessage(taskCount, newTaskToAdd);
            } else if (userInput.contains("event")) {
                // adds input as an "Event" task to the task list
                int eventPosition = userInput.indexOf("event");
                int atPosition = userInput.indexOf("/at");
                String eventTask = userInput.substring(eventPosition + 6, atPosition);
                String eventDate = userInput.substring(atPosition + 4);
                Task newTaskToAdd = new Event(eventTask, eventDate);

                taskList[taskCount] = newTaskToAdd;
                taskCount++;

                printTaskAddedMessage(taskCount, newTaskToAdd);
            }
        }

        printGoodbyeMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Botty McBotface, at your service.");
        System.out.println("Your wish is my command.");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList(Task[] taskList, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.printf("Here is your list of %s:%n", (taskCount == 1) ? "task":"tasks");
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i+1) + ".");
            System.out.println(taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void printInvalidTaskDoneMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("That task is not on the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskCompletedMessage(Task doneTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have noted the completion of this task: ");
        System.out.printf("  [%s] %s%n", doneTask.getStatusIcon(), doneTask.description);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskAddedMessage(int taskCount, Task newTaskToAdd) {
        System.out.println("____________________________________________________________");
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", taskCount, (taskCount == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public static void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Very well. Goodbye.");
        System.out.println("____________________________________________________________");
    }


}
