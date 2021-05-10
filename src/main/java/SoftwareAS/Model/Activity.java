package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OutOfBoundsException;
import Exceptions.SessionNotFoundException;

public class Activity {
	
	private Project project;
	private int id;
	private int startWeek;
	private int endWeek;
	private int estimatedWorkHours = 0;
	private int timeLeft = 0;
	
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
	
	public void setEstimatedWorkHours(int estimatedWorkHours, Developer projectLeader, Project project) throws NotAuthorizedException, OutOfBoundsException {
		if (!(project.isProjectLeader(projectLeader) || projectLeader.isAdmin()))																//1
			throw new NotAuthorizedException("Only project leaders and admins are allowed to set work hours.");
		if (estimatedWorkHours < 0 || estimatedWorkHours > Integer.MAX_VALUE)																	//2
			throw new OutOfBoundsException("Estimated work hours can neither be negative nor larger than Java's max value for integers.");
		this.timeLeft = estimatedWorkHours - (this.estimatedWorkHours - timeLeft) ;
		this.estimatedWorkHours = estimatedWorkHours;
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
	
	public void assignDeveloperToActivity(Developer projectLeader,Developer developer) throws OperationNotAllowedException, DeveloperNotFoundException {
		if(!(project.isProjectLeader(projectLeader) || projectLeader.isAdmin()))
			throw new OperationNotAllowedException("Only a project leader or admin can assign developer to activity");
		if (!project.isDeveloperOnProject(developer.getId()))
			throw new DeveloperNotFoundException("Developer not on project.");
		
		developers.add(developer);
		developer.addActivity(this);
	}
	
	public void removeDeveloperFromActivity(Developer developer) {
		if(developers.contains(developer)) {
			developer.deleteActivity(this);
			developers.remove(developer);
		}
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
	public Session getSessionById(String sessionId) throws SessionNotFoundException {
		for (Session session: registeredSessions) {
			if (sessionId.equals(session.getSessionID2())) 
				return session;
		}
		throw new SessionNotFoundException("Session not found");
		
	}
	
	public boolean containsSession(Session session) {
		if (registeredSessions.contains(session)) {
			return true;
		}
		return false;
	}
	
	public void deleteSession(Session session) {
		if(registeredSessions.contains(session)) registeredSessions.remove(session);
	}
	public List<Developer> getDevelopers(){
		return developers;
	}

	public ActivitySummary createSummary() {
		
		Session[] sessionsFromPastWeek = SummaryHelper.getSessionsFromPastWeek(registeredSessions);

		ActivitySummary summary = new ActivitySummary(this, sessionsFromPastWeek);
		
		summaries.add(summary);
		
		return summary;
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
