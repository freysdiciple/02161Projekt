package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
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
	private int activityID = 1;
	private int activityID2 = 2;
	private ErrorMessageHolder errorMessageHolder;
	
	//Scenario: Successfully create activity
	@Given("there is a project")
	public void thereIsAProject() {
		admin  = new Admin("adminID", database);
		project = new Project(012345, admin);
	}
	
	@Given("there is a user with id {String} and database {DataBase}")
	public void thereIsAUserWithIDAndDataBase() {
		developer = new Developer("developerID", database);
	}
	
	@Given("the user is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException {
		project.assignDeveloper(admin, developer);
		project.setProjectLeader(developer);
	}

	@When("the user creates an activity in that project")
	public void theUserCreatesProject() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, developer);
	}

	@Then("the activity is listed under the project")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException {
		assertTrue(project.containsActivityWithId(activityID));
	}
		//Scenario: Successfully create activity
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	When the user creates an activity in that project
		//	Then the activity is listed under the project

	//Scenario: Duplicate name	
	@Given("there is an activity with the ID {int}")
	public void thereIsActivityWithName() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, developer);
		//is it possible to use the previous @When("the user creates an activity in that project")?
	}

	@When("an activity with the same ID is created")
	public void anActivityWithTheSameIDIsCreated() throws NotAuthorizedException, ActivityNotFoundException {
		try {
			project.createActivity(activityID, developer);
		} catch (ActivityAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("an ActivityAlreadyExistsException is thrown")
	public void anActivityAlreadyExistsExceptionIsThrown() {
		assertEquals("An activity with that ID already exists.", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Duplicate name
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is an activity with the ID {int}
		//	When an activity with the same ID is created
		//	Then an ActivityAlreadyExistsException is thrown
	
	
	//Scenario: Developer trying to create activity
	@Given("the user is not a project leader")
	public void theUserIsNotAProjectLeader() throws OperationNotAllowedException {
		developer2 = new Developer("Developer2ID", database);
		project.assignDeveloper(admin, developer2);
		project.setProjectLeader(developer2);
		assertFalse(project.isProjectLeader(developer));
	}
	
	@When("the user creates an activity in that project")
	public void theUserCreatesAnActivityInThatProject() throws ActivityAlreadyExistsException, ActivityNotFoundException {
		try {
			//is it necessary to use a new ID, since the old one is not in the givens?
			project.createActivity(activityID2, developer);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("a NotAuthorizedException is thrown")
	public void aNotAuthorizedExceptionIsThrown() {
		assertEquals("Only the project leader can create activities.", errorMessageHolder.getErrorMessage());
	}
	
	@Then("the activity is not created")
	public void theActivityIsNotCreated() throws ActivityNotFoundException {
		assertFalse(project.containsActivityWithId(activityID2));
		//assertTrue(!project.containsActivityWithId(activityID2));
	}
		//Scenario: Developer trying to create activity
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is not a project leader
		//	When the user creates an activity in that project
		//	Then a NotAuthorizedException is thrown
		//	And the activity is not created
}