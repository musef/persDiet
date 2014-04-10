package test;

import static org.junit.Assert.*;

import java.util.List;

import model.DietCalendar;
import model.DietFoods;
import org.junit.Test;
import control.DietFoodsBean;

/**
 * The JUnit test for DietFoodsBean
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class TestDietFoodsBean {

	DietFoodsBean fb=new DietFoodsBean();
	DietFoods food=new DietFoods();
	String keyUser="1234567890abcde";
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
		
		long iden=preparingConditions();
		
		// not controlled by default
		assertNotNull("Listado bad null user", fb.showAll(null));
		
		cleaningConditions(iden);
	}
	
	@Test
	public void testWrongkeyuserShowAll() {
		
		long iden=preparingConditions();
		
		// not controlled by default
		assertNotNull("Listado bad wrong user", fb.showAll("BLABLABLA"));
		
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
		
		long ident=preparingConditions();
		
		control=fb.showAll(keyUser);
		
		for (String[]n:control) {
			if (n[1].equals("PRUEBA")) {
				long iden=(long)Long.parseLong(n[0]);
				assertArrayEquals("El identificador buscado leido OK",n,fb.read(iden));
			}
		}
		cleaningConditions(ident);
	}	
	
	
	
	// Testing modify method
	
	@Test
	public void testUnknowIdenModify() {
		// wrong ident, good object
		long iden=preparingConditions();
		
		assertFalse("ident a modificar inexistente",fb.modify(99999,food));
		
		cleaningConditions(iden);
	}
	
	@Test
	public void testBadObjectModify() {
		// good ident, bad object
		
		long iden=preparingConditions();
		
		assertFalse("ident a modificar bad object",fb.modify(iden,null));
		
		cleaningConditions(iden);
		
	}
	
	
	@Test
	public void testOKModify() {
		
		
		long iden=preparingConditions();
		
		assertTrue("un identificador leido",fb.modify(iden,food));
		
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
		food=new DietFoods();		
		assertFalse(fb.record(food));
	}
	
	@Test
	public void testOKRecord() {
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		assertTrue(fb.record(food));
		
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
	
		food=new DietFoods();
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		// getting a user by keyUser
		control=fb.showAll(keyUser);
		long ident=0;
		for (String[] str: control) {
			if (str[1].equals("PRUEBA")) {
				// getting the id of this FOOD
				ident=(long)Long.parseLong(str[0]);
			}
		}
		
		return ident;
	}
	
	
	private void cleaningConditions(long id) {
		System.out.println("del: "+id);
		fb.delete(id);
	}
	
}
