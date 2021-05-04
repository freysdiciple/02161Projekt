package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;

public class Project {
	
	private int projectNumber;
	private GregorianCalendar startDate;
	private Admin creator;
	
	private Developer projectLeader = null;
	private List<Activity> activities = new ArrayList<>();
	private List<Developer> developers = new ArrayList<>();
	private List<ProjectSummary> summaries = new ArrayList<>();
	
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
	
	public void setProjectLeader(Developer admin, Developer developer) throws DeveloperNotFoundException, NotAuthorizedException {
		if (isDeveloperOnProject(developer.getId()) == true) {
			if (admin.isAdmin()  == true) this.projectLeader = developer;
			else throw new NotAuthorizedException("Project leader can only be assigned by Admin");
		}
		else throw new DeveloperNotFoundException("Developer is not on the project");
	}
	
	public Developer getProjectLeader() {
		return projectLeader;
	}
	
	public List<Developer> getDevelopers(){
		return developers;
	}
	
	public boolean isProjectLeader(Developer developer) {
		if (projectLeader == null) return false;
		return projectLeader.equals(developer);
	}
	
	public boolean isDeveloperOnProject(String name) {
		for (Developer developer : developers) {
			if (developer.getId() == name)
				return true;
		}
		return false;
	}
	
	public void assignDeveloperToProjectPL(Developer pl, Developer developer) throws OperationNotAllowedException {
		if(isProjectLeader(pl)) developers.add(developer);
		else throw new OperationNotAllowedException("Only PL's can assign developers to projects");
	}
	
	public void assignDeveloperToProject(Developer admin, Developer developer) throws OperationNotAllowedException {
		if(admin.isAdmin()) {
			developer.addProject(this);
			developers.add(developer);
		}
		else throw new OperationNotAllowedException("Only admins can assign developers to projects");
	}
	
	public void removeDeveloperFromProject(Developer developer) {
		if(developers.contains(developer)) {
			developer.deleteProject(this);
			developers.remove(developer);
		}
	}
	
	public void createActivity(int id, Developer developer) throws NotAuthorizedException, ActivityAlreadyExistsException {
		if (this.isProjectLeader(developer)) {
			if (!this.containsActivityWithId(id))
				activities.add(new Activity(id, this));
			else
				throw new ActivityAlreadyExistsException("An activity with that ID already exists.");
		}
		else throw new NotAuthorizedException("Only the project leader can create activities.");
	}
	
	public void deleteActivity(int id, Developer developer) throws NotAuthorizedException, ActivityNotFoundException {
		if (this.isProjectLeader(developer)) {
			if (!this.containsActivityWithId(id)) {
				Activity activity = getActivityById(id);
				for(Developer dev : activity.getDevelopers()) {
					dev.deleteActivity(activity);
				}
				activities.remove(activity);
			}
			else
				throw new ActivityNotFoundException("An activity with that ID doesnt exists.");
		}
		else throw new NotAuthorizedException("Only the project leader can delete activities.");
	}
	
	public Activity getActivityById(int id) throws ActivityNotFoundException {
		for(Activity activity : activities) {
			if(activity.getId() == id) return activity;
		}
		
		throw new ActivityNotFoundException("No activity with described ID");
	}
	
	public boolean containsActivityWithId(int id) {
		try {
			activities.contains(getActivityById(id));
			return true;
		} catch (ActivityNotFoundException e) {
			return false;
		}
	}
	public ProjectSummary createSummary() {
		ProjectSummary summary = new ProjectSummary(activities);
		summaries.add(summary);
		return summary;
	}
	
}
