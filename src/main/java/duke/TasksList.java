package duke;

import duke.exception.InvalidTaskException;
import duke.exception.MissingDescriptionException;
import duke.exception.NoMatchingTasksException;
import duke.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Records the list of tasks keyed in by the user.
 */
public class TasksList {
    public final ArrayList<Task> tasksList;
    public int arraySize;

    /**
     * Creates a fresh empty tasksList object.
     */
    public TasksList() {
        this.tasksList = new ArrayList<>();
        this.arraySize = 0;
    }

    /**
     * Creates a tasksList object with pre-existing tasks.
     *
     * @param tasksList the list of tasks to be added to the tasksList object
     */
    public TasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        this.arraySize = tasksList.size();
    }

    /**
     * Adds a task to the tasksList.
     *
     * @param newTaskToAdd the task to be added to the tasksList
     * @param ui the ui object handles interactions with the user
     */
    public void addTaskToList(Task newTaskToAdd, Ui ui) {
        tasksList.add(newTaskToAdd);
        arraySize = tasksList.size();
        ui.printTaskAddedMessage(arraySize, newTaskToAdd);
    }

    /**
     * Marks a task in the tasksList as done.
     *
     * @param doneTaskIndex the index of the task to be marked done (list starts from index 1)
     * @param ui the ui object handles interactions with the user
     * @throws InvalidTaskException if the task index exceeds the tasksList index range
     */
    public void markTaskAsDone(int doneTaskIndex, Ui ui) throws InvalidTaskException {
        if (doneTaskIndex < tasksList.size() & doneTaskIndex > 0) {
            Task doneTask = tasksList.get(doneTaskIndex);
            doneTask.markAsDone();
            ui.printTaskCompletedMessage(doneTask);
        } else {
            throw new InvalidTaskException();
        }
    }

    /**
     * Deletes a task in the tasksList.
     *
     * @param taskToDeleteIndex the index of the task to be deleted (list starts from index 1)
     * @param ui the ui object handles interactions with the user
     * @throws InvalidTaskException if the task index exceeds the tasksList index range
     */
    public void deleteTaskFromList(Integer taskToDeleteIndex, Ui ui) throws InvalidTaskException {
        if (taskToDeleteIndex < tasksList.size() & taskToDeleteIndex > 0) {
            Task deletedTask = tasksList.get(taskToDeleteIndex);
            tasksList.remove(deletedTask);
            arraySize = tasksList.size();
            ui.printTaskDeletedMessage(arraySize, deletedTask);
        } else {
            throw new InvalidTaskException();
        }
    }

    /**
     * Finds tasks that contain a keyword given by the user.
     *
     * @param keyword string used to search and filter through tasks
     * @return the ArrayList of tasks that contain the keyword
     * @throws MissingDescriptionException if no keyword is given
     * @throws NoMatchingTasksException if no tasks matching the keyword were found
     */
    public ArrayList<Task> findMatchingTasksInList(String keyword) throws MissingDescriptionException, NoMatchingTasksException {
        if (keyword.isBlank()) {
            throw new MissingDescriptionException();
        }

        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasksList.stream()
                .filter((s) -> s.getDescription().contains(keyword))
                .collect(toList());

        if (filteredTaskList.size() == 0) {
            throw new NoMatchingTasksException();
        }

        return filteredTaskList;
    }
}
