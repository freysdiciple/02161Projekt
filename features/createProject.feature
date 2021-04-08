Feature: Assign developer to project
   Description: The user assign a developer to a given project 
   Actors: Admin
   
# Main scenario   
Scenario: Add developer to project
	Given the user is an admin
	And there is a project
	When the user adds the developer to the project
   	Then the developer is added to the list of developers on the project

# Alternate scenario one 
Scenario: The developer doesn't exist
	Given the user is an admin
	When the user adds a non-existing developer to the project
   	Then the system provides an error message that the developer doesn't exist

# Alternate scenario two 
Scenario: The project doesn't exist
	Given the user is an admin
   	And the project doesn’t exist
	When the user adds the developer to the project
     	Then the system provides an error message that the project doesn't exist

# Alternate scenario three
Scenario: The user is not an admin
	Given the user is a not admin
	When the user adds the developer to the project
   	Then the system throws InvalidUserException

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
 