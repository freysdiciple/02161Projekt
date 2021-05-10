#This feature is made by Simon Bang Hjortkilde - s183557

Feature: Create new project
   Description: The user creates a new project
   Actors: Admin


#Main scenario
Scenario: Create new project
  	Given 5- there is an user with ID "SØR1"
	And 5- the user is an admin
 	When 5- the user creates a project with a valid name "Project123"
   	Then 5- the project with a name "Project123" is contained in the system


#Alternate scenario one
Scenario: A project with the given name already exists
	Given 5- there is an user with ID "MOG1"
	And 5- the user is an admin
	When 5- the user creates a project with a valid name "Project321" identical to an existing project
    Then 5- the system throws ExistingProjectException

#Alternate scenario two
Scenario: User is not an admin
	Given 5- there is an user with ID "KAR1"
	And 5- the user is not an admin
    When 5- the user tries to create a project with a valid name "Project999"
    Then 5- the system throws NotAuthorizedException

#Alternate scenario three
Scenario: Invalid project name length
	Given 5- there is an user with ID "JEP1"
	And 5- the user is an admin
	When 5- the user tries to create a project with a project name "123" with invalid length
	Then 5- the system throws OutOfBoundsException

