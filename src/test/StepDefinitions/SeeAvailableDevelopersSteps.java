package StepDefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class SeeAvailableDevelopersSteps {
	private Developer developer;
	private Admin admin;
	


//Main scenario   
//Scenario: See available developers
// 	Given there is an user with id {String} and database {DataBase}
//	Given there is a project with id {int}
//	Given the user is a Project leader
//  When the user provides information of the time slot of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot
	
	@Given("there is an user with id {String} and database {DataBase}")
	public void thereIsAnUserWithIDAndDataBase() {
		developer = new Developer("Mogens", database);
	}
	
	

	@Given("the user is a Project leader")
	public void theUserIsAnAdmin() {
		assertTrue(developer.isProjectLeader());
	}
	
	
}