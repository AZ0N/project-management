package application.projectmanagement;

import java.util.ArrayList;
import java.util.Date;

public class PredefinedActivities implements Activities {
    private String name;
    private int estimatedTime;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> causeOfAbsence;

    public PredefinedActivities(String name, int estimatedTime, Date startDate, Date endDate, ArrayList<String> causeOfAbsence) {
        this.name = name;
        this.estimatedTime = estimatedTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.causeOfAbsence = causeOfAbsence;
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
    
    public ArrayList<String> getCauseOfAbsence() {
        return causeOfAbsence;
    }
    
    public void setCauseOfAbsence(ArrayList<String> causeOfAbsence) {
        this.causeOfAbsence = causeOfAbsence;
    }
    
    // add new predefined activities to the list 
    public void addCauseOfAbsence(String newActivity) {
        this.causeOfAbsence.add(newActivity);
    }

}

