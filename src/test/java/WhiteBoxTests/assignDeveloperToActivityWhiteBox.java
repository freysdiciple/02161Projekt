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
	public void testInputDataSetA() throws OperationNotAllowedException, OverlappingSessionsException, DeveloperNotFoundException {
		developer = new Developer();
		developer2 = new Developer();
		activity = new Activity();
		try {
			activity.assignDeveloperToActivity(developer2, developer);
		}catch(OperationNotAllowedException e) {
			emh.setErrorMessage(e.getMessage());
		}
		assertEquals(emh,"Only a project leader or admin can assign developer to activity");
	}
	
	@Test
	public void testInputDataSetB() throws DeveloperNotFoundException, OperationNotAllowedException, NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException, AdminNotFoundException {
		database.createAdmin("Blub");
		admin = database.getAdminById("Blub");
		projectLeader = new Developer();
		project = new Project("Hello",admin);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(200, projectLeader);
		activity = project.getActivityById(200);
		
		try {
			activity.assignDeveloperToActivity(projectLeader, developer);
		}catch(DeveloperNotFoundException e) {
			emh.setErrorMessage(e.getMessage());
		}
		assertEquals(emh, "Developer not on project.");
	}
	
	@Test
	public void testInputDataSetC() throws NotAuthorizedException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, OperationNotAllowedException, OutOfBoundsException, AdminNotFoundException {
		database.createAdmin("Blab");
		admin = database.getAdminById("Blab");
		projectLeader = new Developer();
		project = new Project("Hello",admin);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(322, projectLeader);
		activity = project.getActivityById(322);
		developer = new Developer("Helo", database);
		project.assignDeveloperToProject(admin, developer);
		
		activity.assignDeveloperToActivity(projectLeader, developer);
		assertTrue(activity.isDeveloperOnAcitivty(developer.getId()));

	}
	
	
	@Test
	public void testInputDataSetD() throws NotAuthorizedException, DeveloperNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, OutOfBoundsException, OperationNotAllowedException, AdminNotFoundException  {
		database.createAdmin("Blib");
		admin = database.getAdminById("Blib");
		projectLeader = new Developer();
		project = new Project("Jello",admin);
		project.assignDeveloperToProject(admin, projectLeader);
		project.setProjectLeader(admin, projectLeader);
		project.createActivity(322, projectLeader);
		activity = project.getActivityById(322);
		developer = new Developer("Halo", database);
		project.assignDeveloperToProject(admin, developer);
		
		activity.assignDeveloperToActivity(admin, developer);
		assertTrue(activity.isDeveloperOnAcitivty(developer.getId()));
	}

	
}
