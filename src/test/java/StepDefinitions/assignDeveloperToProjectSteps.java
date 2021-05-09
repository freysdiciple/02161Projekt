package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.*;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.*;
import SoftwareAS.Model.*;

public class assignDeveloperToProjectSteps {


    private DataBase database;
    private ErrorMessageHolder errorMessageHolder;
    
    public assignDeveloperToProjectSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
    	this.database = softwareAS.getDataBase();
    	this.errorMessageHolder = errorMessageHolder;
    }


    @Given ("2- the user {string} is an admin")
    public void theUserIsAnAdmin(String adminName) throws Throwable{

        database.createAdmin(adminName);
        assertTrue(database.containsAdmin(adminName));
    }

    @Given("2- there is a project named {string}")
    public void thereIsAProject(String projectName) throws Throwable{

        database.createAdmin("alwaysAdmin");
        database.getAdminById("alwaysAdmin").createProject(projectName);
        assertTrue(database.containsProject(projectName));

    }

    @When("2- the user {string} adds the developer {string} to the project {string}")
    public void theUserAddsTheDeveloperToTheProject(String adminName, String developerName, String projectName) throws Throwable{
        try {
            database.getAdminById(adminName).createDeveloper(developerName);
        }
        catch (AdminNotFoundException e) {
            database.createAdmin("thisAdmin");
            database.getAdminById("thisAdmin").createDeveloper(developerName);
        }

        try {
            database.getProjectByName(projectName).assignDeveloperToProject(database.getAdminById(adminName), database.getDeveloperById(developerName));
        }
        catch (AdminNotFoundException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        catch (ProjectNotFoundException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("2- the developer {string} is added to the list of developers on the project {string}")
    public void theDeveloperIsAddedToTheListOfDevelopersOnTheProject(String developerName, String projectName) throws Throwable{

        assertTrue(database.getProjectByName(projectName).isDeveloperOnProject(developerName));
    }


    @When ("2- the user {string} adds a non-existing developer {string} to the project {string}")
    public void theUserAddsTheNonDeveloperToTheProject(String adminName, String developerName, String projectName) throws Throwable{

        database.getAdminById(adminName).createProject(projectName);
        try {
            database.getProjectByName(projectName).assignDeveloperToProject(database.getAdminById(adminName), database.getDeveloperById(developerName));
        } catch(DeveloperNotFoundException e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then ("2- the system provides an error message that the developer doesn't exist")
    public void developerDoesntExist() throws Throwable {

        assertEquals("No developer with described ID",errorMessageHolder.getErrorMessage());
    }


    @Given("2- the project {string} doesn’t exist")
    public void theProjectDoesntExist(String projectName) throws Throwable {

        if (database.containsProject(projectName)){
            database.deleteProject(projectName);
        }

    }

    @Then("2- the system provides an error message that the project doesn't exist")
    public void theProjectDoesntExist() throws Throwable {

        assertEquals("No project with described ID",errorMessageHolder.getErrorMessage());
    }


    @Given("2- the user {string} is not admin")
    public void theUserIsNotAdmin(String nonAdminName) throws Throwable{

        database.deleteAdmin(nonAdminName);
    }

    @Then("2- the system provides an error message InvalidUserException")
    public void notAdminAdminError() throws Throwable{

        assertEquals("No admin with described ID",errorMessageHolder.getErrorMessage());
    }






}
