package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.*;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

public class setEstimatedHoursSteps {
	private Developer developer;
	private Admin admin;
	private String adminName="Mogens";
	private DataBase database = new DataBase();
	private Project project;
	// Hej
//	Scenario: Successfully set estimated work hours
//		Given there is a project
//		And the user is a project leader
//		And there is an activity
//		When the user provides the estimated hours for the activity
//		Then the estimated hours for the activity is set
	
	@Given("there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer= database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}
	
	@Given("there is a project with ID {int}")
	public void thereIsAProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException {
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	#Scenario: Developer trying to set estimated work hours
//	#	Given there is a project with Id 
//	#	And the user is not a project leader
//	#	And there is an activity
//	#	When the user provides the estimated hours for the activity
//	#	Then a NotAuthorizedException is thrown
//	#	And the estimated work hours is not set


}
