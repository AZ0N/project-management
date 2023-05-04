package application;

import java.util.List;
import java.util.stream.Collectors;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
import application.timemanagement.SystemTimeServer;

public class Model {
    
    private View view;
    private ProjectManager projectmanager;

    private Employee loggedInEmployee;
    private Project selectedProject;
	private ProjectActivity selectedActivity;

    public Model(View view) {
        this.view = view;
        projectmanager = new ProjectManager();
        projectmanager.setTimeServer(new SystemTimeServer());
    }

    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    public void clearLoggedInEmployee() {
        loggedInEmployee = null;
    }

    public void addEmployee(String initials) {
        try {
            projectmanager.addEmployee(initials);
        }
        catch (Exception e) {
            view.showError(e.getMessage());
            return;
        }
        view.updateEmployeeList(projectmanager.getEmployees());
    }

    public void addProject(String projectName) {
        try {
            projectmanager.createProject(projectName);
        } catch (Exception e) {
            view.showError(e.getMessage());
            return;
        }
        view.updateProjectList(projectmanager.getProjects());
    }

    public void createProjectActivity(String activityName) {
        if (selectedProject == null) {
            return; //TODO throw error
        }
        // TODO Check if activity already exists
        selectedProject.addActivity(new ProjectActivity(activityName));
        view.updateProjectDetails(selectedProject);
    }

    public void assignEmployeeToActivity(String employeeInitials) {
    	if (selectedActivity == null) {
            // TODO Handle
    		return; 
    	}
        Employee e = projectmanager.getEmployee(employeeInitials);
        if (e == null) {
            view.showError("Employee with initals " + employeeInitials + " doesn't exist!");
            return;
        }
        if (selectedActivity.getAssignedEmployees().contains(e)) {
            view.showError(employeeInitials + " is already assigned to this activity!");
            return;
        }
    	selectedActivity.addEmployee(e);
    	view.updateSelectedActivityEmployeeListView(getSelectedActivityEmployees());
	}

    public void appointProjectLeader(String employeeInitials) {
        if (selectedProject == null) {
            // TODO Handle
            return;
        }
        Employee e = projectmanager.getEmployee(employeeInitials);
        if (e == null) {
            view.showError("Employee with initals " + employeeInitials + " doesn't exist!");
            return;
        }
        try {
            selectedProject.appointProjectLeader(e);
        }
        catch (Exception ex) {
            view.showError(ex.getMessage());
        }
        view.updateProjectDetails(selectedProject);
    }
    
    public List<ProjectActivity> getSelectedProjectActivities() {
        return selectedProject.getProjectActivities();
    }
    
    public List<Employee> getSelectedActivityEmployees(){
    	return selectedActivity.getAssignedEmployees();
    }

    public void login(String initials) {
        Employee e = projectmanager.getEmployee(initials);
        if (e == null) {
            view.showError("No employee with initials: " + initials);
            return;
        }
        loggedInEmployee = e;
        // Update view
        view.toMainScreen();
        view.updateOverview(projectmanager.getAllProjectsForEmployee(e), projectmanager.getAllActivitiesForEmployee(e));
    }

    public void selectedProject(Project p) {
        selectedProject = p;
    }
    
   public void selectedActivity(ProjectActivity a) {
		selectedActivity = a;
	}

    public Project getSelectedProject() {
        return selectedProject;
    }

   public ProjectActivity getSelectedProjectActivity() {
	   return selectedActivity;
   }

    public List<Employee> searchEmployees(String searchText) {
        return projectmanager.getEmployees().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }

    public List<Project> searchProjects(String searchText) {
        return projectmanager.getProjects().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }
    
    public List<ProjectActivity> getAllActivitiesForEmployee(Employee e) {
    	return projectmanager.getAllActivitiesForEmployee(e);
    }
    
    public List<Project> getAllProjectsForEmployee(Employee e) {
    	return projectmanager.getAllProjectsForEmployee(e);
    }

}
