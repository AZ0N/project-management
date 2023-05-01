package example.cucumber;

import static org.junit.Assert.assertEquals;

import application.projectmanagement.ProjectManager;
import application.timemanagement.ManualTimeServer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectLeaderSteps {

	private ProjectManager projectManager;
	private ErrorMessageHolder errorMessageHolder;
	private ManualTimeServer manualTimeServer;

	public ProjectLeaderSteps(ProjectManager projectManager, ErrorMessageHolder errorMessageHolder, ManualTimeServer manualTimeServer) {
		this.projectManager = projectManager;
		this.errorMessageHolder = errorMessageHolder;
		this.manualTimeServer = manualTimeServer;

		this.projectManager.setTimeServer(this.manualTimeServer);
	}

	@When("the employee with initials {string} is appointed project leader of project with ID {int}")
	public void theEmployeeWithInitialsIsAppointedProjectLeaderOfProjectWithID(String initials, int ID) {
		try {
			projectManager.getProjectByID(ID).appointProjectLeader(projectManager.getEmployee(initials));
		}
		catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project leader of the project with ID {int} is {string}")
	public void theProjectLeaderOfTheProjectWithIDIs(int ID, String projectLeaderInitials) {
		assertEquals(projectManager.getProjectByID(ID).getProjectLeader().getInitials(), projectLeaderInitials);
	}
}
