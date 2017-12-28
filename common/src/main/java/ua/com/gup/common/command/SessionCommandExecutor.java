package ua.com.gup.common.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.gup.common.model.mongo.operation.OperationType;

public abstract class SessionCommandExecutor implements CommandExecutor {

    private final Logger log = LoggerFactory.getLogger(SessionCommandExecutor.class);

    @Override
    public void doCommand(Command command) throws CommandException {
        try {
            command.execute();
            journal(command.getJournalable(), command.getOperationType());
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }

    protected abstract void journal(Journalable journalable, OperationType operationType);
}