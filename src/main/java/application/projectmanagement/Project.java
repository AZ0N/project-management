package application.projectmanagement;

public class Project {
	private String projectName;
	private String initials;
	private String projectLeader;
	
	public Project(String projectName) {
		this.projectName = projectName;
		projectLeader = "";
	}
	
	public void appointProjectLeader (String initials) {
		if (this.projectLeader.isEmpty()) {
			this.projectLeader = initials;
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
	
}
