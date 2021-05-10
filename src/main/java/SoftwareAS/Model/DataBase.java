package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.*;

public class DataBase {

	private static DataBase instance = null;

	private List<Developer> developers = new ArrayList<>();
	private List<Admin> admins = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();
	private int projectsMade = 0;

	public static DataBase getInstance() {
		if(instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	private DataBase() {}

	public List<Developer> getAllDevelopers(){
		return developers;
	}

	public void createDeveloper(Developer developer) {
		developers.add(developer);
	}

	public void deleteDeveloper(String id) {
		Developer developerToRemove = null;
		boolean developerFound = false;

		for(Developer developer : developers) {
			if(developer.getId().equals(id)) {
				developerToRemove = developer;
				developerFound = true;
			}
		}

		if(developerFound){
			developers.remove(developerToRemove);
		}
	}
	public Developer getDeveloperById(String id) throws DeveloperNotFoundException {
		for(Developer developer : developers) {
			if(developer.getId().equals(id)) return developer;
		}

		throw new DeveloperNotFoundException("No developer with described ID");
	}
	public Developer getDeveloperByIdWOE(String id) {
		for(Developer developer : developers) {
			if(developer.getId().equals(id)) return developer;
		}

		return null;
	}
	public boolean containsDeveloper(String id) throws DeveloperNotFoundException {
		return developers.contains(getDeveloperByIdWOE(id));
	}

	public List<Admin> getAllAdmins(){
		return admins;
	}
	public void createAdmin(String id) throws OutOfBoundsException {
		admins.add(new Admin(id, this));
	}
	public void deleteAdmin(String id) {
		Admin adminToRemove = null;
		boolean adminFound = false;

		for(Admin admin : admins) {
			if(admin.getId().equals(id)) {
				adminToRemove = admin;
				adminFound = true;
			}
		}

		if(adminFound){
			admins.remove(adminToRemove);
		}
	}
	public Admin getAdminById(String id) throws AdminNotFoundException {
		for(Admin admin : admins) {
			if(admin.getId().equals(id)) return admin;
		}

		throw new AdminNotFoundException("No admin with described ID");
	}

	public Admin getAdminByIdWOE(String id) {
		for(Admin admin : admins) {
			if(admin.getId().equals(id)) return admin;
		}

		return null;
	}

	public boolean containsAdmin(String id) throws AdminNotFoundException {
		return admins.contains(getAdminByIdWOE(id));
	}

	public List<Project> getAllProjects(){
		return projects;
	}
	public void createProject(Project project) throws ProjectNotFoundException, ProjectAlreadyExistsException {
		if(containsProject(project.getProjectName()))
			throw new ProjectAlreadyExistsException("Project Already Exists");
		projects.add(project);
		projectsMade++;
	}
	public int getProjectsMade() {
		return projectsMade;
	}

	public void deleteProject(String projectName) {
		Project projectToRemove = null;
		boolean projectFound = false;

		for(Project project : projects) {
			if(project.getProjectName().equals(projectName)) {
				projectToRemove = project;
				projectFound = true;
			}
		}

		if(projectFound){
			for(Developer dev : projectToRemove.getDevelopers()) {
				dev.deleteProject(projectToRemove);
			}
			projects.remove(projectToRemove);
		}
	}
	public Project getProjectByName(String projectName) throws ProjectNotFoundException {
		for(Project project : projects) {
			if(project.getProjectName().equals(projectName)) return project;
		}

		throw new ProjectNotFoundException("No project with described ID");
	}
	public Project getProjectByNameWithoutException(String projectName) {
		for(Project project : projects) {
			if(project.getProjectName().equals(projectName)) return project;
		}

		return null;
	}


	public boolean containsProject(String projectName) throws ProjectNotFoundException {
		for(Project project : projects) {
			if(project.getProjectName().equals(projectName)) return true;
		}
		return false;
	}
	
	public void removeAllDevelopers() {
		developers= new ArrayList<Developer>();
	}

	public List<Developer> seeAvailableDevelopers(int startWeek, int endWeek, Developer user)
			throws NotAuthorizedException, OutOfBoundsException {

		boolean isProjectLeader = false;
		List<Developer> availableDevelopers = new ArrayList<>();

		for(Project project : getAllProjects()){
			if(project.isProjectLeader(user)) isProjectLeader = true;
		}

		if (!isProjectLeader && !user.isAdmin()) {
			throw new NotAuthorizedException("Only project leaders or admins can request to see available developers");
		}

		if (startWeek > 52 || endWeek > 52 || startWeek<1 || endWeek<1) {
			throw new OutOfBoundsException("The start week and end week has to be an integer between 1 and 52");
		}

		List<Developer> developers = user.getDatabase().getAllDevelopers();
		for (Developer developer : developers) {
			List<Activity> activities = developer.getActivities();
			int k = 0;
			for (Activity activity : activities) {
				if (activity.getStartWeek() < startWeek && activity.getEndWeek() > endWeek) {
					k++;
				}

			}
			if (k < 21) {
				availableDevelopers.add(developer);
			}
		}
		return availableDevelopers;
	}


}
