package Exceptions;

public class ProjectAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 319;
	
	public ProjectAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

}
