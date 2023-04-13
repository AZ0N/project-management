package example.cucumber;

import application.projectmanagement.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectLeaderSteps {
	
	private Project project;
	
	

@Given("there is a Project named {string}.")
public void thereIsAProjectNamed(String projectName) {
    project = new Project(projectName);
}

@Given("the Project {string} has no Project leader")
public void theProjectHasNoProjectLeader(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user provides the initials {string} of the person who wants to become Project leader.")
public void theUserProvidesTheInitialsOfThePersonWhoWantsToBecomeProjectLeader(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the Project leader of {string} is the Employee with the initials {string}")
public void theProjectLeaderOfIsTheEmployeeWithTheInitials(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



}
