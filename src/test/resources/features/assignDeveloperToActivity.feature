Feature: Assign developer to activity
Description: The project leader assigns developers to an existing activity
Actor: Project leader

Scenario: Successfully assign developer to activity
	Given 1- there is a project with project number 123123
	And 1- there is a user with ID assignDeveloperToActivityDeveloper1ID and database
	And 1- the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
	And 1- there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
	And 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
	Then 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is listed under the activity

Scenario: Assign developer not on project to an activity
	Given 1- there is a project with project number 123123
	And 1- there is a user with ID assignDeveloperToActivityDeveloper1ID and database
	And 1- the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
	And 1- there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
	And 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
	Then 1- a DeveloperNotFoundException is thrown, assignDeveloperToActivity
	And 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under activity

#Scenario: Assign a non-developer to an activity
#	Given 1- there is a project
#	And 1- there is a user with ID and database
#	And 1- the user is a project leader
#	And 1- there is not a developer with ID and database
#	And 1- there is an activity with ID 1
#	When 1- the developer is assigned to the activity
#	Then 1- a DeveloperNotFoundException is thrown

Scenario: Assign developer to an activity that does not exist
	Given 1- there is a project with project number 123123
	And 1- there is a user with ID assignDeveloperToActivityDeveloper1ID and database
	And 1- the user with ID assignDeveloperToActivityDeveloper1ID is a project leader
	And 1- there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
	And 1- there is not an activity with ID 924
	When 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
	Then 1- an ActivityNotFoundException is thrown, assignDeveloperToActivity

Scenario: Developer assigns developer to an activity
	Given 1- there is a project with project number 123123
	And 1- there is a user with ID assignDeveloperToActivityDeveloper1ID and database
	And 1- the user with ID assignDeveloperToActivityDeveloper1ID is not a project leader
	And 1- there is a second developer with ID assignDeveloperToActivityDeveloper2ID and database
	And 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is assigned to the activity
	Then 1- an OperationNotAllowedException is thrown, assignDeveloperToActivity
	And 1- the second developer with ID assignDeveloperToActivityDeveloper2ID is not listed under the activity	


### Old versions (from before implementing code)

#Scenario: Successfully assign developer to activity
#	Given the user is a project leader
#	And there is a developer
#	And there is an activity
#	When the developer is assigned to the activity
#	Then the system adds the developer to the activity

#Scenario: Assign a non-developer to an activity
#	Given the user is a project leader
#	And there is not a developer
#	And there is an activity
#	When the developer is assigned to the activity
#	Then a NoDeveloperException is thrown

#Scenario: Assign developer to an activity that does not exist
#	Given the user is a project leader
#	And there is a developer
#	And there is not an activity
#	When the developer is assigned to the activity
#	Then a NoActivityException is thrown

#Scenario: Developer assigns developer to an activity
#	Given the user is not a project leader
#	And there is a developer
#	And there is an activity
#	When the developer is assigned to the activity
#	Then a NotAuthorizedException is thrown
#	And the system does not add the developer to the activity
