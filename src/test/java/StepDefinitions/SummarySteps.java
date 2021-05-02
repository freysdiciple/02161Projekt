package StepDefinitions;

import io.cucumber.java.en.*;
import SoftwareAS.Controller.*;
import SoftwareAS.Model.*;
import Exceptions.*;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;

public class SummarySteps {
	
	private DataBase database;
	private ErrorMessageHolder errorMessageHolder;
	
	private Developer developer;
	private Developer projectLeader;
	private Admin admin;
	private Project project;
	private Activity activity1;
	private Activity activity2;
	
	public SummarySteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("the user is a project leader")
	public void theUserIsAProjectLeader() throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		admin = new Admin("admin", database);
		admin.createProject(123);
		project = database.getProjectById(123);
		
		developer = new Developer("developers", database);
		projectLeader = new Developer("project leader", database);
		project.assignDeveloperToProject(admin, developer);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(projectLeader);
		
		project.createActivity(1, projectLeader);
		project.createActivity(2, projectLeader);
		activity1 = project.getActivityById(1);
		activity2 = project.getActivityById(2);
		
	}
	
	@Given("the developers have registered their daily time on the activities")
	public void theDevelopersHaveRegisteredTheirDailyTimeOnTheActivity() throws OperationNotAllowedException, OverlappingSessionsException {
		GregorianCalendar start1 = new GregorianCalendar(); 
		GregorianCalendar end1 = new GregorianCalendar(); 
		end1.add(GregorianCalendar.HOUR_OF_DAY, 2);
		
		GregorianCalendar start2 = new GregorianCalendar();
		GregorianCalendar end2 = new GregorianCalendar();
		start2.add(GregorianCalendar.DAY_OF_WEEK, 1);
		end2.add(GregorianCalendar.DAY_OF_WEEK, 1);
		end2.add(GregorianCalendar.HOUR_OF_DAY, 4);
		
		developer.registerSession(activity1, start1, end1);
		developer.registerSession(activity2, start2, end2);
	}
	
	@When("the project leader request a summary of the activity in the last week")
	public void theProjectLeaderRequestASummaryOfTheActivityInTheLastWeek() {
		ActivitySummary summary = activity1.createSummary();
	}
	
	@Then("the project leader successfully receives a summary of the activity")
	public void theProjectLeaderSuccessfullyReceivesASummaryOfTheActivity() {
		
	}
	
	@When("the project manager requests a summary of the project")
	public void theProjectManagerRequestsASummaryOfTheProject() {
		ProjectSummary summary = project.createSummary();
		
	}
	
	@Then("the project manager recieves a summary of the project")
	public void theProjectManagerRecievesASummaryOfTheProject() {
		
	}
	
	
}
