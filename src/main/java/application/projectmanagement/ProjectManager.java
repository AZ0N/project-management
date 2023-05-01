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

    public void createProject(String projectName) throws IllegalArgumentException, Exception {
        if (getProject(projectName) != null) {
    		throw new Exception("Project with name " + projectName + " already exists!");
        }
        Project p = new Project(projectName);
        projects.add(p);
    }
    
    public void deleteProject(String projectName) throws Exception {
    	var projectToDelete = getProject(projectName);
    	if (projectToDelete != null) {
    		projects.remove(projectToDelete);
    	} else {
    		throw new Exception ("The Project doesn't exist!");
    	}
    }
    
    public void addEmployee(String initials) throws IllegalArgumentException, Exception {
        if (getEmployee(initials) != null) {
            throw new Exception("Employee with initials " + initials + " already exists!");
        }
        Employee e = new Employee(initials);
        employees.add(e);
    }
    
    public List<Project> getProjects() {
        return projects;
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
    
    public Employee getEmployee(String initials) {
        return employees.stream().filter(e -> e.getInitials().equals(initials)).findFirst().orElse(null);
    }
    
    public Project getProject(String projectName) {
        return projects.stream().filter(e -> e.getProjectName().equals(projectName)).findFirst().orElse(null);
    }

    public List<Project> getAllProjectsForEmployee(Employee e) {
        return projects.stream().filter(p -> p.isEmployeeInProject(e)).toList();
    }
}