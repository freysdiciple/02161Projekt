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
	
	public void registerSession(Activity activity, GregorianCalendar startTime, GregorianCalendar endTime) {
		Session newSession = new Session(startTime, endTime, this, activity);
		registeredSessions.add(newSession);
		activity.registerSession(newSession);;
	}
	
	public boolean overlapsWithOtherSession(Session current) {
		
		for(Session previous : registeredSessions) {
			
		}
		
		return false;
	}
}
