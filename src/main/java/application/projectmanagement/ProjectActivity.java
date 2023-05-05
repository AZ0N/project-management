package application.projectmanagement;

import java.util.ArrayList;
import java.util.Date;

public class ProjectActivity implements Activity {

    private String name;
    private ArrayList<Employee> assignedEmployees;
    private int estimatedTime;
    private int timeUsed;
    private Date startDate;
    private Date endDate;
    private Project project;

    public ProjectActivity(String name) {
        this.name = name;
        assignedEmployees = new ArrayList<Employee>();
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public void addTimeUsedByEmployee(Employee employee, int time) throws Exception {
        if (!hasEmployee(employee)) {
            throw new Exception("Only employees assigned to activity can add time used!");
        }
        if (time <= 0) {
            throw new IllegalArgumentException("Only positive amount of time can be added!");
        }
        timeUsed += time;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

	public String getName() {
		return name;
	}
	
	public String toString() {
		return project.getID() + " - " + name + " - " + timeUsed + "/" + estimatedTime;
	}

    public boolean hasEmployee(Employee employee) {
        return assignedEmployees.contains(employee);
    }

	public ArrayList<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}
}
