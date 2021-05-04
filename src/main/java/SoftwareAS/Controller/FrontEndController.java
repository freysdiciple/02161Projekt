package SoftwareAS.Controller;

import java.util.Scanner;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import SoftwareAS.Model.*;

public class FrontEndController {
	
	private DataBase database;
	private Scanner input;
	private Developer currentUser;
	
	public FrontEndController(DataBase database) throws AdminNotFoundException, DeveloperNotFoundException {
		this.database = database;
		this.input = new Scanner(System.in);
		loginSequence();
	}
	
	public void loginSequence() throws AdminNotFoundException, DeveloperNotFoundException {
		System.out.println("Please Enter Your ID to login:");
		String id = input.next();
		
		if(database.containsAdmin(id)) {
			loginAsAdmin();
		}
		else if(database.containsDeveloper(id)) {
			loginAsDeveloper();
		}
		else {
			System.out.println("No employee with the given id. Pleas try again!");
			loginSequence();
		}
	}
	
	public void loginAsAdmin() {
		System.out.println("You are logged in as admin!");
	}
	
	public void loginAsDeveloper() {
		System.out.println("You are logged in as admin!");
	}

}
