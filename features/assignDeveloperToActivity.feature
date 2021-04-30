Feature: Assign developer to activity
Description: The project leader assigns developers to an existing activity
Actor: Project leader

Scenario: Successfully assign developer to activity
	Given there is a project
	And there is a user with id {String} and database {DataBase}
	And the user is a project leader
	And there is a second developer with id {String} and database {DataBase}
	And there is an activity
	When the second developer is assigned to the activity
	Then the second developer is listed under the activity

Scenario: Assign a non-developer to an activity
	Given there is a project
	And there is a user with id {String} and database {DataBase}
	And the user is a project leader
	And there is not a developer with id {String} and database {DataBase}
	And there is an activity
	When the developer is assigned to the activity
	Then a DeveloperNotFoundException is thrown

#Scenario: Assign developer to an activity that does not exist
#	Given there is a project
#	And there is a user with id {String} and database {DataBase}
#	And the user is a project leader
#	And there is a second developer
#	And there is not an activity with ID {int}
#	When the developer is assigned to the activity
#	Then an ActivityNotFoundException is thrown
#
#Scenario: Developer assigns developer to an activity
#	Given the user is not a project leader
#	And there is a developer
#	And there is an activity
#	When the developer is assigned to the activity
#	Then a NotAuthorizedException is thrown
#	And the system does not add the developer to the activity




### Ekstra ting der var hashtag på i forvejen

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