package test;

import static org.junit.Assert.*;
import java.util.List;
import model.DietMeals;
import org.junit.Test;
import control.DietMealsBean;



public class TestDietMealsBean {

	DietMealsBean fb=new DietMealsBean();
	DietMeals meal=new DietMeals();
	String keyUser="ABCDEF123456ABC";
	List<String[]> control;
	

	// testing list showAll method
	
	@Test
	public void testOKShowAll() {
		
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
		
		assertNotNull("Listado OK", fb.showAll(keyUser));
	}

	@Test
	public void testNullkeyuserShowAll() {
		
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
		// not controlled by default
		assertNotNull("Listado null user", fb.showAll(null));
	}
	
	@Test
	public void testWrongkeyuserShowAll() {
		
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
		// not controlled by default
		assertNotNull("Listado wrong user", fb.showAll("BLABLABLA"));
	}


	@Test
	public void testEmptykeyuserShowAll() {
		
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
		meal.setMealname("PRUEBA MOD");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		
		assertFalse("ident a modificar inexistente",fb.modify(iden,meal));
	}
	
	@Test
	public void testBadObjectModify() {
		// good ident, bad object
		
		long iden=0;
		
		meal.setMealname("PRUEBA MOD2");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		fb.record(meal);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA MOD2")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		if (iden!=0) {
			meal=null;
			assertFalse("ident a modificar bad object",fb.modify(iden,meal));
			fb.delete(iden);
		} else {
			System.out.println("Error modificando bad object");
		}
		
	}
	
	
	@Test
	public void testOKModify() {
		
		
		long iden=0;
		
		meal.setMealname("PRUEBA MOD3");
		meal.setKeyuser(keyUser);
		meal.setQtt(100);	
		meal.setCal(100);
		meal.setCarbohydrate(100);
		meal.setProtein(100);
		meal.setLipid(100);
		meal.setCalcium(1);
		meal.setIron(1);
		fb.record(meal);
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[1].equals("PRUEBA MOD3")) {
				iden=(long)Long.parseLong(n[0]);
			}
		}
		if (iden!=0) {
			assertTrue("un identificador leido",fb.modify(iden,meal));
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
		
		control=fb.showAll(keyUser);
		for (String[]n:control) {
			if (n[2].equals("PRUEBA")) {
				iden=(long)Long.parseLong(n[0]);
				System.out.println("******* TAKE CARE **********"+n[0]);
			}
		}
		
		if (iden==0) {
			System.out.println("******* ERROR **********"+control.size());
		}
		System.out.println("******* TRUE **********"+iden);
		assertTrue(fb.delete(iden));
	}

} // ****** END OF TEST
