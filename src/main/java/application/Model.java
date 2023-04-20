package application;

import java.util.List;
import java.util.stream.Collectors;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectManager;

public class Model {
    
    private View view;
    private ProjectManager projectmanager;

    private Employee selectedEmployee;

    public Model(View view) {
        this.view = view;
        projectmanager = new ProjectManager();
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

    public List<Employee> searchEmployees(String searchText) {
        return projectmanager.getEmployees().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }

    public List<Project> searchProjects(String searchText) {
        return projectmanager.getProjects().stream().filter(e -> e.match(searchText)).collect(Collectors.toList());
    }
}
