package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.*;

public class Admin extends Developer {

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

	public void createProject(String projectName) throws ProjectAlreadyExistsException, ProjectNotFoundException,
			NotAuthorizedException, OutOfBoundsException, NumberFormatException {
		if (database.containsProject(projectName)) // 1
			throw new ProjectAlreadyExistsException("Project Already Exists");
		if (!isAdmin()) {
			throw new NotAuthorizedException("Only admins can create new projects");
		}
		
		Project newProject = new Project(projectName, this);
		projects.add(newProject);
		database.createProject(newProject);
	}

	public void createDeveloper(String id) {
		database.createDeveloper(new Developer(id, database));
	}

}
