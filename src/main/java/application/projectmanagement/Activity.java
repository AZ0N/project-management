package application.projectmanagement;

import java.util.Date;

public interface Activity {
	
    public Date getStartDate();
    public void setStartDate(Date startDate);

    public Date getEndDate();
    public void setEndDate(Date endDate);
}
