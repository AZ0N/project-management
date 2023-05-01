package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private String initials;
	private int timeUsed;
	private List<ProjectActivity> assignedActivities; 
	private ArrayList<Project> projects;
	
	public Employee(String initials) throws IllegalArgumentException {
		// Verify initials
		if (initials.length() == 0 || initials.length() > 4 || !initials.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Initials \"" + initials + "\" not valid. Only 1-4 letters allowed.");
		}
		this.initials = initials.toLowerCase();
		this.timeUsed = 0;
        this.assignedActivities = new ArrayList<>();
    }
	
	public String getInitials() {
		return initials;
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
	    
	public List<ProjectActivity> getAssignedActivities() {
	    return assignedActivities;
	}
	
	public List<Project> getProjects() {
	    return projects;
	}
	 
	public void assignActivity(ProjectActivity activity) {
	    assignedActivities.add(activity);
	}
	
	public void addTimeUsedToActivity(ProjectActivity activity, int time) {
	    if (assignedActivities.contains(activity)) {
	        activity.addTimeUsedByEmployee(this, time);
	    } else {
	        System.out.println("Employee " + initials + " is not assigned to activity " + activity.getName());
	    }
	}

	public String toString() {
		return initials;
	}

	/**
	 * Check if the given search string matches this Employee
	 * @param searchText String to check if matches Employee
	 * @return True if searchText matches this Employee. False otherwise
	 */
	public boolean match(String searchText) {
		return initials.contains(searchText);
	}
}