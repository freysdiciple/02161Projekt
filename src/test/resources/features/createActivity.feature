Feature: Create activity for project
Description: the project leader creates a new activity
Actor: Project leader

Scenario: Successfully create activity
	Given there is a project with project number 234234
	And there is a user with ID createActivityDeveloper1ID and database
	And the user with ID createActivityDeveloper1ID is a project leader
	When the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
	Then the activity with ID 925 is listed under the project

Scenario: Duplicate name
	Given there is a project with project number 234234
	And there is a user with ID createActivityDeveloper1ID and database
	And the user with ID createActivityDeveloper1ID is a project leader
	And there is an activity with ID 925
	When an activity with ID 925 is created
	Then an ActivityAlreadyExistsException is thrown, createActivity

Scenario: Developer trying to create activity
	Given there is a project with project number 234234
	And there is a user with ID createActivityDeveloper1ID and database
	And the user with ID createActivityDeveloper1ID is not a project leader
	When the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
	Then a NotAuthorizedException is thrown, createActivity
	And an activity with ID 925 is not created


### Old versions (from before implementing code)

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