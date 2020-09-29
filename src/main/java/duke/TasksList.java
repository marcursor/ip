package duke;

import duke.exception.InvalidTaskException;
import duke.exception.MissingDescriptionException;
import duke.exception.NoMatchingTasksException;
import duke.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 *
 */
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

    /**
     *
     * @param newTaskToAdd
     * @param ui
     */
    public void addTaskToList(Task newTaskToAdd, Ui ui) {
        tasksList.add(newTaskToAdd);
        arraySize = tasksList.size();
        ui.printTaskAddedMessage(arraySize, newTaskToAdd);
    }

    /**
     *
     * @param doneTaskIndex
     * @param ui
     * @throws InvalidTaskException
     */
    public void markTaskAsDone(int doneTaskIndex, Ui ui) throws InvalidTaskException {
        if (doneTaskIndex < tasksList.size()) {
            Task doneTask = tasksList.get(doneTaskIndex);
            doneTask.markAsDone();
            ui.printTaskCompletedMessage(doneTask);
        } else {
            throw new InvalidTaskException();
        }
    }

    /**
     * 
     * @param taskToDeleteIndex
     * @param ui
     */
    public void deleteTaskFromList(Integer taskToDeleteIndex, Ui ui) {
        int arraySize;

        Task deletedTask = tasksList.get(taskToDeleteIndex);
        tasksList.remove(deletedTask);
        arraySize = tasksList.size();
        ui.printTaskDeletedMessage(arraySize, deletedTask);
    }

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
