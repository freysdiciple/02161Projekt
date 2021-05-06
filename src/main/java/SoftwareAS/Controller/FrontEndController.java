package SoftwareAS.Controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import Exceptions.ActivityAlreadyExistsException;
import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.NotAuthorizedException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Model.*;

public class FrontEndController {
	
	private DataBase database;
	private Scanner input;
	private Developer currentUser;
	private Activity currentActivity;
	private Project currentProject;
	private Session currentSession;
	
	//ALLE MANGLER KONTROL FOR HVORVIDT DER BLIVER GIVET TAL
	
	public FrontEndController(DataBase database) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		this.database = database;
		this.input = new Scanner(System.in);
		loginSequence();
	}
	
	public static void clearScreen() {     
		try {
			Runtime.getRuntime().exec("clear");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void loginSequence() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
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
	
	public void WelcomePageAdmin() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Welcome " + currentUser.getId() + "!");
		System.out.println("What do you want to do?");
		System.out.println("0 - Log out");
		System.out.println("1 - Manage Employees");
		System.out.println("2 - Manage Projects");
		System.out.println("3 - My Projects");
		System.out.println("4 - My Sessions");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				loginSequence();
				break;
			case 1:
				manageEmployees();
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
	
	private void mySessions() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Welcome to your sessions!");
		System.out.println("What do you want to do?");
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
				chooseSession();
				break;	
			default:
				mySessions();
		}
	}
	
	private void modifySession() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("What would you like to do with this session?");
		System.out.println("0 - Back");
		System.out.println("1 - Delete Session");
		System.out.println("2 - Change Start Time");
		System.out.println("3 - Change End Time");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				chooseSession();
				break;
			case 1:
				deleteSession();
				break;
			case 2:
				changeStartTime();
				break;
			case 3:
				changeEndTime();
			default:
				modifySession();
		}
	}
	
	//MANGLER KONTROL CHECKS
	private void changeEndTime() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Give the new desired end date and time in the form,");
		System.out.println("DD/MM/YYYY HH-MM,");
		System.out.println("or enter 0 to go back");
		
		input.nextLine();
		String info = input.nextLine();
		
		if(info.length() == 1 && Integer.parseInt(info.substring(0, 1)) == 1) {
			modifySession();
		}
		else if(info.length() < 16 || info.length() > 16) {
			changeEndTime();
		}
		else {
			String yearString = info.substring(6,10);
			String monthString = info.substring(3,5);
			String dayString = info.substring(0,2);
			String hourString = info.substring(11,13);
			String minString = info.substring(14,16);
			
			int year = Integer.parseInt(yearString);
			int month = Integer.parseInt(monthString);
			int day = Integer.parseInt(dayString);
			int hour = Integer.parseInt(hourString);
			int min = Integer.parseInt(minString);
			
			GregorianCalendar newEnd = new GregorianCalendar(year,month,day,hour,min);
			
			currentSession.setEndTime(newEnd);
			
			modifySession();
		}
		
		
		
	}

	//MANGLER KONTROL CHECKS
	private void changeStartTime() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Give the new desired start date and time in the form,");
		System.out.println("DD/MM/YYYY HH-MM,");
		System.out.println("or enter 0 to go back");
		
		input.nextLine();
		String info = input.nextLine();
		
		if(info.length() == 1 && Integer.parseInt(info.substring(0, 1)) == 1) {
			modifySession();
		}
		else if(info.length() < 16 || info.length() > 16) {
			changeStartTime();
		}
		else {
			String yearString = info.substring(6,10);
			String monthString = info.substring(3,5);
			String dayString = info.substring(0,2);
			String hourString = info.substring(11,13);
			String minString = info.substring(14,16);
			
			int year = Integer.parseInt(yearString);
			int month = Integer.parseInt(monthString);
			int day = Integer.parseInt(dayString);
			int hour = Integer.parseInt(hourString);
			int min = Integer.parseInt(minString);
			
			GregorianCalendar newStart = new GregorianCalendar(year,month,day,hour,min);
			
			currentSession.setStartTime(newStart);
			
			modifySession();
		}
		
	}

	private void deleteSession() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		currentSession.delete();
		System.out.println("Session deleted");
		chooseSession();
	}

	//MANGLER DEFAULT SWITCHER
	private void chooseSession() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which of your sessions you would like to modify:");
		Switcher switcher = new Switcher();
		List<Session> sessions = currentUser.getRegisteredSessions();
		
		System.out.println("0 - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				mySessions();
			}
		});
		
		//Create cases
		for(int i=0; i<sessions.size(); i++) {
			Session session = sessions.get(i);
			GregorianCalendar end = session.getEndTime();
			
			System.out.println((i+1) + " - " + session.getActivity().getId() + ", " + end.get(GregorianCalendar.DAY_OF_MONTH) + "/" + end.get(GregorianCalendar.MONTH) + "/" + end.get(GregorianCalendar.YEAR));
			
			switcher.addCaseCommand((i+1), new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentSession = session;
					modifySession();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}

	//MANGLER KONTROL CHECKS!!
	private void registerSession(boolean underActivity) throws OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, AdminNotFoundException, DeveloperNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Register a session by entering the following and pressing enter: ");
		System.out.println((underActivity ? "" : "{activityID} ") + "{startdatetime} {enddatetime}");
		System.out.println("The dates have the following syntax:");
		System.out.println("DD/MM/YYYY HH-MM");
		
		input.nextLine();
		String sessionInfo = input.nextLine();
		
		//Check if input is correct
		if(sessionInfo.length() != 40 && sessionInfo.length() != 35 && sessionInfo.length() != 30) {registerSession(underActivity);}
		
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
			if(underActivity) {
				activityMenu();
			}
			else mySessions();
		}
		
	}
	
	//MANGLER DEFAULT SWITCHER
	private void myProjects() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which of your projects you would like to access:");
		Switcher switcher = new Switcher();
		List<Project> projects = currentUser.getProjects();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				if(currentUser.isAdmin()) WelcomePageAdmin();
				else WelcomePageDeveloper();
			}
		});
		
		//Create cases
		for(int i=0; i<projects.size(); i++) {
			Project project = projects.get(i);
			
			System.out.println((i+1) + " - " + project.getProjectNumber());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentProject = project;
					projectMenu();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}
	
	//MANGLER AT BLIVE LAVET
	public void projectMenu() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		
		System.out.println("Welcome to project " + currentProject.getProjectNumber() + "!");
		System.out.println("0 - Back");
		System.out.println("1 - My Activities");
		
		if(currentProject.isProjectLeader(currentUser) || currentUser.isAdmin()) {
			System.out.println("2 - Manage Project");
		}
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				myProjects();
				break;
			case 1:
				myActivities();
				break;
			case 2:
				if(currentProject.isProjectLeader(currentUser) || currentUser.isAdmin()) {
					manageProject();
				}
				break;
			default:
				projectMenu();
		}
	}
	

	private void manageProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		
		System.out.println("Welcome to Manage Project!");
		System.out.println("Here you can manage developers or activities,");
		System.out.println("on the current project. Choose wisely");
		System.out.println("0 - Back");
		System.out.println("1 - Manage Developers");
		System.out.println("2 - Manage Activities");
		
		
		
		int choice = input.nextInt();
		
		switch(choice) {
		case 0:
			projectMenu();
			break;
		case 1:
			manageDevelopers();
			break;
		case 2:
			manageActivities();
			break;
		default:
			manageProject();
		}
	}
	
	//MANGLER DEFAULT SWITCHER
	private void myActivities() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which of your projects you would like to access:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentUser.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				projectMenu();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentActivity = activity;
					activityMenu();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}
	
	private void manageDevelopers() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Welcome to Manage Developers on the current project!");
		System.out.println("0 - Back");
		System.out.println("1 - Add developer to the project");
		System.out.println("2 - Remove developer from the project");
		System.out.println("3 - Add developer to activities");
		System.out.println("4 - Remove developer from activities");
		System.out.println("5 - See available developers");
		
		int choice = input.nextInt();
		
		switch(choice) {
		case 0:
			manageProject();
			break;
		case 1:
			addDevelopersToProject();
			break;
		case 2:
			removeDevelopersFromProject();
			break;
		case 3:
			addDeveloperToActivity();
			break;
		case 4:
			chooseActivityToRemoveDeveloperFromActivity();
			break;	
		case 5:
			seeAvailableDevelopers();
			break;
		default:
			manageDevelopers();
		}
	}
	
	public void addDevelopersToProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose a developer to add to the current project!");
		Switcher switcher = new Switcher();
		List<Developer> developers = database.getAllDevelopers();
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentProject.assignDeveloperToProject(currentUser, developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	
	public void removeDevelopersFromProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which developer from your project you would like to remove:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentProject.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentProject.removeDeveloperFromProject(developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void addDeveloperToActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which developer from your project you would like to add:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentProject.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				chooseActivityToAddDeveloperToActivity();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentActivity.assignDeveloperToActivity(currentUser, developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void removeDeveloperFromActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose which developer from your activity you would like to remove:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentActivity.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				chooseActivityToRemoveDeveloperFromActivity();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentActivity.removeDeveloperFromActivity(developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void chooseActivityToRemoveDeveloperFromActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose the activity from your project, where you want to remove a developer:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentProject.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentActivity = activity;
					removeDeveloperFromActivity();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void chooseActivityToAddDeveloperToActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Choose the activity from your project, where you want to add a developer:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentProject.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
					currentActivity = activity;
					addDeveloperToActivity();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void seeAvailableDevelopers() {
		
	}
	
	private void manageActivities() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Welcome to Manage Activities!");
		System.out.println("Here you can manage activities,");
		System.out.println("on the current project. Choose wisely");
		System.out.println("0 - Back");
		System.out.println("1 - Manage Developers");
		System.out.println("2 - Manage Activities");
		
		
		
		int choice = input.nextInt();
		
		switch(choice) {
		case 0:
			manageProject();
			break;
		case 1:
			createActivity();
			break;
		case 2:
			deleteActivity();
			break;
		case 3:
			getSummary();
		default:
			manageActivities();
		}
	}
	
	private void createActivity() throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Enter the number of the activity you would like to create:");
		
		int number = input.nextInt();
		if(currentProject.containsActivityWithId(number)) {
			System.out.println("An activity with the given number already exists");
			createActivity();
		}
		else {
		currentProject.createActivity(number, currentUser);
		manageActivities();
		}
	}
	
	private void deleteActivity() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Enter the number of the activity you would like to delete:");
		
		int number = input.nextInt();
		boolean activityFound = false;
		
		
		if(currentProject.containsActivityWithId(number)){			
				activityFound = true;
				currentProject.deleteActivity(number, currentUser);
		}
		
		if(!activityFound) {
			System.out.println("Project Not Found");
			deleteActivity();
		}
		else {
			manageActivities();
		}
		
	}
		
	private void getSummary() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
	
	}
	
	
	private void activityMenu() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Welcome to activity " + currentActivity.getId() + "!");
		System.out.println("0 - Back");
		System.out.println("1 - Register Session In " + currentActivity.getId());
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				myActivities();
				break;
			case 1:
				registerSession(true);
				break;
			default:
				activityMenu();
		}
	}

	private void manageProjects() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Welcome to Project Management!");
		System.out.println("Here you can create, delete, or assign a leader to projects...");
		System.out.println("What do you want to do?");
		System.out.println("0 - Back");
		System.out.println("1 - Create Project");
		System.out.println("2 - Delete Project");
		System.out.println("3 - Assign Project Leader to Project");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				WelcomePageAdmin();
				break;
			case 1:
				createProject();
				break;
			case 2:
				deleteProject();
				break;
			case 3:
				assignProjectLeader();
				break;
			default:
				manageProjects();
		}
	}
	
	//MANGLER KONTROL CHECKS!!

	private void assignProjectLeader() throws NumberFormatException, DeveloperNotFoundException, NotAuthorizedException, ProjectNotFoundException, AdminNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectAlreadyExistsException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("To assign a project leader, ");
		System.out.println("give the number of the project and the employee id:");
		System.out.println("(Separated by a single space)");
		
		input.nextLine();
		String info = input.nextLine();
		
		String numberString = info.substring(0,6);
		String idString = info.substring(7,11);
		
		database.getProjectById(Integer.parseInt(numberString)).setProjectLeader(currentUser, database.getDeveloperById(idString));
		
		manageProjects();
		
	}

	private void deleteProject() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Enter the number of the project you would like to delete:");
		
		int number = input.nextInt();
		boolean projectFound = false;
		
		for(Project project : database.getAllProjects()) {			
			if(project.getProjectNumber() == number) {
				database.deleteProject(number);
				projectFound = true;
			}
		}
		
		if(!projectFound) {
			System.out.println("Project Not Found");
			deleteProject();
		}
		else {
			manageProjects();
		}
		
	}
	
	//MANGLER KONTROL CHECKS
	private void createProject() throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Enter the number of the project you would like to create:");
		
		int number = input.nextInt();
		
		Project project = new Project(number, (Admin) currentUser);
		database.createProject(project);
		project.assignDeveloperToProject(currentUser, currentUser);
		
		manageProjects();
	}
	
	private void manageEmployees() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Welcome to Employee management!");
		System.out.println("Here you can create or delete employees...");
		System.out.println("0 - Back");
		System.out.println("1 - Create Employee");
		System.out.println("2 - Delete Employee");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				WelcomePageAdmin();
				break;
			case 1:
				createEmployee();
				break;
			case 2:
				deleteEmployee();
				break;
			default:
				manageEmployees();
		}
	}
	
	//MANGLER KONTROL CHECKS

	private void deleteEmployee() throws AdminNotFoundException, NumberFormatException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Here you can delete an employee!");
		System.out.println("Enter the id of the employee you wish to delete:");
		
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			database.deleteAdmin(id);
		}
		else database.deleteDeveloper(id);
		
		manageEmployees();
	}

	private void createEmployee() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException {
		System.out.println("Here you can create an employee!");
		System.out.println("Enter the id (4 letters) of the employee you wish to create,");
		System.out.println("and if you wish it to be an admin, add admin after:");
		
		input.nextLine();
		String info = input.nextLine();
		
		String id = info.substring(0,4);
		boolean isAdmin = false;
		
		if(info.length() > 4) {
			if(info.length() == 10) {
				if(info.substring(5,10).equals("admin")) {
					isAdmin = true;
				}
				else createEmployee();
			}
			else createEmployee();
		}
		if(info.length() < 4) {
			createEmployee();
		}
		else {			
			if(isAdmin) {
				database.createAdmin(id);
			}
			else database.createDeveloper(new Developer(id, database));
			
			manageEmployees();
		}
		
		
	}

	public void WelcomePageDeveloper() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException {
		clearScreen();
		System.out.println("Welcome " + currentUser.getId() + "!");
		System.out.println("What do you want to do?");
		System.out.println("0 - Log out");
		System.out.println("1 - My Projects");
		System.out.println("2 - My Sessions");
		
		int choice = input.nextInt();
		
		switch(choice) {
			case 0:
				loginSequence();
				break;
			case 1:
				myProjects();
				break;
			case 2:
				mySessions();
				break;
			default:
				WelcomePageDeveloper();
		}
	}
}
