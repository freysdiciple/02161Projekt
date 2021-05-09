package WhiteBoxTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Exceptions.AdminNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.Admin;
import SoftwareAS.Model.DataBase;

public class createProjectWhiteBox {

	/*
	 * 
	 * public void createProject(int projectNumber) { //pre: isAdmin &&
	 * Integer.toString(projectNumber).length()==6 &&
	 * !database.containsProject(projectNumber); //post:
	 * database.containsProject(projectNumber)==true; }
	 * 
	 * }
	 */

	private Admin admin;
	private DataBase database;
	private String userName = "TEST";
	private String projectName = "TEST";
	private String projectNameInvalid = "12";
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

	@Test
	public void PathA() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException {

		database.createAdmin(userName);
		admin = database.getAdminById(userName);
		admin.setAdminState(false);
		try {
			admin.createProject(projectName);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals(errorMessageHolder.getErrorMessage(), "Only admins can create new projects");

	}

	@Test
	public void PathB() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException, NotAuthorizedException {
		database.createAdmin(userName);
		admin = database.getAdminById(userName);
		admin.createProject(projectName);
		try {
			admin.createProject(projectName);
		} catch (ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals(errorMessageHolder.getErrorMessage(), "Project Already Exists");

	}

	@Test
	public void PathC() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException, NotAuthorizedException {
		database.createAdmin(userName);
		admin = database.getAdminById(userName);
		try {
			admin.createProject(projectNameInvalid);
		} catch (OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals(errorMessageHolder.getErrorMessage(), "Project name has to consist of 4-32 characters");

	}

	@Test
	public void PathD() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException, NotAuthorizedException {
		database.createAdmin(userName);
		admin = database.getAdminById(userName);
		admin.createProject(projectName);

		assertTrue(database.containsProject(projectName));
	}
}
