package SoftwareAS.Controller;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import SoftwareAS.Model.*;

public class FrontEndController {
	
	private DataBase database;
	private Scanner input;
	private Developer currentUser;
	private Activity currentActivity;
	private Project currentProject;
	private Session currentSession;
	
	public FrontEndController(DataBase database) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		this.database = database;
		this.input = new Scanner(System.in);
		loginSequence();
	}
	
	public static void clearScreen() {     
	    System.out.println("\n\n\n\n");   
	   } 
	
	public void loginSequence() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		System.out.println("Please Enter Your ID to login:");
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			currentUser = database.getAdminById(id);
			WelcomePageAdmin();
		}
		else if(database.containsDeveloper(id)) {
			currentUser = database.getDeveloperById(id);
			WelcomePageDeveloper();
		}
		else {
			System.out.println("No employee with the given id. Pleas try again!");
			loginSequence();
		}
	}
	
	public void WelcomePageAdmin() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		clearScreen();
		System.out.println("Welcome " + currentUser.getId() + "!");
		System.out.println("What do you want to do? To move forward, enter the number that corresponds to your desired action:");
		System.out.println("0 - Log out");
		System.out.println("1 - Manage Developers");
		System.out.println("2 - Manage Projects");
		System.out.println("3 - My Projects");
		System.out.println("4 - My Sessions");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				loginSequence();
				break;
			case 1:
				manageDevelopers();
				break;
			case 2:
				manageProjects();
				break;
			case 3:
				myProjects();
				break;
			case 4:
				mySessions();
				break;
			default:
				WelcomePageAdmin();
		}
		
	}
	
	private void mySessions() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		clearScreen();
		System.out.println("Welcome to your sessions!");
		System.out.println("What do you want to do? To move forward, enter the number that corresponds to your desired action:");
		System.out.println("0 - Go Back");
		System.out.println("1 - Register A Session");
		System.out.println("2 - Modify A Session");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				if(currentUser.isAdmin()) WelcomePageAdmin();
				else WelcomePageDeveloper();
				break;
			case 1:
				registerSession(false);
				break;
			case 2:
				modifySession();
				break;	
			default:
				mySessions();
		}
	}

	private void modifySession() {
		// TODO Auto-generated method stub
		
	}

	//MANGLER KONTROL CHECKS!!
	private void registerSession(boolean underActivity) throws OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, AdminNotFoundException, DeveloperNotFoundException {
		clearScreen();
		System.out.println("Register a session by entering the following and pressing enter: ");
		System.out.println((underActivity ? "" : "{activityID} ") + "{startdatetime} {enddatetime}");
		System.out.println("The dates have the following syntax:");
		System.out.println("DD/MM/YYYY HH-MM");
		
		input.nextLine();
		String sessionInfo = input.nextLine();
		
		//Check if input is correct
		if(sessionInfo.length() != 40 && sessionInfo.length() != 35 && sessionInfo.length() != 30) {System.out.println("not correct syntax"); registerSession(underActivity);}
		
		//Continue
		String activityidString;
		String startdateString;
		String starttimeString;
		String enddateString;
		String endtimeString;
		
		//Get activity id if not under activity
		if(!underActivity) {
			activityidString = sessionInfo.substring(0,6);
			sessionInfo = sessionInfo.substring(7, sessionInfo.length());
		}
		else activityidString = currentActivity.getId() + "";
		
		//Get dates and times with the ability to use 'today' as parameter
		if(sessionInfo.substring(0,5).equals("today")) {
			startdateString = "today";
			starttimeString = sessionInfo.substring(6,11);
			
			if(sessionInfo.substring(12,17).equals("today")) {
				enddateString = "today";
				endtimeString = sessionInfo.substring(18,23);
			}
			else {
				enddateString = sessionInfo.substring(12, 22);
				endtimeString = sessionInfo.substring(23, 28);
			}
		}
		else {
			startdateString = sessionInfo.substring(0, 10);
			starttimeString = sessionInfo.substring(11, 16);
			
			if(sessionInfo.substring(17,22).equals("today")) {
				enddateString = "today";
				endtimeString = sessionInfo.substring(23,28);
			}
			else {
				enddateString = sessionInfo.substring(17, 27);
				endtimeString = sessionInfo.substring(28, 33);
			}
		}
		//Check if input is correnct
		
		
		//Convert strings to numbers
		int activityId = Integer.parseInt(activityidString);
		
		int startYear; int startMonth; int startDay;
		
		if(startdateString.equals("today")) {
			GregorianCalendar today = new GregorianCalendar();
			startYear = today.get(GregorianCalendar.YEAR);
			startMonth = today.get(GregorianCalendar.MONTH);
			startDay = today.get(GregorianCalendar.DAY_OF_MONTH);
		}
		else {			
			startYear = Integer.parseInt(startdateString.substring(6,10));
			startMonth = Integer.parseInt(startdateString.substring(3,5));
			startDay = Integer.parseInt(startdateString.substring(0,2));
		}
		
		int startHour = Integer.parseInt(starttimeString.substring(0,2));
		int startMin = Integer.parseInt(starttimeString.substring(3,5));
		
		int endYear; int endMonth; int endDay;
		
		if(enddateString.equals("today")) {
			GregorianCalendar today = new GregorianCalendar();
			endYear = today.get(GregorianCalendar.YEAR);
			endMonth = today.get(GregorianCalendar.MONTH);
			endDay = today.get(GregorianCalendar.DAY_OF_MONTH);
		}
		else {
			endYear = Integer.parseInt(enddateString.substring(6,10));
			endMonth = Integer.parseInt(enddateString.substring(3,5));
			endDay = Integer.parseInt(enddateString.substring(0,2));			
		}
		
		int endHour = Integer.parseInt(endtimeString.substring(0,2));
		int endMin = Integer.parseInt(endtimeString.substring(3,5));
		
		
		//Convert to dates and register session
		GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMin);
		GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMin);
		
		Activity activity = null;
		List<Activity> userActivities = currentUser.getActivities();
		
		for(Activity act : userActivities) {
			if(act.getId() == activityId) activity = act;
		}
		
		if(activity == null) registerSession(underActivity);
		else {
			currentUser.registerSession(activity, start, end);
			mySessions();
		}
		
	}

	private void myProjects() {
		clearScreen();
		System.out.println("You have entered your projects!");
		
	}

	private void manageProjects() {
		clearScreen();
		System.out.println("You have entered manage projects!");
		
	}

	private void manageDevelopers() {
		clearScreen();
		System.out.println("You have entered manage developers!");
		
	}

	public void WelcomePageDeveloper() {
		clearScreen();
		System.out.println("You are logged in as developer!");
	}

}
