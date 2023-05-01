package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectLeaderSteps {

	private Project project;
	private ErrorMessageHolder errorMessageHolder;

	public ProjectLeaderSteps(ErrorMessageHolder errorMessageHolder) {
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("there is a Project named {string}.")
	public void thereIsAProjectNamed(String projectName) {
		// TODO Change to match new way of creating projects
		project = new Project(1, projectName);
	}

	@Given("the Project {string} has no Project leader")
	public void theProjectHasNoProjectLeader(String projectName) {
		assertTrue(project.getProjectLeader().isEmpty());
	}

	@When("the user provides the initials {string} of the person who wants to become Project leader.")
	public void theUserProvidesTheInitialsOfThePersonWhoWantsToBecomeProjectLeader(String initials) {
		try {
			project.appointProjectLeader(initials);
		} catch (Exception e) {
			this.errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the Project leader of {string} is the Employee with the initials {string}")
	public void theProjectLeaderOfIsTheEmployeeWithTheInitials(String projectLeader, String initials) {
		assertTrue(project.getProjectLeader().equals(initials));
	}

	@Given("that {string} has a Project leader with initials {string}.")
	public void thatHasAProjectLeader(String projectName,String initials) {
		try {
			project.appointProjectLeader(initials);
		} catch (Exception e) {
			this.errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

}
