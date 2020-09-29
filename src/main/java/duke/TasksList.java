package duke;

import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;

public class TasksList {
    public final ArrayList<Task> tasksList;
    public int arraySize;

    public TasksList() {
        this.tasksList = new ArrayList<>();
        this.arraySize = 0;
    }

    public TasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        this.arraySize = tasksList.size();
    }

    public void addTaskToList(Task newTaskToAdd, Ui ui) {
        tasksList.add(newTaskToAdd);
        arraySize = tasksList.size();
        ui.printTaskAddedMessage(arraySize, newTaskToAdd);
    }

    public void markTaskAsDone(int doneTaskIndex, Ui ui) throws InvalidTaskException {
        if (doneTaskIndex < tasksList.size()) {
            Task doneTask = tasksList.get(doneTaskIndex);
            doneTask.markAsDone();
            ui.printTaskCompletedMessage(doneTask);
        } else {
            throw new InvalidTaskException();
        }
    }

    public void deleteTaskFromList(Integer taskToDeleteIndex, Ui ui) {
        int arraySize;

        Task deletedTask = tasksList.get(taskToDeleteIndex);
        tasksList.remove(deletedTask);
        arraySize = tasksList.size();
        ui.printTaskDeletedMessage(arraySize, deletedTask);
    }
}
