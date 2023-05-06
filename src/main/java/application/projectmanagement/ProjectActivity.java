package application.projectmanagement;

import java.util.ArrayList;

public class ProjectActivity implements Activity {

    private String name;
    private ArrayList<Employee> assignedEmployees;
    private int estimatedTime;
    private int timeUsed;
    private Project project;

    public ProjectActivity(String name) {
        this.name = name;
        assignedEmployees = new ArrayList<Employee>();
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
    
    public int getEstimatedTime() {
        return estimatedTime;
    }
    
    @Override
    public int getTimeUsed() {
        return timeUsed;
    }
    
    @Override
    public String getName() {
        return name;
    }

	public ArrayList<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}

    // An activity is represented by "projectname - activityname - timeused/estimatedTime",
    // eg. "23001 - First Activity - 12/30". Used by ListView<ProjectActivity>.
    @Override
	public String toString() {
		return project.getID() + " - " + name + " - " + timeUsed + "/" + estimatedTime;
	}

    /**
     * Sets the estimated time for this activity.
     * @param estimatedTime
     * @param employee setting the estimated time.
     * @throws IllegalArgumentException if estimated time is non-positive.
     * @throws Exception if the project has a project leader different from the employee trying to set the estimated time
     */
    public void setEstimatedTime(int estimatedTime, Employee employee) throws Exception {
        if (project.hasProjectLeader() && employee != project.getProjectLeader()) {
            throw new Exception("Only project leader can set estimated time!");
        }
        if (estimatedTime <= 0) {
            throw new IllegalArgumentException("Estimated time must be positive!");
        }
        this.estimatedTime = estimatedTime;
    }

    /**
     * Assign employee to an activity.
     * @param toAssign the employee to be assigned to this activity.
     * @param assignedBy the employee assigning the employee to this activity.
     * @throws Exception if the project has a project leader different from assignedBy.
     */
    public void assignEmployee(Employee toAssign, Employee assignedBy) throws Exception {
        if (project.hasProjectLeader() && project.getProjectLeader() != assignedBy) {
            throw new Exception("Only project leader can assign employees!");
        }
        this.assignedEmployees.add(toAssign);
    }
    
    /**
     * Adds time used on this activity by employee.
     * @param employee adding used time.
     * @param time to be added.
     * @throws IllegalArgumentException if time is non-positive.
     * @throws Exception if the project has a project leader different from employee.
     */
    @Override
    public void addTimeUsed(Employee employee, int time) throws Exception {
        if (!hasEmployee(employee)) {
            throw new Exception("Only employees assigned to activity can add time used!");
        }
        if (time <= 0) {
            throw new IllegalArgumentException("Only positive amount of time can be added!");
        }
        timeUsed += time;
    }

    /**
     * Determines if a given employee is assigned to this project activity.
     * @param employee The employee to check for.
     * @return True if the employee has been assigned to this project activity. Otherwise false.
     */
    public boolean hasEmployee(Employee employee) {
        return assignedEmployees.contains(employee);
    }
}
