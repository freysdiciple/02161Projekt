package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Developer{
	
	private Database database;
	
	private List<Project> projects = new ArrayList<>();

	public Admin(String id, DataBase db) {
		super(id, db);
		setAdminState(true);
		this.database = database;
	}
	
	public void createProject(int projectNumber) {
		Project newProject = new Project(projectNumber, this);
		projects.add(newProject);
		database.createProject(newProject);
	}
	
	public void createDeveloper(String id) {
		database.createDeveloper(new Developer(id, database));
	}

}
