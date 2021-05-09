Feature: Set estimated work hours for activity
Description: The project leader assigns estimated work hours for an activity
Actor: Project leader

Scenario: Successfully set estimated work hours
	Given 10- there is an user with ID "Kvak"
   And 10- there is a project with ID "Bellow"
	And 10- the user "Kvak" is a Project leader
	And 10- there is an activity with ID 500
	When 10- the user provides the estimated hours 72 for the activity
	Then 10- the estimated hours for the activity is set

Scenario: Successfully change estimated work hours
	Given 10- there is an user with ID "Quik"
   And 10- there is a project with ID "Quicker"
	And 10- the user "Quik" is a Project leader
	And 10- there is an activity with ID 501
	And 10- the estimated hours are set to 72 for the activity
    When 10- the user changes the estimated hours to 80 for the activity
    Then 10- the estimated hours are the new hours

Scenario: Developer trying to set estimated work hours
   Given 10- there is an user with ID "Krak"
   And 10- there is a project with ID "Kraken"
	And 10- the user "Krak" is not a project leader
	And 10- there is an activity with ID 502
	When 10- the user provides the estimated hours 72 for the activity
	Then 10- a NotAuthorizedException is thrown
	And 10- the estimated work hours is not set
