package application.projectmanagement;

import java.util.ArrayList;

public class Project {
	private String projectName;
	private String initials;
	private String projectLeader;
	private ProjectActivities projectActivity;
	
    private ArrayList<ProjectActivities> projectActivityList;
	
	public Project(String projectName) {
		this.projectName = projectName;
		projectLeader = "";
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
	
	/*
	 * Melissa
	 */
	
	public void addActivity(ProjectActivities projectActivity) {
        this.projectActivityList.add(projectActivity);
	}
	
}
