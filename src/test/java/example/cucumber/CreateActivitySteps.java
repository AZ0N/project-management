package example.cucumber;


import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {
	
    private Project project;
    private ProjectActivity ProjectActivty;
	
	@Given("there is a Project named {string} with Project leader {string}")
	public void thereIsAProjectNamedWithProjectLeader(String projectName, String projectLeader) {
		project = new Project(projectName);
		try {
			project.appointProjectLeader(projectLeader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Given("{string} has no Activity named {string}")
	public void hasNoActivityNamed(String projectName, String activityName) {
		assertTrue(project.getProjectActivities().stream().filter(e -> e.getName().equals(activityName)).findAny().orElse(null) == null);
	}
	
	@When("the Project leader {string} creates the Activity named {string}")
	public void theProjectLeaderCreatesTheActivityNamed(String projectName, String activityName) {
	    throw new io.cucumber.java.PendingException(); 
	}
	
	@Then("{string} has an Activity named {string}")
	public void hasAnActivityNamed(String projectName, String activityName) {
		assertTrue(project.getProjectActivities().stream().filter(e -> e.getName().equals(activityName)).findAny().orElse(null) != null);
	}
	
}

