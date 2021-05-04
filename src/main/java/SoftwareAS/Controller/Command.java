package SoftwareAS.Controller;

import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;

public interface Command {
	void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException;
}
