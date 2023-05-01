Feature: Add Employee

# Main Scenario
Scenario: Add Employee
    When an employee with initials "osca" is added to the system
    Then there is an employee with initials "osca" in the system

# Alternate Scenario
Scenario: Add Employee when one with same initials already exists
    When an employee with initials "osca" is added to the system
    When an employee with initials "osca" is added to the system
    Then the system provides the error message "Employee with initials osca already exists!"

