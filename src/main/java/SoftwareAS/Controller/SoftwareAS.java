package SoftwareAS.Controller;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Model.DataBase;

public class SoftwareAS {
	
	static DataBase database;
	static FrontEndController frontEnd;
	
	public static void main(String[] args) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		database = DataBase.getInstance();
		database.createAdmin("ADM1");
		
		initiateFrontEnd();
	}
	
	public DataBase getDataBase() {
		return database;
	}
	
	public static void initiateFrontEnd() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		frontEnd = new FrontEndController(database);
	}

}
