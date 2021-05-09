package SoftwareAS.Model;

import java.util.GregorianCalendar;

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
	
	public void changeEndTime(String info) {
		String yearString = info.substring(6,10);
		String monthString = info.substring(3,5);
		String dayString = info.substring(0,2);
		String hourString = info.substring(11,13);
		String minString = info.substring(14,16);
		
		int year = Integer.parseInt(yearString);
		int month = Integer.parseInt(monthString);
		int day = Integer.parseInt(dayString);
		int hour = Integer.parseInt(hourString);
		int min = Integer.parseInt(minString);
		
		GregorianCalendar newEnd = new GregorianCalendar(year,month,day,hour,min);
		
		setEndTime(newEnd);
		setSessionId2();
	}
	
	public void changeStartTime(String info) {
		String yearString = info.substring(6,10);
		String monthString = info.substring(3,5);
		String dayString = info.substring(0,2);
		String hourString = info.substring(11,13);
		String minString = info.substring(14,16);
		
		int year = Integer.parseInt(yearString);
		int month = Integer.parseInt(monthString);
		int day = Integer.parseInt(dayString);
		int hour = Integer.parseInt(hourString);
		int min = Integer.parseInt(minString);
		
		GregorianCalendar newStart = new GregorianCalendar(year,month,day,hour,min);
		
		setStartTime(newStart);
		setSessionId2();
	}
	
}
