package example.cucumber;

import application.projectmanagement.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProject {
	
	private Project project;
	
	@Given("there is a new Project named {string}")
	public void thereIsANewProjectNamed(String name) {
		project = new Project(name);
	}
	
	@Given("{string} is not already in the system")
	public void isNotAlreadyInTheSystem(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the Project called {string} is added to the system")
	public void theProjectCalledIsAddedToTheSystem(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the Project {string} is in the system")
	public void theProjectIsInTheSystem(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}

