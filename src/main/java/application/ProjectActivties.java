package application;

import java.util.ArrayList;
import java.util.Date;

public class ProjectActivties implements Activities {
	

    private String name;
    private ArrayList<String> assignedEmployees;
    private int estimatedTime;
    private Date startDate;
    private Date endDate;

    public void ProjectActivity(String name, ArrayList<String> assignedEmployees, int estimatedTime, Date startDate, Date endDate) {
        this.name = name;
        this.assignedEmployees = assignedEmployees;
        this.estimatedTime = estimatedTime;
        this.startDate = startDate;
        this.endDate = endDate;
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
    public void addEmployee(String employee) {
        this.assignedEmployees.add(employee);
    }

    
}
