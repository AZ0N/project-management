# Mads Christian Wrang Nielsen - s224784
Feature: White Box: createProject()

Scenario: createProject() Set A
	Given the year is 2023
	When a Project called "" is added to the system
	Then the system provides the error message "Project name  is not valid."	

Scenario: createProject() Set B
	Given the year is 2023
	And a Project called "First Project" is added to the system
    And the year is 2024
    And a Project called "Second Project" is added to the system
	Then the Project with id 24001 and name "Second Project" is in the system

Scenario: createProject() Set C
	Given the year is 2023
	When a Project called "Project A" is added to the system
	Then the Project with id 23001 and name "Project A" is in the system