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
            return;
        }
        // TODO Check if activity already exists
        selectedProject.addActivity(new ProjectActivity(activityName));
        view.updateSPActivityListView(getSelectedProjectActivities());
    }

    public List<ProjectActivity> getSelectedProjectActivities() {
        return selectedProject.getProjectActivities();
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

    public void selectProject(Project p) {
        selectedProject = p;
    }

    public List<Employee> searchEmployees(String searchText) {
        return projectmanager.getEmployees().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }

    public List<Project> searchProjects(String searchText) {
        return projectmanager.getProjects().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }
    
    public List<ProjectActivity> showEmployeeActivityListView(Employee e){
    	return projectmanager.getAllActivitiesForEmployee(e);
    }
}
