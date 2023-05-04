Feature: Assign Employee to Activity
    Actor: Employee

# Main Scenario
Scenario: An employee is assigned to an activity
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" is assigned to the activity "First Activity" on project with ID 23001 by "test"
    Then the activity "First Activity" on project with ID 23001 has "test" assigned

# Alternate Scenario 1
Scenario: Project Leader assigns an employee an activity
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And an employee with initials "osca" is added to the system
    And the employee with initials "test" is appointed project leader of project with ID 23001
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" is assigned to the activity "First Activity" on project with ID 23001 by "osca"
    Then the system provides the error message "Only project leader can assign employees!"