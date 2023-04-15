package example.cucumber;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteProjectSteps {
	
	private ProjectManager projectManager;
	private ErrorMessageHolder errorMessageHolder;

	public DeleteProjectSteps(ProjectManager projectmanager, ErrorMessageHolder errorMessageHolder) {
		this.projectManager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the Project called {string} is deleted")
	public void theProjectCalledIsDeleted(String projectName) {
	    try {
			projectManager.deleteProject(projectName);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the Project {string} is not in the system")
	public void theProjectIsNotInTheSystem(String projectName) {
	    assertNull(projectManager.getProject(projectName));
	}
	
	@Given("there isn't a Project named {string}")
	public void thereIsnTAProjectNamed(String projectName) {
	    if (projectManager.getProject(projectName) != null) {
	    	 try {
				projectManager.deleteProject(projectName);
			} catch (Exception e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
	    }
	}

}
