Feature: Set estimated work hours for activity
Description: The project leader assigns estimated work hours for an activity
Actor: Project leader

#Scenario: Successfully set estimated work hours
#	Given there is a project
#	And the user is a project leader
#	And there is an activity
#	When the user provides the estimated hours for the activity
#	Then the estimated hours for the activity is set

#Scenario: Successfully change estimated work hours
#	Given there is a project
#	And the user is a project leader
#	And there is an activity
#	And there is an estimated hours set on the activity
#	When the user changes the estimated hours for the activity
#	Then the estimated hours are the new hours

#Scenario: Developer trying to set estimated work hours
#	Given there is a project
#	And the user is not a project leader
#	And there is an activity
#	When the user provides the estimated hours for the activity
#	Then a NotAuthorizedException is thrown
#	And the estimated work hours is not set
