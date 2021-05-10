Feature: Assign project leader to project
   Description: The user assign a project leader to a given project
   Actors: Admin
#Markus
# Main scenario
Scenario: Assign the role project leader to a developer on an existing project successfully 
	Given 3- the user "Bob" is an admin
	And 3- there is a project with ID "Hello"
	And 3- the admin assigns the developer "Kenn" to the project
	And 3- the developer does not have the role project leader
	When 3- the admin assigns the role project leader on the project to the developer
	Then 3- the developer is given the role project leader on that project

# Alternate scenario one
Scenario: Assign the role project leader to a developer not on the existing project
	Given 3- the user "Bob" is an admin
	And 3- there is a project with ID "There"
	And 3- a developer "Knut" who is not listed under the project
	And 3- the developer does not have the role project leader
	When 3- the admin assigns the role project leader on the project to the developer
	Then 3- the system throws developerNotFoundException is given

# Alternate scenario two
Scenario: Assign the role project leader as a non-admin to a developer on an existing project 
	Given 3- the user "Knat" is not an admin 
	And 3- there is a project with ID "Young padawan" created by an admin "Max"
	When 3- the user assigns the role project leader to a developer "Kvat" already on the project
	Then 3- the system throws notAuthorizedException is given