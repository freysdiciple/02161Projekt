package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.OperationNotAllowedException;

public class Activity {
	
	private Project project;
	private int id;
	private int startWeek;
	private int endWeek;
	private int estimatedWorkHours;
	
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
	}
	public int getStartWeek() {
		return startWeek;
	}
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	
	public void assignDeveloper(Developer projectLeader,Developer developer) throws OperationNotAllowedException {
		if(projectLeader.equals(project.getProjectLeader())) developers.add(developer);
		else throw new OperationNotAllowedException("Only project managers can assign to activity");
	}
	
	public void assignDeveloper(Developer developer) throws OperationNotAllowedException {
		developers.add(developer);
	}
	
	public void registerSession(Session session) {
		registeredSessions.add(session);
	}
	public List<Session> getRegisteredSession() {
		return registeredSessions;
	}
	
	public void createSummary() {
		Session[] sessions = new Session[registeredSessions.size()];
		registeredSessions.toArray(sessions);
		
		ActivitySummary summary = new ActivitySummary(sessions);
		summaries.add(summary);
	}
}
