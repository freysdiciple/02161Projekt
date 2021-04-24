Feature: Create new project
   Description: The user creates a new project 
   Actors: Admin
   
   
# Main scenario   
Scenario: Create new project
	Given the user is an admin
   	When the user creates a project with name and number
   	Then the system creates the project with the given name and number

# Alternate scenario one     
Scenario: A project with the given name already exists
	Given the user is an admin
When the user creates a project with name identical to an existing project
   	Then the system throws ExistingProjectException
        
# Alternate scenario two     
Scenario: A project with the given number already exists
	Given the user is an admin
When the user creates a project with number identical to an existing project
   	Then the system throws ExistingProjectException    
 
# Alternate scenario five
Scenario: User is not and admin
	Given the user is not an admin
   	When the user creates a project with name and number
   	Then the system throws InvalidUserException
 