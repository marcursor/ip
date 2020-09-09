import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static boolean isRunning;

    public TaskManager() {
        this.isRunning = true;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    private void setIsRunning(boolean status) {
        isRunning = status;
    }

    public static String[] processInput(String userInput) {
        String[] inputStringArray = userInput.split(" ",2);
        return inputStringArray;
    }

    public void runCommand(String[] inputStringArray, ArrayList<Task> taskList) {
        String commandType = inputStringArray[0];
        String commandDescription = null;
        int arraySize = taskList.size();
        if (inputStringArray.length > 1) {
            commandDescription = inputStringArray[1];
        }

        switch (commandType) {
        case "bye":
            // exit program
            exitProgram();
            break;
        case "todo":
            // add a todo task
            Task newTodoToAdd = new Todo(commandDescription);

            addTaskToList(taskList, newTodoToAdd);
            break;
        case "deadline":
            // add a deadline task
            String[] deadlineString = commandDescription.split("/by ");
            String deadlineTask = deadlineString[0];
            String deadlineDate = deadlineString[1];
            Task newDeadlineToAdd = new Deadline(deadlineTask, deadlineDate);

            addTaskToList(taskList, newDeadlineToAdd);
            break;
        case "event":
            // add an event task
            String[] eventString = commandDescription.split("/at ");
            String eventTask = eventString[0];
            String eventDate = eventString[1];
            Task newEventToAdd = new Event(eventTask, eventDate);

            addTaskToList(taskList, newEventToAdd);
            break;
        case "done":
            int doneTaskIndex = Integer.parseInt(commandDescription) - 1;
            Task doneTask = taskList.get(doneTaskIndex);
            doneTask.markAsDone();
            printTaskCompletedMessage(doneTask);
            break;
        case "list":
            // list the current tasks
            printList(taskList, arraySize);
            break;
        default:
            // unknown command error
            System.out.println("____________________________________________________________");
            System.out.println("Sorry no such command exists.");
            System.out.println("____________________________________________________________");
            break;
        }
    }



    public void exitProgram() {
        setIsRunning(false);
    }

    public void addTaskToList(ArrayList<Task> taskList, Task newTaskToAdd) {
        int arraySize;
        taskList.add(newTaskToAdd);
        arraySize = taskList.size();
        printTaskAddedMessage(arraySize, newTaskToAdd);
    }

    public void printList(ArrayList<Task> taskList, int arraySize) {
        System.out.println("____________________________________________________________");
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < arraySize; i++) {
            System.out.print((i+1) + ".");
            System.out.println(taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void printTaskAddedMessage(int arraySize, Task newTaskToAdd) {
        System.out.println("____________________________________________________________");
        System.out.println("I have added the following to your list of tasks:");
        System.out.println(newTaskToAdd);
        System.out.printf("You now have %d %s in the list.%n", arraySize, (arraySize == 1) ? "task":"tasks");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskCompletedMessage(Task doneTask) {
        System.out.println("____________________________________________________________");
        System.out.println("I have noted the completion of this task: ");
        System.out.printf("  [%s] %s%n", doneTask.getStatusIcon(), doneTask.description);
        System.out.println("____________________________________________________________");
    }
}

/*  1. get user input
    2. process input -> look for keyword **
    3. perform action
 */