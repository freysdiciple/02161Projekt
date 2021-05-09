Feature: Assign developer to project
   Description: The user assign a developer to a given project
   Actors: Admin

# Main scenario
Scenario: Add developer to project
	Given 2- the user "Jan" is an admin
	And 2- there is a project named "210005"
	When 2- the user "Jan" adds the developer "Hans" to the project "210005"
  	Then 2- the developer "Hans" is added to the list of developers on the project "210005"

# Alternate scenario one
#Scenario: The developer doesn't exist
#	Given 2- the user is an admin
#	When 2- the user adds a non-existing developer to the project
#  	Then 2- the system provides an error message that the developer doesn't exist

# Alternate scenario two
#Scenario: The project doesn't exist
#	Given 2- the user is an admin
#  	And 2- the project doesn’t exist
#	When 2- the user adds the developer to the project
#   Then 2- the system provides an error message that the project doesn't exist

# Alternate scenario three
#Scenario: The user is not an admin
#	Given 2- the user is a not admin
#	When 2- the user adds the developer to the project
#  	Then 2- the system throws InvalidUserException
