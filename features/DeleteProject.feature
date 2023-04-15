
Feature: Delete Project
Description: Delete an existing Project
Actor: Employee

# Main Scenario
Scenario: Delete Project
	Given there is a Project named "Project 1"
	When the Project called "Project 1" is deleted
	Then the Project "Project 1" is not in the system
	
# Alternate Scenario
Scenario: Project doesn't exist
	Given there isn't a Project named "Project 1"
	When the Project called "Project 1" is deleted
	Then the system provides the error message "The Project doesn't exist"