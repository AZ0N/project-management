Feature: Create Project
	Description: Create a new Project
	Actor: Employee

# Main Scenario
Scenario: Create new Project
	Given the year is 2023
	When a Project called "Project 1" is added to the system
	Then the Project with id 23001 and name "Project 1" is in the system	

# Alternate Scenario 1
Scenario: Create two Projects with same names but different ID's
	Given the year is 2023
	When a Project called "Project 1" is added to the system
	And a Project called "Project 1" is added to the system
	Then the Project with id 23001 and name "Project 1" is in the system
	And the Project with id 23002 and name "Project 1" is in the system

# Alternate Scenario 2
Scenario: Create Projects with the year changing
	Given the year is 2023
	When a Project called "Project 1" is added to the system
	And the year is 2024
	And a Project called "Project 2" is added to the system
	Then the Project with id 23001 and name "Project 1" is in the system
	And the Project with id 24001 and name "Project 2" is in the system

# Alternate Scenario 3
Scenario: Create Project with empty name
	When a Project called "" is added to the system
	Then the system provides the error message "Project name  is not valid."

# Alternate Scenario 4
Scenario: Create Project with too long name
	When a Project called "ThisProjectsNameIsOverThirtyCharacters" is added to the system
	Then the system provides the error message "Project name ThisProjectsNameIsOverThirtyCharacters is not valid."