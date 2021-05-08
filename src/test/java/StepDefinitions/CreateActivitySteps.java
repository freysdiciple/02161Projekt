package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

public class CreateActivitySteps {
	private DataBase database = new DataBase();
	private Admin admin;
	private Project project;
	private Developer developer;
	private String adminID = "adminID";
	private int activityID;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

	//Scenario: Successfully create activity
	@Given("4- there is a project with project ID {string}")
	public void thereIsAProject(String projectID) throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException, NotAuthorizedException, OutOfBoundsException {
		errorMessageHolder.setErrorMessage("No Error Message Given (init)");
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
		assertTrue(database.containsProject(projectID));
	}

	@Given("4- there is a user with ID {string} and database")
	public void thereIsAUserWithIDAndDataBase(String developerID) throws DeveloperNotFoundException {
		admin.createDeveloper(developerID);
		developer = database.getDeveloperById(developerID);
		assertTrue(database.containsDeveloper(developerID));
	}

	@Given("4- the user is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ProjectNotFoundException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));
	}

	@When("4- the user creates an activity with ID {int} in that project")
	public void theUserCreatesAnActivityInThatProject(int activityID) throws ActivityAlreadyExistsException, ProjectNotFoundException {
		this.activityID = activityID;
		try {
			project.createActivity(activityID, developer);
		} catch (NotAuthorizedException | ActivityAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("4- the activity is listed under the project")
	public void activityIsListedUnderTheProject() {
		assertTrue(project.containsActivityWithId(activityID));
	}
		//Scenario: Successfully create activity
		//	Given 4- there is a project with project ID 123456
		//	And 4- there is a user with ID "John" and database
		//	And 4- the user is a project leader
		//	When 4- the user creates an activity with ID 925 in that project
		//	Then 4- the activity is listed under the project

	//Scenario: Duplicate name
	@Given("4- there is an activity with ID {int}")
	public void thereIsActivityWithName(int activityID) throws NotAuthorizedException, ActivityAlreadyExistsException, ProjectNotFoundException {
		project.createActivity(activityID, developer);
		assertTrue(project.containsActivityWithId(activityID));
	}

	@Then("4- an ActivityAlreadyExistsException is thrown")
	public void anActivityAlreadyExistsExceptionIsThrown() {
		assertEquals("An activity with that ID already exists.", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Duplicate name
		//	Given 4- there is a project with project ID 123456
		//	And 4- there is a user with ID "John" and database
		//	And 4- the user is a project leader
		//	And 4- there is an activity with ID 925
		//	When 4- the user creates an activity with ID 925 in that project
		//	Then 4- an ActivityAlreadyExistsException is thrown


	//Scenario: Developer trying to create activity
	@Given("4- the user is not a project leader")
	public void theUserIsNotAProjectLeader() throws DeveloperNotFoundException, OperationNotAllowedException, NotAuthorizedException {
		String developer2ID = "developer2ID";
		admin.createDeveloper(developer2ID);
		Developer developer2 = database.getDeveloperById(developer2ID);
		project.assignDeveloperToProject(admin, developer2);
		project.setProjectLeader(admin, developer2);
		assertFalse(project.isProjectLeader(developer));
	}

	@Then("4- a NotAuthorizedException is thrown")
	public void aNotAuthorizedExceptionIsThrown() {
		assertEquals("Only the project leader can create activities.", errorMessageHolder.getErrorMessage());
	}

	@Then("4- the activity is not created")
	public void theActivityIsNotCreated() throws ActivityNotFoundException {
		assertFalse(project.containsActivityWithId(activityID));
	}
		//Scenario: Developer trying to create activity
		//	Given 4- there is a project with project ID 123456
		//	And 4- there is a user with ID "John" and database
		//	And 4- the user is not a project leader
		//	When 4- the user creates an activity with ID 925 in that project
		//	Then 4- a NotAuthorizedException is thrown
		//	And 4- the activity is not created
}
