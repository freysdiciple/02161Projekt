Feature: Get Summary
Description: The project manager request a time-related summary of a project or activity
Actors: Project Leader

Scenario: Get summary of activity in last week
	Given the user is a project leader
	And the developers have registered their daily time on the activities
	When the project leader request a summary of the activity in the last week
	Then the project leader successfully receives a summary of the activity

Scenario: Get summary of all activities in project successfully
	Given the user is a project leader
	And the developers have registered their daily time on the activities
	When the project manager requests a summary of the project
	Then the project manager recieves a summary of the project


