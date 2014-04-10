package test;

import static org.junit.Assert.*;

import java.util.List;

import model.DietPersonalData;
import model.DietUsers;

import org.junit.Test;

import control.DietUsersBean;


/**
 * The JUnit test for DietUsersBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestDietUsersBean {

	DietUsersBean fb=new DietUsersBean();
	DietUsers user=new DietUsers();
	DietPersonalData pdata=new DietPersonalData();
	String keyUser="123ABCDEF123456";
	String loginTest="SISEBUTO";
	String passTest="12345678";
	List<String[]> control;
	
	
	
	// ************* testing record method
	
	@Test
	public void testNullRecord() {
		//testing a null user
		assertFalse(fb.record(null));
	}
	
	@Test
	public void testWrongRecord() {
		// testing record a wrong object
		Object obj=new Object();
		assertFalse(fb.record(obj));
	}

	@Test
	public void testEmptyRecord() {
		// testing a empty user
		user=new DietUsers();		
		assertFalse(fb.record(user));
	}
	
	@Test
	public void testOKRecord() {
		
		// testing record a user
		pdata.setKeyuser(keyUser);
		pdata.setName("MANOLO PEREZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(35);
		
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		assertTrue(fb.record(user));
		
		// DELETING THE RECORD
		// getting a user by keyUser
		String[] str=fb.read(keyUser);
		long ident=0;
		if (str==null) {
			fail("Error en test testOKDelete()");
		}
		// getting the id of this user
		ident=(long)Long.parseLong(str[0]);
		fb.delete(ident);
		
	}	
	
	// ******** testing delete method
	
	@Test
	public void testWrongDelete() {
		
		long ident=99999;
		
		assertFalse("deleting wrong id",fb.delete(ident));
		
	}
	
	@Test
	public void testOKDelete() {
		
		long ident=preparingConditions();
		
		assertTrue("delete record OK",fb.delete(ident));
		
	}
	
	// ****************** testing read methods
	
	@Test
	public void testOKCheckExistingLogin() {
		long ident=preparingConditions();
		assertTrue("checking existing user OK", fb.checkLogin(loginTest));
		cleaningConditions(ident);
	}
	
	@Test
	public void testOKChecknewLogin() {
		long ident=preparingConditions();
		String login="blablabla";
		assertFalse("checking new user OK", fb.checkLogin(login));
		cleaningConditions(ident);
	}
	
	@Test
	public void testCheckNullLogin() {
		long ident=preparingConditions();
		String login=null;
		assertTrue("checking null user", fb.checkLogin(login));
		cleaningConditions(ident);
	}	
	
	@Test
	public void testCheckEmptyLogin() {
		long ident=preparingConditions();
		String login="";
		assertTrue("checking empty user", fb.checkLogin(login));
		cleaningConditions(ident);
	}
	
		
	@Test
	public void testReadOKbyKeyUser() {
		long ident=preparingConditions();
		assertNotNull("read by keyUser OK", fb.read(keyUser));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadWrongbyKeyUser() {
		long ident=preparingConditions();
		assertNull("read by a wrong keyUser", fb.read("BLABLABLA"));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadNullbyKeyUser() {
		long ident=preparingConditions();
		assertNull("read by a null keyUser", fb.read(null));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadEmptybyKeyUser() {
		long ident=preparingConditions();
		assertNull("read by a Empty keyUser", fb.read(""));
		cleaningConditions(ident);
	}
	
	
	@Test
	public void testReadOKbyLoginPass() {
		long ident=preparingConditions();
		assertNotNull("read by login-pass OK", fb.read(loginTest, passTest));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadWrongbyLoginPass() {
		long ident=preparingConditions();
		assertNull("read by a wrong login-pass", fb.read("BLABLABLA","blabla"));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadNullgbyLoginPass() {
		long ident=preparingConditions();
		assertNull("read by a null login-pass", fb.read(null,null));
		cleaningConditions(ident);
	}
	
	@Test
	public void testReadEmptygbyLoginPass() {
		long ident=preparingConditions();
		assertNull("read by a Empty login-pass", fb.read("",""));
		cleaningConditions(ident);
	}	
	
	
	@Test
	public void testReadOKbyIdent() {
		
		long ident=preparingConditions();
		assertNotNull("read by ident OK", fb.read(ident));
		cleaningConditions(ident);
		
	}
	
	@Test
	public void testReadWrongbyIdent() {
		long ident=preparingConditions();
		assertNull("read by a wrong ident", fb.read(99999));
		cleaningConditions(ident);
	}
	
	
	// *************** testing modify methods
	
	@Test
	public void testModifyLongObjectOK() {
		
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName("ANDRES LOPEZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertTrue("modify by ident OK", fb.modify(ident, user));
		cleaningConditions(ident);
		
	}
	
	@Test
	public void testModifyLongObjectWrong1() {
			
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName("ANDRES LOPEZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);		
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertFalse("modify by wrong ident", fb.modify(99999, user));
		cleaningConditions(ident);
		
	}

	@Test
	public void testModifyLongObjectWrong2() {
		
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName(null);
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		
		user.setKeyuser(keyUser);
		user.setLogin(null);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertFalse("modify by null fields object", fb.modify(ident, user));
		cleaningConditions(ident);
		
	}
	
	
	@Test
	public void testModifyLongObjectWrong3() {
		
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser("");
		pdata.setName("SANCHO PANZA");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass("");
		user.setPdata(pdata);
		
		assertFalse("modify by empty fields object", fb.modify(ident, user));
		cleaningConditions(ident);
		
	}
	
	@Test
	public void testModifyLongObjectWrong4() {
		
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=null;
		
		assertFalse("modify by null object", fb.modify(ident, user));
		cleaningConditions(ident);
	}
	
	@Test
	public void testModifyStringObjectOK() {

		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName("SAPOROVNI BUTIKI");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertTrue("modify by ident OK", fb.modify(keyUser, user));
		cleaningConditions(ident);
		
	}
	
	@Test
	public void testModifyStringObjectWrong1() {
			
		long ident=preparingConditions();
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName("ANDRES LOPEZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertFalse("modify by wrong key user", fb.modify("blablabla", user));
		cleaningConditions(ident);
		
	}

	@Test
	public void testModifyStringObjectWrong2() {
		
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser(keyUser);
		pdata.setName(null);
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);	
		user.setKeyuser(keyUser);
		user.setLogin(null);
		user.setPass(passTest);
		user.setPdata(pdata);
		
		assertFalse("modify by null fields object", fb.modify(keyUser, user));
		cleaningConditions(ident);
	}
	
	
	@Test
	public void testModifyStringObjectWrong3() {
				
		long ident=preparingConditions();
		
		// creating a DietUsers
		user=new DietUsers();
		pdata=new DietPersonalData();
		
		pdata.setKeyuser("");
		pdata.setName("ALDO MORRO");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(55);
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass("");
		user.setPdata(pdata);
		
		assertFalse("modify by empty fields object", fb.modify(keyUser, user));
		cleaningConditions(ident);
	}
	
	@Test
	public void testModifyStringObjectWrong4() {
				
		long ident=preparingConditions();
		// creating a DietUsers
		user=null;
		
		assertFalse("modify by null object", fb.modify(keyUser, user));
		cleaningConditions(ident);
		
	}	
	
	
	/*
		// TEST ARE CREATED, BUT .... THE METHOD IS NOT IMPLEMENTED YET
	
	// testing list showAll method
	
		@Test
		public void testOKShowAll() {
			
			pdata.setKeyuser(keyUser);
			pdata.setName("MANOLO PEREZ");
			pdata.setWeight(85);
			pdata.setHeight(180);
			pdata.setAge(35);
			
			user.setKeyuser(keyUser);
			user.setLogin("MANOLO");
			user.setPass("123456");
			user.setPdata(pdata);

			fb.record(user);
		
			assertNotNull("Listado OK", fb.showAll(keyUser));
			
		}

		@Test
		public void testNullkeyuserShowAll() {
			
			pdata.setKeyuser(keyUser);
			pdata.setName("MANOLO PEREZ");
			pdata.setWeight(85);
			pdata.setHeight(180);
			pdata.setAge(35);
			
			user.setKeyuser(keyUser);
			user.setLogin("MANOLO");
			user.setPass("123456");
			user.setPdata(pdata);

			fb.record(user);
			// not controlled by default
			assertNotNull("Listado null user", fb.showAll(null));
		}
		
		@Test
		public void testWrongkeyuserShowAll() {
			
			pdata.setKeyuser(keyUser);
			pdata.setName("MANOLO PEREZ");
			pdata.setWeight(85);
			pdata.setHeight(180);
			pdata.setAge(35);
			
			user.setKeyuser(keyUser);
			user.setLogin("MANOLO");
			user.setPass("123456");
			user.setPdata(pdata);
			fb.record(user);
			// not controlled by default
			assertNotNull("Listado wrong user", fb.showAll("BLABLABLA"));
		}


		@Test
		public void testEmptykeyuserShowAll() {
			
			pdata.setKeyuser(keyUser);
			pdata.setName("MANOLO PEREZ");
			pdata.setWeight(85);
			pdata.setHeight(180);
			pdata.setAge(35);
			
			user.setKeyuser(keyUser);
			user.setLogin("MANOLO");
			user.setPass("123456");
			user.setPdata(pdata);
			fb.record(user);
			// not controlled by default
			assertNotNull("Listado empty user", fb.showAll(""));
		}
		
*/

	private long preparingConditions() {
		
		// creating a record to test
		pdata.setKeyuser(keyUser);
		pdata.setName("MANOLO PEREZ");
		pdata.setWeight(85);
		pdata.setHeight(180);
		pdata.setAge(35);		
		user.setKeyuser(keyUser);
		user.setLogin(loginTest);
		user.setPass(passTest);
		user.setPdata(pdata);
		fb.record(user);
		
		// getting a user by keyUser
		String[] str=fb.read(keyUser);
		long ident=0;
		if (str==null) {
			fail("Error en test testOKDelete()");
		}
		// getting the id of this user
		ident=(long)Long.parseLong(str[0]);
		
		return ident;
	}
	
	
	private void cleaningConditions(long id) {
		fb.delete(id);
	}
	
}
