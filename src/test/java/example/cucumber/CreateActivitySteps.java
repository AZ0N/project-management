package example.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {
	
	private String projectName;
    private String projectLeader;
    private String activityName;
    private boolean activityCreated;
	
	@Given("there is a Project named {string} with Project leader {string}")
	public void thereIsAProjectNamedWithProjectLeader(String projectName, String projectLeader) {
	
		        this.projectName = projectName;
		        this.projectLeader = projectLeader;
		        
	    throw new io.cucumber.java.PendingException();
	    
	}
	
	@Given("{string} has no Activity named {string}")
	public void hasNoActivityNamed(String string, String string2) {
		
		// Set activityCreated to false if it does not exist
        activityCreated = false;
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the Project leader {string} creates the Activity named {string}")
	public void theProjectLeaderCreatesTheActivityNamed(String string, String string2) {
	
		// Set activityCreated to true if it is created successfully
		this.activityName = string2;
        activityCreated = true;
		
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("{string} has an Activity named {string}")
	public void hasAnActivityNamed(String string, String string2) {
	    
	     // Assert that activityCreated is true
	    throw new io.cucumber.java.PendingException();
	}
	
	
}

