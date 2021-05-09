package StepDefinitions;

import static org.junit.Assert.*;

import Exceptions.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;

public class assignDeveloperToProjectSteps {


    private DataBase database = DataBase.getInstance();

    @Given ("2- the user {string} is an admin")
    public void theUserIsAnAdmin(String adminName) throws Throwable{

        database.createAdmin(adminName);
        assertTrue(database.containsAdmin(adminName));
    }

    @Given("2- there is a project named {string}")
    public void thereIsAProject(String projectName) throws Throwable{

        database.createAdmin("thisAdmin");
        database.getAdminById("thisAdmin").createProject(projectName);
        assertTrue(database.containsProject(projectName));

    }

    @When("2- the user {string} adds the developer {string} to the project {string}")
    public void theUserAddsTheDeveloperToTheProject(String adminName, String developerName, String projectName) throws Throwable{

        database.getAdminById(adminName).createDeveloper(developerName);
        database.getProjectById(projectName).assignDeveloperToProject(database.getAdminById(adminName), database.getDeveloperById(developerName));

    }

    @Then("2- the developer {string} is added to the list of developers on the project {string}")
    public void theDeveloperIsAddedToTheListOfDevelopersOnTheProject(String developerName, String projectName) throws Throwable{

        assertTrue(database.getProjectById(projectName).isDeveloperOnProject(developerName));
    }



}
