package WhiteBoxTests;

import Exceptions.AdminNotFoundException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.Admin;
import SoftwareAS.Model.DataBase;

public class createProjectWhiteBox {
	
	
	/*
	
	public void createProject(int projectNumber) {
		//pre: isAdmin && Integer.toString(projectNumber).length()==6 && !database.containsProject(projectNumber);
		//post: database.containsProject(projectNumber)==true;
	}

}
*/
	
	private Admin admin;
	private DataBase database;
	private String userName = "TEST";
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	
	public void PathA() throws AdminNotFoundException {
	
	database.createAdmin(userName);
	admin=database.getAdminById(userName);
	admin.createDeveloper(userName);
	}
	
}

