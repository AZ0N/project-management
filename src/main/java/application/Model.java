package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectManager;

public class Model {
    
    private View view;
    private int value;

    private ProjectManager projectmanager;

    public Model(View view) {
        this.view = view;
        this.value = 0;

        projectmanager = new ProjectManager();
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
}
