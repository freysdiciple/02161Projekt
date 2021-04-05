package Exceptions;

public class DeveloperNotFoundException extends Exception{
	
	private static final long serialVersionUID = 310;
	
	public DeveloperNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
