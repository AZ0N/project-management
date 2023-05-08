# Mads Christian Wrang Nielsen - s224784
Feature: Searching for employees in the system 

# Main Scenario
Scenario: Searching for an employee that is in the system
    Given an employee with initials "test" is added to the system
    When an employee searches for employees with search text "tes"
    Then the employee with initials "test" is in the search result

# Alternate Scenario 1
Scenario: Searching for an employee that is not in the system
    Given an employee with initials "test" is added to the system
    When an employee searches for employees with search text "cas"
    Then the employee with initials "test" is not in the search result

# Alternate Scenario 2
Scenario: Searching for multiple employees in the system
    Given an employee with initials "test" is added to the system
    Given an employee with initials "bes" is added to the system
    When an employee searches for employees with search text "es"
    Then the employee with initials "test" is in the search result
    Then the employee with initials "bes" is in the search result