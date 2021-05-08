package StepDefinitions;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import io.cucumber.java.en.*;
import SoftwareAS.Model.*;

public class SeeAvailableDevelopersSteps {
	private Developer developer;
	private Admin admin;
	private String adminName="Mogens";
	private DataBase database = DataBase.getInstance();
	private Project project;
	private List<Developer> availableDevelopers;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;



//Main scenario
//Scenario: See available developers
//	Given there is an user with ID {string}
//  Given there is a project with ID {String}
//	Given the user is a Project leader
//  When the user provides information of the start time {string} and end time {string} of the activity where he needs developers
//  Then the system displays a list of available developers at the given time slot

	@Given("9- there is an user with ID {string}")
	public void thereIsAnUserWithIDAndDataBase(String userName) throws NotAuthorizedException, OperationNotAllowedException, AdminNotFoundException, DeveloperNotFoundException {
		database.createAdmin(adminName);
		admin = database.getAdminById(adminName);
		admin.createDeveloper(userName);
		developer= database.getDeveloperById(userName);
		assertTrue(database.containsDeveloper(userName));
	}

	@Given("9- there is a project with ID {int}")
	public void thereIsAProject(String projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		admin.createProject(projectNumber);
		project=database.getProjectById(projectNumber);
		assertTrue(database.containsProject(projectNumber));
	}


	@Given("9- the user is a Project leader")
	public void theUserIsAProjectLeader() throws OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException {
		project.assignDeveloperToProject(admin, developer);
		project.setProjectLeader(admin, developer);
		assertTrue(project.isProjectLeader(developer));

	}

	@When("9- the user provides information of the start time {string} and end time {string} of the activity where he needs developers")
	public void theUserProvidesTimeSlot(String start, String end) {
		String startYearString = start.substring(6,10);
		String startMonthString = start.substring(3,5);
		String startDayString = start.substring(0,2);
		String startHourString = start.substring(11,13);
		String startMinString = start.substring(14,16);
		
		int startYear = Integer.parseInt(startYearString);
		int startMonth = Integer.parseInt(startMonthString);
		int startDay = Integer.parseInt(startDayString);
		int startHour = Integer.parseInt(startHourString);
		int startMin = Integer.parseInt(startMinString);
		
		String endYearString = end.substring(6,10);
		String endMonthString = end.substring(3,5);
		String endDayString = end.substring(0,2);
		String endHourString = end.substring(11,13);
		String endMinString = end.substring(14,16);
		
		int endYear = Integer.parseInt(endYearString);
		int endMonth = Integer.parseInt(endMonthString);
		int endDay = Integer.parseInt(endDayString);
		int endHour = Integer.parseInt(endHourString);
		int endMin = Integer.parseInt(endMinString);
		
		startTime = new GregorianCalendar(startYear,startMonth,startDay,startHour,startMin);
		endTime = new GregorianCalendar(endYear,endMonth,endDay,endHour,endMin);

			}
			
	
	@Then("9- the system displays a list of available developers at the given time slot")
	public void theSystemProvidesListOfAvailableDevelopers() throws NotAuthorizedException {
		availableDevelopers=project.seeAvailableDevelopers(startTime, endTime, developer);
		for (Developer developer : availableDevelopers) {
			
			
		}
		
		
	}
}
 
