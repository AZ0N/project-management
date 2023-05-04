package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
	
	private int ID;
	private String projectName;
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
	
	public List<ProjectActivity> getProjectActivities(){
		return projectActivities;
	}
	
	public String toString() {
		return ID + " - " + projectName;
	}

	/**
	 * Adds a project activity to the project.
	 * @param projectActivity to be added.
	 * @param employee adding the activity.
	 * @throws Exception if the project has a project leader different from employee.
	 */
	public void addActivity(ProjectActivity projectActivity, Employee employee) throws Exception {
		if (hasProjectLeader() && employee != projectLeader) {
			throw new Exception("Only project leader can create activities!");
		}
		projectActivity.setProject(this);
		projectActivities.add(projectActivity);
	}

	/**
	 * Get project activity with given name.
	 * @param activityName to get.
	 * @return project activity with given name, or null if it doesn't exist in project.
	 */
	public ProjectActivity getProjectActivity(String activityName) {
		return projectActivities.stream().filter(e -> e.getName().equals(activityName)).findFirst().orElse(null);
	}

	/**
	 * Checks if a given employee is in the project.
	 * @param employee The employee to check.
	 * @return True if the employee is the project leader or assigned to a activity in the project.
	 */
	public boolean isEmployeeInProject(Employee employee) {
		return employee == projectLeader || getActivitiesForEmployee(employee).size() > 0;
	}

	/**
	 * Returns all activities in project which has the employee assigned to them.
	 * @param employee to get activities for.
	 * @return list of activities with employee assigned to.
	 */
	public List<ProjectActivity> getActivitiesForEmployee(Employee employee) {
		return projectActivities.stream().filter(projectActivity -> projectActivity.hasEmployee(employee)).collect(Collectors.toList());
	} 

	/**
	 * Check if the given search string matches this project.
	 * Checks if searchText is contained in project name or ID. Ignores case.
	 * @param searchText String to check if matches project.
	 * @return True if searchText matches this project. False otherwise.
	 */
	public boolean match(String searchText) {
		return projectName.toLowerCase().contains(searchText.toLowerCase()) || Integer.toString(ID).contains(searchText);
	}
}
