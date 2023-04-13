package application.projectmanagement;

import java.util.ArrayList;

public class Projectmanager {
    
    private ArrayList<Employee> employees;

    public Projectmanager() {
        employees = new ArrayList<>();
    }

    public void AddEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee GetEmployee(String initials) {
        return employees.stream().filter(e -> e.GetInitials().equals(initials)).findFirst().get();
    }
}
