Feature: Appoint Project leader
    Description: An Employee appoints a Project leader of a Project
    Actor: Employee

# Main Scenario
Scenario: An Employee is appointed Project Leader.
    Given the year is 2023
    And an employee with initials "jeff" is added to the system
    And a Project called "Project 1" is added to the system
    When the employee with initials "jeff" is appointed project leader of project with ID 23001
    Then the project leader of the project with ID 23001 is "jeff"

# Alternate Scenario 1
Scenario: An Employee is appointed Project Leader on a project which already has a Project Leader
    Given the year is 2023
    And an employee with initials "jeff" is added to the system
    And an employee with initials "bob" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee with initials "jeff" is appointed project leader of project with ID 23001
    When the employee with initials "bob" is appointed project leader of project with ID 23001
    Then the system provides the error message "This projects already has a project leader!"