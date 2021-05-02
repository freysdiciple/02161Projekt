package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;

public class Developer {
	
	private String id;
	private boolean isAdmin = false;
	protected DataBase database;
	private List<Session> registeredSessions = new ArrayList<>();
	
	public Developer(String id, DataBase database) {
		this.id = id;
		this.database = database;
	}
	public Developer() {}
	
	public String getId() {
		return id;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdminState(boolean state) {
		isAdmin = state;
	}
	
	public void registerSession(Activity activity, GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowedException, OverlappingSessionsException {
		if(startTime.compareTo(endTime) >= 0) throw new OperationNotAllowedException("A session cannot end before it starts...");
		if(overlapsWithOtherSession(startTime, endTime)) throw new OverlappingSessionsException("Overlapping Sessions");
		Session newSession = new Session(startTime, endTime, this, activity);
		
		registeredSessions.add(newSession);
		if(activity != null) activity.registerSession(newSession);
	}
	
	public List<Session> getRegisteredSessions(){
		return registeredSessions;
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
}
