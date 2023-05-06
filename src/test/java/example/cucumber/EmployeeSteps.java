package example.cucumber;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import application.projectmanagement.Employee;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

	private ProjectManager projectmanager;
	private ErrorMessageHolder errorMessageHolder;

	public EmployeeSteps(ProjectManager projectmanager, ErrorMessageHolder errorMessageHolder) {
		this.projectmanager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("an employee with initials {string} is added to the system")
	public void anEmployeeWithInitialsIsAddedToTheSystem(String initials) {
		try {
			projectmanager.addEmployee(initials);
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("there is an employee with initials {string} in the system")
	public void thereIsAnEmployeeWithInitialsInTheSystem(String initials) {
		// Try to get the employee with {initials}
		Employee e = projectmanager.getEmployee(initials);

		// Assert that we found an object
		assertNotEquals(e, null);

		// If we found an employee, check the initials match
		if (e != null) {
			assertTrue(e.getInitials().equals(initials));
		}
	}
}
