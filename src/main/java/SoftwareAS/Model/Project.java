package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.ActivityNotFoundException;
import Exceptions.OperationNotAllowedException;

public class Project {
	
	private int projectNumber;
	private GregorianCalendar startDate;
	private Admin creator;
	
	private Developer projectManager = null;
	private List<Activity> activities = new ArrayList<>();
	private List<Developer> developers = new ArrayList<>();
	
	public Project(int projectNumber, Admin creator) {
		this.projectNumber = projectNumber;
		this.creator = creator;
		this.startDate = new GregorianCalendar();
	}
	
	public int getProjectNumber() {
		return projectNumber;
	}
	public Admin getCreator() {
		return creator;
	}
	public GregorianCalendar getStartDate() {
		return startDate;
	}
	
	public void setProjectManager(Developer projectManager) {
		this.projectManager = projectManager;
	}
	public Developer getProjectManager() {
		return projectManager;
	}
	public boolean isProjectManager(Developer developer) {
		return projectManager.equals(developer);
	}
	
	public void assignDeveloper(Developer admin, Developer developer) throws OperationNotAllowedException {
		if(admin.isAdmin()) developers.add(developer);
		else throw new OperationNotAllowedException("Only admins can assign developers to projects");
	}
	
	public void createActivity(int id) {
		activities.add(new Activity(id, this));
	}
	public Activity getActivityById(int id) throws ActivityNotFoundException {
		for(Activity activity : activities) {
			if(activity.getId() == id) return activity;
		}
		
		throw new ActivityNotFoundException("No activity with described ID");
	}
	public boolean containsActivityWithId(int id) throws ActivityNotFoundException {
		return activities.contains(getActivityById(id));
	}
	
}
