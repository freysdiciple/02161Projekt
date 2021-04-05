package Exceptions;

public class OperationNotAllowedException extends Exception{
	
	private static final long serialVersionUID = 314;
	
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}
