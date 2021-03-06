Feature: Modify registered time
   Description: The user assign a project leader to a given project
   Actors: Admin
## Markus

#Main scenario
Scenario: The developer removes the registered time on an activity the developer has registered
   Given 7- the user "ludw" is a developer
	And 7- there is a project with id "BIBOBO" created by Bob
	And 7- there is an activity with Id 701 created by the project leader "Kenn"
	And 7- the developer have registered time on that activity
	When 7- the developer removes the registered time on that activity
	Then 7- the registered time is removed successfully

#Alternate scenario one
Scenario: The developer changes the registered time on an activity the developer has registered
	Given 7- the user "ludw" is a developer 
	And 7- there is a project with id "BLOOB" created by Bob
	And 7- there is an activity with Id 701 created by the project leader "Kenn"
	And 7- the developer have registered time on that activity
	When 7- the developer changes start and end time of the session on that activity
	Then 7- the registered time is changed successfully

