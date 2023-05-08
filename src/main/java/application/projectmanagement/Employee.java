package application.projectmanagement;

/**
 * @author Jonathan Victor Flint - s224770
 */
public class Employee {

	private String initials;

	/**
	 * Create Employee with provided initials.
	 * Initials must be 1-4 letters.
	 * @param initials
	 * @throws IllegalArgumentException
	 */
	public Employee(String initials) throws IllegalArgumentException {
		this.initials = initials.toLowerCase();
    }
	
	public String getInitials() {
		return initials;
	}

	// An Employee is represented by initials. Used with ListView<Employee>.
	public String toString() {
		return getInitials();
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