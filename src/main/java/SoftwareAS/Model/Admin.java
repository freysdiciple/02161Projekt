package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.*;

public class Admin extends Developer{
	
	private DataBase database;
	
	private List<Project> projects = new ArrayList<>();

	public Admin(String id, DataBase database) {
		super(id, database);
		setAdminState(true);
		this.database = database;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public void createProject(int projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException, NotAuthorizedException, OutOfBoundsException {
		if(database.containsProject(projectNumber)) 
			throw new ProjectAlreadyExistsException("Project Already Exists");
		if(!isAdmin())
			throw new NotAuthorizedException("Only admins can create new projects");
		String projectID = Integer.toString(projectNumber);
		if (projectID.length() != 6)
			throw new OutOfBoundsException("Project Number input is out of bounds, please enter 6 digits");
		
		Project newProject = new Project(projectNumber, this);
		projects.add(newProject);
		database.createProject(newProject);
	}
	
	public void createDeveloper(String id) {
		database.createDeveloper(new Developer(id, database));
	}

}
