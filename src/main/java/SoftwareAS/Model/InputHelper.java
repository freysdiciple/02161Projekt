package SoftwareAS.Model;

import java.util.GregorianCalendar;
import java.util.List;

import Exceptions.OutOfBoundsException;

public class InputHelper {
	
	public static GregorianCalendar stringToGregorianCalendar(String time) throws OutOfBoundsException {
		
		boolean usedTodayParameter = false;
		
		if(time.length() != 16) {
			if(time.length() == 11) {
				usedTodayParameter = true;
			}
			else throw new OutOfBoundsException("String must be a length of 16 or 5");
		}
		
		String yearString = time.substring(6,10);
		String monthString = time.substring(3,5);
		String dayString = time.substring(0,2);
		String hourString = time.substring(11,13);
		String minString = time.substring(14,16);
		
		if(!usedTodayParameter) {
			yearString = time.substring(6,10);
			monthString = time.substring(3,5);
			dayString = time.substring(0,2);
			hourString = time.substring(11,13);
			minString = time.substring(14,16);
		}
		else {
			GregorianCalendar today = new GregorianCalendar();
			yearString = today.get(GregorianCalendar.YEAR) + "";
			monthString = today.get(GregorianCalendar.MONTH) + "";
			dayString = today.get(GregorianCalendar.DAY_OF_MONTH) + "";
			hourString = time.substring(6,8);
			minString = time.substring(9,11);
		}
		
		int year = Integer.parseInt(yearString);
		int month = Integer.parseInt(monthString);
		int day = Integer.parseInt(dayString);
		int hour = Integer.parseInt(hourString);
		int min = Integer.parseInt(minString);
		
		if(month<1 || month>12 || day<1 || day>31 || hour <0 || hour > 23 || min<0 || min > 59) {
			throw new OutOfBoundsException("The time has to be compatible with GregorianCalendar");
			
		}
		
		return new GregorianCalendar(year,month,day,hour,min);
		
	}
	
	public static Object[] stringToSessionProperties(String sessionInfo, Developer currentUser, Activity activity) throws OutOfBoundsException {
		if(sessionInfo.length() != 40 && sessionInfo.length() != 35 && sessionInfo.length() != 30) throw new OutOfBoundsException("Input not correct");
		
		//Continue
		int activityid;
				
		//Get activity id if not under activity
		if(activity == null) {
			try {
				String activityidString = sessionInfo.substring(0,6);
				activityid = Integer.parseInt(activityidString);
				sessionInfo = sessionInfo.substring(7, sessionInfo.length());
			}catch(Exception e) {
				throw e;
			}
		}
		else activityid = activity.getId();
		
		//Divide up dates
		String startString;
		String endString;
		
		if(sessionInfo.substring(0,5).equals("today")) {
			startString = sessionInfo.substring(0,11);
			sessionInfo = sessionInfo.substring(12, sessionInfo.length());
		}
		else {
			startString = sessionInfo.substring(0,16);
			sessionInfo = sessionInfo.substring(17, sessionInfo.length());
		}
		
		if(sessionInfo.substring(0,5).equals("today")) {
			endString = sessionInfo.substring(0,11);
		}
		else {
			endString = sessionInfo.substring(0,16);
		}
				
		//Convert to dates and register session
		try {
			GregorianCalendar start = stringToGregorianCalendar(startString);
			GregorianCalendar end = stringToGregorianCalendar(endString);
			
			if(activity == null) {
				List<Activity> userActivities = currentUser.getActivities();
						
				for(Activity act : userActivities) {
					if(act.getId() == activityid) activity = act;
				}
			}
					
			return new Object[] {start, end, activity};
			
		} catch(OutOfBoundsException e) {
			throw e;
		}
				
		
				
	}

}
