Feature: Assign developer to project
   Description: The user assign a developer to a given project 
   Actors: Admin
   
# Main scenario   
#Scenario: Add developer to project
#	Given the user is an admin
#	And there is a project
#	When the user adds the developer to the project
#  	Then the developer is added to the list of developers on the project

# Alternate scenario one 
#Scenario: The developer doesn't exist
#	Given the user is an admin
#	When the user adds a non-existing developer to the project
#  	Then the system provides an error message that the developer doesn't exist

# Alternate scenario two 
#Scenario: The project doesn't exist
#	Given the user is an admin
#  	And the project doesn’t exist
#	When the user adds the developer to the project
#   Then the system provides an error message that the project doesn't exist

# Alternate scenario three
#Scenario: The user is not an admin
#	Given the user is a not admin
#	When the user adds the developer to the project
#  	Then the system throws InvalidUserException
