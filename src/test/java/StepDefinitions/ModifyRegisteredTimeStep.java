
package StepDefinitions;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;

public class ModifyRegisteredTimeStep {
	/*
	private Admin admin;
	private Developer developer0;
	private Developer developer;
	private Developer projectLeader;
	private Project project;
	private Activity activity;
	private Session session;
	private GregorianCalendar startTime = new GregorianCalendar(2021, 6, 27, 16, 00);
	private GregorianCalendar endTime = new GregorianCalendar(2021, 6, 29, 16, 00);
	private DataBase database = new DataBase();
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

//	Main scenario:
//	#Scenario: The developer removes the registered time on an activity the developer has registered
//	#   Given the user {string} is a developer
//	#	And there is a project
//	#	And there is an activity
//	#	And the developer have registered time on that activity
//	#	When the developer removes the registered time on that activity
//	#	Then the registered time is removed successfully

	@Given("7- the user {string} is a developer")
	public void theUserIsADeveloper(String developerName) throws DeveloperNotFoundException {
		admin = new Admin("Bob", database);
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		assertTrue(database.containsDeveloper(developerName));
	}

	@Given("7- there is a project with id {int} created by Bob")
	public void thereIsAProjectWithId801(int projectID) throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException {
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
		project.assignDeveloperToProject(admin, developer);
		assertTrue(database.containsProject(projectID));
	}

	@Given("7- there is an activity with id {int} created by Tobi")
	public void thereIsAnActivity(int activityID) throws DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException {
		admin.createDeveloper("Knut");
		projectLeader = database.getDeveloperById("Knut");
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(activityID, projectLeader);
		activity = project.getActivityById(activityID);
		assertTrue(project.containsActivityWithId(activityID));
	}

	@Given("7- the developer have registered time on that activity")
	public void theDeveloperHaveRegisteredTimeOnThatActivity() throws OperationNotAllowedException, DeveloperNotFoundException, OverlappingSessionsException {
		activity.assignDeveloperToActivity(projectLeader, developer);
		developer.registerSession(activity, startTime, endTime);


	}

	//@When("7- the developer removes the registered time on")

	*/

}
