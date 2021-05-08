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

	public void createProject(String projectNumber) throws ProjectAlreadyExistsException, ProjectNotFoundException,
			NotAuthorizedException, OutOfBoundsException {
		if (database.containsProject(projectNumber)) // 1
			throw new ProjectAlreadyExistsException("Project Already Exists");
		if (!isAdmin()) // 2
			throw new NotAuthorizedException("Only admins can create new projects");
		if (projectNumber.length() != 6) // 3
			throw new OutOfBoundsException("Project Number input is out of bounds, please enter 6 digits");

		Project newProject = new Project(projectNumber, this);
		projects.add(newProject);
		database.createProject(newProject);
	}

	public void createDeveloper(String id) {
		database.createDeveloper(new Developer(id, database));
	}

}
