package StepDefinitions;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.DeveloperNotFoundException;
import Exceptions.OperationNotAllowedException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;



/*


public class ProjectLeaderSteps {

	private Admin admin;
	private Developer developer;
	private Developer developer2;
	private Project project1;
	private Project project2;
	private DataBase database = new DataBase();
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
	
	
	
	
	@Given("the user is an admin with ID Bob")
	public void theUserIsAnAdminWithIdBob() {
		admin = new Admin("Bob", database);
	}
	
	@Given("there is a project created by Bob")
	public void thereIsAProjectCreatedByBob() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		admin.createProject(projectNumber1);
		project1 = database.getProjectById(projectNumber1);
	}
	
	@Given("there is a developer listed on the project")
	public void thereIsADeveloperListedOnTheProject() throws OperationNotAllowedException, ProjectNotFoundException {
		developer = new Developer("Blob", database);
		project1.assignDeveloperToProject(admin, developer);
	}
	
	@Given("the developer does not have the role project leader")
	public void developerDoesNotHaveRoleProjectLeader() throws ProjectNotFoundException {
		assertFalse(project1.isProjectLeader(developer));
	}
	
	@When("the admin assigns the role project leader on the project to the developer")
	public void adminAssignsTheRoleProjectLeaderToTheDeveloper() throws ProjectNotFoundException, DeveloperNotFoundException {
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
////	
	
	
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}
	
	@Given("there is a project")
	public void thereIsAProject() throws ProjectAlreadyExistsException, ProjectNotFoundException {
		assertTrue(database.containsProject(projectNumber1));
	}
	
	@Given("a developer knut who is not listed under the project")
	public void developerKnutWhoIsNotListedUnderProject() throws ProjectNotFoundException {
		developer2 = new Developer("Knut", database);
		assertFalse(project1.isDeveloperOnProject(developer2.getId()));
	}
	
	@Given("the developer knut does not have the role project leader")
	public void developer2IsNotProjectLeader() throws ProjectNotFoundException {
		assertFalse(project1.isProjectLeader(developer2));
	}
	
	@When("the admin assigns the role project leader on the project to the developer knut")
	public void adminAssignsProjectLeaderOnProjectToDeveloper() throws ProjectNotFoundException {
		try {
			project1.setProjectLeader(developer2);
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
	
	
	
	
	
	
	
	
	
	
}

*/
