package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.Projectmanager;

public class Model {
    
    private View view;
    private int value;

    private Projectmanager projectmanager;

    public Model(View view) {
        this.view = view;
        this.value = 0;

        projectmanager = new Projectmanager();
    }

    public void addEmployee(String initials) {
        try {
            Employee e = new Employee(initials);
            projectmanager.addEmployee(e);
        }
        catch (Exception e) {
            //TODO Show error message when adding employee failed
           return;
        }
        view.updateEmployeeList(projectmanager.getEmployees());
    }

    public void addProject(String projectName) {
        try {
            Project p = new Project(projectName);
            projectmanager.addProject(p);
        } catch (Exception e) {
            //TODO Show error message when adding employee failed
           return;
        }
        view.updateProjectList(projectmanager.getProjects());
    }
}
