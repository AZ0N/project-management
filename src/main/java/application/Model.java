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

    public void addTimeUsed(String timeUsed) {
    	if (selectedActivity == null) {
            view.showError("No activity selected!");
    		return; 
    	}
        int time;
        try {
            time = Integer.parseInt(timeUsed);
            selectedActivity.addTimeUsedByEmployee(loggedInEmployee, time);
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
        return projectmanager.searchEmployees(searchText);
    }

    public List<Project> searchProjects(String searchText) {
        return projectmanager.searchProjects(searchText);
    }
    
    public List<ProjectActivity> getAllActivitiesForEmployee(Employee e) {
    	return projectmanager.getAllActivitiesForEmployee(e);
    }
    
    public List<Project> getAllProjectsForEmployee(Employee e) {
    	return projectmanager.getAllProjectsForEmployee(e);
    }
}