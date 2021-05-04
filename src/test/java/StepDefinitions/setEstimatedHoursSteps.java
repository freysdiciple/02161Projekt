package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.*;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

public class setEstimatedHoursSteps {
	private Developer projectLeader;
	private Developer developer;
	private Admin admin;
	private String adminName = "Mogens";
	private DataBase database = new DataBase();
	private Project project;
	private Activity activity;
	private int time;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

//	Scenario: Successfully set estimated work hours
//		Given there is an user with ID "Søren"
//	    And there is a project with ID 100
//		And the user is a Project leader
//		And there is an activity with ID 500
//		When the user provides the estimated hours for the activity
//		Then the estimated hours for the activity is set

	@Given("there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException,
			OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("there is a project with ID {int}")
	public void thereIsAProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber);
		project = database.getProjectById(projectNumber);
		assertTrue(database.containsProject(projectNumber));
	}

	@Given("the user {string} is a Project leader")
	public void theUserIsAProjectLeader(String userName)
			throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		projectLeader = database.getDeveloperById(userName);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		assertTrue(project.isProjectLeader(projectLeader));
	}

	@Given("there is an activity with ID {int}")
	public void thereIsAnActivityWithID(int activityID)
			throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		project.createActivity(activityID, projectLeader);
		activity = project.getActivityById(activityID);
		assertTrue(project.containsActivityWithId(activityID));

	}

	@When("the user provides the estimated hours {int} for the activity")
	public void theUserProvidesTheEstimatedHours(int time) throws NotAuthorizedException {
		this.time = time;
		try {
			activity.setEstimatedWorkHours(time, projectLeader, project);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

	}

	@Then("the estimated hours for the activity is set")
	public void theEstimatedHoursForTheActivityIsSet() {
		assertEquals(activity.getEstimatedWorkHours(), time);

	}

	
	//Scenario: Successfully change estimated work hours
	@Given("the estimated hours are set to {int} for the activity")
	public void theUserProvidesTheEstimatedHoursForTheActivity(int time) throws NotAuthorizedException {
		activity.setEstimatedWorkHours(time, projectLeader, project);
		assertTrue(activity.getEstimatedWorkHours() == time);
	}
	
	@When("the user changes the estimated hours to {int} for the activity")
	public void theUserChangesTheEstimatedHoursForTheActivity(int time) throws NotAuthorizedException {
		this.time = time;
		activity.setEstimatedWorkHours(time, projectLeader, project);
	}
	
	@Then("the estimated hours are the new hours")
	public void theEstimatedHoursAreTheNewHours() {
		assertTrue(activity.getEstimatedWorkHours() == this.time);
	}
		//Scenario: Successfully change estimated work hours
		//	Given there is an user with ID "Søren"
		//  And there is a project with ID 101
		//	And the user "Søren" is a Project leader
		//	And there is an activity with ID 501
		//	And there is an estimated hours set on the activity
		//	When the user changes the estimated hours for the activity
		//	Then the estimated hours are the new hours
	
	
//	#Scenario: Developer trying to set estimated work hours
//	#	Given there is a project with Id 
//	#	And the user is not a project leader
//	#	And there is an activity
//	#	When the user provides the estimated hours for the activity
//	#	Then a NotAuthorizedException is thrown
//	#	And the estimated work hours is not set

}

// TEST TEST
