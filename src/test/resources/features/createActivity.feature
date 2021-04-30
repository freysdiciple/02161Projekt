Feature: Create activity for project
Description: the project leader creates a new activity
Actor: Project leader

Scenario: Successfully create activity
	Given there is a project
	And there is a user with id {String} and database {DataBase}
	And the user is a project leader
	When the user creates an activity in that project
	Then the activity is listed under the project

Scenario: Duplicate name
	Given there is a project
	And there is a user with id {String} and database {DataBase}
	And the user is a project leader
	And there is an activity with the ID {int}
	When an activity with the same ID is created
	Then an ActivityAlreadyExistsException is thrown

Scenario: Developer trying to create activity
	Given there is a project
	And there is a user with id {String} and database {DataBase}
	And the user is not a project leader
	When the user creates an activity in that project
	Then a NotAuthorizedException is thrown
	And the activity is not created

#Scenario: Successfully create activity
#	Given there is a project
#	And the user is a project leader
#	When the project leader creates an activity in that project
#	Then the activity is listed under the project

#Scenario: Duplicate name
#	Given there is a project
#	And the user is a project leader
#	And there is an activity with the name "Activity_1"
#	When an activity with the same name is created
#	Then a DuplicateNameException is thrown
	
#Scenario: Developer trying to create activity
#	Given there is a project
#	And the user is not a project leader
#	When the developer creates an activity in that project
#	Then a NotAuthorizedException is thrown
#	And the activity is not created