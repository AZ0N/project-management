# Christian Brix Saksager - s224777
Feature: Delete Project
	Description: Delete an existing Project
	Actor: Employee

# Main Scenario
Scenario: Delete Project
	Given the year is 2023
	And a Project called "Project 1" is added to the system
	When the Project with ID 23001 is deleted
	Then the project with ID 23001 and name "Project 1" is not in the system
	
# Alternate Scenario
Scenario: Project doesn't exist
	Given there isn't a Project with ID 23001
	When the Project with ID 23001 is deleted
	Then the system provides the error message "The Project doesn't exist!"