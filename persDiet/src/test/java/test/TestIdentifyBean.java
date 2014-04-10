package test;

import static org.junit.Assert.*;

import model.DietPersonalData;
import model.DietUsers;

import org.junit.Test;

import control.DietUsersBean;
import control.IdentifyBean;

/**
 * The JUnit test for IdentifyBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestIdentifyBean {

	private String loginTest="ESCOMBRO";
	private String passTest="HUMANO";

	@Test
	public void testOKCheckIdentification() {
		
		long ident=preparingConditions();
		
		IdentifyBean id=new IdentifyBean();
		id.setLoginUser(loginTest);
		id.setPassUser(passTest);
		assertEquals("Identification OK", "mainDiet.xhtml", id.checkIdentification());
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBADCheckIdentification() {
		
		IdentifyBean id=new IdentifyBean();
		id.setLoginUser("BLABLABLA");
		id.setPassUser("ASDFSADF");
		assertEquals("Identification Wrong", "badLogin.xhtml", id.checkIdentification());
	}
	
	@Test
	public void testNULLCheckIdentification() {
		
		IdentifyBean id=new IdentifyBean();
		id.setLoginUser(null);
		id.setPassUser(null);
		assertEquals("Identification null", "badLogin.xhtml", id.checkIdentification());
	}
	
	@Test
	public void testEMPTYCheckIdentification() {
		
		IdentifyBean id=new IdentifyBean();
		id.setLoginUser("");
		id.setPassUser("");
		assertEquals("Identification empty", "badLogin.xhtml", id.checkIdentification());
	}
	
	
	private long preparingConditions() {
		
		DietUsersBean fb=new DietUsersBean();
		DietUsers user=new DietUsers();
		DietPersonalData pdata=new DietPersonalData();
		
		// creating a record to test
		pdata.setKeyuser("1234567890");
		pdata.setName("MANOLO PEREZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(35);		
		user.setKeyuser("1234567890");
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		fb.record(user);
		
		// getting a user by keyUser
		String[] str=fb.read("1234567890");
		long ident=0;
		if (str==null) {
			fail("Error en test testOKDelete()");
		}
		// getting the id of this user
		ident=(long)Long.parseLong(str[0]);
		
		return ident;
	}
	
	
	private void cleaningConditions(long id) {
		
		DietUsersBean fb=new DietUsersBean();

		fb.delete(id);
	}

}
