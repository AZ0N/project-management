package application.projectmanagement;

/**
 * @author Melissa Safari - 224818
 */
public interface Activity {
	
    public void addTimeUsed(Employee employee, int timeUsed) throws Exception;
    public int getTimeUsed();

    public String getName();
    public String toString();
}
