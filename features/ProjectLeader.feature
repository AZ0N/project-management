Feature: Appoint Project leader
    Description: An Employee appoints a Project leader of a Project
    Actor: Employee

Scenario: An Employee is appointed Project leader.
    Given   there is a Project named "Project 1".
    And     the Project "Project 1" has no Project leader
    When    the user provides the initials "test" of the person who wants to become Project leader.
    Then    the Project leader of "Project 1" is the Employee with the initials "test"

#Scenario: An Employee is appoints Project leader to Project
#          which already has Project leader.
#    Given   there is a Project named "Project 1".
#    And     that "Project 1" has a Project leader.
#    When    the user provides the initials "test"
#            of the person who wants to become Project leader.
#    Then    the system provides the error message
#            "Project already has Project leader"