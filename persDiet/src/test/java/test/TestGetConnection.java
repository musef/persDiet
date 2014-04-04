package test;

import static org.junit.Assert.*;

import org.junit.Test;

import control.GetConnection;

public class TestGetConnection {

	

	@Test
	public void testOKNewConnection() {
		GetConnection gc=new GetConnection();
		assertNotNull( gc.newConnection());
	}

}
