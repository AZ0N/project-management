package application;

import java.util.ArrayList;

public class ProjectActivties {
	
	private String name;
    private ArrayList<String> assignedEmployees;
    private int estimatedTime;
    private int startWeek;
    private int endWeek;

    public ProjectActivties(String name, ArrayList<String> assignedEmployees, int estimatedTime, int startWeek, int endWeek) {
        this.name = name;
        this.assignedEmployees = assignedEmployees;
        this.estimatedTime = estimatedTime;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAssignedEmployees() {
        return assignedEmployees;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }
}
