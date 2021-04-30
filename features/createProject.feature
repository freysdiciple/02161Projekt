Feature: Create new project
   Description: The user creates a new project 
   Actors: Admin
   
   # Main scenario
	 Scenario: Create new project
	 Given there is an user with id {String} and database {DataBase}
	 And the user is an admin
	 When the user creates a project with a number {int} and a creator {Admin}
	 Then the project with a number {int} is contained in the system

   # Alternate scenario one     
	 Scenario: A project with the given name already exists
		Given there is a user
		And the user is an admin
		When the user creates a project with name identical to an existing project
    	Then the system throws ExistingProjectException
 
   # Alternate scenario three
	 Scenario: User is not and admin
		Given there is a user
		And the user is not an admin
	   	When the user creates a project with name and number
	   	Then the system throws InvalidUserException
 