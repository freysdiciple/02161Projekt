package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.ProjectNotFoundException;

public class DataBase {
	
	private List<Developer> developers = new ArrayList<>();
	private List<Admin> admins = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public DataBase() {}
	
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
	public boolean containsDeveloper(String id) throws DeveloperNotFoundException {
		return developers.contains(getDeveloperById(id));
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
	public boolean containsAdmin(String id) throws AdminNotFoundException {
		return admins.contains(getAdminById(id));
	}
	
	public List<Project> getAllProjects(){
		return projects;
	}
	public void createProject(Project project) {
		projects.add(project);
	}
	public void deleteProject(int projectNumber) {
		for(Project project : projects) {
			if(project.getProjectNumber() == projectNumber) projects.remove(project);
		}
	}
	public Project getProjectById(int projectNumber) throws ProjectNotFoundException {
		for(Project project : projects) {
			if(project.getProjectNumber() == projectNumber) return project;
		}
		
		throw new ProjectNotFoundException("No project with described ID");
	}
	public boolean containsProject(int projectNumber) throws ProjectNotFoundException {
		return projects.contains(getProjectById(projectNumber));
	}
	

	
}
