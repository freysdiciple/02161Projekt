package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;

public class Developer {
	
	private String id;
	private boolean isAdmin = false;
	protected DataBase database;
	private List<Session> registeredSessions = new ArrayList<>();
	
	public Developer(String id, DataBase database) {
		this.id = id;
		this.database = database;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdminState(boolean state) {
		isAdmin = state;
	}
	
	public void registerSession(Activity activity, GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowedException {
		if(startTime.compareTo(endTime) >= 0) throw new OperationNotAllowedException("A session cannot end before it starts...");
		if(overlapsWithOtherSession(startTime, endTime)) throw new OperationNotAllowedException("Two sessions cannot overlap");
		Session newSession = new Session(startTime, endTime, this, activity);
		
		registeredSessions.add(newSession);
		activity.registerSession(newSession);;
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
