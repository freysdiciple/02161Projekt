package Exceptions;

public class OverlappingSessionsException extends Exception {
	
	private static final long serialVersionUID = 318;
	
	public OverlappingSessionsException(String errorMessage) {
		super(errorMessage);
	}

}
