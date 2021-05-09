package StepDefinitions;

import io.cucumber.java.en.*;
import SoftwareAS.Controller.*;
import SoftwareAS.Model.*;
import Exceptions.*;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;

public class SummarySteps {

	private DataBase database;

	private ActivitySummary aSummary;
	private ProjectSummary pSummary;

	private Developer developer;
	private Developer projectLeader;
	private Admin admin;
	private Project project;
	private Activity activity1;
	private Activity activity2;

	private int workedTime1 = 2;
	private int workedTime2 = 4;
	private int estimatedTime1 = 10;
	private int estimatedTime2 = 10;

	public SummarySteps(SoftwareAS softwareAS) {
		this.database = softwareAS.getDataBase();
	}

	@Given("6 - the user is a project leader1")
	public void theUserIsAProjectLeader1() throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException {
		admin = new Admin("admin", database);
		admin.createProject("project3");
		project = database.getProjectByName("project3");

		developer = new Developer("developers", database);
		projectLeader = new Developer("project leader", database);
		project.assignDeveloperToProject(admin, developer);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);

		project.createActivity(1, projectLeader);
		project.createActivity(2, projectLeader);
		activity1 = project.getActivityById(1);
		activity2 = project.getActivityById(2);

		activity1.setEstimatedWorkHours(estimatedTime1, projectLeader, project);
		activity2.setEstimatedWorkHours(estimatedTime2, projectLeader, project);

	}

	@Given("6 - the user is a project leader2")
	public void theUserIsAProjectLeader2() throws ProjectAlreadyExistsException, ProjectNotFoundException, OperationNotAllowedException, DeveloperNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException {
		admin = new Admin("admin", database);
		admin.createProject("project4");
		project = database.getProjectByName("project4");

		developer = new Developer("developers", database);
		projectLeader = new Developer("project leader", database);
		project.assignDeveloperToProject(admin, developer);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);

		project.createActivity(100000, projectLeader);
		project.createActivity(200000, projectLeader);
		activity1 = project.getActivityById(100000);
		activity2 = project.getActivityById(200000);

		activity1.setEstimatedWorkHours(estimatedTime1, projectLeader, project);
		activity2.setEstimatedWorkHours(estimatedTime2, projectLeader, project);

	}

	@Given("6 - the developers have registered their daily time on the activities")
	public void theDevelopersHaveRegisteredTheirDailyTimeOnTheActivity6() throws OperationNotAllowedException, OverlappingSessionsException {
		GregorianCalendar start1 = new GregorianCalendar();
		GregorianCalendar end1 = new GregorianCalendar();
		start1.add(GregorianCalendar.DAY_OF_YEAR, -3);
		end1.add(GregorianCalendar.DAY_OF_YEAR, -3);
		end1.add(GregorianCalendar.HOUR, 2);

		GregorianCalendar start2 = new GregorianCalendar();
		GregorianCalendar end2 = new GregorianCalendar();
		start2.add(GregorianCalendar.DAY_OF_YEAR, -2);
		end2.add(GregorianCalendar.DAY_OF_YEAR, -2);
		end2.add(GregorianCalendar.HOUR, 4);

		developer.registerSession(activity1, start1, end1);
		developer.registerSession(activity2, start2, end2);
	}

	@When("6 - the project leader request a summary of the activity in the last week")
	public void theProjectLeaderRequestASummaryOfTheActivityInTheLastWeek6() {
		aSummary = activity1.createSummary();
	}

	@Then("6 - the project leader successfully receives a summary of the activity")
	public void theProjectLeaderSuccessfullyReceivesASummaryOfTheActivity6() {
		assertEquals(aSummary.getTotalWorkLoad(), workedTime1);
		assertEquals(aSummary.getRemainingTime(), estimatedTime1 - workedTime1);

	}

	@When("6 - the project manager requests a summary of the project")
	public void theProjectManagerRequestsASummaryOfTheProject6() {
		pSummary = project.createSummary();

	}

	@Then("6 - the project manager recieves a summary of the project")
	public void theProjectManagerRecievesASummaryOfTheProject6s() {
		assertTrue(pSummary.getActivityProgress()[0] == estimatedTime1 - workedTime1 && pSummary.getActivityProgress()[1] == estimatedTime2 - workedTime2);
		assertTrue(pSummary.getActivityWorktime()[0] == workedTime1 && pSummary.getActivityWorktime()[1] == workedTime2);

	}


}
