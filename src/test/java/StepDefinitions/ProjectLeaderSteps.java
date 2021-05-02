package StepDefinitions;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;




public class ProjectLeaderSteps {
/*
	private Admin admin;
	private Developer developer;
	private Developer developer2;
	private Project project;
	private DataBase database = new DataBase();
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	
	
//	Scenario: Assign the role project leader to a developer on an existing project successfully 
//	Given the user is an admin
//	And there is a project
//	And a developer listed under that project
//	And the developer does not have the role project leader
//	When the admin assigns the role project leader on the project to the developer
//	Then the developer is given the role project leader on that project
	
	
	
	
	@Given("the user {string} is an admin")
	public void theUserIsAnAdmin(String adminName) {
		admin = new Admin(adminName, database);
	}
	
	@Given("there is a project with ID {int}")
	public void thereIsAProject(int projectID) throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectID);
		project = database.getProjectById(projectID);
	}
	
	@Given("the user assigns the developer {string} to the project")
	public void thereIsADeveloperListedOnTheProject(String developerName) throws OperationNotAllowedException, ProjectNotFoundException, DeveloperNotFoundException {
		developer = new Developer(developerName, database);
		project.assignDeveloperToProject(admin, developer);
	}
	
	@Given("the developer {string} does not have the role project leader")
	public void developerDoesNotHaveRoleProjectLeader(String developerName) throws ProjectNotFoundException, DeveloperNotFoundException {
		assertFalse(project.isProjectLeader(developer));
	}
	
	@When("the admin assigns the role project leader on the project to the developer")
	public void adminAssignsTheRoleProjectLeaderToTheDeveloper() throws ProjectNotFoundException, DeveloperNotFoundException, NotAuthorizedException {
		project.setProjectLeader(admin, developer);
	}
	
	@Then("the developer is given the role project leader on that project")
	public void developerIsGivenTheRoleProjectLeaderOnThatProject() throws ProjectNotFoundException {
		assertTrue(project.isProjectLeader(developer));
	}
	
	
//	# Alternate scenario one
//	Scenario: Assign the role project leader to a developer not on the existing project
//		Given the user is an admin
//		And there is a project
//		And a developer, who is not listed under the project
//		And the developer does not have the role project leader
//		When the admin assigns the role project leader on the project to the developer
//		Then the system throws developerNotListedOnProjectException is given
////	

	
	@Given("a developer {string} who is not listed under the project")
	public void developerKnutWhoIsNotListedUnderProject(String developerName) throws ProjectNotFoundException {
		developer2 = new Developer(developerName, database);
		assertFalse(project.isDeveloperOnProject(developer2.getId()));
	}

	
	@When("the admin assigns the role project leader on the project to the developer knut")
	public void adminAssignsProjectLeaderOnProjectToDeveloper() throws ProjectNotFoundException, NotAuthorizedException {
		try {
			project.setProjectLeader(admin, developer2);
		}
		catch(DeveloperNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}
	
	@Then("the system throws developerNotFoundException is given")
	public void systemThrowsDeveloperNotFoundException() {
		assertEquals("Developer is not on the project", errorMessageHolder.getErrorMessage());
	}
	
	
//	# Alternate scenario two
//	Scenario: Assign the role project leader to a developer who has the role on an existing project 
//		Given the user is an admin 
//		And there is a project
//		And a developer listed under that project
//		And the developer has the role project leader on the given project
//		When the admin assigns the role project leader on the project to the developer
//		Then the system throws developerIsProjectLeaderException is given
	
	
	@Given("the developer is the project leader on the given project")
	public void theDeveloperIsProjectLeaderOnTheProject() throws DeveloperNotFoundException, NotAuthorizedException {
		
		project.setProjectLeader(admin, developer);
		
	}
	
	
	
	
	
	
*/
}

