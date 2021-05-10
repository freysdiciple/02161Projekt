package SoftwareAS.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OutOfBoundsException;

public class Project {

	private String projectName;
	private GregorianCalendar startDate;
	private Admin creator;
	private String projectId = "";


	private Developer projectLeader = null;
	private List<Activity> activities = new ArrayList<>();
	private List<Developer> developers = new ArrayList<>();
	private List<ProjectSummary> summaries = new ArrayList<>();
	private List<Developer> availableDevelopers = new ArrayList<>();

	public Project(String projectName, Admin creator) {
		this.projectName = projectName;
		this.creator = creator;
		this.startDate = new GregorianCalendar();

		setProjectID();
	}

	public void setProjectID() {
		DateFormat df = new SimpleDateFormat("yy");
		String year = df.format(Calendar.getInstance().getTime());

		projectId = projectId + year;

		String amountOfProjects = String.valueOf(DataBase.getInstance().getProjectsMade() + 1);

		for (int i=4; i > amountOfProjects.length(); i--)
			projectId = projectId + "0";

		projectId = projectId + amountOfProjects;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectID() {
		return projectId;
	}

	public Admin getCreator() {
		return creator;
	}
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setProjectLeader(Developer admin, Developer developer) throws DeveloperNotFoundException, NotAuthorizedException {
		if (isDeveloperOnProject(developer.getId()) == false)
			throw new DeveloperNotFoundException("Developer is not on the project");
		if (admin.isAdmin()  == false)
			throw new NotAuthorizedException("Project leader can only be assigned by Admin");
		this.projectLeader = developer;
	}

	public Developer getProjectLeader() {
		return projectLeader;
	}

	public List<Developer> getDevelopers(){
		return developers;
	}
	public List<Activity> getActivities(){
		return activities;
	}

	public boolean isProjectLeader(Developer developer) {
		if (projectLeader == null) return false;
		return projectLeader.equals(developer);
	}

	public boolean isDeveloperOnProject(String name) {
		for (Developer developer : developers) {
			if (developer.getId().equals(name))
				return true;
		}
		return false;
	}

	public void assignDeveloperToProject(Developer admin, Developer developer) throws NotAuthorizedException {
		if(!(admin.isAdmin() || isProjectLeader(admin)))
			throw new NotAuthorizedException("Not authorized to assign developers to projects");
		developers.add(developer);
	}

	public void removeDeveloperFromProject(Developer developer) {
		if(developers.contains(developer)) {
			developer.deleteProject(this);
			developers.remove(developer);
		}
	}

	public void createActivity(int id, Developer developer) throws NotAuthorizedException, ActivityAlreadyExistsException {
		if (!(this.isProjectLeader(developer) || developer.isAdmin()))
			throw new NotAuthorizedException("Not authorized to create activities.");
		if (this.containsActivityWithId(id))
			throw new ActivityAlreadyExistsException("An activity with that ID already exists.");

		activities.add(new Activity(id, this));
	}


	public void deleteActivity(int id, Developer developer) throws NotAuthorizedException, ActivityNotFoundException {
		if (!(this.isProjectLeader(developer) || developer.isAdmin()))
			throw new NotAuthorizedException("Not authorized to delete activities.");
		if (!this.containsActivityWithId(id))
			throw new ActivityNotFoundException("An activity with that ID doesnt exists.");
		Activity activity = getActivityById(id);
		for(Developer dev : activity.getDevelopers())
			dev.deleteActivity(activity);

		activities.remove(getActivityById(id));
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
	public List<Developer> seeAvailableDevelopers(int startWeek, int endWeek, Developer user)
			throws NotAuthorizedException, OutOfBoundsException {
		if (!isProjectLeader(user) && !user.isAdmin()) {
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
