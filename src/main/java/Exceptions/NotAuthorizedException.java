package Exceptions;

public class NotAuthorizedException extends Exception{
	
	private static final long serialVersionUID = 320;
	
	public NotAuthorizedException(String errorMessage) {
		super(errorMessage);
	}

}
