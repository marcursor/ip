package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Storage storage, TasksList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
