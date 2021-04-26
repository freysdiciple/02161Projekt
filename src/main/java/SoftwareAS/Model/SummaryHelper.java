package SoftwareAS.Model;

public abstract class SummaryHelper {
	
	public static int[] calculateDailyWorkload(Session[] sessions) {
		return new int[5];
	}
	
	public static int calculateTotalWorkload(Session[] sessions) {
		return 0;
	}
	
	public static int calculateRemainingTime(Session[] sessions, int previousRemaining) {
		
		int prevTotal = previousRemaining;
		int newTotal = 0;
		
		for(Session session : sessions) {
			newTotal += session.getDuration();
		}
		prevTotal -= newTotal;
		
		if(prevTotal > 0) return prevTotal; else return 0;
	}

}
