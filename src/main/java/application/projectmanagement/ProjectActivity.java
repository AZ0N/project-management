package application.projectmanagement;

import java.util.ArrayList;
import java.util.Date;

public class ProjectActivity implements Activities {

    private String name;
    private ArrayList<Employee> assignedEmployees;
    private int estimatedTime;
    private int timeUsed;
    private Date startDate;
    private Date endDate;

    //method for ProjectActivities
    public ProjectActivity(String name) {
        this.name = name;
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
    
    //add employee to the activity
    public void addEmployee(Employee employee) {
        this.assignedEmployees.add(employee);
    }

    
    // Adds time to the activity used by the employee
    public void addTimeUsedByEmployee(Employee employee, int time) {
    	
    // Checks if the employee is assigned to the activity. 
    // If the employee is assigned to the activity, the method updates the timeUsed field of the activity
    // and calls the addTimeUsed() method of the Employee class to add the time used by the employee.	
        if (assignedEmployees.contains(employee)) {
            timeUsed += time;
            employee.addTimeUsed(time);
        }
    }

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

    public boolean hasEmployee(Employee employee) {
        return assignedEmployees.contains(employee);
    }
}
