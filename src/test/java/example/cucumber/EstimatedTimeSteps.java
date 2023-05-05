package example.cucumber;

import static org.junit.Assert.assertEquals;

import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import application.timemanagement.ManualTimeServer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EstimatedTimeSteps {
    
    private ProjectManager projectManager;
    private ErrorMessageHolder errorMessageHolder;
    private ManualTimeServer manualTimeServer;

    public EstimatedTimeSteps(ProjectManager projectmanager, ErrorMessageHolder errorMessageHolder, ManualTimeServer manualTimeServer) {
		this.projectManager = projectmanager;
		this.errorMessageHolder = errorMessageHolder;
		this.manualTimeServer = manualTimeServer;
		this.projectManager.setTimeServer(this.manualTimeServer);
	}

    @When("the employee {string} sets the estimated time for activity {string} on project {int} to {int}")
    public void theEmployeeSetsTheEstimatedTimeForActivityOnProjectTo(String initals, String activityName, int projectID, int estimatedTime) {
        ProjectActivity projectActivity = projectManager.getProjectByID(projectID).getProjectActivity(activityName);
        try {
            projectActivity.setEstimatedTime(estimatedTime, projectManager.getEmployee(initals));
        }
        catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity {string} on project with ID {int} has an estimated time of {int} hours")
    public void theActivityOnProjectWithIDHasAnEstimatedTimeOfHours(String activityName, int projectID, int estimatedTime) {
        Project project = projectManager.getProjectByID(projectID);
        ProjectActivity projectActivity = project.getProjectActivity(activityName);
        assertEquals(projectActivity.getEstimatedTime(), estimatedTime);
    }
}
