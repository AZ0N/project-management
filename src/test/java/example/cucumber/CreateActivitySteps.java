package example.cucumber;


import static org.junit.Assert.assertTrue;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {
	
    private Project project;
    private ProjectActivity projectActivity;
	
	@Given("there is a Project named {string} with Project leader {string}")
	public void thereIsAProjectNamedWithProjectLeader(String projectName, String projectLeader) {
		// TODO Change to match new way of creating projects
		project = new Project(1, projectName);
		try {
			//project.appointProjectLeader(projectLeader);
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
	    projectActivity = new ProjectActivity(activityName);
	    project.addActivity(projectActivity); 
	}
	
	@Then("{string} has an Activity named {string}")
	public void hasAnActivityNamed(String projectName, String activityName) {
		assertTrue(project.getProjectActivities().stream().filter(e -> e.getName().equals(activityName)).findAny().orElse(null) != null);
	}
	
}

