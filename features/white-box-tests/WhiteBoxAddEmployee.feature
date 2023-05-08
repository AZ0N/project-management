Feature: White Box: addEmployee()

Scenario: addEmployee() Set A 
    When an employee with initials " " is added to the system
    Then the system provides the error message "Initials   not valid. Only 1-4 letters allowed."

Scenario: addEmployee() Set B 
    When an employee with initials "mads" is added to the system
    When an employee with initials "mads" is added to the system
    Then the system provides the error message "Employee with initials mads already exists!"

Scenario: addEmployee() Set C
    When an employee with initials "brix" is added to the system
    Then there is an employee with initials "brix" in the system
