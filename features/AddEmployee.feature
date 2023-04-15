Feature: Add Employee

# Main Scenario
Scenario: Add Employee
    Given there is an employee with initials "osca"
    When the employee is added to the system
    Then there is an employee with initials "osca" in the system

# Alternate Scenario
Scenario: Add Employee when one with same initials already exists
    Given there is an employee with initials "osca"
    And the employee is added to the system
    When the employee is added to the system
    Then an error message "Employee with initials osca already exists!" is shown 