package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class SummaryHelper {
	
	public static int[] calculateDailyWorkload(Session[] sessions) {
		return new int[5];
	}
	
	public static int calculateTotalWorkload(Session[] sessions) {
		int newTotal = 0;
		
		for(Session session : sessions) {
			newTotal += session.getDurationInHours();
		}

		return newTotal;
	}
	
	public static int[] calculateActivityProgresses(List<ActivitySummary> summaries) {
		int[] progresses = new int[summaries.size()];
		
		for(int i=0; i<summaries.size(); i++) {
			progresses[i] = summaries.get(i).getRemainingTime();
		}
		
		return progresses;
	}
	
	public static int[] calculateActivityWorkTimes(List<ActivitySummary> summaries) {
		int[] worktimes = new int[summaries.size()];
		
		for(int i=0; i<summaries.size(); i++) {
			worktimes[i] = summaries.get(i).getTotalWorkLoad();
		}
		
		return worktimes;
	}

	public static boolean endedInInterval(Session session, GregorianCalendar start, GregorianCalendar end) {
		GregorianCalendar sessionEnd = session.getEndTime();

		if(sessionEnd.compareTo(start) > 0 && sessionEnd.compareTo(end) < 0) return true;
		else return false;
	}

	public static Session[] getSessionsFromPastWeek(List<Session> registeredSessions){
		List<Session> list = new ArrayList<>();

		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar start = getLastMonday(now);

		//We make a second now, as the first one is for
		//some reason corrupted while getting last monday
		GregorianCalendar end = new GregorianCalendar();


		for(Session session : registeredSessions) {
			if(endedInInterval(session, start, end)) list.add(session);
		}


		Session[] array = new Session[list.size()];
		list.toArray(array);

		return array;
	}


	public static GregorianCalendar getLastMonday(GregorianCalendar now) {
		int currentDayOfWeek = now.get(GregorianCalendar.DAY_OF_WEEK);
		GregorianCalendar lastMonday = now;

		//Puts sunday at end of daylist insted of in front
		if(currentDayOfWeek == 1) currentDayOfWeek += 7;
		currentDayOfWeek --;

		if(currentDayOfWeek == 1){
			lastMonday.add(GregorianCalendar.DAY_OF_YEAR, -7);
			return lastMonday;
		}

		int daysFromMonday = 0;
		while(currentDayOfWeek > 1) {
			daysFromMonday ++;
			currentDayOfWeek --;
		}

		//Returns last monday at 00:00
		lastMonday.add(GregorianCalendar.DAY_OF_YEAR, -daysFromMonday);
		lastMonday.add(GregorianCalendar.HOUR, 0);
		lastMonday.set(GregorianCalendar.HOUR_OF_DAY, 0);
		lastMonday.set(GregorianCalendar.MINUTE, 0);
		lastMonday.set(GregorianCalendar.SECOND, 0);
		lastMonday.set(GregorianCalendar.MILLISECOND, 0);
		return lastMonday;
	}

}
