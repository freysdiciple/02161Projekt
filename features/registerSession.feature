Feature: Register Spent Time
Description: The developer has to register the amount of time he has spent on different activities that day
Actors: Developer

Scenario: The developer registers all the above hours at the end of the day
	Given the user is a developer
	When the developer registers a session
	Then the session is registered under the activity
	And the session is registered under the developer

Scenario: The developer registers hours that overlap
	Given the user is a developer 
	And the developer registers a session	
	When the developer registers another overlapping session
	Then the system throws an OverlappingSessionsException
