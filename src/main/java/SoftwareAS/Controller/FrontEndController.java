package SoftwareAS.Controller;

import java.util.Scanner;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import SoftwareAS.Model.*;

public class FrontEndController {
	
	private DataBase database;
	private Scanner input;
	private Developer currentUser;
	private Activity currentActivity;
	private Project currentProject;
	private Session currentSession;
	
	public FrontEndController(DataBase database) throws AdminNotFoundException, DeveloperNotFoundException {
		this.database = database;
		this.input = new Scanner(System.in);
		loginSequence();
	}
	
	public void loginSequence() throws AdminNotFoundException, DeveloperNotFoundException {
		System.out.println("Please Enter Your ID to login:");
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			WelcomePageAdmin(id);
		}
		else if(database.containsDeveloper(id)) {
			WelcomePageDeveloper(id);
		}
		else {
			System.out.println("No employee with the given id. Pleas try again!");
			loginSequence();
		}
	}
	
	public void WelcomePageAdmin(String id) throws AdminNotFoundException, DeveloperNotFoundException {
		System.out.println("Welcome " + id + "!");
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
				manageDevelopers(id);
				break;
			case 2:
				manageProjects(id);
				break;
			case 3:
				myProjects(id, true);
				break;
			case 4:
				mySessions(id, true);
				break;
			default:
				System.out.println("Please enter one of the above numbers");
				WelcomePageAdmin(id);
		}
		
	}
	
	private void mySessions(String id, boolean isAdmin) {
		System.out.println("You have entered your sessions!");
		
	}

	private void myProjects(String id, boolean isAdmin) {
		System.out.println("You have entered your projects!");
		
	}

	private void manageProjects(String id) {
		System.out.println("You have entered manage projects!");
		
	}

	private void manageDevelopers(String id) {
		System.out.println("You have entered manage developers!");
		
	}

	public void WelcomePageDeveloper(String id) {
		System.out.println("You are logged in as developer!");
	}

}
