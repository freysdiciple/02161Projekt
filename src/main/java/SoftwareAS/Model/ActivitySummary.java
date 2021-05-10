package SoftwareAS.Model;


// This class is made by Massimo
public class ActivitySummary {
	
	private int[] dailyWorkLoad;
	private int totalWorkLoad;
	private int remainingTime;
	
	public ActivitySummary(Activity activity, Session[] list) {
		
		dailyWorkLoad = SummaryHelper.calculateDailyWorkload(list);
		totalWorkLoad = SummaryHelper.calculateTotalWorkload(list);
		remainingTime = activity.getTimeLeft() - totalWorkLoad;
		activity.setTimeLeft(remainingTime);
	}
	
	public int[] getDailyWorkLoad() {
		return dailyWorkLoad;
	}
	public int getTotalWorkLoad() {
		return totalWorkLoad;
	}
	public int getRemainingTime() {
		return remainingTime;
	}

}
