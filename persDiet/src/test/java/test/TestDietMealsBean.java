package test;

import static org.junit.Assert.*;

import java.util.List;

import model.DietMeals;
import org.junit.Test;
import control.DietMealsBean;

/**
 * The JUnit test for DietMealsBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestDietMealsBean {

	DietMealsBean fb=new DietMealsBean();
	DietMeals meal=new DietMeals();
	String keyUser="ABCDEF123456ABC";
	List<String[]> control;
	

	// testing list showAll method
	
	@Test
	public void testOKShowAll() {
		
		long iden=preparingConditions();
		
		assertNotNull("Listado OK", fb.showAll(keyUser));
		
		cleaningConditions(iden);
	}

	@Test
	public void testNullkeyuserShowAll() {
		
		// not controlled by default
		assertNotNull("Listado null user", fb.showAll(null));
	}
	
	@Test
	public void testWrongkeyuserShowAll() {
		
		long iden=preparingConditions();
		
		// not controlled by default
		assertNotNull("Listado wrong user", fb.showAll("BLABLABLA"));
		
		cleaningConditions(iden);
	}


	@Test
	public void testEmptykeyuserShowAll() {
		
		long iden=preparingConditions();
		
		// not controlled by default
		assertNotNull("Listado empty user", fb.showAll(""));
		
		cleaningConditions(iden);
	}
	
	
	
	
	// Testing read method
	
	
	@Test
	public void testUnknowRead() {
		long iden=999999;		
		assertNull("identificador inexistente",fb.read(iden));
	}
	
	
	@Test
	public void testOKRead() {
		
		
		long iden=preparingConditions();
		
		assertNotNull("un identificador leido",fb.read(iden));
			
		cleaningConditions(iden);

	}
	
	@Test
	public void testOK2Read() {
		
		long iden=preparingConditions();
		
		control=fb.showAll(keyUser);
		
		for (String[]n:control) {
			if (n[1].equals("PRUEBA")) {
				iden=(long)Long.parseLong(n[0]);
				assertArrayEquals("El identificador buscado leido OK",n,fb.read(iden));
			}
		}

		cleaningConditions(iden);
	}	
	
	
	// Testing modify method
	
	@Test
	public void testUnknowIdenModify() {
		// wrong ident, good object
		
		long iden=preparingConditions();
		
		meal.setMealname("PRUEBA MOD");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		
		assertFalse("ident a modificar inexistente",fb.modify(99999,meal));
		
		cleaningConditions(iden);
	}
	
	@Test
	public void testBadObjectModify() {
		// good ident, bad object
		
		long iden=preparingConditions();
		
		assertFalse("ident a modificar null object",fb.modify(iden,null));
		
		cleaningConditions(iden);	
	}
	
	
	@Test
	public void testOKModify() {
			
		long iden=preparingConditions();
		
		meal.setMealname("PRUEBA MOD3");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		
		assertTrue("un identificador leido",fb.modify(iden,meal));
			
		cleaningConditions(iden);
			
	}
	
		// testing record method
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
		meal=new DietMeals();		
		assertFalse(fb.record(meal));
	}
	
	@Test
	public void testOKRecord() {
		
		meal.setMealname("PRUEBA");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		
		assertTrue(fb.record(meal));
		
		// cleaning the record
		control=fb.showAll(keyUser);
		long iden=(long)Long.parseLong(control.get(0)[0]);
		cleaningConditions(iden);
	}
	
	
	
		// testing delete method

	@Test
	public void testUnknowDelete() {
		long iden=999999;		
		assertFalse(fb.delete(iden));
	}
	
	
	@Test
	public void testOKDelete() {
		
		long iden=preparingConditions();
		
		assertTrue(fb.delete(iden));
	}

	
	
	
	
	private long preparingConditions() {
		
		// creating a record to test
	
		meal=new DietMeals();
		meal.setMealname("PRUEBA");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		fb.record(meal);
		
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
	
} // ****** END OF TEST
