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
	private Employee employee;

	public EmployeeSteps(Projectmanager projectmanager) {
		this.projectmanager = projectmanager;
	}

	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) {
		employee = new Employee(initials);
	}

	@When("the employee is added to the system")
	public void theEmployeeIsAddedToTheSystem() {
		projectmanager.AddEmployee(employee);
	}

	@Then("there is an employee with initials {string} in the system")
	public void thereIsAnEmployeeWithInitialsInTheSystem(String initials) {
		// Try to get the employee with {initials}
		Employee e = projectmanager.GetEmployee(initials);

		// Assert that we found an object
		assertNotEquals(e, null);

		// If we found an employee, check the initials match
		if (e != null) {
			assertTrue(e.GetInitials().equals(initials));
		}
	}
}
