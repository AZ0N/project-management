package example.cucumber;

import static org.junit.Assert.assertTrue;

import java.util.List;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
    
    private ProjectManager projectManager;

    private List<Employee> employeeSearchResult;
    private List<Project> projectSearchResult;

	public SearchSteps(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

    @When("an employee searches for employees with search text {string}")
    public void anEmployeeSearchesForEmployeesWithSearchText(String searchText) {
        employeeSearchResult = projectManager.searchEmployees(searchText);
    }

    @Then("the employee with initials {string} is in the search result")
    public void theEmployeeWithInitialsIsInTheSearchResult(String initials) {
        assertTrue(employeeSearchResult.contains(projectManager.getEmployee(initials)));
    }

    @Then("the employee with initials {string} is not in the search result")
    public void theEmployeeWithInitialsIsNotInTheSearchResult(String initials) {
        assertTrue(!employeeSearchResult.contains(projectManager.getEmployee(initials)));
    }

    @When("an employee searches for projects with search text {string}")
    public void anEmployeeSearchesForProjectsWithSearchText(String searchText) {
        projectSearchResult = projectManager.searchProjects(searchText);
    }

    @Then("the project with ID {int} is in the search result")
    public void theProjectWithIDIsInTheSearchResult(int projectID) {
        assertTrue(projectSearchResult.contains(projectManager.getProjectByID(projectID)));
    }

    @Then("the project with ID {int} is not in the search result")
    public void theProjectWithIDIsNotInTheSearchResult(int projectID) {
        assertTrue(!projectSearchResult.contains(projectManager.getProjectByID(projectID)));
    }
}
