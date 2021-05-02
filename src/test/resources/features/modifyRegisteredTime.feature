Feature: Modify registered time
   Description: The user assign a project leader to a given project
   Actors: Admin


#Main scenario
#Scenario: The developer removes the registered time on an activity the developer has registered 
#   Given the user is a developer
#	And there is a project
#	And there is an activity
#	And the developer have registered time on that activity
#	When the developer removes the registered time on that activity
#	Then the registered time is removed successfully

# Alternate scenario one
#Scenario: The developer changes the registered time on an activity the developer has registered 
#	Given the user is a developer
#	And there is a project
#	And there is an activity
#	And another developer have registered time on that activity
#	When the developer changes the registered time on that activity
#	Then the registered time is changed successfully

# Alternate scenario two
#Scenario: The developer changes the registered time on an activity another developer has registered
#	Given the user is a developer
#	And there is a project
#	And there is an activity
#	And another developer have registered time on that activity
#	When the developer removes the registered time on that activity
#	Then the error message “Data can only be modified by the developer who has registered the data” is given

# Alternate scenario three
#Scenario: The admin changes the registered time on an activity another developer has registered
#	Given the user is an admin
#	And there is a project
#	And there is an activity
#	And another developer have registered time on that activity
#	When the admin changes the registered time on that activity
#	Then the registered time is changed successfully

# Alternate scenario four
#Scenario: The project leader changes the registered time on an activity another developer has registered
#	Given the user is a project leader
#	And there is a project
#	And there is an activity
#	And another developer have registered time on that activity
#	When the project leader removes the registered time on that activity
#	Then the error message “Data can only be modified by the developer who has registered the data” is given