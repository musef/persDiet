package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import model.DietCalendar;
import control.DietCalendarBean;


/**
 * The JUnit test for DietCalendarBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestDietCalendarBean {

	
	DietCalendarBean fb=new DietCalendarBean();
	DietCalendar cal=new DietCalendar();
	String keyUser="ABCDEF123456ABC";
	List<String[]> control;
	Date datePr=Date.valueOf("2014-01-01");

	
	
	// **** testing delete method

	@Test
	public void testUnknowDelete() {
		long iden=999999;		
		assertFalse("deleting false iden",fb.delete(iden));
	}
	
	@Test
	public void testOKDelete() {
		
		long iden=preparingConditions();
		
		assertTrue("deleting ok",fb.delete(iden));
	}
	

		
	// **** testing list showAll method
	
	@Test
	public void testOKShowAll() {
		
		long ident=preparingConditions();
		
		assertNotNull("Listado OK", fb.showAll(keyUser));
		
		cleaningConditions(ident);
	}

	@Test
	public void testNullkeyuserShowAll() {
			
		assertNull("Listado null user", fb.showAll(null));
	}
	
	@Test
	public void testWrongkeyuserShowAll() {
		
		assertNull("Listado wrong user", fb.showAll("BLABLABLe"));
	}


	@Test
	public void testEmptykeyuserShowAll() {
		
		assertNull("Listado empty user", fb.showAll(""));
	}
	
	@Test
	public void testOKShowAll_Dates() {
		
		long ident=preparingConditions();
		String d1="2014-01-01";
		String d2="2014-12-01";
		
		assertNotNull("Listado OK", fb.showAll(keyUser,d1,d2));
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBAD1ShowAll_Dates() {
		
		long ident=preparingConditions();
		
		assertNull("Listado fechas null", fb.showAll(keyUser,null,null));
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBAD2ShowAll_Dates() {
		
		long ident=preparingConditions();
		
		assertNull("Listado fechas empty", fb.showAll(keyUser,"",""));
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBAD3ShowAll_Dates() {
		
		long ident=preparingConditions();
		
		assertNull("Listado fechas error", fb.showAll(keyUser,"blabla","blabla"));
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBAD4ShowAll_Dates() {
		
		long ident=preparingConditions();
		String d1="2013-01-01";
		String d2="2013-12-01";
		
		assertNull("Listado fechas fuera de rango", fb.showAll(keyUser,d1,d2));
		
		cleaningConditions(ident);
	}
		
	@Test
	public void testNullkeyuserShowAll_Dates() {
			
		assertNull("Listado todo null", fb.showAll(null,null,null));
	}
	
	@Test
	public void testWrongkeyuserShowAll_Dates() {
		long ident=preparingConditions();
		String d1="2014-01-01";
		String d2="2014-12-01";
		assertNull("Listado wrong user", fb.showAll("BLABLABLu",d1,d2));
		cleaningConditions(ident);
	}

	@Test
	public void testEmptykeyuserShowAll_Dates() {
		long ident=preparingConditions();
		String d1="2014-01-01";
		String d2="2014-12-01";
		assertNull("Listado empty user", fb.showAll("",d1,d2));
		cleaningConditions(ident);
	}
	
	
	
	// **** testing record method
	
	@Test
	public void testNullRecord() {
		assertFalse(fb.record(null));
	}
	
	@Test
	public void testWrongRecord() {
		
		Object obj=new Object();
		assertFalse(fb.record(obj));
	}
	
	@Test
	public void testEmptyRecord() {
		cal=new DietCalendar();		
		assertFalse(fb.record(cal));
	}
	
	@Test
	public void testOKRecord() {
		
		cal=new DietCalendar();	
		cal.setKeyuser("1234567890ABCDE");
		cal.setDate(datePr);
		cal.setMoment(1);	
		cal.setType(1);
		cal.setIdproduct(2);
		cal.setQtt(100);

		assertTrue(fb.record(cal));
		
		control=fb.showAll("1234567890ABCDE");
		long ident=(long)Long.parseLong(control.get(0)[0]);
		cleaningConditions(ident);
	}
	
	
	
		// **** Testing read method
	
		@Test
		public void testUnknowRead() {
			long iden=999999;		
			assertNull("identificador inexistente",fb.read(iden));
		}
		
		@Test
		public void testOKRead() {
					
			long ident=preparingConditions();
			
			assertNotNull("un identificador leido",fb.read(ident));
			
			cleaningConditions(ident);

		}

	
	
		
	// **** Testing modify method
	
	@Test
	public void testUnknowIdenModify() {
		// wrong ident, good object
		
		long ident=preparingConditions();
				
		cal=new DietCalendar();		
		cal.setKeyuser(keyUser);
		cal.setDate(datePr);
		cal.setMoment(2);	
		cal.setType(2);
		cal.setIdproduct(5);
		cal.setQtt(500);
		
		assertFalse("ident a modificar inexistente",fb.modify(99999,cal));
		
		cleaningConditions(ident);
	}
	
	@Test
	public void testBad1ObjectModify() {
		// good ident, bad object
		
		long ident=preparingConditions();
		
		assertFalse("ident a modificar null object",fb.modify(ident,null));
		
		cleaningConditions(ident);
		
	}

	@Test
	public void testBad2ObjectModify() {
		// good ident, bad object
		
		long ident=preparingConditions();
		
		String[] cola={"1","2","3"};
		
		assertFalse("ident a modificar wrong object",fb.modify(ident,cola));
		
		cleaningConditions(ident);
		
	}
	
	@Test
	public void testOKModify() {
		
		long ident=preparingConditions();
		
		cal=new DietCalendar();		
		cal.setKeyuser(keyUser);
		cal.setDate(datePr);
		cal.setMoment(2);	
		cal.setType(2);
		cal.setIdproduct(5);
		cal.setQtt(500);
		
		assertTrue("un producto modificado",fb.modify(ident,cal));
		
		cleaningConditions(ident);

	}
	
	
	
	
	
	
	
	private long preparingConditions() {
		
		// creating a record to test
	
		cal=new DietCalendar();		
		cal.setKeyuser(keyUser);
		cal.setDate(datePr);
		cal.setMoment(1);	
		cal.setType(1);
		cal.setIdproduct(2);
		cal.setQtt(100);
		fb.record(cal);
		
		// getting a user by keyUser
		String[] str=fb.showAll(keyUser).get(0);
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
