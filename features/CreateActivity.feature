# Melissa Safari - 224818
Feature: Create Activity
    Description: A Project leader or Employee creates an Activity
    Actor: Employee/Project leader

# Main Scenario
Scenario: An Activity is created
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    When the employee "test" creates and activity named "First Activity" on project with ID 23001
    Then the project with ID 23001 has an activity named "First Activity"

# Alternate Scenario 1
Scenario: An Activity is created by Project Leader
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And the employee with initials "test" is appointed project leader of project with ID 23001
    When the employee "test" creates and activity named "First Activity" on project with ID 23001
    Then the project with ID 23001 has an activity named "First Activity"

# Alternate Scenario 2
Scenario: An employee that isn't project leader tries to create activity
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And an employee with initials "osca" is added to the system
    And the employee with initials "test" is appointed project leader of project with ID 23001
    When the employee "osca" creates and activity named "First Activity" on project with ID 23001
    Then the system provides the error message "Only project leader can create activities!"

# Alternate Scenario 3
Scenario: Adding activity with existing named
    Given the year is 2023
    And an employee with initials "test" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" creates and activity named "First Activity" on project with ID 23001
    Then the system provides the error message "Project activity with name already exists!"