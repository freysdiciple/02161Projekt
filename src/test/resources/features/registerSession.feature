Feature: Register Spent Time
Description: The developer has to register the amount of time he has spent on different activities that day
Actors: Developer

Scenario: The developer successfully registers a session
	Given there is a developer
	And there is an activity
	And the developer is assigned to the activity
	When the developer registers a session
	Then the session is registered under the activity
	And the session is registered under the developer

Scenario: The developer registers hours that overlap
	Given there is a developer
	And there is an activity
	And the developer is assigned to the activity
	And the developer registers a session
	When the developer registers another overlapping session
	Then the system throws an OverlappingSessionsException
