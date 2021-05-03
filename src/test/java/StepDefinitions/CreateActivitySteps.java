package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
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
//	private Developer developer2;
	private String adminID = "adminID";
	private int projectID = 123456;
	private String developerID = "createActivityDeveloper1ID";
	private int activityID = 925;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	
	//Scenario: Successfully create activity
	@Given("there is a project with project number 234234")
	public void thereIsAProject() throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException {
		errorMessageHolder.setErrorMessage("No Error Message Given (init)");
//		admin  = new Admin("adminID", database);
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
		assertTrue(database.containsProject(projectID));
	}
	
	@Given("there is a user with ID createActivityDeveloper1ID and database")
	public void thereIsAUserWithIDAndDataBase() throws DeveloperNotFoundException {
		//developer = new Developer("developerID", database);
		admin.createDeveloper(developerID);
		developer = database.getDeveloperById(developerID);
		assertTrue(database.containsDeveloper(developerID));
	}
	
	@Given("the user with ID createActivityDeveloper1ID is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ProjectNotFoundException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));
	}

	@When("the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project")
	public void theUserCreatesAnActivityInThatProject() throws ActivityAlreadyExistsException, ProjectNotFoundException {
		try {
			project.createActivity(activityID, developer);
		} catch (NotAuthorizedException | ActivityAlreadyExistsException e) {
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
	public void thereIsActivityWithName() throws NotAuthorizedException, ActivityAlreadyExistsException, ProjectNotFoundException {
		project.createActivity(activityID, developer);
		assertTrue(project.containsActivityWithId(activityID));
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
		//	When the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
		//	Then an ActivityAlreadyExistsException is thrown, createActivity
	
	
	//Scenario: Developer trying to create activity
	@Given("the user with ID createActivityDeveloper1ID is not a project leader")
	public void theUserIsNotAProjectLeader() throws DeveloperNotFoundException, OperationNotAllowedException, NotAuthorizedException {
		String developer2ID = "developer2ID";
		admin.createDeveloper(developer2ID);
		Developer developer2 = database.getDeveloperById(developer2ID);
		//developer2 = new Developer("Developer2ID", database);
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