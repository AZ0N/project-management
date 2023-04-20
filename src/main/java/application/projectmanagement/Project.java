package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private String projectName;
	private String initials;
	private String projectLeader;
	private ArrayList<ProjectActivity> projectActivities;
	
	public Project(String projectName) {
		this.projectName = projectName;
		projectLeader = "";
		projectActivities = new ArrayList<ProjectActivity>();
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

	public ProjectActivity getProjectActivity(ProjectActivity projectActivityName) {
		return projectActivities.stream().filter(e -> e.getName().equals(projectActivityName)).findFirst().orElse(null);
	}
	
	public void deleteProjectActivity(ProjectActivity projectActivityName) throws Exception {
    	var activityToDelete = getProjectActivity(projectActivityName);
    	if (activityToDelete != null) {
    		projectActivities.remove(activityToDelete);
    	} else {
    		throw new Exception ("The Project doesn't exist!");
    	}
    }
	
	
}
