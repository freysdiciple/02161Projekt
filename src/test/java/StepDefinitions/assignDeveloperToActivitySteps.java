package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

public class assignDeveloperToActivitySteps {
	private DataBase database;
	private Admin admin;
	private Project project;
	private Developer developer;
	private Developer developer2;
	private int activityID = 1;
	private ErrorMessageHolder errorMessageHolder;
	
	//why does the user have an id and a database, but the activity does not?
	
	//Scenario: Successfully assign developer to activity
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
	
	@Given("there is a second developer")
	public void thereIsASecondDeveloper() {
		developer2 = new Developer("developer2ID", database);
	}
	
	@Given("there is an activity")
	public void thereIsAnActivity() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, developer);
	}
	
	@When("the second developer is assigned to the activity")
	public void theSecondDeveloperIsAssignedToTheActivity() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException {
		project.getActivityById(activityID).assignDeveloper(developer, developer2);
	}

	@Then("the activity is listed under the project")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException {
		//assertTrue(project.getActivityById(activityID).contains(developer);
		//use for loop to check if developer is in the ArrayList developers?
		//create method in Activity.java to check if a developer is part of activity?
	}
		//Scenario: Successfully assign developer to activity
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is a second developer
		//	And there is an activity
		//	When the second developer is assigned to the activity
		//	Then the developer is listen under the activity

	//Scenario: Assign developer to an activity that does not exist
	@Given("there is not an activity ")
	public void theUserCreatesProject() throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException {
		project.getActivityById(activityID).assignDeveloper(developer, developer2);
	}
		//Scenario: Assign developer to an activity that does not exist
		//	Given there is a project
		//	And there is a user with id {String} and database {DataBase}
		//	And the user is a project leader
		//	And there is a second developer
		//	And there is not an activity with ID {int}
		//	When the developer is assigned to the activity
		//	Then a NoActivityException is thrown
}