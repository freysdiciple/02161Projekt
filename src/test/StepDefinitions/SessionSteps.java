import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import SoftwareAS.Model.Activity;
import SoftwareAS.Model.Developer;
import io.cucumber.java.en.*;

public class SessionSteps {
	
	DataBase database;
	Activity activity;
	Developer developer;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
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
	    assertTrue(activity);
	}

	@Then("the session is registered under the developer")
	public void the_session_is_registered_under_the_developer() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the developer registers a session")
	public void the_developer_registers_a_session() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the developer registers another overlapping session")
	public void the_developer_registers_another_overlapping_session() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the system throws an OverlappingSessionsException")
	public void the_system_throws_an_overlapping_sessions_exception() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
