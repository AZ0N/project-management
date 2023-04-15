Feature: Appoint Project leader
    Description: An Employee appoints a Project leader of a Project
    Actor: Employee

# Main Scenario
Scenario: An Employee is appointed Project leader.
    Given there is a Project named "Project 1".
    And the Project "Project 1" has no Project leader
    When the user provides the initials "test" of the person who wants to become Project leader.
    Then the Project leader of "Project 1" is the Employee with the initials "test"

# Alternate Scenario
Scenario: An Employee appoints a Project leader to a Project which already has a Project leader.
    Given there is a Project named "Project 1".
    And that "Project 1" has a Project leader with initials "test".
    When the user provides the initials "test" of the person who wants to become Project leader.
    Then the system provides the error message "This project already has a project leader"
    
    