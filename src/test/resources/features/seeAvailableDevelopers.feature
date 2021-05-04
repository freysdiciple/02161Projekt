Feature: See available developers
   Description: The user requests to see a list of available developers
   Actors: Project leader

# Main scenario   
	Scenario: See available developers
	Given there is an user with ID "Søren"
	Given there is a project with ID 12345
	Given the user is a Project leader
	When the user provides information of the time slot of the activity where he needs developers
	Then the system displays a list of available developers at the given time slot
	
   	

# Alternate scenario one  
Scenario: Invalid time for available developers
	Given the user is a Project leader
	When the user provides information of the time slot of the activity where he needs developers, which isn't valid
  	Then the system provides an error message that the time is invalid