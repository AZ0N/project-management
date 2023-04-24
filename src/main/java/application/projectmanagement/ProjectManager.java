package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

import application.timemanagement.TimeServer;

public class ProjectManager {
    
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;

    private TimeServer timeServer;

    public ProjectManager() {
        employees = new ArrayList<>();
        projects = new ArrayList<>(); 
    }

    public void setTimeServer(TimeServer timeServer) {
        this.timeServer = timeServer;
    }

    // Methods for Projects
    public void addProject(Project project) throws Exception{
    	if (getProject(project.getProjectName()) != null) {
    		throw new Exception("Project with name " + project.getProjectName() + " already exists!");
    	}
    	if (project.getProjectName().isEmpty()) {
    		throw new Exception("The project needs a name!");
    	}
    	projects.add(project);
    } 
    
    public void deleteProject(String projectName) throws Exception {
    	var projectToDelete = getProject(projectName);
    	if (projectToDelete != null) {
    		projects.remove(projectToDelete);
    	} else {
    		throw new Exception ("The Project doesn't exist!");
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
        if (employee.getInitials().isEmpty()) {
        	throw new Exception("The employee needs initials!");
        }
        employees.add(employee);
    }

    public Employee getEmployee(String initials) {
        return employees.stream().filter(e -> e.getInitials().equals(initials)).findFirst().orElse(null);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<ProjectActivity> getAllActivitiesForEmployee(Employee e) {
        ArrayList<ProjectActivity> result = new ArrayList<>();
        for (Project project : projects) {
            result.addAll(project.getActivitiesForEmployee(e));
        }
        return result;
    }

    public List<Project> getAllProjectsForEmployee(Employee e) {
        return projects.stream().filter(p -> p.isEmployeeInProject(e)).toList();
    }
}