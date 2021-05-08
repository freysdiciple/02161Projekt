package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;

public class DataBase {
	
	private static DataBase instance = null;
	
	private List<Developer> developers = new ArrayList<>();
	private List<Admin> admins = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

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
		for(Developer developer : developers) {
			if(developer.getId().equals(id)) developers.remove(developer);
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
	public void createAdmin(String id) {
		admins.add(new Admin(id, this));
	}
	public void deleteAdmin(String id) {
		for(Admin admin : admins) {
			if(admin.getId().equals(id)) developers.remove(admin);
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
		if(containsProject(project.getProjectNumber())) 
			throw new ProjectAlreadyExistsException("Project Already Exists");
		projects.add(project);
	}
	public void deleteProject(String s) {
		for(Project project : projects) {
			if(project.getProjectNumber() == s) {
				for(Developer dev : project.getDevelopers()) {
					dev.deleteProject(project);
				}
				projects.remove(project);
			}
		}
	}
	public Project getProjectById(String projectNumber) throws ProjectNotFoundException {
		for(Project project : projects) {
			if(project.getProjectNumber() == projectNumber) return project;
		}
		
		throw new ProjectNotFoundException("No project with described ID");
	}
	public Project getProjectByIdWithoutException(String projectNumber) {
		for(Project project : projects) {
			if(project.getProjectNumber() == projectNumber) return project;
		}
		
		return new Project("", new Admin());
	}
	
	
	public boolean containsProject(String projectNumber) throws ProjectNotFoundException {
		for(Project project : projects) {
			if(project.getProjectNumber().equals(projectNumber)) return true;
		}
		return false;
	}
	

	
}
