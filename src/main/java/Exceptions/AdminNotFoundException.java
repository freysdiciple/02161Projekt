package Exceptions;

public class AdminNotFoundException extends Exception{
	
	private static final long serialVersionUID = 311;
	
	public AdminNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
