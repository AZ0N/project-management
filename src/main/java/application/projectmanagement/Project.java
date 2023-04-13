package application.projectmanagement;

public class Project {
	private String projectName;
	private String initials;
	private String projectLeader;
	
	public Project(String projectName) {
		this.projectName = projectName;
	}
	
	public void appointProjectLeader (String projectName, String initials) {
		if (initials.isEmpty()) {
			projectLeader = initials;
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
