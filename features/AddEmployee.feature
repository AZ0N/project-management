Feature: Add Employee

# Main Scenario
Scenario: Add Employee
    When an employee with initials "osca" is added to the system
    Then there is an employee with initials "osca" in the system

# Alternate Scenario 1
Scenario: Add Employee when one with same initials already exists
    When an employee with initials "osca" is added to the system
    When an employee with initials "osca" is added to the system
    Then the system provides the error message "Employee with initials osca already exists!"

# Alternate Scenario 2
Scenario: Add Employee with too many initials
    When an employee with initials "TooMany" is added to the system
    Then the system provides the error message "Initials TooMany not valid. Only 1-4 letters allowed."

# Alternate Scenario 3
Scenario: Add Employee with no initials
    When an employee with initials "" is added to the system
    Then the system provides the error message "Initials  not valid. Only 1-4 letters allowed."

# Alternate Scenario 4
Scenario: Add Employee with initials containing special characters
    When an employee with initials "Haj!" is added to the system
    Then the system provides the error message "Initials Haj! not valid. Only 1-4 letters allowed."