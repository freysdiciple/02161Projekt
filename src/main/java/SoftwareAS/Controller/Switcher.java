package SoftwareAS.Controller;

import java.util.HashMap;
import java.util.Map;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;

class DoNothingCommand implements Command {
    @Override public void execute() {}
}


//Massimo
public class Switcher {
	
	private Map<Integer, Command> caseCommands;

    private Command defaultCommand;

    private Command getCaseCommandByCaseId(Integer caseId) {
        if (caseCommands.containsKey(caseId)) {
            return caseCommands.get(caseId);
        } else {
            return defaultCommand;
        }
    }

    public Switcher() {
        caseCommands = new HashMap<Integer, Command>();

        setDefaultCaseCommand(new DoNothingCommand());
    }

    public void addCaseCommand(Integer caseId, Command caseCommand) {
        caseCommands.put(caseId, caseCommand);
    }

    public void setDefaultCaseCommand(Command defaultCommand) {
        if (defaultCommand != null) {
            this.defaultCommand = defaultCommand;
        }
    }

    public void on(Integer caseId) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
        Command command = getCaseCommandByCaseId(caseId);

        command.execute();
    }

}
