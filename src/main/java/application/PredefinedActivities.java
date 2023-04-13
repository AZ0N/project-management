package application;

import java.util.ArrayList;
import java.util.List;


public class PredefinedActivities {
	
	private String predefinedActivity;
	
	private List<String> predefinedActivities = new ArrayList<>();
	
	public void predefinedActivities (String predefinedActivity) {
		
		this.predefinedActivity = predefinedActivity;
		
		predefinedActivities.add(predefinedActivity);

	}
}
