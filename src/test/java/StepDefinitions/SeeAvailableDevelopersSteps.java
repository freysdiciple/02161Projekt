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
import SoftwareAS.Model.*;

public class SeeAvailableDevelopersSteps {
	private Developer developer;
	private Admin admin;
	private String adminName="Mogens";
	private DataBase database = new DataBase();
	private Project project;
	private List<Developer> availableDevelopers;



//Main scenario
//Scenario: See available developers
//	Given there is an user with ID {string}
//  Given there is a project with ID {int}
//	Given the user is a Project leader
//  When the user provides information of the start time {string} and end time {string} of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot

	@Given("9- there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer= database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("9- there is a project with ID {int}")
	public void thereIsAProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectNumber);
		project=database.getProjectById(projectNumber);
		assertTrue(database.containsProject(projectNumber));
	}


	@Given("9- the user is a Project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));

	}

	@When("9- the user provides information of the start time {string} and end time {string} of the activity where he needs developers")
	public void theUserProvidesTimeSlot(String start, String end) {
		
		
		GregorianCalendar startTime = new GregorianCalendar(year,month,day,hour,min);

			}
			
	
	@Then("9- the system displays a list of available developers at the given time slot")
	public void theSystemProvidesListOfAvailableDevelopers() throws NotAuthorizedException {
		availableDevelopers=project.seeAvailableDevelopers(startTime, endTime, developer);
		for (Developer developer : availableDevelopers) {
			
			
		}
		
		
	}
}
 
