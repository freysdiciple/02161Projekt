package WhiteBoxTests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import Exceptions.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;
import org.junit.jupiter.api.Test;

public class assignDeveloperToActivityWhiteBox {
	
	ErrorMessageHolder emh = new ErrorMessageHolder();
	
	DataBase database = DataBase.getInstance();
	Activity activity;
	Developer developer;
	Developer developer2;
	Admin admin;
	Developer developerNotOnProject;
	Developer projectLeader;
	Project project;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
	
	
	@Test
	public void testInputDataSetA() throws OperationNotAllowedException, OverlappingSessionsException, DeveloperNotFoundException, OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		database.createAdmin("Blib");
		admin = database.getAdminById("Blib");
		admin.createDeveloper("Bobb");
		developer = database.getDeveloperById("Bobb");
		admin.createDeveloper("Klo");
		developer2 = database.getDeveloperById("Klo");
		admin.createProject("DatasetA");
		project = database.getProjectByName("DatasetA");
		admin.createDeveloper("Proj");
		projectLeader = database.getDeveloperById("Proj");
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(200, projectLeader);
		activity = project.getActivityById(200);
		
		try {
			activity.assignDeveloperToActivity(developer2, developer);
		} catch(OperationNotAllowedException e) {
			emh.setErrorMessage(e.getMessage());
		}
		assertEquals(emh.getErrorMessage(),"Only a project leader or admin can assign developer to activity");
	}
	
	@Test
	public void testInputDataSetB() throws DeveloperNotFoundException, OperationNotAllowedException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException {
		database.createAdmin("Blib");
		admin = database.getAdminById("Blib");
		admin.createDeveloper("Bobb");
		developer = database.getDeveloperById("Bobb");
		admin.createProject("DatasetB");
		project = database.getProjectByName("DatasetB");
		admin.createDeveloper("Proj");
		projectLeader = database.getDeveloperById("Proj");
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(200, projectLeader);
		activity = project.getActivityById(200);
		
		
		
		
		try {
			activity.assignDeveloperToActivity(projectLeader, developer);
		}catch(DeveloperNotFoundException e) {
			emh.setErrorMessage(e.getMessage());
		}
		assertEquals(emh.getErrorMessage(), "Developer not on project.");
	}
	
	@Test
	public void testInputDataSetC() throws NotAuthorizedException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException, OutOfBoundsException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException {
		
		database.createAdmin("Blib");
		admin = database.getAdminById("Blib");
		admin.createDeveloper("Bobb");
		developer = database.getDeveloperById("Bobb");
		admin.createProject("DatasetC");
		project = database.getProjectByName("DatasetC");
		admin.createDeveloper("Proj");
		projectLeader = database.getDeveloperById("Proj");
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(200, projectLeader);
		activity = project.getActivityById(200);
		
		
		
		
		project.assignDeveloperToProject(projectLeader, developer);
		
		activity.assignDeveloperToActivity(projectLeader, developer);
		assertTrue(activity.isDeveloperOnAcitivty(developer.getId()));

	}
	
	
	@Test
	public void testInputDataSetD() throws NotAuthorizedException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException, OperationNotAllowedException, AdminNotFoundException, NumberFormatException, ProjectAlreadyExistsException, ProjectNotFoundException  {
		database.createAdmin("Blib");
		admin = database.getAdminById("Blib");
		admin.createDeveloper("Babb");
		developer = database.getDeveloperById("Babb");
		admin.createProject("DatasetD");
		project = database.getProjectByName("DatasetD");
		admin.createDeveloper("Proj");
		projectLeader = database.getDeveloperById("Proj");
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(200, projectLeader);
		activity = project.getActivityById(200);
		
		
		
		activity.assignDeveloperToActivity(admin, developer);
		assertTrue(activity.isDeveloperOnAcitivty(developer.getId()));
	}

	
}
