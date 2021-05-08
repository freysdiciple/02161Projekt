Feature: Set start/deadline for activity
Description: The project leader assigns a start and a deadline for an activity
Actor: Project leader

Scenario: Successfully set start/deadline for an activity
	Given 11- there is a project
	And 11- the user is a project leader
	And 11- there is an activity
	When 11- the user provides the start/deadline for the activity
	Then 11- the start/deadline for the activity is set

Scenario: Successfully change start/deadline for an activity
	Given 11- there is a project
	And 11- the user is a project leader
	And 11- there is an activity
	And 11- the activity already has a start/deadline time
	When 11- the user provides the start/deadline for the activity
	Then 11- the start/deadline time for the activity is changed

Scenario: Developer trying to set start/deadline
	Given 11- there is a project
	And 11- the user is not a project leader
	And 11- there is an activity
	When 11- the user provides the start/deadline for the activity
	Then 11- a NotAuthorizedException is thrown
	And 11- the start/deadline for the activity is not set
