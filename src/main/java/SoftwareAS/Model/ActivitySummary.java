package SoftwareAS.Model;

public class ActivitySummary {
	
	private int[] dailyWorkLoad;
	private int totalWorkLoad;
	private int remainingTime;
	
	public ActivitySummary(Session[] sessions) {
		
		dailyWorkLoad = SummaryHelper.calculateDailyWorkload(sessions);
	}
	
	public int[] getDailyWorkLoad() {
		return dailyWorkLoad;
	}
	public int gotTotalWorkLoad() {
		return totalWorkLoad;
	}
	public int getRemainingTime() {
		return remainingTime;
	}

}
