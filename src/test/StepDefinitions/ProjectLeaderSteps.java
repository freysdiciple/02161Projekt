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
	private int projectNumber = 12345;
	
	
	
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
		admin.createProject(projectNumber);
		
	}
	
	@Given("there is a developer listed on the project")
	public void thereIsADeveloperListedOnTheProject() {
		developer = new Developer("Bob", database);
		database.getProjectById(projectNumber).assignDeveloper(admin, developer);
		
		
		
	}
	
	
	
}
