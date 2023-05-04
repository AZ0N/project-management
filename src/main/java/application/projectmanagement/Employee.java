package application.projectmanagement;

public class Employee {

	private String initials;
	private int timeUsed;
	
	public Employee(String initials) throws IllegalArgumentException {
		// Verify initials
		if (initials.length() == 0 || initials.length() > 4 || !initials.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Initials " + initials + " not valid. Only 1-4 letters allowed.");
		}
		this.initials = initials.toLowerCase();
		this.timeUsed = 0;
    }
	
	public String getInitials() {
		return initials;
	}

	public int getTimeUsed() {
	    return timeUsed;
	}

	public void setTimeUsed(int timeUsed) {
	    this.timeUsed = timeUsed;
	}
	    
	public void addTimeUsed(int time) {
	    this.timeUsed += time;
	}
	    
	public String toString() {
		return initials;
	}

	/**
	 * Check if the given search string matches this Employee
	 * @param searchText String to check if matches Employee
	 * @return True if searchText matches this Employee. False otherwise
	 */
	public boolean match(String searchText) {
		return initials.contains(searchText);
	}
}