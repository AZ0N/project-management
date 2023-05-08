Feature: White Box: addActivity() 

Scenario: addActivity() Set A 
    Given the year is 2023
    And an employee with initials "mads" is added to the system
    And a Project called "First Project" is added to the system
    And the employee "mads" creates and activity named "Activity" on project with ID 23001
    When the employee "mads" creates and activity named "Activity" on project with ID 23001
    Then the system provides the error message "Project activity with name already exists!"

Scenario: addActivity() Set B 
    Given the year is 2023
    And a Project called "First Project" is added to the system
    And an employee with initials "mads" is added to the system
    And an employee with initials "brix" is added to the system
    And the employee with initials "mads" is appointed project leader of project with ID 23001
    When the employee "brix" creates and activity named "First Activity" on project with ID 23001
    Then the system provides the error message "Only project leader can create activities!"

Scenario: addActivity() Set C 
    Given the year is 2023
    And a Project called "First Project" is added to the system
    And an employee with initials "brix" is added to the system
    And the employee with initials "brix" is appointed project leader of project with ID 23001
    When the employee "brix" creates and activity named "First Activity" on project with ID 23001
    Then the project with ID 23001 has an activity named "First Activity"