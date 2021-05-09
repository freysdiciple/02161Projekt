package StepDefinitions;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

public class SeeAvailableDevelopersSteps {
	private Developer developer;
	private Admin admin;
	private String adminName = "Mogens";
	private DataBase database = DataBase.getInstance();
	private Project project;
	private List<Developer> availableDevelopers;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

//Main scenario
//Scenario: See available developers
//	Given there is an user with ID {string}
//  Given there is a project with ID {String}
//	Given the user is a Project leader
//  When the user provides information of the start time {string} and end time {string} of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot

	@Given("9- there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException,
			OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer = database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("9- there is a project with ID {int}")
	public void thereIsAProject(String projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException,
			NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectNumber);
		project = database.getProjectByName(projectNumber);
		assertTrue(database.containsProject(projectNumber));
	}

	@Given("9- the user is a Project leader")
	public void theUserIsAProjectLeader()
			throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));

	}

	@When("9- the user provides information of the start time {string} and end time {string} of the activity where he needs developers")
	public void theUserProvidesTimeSlot(String start, String end) throws OutOfBoundsException {
		
		startTime = InputHelper.stringToGregorianCalendar(start);
		endTime = InputHelper.stringToGregorianCalendar(start);

	}

	@Then("9- the system displays a list of available developers at the given time slot")
	public void theSystemProvidesListOfAvailableDevelopers() throws NotAuthorizedException {
		availableDevelopers = project.seeAvailableDevelopers(startTime, endTime, developer);
		for (Developer developer : availableDevelopers) {
			List<Session> sessions = developer.getRegisteredSessions();
			for (Session session : sessions) {
				assertTrue(session.getStartTime().compareTo(startTime) < 0 && session.getEndTime().compareTo(endTime) > 0);
			}

		}

	}
	
	//# Alternate scenario one
	//Scenario: Given time not valid length
	//	Given 9- there is an user with ID "Søren"
	//	And 9- there is a project with ID "211234"
	//	And 9- the user is a Project leader
	//	When 9- the user provides invalid length input of the start time {string} and end time {string} of the activity where he needs developers
	//	Then 9- the system provides an error message that the time is invalid
	
	@When("9- the user provides invalid length input of the start time {string} and end time {string} of the activity where he needs developers")
	public void theUserProvidesTooLongTimeInput(String start, String end) throws OutOfBoundsException {
		try {
			InputHelper.stringToGregorianCalendar(start);
		}
		catch(OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
			InputHelper.stringToGregorianCalendar(end);
		}
		catch(OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

		
	@Then("9- the system provides an error message that the length of the input time is invalid")
	public void theSystemProvidesAnErrorMessageThatTheTimeLengthIsInvalid() throws OutOfBoundsException {
		assertEquals("String must be a length of 16", errorMessageHolder.getErrorMessage());
		
	}

		
	
	@When("9- the user provides invalid format input of the start time {string} and end time {string} of the activity where he needs developers")
	public void theUserProvidesInvalidFormatTimeInput(String start, String end) throws OutOfBoundsException {
		try {
			InputHelper.stringToGregorianCalendar(start);
		}
		catch(OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
			InputHelper.stringToGregorianCalendar(end);
		}
		catch(OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("9- the system provides an error message that the format of the input time is invalid")
	public void theSystemProvidesAnErrorMessageThatTheTimeFormatIsInvalid() throws OutOfBoundsException {
		assertEquals("The time has to be compatible with GregorianCalendar", errorMessageHolder.getErrorMessage());
		
	}



	}


