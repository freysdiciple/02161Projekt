Feature: Register Spent Time
Description: The developer has to register the amount of time he has spent on different activities that day
Actors: Developer

Scenario: The developer successfully registers a session
	Given 8- there is a developer
	And 8- there is an activity1
	And 8- the developer is assigned to the activity
	When 8- the developer registers a session
	Then 8- the session is registered under the activity
	And 8- the session is registered under the developer

Scenario: The developer registers hours that overlap
	Given there is a developer
	And 8- there is an activity2
	And 8- the developer is assigned to the activity
	And 8- the developer registers a session
	When 8- the developer registers another overlapping session
	Then 8- the system throws an OverlappingSessionsException
