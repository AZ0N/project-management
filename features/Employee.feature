Feature: Employee

Scenario: Add Employee
    Given there is an employee with initials "osca"
    When the employee is added to the system
    Then there is an employee with initials "osca" in the system