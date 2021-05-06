package Exceptions;

public class OutOfBoundsException extends Exception{
	
	private static final long serialVersionUID = 322;
	
	public OutOfBoundsException(String errorMessage) {
		super(errorMessage);
	}

}