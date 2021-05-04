package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;


public class CreateProjectSteps {
	private Admin admin;
	private Project project;
	private DataBase database = new DataBase();
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	/*
	
	public CreateProjectSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	

	// # Main scenario
	// Scenario: Create new project
	// Given there is an user with ID {string}
	// And the user is an admin
	// When the user creates a project with a number {int}
	// Then the project with a number {int} is contained in the system

	@Given("there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws DeveloperNotFoundException, NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException {
		database.createAdmin(userName);
		admin = database.getAdminById(userName);
		assertTrue(database.containsAdmin(userName));
	}

	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}

	@When("the user creates a project with a number {int}")
	public void theUserCreatesProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber);
	}

	@Then("the project with a number {int} is contained in the system")
	public void systemCreatesProjectWithGivenNumberAndCreator(int projectNumber) throws ProjectNotFoundException {
		assertTrue(database.containsProject(projectNumber));
	}

//# Alternate scenario one     
//Scenario: A project with the the given number already exists
//	Given there is a user
//	And the user is an admin
//	When the user creates a project with a number {int} identical to an existing project
//  Then the system throws ExistingProjectException

	@When("the user creates a project with a number {int} identical to an existing project")
	public void theUserCreatesAProjectWithNameIdenticalToAnExistisngProject(int projectNumber) throws ProjectNotFoundException, ProjectAlreadyExistsException {
		admin.createProject(projectNumber);
		try {
			admin.createProject(projectNumber);
		}
		catch(ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the system throws ExistingProjectException")
	public void systemThrowsExistingProjectException() {
		assertEquals("Project Already Exists", errorMessageHolder.getErrorMessage());
		
		
	}

//	# Alternate scenario two
//	Scenario: User is not an admin
//		Given there is a user
//		And the user is not an admin
//	   	When the user creates a project with name and number
//	   	Then the system throws InvalidUserException
	


	@Given("the user is not an admin")
	public void theUserIsAnAdmin1() {
		assertFalse(admin.isAdmin());
	}

	@When("the user tries to create a project with a number {int}")
	public void theUserCreatesProject1() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		developer.createProject(projectNumber);
	}

	@Then("the system throws AdminNotFoundException")
	public void systemThrowsAdminNotFoundException() throws AdminNotFoundException {
		throw new AdminNotFoundException("User not an admin");
	}
*/

	
}


