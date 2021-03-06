package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

import io.cucumber.java.en.*;



//Markus
public class ProjectLeaderSteps {

	private Admin admin;
	private Admin user;
	private Developer developer;
	private Project project;
	private DataBase database = DataBase.getInstance();
	private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	
	
	public ProjectLeaderSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}
	
//	Scenario: Assign the role project leader to a developer on an existing project successfully 
	
	@Given("3- the user {string} is an admin")
	public void theUserIsAnAdmin(String adminName) throws AdminNotFoundException, OutOfBoundsException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		assertTrue(database.containsAdmin(adminName));
	}
	
	@Given("3- there is a project with ID {string}")
	public void thereIsAProject(String projectName) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectName);
		project = database.getProjectByName(projectName);
		assertTrue(database.containsProject(projectName));
	}
	
	@Given("3- the admin assigns the developer {string} to the project")
	public void thereIsADeveloperListedOnTheProject(String developerName) throws OperationNotAllowedException, ProjectNotFoundException, DeveloperNotFoundException, NotAuthorizedException, OutOfBoundsException {
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		project.assignDeveloperToProject(admin, developer);
		assertTrue(project.isDeveloperOnProject(developerName));
	}
	
	@Given("3- the developer does not have the role project leader")
	public void developerDoesNotHaveRoleProjectLeader() throws ProjectNotFoundException, DeveloperNotFoundException {
		assertFalse(project.isProjectLeader(developer));
	}
	
	@When("3- the admin assigns the role project leader on the project to the developer")
	public void adminAssignsTheRoleProjectLeaderToTheDeveloper() throws ProjectNotFoundException, DeveloperNotFoundException, NotAuthorizedException {
		try {
			project.setProjectLeader(admin, developer);
		}
		catch(DeveloperNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("3- the developer is given the role project leader on that project")
	public void developerIsGivenTheRoleProjectLeaderOnThatProject() throws ProjectNotFoundException {
		assertTrue(project.isProjectLeader(developer));
	}
	
	
//	# Alternate scenario one

	@Given("3- a developer {string} who is not listed under the project")
	public void developerKnutWhoIsNotListedUnderProject(String developerName) throws ProjectNotFoundException, DeveloperNotFoundException, OutOfBoundsException {
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		assertFalse(project.isDeveloperOnProject(developer.getId()));
	}
	
	@Then("3- the system throws developerNotFoundException is given")
	public void systemThrowsDeveloperNotFoundException() {
		assertEquals("Developer is not on the project", errorMessageHolder.getErrorMessage());
	}
	
	
//	# Alternate scenario two

	@Given("3- the user {string} is not an admin")
	public void theUserIsNotAnAdmin(String userName) throws AdminNotFoundException, OutOfBoundsException {
		database.createAdmin(userName);
		user = database.getAdminById(userName);
		user.setAdminState(false);
		assertFalse(user.isAdmin());
	}
	
	@Given("3- there is a project with ID {string} created by an admin {string}")
	public void thereIsAProject(String projectName, String adminName) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException, AdminNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createProject(projectName);
		project = database.getProjectByName(projectName);
		assertTrue(database.containsProject(projectName));
	}
	
	@When("3- the user assigns the role project leader to a developer {string} already on the project")
	public void theUserAssignsProjectLeaderToADeveloperAlreadyOnTheProject(String developerName) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, NotAuthorizedException, OutOfBoundsException {
		admin.createDeveloper(developerName);
		developer = database.getDeveloperById(developerName);
		project.assignDeveloperToProject(admin, developer);
		
		try {
			project.setProjectLeader(user, developer);
		} catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("3- the system throws notAuthorizedException is given")
	public void theSystemThrowsNotAuthorized() {
		assertEquals("Project leader can only be assigned by Admin", errorMessageHolder.getErrorMessage());
	}

}

