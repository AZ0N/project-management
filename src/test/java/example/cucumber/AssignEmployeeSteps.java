package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignEmployeeSteps {

    private ProjectManager projectManager;
    private ErrorMessageHolder errorMessageHolder;

    public AssignEmployeeSteps(ProjectManager projectManager, ErrorMessageHolder errorMessageHolder) {
        this.projectManager = projectManager;
        this.errorMessageHolder = errorMessageHolder;
    }
    
    @When("the employee {string} is assigned to the activity {string} on project with ID {int} by {string}")
    public void theEmployeeIsAssignedToTheActivityOnProjectWithIDBy(String employeeInitials, String activityName, int projectID, String userInitials) {
        Project project = projectManager.getProjectByID(projectID);
        ProjectActivity projectActivity = project.getProjectActivity(activityName);
        try {
            projectActivity.assignEmployee(projectManager.getEmployee(employeeInitials), projectManager.getEmployee(userInitials));
        }
        catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity {string} on project with ID {int} has {string} assigned")
    public void theActivityOnProjectWithIDHasAssigned(String activityName, int projectID, String employeeInitials) {
        Employee employee = projectManager.getEmployee(employeeInitials);
        ProjectActivity projectActivity = projectManager.getProjectByID(projectID).getProjectActivity(activityName);
        assertTrue(projectActivity.getAssignedEmployees().contains(employee));
    }
}
