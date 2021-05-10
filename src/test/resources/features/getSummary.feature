Feature: Get Summary
Description: The project manager request a time-related summary of a project or activity
Actors: Project Leader
#Massimo
Scenario: Get summary of activity in last week
	Given 6 - the user is a project leader1
	And 6 - the developers have registered their daily time on the activities
	When 6 - the project leader request a summary of the activity in the last week
	Then 6 - the project leader successfully receives a summary of the activity

Scenario: Get summary of all activities in project successfully
	Given 6 - the user is a project leader2
	And 6 - the developers have registered their daily time on the activities
	When 6 - the project manager requests a summary of the project
	Then 6 - the project manager recieves a summary of the project
