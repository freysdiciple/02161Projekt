package StepDefinitions;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.OperationNotAllowedException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;






public class ProjectLeaderSteps {

	private Admin admin;
	private Developer developer;
	private Project project;
	private DataBase database;
	private int projectNumber1 = 12345;
	private int projectNumber2 = 123456;
	private ErrorMessageHolder errorMessageHolder;
	
	
//	Scenario: Assign the role project leader to a developer on an existing project successfully 
//	Given the user is an admin
//	And there is a project
//	And a developer listed under that project
//	And the developer does not have the role project leader
//	When the admin assigns the role project leader on the project to the developer
//	Then the developer is given the role project leader on that project
	
	
	
	
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		admin = new Admin("Søren", database);
	}
	
	@Given("there is a project with a number {int} and a creator {Admin}")
	public void thereIsAProjectWithNumberAndCreator() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber1);
	}
	
	@Given("there is a developer listed on the project")
	public void thereIsADeveloperListedOnTheProject() throws OperationNotAllowedException, ProjectNotFoundException {
		developer = new Developer("Bob", database);
		database.getProjectById(projectNumber1).assignDeveloper(admin, developer);
	}
	
	@Given("the developer does not have the role project leader")
	public void developerDoesNotHaveRoleProjectLeader() throws ProjectNotFoundException {
		assertFalse(database.getProjectById(projectNumber1).isProjectLeader(developer));
	}
	
	@When("the admin assigns the role project leader on the project to the developer")
	public void adminAssignsTheRoleProjectLeaderToTheDeveloper() throws ProjectNotFoundException {
		database.getProjectById(projectNumber1).setProjectLeader(developer);
	}
	
	@Then("the developer is given the role project leader on that project")
	public void developerIsGivenTheRoleProjectLeaderOnThatProject() throws ProjectNotFoundException {
		assertTrue(database.getProjectById(projectNumber1).isProjectLeader(developer));
	}
	
	
//	# Alternate scenario one
//	Scenario: Assign the role project leader to a developer not on the existing project
//		Given the user is an admin
//		And there is a project
//		And a developer, who is not listed under the project
//		And the developer does not have the role project leader
//		When the admin assigns the role project leader on the project to the developer
//		Then the system throws developerNotListedOnProjectException is given
//	
	
	
	@Given("the user is an admin")
	public void theUserIsAnAdmin1() {
		assertTrue(admin.isAdmin());
	}
	
	@Given("there is a project with a number {int} and a creator {Admin}")
	public void thereIsAProjectWithNumberAndCreator1() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber2);
	}
	
	@Given("a developer who is not listed under the project")
	public void developerWhoIsNotListedUnderProject() throws ProjectNotFoundException {
		developer = new Developer("Knut", database);
		assertFalse(database.getProjectById(projectNumber2).isDeveloperOnProject(developer.getId()));
	}
	
	@Given("the developer does not have the role project leader")
	public void developerIsNotProjectLeader() throws ProjectNotFoundException {
		assertFalse(database.getProjectById(projectNumber2).isProjectLeader(developer));
	}
	
	@When("the admin assigns the role project leader on the project to the developer")
	public void adminAssignsProjectLeaderOnProjectToDeveloper() throws ProjectNotFoundException {
		database.getProjectById(projectNumber2).setProjectLeader(developer);
	}
	
	@Then("the system throws developerNotListedOnProjectException is given")
	public void systemThrowsDeveloperNotListedOnProjectException() {
		
		
	}
}