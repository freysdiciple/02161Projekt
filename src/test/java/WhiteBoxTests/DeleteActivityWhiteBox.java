package WhiteBoxTests;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import Exceptions.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;
import org.junit.jupiter.api.Test;


// This class is made by Mathias Jensen
public class DeleteActivityWhiteBox {

    ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();

    Admin admin;
    DataBase dataBase = DataBase.getInstance();
    Project project;
    Developer developer;
    Activity activity;


    @Test
    public void testInputDataSetA() throws Throwable {
        dataBase.createAdmin("Hans");
        admin = dataBase.getAdminById("Hans");
        admin.createProject("DatasetA");
        project = dataBase.getProjectByName("DatasetA");
        admin.createDeveloper("Jørn");
        developer = dataBase.getDeveloperById("Jørn");
        int id = 0;

        try {
            project.deleteActivity(id,developer);
        } catch (NotAuthorizedException e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

        assertEquals(errorMessageHolder.getErrorMessage(),"Not authorized to delete activities.");

    }

    @Test
    public void testInputDatasetB() throws Throwable{

        dataBase.createAdmin("Hans");
        admin = dataBase.getAdminById("Hans");
        admin.createProject("DatasetB");
        project = dataBase.getProjectByName("DatasetB");
        admin.createDeveloper("Jørn");
        developer = dataBase.getDeveloperById("Jørn");
        int nonId = 0;

        try {
            project.deleteActivity(nonId,admin);
        } catch (ActivityNotFoundException e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

        assertEquals(errorMessageHolder.getErrorMessage(),"An activity with that ID doesnt exists.");

    }

    @Test
    public void testInputDatasetC() throws Throwable{

        dataBase.createAdmin("Hans");
        admin = dataBase.getAdminById("Hans");
        admin.createProject("DatasetC");
        project = dataBase.getProjectByName("DatasetC");
        admin.createDeveloper("Jørn");
        developer = dataBase.getDeveloperById("Jørn");
        int id = 1;
        project.createActivity(id, admin);
        activity = project.getActivityById(id);

        project.deleteActivity(id,admin);

        try {
            project.getActivityById(id);
        } catch (ActivityNotFoundException e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

        assertEquals(errorMessageHolder.getErrorMessage(),"No activity with described ID");

    }




}
