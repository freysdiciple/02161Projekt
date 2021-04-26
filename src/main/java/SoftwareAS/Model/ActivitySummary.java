package SoftwareAS.Model;

public class ActivitySummary {
	
	private int[] dailyWorkLoad;
	private int totalWorkLoad;
	private int remainingTime;
	
	public ActivitySummary(Activity activity, Session[] sessions) {
		
		dailyWorkLoad = SummaryHelper.calculateDailyWorkload(sessions);
		totalWorkLoad = SummaryHelper.calculateTotalWorkload(sessions);
		remainingTime = activity.getTimeLeft() - totalWorkLoad;
		activity.setTimeLeft(remainingTime);
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
