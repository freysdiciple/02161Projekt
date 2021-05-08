package StepDefinitions;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;
import io.cucumber.java.en.*;

public class SessionSteps {
	
	
	ErrorMessageHolder errorMessageHolder;
	
	DataBase database;
	Project project;
	Admin admin;
	Activity activity;
	Developer developer;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
	public SessionSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("8 - there is a developer")
	public void there_is_a_developer8() {
	    developer = new Developer();
	    
	}

	@Given("8 - there is an activity1")
	public void there_is_an_activity1() throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		admin = new Admin("admin", database);
		admin.createProject(900000);
		project = database.getProjectById(900000);
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
	    project.createActivity(120000, developer);
	}
	
	@Given("8 - there is an activity2")
	public void there_is_an_activity2() throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		admin = new Admin("admin", database);
		admin.createProject(910000);
		project = database.getProjectById(910000);
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
	    project.createActivity(120000, developer);
	}
	
	@Given("8 - the developer is assigned to the activity")
	public void the_developer_is_assigned_to_the_activity8() throws OperationNotAllowedException, DeveloperNotFoundException, ActivityNotFoundException {
		activity = project.getActivityById(120000);
	    activity.assignDeveloperToActivity(developer, developer);
	}
	
	@When("8 - the developer registers a session")
	public void the_developer_registers_a_session8() throws OperationNotAllowedException, OverlappingSessionsException {
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		end.add(GregorianCalendar.HOUR_OF_DAY, 2);
	    developer.registerSession(activity, start, end);
	}

	@Then("8 - the session is registered under the activity")
	public void the_session_is_registered_under_the_activity8() {
		Session sessionInActivity = activity.getRegisteredSession().get(0);
	    assertTrue(sessionInActivity.getStartTime() == start && sessionInActivity.getEndTime() == end);
	}

	@Then("8 - the session is registered under the developer")
	public void the_session_is_registered_under_the_developer8() {
		Session sessionInDeveloper = developer.getRegisteredSessions().get(0);
	    assertTrue(sessionInDeveloper.getStartTime() == start && sessionInDeveloper.getEndTime() == end);
	}

	

	@When("8 - the developer registers another overlapping session")
	public void the_developer_registers_another_overlapping_session8() throws OperationNotAllowedException {
	    start.add(GregorianCalendar.HOUR_OF_DAY, 1);
	    end.add(GregorianCalendar.HOUR_OF_DAY, 1);
	    
	    try {	    	
	    	developer.registerSession(activity, start, end);
	    }
	    catch(OverlappingSessionsException e){
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("8 - the system throws an OverlappingSessionsException")
	public void the_system_throws_an_overlapping_sessions_exception8() {
	    assertEquals("Overlapping Sessions", errorMessageHolder.getErrorMessage());
	}

}
