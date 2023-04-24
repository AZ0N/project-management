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

    private Employee selectedEmployee;
    private Project selectedProject;
	private ProjectActivity selectedActivity;

    public Model(View view) {
        this.view = view;
        projectmanager = new ProjectManager();
        projectmanager.setTimeServer(new SystemTimeServer());
    }

    public Employee getCurrentEmployee() {
        return selectedEmployee;
    }

    public void clearSelectedEmployee() {
        selectedEmployee = null;
    }

    public void addEmployee(String initials) {
        try {
            Employee e = new Employee(initials);
            projectmanager.addEmployee(e);
        }
        catch (Exception e) {
            view.showError(e.getMessage());
            return;
        }
        view.updateEmployeeList(projectmanager.getEmployees());
    }

    public void addProject(String projectName) {
        try {
            Project p = new Project(projectName);
            projectmanager.addProject(p);
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
        view.updateSPActivityListView(getSelectedProjectActivities());
    }

    public void assignEmployeeToActivity(String employeeInitials) {
    	if (selectedActivity == null) {
    		return; //TODO throw error
    	}
    	selectedActivity.addEmployee(new Employee(employeeInitials));
    	view.updateSAEmployeeListView(getSelectedActivityEmployees());
    	// TODO list of employees on activity
		
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
        selectedEmployee = e;
        //Update view
        view.toMainScreen();
    }

    public void selectedProject(Project p) {
        selectedProject = p;
    }
    
   public void selectedActivity(ProjectActivity a) {
		selectedActivity = a;
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
    
    public List<ProjectActivity> showEmployeeActivityListView(Employee e) {
    	return projectmanager.getAllActivitiesForEmployee(e);
    }
    
    public List<Project> showEmployeeProjectListView(Employee e) {
    	return projectmanager.getAllProjectsForEmployee(e);
    }

}
