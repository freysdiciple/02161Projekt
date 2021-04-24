
import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class CreateProjectSteps {
	private Admin admin;
	private Project project;
	private DataBase database;
	private int projectNumber = 12345;

	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		admin = new Admin("Søren", database);
	}

	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(admin.isAdmin());
	}

	@When("the user creates a project with a number {int} and a creator {Admin}")
	public void theUserCreatesProject() {
		admin.createProject(projectNumber);
	}
	
	@Then("the project with a number {int} is contained in the system")
	public void systemCreatesProjectWithGivenNumberAndCreator() {
		assertTrue(database.containsProject(projectNumber));
	}
}

//# Main scenario   
//Scenario: Create new project
//	Given there is an user with id {String} and database {DataBase}
//	And the user is an admin
//  When the user creates a project with a number {int} and a creator {Admin}
//  Then the project with a number {int} is contained in the system

