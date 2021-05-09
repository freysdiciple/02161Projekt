Feature: Create activity for project
Description: the project leader creates a new activity
Actor: Project leader

Scenario: Successfully create activity
	Given 4- there is a project with project ID "400001"
	And 4- there is a user with ID "John" and database
	And 4- the user is a project leader
	When 4- the user creates an activity with ID 925 in that project
	Then 4- the activity is listed under the project

Scenario: Duplicate name
	Given 4- there is a project with project ID "400002"
	And 4- there is a user with ID "John" and database
	And 4- the user is a project leader
	And 4- there is an activity with ID 925
	When 4- the user creates an activity with ID 925 in that project
	Then 4- an ActivityAlreadyExistsException is thrown

Scenario: Developer trying to create activity
	Given 4- there is a project with project ID "400003"
	And 4- there is a user with ID "John" and database
	And 4- the user is not a project leader
	When 4- the user creates an activity with ID 925 in that project
	Then 4- a NotAuthorizedException is thrown
	And 4- the activity is not created