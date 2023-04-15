package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class Projectmanager {
    
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;

    public Projectmanager() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();
    }

    // Methods for Projects
    public void addProject(Project projectName) throws Exception{
    	if (getProject(projectName.getProjectName()) != null) {
    		throw new Exception("Project with this name already exists!");
    	}
    	projects.add(projectName);
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
            throw new Exception("Employee with initials already exists!");
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