package SoftwareAS.Model;

import java.util.GregorianCalendar;

public class Session {
	
	private int sessionId;
	private Developer developer;
	private Activity activity;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	
	public Session(GregorianCalendar startTime, GregorianCalendar endTime, Developer developer, Activity activity) {
		this.developer = developer;
		this.activity = activity;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int getSessionID() {
		return sessionId;
	}
	public int getDurationInHours() {
		return (int)(getEndTime().getTimeInMillis() - getStartTime().getTimeInMillis())/(1000 * 60 * 60);
	}
	public GregorianCalendar getStartTime() {
		return startTime;
	}
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}
	public GregorianCalendar getEndTime() {
		return endTime;
	}
	public void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}
	public Activity getActivity() {
		return activity;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void delete() {
		activity.deleteSession(this);
		developer.deleteSession(this);
	}
}
