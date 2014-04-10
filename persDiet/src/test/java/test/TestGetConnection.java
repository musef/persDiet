package test;

import static org.junit.Assert.*;

import org.junit.Test;

import control.GetConnection;

/**
 * The JUnit test for GetConnection
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestGetConnection {

	@Test
	public void testOKNewConnection() {
		GetConnection gc=new GetConnection();
		assertNotNull( gc.newConnection());
	}

}
