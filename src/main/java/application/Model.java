package application;

import java.util.List;

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

    // Try to add employee with initials to the system.
    // Shows error if the employee with initials already exists.
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

    // Try to add project with provided name.
    // Shows error if the project name isn't valid (1-30 characters).
    public void addProject(String projectName) {
        try {
            projectmanager.createProject(projectName);
        } catch (Exception e) {
            view.showError(e.getMessage());
            return;
        }
        view.updateProjectList(projectmanager.getProjects());
    }
    
    // Try to delete the project with provided project ID.
    // Shows error if there isn't a project with the provided ID.
    public void deleteProject(int projectID) {
    	projectmanager.getProjectByID(projectID);
    	try {
    		projectmanager.deleteProject(projectID);
    	} catch (Exception e) {
    		view.showError(e.getMessage());
    		return;
    	}
    	view.updateProjectList(projectmanager.getProjects());
    }

    // Try to create a project activity on the selected project with provided name.
    // Shows error if no project is selected or there already exists an activity with the provided name.
    public void createProjectActivity(String activityName) {
        if (selectedProject == null) {
            view.showError("No project selected!");
            return;
        }
        try {
            selectedProject.addActivity(new ProjectActivity(activityName), loggedInEmployee);
            view.updateProjectDetails(selectedProject);
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    // Try to assign an employee with provided initials to the selected project activity.
    // Shows error if no project activity is selected or the provided initials doesn't match an employee.
    public void assignEmployeeToActivity(String employeeInitials) {
    	if (selectedActivity == null) {
            view.showError("No activity selected!");
    		return; 
    	}
        Employee employee = projectmanager.getEmployee(employeeInitials);
        if (employee == null) {
            view.showError("Employee with initals " + employeeInitials + " doesn't exist!");
            return;
        }
        if (selectedActivity.getAssignedEmployees().contains(employee)) {
            view.showError(employeeInitials + " is already assigned to this activity!");
            return;
        }
        try {
            selectedActivity.assignEmployee(employee, loggedInEmployee);
            view.updateSelectedActivityEmployeeListView(getSelectedActivityEmployees());
            view.updateProjectDetails(selectedProject);
        }
        catch (Exception e) {
            view.showError(e.getMessage());
        }
	}

    // The logged in user sets the estimated time on the selected activity.
    // Shows error if no activity is selected, the input isn't a valid number or the logged in user can't set the estimated time.
    public void setEstimatedTime(String estimatedTime) {
    	if (selectedActivity == null) {
            view.showError("No activity selected!");
    		return; 
    	}
        int time;
        try {
            time = Integer.parseInt(estimatedTime);
            selectedActivity.setEstimatedTime(time, loggedInEmployee);
        }
        catch (NumberFormatException e) {
            view.showError("Please enter a valid number!");
            return;
        }
        catch (Exception e) {
            view.showError(e.getMessage());
        }
        view.updateProjectDetails(selectedProject);
    }

    // The logged in user adds time used to the selected activity.
    // Shows error if no activity is selected, the provided input isn't a number or the logged in employee isn't assigned to the project.
    public void addTimeUsed(String timeUsed) {
    	if (selectedActivity == null) {
            view.showError("No activity selected!");
    		return; 
    	}
        int time;
        try {
            time = Integer.parseInt(timeUsed);
            selectedActivity.addTimeUsed(loggedInEmployee, time);
        }
        catch (NumberFormatException e) {
            view.showError("Please enter a valid number!");
            return;
        }
        catch (Exception e) {
            view.showError(e.getMessage());
        }
        view.updateProjectDetails(selectedProject);
    }

    // Appoint employee with provided initials to project leader on the selected project.
    // Shows error if not project is selected, the employee with initials doesn't exists, or the project already has a project leader.
    public void appointProjectLeader(String employeeInitials) {
        if (selectedProject == null) {
            view.showError("No project selected!");
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

    // Try to login using provided initials. Shows error if there isn't an employee with provided initials.
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

    public List<ProjectActivity> getSelectedProjectActivities() {
        return selectedProject.getProjectActivities();
    }
    
    public List<Employee> getSelectedActivityEmployees(){
    	return selectedActivity.getAssignedEmployees();
    }

    public List<ProjectActivity> getAllActivitiesForEmployee(Employee e) {
        return projectmanager.getAllActivitiesForEmployee(e);
    }
    
    public List<Project> getAllProjectsForEmployee(Employee e) {
        return projectmanager.getAllProjectsForEmployee(e);
    }

    public List<Employee> searchEmployees(String searchText) {
        return projectmanager.searchEmployees(searchText);
    }

    public List<Project> searchProjects(String searchText) {
        return projectmanager.searchProjects(searchText);
    }
}