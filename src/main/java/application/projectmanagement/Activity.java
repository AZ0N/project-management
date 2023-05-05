package application.projectmanagement;

public interface Activity {
	
    public void addTimeUsed(Employee employee, int timeUsed) throws Exception;
    public int getTimeUsed();

    public String getName();
    public String toString();
}
