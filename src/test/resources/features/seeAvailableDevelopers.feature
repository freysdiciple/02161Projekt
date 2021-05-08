Feature: See available developers
   Description: The user requests to see a list of available developers
   Actors: Project leader

# Main scenario
	Scenario: See available developers
	Given 9- there is an user with ID "Søren"
	And 9- there is a project with ID "211234"
	And 9- the user is a Project leader
	When 9- the user provides information of the start time {string} and end time {string} of the activity where he needs developers
	Then 9- the system displays a list of available developers at the given time slot



# Alternate scenario one
Scenario: Given time not valid length
	Given 9- there is an user with ID "Søren"
	And 9- there is a project with ID "211234"
	And 9- the user is a Project leader
	When 9- the user provides invalid length input of the start time {string} and end time {string} of the activity where he needs developers
  	Then 9- the system provides an error message that the length of the input time is invalid
  	
# Alternate scenario two
Scenario: Given time not valid format
	Given 9- there is an user with ID "Søren"
	And 9- there is a project with ID "211234"
	And 9- the user is a Project leader
	When 9- the user provides invalid format input of the start time {string} and end time {string} of the activity where he needs developers
  	Then 9- the system provides an error message that the format of the input time is invalid

  	//Not ProjectLeader