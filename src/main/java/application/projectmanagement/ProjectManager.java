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
        if (projectName.length() == 0 || projectName.length() > 30 || projectName.isBlank() || projectName == null) {
			throw new IllegalArgumentException("Project name " + projectName + " is not valid.");
		}
        assert projectName != null && !projectName.isBlank() && projectName.length() > 0 && projectName.length() <= 30;
        int previousProjectsCreated = projectsCreated;
        int numberOfProjects = projects.size();

        // If the year changed since we last created a project the Project numbering should start over
        int currentYear = timeServer.getYear();
        if (lastUsedYear != currentYear) {
            lastUsedYear = currentYear;
            projectsCreated = 0;
        }
        Project p = new Project((currentYear % 100) * 1000 + projectsCreated + 1, projectName);
        projects.add(p);
        projectsCreated += 1;

        assert lastUsedYear == currentYear || projectsCreated == previousProjectsCreated + 1;
        assert projects.size() == numberOfProjects + 1  && projects.stream().anyMatch(pr -> pr.getProjectName().equals(projectName));
    }
   
    public void deleteProject(int projectID) throws Exception {
        // TODO assert 
    	var projectToDelete = getProjectByID(projectID);
    	if (projectToDelete != null) {
    		projects.remove(projectToDelete);
    	} else {
    		throw new Exception ("The Project doesn't exist!");
    	}
        assert projectToDelete == null || !projects.contains(projectToDelete);
    }
    
    public void addEmployee(String initials) throws IllegalArgumentException, Exception {
		if (initials.length() == 0 || initials.length() > 4 || !initials.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Initials " + initials + " not valid. Only 1-4 letters allowed.");
		}
        if (getEmployee(initials) != null) {
            throw new Exception("Employee with initials " + initials + " already exists!");
        }
        assert initials != null && !initials.isBlank();
        assert initials.length() > 0 && initials.length() <= 4;
        assert initials.chars().allMatch(Character::isLetter);
        assert !employees.stream().anyMatch(e -> e.getInitials().equals(initials));

        Employee e = new Employee(initials);
        employees.add(e);

        assert getEmployee(initials) != null;
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