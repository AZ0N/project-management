package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private String initials;
	private int timeUsed;
	private List<ProjectActivities> assignedActivities; 
	
	public Employee(String initials) {
		this.initials = initials;
		this.timeUsed = 0;
        this.assignedActivities = new ArrayList<>();
    }
	
	public String getInitials() {
		return initials;
	}
	
	public void setInitials(String initials) {
	    this.initials = initials;
	}

	public int getTimeUsed() {
	    return timeUsed;
	}

	public void setTimeUsed(int timeUsed) {
	    this.timeUsed = timeUsed;
	}
	    
	public void addTimeUsed(int time) {
	    this.timeUsed += time;
	}
	    
	public List<ProjectActivities> getAssignedActivities() {
	    return assignedActivities;
	}
	    
	public void assignActivity(ProjectActivities activity) {
	    assignedActivities.add(activity);
	}
	    
	public void addTimeUsedToActivity(ProjectActivities activity, int time) {
	    if (assignedActivities.contains(activity)) {
	        activity.addTimeUsedByEmployee(this, time);
	    } else {
	        System.out.println("Employee " + initials + " is not assigned to activity " + activity.getName());
	    }
	}

	public String toString() {
		return initials;
	}
}