Feature: Create Project
	Description: Create a new Project
	Actor: Employee

# Main Scenario
Scenario: Create new Project
	When a Project called "Project 1" is added to the system
	Then the Project called "Project 1" is in the system	

# Alternate Scenario 1
Scenario: Create existing Project
	When a Project called "Project 1" is added to the system
	And a Project called "Project 1" is added to the system
	Then the system provides the error message "Project with name Project 1 already exists!"

# Alternate Scenario 2
Scenario: Create Project with empty name
	When a Project called "" is added to the system
	Then the system provides the error message "Project name  is not valid."

# Alternate Scenario 3
Scenario: Create Project with too long name
	When a Project called "ThisProjectsNameIsOverThirtyCharacters" is added to the system
	Then the system provides the error message "Project name ThisProjectsNameIsOverThirtyCharacters is not valid."