package SoftwareAS.Controller;

import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import SoftwareAS.Model.DataBase;

public class SoftwareAS {
	
	static DataBase database = new DataBase();
	static FrontEndController frontEnd;
	
	public static void main(String[] args) throws AdminNotFoundException, DeveloperNotFoundException {
		database = new DataBase();
		database.createAdmin("admin1");
		
		initiateFrontEnd();
	}
	
	public DataBase getDataBase() {
		return database;
	}
	
	public static void initiateFrontEnd() throws AdminNotFoundException, DeveloperNotFoundException {
		frontEnd = new FrontEndController(database);
	}

}
