# Mads Christian Wrang Nielsen - s224784
Feature: White Box: appointProjectLeader()

Scenario: appointProjectLeader() Set A 
    Given the year is 2023
    And an employee with initials "mads" is added to the system
    And an employee with initials "brix" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee with initials "mads" is appointed project leader of project with ID 23001
    When the employee with initials "brix" is appointed project leader of project with ID 23001
    Then the system provides the error message "This projects already has a project leader!"

Scenario: appointProjectLeader() Set B
    Given the year is 2023
    And an employee with initials "osca" is added to the system
    And a Project called "Project 1" is added to the system
    When the employee with initials "osca" is appointed project leader of project with ID 23001
    Then the project leader of the project with ID 23001 is "osca"