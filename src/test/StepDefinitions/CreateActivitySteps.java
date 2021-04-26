
import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class CreateActivitySteps {
	private Developer developer;
	private Developer developer2;
	private Project project;
	private DataBase database;
	private int activityID = 1;
	private int activityID2 = 2;

//Scenario: Successfully create activity
	@Given("there is a project")
	public void thereIsAProject() {
		 project = new Project(012345, new Admin("adminID", database));
	}
	
	@Given("there is a user with id {String} and database {DataBase}")
	public void thereIsAUserWithIDAndDataBase() {
		developer = new Developer("developerID", database);
		
	}
	
	@Given("the user is a project leader")
	public void theUserIsAProjectLeader() {
		project.setProjectLeader(developer); //moved here from @Given("there is a user with id {String} and database {DataBase}") because of scenario where there is user that is not the project leader
		assertTrue(project.isProjectLeader(developer));
	}

	@When("the user creates an activity in that project")
	public void theUserCreatesProject() {
		project.createActivity(activityID);
	}

	@Then("the activity is listed under the project")
	public void activityIsListedUnderTheProject() {
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
	public void thereIsActivityWithName() {
		project.createActivity(activityID);
		//is it possible to use the previous @When("the user creates an activity in that project")
	}

	@When("an activity with the same name is created")
	public void anActivityWithTheSameNameIsCreated() {
		project.createActivity(activityID);
		//What should be in this when?
	}
	
	@Then("a DupliceNameException is thrown")
	public void aDupliceNameExceptionIsThrown() {
		//how to assert and error message was thrown?
	}
//Scenario: Duplicate name
//	Given there is a project
//	And there is a user with id {String} and database {DataBase}
//	And the user is a project leader
//	And there is an activity with the ID {int}
//	When an activity with the same name is created
//	Then a DuplicateNameException is thrown
	
	
//Scenario: Developer trying to create activity
	@Given("the user is not a project leader")
	public void theUserIsNotAProjectLeader() {
		developer2 = new Developer("Developer2ID", database);
		project.setProjectLeader(developer2);
		//is there a way to just unassign the project leader
		//perhaps I shouold create a new developer that isn't already set a project leader, but then can't use previous one?
	}
	
	@When("the user creates an activity in that project")
	public void theUserCreatesAnActivityInThatProject() {
		project.createActivity(activityID2);
	}
	
	@Then("a NotAuthorizedException is thrown")
	public void aNotAuthorizedExceptionIsThrown() {
		//how to do this properly?
	}
	
	@Then("the activity is not created")
	public void theActivityIsNotCreated() {
		assertTrue(!project.containsActivityWithId(activityID2));
		//assertFalse(project.containsActivityWithId(activityID2));
	}
	
//Scenario: Developer trying to create activity
//	Given there is a project
//	And there is a user with id {String} and database {DataBase}
//	And the user is not a project leader
//	When the user creates an activity in that project
//	Then a NotAuthorizedException is thrown
//	And the activity is not created
}