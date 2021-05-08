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

public class AssignDeveloperToActivitySteps {
	private DataBase database = new DataBase();
	private Admin admin;
	private Project project;
	private Developer developer;
	private Developer developer2;
	private String adminID = "adminID";
	private String developerID = "assignDeveloperToActivityDeveloper1ID";
	private String developer2ID = "assignDeveloperToActivityDeveloper2ID";
	private int activityID = 924;
	private int activityID2 = 2;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

	//Scenario: Successfully assign developer to activity
	@Given("1- there is a project with project number {int}")
	public void thereIsAProject(int projectID) throws ProjectAlreadyExistsException, ProjectNotFoundException, AdminNotFoundException, NotAuthorizedException, OutOfBoundsException {
//		admin  = new Admin("adminID", database);
		//project = new Project(123123, admin);
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
		assertTrue(database.containsProject(projectID));
	}

	@Given("1- there is a user with ID assignDeveloperToActivityDeveloper1ID and database")
	public void thereIsAUserWithIDAndDataBase() throws DeveloperNotFoundException {
		admin.createDeveloper(developerID);
		developer = database.getDeveloperById(developerID);
		//developer = new Developer(developerID, database);
		assertTrue(database.containsDeveloper(developerID));
	}

	@Given("1- the user with ID assignDeveloperToActivityDeveloper1ID is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ProjectNotFoundException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));
	}

	@Given("1- there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database")
	public void thereIsASecondDeveloperWithIDAndDatabase() throws DeveloperNotFoundException {
		admin.createDeveloper(developer2ID);
		developer2 = database.getDeveloperById(developer2ID);
		//developer2 = new Developer(developer2ID, database);
		assertTrue(database.containsDeveloper(developer2ID));
	}

	@Given("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is part of the project")
	public void theSecondDeveloperIsPartOfTheProject() throws OperationNotAllowedException, ProjectNotFoundException {
		project.assignDeveloperToProject(admin, developer2);
		assertTrue(project.isDeveloperOnProject(developer2ID));
	}

	@Given("1- there is an activity with ID 924")
	public void thereIsAnActivity() throws NotAuthorizedException, ActivityAlreadyExistsException, DeveloperNotFoundException, OperationNotAllowedException, ProjectNotFoundException {
		if (project.isProjectLeader(developer)) {
			project.createActivity(activityID, developer);
		} else {
			String developer3ID = "developer3ID";
			admin.createDeveloper(developer3ID);
			Developer developer3 = database.getDeveloperById(developer3ID);
			//Developer developer3 = new Developer("developer3ID", database);
			project.assignDeveloperToProject(admin, developer3);
			project.setProjectLeader(admin, developer3);
			project.createActivity(activityID, developer3);
		}
		assertTrue(project.containsActivityWithId(activityID));
	}

	@When("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity")
	public void theSecondDeveloperIsAssignedToTheActivity() {
		try {
			project.getActivityById(activityID).assignDeveloperToActivity(developer, developer2);
		} catch(DeveloperNotFoundException | OperationNotAllowedException | ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is listed under the activity")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException {
		assertTrue(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2ID));
	}

		//Scenario: Successfully assign developer to activity
		//	Given there is a project with project number 123123
		//	And there is a user with ID assignDeveloperToActivityDeveloper1ID and database
		//	And the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
		//	And there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
		//	And the second developer with ID assignDeveloperToActivityDeveloper2ID is part of the project
		//	And there is an activity with ID 924
		//	When the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
		//	Then the second developer with ID assignDeveloperToActivityDeveloper2ID is listed under the activity

	//Scenario: Assign developer not on project to an activity
	@Given("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not part of the project")
	public void theSecondDeveloperIsNotPartOfTheProject() {
		assertFalse(project.isDeveloperOnProject(developer2ID));
	}

	@Then("1- a DeveloperNotFoundException is thrown, assignDeveloperToActivity")
	public void aDeveloperNotFoundExceptionIsThrown() {
		assertEquals("Developer not on project.", errorMessageHolder.getErrorMessage());
	}

	@Then("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under activity")
	public void theSecondDeveloperIsNotListedUnderActivity() throws ActivityNotFoundException {
		assertFalse(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2ID));
	}
		//Scenario: Assign developer not on project to an activity
		//	Given there is a project with project number 123123
		//	And there is a user with ID assignDeveloperToActivityDeveloper1ID and database
		//	And the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
		//	And there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
		//	And the second developer with ID assignDeveloperToActivityDeveloper2ID is not part of the project
		//	And there is an activity with ID 924
		//	When the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
		//	Then a DeveloperNotFoundException is thrown, assignDeveloperToActivity
		//	And the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under activity

	//Scenario: Assign a non-developer to an activity
//	@Given("there is not a developer with ID {String} and database {DataBase}")
//	public void thereIsNotADeveloperWithIDAndDataBase() {
//		assertFalse(project.isDeveloperOnProject(fakeDeveloperID));
//	}
//
//	@When("the developer is assigned to the activity")
//	public void theDeveloperIsAsignedToTheActivity() {
//		try {
//			project.getActivityById(activityID).assignDeveloperToActivity();
//		}
//	}
//
//	@Then("a DeveloperNotFoundException is thrown")
//	public void aDeveloperNotFoundExceptionIsThrown() {
//
//	} //can use the one created previously?
		//Scenario: Assign a non-developer to an activity
		//	Given there is a project
		//	And there is a user with ID {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is not a developer with ID {String} and database {DataBase}
		//	And there is an activity
		//	When the developer is assigned to the activity
		//	Then a DeveloperNotFoundException is thrown


	//Scenario: Assign developer to an activity that does not exist
	@Given("1- there is not an activity with ID 924")
	public void thereIsNotAnActivityWithID() {
		try {
			project.getActivityById(activityID2);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}

	@Then("1- an ActivityNotFoundException is thrown, assignDeveloperToActivity")
	public void anActivityNotFoundExceptionIsThrown() {
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Assign developer to an activity that does not exist
		//	Given there is a project with project number 123123
		//	And there is a user with ID assignDeveloperToActivityDeveloper1ID and database
		//	And the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
		//	And there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
		//	And there is not an activity with ID 924
		//	When the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
		//	Then an ActivityNotFoundException is thrown, assignDeveloperToActivity


	//Scenario: Developer assigns developer to an activity
	@Given("1- the user with ID assignDeveloperToActivityDeveloper1ID is not a project leader")
	public void theUserIsNotAProjectLeader() {
		assertFalse(project.isProjectLeader(developer));
	}

	@Then("1- an OperationNotAllowedException is thrown, assignDeveloperToActivity")
	public void anOperationNotAllowedExceptionIsThrown() {
		assertEquals("Only project leaders can assign to activity", errorMessageHolder.getErrorMessage());
	}

	@Then("1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under the activity")
	public void theSecondDeveloperIsNotListedUnderTheActivity() throws ActivityNotFoundException {
		assertFalse(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2ID));
	}
		//Scenario: Developer assigns developer to an activity
		//	Given there is a project with project number 123123
		//	And there is a user with ID assignDeveloperToActivityDeveloper1ID and database
		//	And the user with ID assignDeveloperToActivityDeveloper1ID is not a project leader
		//	And there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
		//	And the second developer with ID assignDeveloperToActivityDeveloper2ID is part of the project
		//	And there is an activity with ID 924
		//	When the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
		//	Then an OperationNotAllowedException is thrown, assignDeveloperToActivity
		//	And the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under the activity
}
