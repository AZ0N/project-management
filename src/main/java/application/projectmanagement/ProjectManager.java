package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;

    public ProjectManager() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();
    }

    // Methods for Projects
    public void addProject(Project projectName) throws Exception{
    	if (getProject(projectName.getProjectName()) != null) {
    		throw new Exception("Project with name " + projectName + " already exists!");
    	}
    	projects.add(projectName);
    } 
    
    public void deleteProject(String projectName) throws Exception {
    	var projectToDelete = getProject(projectName);
    	if (projectToDelete != null) {
    		projects.remove(projectToDelete);
    	} else {
    		throw new Exception ("The Project doesn't exist");
    	}
    }
    
    public Project getProject(String projectName) {
        return projects.stream().filter(e -> e.getProjectName().equals(projectName)).findFirst().orElse(null);
	}

    public List<Project> getProjects() {
        return projects;
    }

    // Methods for Employees
	public void addEmployee(Employee employee) throws Exception {
        if (getEmployee(employee.getInitials()) != null) {
            throw new Exception("Employee with initials " + employee.getInitials() + " already exists!");
        }
        employees.add(employee);
    }

    public Employee getEmployee(String initials) {
        return employees.stream().filter(e -> e.getInitials().equals(initials)).findFirst().orElse(null);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}