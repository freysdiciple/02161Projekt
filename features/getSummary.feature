Feature: Get Summary
Description: The project manager request a time-related summary of a project or activity
Actors: Project Leader

Scenario: Get summary of single activity in project in the last week successfully
	Given the user is a project leader
	And the developers have registered their daily time on the activity
	When the project manager request a summary of the activity in the last week
	Then the project manager successfully receives a summary of the activity

Scenario: Get summary of all activities in project successfully
	Given the user is a project leader
	And the developers have registered their daily time on all activities
	When the project manager requests a summary of the project
	Then the project manager receives a summary of the project


