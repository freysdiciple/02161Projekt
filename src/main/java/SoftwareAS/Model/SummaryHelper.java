package SoftwareAS.Model;

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
		return new int[] {0};
	}
	
	public static int[] calculateActivityWorkTimes(List<ActivitySummary> summaries) {
		return new int[] {0};
	}

}
