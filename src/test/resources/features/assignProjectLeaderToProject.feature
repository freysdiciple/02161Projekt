Feature: Assign project leader to project
   Description: The user assign a project leader to a given project
   Actors: Admin

# Main scenario
#Scenario: Assign the role project leader to a developer on an existing project successfully 
#	Given the user is an admin
#	And there is a project
#	And a developer listed under that project
#	And the developer does not have the role project leader
#	When the admin assigns the role project leader on the project to the developer
#	Then the developer is given the role project leader on that project

# Alternate scenario one
#Scenario: Assign the role project leader to a developer not on the existing project
#	Given the user is an admin
#	And there is a project
#	And a developer, who is not listed under the project
#	And the developer does not have the role project leader
#	When the admin assigns the role project leader on the project to the developer
#	Then the system throws developerNotListedOnProjectException is given

# Alternate scenario two
#Scenario: Assign the role project leader to a developer who has the role on an existing project 
#	Given the user is an admin 
#	And there is a project
#	And a developer listed under that project
#	And the developer has the role project leader on the given project
#	When the admin assigns the role project leader on the project to the developer
#	Then the system throws developerIsProjectLeaderException is given

# Alternate scenario three
#Scenario: Assign the role project leader to a developer on an existing project as a non-admin
#	Given the user is not an admin
#	And there is a project
#	And a developer listed under that project
#	And the developer does not have the role project leader
#	When the non-admin assigns the role project leader on the project to the developer
#	Then the system throw userNotAnAdminException is given
	
	