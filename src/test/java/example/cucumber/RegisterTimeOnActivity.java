package example.cucumber;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterTimeOnActivity {
	
	private Project project;
	private ErrorMessageHolder errorMessageHolder;
	private ProjectActivity projectActivity;
	

@When("the Employee provides the initials {string} and an activity named {string}.")
public void theEmployeeProvidesTheInitialsAndAnActivityNamed(String projectName, String activityName) {
	projectActivity = new ProjectActivity(activityName);
}

@When("the Employee provides the total hours of work {string} on the Activity.")
public void theEmployeeProvidesTheTotalHoursOfWorkOnTheActivity(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the system will register the Employees’ hours spent on \"Activity {int}")
public void theSystemWillRegisterTheEmployeesHoursSpentOnActivity(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
	

}
