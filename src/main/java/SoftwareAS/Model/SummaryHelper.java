package SoftwareAS.Model;

public abstract class SummaryHelper {
	
	public static int[] calculateDailyWorkload(Session[] sessions) {
		return new int[5];
	}
	
	public static int calculateTotalWorkload(Session[] sessions) {
		return 0;
	}
	
	public static int calculateRemainingTime(Session[] sessions) {
		return 0;
	}

}
