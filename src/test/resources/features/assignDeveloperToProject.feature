# This Class is made by Mathias Jensen - s204480

Feature: Assign developer to project
   Description: The user assign a developer to a given project
   Actors: Admin

# Main scenario
Scenario: Add developer to project
	Given 2- the user "John" is an admin
	And 2- there is a project named "210005"
	When 2- the user "John" adds the developer "Hans" to the project "210005"
  	Then 2- the developer "Hans" is added to the list of developers on the project "210005"

# Alternate scenario one
Scenario: The developer doesn't exist
	Given 2- the user "Karl" is an admin
	When 2- the user "Karl" adds a non-existing developer "Lise" to the project "210006"
 	Then 2- the system provides an error message that the developer doesn't exist

# Alternate scenario two
Scenario: The project doesn't exist
	Given 2- the user "John" is an admin
  	And 2- the project "210005" doesn’t exist
	When 2- the user "John" adds the developer "Laur" to the project "210005"
    Then 2- the system provides an error message that the project doesn't exist

# Alternate scenario three
Scenario: The user is not an admin
	Given 2- the user "Mads" is not admin
    And 2- there is a project named "210008"
	When 2- the user "Mads" adds the developer "Hans" to the project "210008"
  	Then 2- the system provides an error message InvalidUserException
