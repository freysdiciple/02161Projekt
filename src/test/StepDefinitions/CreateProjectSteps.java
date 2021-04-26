package StepDefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class CreateProjectSteps {
	private Admin admin;
	private Project project;
	private DataBase database;
	private int projectNumber = 12345;
	private int existingProjectNumber = projectNumber;

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
	public void theUserCreatesProject() {
		admin.createProject(projectNumber);
	}

	@Then("the project with a number {int} is contained in the system")
	public void systemCreatesProjectWithGivenNumberAndCreator() {
		assertTrue(database.containsProject(projectNumber));
	}

//# Alternate scenario one     
//Scenario: A project with the given name already exists
//	Given there is a user
//	And the user is an admin
//	When the user creates a project with name identical to an existing project
//  Then the system throws ExistingProjectException

	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		admin = new Admin("Søren", database);
	}
	
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}
	
	@When("the user creates a project with a number {int} identical to an existing project")
	public void theUserCreatesProject() {
		projectNumber=existingProjectNumber;
	}
	
	@Then("the system throws ExistingProjectException")
	public void systemThrowsExistingProjectException() {
		if (database.containsProject(projectNumber))
			throw ExistingProjectException();
		admin.createProject(projectNumber);
	}
	
//	# Alternate scenario three
//	Scenario: User is not and admin
//		Given there is a user
//		And the user is not an admin
//	   	When the user creates a project with name and number
//	   	Then the system throws InvalidUserException
	
	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		admin = new Admin("Søren", database);
	}

	@Given("the user is not an admin")
	public void theUserIsAnAdmin() {
		assertFalse(admin.isAdmin());
	}

	@When("the user creates a project with a number {int} and a creator {Admin}")
	public void theUserCreatesProject() {
		admin.createProject(projectNumber);
	}

	@Then("the system throws InvalidUserException")
	public void systemCreatesProjectWithGivenNumberAndCreator() {
		assertTrue(database.containsProject(projectNumber));
	
	}

}
