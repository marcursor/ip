package duke;


import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class Duke {
    private Storage storage;
    private TasksList tasks;
    Ui ui;


    public Duke(Path filepath) {
        ui = new Ui();

        storage = new Storage(filepath);

        storage.initialiseFolder();
        try {
            tasks = new TasksList(storage.loadTaskListFromFile());
            ui.printTasksListsLoadedMessage();
        } catch (FileNotFoundException e) {
            tasks = new TasksList();
            ui.printLoadingErrorMessage();
        } catch (MissingDescriptionException e) {
            System.out.println("Loaded task is missing a description.");
        }
    }

    public static void main(String[] args) {
        Path filepath = Paths.get(System.getProperty("user.dir"),Storage.TASKLIST_DIRECTORY);
        new Duke(filepath).run();
    }

    private void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.handleUserInput();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.printDividerLine();
            }
        }
    }

}
