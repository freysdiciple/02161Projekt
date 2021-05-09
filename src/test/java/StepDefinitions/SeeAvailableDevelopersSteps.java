package StepDefinitions;

import static org.junit.Assert.*;
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
	private String adminName = "MOG1";
	private DataBase database = DataBase.getInstance();
	private Project project;
	private List<Developer> availableDevelopers;
	private int startTime;
	private int endTime;
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	private Developer developer1;
	private Developer developer2;
	private Developer developer3;

//Main scenario
//Scenario: See available developers
//	Given there is an user with ID {string}
//  Given there is a project with ID {String}
//	Given the user is a Project leader
//  When the user provides information of the start time {string} and end time {string} of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot

	@Given("9- there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException,
			OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException, OutOfBoundsException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer = database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("9- there is a project with name {string}")
	public void thereIsAProject(String projectname) throws ProjectAlreadyExistsException, ProjectNotFoundException,
			NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectname);
		project = database.getProjectByName(projectname);
		assertTrue(database.containsProject(projectname));
	}

	@Given("9- the user is a Project leader")
	public void theUserIsAProjectLeader()
			throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));

	}
	
	@Given("9- there are other developers")
	public void thereIsOtherUsers() throws OutOfBoundsException, DeveloperNotFoundException {
		admin.createDeveloper("1111");
		admin.createDeveloper("2222");
		admin.createDeveloper("3333");
		
		developer1=database.getDeveloperById("1111");
		developer2=database.getDeveloperById("2222");
		developer3=database.getDeveloperById("3333");
	}
	
	

	@When("9- the user provides information of the start week {int} and end week {int} of the activity where he needs developers")
	public void theUserProvidesTimeSlot(int start, int end) throws OutOfBoundsException {
		
		startTime = start;
		endTime = end;

	}

	@Then("9- the system displays a list of available developers at the given time slot")
	public void theSystemProvidesListOfAvailableDevelopers() throws NotAuthorizedException, OutOfBoundsException {
		availableDevelopers = project.seeAvailableDevelopers(startTime, endTime, developer);
		for (Developer developer : availableDevelopers) {
			List<Activity> activities = developer.getActivities();
			int k=0;
			for (Activity activity : activities) {
				if (activity.getStartWeek() < startTime && activity.getEndWeek() > endTime) {
					k++;
				}
			assertTrue(k<21);
			}

		}

	}
	
	//# Alternate scenario one
	//Scenario: Given time not valid length
	//	Given 9- there is an user with ID "Søren"
	//	And 9- there is a project with ID "211234"
	//	And 9- the user is a Project leader
	//	When 9- the user provides invalid length input of the start week {int} and end week {int} of the activity where he needs developers
	//	Then 9- the system provides an error message that the start week and/or end week is invalid
	
	@When("9- the user provides invalid input of the start week {int} and end week {int} of the activity where he needs developers")
	public void theUserProvidesInvalidTimeInput(int start, int end) throws OutOfBoundsException, NotAuthorizedException {
		try {
			project.seeAvailableDevelopers(start, end, developer);
		}
		catch(OutOfBoundsException e) {
		errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
		

		
	@Then("9- the system provides an error message that the start week and\\/or end week is invalid")
	public void theSystemProvidesAnErrorMessageThatTheTimeInputIsInvalid() throws OutOfBoundsException {
		assertEquals("The start week and end week has to be an integer between 1 and 52", errorMessageHolder.getErrorMessage());
		
	}
}




