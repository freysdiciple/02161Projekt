package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
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
	public void setEstimatedWorkHours(int estimatedWorkHours, Developer projectLeader, Project project) throws NotAuthorizedException {
		if (!(project.isProjectLeader(projectLeader) || projectLeader.isAdmin()) )
			throw new NotAuthorizedException("Only project leaders is allowed to set work hours");
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
	
	public void assignDeveloperToActivity(Developer projectLeader,Developer developer) throws OperationNotAllowedException, DeveloperNotFoundException {
		if(!project.isProjectLeader(projectLeader))
			throw new OperationNotAllowedException("Only project leaders can assign to activity");
		if (!project.isDeveloperOnProject(developer.getId()))
			throw new DeveloperNotFoundException("Developer not on project.");
		
		developers.add(developer);
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
	public void deleteSession(Session session) {
		if(registeredSessions.contains(session)) registeredSessions.remove(session);
	}
	public List<Developer> getDevelopers(){
		return developers;
	}
	
	public boolean endedInInterval(Session session,GregorianCalendar start, GregorianCalendar end) {
		GregorianCalendar sessionEnd = session.getEndTime();
		
		if(sessionEnd.compareTo(start) > 0 && sessionEnd.compareTo(end) < 0) return true;
		else return false;
	}
	
	public Session[] getSessionsFromPastWeek(){
		List<Session> list = new ArrayList<>();
		
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar start = getLastMonday(now);
		
		//We make a second now, as the first one is for
		//some reason corrupted while getting last monday
		GregorianCalendar end = new GregorianCalendar();
		
		System.out.println("start day: " + start.get(GregorianCalendar.DAY_OF_MONTH));
		System.out.println("end day: " + end.get(GregorianCalendar.DAY_OF_MONTH));
		
		for(Session session : registeredSessions) {
			if(endedInInterval(session, start, end)) list.add(session);
		}
		
		System.out.println(list.size());
		
		Session[] array = new Session[list.size()];
		list.toArray(array);
		return array;
	}
	

	private GregorianCalendar getLastMonday(GregorianCalendar now) {
		int currentDayOfWeek = now.get(GregorianCalendar.DAY_OF_WEEK);
		
		//Puts sunday at end of daylist insted of in front
		if(currentDayOfWeek == 1) currentDayOfWeek += 7;
		currentDayOfWeek --;
		
		int daysFromMonday = 0;
		while(currentDayOfWeek > 1) {
			daysFromMonday ++;
			currentDayOfWeek --;
		}
		
		//Returns last monday at 00:00
		GregorianCalendar lastMonday = now;
		lastMonday.add(GregorianCalendar.DAY_OF_YEAR, -daysFromMonday);
		lastMonday.add(GregorianCalendar.HOUR, 0);
		lastMonday.set(GregorianCalendar.HOUR_OF_DAY, 0);
		lastMonday.set(GregorianCalendar.MINUTE, 0);
		lastMonday.set(GregorianCalendar.SECOND, 0);
		lastMonday.set(GregorianCalendar.MILLISECOND, 0);
		return lastMonday;
	}

	public ActivitySummary createSummary() {
		
		Session[] sessionsFromPastWeek = getSessionsFromPastWeek();

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
