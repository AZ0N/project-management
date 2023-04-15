package example.cucumber;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import application.projectmanagement.Projectmanager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProject {

	private Projectmanager projectmanager;
	private ErrorMessageHolder errorMessageHolder;

	public CreateProject(Projectmanager projectmanager, ErrorMessageHolder errorMessageHolder) {
		this.projectmanager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
	}

	private Project project;

	@Given("there is a new Project named {string}")
	public void thereIsANewProjectNamed(String name) {
		project = new Project(name);
	}

	@When("the Project called {string} is added to the system")
	public void theProjectCalledIsAddedToTheSystem(String string) {
		try {
			projectmanager.addProject(project);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the Project {string} is in the system")
	public void theProjectIsInTheSystem(String projectName) {
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
