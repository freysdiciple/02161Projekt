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
import Exceptions.OutOfBoundsException;
import Exceptions.OverlappingSessionsException;
import Exceptions.ProjectAlreadyExistsException;
import Exceptions.ProjectNotFoundException;
import SoftwareAS.Model.*;

public class FrontEndController {
	
	private DataBase database;
	private Scanner input;
	private Developer currentUser;
	private Admin currentAdmin;
	private Activity currentActivity;
	private Project currentProject;
	private Session currentSession;
	
	//ALLE MANGLER KONTROL FOR HVORVIDT DER BLIVER GIVET TAL
	
	public FrontEndController(DataBase database) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	public void loginSequence() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Please Enter Your ID to login:");
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			currentUser = database.getAdminById(id);
			currentAdmin = database.getAdminById(id);
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
	
	public void WelcomePageAdmin() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	private void mySessions() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	private void modifySession() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	private void changeEndTime() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		System.out.println("Give the new desired end date and time in the form,");
		System.out.println("DD/MM/YYYY HH-MM,");
		System.out.println("or enter 0 to go back");
		
		input.nextLine();
		String info = input.nextLine();
		
		if(info.length() == 1 && Integer.parseInt(info.substring(0, 1)) == 0) {
			modifySession();
		}
		else if(info.length() != 16)  {
			changeEndTime();
		}
		else {
			try {
				GregorianCalendar newEnd = InputHelper.stringToGregorianCalendar(info);
				
				currentSession.setEndTime(newEnd);
				
				modifySession();
			}catch(Exception e) {
				changeEndTime();
			}
		}
		
		
		
	}

	//MANGLER KONTROL CHECKS
	private void changeStartTime() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		System.out.println("Give the new desired start date and time in the form,");
		System.out.println("DD/MM/YYYY HH-MM,");
		System.out.println("or enter 0 to go back");
		
		input.nextLine();
		String info = input.nextLine();
		
		if(info.length() == 1 && Integer.parseInt(info.substring(0, 1)) == 0) { 
			modifySession();
		}
		else if(info.length() != 16) { 
			changeStartTime();
		}
		else {
			try {
				GregorianCalendar newStart = InputHelper.stringToGregorianCalendar(info);
				
				currentSession.setStartTime(newStart);
				
				modifySession();
			}catch(Exception e) {
				changeStartTime();
			}
		}
		
	}

	private void deleteSession() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		currentSession.delete();
		System.out.println("Session deleted");
		chooseSession();
	}

	//MANGLER DEFAULT SWITCHER
	private void chooseSession() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which of your sessions you would like to modify:");
		Switcher switcher = new Switcher();
		List<Session> sessions = currentUser.getRegisteredSessions();
		
		System.out.println("0 - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentSession = session;
					modifySession();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}

	//MANGLER KONTROL CHECKS!!
	private void registerSession(boolean underActivity) throws OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, AdminNotFoundException, DeveloperNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Register a session by entering the following and pressing enter: ");
		System.out.println((underActivity ? "" : "{activityID} ") + "{startdatetime} {enddatetime}");
		System.out.println("The dates have the following syntax:");
		System.out.println("DD/MM/YYYY HH-MM");
		
		input.nextLine();
		String sessionInfo = input.nextLine();
		
		try {
			Object[] properties = InputHelper.stringToSessionProperties(sessionInfo, currentUser, underActivity? currentActivity : null);
			currentUser.registerSession((Activity) properties[2], (GregorianCalendar)properties[0], (GregorianCalendar)properties[1]);
		}catch(Exception e) {
			registerSession(underActivity);
		}
		
		
	}
	
	//MANGLER DEFAULT SWITCHER
	private void myProjects() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which of your projects you would like to access:");
		Switcher switcher = new Switcher();
		List<Project> projects = currentUser.getProjects();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				if(currentUser.isAdmin()) WelcomePageAdmin();
				else WelcomePageDeveloper();
			}
		});
		
		//Create cases
		for(int i=0; i<projects.size(); i++) {
			Project project = projects.get(i);
			
			System.out.println((i+1) + " - " + project.getProjectName());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentProject = project;
					projectMenu();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}
	
	//MANGLER AT BLIVE LAVET
	public void projectMenu() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		
		System.out.println("Welcome to project " + currentProject.getProjectName() + "!");
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
	

	private void manageProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		
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
	private void myActivities() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which of your projects you would like to access:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentUser.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				projectMenu();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentActivity = activity;
					activityMenu();
				}
			});
		}
		
		int choice = input.nextInt();
		
		switcher.on(choice);
		
	}
	
	private void manageDevelopers() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	public void addDevelopersToProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose a developer to add to the current project!");
		Switcher switcher = new Switcher();
		List<Developer> developers = database.getAllDevelopers();
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentProject.assignDeveloperToProject(currentUser, developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	
	public void removeDevelopersFromProject() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which developer from your project you would like to remove:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentProject.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentProject.removeDeveloperFromProject(developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void addDeveloperToActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which developer from your project you would like to add:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentProject.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				chooseActivityToAddDeveloperToActivity();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentActivity.assignDeveloperToActivity(currentUser, developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void removeDeveloperFromActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose which developer from your activity you would like to remove:");
		Switcher switcher = new Switcher();
		List<Developer> developers = currentActivity.getDevelopers();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				chooseActivityToRemoveDeveloperFromActivity();
			}
		});
		
		//Create cases
		for(int i=0; i<developers.size(); i++) {
			Developer developer = developers.get(i);
			
			System.out.println((i+1) + " - " + developer.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentActivity.removeDeveloperFromActivity(developer);
					manageProject();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void chooseActivityToRemoveDeveloperFromActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose the activity from your project, where you want to remove a developer:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentProject.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentActivity = activity;
					removeDeveloperFromActivity();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	public void chooseActivityToAddDeveloperToActivity() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Choose the activity from your project, where you want to add a developer:");
		Switcher switcher = new Switcher();
		List<Activity> activities = currentProject.getActivities();
		
		System.out.println(0 + " - Back");
		switcher.addCaseCommand(0, new Command() {
			@Override
			public void execute() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
				manageDevelopers();
			}
		});
		
		//Create cases
		for(int i=0; i<activities.size(); i++) {
			Activity activity = activities.get(i);
			
			System.out.println((i+1) + " - " + activity.getId());
			
			switcher.addCaseCommand(i+1, new Command() {
				@Override 
				public void execute() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
					currentActivity = activity;
					addDeveloperToActivity();
				}
			});
		}
		int choice = input.nextInt();
		switcher.on(choice);
	}
	
	//MANGLER AT BLIVE LAVET
	public void seeAvailableDevelopers() {
		
	}
	
	private void manageActivities() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	private void createActivity() throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
	
	private void deleteActivity() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
		
	private void getSummary() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		System.out.println("Enter the number of the activity you would like to get summary of:");
		int number = input.nextInt();
		boolean activityFound = false;
		
		if(currentProject.containsActivityWithId(number)){			
			activityFound = true;
			currentActivity=currentProject.getActivityById(number);
			int startWeek=currentActivity.getStartWeek();
			int endWeek=currentActivity.getEndWeek();
			List<Developer> developers=currentActivity.getDevelopers();
			
			System.out.println("The activity starts in week: " + startWeek);
			System.out.println("The activity ends in week: " + endWeek);
			System.out.println("The following developers are on the project:");
			for (Developer d : developers) {
			System.out.println(d.getId());
			
			}
	
		if(!activityFound) {
			System.out.println("Project Not Found");
			getSummary();
		}
		else {
			manageActivities();
		}
	}
	}
	
	
	private void activityMenu() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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

	private void manageProjects() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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

	private void assignProjectLeader() throws NumberFormatException, DeveloperNotFoundException, NotAuthorizedException, ProjectNotFoundException, AdminNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectAlreadyExistsException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("To assign a project leader, ");
		System.out.println("give the number of the project and the employee id:");
		System.out.println("(Separated by a single space)");
		
		input.nextLine();
		String info = input.nextLine();
		
		String numberString = info.substring(0,6);
		String idString = info.substring(7,11);
		
		database.getProjectByName(numberString).setProjectLeader(currentUser, database.getDeveloperById(idString));
		
		manageProjects();
		
	}

	private void deleteProject() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Enter the name of the project you would like to delete:");
		
		String s = input.next();
		boolean projectFound = false;
		
		for(Project project : database.getAllProjects()) {			
			if(project.getProjectName().equals(s)) {
				database.deleteProject(s);
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
	private void createProject() throws ProjectNotFoundException, ProjectAlreadyExistsException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		clearScreen();
		System.out.println("Enter the name of the project you would like to create");
		System.out.println("The format of the number is YYNNNN");
		System.out.println("YY - Year, NNNN - 4 Digit unique number:");
		String s = input.next();
		
		currentAdmin.createProject(s);
		Project project = database.getProjectByName(s);
		project.assignDeveloperToProject(currentAdmin, currentUser);
		
		manageProjects();
	}
	
	private void manageEmployees() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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

	private void deleteEmployee() throws AdminNotFoundException, NumberFormatException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
		System.out.println("Here you can delete an employee!");
		System.out.println("Enter the id of the employee you wish to delete:");
		
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			database.deleteAdmin(id);
		}
		else database.deleteDeveloper(id);
		
		manageEmployees();
	}

	private void createEmployee() throws NumberFormatException, AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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

	public void WelcomePageDeveloper() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException, ProjectNotFoundException, ProjectAlreadyExistsException, NumberFormatException, NotAuthorizedException, ActivityAlreadyExistsException, OutOfBoundsException {
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
