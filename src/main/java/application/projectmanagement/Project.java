package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
	
	private String projectName;
	private String initials;
	private String projectLeader;
	private ArrayList<ProjectActivity> projectActivities;
	
	public Project(String projectName) throws IllegalArgumentException {
		if (projectName.length() == 0 || projectName.length() > 30) {
			throw new IllegalArgumentException("Project name " + projectName + " is not valid.");
		}
		this.projectName = projectName;
		this.projectLeader = "";
		this.projectActivities = new ArrayList<ProjectActivity>();
	}
	
	public void appointProjectLeader (String initials) throws Exception {
		if (this.projectLeader.isEmpty()) {
			this.projectLeader = initials;
		} else {
			throw new Exception("This project already has a project leader");
		}
	}
	
	public String getInitials() {
		return initials;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getProjectLeader() {
		return projectLeader;
	}
	
	public String toString() {
		return projectName;
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
