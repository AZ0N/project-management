Feature: White Box: deleteProject()

Scenario: deleteProject() Set A
	Given there isn't a Project with ID 23001
	When the Project with ID 23001 is deleted
	Then the system provides the error message "The Project doesn't exist!"

Scenario: deleteProject() Set B
	Given the year is 2023
	And a Project called "Project 1" is added to the system
	When the Project with ID 23001 is deleted
	Then the project with ID 23001 and name "Project 1" is not in the system