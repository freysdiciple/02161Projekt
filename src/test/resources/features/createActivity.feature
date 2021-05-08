Feature: Create activity for project
Description: the project leader creates a new activity
Actor: Project leader

Scenario: Successfully create activity
	Given 4- there is a project with project number 234234
	And 4- there is a user with ID createActivityDeveloper1ID and database
	And 4- the user with ID createActivityDeveloper1ID is a project leader
	When 4- the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
	Then 4- the activity with ID 925 is listed under the project

Scenario: Duplicate name
	Given 4- there is a project with project number 234234
	And 4- there is a user with ID createActivityDeveloper1ID and database
	And 4- the user with ID createActivityDeveloper1ID is a project leader
	And 4- there is an activity with ID 925
	When 4- the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
	Then 4- an ActivityAlreadyExistsException is thrown, createActivity

Scenario: Developer trying to create activity
	Given 4- there is a project with project number 234234
	And 4- there is a user with ID createActivityDeveloper1ID and database
	And 4- the user with ID createActivityDeveloper1ID is not a project leader
	When 4- the user with ID createActivityDeveloper1ID creates an activity with ID 925 in that project
	Then 4- a NotAuthorizedException is thrown, createActivity
	And 4- an activity with ID 925 is not created


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
