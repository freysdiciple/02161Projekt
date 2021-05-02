package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.AdminNotFoundException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

/*
public class CreateProjectSteps {
	private Admin admin;
	private Project project;
	private DataBase database;
	private ErrorMessageHolder errorMessageHolder;
	
	private int projectNumber = 12345;
	private int existingProjectNumber = projectNumber;
	
	
	public CreateProjectSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}

	// # Main scenario
	// Scenario: Create new project
	// Given there is an user with id {String} and database {DataBase}
	// And the user is an admin
	// When the user creates a project with a number {int} and a creator {Admin}
	// Then the project with a number {int} is contained in the system

	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		admin = new Admin("Søren", database);
	}

	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}

	@When("the user creates a project with a number {int} and a creator {Admin}")
	public void theUserCreatesProject() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber);
	}

	@Then("the project with a number {int} is contained in the system")
	public void systemCreatesProjectWithGivenNumberAndCreator() throws ProjectNotFoundException {
		assertTrue(database.containsProject(projectNumber));
	}

//# Alternate scenario one     
//Scenario: A project with the given name already exists
//	Given there is a user
//	And the user is an admin
//	When the user creates a project with name identical to an existing project
//  Then the system throws ExistingProjectException

	@When("the user creates a project with name identical to an existing project")
	public void theUserCreatesAProjectWithNameIdenticalToAnExistisngProject() throws ProjectNotFoundException {
		try {
			admin.createProject(projectNumber);
		}
		catch(ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the system throws ExistingProjectException")
	public void systemThrowsExistingProjectException() {
		assertEquals("Existing Project", errorMessageHolder.getErrorMessage());
		
		
	}
	
//	# Alternate scenario three
//	Scenario: User is not and admin
//		Given there is a user
//		And the user is not an admin
//	   	When the user creates a project with name and number
//	   	Then the system throws InvalidUserException
	
	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase1() {
		admin = new Admin("Søren", database);
	}

	@Given("the user is not an admin")
	public void theUserIsAnAdmin1() {
		assertFalse(admin.isAdmin());
	}

	@When("the user creates a project with a number {int} and a creator {Admin}")
	public void theUserCreatesProject1() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber);
	}

	@Then("the system throws AdminNotFoundException")
	public void systemThrowsAdminNotFoundException() throws AdminNotFoundException {
		throw new AdminNotFoundException("User not an admin");
	

}
}
*/
