package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

import application.timemanagement.TimeServer;

public class ProjectManager {
    
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;

    private TimeServer timeServer;
    private int lastUsedYear;
    private int projectsCreated;

    public ProjectManager() {
        employees = new ArrayList<>();
        projects = new ArrayList<>(); 
    }

    public void setTimeServer(TimeServer timeServer) {
        this.timeServer = timeServer;
        this.projectsCreated = 0;
        this.lastUsedYear = -1;
    }

    public void createProject(String projectName) throws IllegalArgumentException {
        // If the year changed since we last created a project the Project numbering should start over
        int currentYear = timeServer.getYear();
        if (lastUsedYear != currentYear) {
            lastUsedYear = currentYear;
            projectsCreated = 0;
        }
        Project p = new Project((currentYear % 100) * 1000 + projectsCreated + 1, projectName);
        projects.add(p);
        projectsCreated += 1;
    }
   
    // TODO Add GUI method to delete project
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

    public Project getProjectByID(int ID) {
        return projects.stream().filter(p -> p.getID() == ID).findFirst().orElse(null);
    }

    public List<Project> getAllProjectsForEmployee(Employee e) {
        return projects.stream().filter(p -> p.isEmployeeInProject(e)).toList();
    }
}