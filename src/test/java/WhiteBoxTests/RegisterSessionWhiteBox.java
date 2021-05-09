package WhiteBoxTests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import Exceptions.*;
import SoftwareAS.Controller.ErrorMessageHolder;
import SoftwareAS.Model.*;

public class RegisterSessionWhiteBox {
	
	ErrorMessageHolder emh = new ErrorMessageHolder();
	
	Activity activity;
	Developer developer;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
	
	public void PathA() throws OperationNotAllowedException, OverlappingSessionsException {
		activity = new Activity();
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		start.add(GregorianCalendar.DAY_OF_YEAR, 2);
		
		developer = new Developer();
		developer.registerSession(activity, start, end);
	}
	
	public void PathB() throws OperationNotAllowedException, OverlappingSessionsException {
		activity = new Activity();
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		start.add(GregorianCalendar.DAY_OF_YEAR, 2);
		
		developer = new Developer();
		developer.registerSession(activity, start, end);
		
		GregorianCalendar start2 = new GregorianCalendar();
		GregorianCalendar end2 = new GregorianCalendar();
		start2.add(GregorianCalendar.DAY_OF_YEAR, 1);
		end2.add(GregorianCalendar.DAY_OF_YEAR, 1);
		
		developer.registerSession(activity, start2, end2);
	}
	
	public void PathC() throws OperationNotAllowedException, OverlappingSessionsException {
		activity = null;
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		start.add(GregorianCalendar.DAY_OF_YEAR, 2);
		
		developer = new Developer();
		developer.registerSession(activity, start, end);
	}
	
	public void PathD() throws OperationNotAllowedException, OverlappingSessionsException {
		activity = new Activity();
		start = new GregorianCalendar();
		end = new GregorianCalendar();
		end.add(GregorianCalendar.DAY_OF_YEAR, 2);
		
		developer = new Developer();
		developer.registerSession(activity, start, end);
	}
	
	public void TestA() throws OverlappingSessionsException {
		try {
			PathA();
		}catch(OperationNotAllowedException e) {
			emh.setErrorMessage(e.getMessage());
		}
		
		assertEquals(emh.getErrorMessage(), "A session cannot end before it starts...");
	}
	
	public void TestB() throws OperationNotAllowedException {
		try {
			PathB();
		}catch(OverlappingSessionsException e) {
			emh.setErrorMessage(e.getMessage());
		}
		
		assertEquals(emh.getErrorMessage(), "Overlapping Sessions");
	}
	
	public void TestC() throws OperationNotAllowedException, OverlappingSessionsException {
		PathC();
		
		assertEquals(developer.getRegisteredSessions().get(0).getStartTime(), start);
		assertEquals(developer.getRegisteredSessions().get(0).getEndTime(), end);
	}
	
	public void TestD() throws OperationNotAllowedException, OverlappingSessionsException {
		PathD();
		
		assertEquals(developer.getRegisteredSessions().get(0).getStartTime(), start);
		assertEquals(developer.getRegisteredSessions().get(0).getEndTime(), end);
		assertEquals(activity.getRegisteredSession().get(0).getStartTime(), start);
		assertEquals(activity.getRegisteredSession().get(0).getEndTime(), end);
	}
	
	
	
	
	
}
