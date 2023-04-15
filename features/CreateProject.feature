Feature: Create Project
	Description: Create a new Project
	Actor: Employee

# Main Scenario
Scenario: Create new Project
	Given there is a Project named "Project 1"
	When the Project called "Project 1" is added to the system
	Then the Project "Project 1" is in the system	

# Alternate Scenario
Scenario: Create existing Project
	Given there is a Project named "Project 1"
	And the Project called "Project 1" is added to the system
	When the Project called "Project 1" is added to the system
    Then an error message "Project with name Project 1 doesn't exist" is shown 
