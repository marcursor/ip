package duke;


import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TasksList tasks;
    private Ui ui;
    private static final Parser parser = new Parser();

    public Duke(Path filepath) {
        ui = new Ui();

        storage = new Storage(filepath);

        storage.initialiseFolder();
        try {
            tasks = new TasksList(storage.loadTaskListFromFile());
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage();
            tasks = new TasksList();
        } catch (MissingDescriptionException e) {
            System.out.println("Loaded task is missing a description.");
        }
    }

    public static void main(String[] args) {
        Path filepath = Paths.get(System.getProperty("user.dir"),Storage.TASKLIST_DIRECTORY);
        new Duke(filepath).run();
    }

    private void run() {
        Ui.printWelcomeMessage();

        Ui.handleUserInput();

        Ui.printGoodbyeMessage();
    }

}
