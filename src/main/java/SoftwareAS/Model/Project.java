package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;

public class Project {
	
	private int projectNumber;
	private GregorianCalendar startDate;
	private Admin creator;
	
	private Developer projectLeader = null;
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
	
	public void setProjectLeader(Developer projectLeader) {
		this.projectLeader = projectLeader;
	}
	public Developer getProjectLeader() {
		return projectLeader;
	}
	public boolean isProjectLeader(Developer developer) {
		return projectLeader.equals(developer);
	}
	
	public boolean isDeveloperOnProject(String name) {
		for (Developer developer : developers) {
			if (developer.getId() == name)
				return true;
		}
		return false;
	}
	public void assignDeveloper(Developer admin, Developer developer) throws OperationNotAllowedException {
		if(admin.isAdmin()) developers.add(developer);
		else throw new OperationNotAllowedException("Only admins can assign developers to projects");
	}
	public void createActivity(int id, Developer developer) throws NotAuthorizedException, ActivityAlreadyExistsException, ActivityNotFoundException {
		if (this.isProjectLeader(developer)) {
			if (!this.containsActivityWithId(id))
				activities.add(new Activity(id, this));
			else
				throw new ActivityAlreadyExistsException("An activity with that ID already exists.");
		}
		else throw new NotAuthorizedException("Only the project leader can create activities.");
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
