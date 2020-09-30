package duke;

import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class Storage {
    public static final String TEXT_SEPARATOR = "|";
    public static final String TASKLIST_DIRECTORY = "data";
    public static final String TASKLIST_FILENAME = "data/tasklist.txt";

    public final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public void initialiseFolder() {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("IO exception encountered when creating data directory.");
            }
        }
    }

    public ArrayList<Task> loadTaskListFromFile() throws MissingDescriptionException, FileNotFoundException {
        File f = new File(TASKLIST_FILENAME);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<String> taskInfo;
        ArrayList<Task> loadedTasksList = new ArrayList<>();

        while (s.hasNext()) {
            taskInfo = Arrays.asList(s.nextLine().split("\\|"));

            switch(taskInfo.get(0)) {
            case "T":
                Todo todo = new Todo(taskInfo.get(2));
                if (taskInfo.get(1).equals("1")) {
                    todo.markAsDone();
                }
                loadedTasksList.add(todo);
                break;
            case "D":
                try {
                    Deadline deadline = new Deadline(taskInfo.get(2), taskInfo.get(3));
                    if (taskInfo.get(1).equals("1")) {
                        deadline.markAsDone();
                    }
                    loadedTasksList.add(deadline);
                } catch (MissingDateException e) {
                    System.out.println("Loaded task is missing a date.");
                }
                break;
            case "E":
                try {
                    Event event = new Event(taskInfo.get(2), taskInfo.get(3));
                    if (taskInfo.get(1).equals("1")) {
                        event.markAsDone();
                    }
                    loadedTasksList.add(event);
                } catch (MissingDateException e) {
                    System.out.println("Loaded task is missing a date.");
                }

                break;
            default:
                System.out.println("Loaded task is missing a type.");
                break;
            }

        }

        return loadedTasksList;
    }

    public void saveTasksListToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(TASKLIST_FILENAME);

            for (Task task : taskList) {
                if (task instanceof Todo) {
                    fw.write("T"+ TEXT_SEPARATOR + ((task.isDone) ? "1":"0") + TEXT_SEPARATOR + task.description);
                } else if (task instanceof Deadline) {
                    fw.write("D"+ TEXT_SEPARATOR + ((task.isDone) ? "1":"0") + TEXT_SEPARATOR + task.description + TEXT_SEPARATOR + ((Deadline) task).by);
                } else if (task instanceof Event) {
                    fw.write("E"+ TEXT_SEPARATOR + ((task.isDone) ? "1":"0") + TEXT_SEPARATOR + task.description + TEXT_SEPARATOR + ((Event) task).at);
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
