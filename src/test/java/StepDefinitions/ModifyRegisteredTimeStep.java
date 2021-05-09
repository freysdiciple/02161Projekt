
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
	
	private Admin admin;
	private Developer developer;
	private Developer projectLeader;
	private Project project;
	private Activity activity;
	private Session session;
	private String sessionId;
	private GregorianCalendar startTime = new GregorianCalendar(2020, 6, 23, 16, 0);
	private GregorianCalendar endTime = new GregorianCalendar(2021, 6, 28, 16, 0);
	private String newStartTime = "24/07/2021 17-00";
	private String newEndTime = "27/08/2021 17-00";
	private GregorianCalendar newCalendarStartTime;
	private GregorianCalendar newCalendarEndTime;
	private DataBase database = DataBase.getInstance();
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
	public void theUserIsADeveloper(String developerName) throws DeveloperNotFoundException, AdminNotFoundException {
		database.createAdmin("Bob");
		admin = database.getAdminById("Bob");
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		assertTrue(database.containsDeveloper(developerName));
	}

	@Given("7- there is a project with id {string} created by Bob")
	public void thereIsAProjectWithId(String projectID) throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, NumberFormatException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
		project.assignDeveloperToProject(admin, developer);
		assertTrue(database.containsProject(projectID));
	}

	@Given("7- there is an activity with Id {int} created by the project leader {string}")
	public void thereIsAnActivityWithIdCreatedByProjectLeader(int activityID, String projectLeaderName) throws DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException {
		admin.createDeveloper(projectLeaderName);
		projectLeader = database.getDeveloperById(projectLeaderName);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(activityID, projectLeader);
		activity = project.getActivityById(activityID);
		assertTrue(project.containsActivityWithId(activityID));
	}

	@Given("7- the developer have registered time on that activity")
	public void theDeveloperHaveRegisteredTimeOnThatActivity() throws OperationNotAllowedException, DeveloperNotFoundException, OverlappingSessionsException, SessionNotFoundException {
		activity.assignDeveloperToActivity(projectLeader, developer);
		developer.registerSession(activity, startTime, endTime);
		sessionId = "ludwig70120206231602021628160";
		session = activity.getSessionById(sessionId);
		assertTrue(activity.containsSession(session));
		
	}

	
	@When("7- the developer removes the registered time on that activity")
	public void theDeveloperRemovesTheRegisteredTimeOnThatActivity() {
		session.delete();
	}

	@Then("7- the registered time is removed successfully")
	public void theRegisteredTimeIsRemovedSuccessfully() {
		assertFalse(activity.containsSession(session));
	}
	
//	Alternate scenario 1
//	Scenario: The developer changes the registered time on an activity the developer has registered
//	Given 7- the user "kludwig" is a developer 
//	And 7- there is a project with id "790123" created by Bob
//	And 7- there is an activity with Id 701 created by the project leader "Kenneth"
//	When 7- the developer changes the registered time on that activity
//	Then 7- the registered time is changed successfully
	
	
	
	@When("7- the developer changes start and end time of the session on that activity")
	public void theDeveloperChangesStartAndEndTimeOfTheRegisteredTimeOnThatActivity() throws SessionNotFoundException {
		session.changeStartTime(newStartTime);
		session.changeEndTime(newEndTime);
		newCalendarStartTime = session.getStartTime();
		newCalendarEndTime = session.getEndTime();
	}
	
	@Then("7- the registered time is changed successfully")
	public void theRegisteredTimeIsChangedSuccessfully() {
		
		assertEquals(2021, newCalendarStartTime.get(newCalendarStartTime.YEAR));
		assertEquals(7, newCalendarStartTime.get(newCalendarStartTime.MONTH));
		assertEquals(24, newCalendarStartTime.get(newCalendarStartTime.DATE));
		assertEquals(17, newCalendarStartTime.get(newCalendarStartTime.HOUR_OF_DAY));
		assertEquals(2021, newCalendarEndTime.get(newCalendarEndTime.YEAR));
		assertEquals(8, newCalendarEndTime.get(newCalendarEndTime.MONTH));
		assertEquals(27, newCalendarEndTime.get(newCalendarEndTime.DATE));
		assertEquals(17, newCalendarEndTime.get(newCalendarEndTime.HOUR_OF_DAY));
	}
	
}
