package WhiteBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
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
import io.cucumber.java.en.Given;

//This class is made by Peter - s204484
public class setEstimatedWorkHoursWhiteBox {
	ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
	private DataBase database = DataBase.getInstance();
	private Admin admin;
	private String adminID = "adID";
	private Developer developer;
	private String developerID = "John";
	private Developer projectLeader;
	private String projectLeaderID = "Joe";
	private Project project;
	private Activity activity;
	private int activityID = 5;
	
	private int negativeEstimatedWorkHours = -1;
	private int positiveEstimatedWorkHours = 1;
	
	@Test
	public void A() throws OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject("Project Name 1");
		project = database.getProjectByName("Project Name 1");
		
		admin.createDeveloper(developerID);
		developer = database.getDeveloperById(developerID);
		project.assignDeveloperToProject(admin, developer);
		
		project.createActivity(activityID, admin);
		activity = project.getActivityById(activityID);
		try {
			activity.setEstimatedWorkHours(negativeEstimatedWorkHours, developer, project);
		}
		catch (NotAuthorizedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("Only project leaders and admins are allowed to set work hours.", errorMessageHolder.getErrorMessage());
	}
	
	@Test
	public void B() throws NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException, DeveloperNotFoundException, AdminNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException  {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject("Project Name 2");
		project = database.getProjectByName("Project Name 2");
		
		admin.createDeveloper(projectLeaderID);
		projectLeader = database.getDeveloperById(projectLeaderID);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		
		project.createActivity(activityID, admin);
		activity = project.getActivityById(activityID);
		try {
			activity.setEstimatedWorkHours(negativeEstimatedWorkHours, projectLeader, project);
		} catch (OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("Estimated work hours can neither be negative nor larger than Java's max value for integers.", errorMessageHolder.getErrorMessage());
	}
	
	@Test
	public void C() throws NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException, DeveloperNotFoundException, AdminNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException  {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject("Project Name 3");
		project = database.getProjectByName("Project Name 3");
		
		admin.createDeveloper(projectLeaderID);
		projectLeader = database.getDeveloperById(projectLeaderID);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		
		project.createActivity(activityID, admin);
		activity = project.getActivityById(activityID);
		try {
			activity.setEstimatedWorkHours(Integer.MAX_VALUE + 1, projectLeader, project);
		} catch (OutOfBoundsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertEquals("Estimated work hours can neither be negative nor larger than Java's max value for integers.", errorMessageHolder.getErrorMessage());
	}
	
	@Test
	public void D() throws NotAuthorizedException, OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject("Project Name 4");
		project = database.getProjectByName("Project Name 4");
		
		admin.createDeveloper(projectLeaderID);
		projectLeader = database.getDeveloperById(projectLeaderID);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		
		project.createActivity(activityID, admin);
		activity = project.getActivityById(activityID);
		
		activity.setEstimatedWorkHours(positiveEstimatedWorkHours, projectLeader, project);
		assertTrue(activity.getEstimatedWorkHours() == positiveEstimatedWorkHours);
		assertTrue(activity.getTimeLeft() == positiveEstimatedWorkHours);
	}
	
	@Test
	public void E() throws NotAuthorizedException, OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
		database.createAdmin(adminID);
		admin = database.getAdminById(adminID);
		admin.createProject("Project Name 5");
		project = database.getProjectByName("Project Name 5");
		
		project.createActivity(activityID, admin);
		activity = project.getActivityById(activityID);
		
		activity.setEstimatedWorkHours(positiveEstimatedWorkHours, admin, project);
		assertTrue(activity.getEstimatedWorkHours() == positiveEstimatedWorkHours);
		assertTrue(activity.getTimeLeft() == positiveEstimatedWorkHours);
	}
	
}
