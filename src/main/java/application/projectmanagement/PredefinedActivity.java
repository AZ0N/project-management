package application.projectmanagement;

import java.util.ArrayList;
import java.util.Date;

public class PredefinedActivity implements Activities {
    private String predefinedActivityName;
    private int estimatedTime;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> causeOfAbsence;

    public PredefinedActivity(String predefinedActivityName, int estimatedTime, Date startDate, Date endDate) {
        this.predefinedActivityName = predefinedActivityName;
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

