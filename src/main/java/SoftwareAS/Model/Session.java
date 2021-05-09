package SoftwareAS.Model;

import java.util.GregorianCalendar;

import Exceptions.OutOfBoundsException;
import Exceptions.SessionNotFoundException;

public class Session {
	
	private int sessionId;
	private String sessionId2;
	private Developer developer;
	private Activity activity;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	
	public Session(GregorianCalendar startTime, GregorianCalendar endTime, Developer developer, Activity activity) {
		this.developer = developer;
		this.activity = activity;
		this.startTime = startTime;
		this.endTime = endTime;		
		setSessionId2();
	}
	
	public void setSessionId2() {
		sessionId2 = "" + developer.getId() + activity.getId() + startTime.get(startTime.YEAR) + startTime.get(startTime.MONTH) + 
				startTime.get(startTime.DATE) + startTime.get(startTime.HOUR_OF_DAY) + startTime.get(startTime.MINUTE) + 
				endTime.get(endTime.YEAR) + endTime.get(endTime.MONTH) + endTime.get(endTime.DATE) + endTime.get(endTime.HOUR_OF_DAY) +
				endTime.get(endTime.MINUTE);
		
	}
	
	public int getSessionID() {
		return sessionId;
	}
	public String getSessionID2() {
		return sessionId2;
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
	
	public void changeEndTime(String info) throws OutOfBoundsException {
		GregorianCalendar newEnd = InputHelper.stringToGregorianCalendar(info);
		
		setEndTime(newEnd);
		setSessionId2();
	}
	
	public void changeStartTime(String info) throws OutOfBoundsException {
	
		GregorianCalendar newStart = InputHelper.stringToGregorianCalendar(info);
		
		setStartTime(newStart);
		setSessionId2();
	}
	
}
