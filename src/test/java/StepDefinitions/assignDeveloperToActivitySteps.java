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

/*
public class assignDeveloperToActivitySteps {
	private DataBase database;
	private Admin admin;
	private Project project;
	private Developer developer;
	private Developer developer2;
	private String developerID = "developerID";
	private String developer2ID = "developer2ID";
	private String fakeDeveloperID = "thisDeveloperDoesNotExist";
	private int activityID = 1;
	private int activityID2 = 2;
	private ErrorMessageHolder errorMessageHolder;
	
	//why does the user have an id and a database, but the activity does not?
	//instead of using the developer object when assigning project leader and assigning someone to an activity, should instead use the id (something like: project.getDeveloperWithID(developerID))
	
	//Scenario: Successfully assign developer to activity
	@Given("there is a project")
	public void thereIsAProject() {
		admin  = new Admin("adminID", database);
		project = new Project(012345, admin);
	}
	
	@Given("there is a user with id developerID and database")
	public void thereIsAUserWithIDAndDataBase() {
		developer = new Developer(developerID, database);
	}
	
	@Given("the user is a project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(developer);
	}
	
	@Given("there is a second developer with id developer2ID and database")
	public void thereIsASecondDeveloperWithIDAndDatabase() {
		developer2 = new Developer(developer2ID, database);
	}
	
	@Given("the second developer is part of the project")
	public void theSecondDeveloperIsPartOfTheProject() throws OperationNotAllowedException {
		project.assignDeveloperToProject(developer, developer2);
	}
	
	@Given("there is an activity")
	public void thereIsAnActivity() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, developer);
	}
	
	@When("the second developer is assigned to the activity")
	public void theSecondDeveloperIsAssignedToTheActivity() throws ActivityAlreadyExistsException, ActivityNotFoundException {
		try {
			project.getActivityById(activityID).assignDeveloperToActivity(developer, developer2);
		} catch(DeveloperNotFoundException | OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the second developer is listed under the activity")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException, DeveloperNotFoundException {
		assertTrue(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2ID));
	}
	
		//Scenario: Successfully assign developer to activity
		//	Given there is a project
		//	And there is a user with id {String} and database
		//	And the user is a project leader
		//	And there is a second developer with id {String} and database {DataBase}
		//	And the second developer is part of the project
		//	And there is an activity
		//	When the second developer is assigned to the activity
		//	Then the second developer is listed under the activity
	
	//Scenario: Assign developer not on project to an activity
	@Given("the second developer is not part of the project")
	public void theSecondDeveloperIsNotPartOfTheProject() throws OperationNotAllowedException {
		assertFalse(project.isDeveloperOnProject(developer2ID));
	}
	
	@Then("a DeveloperNotFoundException is thrown")
	public void aDeveloperNotFoundExceptionIsThrown() {
		assertEquals("Developer not on project.", errorMessageHolder.getErrorMessage());
	}
	
	@Then("the second developer is not listed under activity")
	public void theSecondDeveloperIsNotListedUnderActivity() throws ActivityNotFoundException {
		assertFalse(project.getActivityById(activityID).isDeveloperOnAcitivty(developer2ID));
	}
		//Scenario: Assign developer not on project to an activity
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is a second developer with id {String} and database {DataBase}
		//	And the second developer is not part of the project
		//	And there is an activity
		//	When the second developer is assigned to the activity
		//	Then a DeveloperNotFoundException is thrown
		//	And the second developer is not listed under activity
	
	//Scenario: Assign a non-developer to an activity
//	@Given("there is not a developer with id {String} and database {DataBase}")
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
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is not a developer with id {String} and database {DataBase}
		//	And there is an activity
		//	When the developer is assigned to the activity
		//	Then a DeveloperNotFoundException is thrown
	
	
	//Scenario: Assign developer to an activity that does not exist
	@Given("there is not an activity with ID {int}")
	public void thereIsNotAnActivityWithID() {
		try {
			project.getActivityById(activityID2);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}
	
	@When("the developer is assigned to the activity")
	public void theDeveloperIsAssignedToTheActivity() throws OperationNotAllowedException, DeveloperNotFoundException {
		try {
			project.getActivityById(activityID2).assignDeveloperToActivity(developer, developer2);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("an ActivityNotFoundException is thrown")
	public void anActivityNotFoundExceptionIsThrown() {
		assertEquals("No activity with described ID", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Assign developer to an activity that does not exist
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is a second developer
		//	And there is not an activity with ID {int}
		//	When the developer is assigned to the activity
		//	Then an ActivityNotFoundException is thrown
	
	
	//Scenario: Developer assigns developer to an activity
	@Given("the user is not a project leader")
	public void theUserIsNotAProjectLeader() {
		assertFalse(project.isProjectLeader(developer));
	}
	
	@Then("an OperatonNotAllowedException is thrown")
	public void anOperationNotAllowedExceptionIsThrown() {
		assertEquals("Only project leaders can assign to activity", errorMessageHolder.getErrorMessage());
	}
		//Scenario: Developer assigns developer to an activity
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is not a project leader
		//	And there is a second developer with id {String} and database {DataBase}
		//	And the second developer is part of the project
		//	And there is an activity
		//	When the second developer is assigned to the activity
		//	Then an OperationNotAllowedException is thrown
		//	And the second developer is not listed under the activity	
}
*/