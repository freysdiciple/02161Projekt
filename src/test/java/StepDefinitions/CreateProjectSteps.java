

package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

//This class is made by Simon Bang Hjortkilde - s183557
public class CreateProjectSteps {
	private Admin admin;
	private DataBase database;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();


	public CreateProjectSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}


	//#Main scenario
	//Scenario: Create new project
	//  Given 5- there is an user with ID "SØR1"
	//	And 5- the user is an admin
	// 	When 5- the user creates a project with a valid name "Project123"
	//  Then 5- the project with a name "Project123" is contained in the system

	@Given("5- there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws DeveloperNotFoundException, NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException, OutOfBoundsException {
		database.createAdmin(userName);
		admin=database.getAdminById(userName);
		admin.createDeveloper(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("5- the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}

	@When("5- the user creates a project with a valid name {string}")
	public void theUserCreatesProject(String projectName) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectName);
	}

	@Then("5- the project with a name {string} is contained in the system")
	public void systemCreatesProjectWithGivenNumberAndCreator(String projectName) throws ProjectNotFoundException {
		assertTrue(database.containsProject(projectName));
	}

	//#Alternate scenario one
	//Scenario: A project with the given name already exists
	//	Given 5- there is an user with ID "MOG1"
	//	And 5- the user is an admin
	//	When 5- the user creates a project with a valid name "Project321" identical to an existing project
	//  Then 5- the system throws ExistingProjectException

	@When("5- the user creates a project with a valid name {string} identical to an existing project")
	public void theUserCreatesAProjectWithNameIdenticalToAnExistisngProject(String projectName) throws ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectName);
		try {
			admin.createProject(projectName);
		}
		catch(ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("5- the system throws ExistingProjectException")
	public void systemThrowsExistingProjectException() {
		assertEquals("Project Already Exists", errorMessageHolder.getErrorMessage());


	}

	//#Alternate scenario two
	//Scenario: User is not an admin
	//	Given 5- there is an user with ID "KAR1"
	//	And 5- the user is not an admin
	//  When 5- the user tries to create a project with a valid name "Project999"
	//  Then 5- the system throws NotAuthorizedException



	@Given("5- the user is not an admin")
	public void theUserIsAnAdmin1() {
		admin.setAdminState(false);
		assertFalse(admin.isAdmin());
	}

	@When("5- the user tries to create a project with a valid name {string}")
	public void theUserCreatesProject1(String projectName) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		try {
			admin.createProject(projectName);
		}
		catch(NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("5- the system throws NotAuthorizedException")
	public void systemThrowsNotAuthorizedException() throws NotAuthorizedException {
		assertEquals("Only admins can create new projects", errorMessageHolder.getErrorMessage());
	}
	
	//#Alternate scenario three
	//Scenario: Invalid project name length
	//	Given 5- there is an user with ID "JEP1"
	//	And 5- the user is an admin
	//	When 5- the user tries to create a project with a project name "xxx" with invalid length
	//	Then 5- the system throws OutOfBoundsException

	@When("5- the user tries to create a project with a project name {string} with invalid length")
	public void theUserTriesToCreateAProjectWithInvalidLengthOfProjectName(String projectName) throws NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException {
		try {
			admin.createProject(projectName);
		}
		catch(OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("5- the system throws OutOfBoundsException")
	public void systemThrowsOutOfBoundsException() throws OutOfBoundsException {
		assertEquals("Project name has to consist of 4-32 characters", errorMessageHolder.getErrorMessage());
	}
}
	


