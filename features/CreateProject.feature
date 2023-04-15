Feature: Create Projekt
  Description: Create a new Project
	Actor: EmployeeI want to use this template for my feature file

#Main Scenario
Scenario: Create new Project
		Given there is a new Project named "Project 1"
		When the Project called "Project 1" is added to the system
		Then the Project "Project 1" is in the system	

#Alternate Scenario
Scenario: Create existing Project
		Given there is a new Project named "Project 1"
		And the Project called "Project 1" is added to the system
		When the Project called "Project 1" is added to the system
    Then an error message "The project already exists" is shown 
