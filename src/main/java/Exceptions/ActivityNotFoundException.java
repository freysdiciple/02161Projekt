package Exceptions;

public class ActivityNotFoundException extends Exception{
	
	private static final long serialVersionUID = 313;
	
	public ActivityNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
