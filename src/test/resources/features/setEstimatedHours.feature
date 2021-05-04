Feature: Set estimated work hours for activity
Description: The project leader assigns estimated work hours for an activity
Actor: Project leader

#Scenario: Successfully set estimated work hours
#	Given there is an user with ID "Søren"
#   And there is a project with ID 100
#	And the user "Søren" is a Project leader
#	And there is an activity with ID 500
#	When the user provides the estimated hours for the activity
#	Then the estimated hours for the activity is set

#Scenario: Successfully change estimated work hours
#	Given there is an user with ID "Søren"
#   And there is a project with ID 101
#	And the user "Søren" is a Project leader
#	And there is an activity with ID 501
#	And there is an estimated hours set on the activity
#	When the user changes the estimated hours for the activity
#	Then the estimated hours are the new hours

#Scenario: Developer trying to set estimated work hours
#   Given there is an user with ID "Søren"
#   And there is a project with ID 102
#	And the user is a not Project leader
#	And there is an activity with ID 502
#	When the user provides the estimated hours for the activity
#	Then a NotAuthorizedException is thrown
#	And the estimated work hours is not set
