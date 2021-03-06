
package StepDefinitions;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;

import Exceptions.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;


//Markus
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
	private DataBase database;

	
	public ModifyRegisteredTimeStep(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
	}
	
	
//	Main scenario:


	@Given("7- the user {string} is a developer")
	public void theUserIsADeveloper(String developerName) throws DeveloperNotFoundException, AdminNotFoundException, OutOfBoundsException {
		database.createAdmin("Bob");
		admin = database.getAdminById("Bob");
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		assertTrue(database.containsDeveloper(developerName));
	}

	@Given("7- there is a project with id {string} created by Bob")
	public void thereIsAProjectWithId(String projectName) throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, NumberFormatException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectName);
		project = database.getProjectByName(projectName);
		project.assignDeveloperToProject(admin, developer);
		assertTrue(database.containsProject(projectName));
	}

	@Given("7- there is an activity with Id {int} created by the project leader {string}")
	public void thereIsAnActivityWithIdCreatedByProjectLeader(int activityID, String projectLeaderName) throws DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException, OutOfBoundsException {
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
		sessionId = "ludw70120206231602021628160";
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


	@When("7- the developer changes start and end time of the session on that activity")
	public void theDeveloperChangesStartAndEndTimeOfTheRegisteredTimeOnThatActivity() throws SessionNotFoundException, OutOfBoundsException {
		session.changeStartTime(newStartTime);
		session.changeEndTime(newEndTime);
		newCalendarStartTime = session.getStartTime();
		newCalendarEndTime = session.getEndTime();
	}
	
	@Then("7- the registered time is changed successfully")
	public void theRegisteredTimeIsChangedSuccessfully() {
		
		assertEquals(2021, newCalendarStartTime.get(GregorianCalendar.YEAR));
		assertEquals(7, newCalendarStartTime.get(GregorianCalendar.MONTH));
		assertEquals(24, newCalendarStartTime.get(GregorianCalendar.DATE));
		assertEquals(17, newCalendarStartTime.get(GregorianCalendar.HOUR_OF_DAY));
		assertEquals(2021, newCalendarEndTime.get(GregorianCalendar.YEAR));
		assertEquals(8, newCalendarEndTime.get(GregorianCalendar.MONTH));
		assertEquals(27, newCalendarEndTime.get(GregorianCalendar.DATE));
		assertEquals(17, newCalendarEndTime.get(GregorianCalendar.HOUR_OF_DAY));
	}
	
}
