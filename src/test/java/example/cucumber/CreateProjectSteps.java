package example.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectManager;
import application.timemanagement.ManualTimeServer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProjectSteps {

	private ProjectManager projectmanager;
	private ErrorMessageHolder errorMessageHolder;

	public CreateProjectSteps(ProjectManager projectmanager, ErrorMessageHolder errorMessageHolder, ManualTimeServer manualTimeServer) {
		this.projectmanager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
		this.projectmanager.setTimeServer(manualTimeServer);
	}

	@When("a Project called {string} is added to the system")
	public void theProjectCalledIsAddedToTheSystem(String projectName) {
		try {
			projectmanager.createProject(projectName);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the Project with id {int} and name {string} is in the system")
	public void theProjectWithIdAndNameIsInTheSystem(int ID, String name) {

		Project p = projectmanager.getProjectByID(ID);

		assertNotEquals(p, null);

		if (p != null) {
			assertEquals(p.getID(), ID);
			assertEquals(p.getProjectName(), name);
		}

	}

	@Then("the Project called {string} is in the system")
	public void theProjectCalledIsInTheSystem(String projectName) {
		// Try to get the project with {projectName}
		Project e = projectmanager.getProject(projectName); 

		// Assert that we found an object
		assertNotEquals(e, null);

		// If we found a project, check of the names match
		if (e != null) {
			assertTrue(e.getProjectName().equals(projectName));
		}
	}
}
