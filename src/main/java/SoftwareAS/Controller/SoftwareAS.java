package SoftwareAS.Controller;

import Exceptions.ActivityNotFoundException;
import Exceptions.AdminNotFoundException;
import Exceptions.DeveloperNotFoundException;
import Exceptions.OperationNotAllowedException;
import Exceptions.OverlappingSessionsException;
import SoftwareAS.Model.DataBase;

public class SoftwareAS {
	
	static DataBase database = new DataBase();
	static FrontEndController frontEnd;
	
	public static void main(String[] args) throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		database = new DataBase();
		database.createAdmin("admin1");
		
		initiateFrontEnd();
	}
	
	public DataBase getDataBase() {
		return database;
	}
	
	public static void initiateFrontEnd() throws AdminNotFoundException, DeveloperNotFoundException, OperationNotAllowedException, OverlappingSessionsException, ActivityNotFoundException {
		frontEnd = new FrontEndController(database);
	}

}
