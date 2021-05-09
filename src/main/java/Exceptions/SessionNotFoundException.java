package Exceptions;

public class SessionNotFoundException extends Exception{
	
	private static final long serialVersionUID = 324;
	
	public SessionNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
