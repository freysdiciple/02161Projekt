Feature: Assign developer to activity
Description: The project leader assigns developers to an existing activity
Actor: Project leader

Scenario: Successfully assign developer to activity
	Given 1- there is a project with project name "1- Scenario 1"
	And 1- there is a user with ID "John" and database
	And 1- the user is a project leader
	And 1- there is a second developer with ID "James" and database
	And 1- the second developer is part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer is assigned to the activity
	Then 1- the second developer is listed under the activity

Scenario: Assign developer not on project to an activity
	Given 1- there is a project with project name "1- Scenario 2"
	And 1- there is a user with ID "John" and database
	And 1- the user is a project leader
	And 1- there is a second developer with ID "James" and database
	And 1- the second developer is not part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer is assigned to the activity
	Then 1- a DeveloperNotFoundException is thrown
	And 1- the second developer is not listed under activity

Scenario: Assign developer to an activity that does not exist
	Given 1- there is a project with project name "1- Scenario 3"
	And 1- there is a user with ID "John" and database
	And 1- the user is a project leader
	And 1- there is a second developer with ID "James" and database
	And 1- there is not an activity with ID 924
	When 1- the second developer is assigned to the activity
	Then 1- an ActivityNotFoundException is thrown

Scenario: Developer assigns developer to an activity
	Given 1- there is a project with project name "1- Scenario 4"
	And 1- there is a user with ID "John" and database
	And 1- the user is not a project leader
	And 1- there is a second developer with ID "James" and database
	And 1- the second developer is part of the project
	And 1- there is an activity with ID 924
	When 1- the second developer is assigned to the activity
	Then 1- an OperationNotAllowedException is thrown
	And 1- the second developer is not listed under the activity