# Jonathan Victor Flint - s224770
Feature: Register hours spent on Activity
    Description: An Employee can register his time spent (hours) on an Activity
    Actor: Employee

# Main Scenario
Scenario: An employee registers time on activity he is assigned to
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    And the employee "test" is assigned to the activity "First Activity" on project with ID 23001 by "test"
    When the employee "test" adds 10 hours used on activity "First Activity" on project with ID 23001
    Then the activity "First Activity" on project with ID 23001 has 10 hours used

# Alternate Scenario 1
Scenario: An employee registers time on activity he is not assigned to
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    When the employee "test" adds 10 hours used on activity "First Activity" on project with ID 23001
    Then the system provides the error message "Only employees assigned to activity can add time used!"

# Alternate Scenario 2
Scenario: An employee registers negative time on activity
    Given the year is 2023
    And a Project called "Project 1" is added to the system
    And an employee with initials "test" is added to the system
    And the employee "test" creates and activity named "First Activity" on project with ID 23001
    And the employee "test" is assigned to the activity "First Activity" on project with ID 23001 by "test"
    When the employee "test" adds -100 hours used on activity "First Activity" on project with ID 23001
    Then the system provides the error message "Only positive amount of time can be added!"