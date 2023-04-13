Feature: Create Projekt
  Description: Create a new Project
	Actor: EmployeeI want to use this template for my feature file

#Main Scenario
Scenario: Create new Project
	Given there is a new Project named "Project 1"
	And "Project 1" is not already in the system
	When the Project called "Project 1" is added to the system
	Then the Project "Project 1" is in the system	

#Alternate Scenario
#Scenario: Create existing Project
#	Given there is a Project named "Project 1" in the system
#	When the Project called "Project 1" is added to the system
#	Then the system provides the error message "The Project already exists"
