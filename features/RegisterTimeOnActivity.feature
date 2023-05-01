Feature: Register hours spent on Activity
Description: An Employee can register his time spent (hours) on an Activity
Actor: Employee

# Main Scenario

Scenario: an Employee registers his hours spent on an Activity.
# TODO Fix this missing step definition
Given there is a Project named "Project 1"
And that "Project 1" has an Activity named "Activity 1".
When the Employee with the initials "TEST" provides the Activity named "Activity 1".
And the Employee with the initials "TEST" provides the total hours of work 2 on the Activity.
Then the system will register the Employees’ hours spent on "Activity 1"