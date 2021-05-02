package SoftwareAS.Model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSummary {

	private int[] activityProgress;
	private int[] activityWorktime;
	
	public ProjectSummary(List<Activity> activities) {
		
		
		List<ActivitySummary> summaries = new ArrayList<>();
		for(Activity activity : activities) {
			summaries.add(activity.createSummary());
		}
		
		activityProgress = SummaryHelper.calculateActivityProgresses(summaries);
		activityWorktime = SummaryHelper.calculateActivityWorkTimes(summaries);
	}
	
	public int[] getActivityProgress() {
		return activityProgress;
	}
	
	public int[] getActivityWorktime() {
		return activityWorktime;
	}

}
