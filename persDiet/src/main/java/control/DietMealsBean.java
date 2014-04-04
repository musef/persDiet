package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.DietFoods;
import model.DietMeals;


/**
 * The java bean for the dietMeals class.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */


public class DietMealsBean implements Serializable, DbOperations {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	private GetConnection connect;
	
	
	
	public DietMealsBean () {
		// CONSTRUCTOR

		connect=new GetConnection();
		
	}
	
		
	
	
	
	/**
	 * This method records a new meal in the database.
	 * 
	 * @param meal - a DietMeals object to recording.
	 * @return Return a boolean confirming a new meal recorded.
	 */
	

	@Override
	public boolean record(Object mealObj) { 
		
		
		DietMeals meal=null;
		try {
			meal=(DietMeals) mealObj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
		
		// getting connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		try {
		
			transac.begin();
			em.persist(meal);
			transac.commit();	
			
		} catch (Exception ex) {
			if (transac.isActive()) {transac.rollback();};
			System.err.println("Error 2.1 Error en el proceso de grabación en DDBB");
			ex.printStackTrace();
			return false;
			
		} finally {
			em.close();
		}
		
		return true;		
		
	} // END OF METHOD RECORD



	@Override
	public boolean delete(long ident) {

		EntityTransaction tx=null;
		// getting a new connection
		em=connect.newConnection();
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			// getting the object
			DietMeals mDel=em.find(DietMeals.class, ident);
			tx.commit();
			
			// deleting
			tx.begin();
			em.remove(mDel);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 2.2 Error en el proceso de borrado en DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;		
		
	} // END OF METHOD DELETE





	@Override
	public boolean modify(long ident, Object obj) {

		// casting to DietMeals object
		DietMeals meal=null;
		try {
			meal=(DietMeals) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
		
		// to reading DietMeals
		DietMeals toMod=new DietMeals();
		
		EntityTransaction tx=null;
		// getting a new connection
		em=connect.newConnection();
		
		try {

			tx=em.getTransaction();
			tx.begin();
			// getting the object to modify
			toMod=em.find(DietMeals.class, ident);
			tx.commit();

			toMod.setMealname(meal.getMealname());
			toMod.setQtt(meal.getQtt());
			toMod.setCal(meal.getCal());
			toMod.setCarbohydrate(meal.getCarbohydrate());
			toMod.setProtein(meal.getProtein());
			toMod.setLipid(meal.getLipid());
			toMod.setCalcium(meal.getCalcium());
			toMod.setIron(meal.getIron());
			
			tx.begin();
			em.merge(toMod);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 2.3 Error en el proceso de modificación de DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;
		
	} // END OF METHOD MODIFY





	@SuppressWarnings("unchecked")
	@Override
	public String[] read(long identif) {

		// getting a new connection
		em=connect.newConnection();
		
		String[] result=null;
		List<DietMeals> listM=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("aMeal");
			q.setParameter("ident", identif);
			tx.commit();
			listM=(List<DietMeals>) q.getResultList();
			
		} catch (Exception ex) {
			
			System.err.println("Error 2.4 Error en el proceso de lectura de DDBB");
			ex.printStackTrace();
			return null;
			
		} finally {
			try {
				em.close();
			} catch (IllegalStateException il) {
				// do nothing
			}

		}
		
		// when the method try to read a unknow value then gets a empty list
		if (listM.isEmpty()) {
			return null;
		}
		
		result=new String[9];
		result[0]=String.valueOf(listM.get(0).getId());
		result[1]=listM.get(0).getMealname();
		result[2]=String.valueOf(listM.get(0).getQtt());
		result[3]=String.valueOf(listM.get(0).getCal());
		result[4]=String.valueOf(listM.get(0).getCarbohydrate());
		result[5]=String.valueOf(listM.get(0).getProtein());
		result[6]=String.valueOf(listM.get(0).getLipid());
		result[7]=String.valueOf(listM.get(0).getCalcium());
		result[8]=String.valueOf(listM.get(0).getIron());		
		
		return result;
		
	} // END OF METHOD READ

	
	
	/**
	 * This method shows all the meals registered in the database.
	 * 
	 * @param keyUser - the key that identifies the user
	 * 
	 * @return a List String including all the foods in ddbb or null if
	 * something is wrong.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> showAll(String keyUser) {
		List<DietMeals> all=new ArrayList<DietMeals>();
		List<String[]> ret=new ArrayList<String[]>();
		
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
	
		Query q=null;
		
		
		try {
			
			transac.begin();
			q=em.createNamedQuery("allMeals");
			q.setParameter("keyUser", keyUser);
	
			all=(List<DietMeals>)q.getResultList();
			transac.commit();
			
		} catch (Exception e) {
			transac.rollback();
			System.err.println("Error 2.5 Error en el proceso de búsqueda en DDBB");
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		 
		for (int n=0;n<all.size();n++) {
			String[] cad=new String[2];
				cad[0]=all.get(n).getMealname();
				cad[1]=String.valueOf(all.get(n).getCal());		
			ret.add(cad);
		}
		
		for (DietMeals n:all) {
			String[] datos=new String[10];
			datos[0]=String.valueOf(n.getId());
			datos[1]=n.getKeyuser();
			datos[2]=n.getMealname();
			datos[3]=String.valueOf(n.getQtt());
			datos[4]=String.valueOf(n.getCal());
			datos[5]=String.valueOf(n.getCarbohydrate());
			datos[6]=String.valueOf(n.getProtein());
			datos[7]=String.valueOf(n.getLipid());
			datos[8]=String.valueOf(n.getCalcium());
			datos[9]=String.valueOf(n.getIron());
			ret.add(datos);
		}
		
		
		return ret;
		
	} // END OF METHOD SHOWALL


	
	
} // ************ END OF CLASS