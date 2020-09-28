package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TasksList {
    private final ArrayList<Task> tasksList;

    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    public TasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }
}
