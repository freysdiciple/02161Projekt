import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import Exceptions.OperationNotAllowedException;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.Activity;
import SoftwareAS.Model.Developer;
import SoftwareAS.Model.Session;
import io.cucumber.java.en.*;

public class SessionSteps {
	
	
	ErrorMessageHolder errorMessageHolder;
	
	DataBase database;
	Activity activity;
	Developer developer;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
	public SessionSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("there is a developer")
	public void there_is_a_developer() {
	    developer = new Developer();
	}

	@Given("there is an activity")
	public void there_is_an_activity() {
	    activity = new Activity();
	}
	
	@Given("the developer is assigned to the activity")
	public void the_developer_is_assigned_to_the_activity() {
	    activity.assignDeveloper(developer);
	}
	
	@When("the developer registers a session")
	public void the_developer_registers_a_session() {
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		end.add(GregorianCalendar.DAY_OF_MONTH, 2);
	    developer.registerSession(activity, start, end);
	}

	@Then("the session is registered under the activity")
	public void the_session_is_registered_under_the_activity() {
		Session sessionInActivity = activity.getRegisteredSession().get(0);
	    assertTrue(sessionInActivity.getStartTime() == start && sessionInActivity.getEndTime() == end);
	}

	@Then("the session is registered under the developer")
	public void the_session_is_registered_under_the_developer() {
		Session sessionInDeveloper = developer.getRegisteredSessions().get(0);
	    assertTrue(sessionInDeveloper.getStartTime() == start && sessionInDeveloper.getEndTime() == end);
	}

	@Given("the developer registers a session")
	public void the_developer_registers_a_session() {
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		end.add(GregorianCalendar.DAY_OF_MONTH, 10);
	    developer.registerSession(activity, start, end);
	}

	@When("the developer registers another overlapping session")
	public void the_developer_registers_another_overlapping_session() {
	    start.add(GregorianCalendar.DAY_OF_MONTH, 5);
	    end.add(GregorianCalendar.DAY_OF_MONTH, 5);
	    
	    try {	    	
	    	developer.registerSession(activity, start, end);
	    }
	    catch(OperationNotAllowedException e){
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the system throws an OverlappingSessionsException")
	public void the_system_throws_an_overlapping_sessions_exception() {
	    assertEquals("Overlapping Sessions", errorMessageHolder.getErrorMessage());
	}

}
