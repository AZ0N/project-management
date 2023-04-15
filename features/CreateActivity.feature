Feature: Create Activity
    Description: A Project leader or Employee creates an Activity
    Actor: Employee/Project leader

# Main Scenario
Scenario: A Project leader creates an Activity.
    Given there is a Project named "Project 1" with Project leader "LBM"
    And "Project 1" has no Activity named "Activity 1"
    When the Project leader "LBM" creates the Activity named "Activity 1"
    Then "Project 1" has an Activity named "Activity 1"

