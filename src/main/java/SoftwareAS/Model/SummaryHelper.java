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

}
