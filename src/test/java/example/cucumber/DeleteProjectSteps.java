package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectManager;
import application.timemanagement.ManualTimeServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteProjectSteps {
	
	private ProjectManager projectManager;
	private ErrorMessageHolder errorMessageHolder;
	private ManualTimeServer manualTimeServer;

	public DeleteProjectSteps(ProjectManager projectmanager, ErrorMessageHolder errorMessageHolder, ManualTimeServer manualTimeServer) {
		this.projectManager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
		this.manualTimeServer = manualTimeServer;
		this.projectManager.setTimeServer(this.manualTimeServer);
	}

	@When("the Project with ID {int} is deleted")
	public void theProjectWithIDIsDeleted(int projectID) {
		try {
			projectManager.deleteProject(projectID);
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project with ID {int} and name {string} is not in the system")
	public void theProjectWithIDAndNameIsNotInTheSystem(int projectID, String projectName) {
		Project project = projectManager.getProjectByID(projectID);
		if (project != null) {
			assertTrue(!project.getProjectName().equals(projectName));
		}
	}

	@Given("there isn't a Project with ID {int}")
	public void thereIsnTAProjectWithID(int projectID) {
		assertTrue(projectManager.getProjectByID(projectID) == null);
	}
}
