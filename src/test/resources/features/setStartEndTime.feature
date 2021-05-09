Feature: Set start/deadline for activity
Description: The project leader assigns a start and a deadline for an activity
Actor: Project leader


Scenario: Successfully set start/deadline for an activity
	Given 11- there is a project
	And 11- the user is a project leader
	And 11- there is an activity
	When 11- the user sets the activity to begin week 5 and end week 15
	Then 11- the the activity is registered to begin week 5 and end week 15

Scenario: Successfully change start/deadline for an activity
	Given 11- there is a project
	And 11- the user is a project leader
	And 11- there is an activity
	And 11- the activity is already registered to begin week 3 and end week 5
	When 11- the user sets the activity to begin week 2 and end week 10
	Then 11- the the activity is registered to begin week 2 and end week 10

Scenario: Developer trying to set start/deadline
	Given 11- there is a project
	And 11- the user is not a project leader
	And 11- there is an activity
	When 11- the user sets the activity to begin week 7 and end week 9
	Then 11- the start/deadline for the activity is not set
