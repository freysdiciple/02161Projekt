package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import Exceptions.SessionNotFoundException;

public class Developer {
	
	private String id;
	private boolean isAdmin = false;
	protected DataBase database;
	private List<Session> registeredSessions = new ArrayList<>();
	private List<Activity> activities = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();
	
	public Developer(String id, DataBase database) {
		this.id = id;
		this.database = database;
	}
	public Developer() {}
	
	public String getId() {
		return id;
	}
	
	public DataBase getDatabase() {
		return database;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdminState(boolean state) {
		isAdmin = state;
	}
	
	public void registerSession(Activity activity, GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowedException, OverlappingSessionsException {
		if(startTime.compareTo(endTime) >= 0) throw new OperationNotAllowedException("A session cannot end before it starts...");    //1
		if(overlapsWithOtherSession(startTime, endTime)) throw new OverlappingSessionsException("Overlapping Sessions");             //2
		Session newSession = new Session(startTime, endTime, this, activity);
		
		registeredSessions.add(newSession);
		if(activity != null) activity.registerSession(newSession);                                                                   //3
	}
	
	public List<Session> getRegisteredSessions(){
		return registeredSessions;
	}
	
	public Session getSessionById(String sessionId) throws SessionNotFoundException {
		for (Session session: registeredSessions) {
			if (sessionId.equals(session.getSessionID2())) return session;
		}
		throw new SessionNotFoundException("Session not found");
	}
	
	public boolean containsSession(String sessionId) {
		for (Session session: registeredSessions) {
			if (sessionId.equals(session.getSessionID2())) return true;
		}
		
		
		return false;
	}
	
	public void deleteSession(Session session) {
		if(registeredSessions.contains(session)) registeredSessions.remove(session);
	}
	
	public boolean overlapsWithOtherSession(GregorianCalendar currentStart, GregorianCalendar currentEnd) {
		
		for(Session previous : registeredSessions) {
			GregorianCalendar previousStart = previous.getStartTime();
			GregorianCalendar previousEnd = previous.getEndTime();
			
			if(currentStart.compareTo(previousEnd) < 0 && currentStart.compareTo(previousStart) > 0) return true;
			if(currentEnd.compareTo(previousEnd) <= 0 && currentEnd.compareTo(previousStart) > 0) return true;
		}
		
		return false;
	}
	public void addActivity(Activity activity) {
		if(!activities.contains(activity)) activities.add(activity);
	}
	
	public List<Activity> getActivities(){
		return activities;
	}
	
	public List<Project> getProjects(){
		return projects;
	}
	
	public void deleteActivity(Activity activity) {
		if(activities.contains(activity)) activities.remove(activity);
		
	}
	public void addProject(Project project) {
		if(!projects.contains(project)) projects.add(project);
	}
	
	public void deleteProject(Project project) {
		if(projects.contains(project)) projects.remove(project);
		
	}
}
