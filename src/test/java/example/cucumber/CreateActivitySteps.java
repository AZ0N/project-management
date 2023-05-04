package example.cucumber;


import static org.junit.Assert.assertTrue;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {
	
	private ProjectManager projectManager;
	private ErrorMessageHolder errorMessageHolder;

	public CreateActivitySteps(ProjectManager projectManager, ErrorMessageHolder errorMessageHolder) {
		this.projectManager = projectManager;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("the employee {string} creates and activity named {string} on project with ID {int}")
	public void theEmployeeCreatesAndActivityNamedOnProjectWithID(String employeeInitials, String activityName, int projectID) {
		Employee employee = projectManager.getEmployee(employeeInitials);
		Project project = projectManager.getProjectByID(projectID);
		try {
			project.addActivity(new ProjectActivity(activityName), employee);
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project with ID {int} has an activity named {string}")
	public void theProjectWithIDHasAnActivityNamed(int projectID, String activityName) {
		Project project = projectManager.getProjectByID(projectID);
		assertTrue(project.getProjectActivity(activityName).getName().equals(activityName));
	}
}

