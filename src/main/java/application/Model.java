package application;

import application.projectmanagement.Employee;
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
            projectmanager.AddEmployee(e);
        }
        catch (Exception e) {
            //TODO Show error message when adding employee failed
           return;
        }

        view.UpdateEmployeeList(projectmanager.GetEmployees());
    }
}
