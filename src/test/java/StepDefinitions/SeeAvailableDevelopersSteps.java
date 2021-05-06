package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
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
	


//Main scenario   
//Scenario: See available developers
//	Given there is an user with ID {string}
//  Given there is a project with ID {int}
//	Given the user is a Project leader
//  When the user provides information of the time slot of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot
	
	@Given("there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer= database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}
	
	@Given("there is a project with ID {int}")
	public void thereIsAProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException {
		admin.createProject(projectNumber);
		project=database.getProjectById(projectNumber);
		assertTrue(database.containsProject(projectNumber));
	}
	
	
	@Given("the user is a Project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));
		
	}
	
	@When("the user provides information of the time slot of the activity where he needs developers")
	public void theUserProvidesTimeSlot() {
		
	}
	
	
	
	
}

	


