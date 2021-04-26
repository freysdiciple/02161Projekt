package StepDefinitions;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import SoftwareAS.Model.*;

import io.cucumber.java.en.*;






public class ProjectLeaderSteps {

	private Admin admin;
	private Developer developer;
	private Project project;
	private DataBase database;
	private int projectNumber1 = 12345;
	private int projectNumber2 = 123456;
	
	
//	Scenario: Assign the role project leader to a developer on an existing project successfully 
//	Given the user is an admin
//	And there is a project
//	And a developer listed under that project
//	And the developer does not have the role project leader
//	When the admin assigns the role project leader on the project to the developer
//	Then the developer is given the role project leader on that project
	
	
	
	
	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		admin = new Admin("Søren", database);
	}
	
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}
	
	@Given("there is a project with a number {int} and a creator {Admin}")
	public void thereIsAProjectWithNumberAndCreator() {
		admin.createProject(projectNumber1);
	}
	
	@Given("there is a developer listed on the project")
	public void thereIsADeveloperListedOnTheProject() {
		developer = new Developer("Bob", database);
		database.getProjectById(projectNumber1).assignDeveloper(admin, developer);
	}
	
	@Given("the developer does not have the role project leader")
	public void developerDoesNotHaveRoleProjectLeader() {
		assertFalse(database.getProjectById(projectNumber1).isProjectLeader(developer));
	}
	
	@When("the admin assigns the role project leader on the project to the developer")
	public void adminAssignsTheRoleProjectLeaderToTheDeveloper() {
		database.getProjectById(projectNumber1).setProjectLeader(developer);
	}
	
	@Then("the developer is given the role project leader on that project")
	public void developerIsGivenTheRoleProjectLeaderOnThatProject() {
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
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}
	
	@Given("there is a project with a number {int} and a creator {Admin}")
	public void thereIsAProjectWithNumberAndCreator() {
		admin.createProject(projectNumber2);
	}
	
	@Given("a developer who is not listed under the project")
	public void developerWhoIsNotListedUnderProject() {
		developer = new Developer("Knut", database);
		
	}
	
	
	
}
