package example.cucumber;

import static org.junit.Assert.assertEquals;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterTimeOnActivity {
	
	private ProjectManager projectManager;
	private ErrorMessageHolder errorMessageHolder;

	public RegisterTimeOnActivity(ProjectManager projectManager, ErrorMessageHolder errorMessageHolder) {
		this.projectManager = projectManager;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("the employee {string} adds {int} hours used on activity {string} on project with ID {int}")
	public void theEmployeeAddsHoursUsedOnActivityOnProjectWithID(String employeeInitials, int timeUsed, String activityName, int projectID) {
		Project project = projectManager.getProjectByID(projectID);
		ProjectActivity projectActivity = project.getProjectActivity(activityName);
		try {
			projectActivity.addTimeUsed(projectManager.getEmployee(employeeInitials), timeUsed);
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} on project with ID {int} has {int} hours used")
	public void theActivityOnProjectWithIDHasHoursUsed(String activityName, int projectID, int timeUsed) {
		ProjectActivity projectActivity = projectManager.getProjectByID(projectID).getProjectActivity(activityName);
		assertEquals(projectActivity.getTimeUsed(), timeUsed);
	}
}
