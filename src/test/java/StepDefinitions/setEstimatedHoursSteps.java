package StepDefinitions;

import static org.junit.Assert.*;
import Exceptions.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Controller.SoftwareAS;
import SoftwareAS.Model.*;



public class SetStartEndTimeSteps {

    /*
    private String developerAndleaderName = "Konrad";
    private String justDeveloperName = "Christian";
    private String projectID = "210001";
    private int activityID = 102;

    private DataBase database;
    private Admin admin = new Admin("mogens",database);
    private Developer developerAndLeader;
    private Developer justDeveloper;
    private Project project;
    */

    private DataBase database;
    private Admin admin;
    private Project project;
    private Developer developer;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();


    public SetStartEndTimeSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) {
		this.database = softwareAS.getDataBase();
	}

    @Given ("11- there is a project {string}")
    public void thereIsAProject(String projectName) throws Throwable {


        database.createAdmin("alwaysAdmin");
        admin = database.getAdminById("alwaysAdmin");

        try {
            admin.createProject(projectName);
        }
        catch (ProjectAlreadyExistsException e) {}

        project = database.getProjectByName(projectName);
        assertTrue(database.containsProject(projectName));
    }

    @Given ("11- the user {string} is a project leader")
    public void theUserIsAProjectLeader(String userName) throws Throwable{


        admin.createDeveloper(userName);
        developer = database.getDeveloperById(userName);
        project.assignDeveloperToProject(admin, developer);
        project.setProjectLeader(admin, developer);

    }

    @Given ("11- there is an activity {int}")
    public void thereIsAnActivity(int activityID) throws Throwable{

        try {
            project.createActivity(activityID, developer);
        }catch (ActivityAlreadyExistsException e) {}

    }


    @When ("11- the user {string} sets the activity {int} to begin week {int} and end week {int}")
    public void userProvidesTimeForTheActivity(String username, int activityID, int activityStart, int activityEnd) throws Throwable{

        project.getActivityById(activityID).setStartWeek(activityStart);
        project.getActivityById(activityID).setEndWeek(activityEnd);

    }


    @Then("11- the the activity {int} is registered to begin week {int} and end week {int}")
    public void theTimeForTheActivityIsSet(int activityID, int activityStart, int activityEnd) throws Throwable{

        assertEquals(activityStart, project.getActivityById(activityID).getStartWeek());
        assertEquals(activityEnd, project.getActivityById(activityID).getEndWeek());

    }


    @Given ("11- the activity {int} is already registered to begin week {int} and end week {int}")
    public void theActivityAlreadyHasTime(int activityID, int existingActivityStart, int existingActivityEnd) throws Throwable{

        project.getActivityById(activityID).setStartWeek(existingActivityStart);
        project.getActivityById(activityID).setEndWeek(existingActivityEnd);

    }



    @Given ("11- the user {string} is not a project leader")
    public void theUserIsNotAProjectLeader(String userName) throws Throwable{

        admin.createDeveloper(userName);
        developer = database.getDeveloperById(userName);
        project.assignDeveloperToProject(admin, developer);

    }
/*
    @Then ("11- a NotAuthorizedException is thrown")
    public void aNotAuthorizedExceptionIsThrown() throws Throwable{

        throw new PendingException();
    }



    @Then ("11- the start\\/deadline for the activity is not set")
    public void theTimeForTheActivityIsNotSet() throws Throwable{

        throw new PendingException();
    }


*/

}
