package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.ActivityNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class CreateActivitySteps {
	private Developer developer;
	private Project project;
	private DataBase database;
	private int activityID = 1;

	@Given("there is a project")
	public void thereIsAProject() {
		 project = new Project(012345, new Admin("adminID", database));
	}
	
	@Given("there is a user with id {String} and database {DataBase}")
	public void thereIsAUserWithIDAndDataBase() {
		developer = new Developer("developerID", database);
		project.setProjectLeader(developer);
	}
	
	@Given("the user is a project leader")
	public void theUserIsAProjectLeader() {
		assertTrue(project.isProjectLeader(developer));
	}

	@When("the user creates an activity in that project")
	public void theUserCreatesProject() {
		project.createActivity(activityID);
	}

	@Then("the activity is listed under the project")
	public void activityIsListedUnderTheProject() throws ActivityNotFoundException {
		assertTrue(project.containsActivityWithId(activityID));
	}
}

//Scenario: Successfully create activity
//	Given there is a project
//	And there is a user with id {String} and database {DataBase}
//	And the user is a project leader
//	When the user creates an activity in that project
//	Then the activity is listed under the project