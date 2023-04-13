package application.projectmanagement;

import java.util.ArrayList;
import java.util.List;

public class Projectmanager {
    
    private ArrayList<Employee> employees;

    public Projectmanager() {
        employees = new ArrayList<>();
    }

    public void AddEmployee(Employee employee) throws Exception {
        if (GetEmployee(employee.getInitials()) != null) {
            throw new Exception("Employee with initials already exists!");
        }
        employees.add(employee);
    }

    public Employee GetEmployee(String initials) {
        return employees.stream().filter(e -> e.getInitials().equals(initials)).findFirst().orElse(null);
    }

    public List<Employee> GetEmployees() {
        return employees;
    }
}