Feature: Add Employee

Scenario: Add Employee
    Given there is an employee with initials "osca"
    When the employee is added to the system
    Then there is an employee with initials "osca" in the system

Scenario: Add Employee
    Given there is an employee with initials "osca"
    And the employee is added to the system
    When the employee is added to the system
    Then an error message "Employee with initials already exists!" is shown 