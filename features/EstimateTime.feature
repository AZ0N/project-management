# Oscar Svenstrup Nielsen - s224812
Feature: Set estimated time on activity 
    Description: An Employee/Project leader sets estimated time on activity 
    Actor: Employee/Project leader

# Main Scenario
Scenario: An employee sets estimated time on activity
    Given the year is 2023
    And an employee with initials "test" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" sets the estimated time for activity "First Activity" on project 23001 to 50
    Then the activity "First Activity" on project with ID 23001 has an estimated time of 50 hours

# Alternate Scenario 1
Scenario: Project leader sets estimated time on activity
    Given the year is 2023
    And an employee with initials "test" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee with initials "test" is appointed project leader of project with ID 23001
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" sets the estimated time for activity "First Activity" on project 23001 to 200
    Then the activity "First Activity" on project with ID 23001 has an estimated time of 200 hours

# Alternate Scenario 2
Scenario: An employee who isn't project leader tries to set estimated time on activity
    Given the year is 2023
    And an employee with initials "test" is added to the system
    And an employee with initials "bob" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee with initials "bob" is appointed project leader of project with ID 23001
    And the employee "bob" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" sets the estimated time for activity "First Activity" on project 23001 to 200
    Then the system provides the error message "Only project leader can set estimated time!"

# Alternate Scenario 3
Scenario: An employee tries to set estimated time on activity to negative amount
    Given the year is 2023
    And an employee with initials "test" is added to the system
    And a Project called "Project 1" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" sets the estimated time for activity "First Activity" on project 23001 to -50
    Then the system provides the error message "Estimated time must be positive!"