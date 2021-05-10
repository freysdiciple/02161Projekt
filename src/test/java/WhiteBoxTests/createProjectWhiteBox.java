

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

//This class is made by Simon Bang Hjortkilde - s183557
public class createProjectWhiteBox {

	private Admin admin;
	private DataBase database = DataBase.getInstance();
	private String projectName1 = "TEST1";
	private String projectName2 = "TEST2";
	private String projectName3 = "TEST3";
	private String projectNameInvalid = "12";
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	private String userName1 = "T111";
	private String userName2 = "T222";
	private String userName3 = "T333";
	private String userName4 = "T444";

	@Test
	public void PathA() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException {

		database.createAdmin(userName1);
		admin = database.getAdminById(userName1);
		admin.setAdminState(false);
		try {
			admin.createProject(projectName1);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals(errorMessageHolder.getErrorMessage(), "Only admins can create new projects");

	}

	@Test
	public void PathB() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException, NotAuthorizedException {
		database.createAdmin(userName2);
		admin = database.getAdminById(userName2);
		admin.createProject(projectName2);
		try {
			admin.createProject(projectName2);
		} catch (ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals(errorMessageHolder.getErrorMessage(), "Project Already Exists");

	}

	@Test
	public void PathC() throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException,
			ProjectNotFoundException, OutOfBoundsException, NotAuthorizedException {
		database.createAdmin(userName3);
		admin = database.getAdminById(userName3);
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
		database.createAdmin(userName4);
		admin = database.getAdminById(userName4);
		admin.createProject(projectName3);

		assertTrue(database.containsProject(projectName3));
	}
}
