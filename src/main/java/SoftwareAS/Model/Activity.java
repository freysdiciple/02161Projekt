package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ActivityNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.OperationNotAllowedException;

public class Activity {
	
	private Project project;
	private int id;
	private int startWeek;
	private int endWeek;
	private int estimatedWorkHours;
	private int timeLeft;
	
	private List<Developer> developers = new ArrayList<>();
	private List<Session> registeredSessions = new ArrayList<>();
	private List<ActivitySummary> summaries = new ArrayList<>();
	
	public Activity(int id, Project project) {
		this.project = project;
		this.id = id;
	}
	
	public Activity() {
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public int getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}
	public int getEstimatedWorkHours() {
		return estimatedWorkHours;
	}
	public void setEstimatedWorkHours(int estimatedWorkHours) {
		this.estimatedWorkHours = estimatedWorkHours;
		this.timeLeft = estimatedWorkHours;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public int getStartWeek() {
		return startWeek;
	}
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	
	public void assignDeveloperToActivity(Developer projectLeader,Developer developer) throws OperationNotAllowedException {
		if(projectLeader.equals(project.getProjectLeader())) developers.add(developer);
		else throw new OperationNotAllowedException("Only project managers can assign to activity");
	}
	
//	public void assignDeveloper(Developer developer) throws OperationNotAllowedException {
//		developers.add(developer);
//	}
	
	public void registerSession(Session session) {
		registeredSessions.add(session);
	}
	public List<Session> getRegisteredSession() {
		return registeredSessions;
	}
	
	public void createSummary() {
		Session[] sessions = new Session[registeredSessions.size()];
		registeredSessions.toArray(sessions);
		
		ActivitySummary summary = new ActivitySummary(this, sessions);
		summaries.add(summary);
	}
	
//	public Developer getDeveloperById(String id) throws DeveloperNotFoundException {
//		for(Developer developer : developers) {
//			if(developer.getId() == id) return developer;
//		}
//		throw new DeveloperNotFoundException("No developer with described ID");
//	}
//	
//	public boolean containsDeveloperWithId(String id) throws DeveloperNotFoundException {
//		return developers.contains(getDeveloperById(id));
//	}
	
	public boolean isDeveloperOnAcitivty(String name) {
		for (Developer developer : developers) {
			if (developer.getId() == name)
				return true;
		}
		return false;
	}
}
