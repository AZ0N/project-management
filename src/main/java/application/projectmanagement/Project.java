package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
	
	private int ID;
	private String projectName;
	private String initials;
	private Employee projectLeader;
	private ArrayList<ProjectActivity> projectActivities;
	
	public Project(int projectID, String projectName) throws IllegalArgumentException {
		if (projectName.length() == 0 || projectName.length() > 30) {
			throw new IllegalArgumentException("Project name " + projectName + " is not valid.");
		}
		this.ID = projectID;
		this.projectName = projectName;
		this.projectActivities = new ArrayList<ProjectActivity>();
	}
	
	public void appointProjectLeader (Employee employee) throws Exception {
		if (projectLeader != null) {
			throw new Exception("This projects already has a project leader!");
		}
		projectLeader = employee;
	}
	
	public String getInitials() {
		return initials;
	}

	public int getID() {
		return ID;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public Employee getProjectLeader() {
		return projectLeader;
	}

	public boolean hasProjectLeader() {
		return projectLeader != null;
	}
	
	public String toString() {
		return ID + " - " + projectName;
	}

	public void addActivity(ProjectActivity projectActivity) {
		projectActivities.add(projectActivity);
	}

	public List<ProjectActivity> getProjectActivities(){
		return projectActivities;
	} 

	public boolean isEmployeeInProject(Employee e) {
		return getActivitiesForEmployee(e).size() > 0;
	}

	public List<ProjectActivity> getActivitiesForEmployee(Employee e) {
		return projectActivities.stream().filter(projectActivity -> projectActivity.hasEmployee(e)).collect(Collectors.toList());
	} 

	/**
	 * Check if the given search string matches this Project 
	 * @param searchText String to check if matches Project
	 * @return True if searchText matches this Project. False otherwise
	 */
	public boolean match(String searchText) {
		return projectName.contains(searchText);
	}
}
