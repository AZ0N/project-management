package example.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;

import application.projectmanagement.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectLeaderSteps {

	private Project project;
	private String errorMessage;

	@Given("there is a Project named {string}.")
	public void thereIsAProjectNamed(String projectName) {
		project = new Project(projectName);
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
			this.errorMessage = e.getMessage();
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
			this.errorMessage = e.getMessage();
		}
	}

	@Then("the system provides the error message {string}")
	public void theSystemProvidesTheErrorMessage(String expectedErrorMessage) {
		assertEquals(expectedErrorMessage,errorMessage);
	}

}
