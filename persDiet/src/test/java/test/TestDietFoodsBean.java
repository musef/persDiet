package test;

import static org.junit.Assert.*;
import java.util.List;
import model.DietFoods;
import org.junit.Test;
import control.DietFoodsBean;



public class TestDietFoodsBean {

	DietFoodsBean fb=new DietFoodsBean();
	DietFoods food=new DietFoods();
	String keyUser="ABCDEF123456ABC";
	List<String[]> control;
	

	// testing list showAll method
	
	@Test
	public void testOKShowAll() {
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		assertNotNull("Listado OK", fb.showAll(keyUser));
	}

	@Test
	public void testNullkeyuserShowAll() {
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		// not controlled by default
		assertNotNull("Listado null user", fb.showAll(null));
	}
	
	@Test
	public void testWrongkeyuserShowAll() {
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		// not controlled by default
		assertNotNull("Listado wrong user", fb.showAll("BLABLABLA"));
	}


	@Test
	public void testEmptykeyuserShowAll() {
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		// not controlled by default
		assertNotNull("Listado empty user", fb.showAll(""));
	}
	
	
	
	// Testing read method
	
	
	@Test
	public void testUnknowRead() {
		long iden=999999;		
		assertNull("identificador inexistente",fb.read(iden));
	}
	
	
	@Test
	public void testOKRead() {
		
		
		long iden=0;
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		if (iden!=0) {
			assertNotNull("un identificador leido",fb.read(iden));
			fb.delete(iden);
		} else {
			System.out.println("Error leyendo iden");
		}

	}
	
	@Test
	public void testOK2Read() {
		
		long iden=0;
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		control=fb.showAll(keyUser);
		
		for (String[]n:control) {
			if (n[1].equals("PRUEBA")) {
				iden=(long)Long.parseLong(n[0]);
				assertArrayEquals("El identificador buscado leido OK",n,fb.read(iden));
				fb.delete(iden);
			}
		}

	}	
	
	
	// Testing modify method
	
	@Test
	public void testUnknowIdenModify() {
		// wrong ident, good object
		long iden=999999;		
		food.setFoodname("PRUEBA MOD");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		
		assertFalse("ident a modificar inexistente",fb.modify(iden,food));
	}
	
	@Test
	public void testBadObjectModify() {
		// good ident, bad object
		
		long iden=0;
		
		food.setFoodname("PRUEBA MOD2");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA MOD2")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		if (iden!=0) {
			food=null;
			assertFalse("ident a modificar bad object",fb.modify(iden,food));
			fb.delete(iden);
		} else {
			System.out.println("Error modificando bad object");
		}
		
	}
	
	
	@Test
	public void testOKModify() {
		
		
		long iden=0;
		
		food.setFoodname("PRUEBA MOD3");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA MOD3")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		if (iden!=0) {
			assertTrue("un identificador leido",fb.modify(iden,food));
			fb.delete(iden);
		} else {
			System.out.println("Error modificando iden3");
		}

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
	}
	
	
	
		// testing delete method

	@Test
	public void testUnknowDelete() {
		long iden=999999;		
		assertFalse(fb.delete(iden));
	}
	
	
	@Test
	public void testOKDelete() {
		
		long iden=0;
		
		food.setFoodname("PRUEBA");
		food.setQtt(100);	
		food.setCal(100);
		food.setCarbohydrate(100);
		food.setProtein(100);
		food.setLipid(100);
		food.setCalcium(1);
		food.setIron(1);
		fb.record(food);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		
		assertTrue(fb.delete(iden));
	}
	
}
