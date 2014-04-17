package test;

import static org.junit.Assert.*;

import org.junit.Test;

import control.CreationBean;
import control.DietUsersBean;

/**
 * The JUnit test for CreationBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestCreationBean {

	private String loginTest="CALAMBRE";
	private String passTest="123456";
	
	// testing recordUser() method
	
	@Test
	public void testOKRecordUser() {
		
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO1");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.setSex(2);
		
		assertNotNull("Creating OK new user", cBean.recordUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(loginTest, passTest);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}

	@Test
	public void testNullRecordUser() {
		
		CreationBean cBean=new CreationBean();
		cBean.setLogin(null);
		cBean.setPassword(null);
		cBean.setName(null);
		cBean.setWeight(0);
		cBean.setHeight(0);
		cBean.setAge(0);
		
		assertNull("Creating new user null data", cBean.recordUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(null, null);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	@Test
	public void testEmptyRecordUser() {
		
		CreationBean cBean=new CreationBean();
		cBean.setLogin("");
		cBean.setPassword("");
		cBean.setName("");
		cBean.setWeight(0);
		cBean.setHeight(0);
		cBean.setAge(0);
		
		assertNull("Creating new user empty data", cBean.recordUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read("", "");
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	
	@Test
	public void testTooLongRecordUser() {
		
		CreationBean cBean=new CreationBean();
		cBean.setLogin("aaaaaaaaaaaaaaaaaaa");
		cBean.setPassword("123123123456456456");
		cBean.setName("loquesea");
		cBean.setWeight(50);
		cBean.setHeight(150);
		cBean.setAge(50);
		
		assertNull("Creating new user too long login-pass", cBean.recordUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read("aaaaaaaaaaaaaaaaaaa", "123123123456456456");
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	
	@Test
	public void testTooShortRecordUser() {
		
		CreationBean cBean=new CreationBean();
		cBean.setLogin("aaa");
		cBean.setPassword("123");
		cBean.setName("loquesea");
		cBean.setWeight(50);
		cBean.setHeight(150);
		cBean.setAge(50);
		
		assertNull("Creating new user too short login-pass", cBean.recordUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read("aaa", "123");
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	// testing changeUser() method
	
	@Test
	public void testOKChangeUser() {
		
		// first creating user
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO2");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.setSex(2);
		cBean.recordUser();
		
		// body test
		cBean.setLogin("OTRACOSA");
		cBean.setPassword("MARIPOSA");
		cBean.setName("LA MARTILLEANTE");
		cBean.setWeight(80);
		cBean.setHeight(180);
		cBean.setAge(80);
		assertTrue("Creating OK new user", cBean.changeUser());
		
		// deleting user
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read("OTRACOSA", "MARIPOSA");
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
		
	}

	@Test
	public void testNullChangeUser() {
		
		// first creating user
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO3");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.recordUser();
		
		// body test
		cBean.setLogin(null);
		cBean.setPassword(null);
		cBean.setName(null);
		cBean.setWeight(0);
		cBean.setHeight(0);
		cBean.setAge(0);
		
		assertFalse("Creating new user null data", cBean.changeUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(loginTest, passTest);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	@Test
	public void testEmptyChangeUser() {
		
		// first creating user
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO4");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.recordUser();
		
		// body test
		cBean.setLogin("");
		cBean.setPassword("");
		cBean.setName("");
		cBean.setWeight(0);
		cBean.setHeight(0);
		cBean.setAge(0);
		
		assertFalse("Creating new user empty data", cBean.changeUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(loginTest, passTest);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	
	@Test
	public void testTooLongChangeUser() {
		
		// first creating user
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO5");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.recordUser();
		
		// body test
		cBean.setLogin("aaaaaaaaaaaaaaaaaaa");
		cBean.setPassword("123123123456456456");
		cBean.setName("loquesea");
		cBean.setWeight(50);
		cBean.setHeight(150);
		cBean.setAge(50);
		
		assertFalse("Creating new user too long login-pass", cBean.changeUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(loginTest, passTest);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}
	
	
	@Test
	public void testTooShortChangeUser() {
		
		// first creating user
		CreationBean cBean=new CreationBean();
		cBean.setLogin(loginTest);
		cBean.setPassword(passTest);
		cBean.setName("LA MARTILLO6");
		cBean.setWeight(85);
		cBean.setHeight(185);
		cBean.setAge(85);
		cBean.recordUser();
		
		// body test
		cBean.setLogin("aaa");
		cBean.setPassword("123");
		cBean.setName("loquesea");
		cBean.setWeight(50);
		cBean.setHeight(150);
		cBean.setAge(50);
		
		assertFalse("Creating new user too short login-pass", cBean.changeUser());
		
		// deleting record
		DietUsersBean db=new DietUsersBean();
		String[] str=db.read(loginTest, passTest);
		if (str!=null) {
			db.delete((long)Long.parseLong(str[0]));
		}
	}

}
