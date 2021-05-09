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


    private String developerAndleaderName = "KONR";
    private String justDeveloperName = "CHRI";
    private String projectID = "210001";
    private int activityID = 102;

    private DataBase database;
    private Admin admin = new Admin("MOG1",database);
    private Developer developerAndLeader;
    private Developer justDeveloper;
    private Project project;
    
    public SetStartEndTimeSteps(SoftwareAS softwareAS, ErrorMessageHolder errorMessageHolder) throws OutOfBoundsException {
		this.database = softwareAS.getDataBase();
	}

    @Given ("11- there is a project")
    public void thereIsAProject() throws Throwable {

        try {
            admin.createProject(projectID);
        } catch (ProjectAlreadyExistsException e){}

        project = database.getProjectByName(projectID);
        assertTrue(database.containsProject(projectID));
    }

    @Given ("11- the user is a project leader")
    public void theUserIsAProjectLeader() throws Throwable{

        admin.createDeveloper(developerAndleaderName);
        developerAndLeader = database.getDeveloperById(developerAndleaderName);
        project.assignDeveloperToProject(admin, developerAndLeader);
        project.setProjectLeader(admin, developerAndLeader);

        assertEquals(developerAndLeader, project.getProjectLeader());
    }


    @Given ("11- there is an activity")
    public void thereIsAnActivity() throws Throwable{

        try {
            project.createActivity(activityID, developerAndLeader);
        }catch (ActivityAlreadyExistsException e) {}

        assertTrue(project.containsActivityWithId(activityID));
    }


    @When ("11- the user sets the activity to begin week {int} and end week {int}")
    public void userProvidesTimeForTheActivity(int activityStart, int activityEnd) throws Throwable{

        project.getActivityById(activityID).setStartWeek(activityStart);
        project.getActivityById(activityID).setEndWeek(activityEnd);
    }


    @Then("11- the the activity is registered to begin week {int} and end week {int}")
    public void theTimeForTheActivityIsSet(int activityStart, int activityEnd) throws Throwable{
        assertEquals(activityStart, project.getActivityById(activityID).getStartWeek());
        assertEquals(activityEnd, project.getActivityById(activityID).getEndWeek());

        System.out.println("The start variable is " + activityStart + " and the registered start week is " + project.getActivityById(activityID).getStartWeek());
        System.out.println("The end variable is " + activityEnd + " and the registered end week is " + project.getActivityById(activityID).getEndWeek());
    }


    @Given ("11- the activity is already registered to begin week {int} and end week {int}")
    public void theActivityAlreadyHasTime(int existingActivityStart, int existingActivityEnd) throws Throwable{
        project.getActivityById(activityID).setStartWeek(existingActivityStart);
        project.getActivityById(activityID).setEndWeek(existingActivityEnd);

        assertEquals(existingActivityStart, project.getActivityById(activityID).getStartWeek());
        assertEquals(existingActivityEnd, project.getActivityById(activityID).getEndWeek());

    }



    @Given ("11- the user is not a project leader")
    public void theUserIsNotAProjectLeader() throws Throwable{

        // This user is just a Developer
        admin.createDeveloper(justDeveloperName);
        justDeveloper = database.getDeveloperById(justDeveloperName);
        project.assignDeveloperToProject(admin, justDeveloper);

        // This Leader is made to create activities only.
        admin.createDeveloper(developerAndleaderName);
        developerAndLeader = database.getDeveloperById(developerAndleaderName);
        project.assignDeveloperToProject(admin, developerAndLeader);
        project.setProjectLeader(admin, developerAndLeader);

        assertTrue(!project.isProjectLeader(justDeveloper));
        System.out.println("Are the developer a project leader? " + project.isProjectLeader(justDeveloper));

    }

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
