package application.projectmanagement;

public class Employee {

	private String initials;

	/**
	 * Create Employee with provided initials.
	 * Initials must be 1-4 letters.
	 * @param initials
	 * @throws IllegalArgumentException
	 */
	public Employee(String initials) throws IllegalArgumentException {
		// Verify initials
		if (initials.length() == 0 || initials.length() > 4 || !initials.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Initials " + initials + " not valid. Only 1-4 letters allowed.");
		}
		this.initials = initials.toLowerCase();
    }
	
	public String getInitials() {
		return initials;
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