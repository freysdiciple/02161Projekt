package SoftwareAS.Model;

import java.util.GregorianCalendar;

public class Session {
	
	private int sessionId;
	private Developer developer;
	private Activity activity;
	private Calendar startTime;
	private Calendar endTime;
	
	public Session(GregorianCalendar startTime, GregorianCalendar endTime, Developer developer, Activity activity) {
		this.developer = developer;
		this.activity = activity;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int getSessionID() {
		return sessionId;
	}
	public GregorianCalendar getStartTime() {
		return startTime;
	}
	public GregorianCalendar getEndTime() {
		return endTime;
	}
	public Activity getActivity() {
		return activity;
	}
	public Developer getDeveloper() {
		return developer;
	}
}
