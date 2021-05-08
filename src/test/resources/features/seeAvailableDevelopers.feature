Feature: See available developers
   Description: The user requests to see a list of available developers
   Actors: Project leader

# Main scenario
	Scenario: See available developers
	Given 9- there is an user with ID "Søren"
	Given 9- there is a project with ID 12345
	Given 9- the user is a Project leader
	When 9- the user provides information of the time slot of the activity where he needs developers
	Then 9- the system displays a list of available developers at the given time slot



# Alternate scenario one
Scenario: Invalid time for available developers
	Given 9- the user is a Project leader
	When 9- the user provides information of the time slot of the activity where he needs developers, which isn't valid
  	Then 9- the system provides an error message that the time is invalid
