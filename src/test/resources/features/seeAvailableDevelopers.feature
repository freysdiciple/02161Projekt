Feature: See available developers
   Description: The user requests to see a list of available developers
   Actors: Project leader

# Main scenario
	Scenario: See available developers
	Given 9- there is an user with ID "SØR1"
	And 9- there is a project with name "211234"
	And 9- the user is a Project leader
	And 9- there are other developers
	When 9- the user provides information of the start week 32 and end week 40 of the activity where he needs developers
	Then 9- the system displays a list of available developers at the given time slot



# Alternate scenario one
Scenario: Given time not valid length
	Given 9- there is an user with ID "MOG1"
	And 9- there is a project with name "214321"
	And 9- the user is a Project leader
	When 9- the user provides invalid input of the start week -32 and end week 400 of the activity where he needs developer
  	Then 9- the system provides an error message that the start week and/or end week is invalid
  	
