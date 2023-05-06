package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Creates a new project.
     * @param projectName The name of the project
     * @throws IllegalArgumentException if the project name is blank, less than 1 character or more than 30 characters
     */
    public void createProject(String projectName) throws IllegalArgumentException {
        // Defensive programming
        if (projectName.length() == 0 || projectName.length() > 30 || projectName.isBlank() || projectName == null) {
			throw new IllegalArgumentException("Project name " + projectName + " is not valid.");
		}
        // Pre-condition
        assert projectName != null && !projectName.isBlank() && projectName.length() > 0 && projectName.length() <= 30;
        int previousProjectsCreated = projectsCreated;
        int numberOfProjects = projects.size();

        // If the year changed since we last created a project,
        // the project numbering should start over
        int currentYear = timeServer.getYear();
        if (lastUsedYear != currentYear) {
            lastUsedYear = currentYear;
            projectsCreated = 0;
        }
        // Create a new project with the correct ID and add it to the list of projects
        Project p = new Project((currentYear % 100) * 1000 + projectsCreated + 1, projectName);
        projects.add(p);
        projectsCreated += 1;
        // Post-condition
        assert lastUsedYear == currentYear || projectsCreated == previousProjectsCreated + 1;
        assert projects.size() == numberOfProjects + 1  && projects.stream().anyMatch(pr -> pr.getProjectName().equals(projectName));
    }
   
    /**
     * Deletes the project with projectID.
     * @param projectID The ID of the project to delete.
     * @throws Exception If the project with ID projectID doesn't exist.
     */
    public void deleteProject(int projectID) throws Exception {
        // Defensive programming
        var projectToDelete = getProjectByID(projectID);
    	if (projectToDelete == null) {
            throw new Exception ("The Project doesn't exist!");
    	}
        // Pre-condition
        assert getProjectByID(projectID) != null;
        projects.remove(projectToDelete);
        // Post-condition
        assert projectToDelete == null || !projects.contains(projectToDelete);
    }
    
    /**
     * Add new employee to the system.
     * @param initials Initials of the employee.
     * @throws IllegalArgumentException If the provided initials are not 1-4 letters.
     * @throws Exception If there already exists an employee with the provided initials.
     */
    public void addEmployee(String initials) throws IllegalArgumentException, Exception {
        // Defensive programming
		if (initials.length() == 0 || initials.length() > 4 || !initials.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Initials " + initials + " not valid. Only 1-4 letters allowed.");
		}
        if (getEmployee(initials) != null) {
            throw new Exception("Employee with initials " + initials + " already exists!");
        }
        // Pre-condition
        assert initials != null && !initials.isBlank();
        assert initials.length() > 0 && initials.length() <= 4;
        assert initials.chars().allMatch(Character::isLetter);
        assert !employees.stream().anyMatch(e -> e.getInitials().equals(initials));
        // Create new employee and add it to the system
        Employee e = new Employee(initials);
        employees.add(e);
        // Post-condition
        assert getEmployee(initials) != null;
    }
    
    public List<Project> getProjects() {
        return projects;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
   
    /**
     * Returns all activities employee is assigned to, for all projects.
     * @param employee Employee to search for activities assigned to.
     * @return List of ProjectActivity.
     */
    public List<ProjectActivity> getAllActivitiesForEmployee(Employee employee) {
        // Flatmap projects to the activities the employee is assigned to and collect them to a list
        return projects.stream().flatMap(p -> p.getActivitiesForEmployee(employee).stream()).collect(Collectors.toList());
    }
    
    /**
     * Returns the employee with provided initials if it's in the system.
     * @param initials The initials of the desired employee.
     * @return The employee with provided initials. If the employee isn't in the system return null.
     */
    public Employee getEmployee(String initials) {
        return employees.stream().filter(e -> e.getInitials().equals(initials)).findFirst().orElse(null);
    }

    /**
     * Returns the project with the provided ID.
     * @param ID The ID of the desired project.
     * @return The project with matching ID (exact match) if it's in the system. Otherwise null.
     */
    public Project getProjectByID(int ID) {
        return projects.stream().filter(p -> p.getID() == ID).findFirst().orElse(null);
    }

    /**
     * Returns all projects for an employee.
     * @param employee Employee to search for projects for.
     * @return List of all projects where the employee is project leader or assigned to an activity.
     */
    public List<Project> getAllProjectsForEmployee(Employee employee) {
        return projects.stream().filter(p -> p.isEmployeeInProject(employee)).toList();
    }

    /**
     * Searches for employees given a search string.
     * @param searchText The text to search for.
     * @return List of employees {@link Employee#match(String) matching} the searcText.
     */
    public List<Employee> searchEmployees(String searchText) {
        return employees.stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }

    /**
     * Searches for projects given a search string.
     * @param searchText
     * @return List of projects {@link Project#match(String) matching} the searchText.
     */
    public List<Project> searchProjects(String searchText) {
        return projects.stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }
}