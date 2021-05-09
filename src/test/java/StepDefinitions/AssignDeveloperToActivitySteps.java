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
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

public class AssignDeveloperToActivitySteps {
	private DataBase database;
	private Admin admin;
	private Project project;
	private Developer developer;
	private Developer developer2;
	private String adminID = "adminID";
	private int activityID;
	private ErrorMessageHolder errorMessageHolder;
	
	public AssignDeveloperToActivitySteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
    	this.database = softwareAS.getDataBase();
    	this.errorMessageHolder = errorMessageHolder;
    }

	//Scenario: Successfully assign developer to activity
	@Given("1- there is a project with project name {string}")
	public void thereIsAProject(String projectName) throws AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject(projectName);
		project = database.getProjectByName(projectName);
		assertTrue(database.containsProject(projectName));
	}

	@Given("1- there is a user with ID {string} and database")
	public void thereIsAUserWithIDAndDataBase(String developerID) throws DeveloperNotFoundException {
		admin.createDeveloper(developerID);
		developer = database.getDeveloperById(developerID);
		assertTrue(database.containsDeveloper(developerID));
	}

	@Given("1- the user is a project leader")
	public void theUserIsAProjectLeader() throws NotAuthorizedException, DeveloperNotFoundException  {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));
	}

	@Given("1- there is a second developer with ID {string} and database")
	public void thereIsASecondDeveloperWithIDAndDatabase(String developer2ID) throws DeveloperNotFoundException {
		admin.createDeveloper(developer2ID);
		developer2 = database.getDeveloperById(developer2ID);
		assertTrue(database.containsDeveloper(developer2ID));
	}

	@Given("1- the second developer is part of the project")
	public void theSecondDeveloperIsPartOfTheProject() throws OperationNotAllowedException, ProjectNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer2);
		assertTrue(project.isDeveloperOnProject(developer2.getId()));
	}

	@Given("1- there is an activity with ID {int}")
	public void thereIsAnActivity(int activityID) throws NotAuthorizedException, ActivityAlreadyExistsException, DeveloperNotFoundException  {
		this.activityID = activityID;
		if (project.isProjectLeader(developer)) {
			project.createActivity(activityID, developer);
		} else {
			String developer3ID = "developer3ID";
			admin.createDeveloper(developer3ID);
			Developer developer3 = database.getDeveloperById(developer3ID);
			project.assignDeveloperToProject(admin, developer3);
			project.setProjectLeader(admin, developer3);
			project.createActivity(activityID, developer3);
		}
		assertTrue(project.containsActivityWithId(activityID));
	}

	@When("1- the second developer is assigned to the activity")
	public void theSecondDeveloperIsAssignedToTheActivity() {
		try {
			project.getActivityById(activityID).assignDeveloperToActivity(developer, developer2);
		} catch(DeveloperNotFoundException | OperationNotAllowedException | ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("1- the second developer is listed under the activity")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException {
		assertTrue(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2.getId()));
	}
		//Scenario: Successfully assign developer to activity
		//	Given 1- there is a project with project ID 123456
		//	And 1- there is a user with ID "John" and database
		//	And 1- the user is a project leader
		//	And 1- there is a second developer with ID "James" and database
		//	And 1- the second developer is part of the project
		//	And 1- there is an activity with ID 924
		//	When 1- the second developer is assigned to the activity
		//	Then 1- the second developer is listed under the activity
	
	
	//Scenario: Assign developer not on project to an activity
	@Given("1- the second developer is not part of the project")
	public void theSecondDeveloperIsNotPartOfTheProject() {
		assertFalse(project.isDeveloperOnProject(developer2.getId()));
	}

	@Then("1- a DeveloperNotFoundException is thrown")
	public void aDeveloperNotFoundExceptionIsThrown() {
		assertEquals("Developer not on project.", errorMessageHolder.getErrorMessage());
	}

	@Then("1- the second developer is not listed under activity")
	public void theSecondDeveloperIsNotListedUnderActivity() throws ActivityNotFoundException {
		assertFalse(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2.getId()));
	}
		//Scenario: Assign developer not on project to an activity
		//	Given 1- there is a project with project ID 123456
		//	And 1- there is a user with ID "John" and database
		//	And 1- the user is a project leader
		//	And 1- there is a second developer with ID "James" and database
		//	And 1- the second developer is not part of the project
		//	And 1- there is an activity with ID 924
		//	When 1- the second developer is assigned to the activity
		//	Then 1- a DeveloperNotFoundException is thrown
		//	And 1- the second developer is not listed under activity

	//Scenario: Assign developer to an activity that does not exist
	@Given("1- there is not an activity with ID {int}")
	public void thereIsNotAnActivityWithID(int activity2ID) {
		try {
			project.getActivityById(activity2ID);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}

	@Then("1- an ActivityNotFoundException is thrown")
	public void anActivityNotFoundExceptionIsThrown() {
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Assign developer to an activity that does not exist
		//	Given 1- there is a project with project ID 123456
		//	And 1- there is a user with ID "John" and database
		//	And 1- the user is a project leader
		//	And 1- there is a second developer with ID "James" and database
		//	And 1- there is not an activity with ID 924
		//	When 1- the second developer is assigned to the activity
		//	Then 1- an ActivityNotFoundException is thrown


	//Scenario: Developer assigns developer to an activity
	@Given("1- the user is not a project leader")
	public void theUserIsNotAProjectLeader() {
		assertFalse(project.isProjectLeader(developer));
	}

	@Then("1- an OperationNotAllowedException is thrown")
	public void anOperationNotAllowedExceptionIsThrown() {
		assertEquals("Only a project leader or admin can assign developer to activity", errorMessageHolder.getErrorMessage());
	}

	@Then("1- the second developer is not listed under the activity")
	public void theSecondDeveloperIsNotListedUnderTheActivity() throws ActivityNotFoundException {
		assertFalse(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2.getId()));
	}
		//Scenario: Developer assigns developer to an activity
		//	Given 1- there is a project with project ID 123456
		//	And 1- there is a user with ID "John" and database
		//	And 1- the user is not a project leader
		//	And 1- there is a second developer with ID "James" and database
		//	And 1- the second developer is part of the project
		//	And 1- there is an activity with ID 924
		//	When 1- the second developer is assigned to the activity
		//	Then 1- an OperationNotAllowedException is thrown
		//	And 1- the second developer is not listed under the activity
}
