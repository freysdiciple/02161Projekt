package StepDefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class SeeAvailableDevelopersSteps {
	private Developer developer;
	private Admin admin;
	private DataBase database;
	private Project project;
	private int projectNumber = 12345;
	


//Main scenario   
//Scenario: See available developers
//  Given there is a project with id {String}
// 	Given there is an user with id {String} and database {DataBase}
//	Given the user is a Project leader
//  When the user provides information of the time slot of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot
	
	@Given("there is a project")
	public void thereIsAProject() {
		admin = new Admin("AdminID", database);
		project = new Project(projectNumber, admin);
	}
	
	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase1() {
		developer = new Developer("Mogens", database);
		project.setProjectLeader(developer);
	}
	
	@Given("the user is a Project leader")
	public void theUserIsAProjectLeader() {
		assertTrue(project.isProjectLeader(developer));
		
	}
	
	@When("the user provides information of the time slot of the activity where he needs developers")
	public void theUserProvidesTimeSlot() {
		
	}
	
	
	
}
	


