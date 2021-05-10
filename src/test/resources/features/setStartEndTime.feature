Feature: Set start/deadline for activity
Description: The project leader assigns a start and a deadline for an activity
Actor: Project leader


Scenario: Successfully set start/deadline for an activity
	Given 11- there is a project "2100001"
	And 11- the user "Mads" is a project leader
	And 11- there is an activity 1234
	When 11- the user "Mads" sets the activity 1234 to begin week 5 and end week 15
	Then 11- the the activity 1234 is registered to begin week 5 and end week 15

Scenario: Successfully change start/deadline for an activity
	Given 11- there is a project "210001"
	And 11- the user "Mads" is a project leader
	And 11- there is an activity 1234
	And 11- the activity 1234 is already registered to begin week 3 and end week 5
	When 11- the user "Mads" sets the activity 1234 to begin week 2 and end week 10
	Then 11- the the activity 1234 is registered to begin week 2 and end week 10

#Scenario: Developer trying to set start/deadline
#	Given 11- there is a project "210001"
#	And 11- the user "Mads" is not a project leader
#	And 11- there is an activity
#	When 11- the user sets the activity to begin week 7 and end week 9
#	Then 11- the start/deadline for the activity is not set
