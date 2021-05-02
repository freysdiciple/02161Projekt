package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

public class CreateActivitySteps {
	private DataBase database;
	private Admin admin;
	private Project project;
	private Developer developer;
	private Developer developer2;
	private int activityID = 925;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	
	//Scenario: Successfully create activity
	@Given("there is a project with project number 234234")
	public void thereIsAProject() {
		errorMessageHolder.setErrorMessage("No Error Message Given (init)");
		admin  = new Admin("adminID", database);
		project = new Project(123456, admin);
	}
	
	@Given("there is a user with ID createActivityDeveloper1ID and database")
	public void thereIsAUserWithIDAndDataBase() {
		developer = new Developer("developerID", database);
	}
	
	@Given("the user with ID createActivityDeveloper1ID is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
	}

	@When("the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project")
	public void theUserCreatesAnActivityInThatProject() throws ActivityAlreadyExistsException {
		try {
			project.createActivity(activityID, developer);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity with ID 925 is listed under the project")
	public void activityIsListedUnderTheProject() {
		assertTrue(project.containsActivityWithId(activityID));
	}
		//Scenario: Successfully create activity
		//	Given there is a project with project number 234234
		//	And there is a user with ID createActivityDeveloper1ID and database
		//	And the user with ID createActivityDeveloper1ID is a project leader
		//	When the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
		//	Then the activity with ID 925 is listed under the project

	//Scenario: Duplicate name	
	@Given("there is an activity with ID 925")
	public void thereIsActivityWithName() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, developer);
	}

	@When("an activity with ID 925 is created")
	public void anActivityWithTheSameIDIsCreated() throws NotAuthorizedException, ActivityNotFoundException {
		try {
			project.createActivity(activityID, developer);
		} catch (ActivityAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("an ActivityAlreadyExistsException is thrown, createActivity")
	public void anActivityAlreadyExistsExceptionIsThrown() {
		assertEquals("An activity with that ID already exists.", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Duplicate name
		//	Given there is a project with project number 234234
		//	And there is a user with ID createActivityDeveloper1ID and database
		//	And the user with ID createActivityDeveloper1ID is a project leader
		//	And there is an activity with ID 925
		//	When an activity with ID 925 is created
		//	Then an ActivityAlreadyExistsException is thrown, createActivity
	
	
	//Scenario: Developer trying to create activity
	@Given("the user with ID createActivityDeveloper1ID is not a project leader")
	public void theUserIsNotAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		developer2 = new Developer("Developer2ID", database);
		project.assignDeveloperToProject(admin, developer2);
		project.setProjectLeader(admin, developer2);
		assertFalse(project.isProjectLeader(developer));
	}
	
	@Then("a NotAuthorizedException is thrown, createActivity")
	public void aNotAuthorizedExceptionIsThrown() {
		assertEquals("Only the project leader can create activities.", errorMessageHolder.getErrorMessage());
	}
	
	@Then("an activity with ID 925 is not created")
	public void theActivityIsNotCreated() throws ActivityNotFoundException {
		assertFalse(project.containsActivityWithId(activityID));
	}
		//Scenario: Developer trying to create activity
		//	Given there is a project with project number 234234
		//	And there is a user with ID createActivityDeveloper1ID and database
		//	And the user with ID createActivityDeveloper1ID is not a project leader
		//	When the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
		//	Then a NotAuthorizedException is thrown, createActivity
		//	And an activity with ID 925 is not created
}