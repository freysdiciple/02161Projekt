Feature: Create new project
   Description: The user creates a new project 
   Actors: Admin
   
   
#Main scenario   
#Scenario: Create new project
#	Given there is an user with ID "Søren"
#	And the user is an admin
# 	When the user creates a project with a number 12345
#  Then the project with a number 12345 is contained in the system

        
# Alternate scenario one     
#Scenario: A project with the given number already exists
#	Given there is an user with ID "Søren"
#	And the user is an admin
#	When the user creates a project with a number 12345 identical to an existing project
#   Then the system throws ExistingProjectException    
 
#Alternate scenario two
#Scenario: User is not an admin
#	Given there is an user with ID "Søren"
#	And the user is not an admin
#   When the user tries to create a project with a number 12345
#   Then the system throws NotAuthorizedException
 