package Exceptions;

public class ActivityAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 321;
	
	public ActivityAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

}
