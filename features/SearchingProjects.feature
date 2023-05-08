# Mads Christian Wrang Nielsen - s224784
Feature: Searching for projects in the system 

# Main Scenario
Scenario: Searching for a project that is in the system by name
    Given the year is 2023
    And a Project called "First Project" is added to the system
    When an employee searches for projects with search text "Project"
    Then the project with ID 23001 is in the search result

# Alternate Scenario 1
Scenario: Searching for a project that is in the system by ID
    Given the year is 2023
    And a Project called "First Project" is added to the system
    When an employee searches for projects with search text "230"
    Then the project with ID 23001 is in the search result

# Alternate Scenario 2
Scenario: Searching for multiple projects with multiple results
    Given the year is 2023
    And a Project called "First Project" is added to the system
    And a Project called "Second Project" is added to the system
    When an employee searches for projects with search text "23"
    Then the project with ID 23001 is in the search result
    And the project with ID 23002 is in the search result

# Alternate Scenario 2
Scenario: Searching for multiple projects with single result
    Given the year is 2023
    And a Project called "First Project" is added to the system
    And the year is 2024
    And a Project called "Second Project" is added to the system
    When an employee searches for projects with search text "23"
    Then the project with ID 23001 is in the search result
    And the project with ID 24001 is not in the search result