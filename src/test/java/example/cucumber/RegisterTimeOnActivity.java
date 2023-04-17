package example.cucumber;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterTimeOnActivity {
	
	private Project project;
	private ErrorMessageHolder errorMessageHolder;
	private ProjectActivity projectActivity;
	private ProjectManager projectManager;
	
	@Given("that {string} has an Activity named {string}.")
	public void thatHasAnActivityNamed(String projectName, String activityName) {
		projectActivity = new ProjectActivity(activityName);
	}

	@When("the Employee with the initials {string} provides the Activity named {string}.")
	public void theEmployeeWithTheInitialsProvidesTheActivityNamed(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the Employee with the initials {string} provides the total hours of work {int} on the Activity.")
	public void theEmployeeWithTheInitialsProvidesTheTotalHoursOfWorkOnTheActivity(String initials, Integer timeSpent) {
		Employee e = projectManager.getEmployee(initials);
	    projectActivity.addTimeUsedByEmployee(e, timeSpent);
	}

	@Then("the system will register the Employees’ hours spent on {string}")
	public void theSystemWillRegisterTheEmployeesHoursSpentOn(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
