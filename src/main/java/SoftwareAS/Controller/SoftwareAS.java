package SoftwareAS.Controller;

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
import SoftwareAS.Model.DataBase;

public class SoftwareAS {
	
	static DataBase database = DataBase.getInstance();
	static FrontEndController frontEnd;
	
	public static void main(String[] args) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		database.createAdmin("ADM1");
		
		initiateFrontEnd();
	}
	
	public DataBase getDataBase() {
		return database;
	}
	
	public static void initiateFrontEnd() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		frontEnd = new FrontEndController(database);
	}

}
