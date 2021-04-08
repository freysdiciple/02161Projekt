Feature: Set start/deadline for activity
Description: The project leader assigns a start and a deadline for an activity
Actor: Project leader

Scenario: Successfully set start/deadline for an activity
	Given there is a project
	And the user is a project leader
	And there is an activity
	When the user provides the start/deadline for the activity
	Then the start/deadline for the activity is set

Scenario: Successfully change start/deadline for an activity
	Given there is a project
	And the user is a project leader
	And there is an activity
	And the activity already has a start/deadline time
	When the user provides the start/deadline for the activity
	Then the start/deadline time for the activity is changed

Scenario: Developer trying to set start/deadline
	Given there is a project
	And the user is not a project leader
	And there is an activity
	When the user provides the start/deadline for the activity
	Then a NotAuthorizedException is thrown
	And the start/deadline for the activity is not set

