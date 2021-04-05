package Exceptions;

public class ProjectNotFoundException extends Exception{
	
	private static final long serialVersionUID = 312;
	
	public ProjectNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
