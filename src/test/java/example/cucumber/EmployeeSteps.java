package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.projectmanagement.Employee;
import application.projectmanagement.Projectmanager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

	private Projectmanager projectmanager;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;

	public EmployeeSteps(Projectmanager projectmanager, ErrorMessageHolder errorMessageHolder) {
		this.projectmanager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) {
		employee = new Employee(initials);
	}

	@When("the employee is added to the system")
	public void theEmployeeIsAddedToTheSystem() {
		try {
			projectmanager.AddEmployee(employee);
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("there is an employee with initials {string} in the system")
	public void thereIsAnEmployeeWithInitialsInTheSystem(String initials) {
		// Try to get the employee with {initials}
		Employee e = projectmanager.GetEmployee(initials);

		// Assert that we found an object
		assertNotEquals(e, null);

		// If we found an employee, check the initials match
		if (e != null) {
			assertTrue(e.getInitials().equals(initials));
		}
	}

    @Then("an error message {string} is shown")
    public void an_error_message_is_shown(String errormessage) {
		errorMessageHolder.getErrorMessage().equals(errormessage);
    }
}
