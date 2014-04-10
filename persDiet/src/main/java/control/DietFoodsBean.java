package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.DietFoods;



/**
 * The java bean for the dietFoods class.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class DietFoodsBean implements DbOperations, Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	private GetConnection connect;
	
	
	public DietFoodsBean() {
		// CONSTRUCTOR
		
		connect=new GetConnection();
		
	} // END OF CONSTRUCTOR
	
	
	

	/**
	 * This method records a new food in the database.
	 * 
	 * @param foodObj - a DietFoods object to recording.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a new food recorded.
	 */

	@Override
	public boolean record(Object foodObj) {

		DietFoods food=null;
		try {
			food=(DietFoods) foodObj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
			
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		try {
			
			transac.begin();
			em.persist(food);
			transac.commit();
			
		} catch (Exception ex) {
			if (transac.isActive()) {transac.rollback();};
			System.err.println("Error 1.1 Error en el proceso de grabación en DDBB");
			ex.printStackTrace();
			return false;
			
		} finally {
			em.close();
		}
				
		return true;
		
	} // END OF METHOD RECORD

	

	/**
	 * This method delete a product registered in the DDBB, identifies by
	 * a Long ident.
	 * 
	 * @param ident - long number is the product id in ddbb.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a food deleted.
	 */
	
	@Override
	public boolean delete(long ident) {
			
		EntityTransaction tx=null;
		// getting a new connection
		em=connect.newConnection();
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			// getting the object
			DietFoods fDel=em.find(DietFoods.class, ident);
			tx.commit();
			
			// deleting
			tx.begin();
			em.remove(fDel);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 1.2 Error en el proceso de borrado en DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;
		
	} // END OF METHOD DELETE


	
	/**
	 * This method modifies a product registered in the DDBB, identifies by
	 * a Long ident.
	 * 
	 * @param ident - long number is the product id in ddbb.
	 * @param obj - a DietFoods object including all the changes.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a food modified.
	 */

	@Override
	public boolean modify(long ident, Object obj) {

		// casting to DietFoods object
		DietFoods foodM=(DietFoods) obj;
		
		// reading DietFoods
		DietFoods toMod=new DietFoods();
		
		EntityTransaction tx=null;
		// getting a new connection
		em=connect.newConnection();
		
		try {

			tx=em.getTransaction();
			tx.begin();
			// getting the object to modify
			toMod=em.find(DietFoods.class, ident);

			toMod.setFoodname(foodM.getFoodname());
			toMod.setQtt(foodM.getQtt());
			toMod.setCal(foodM.getCal());
			toMod.setCarbohydrate(foodM.getCarbohydrate());
			toMod.setProtein(foodM.getProtein());
			toMod.setLipid(foodM.getLipid());
			toMod.setCalcium(foodM.getCalcium());
			toMod.setIron(foodM.getIron());
			tx.commit();
			tx.begin();
			em.merge(toMod);
			tx.commit();		
			
		} catch (Exception ex) {
		
			tx.rollback();
			System.err.println("Error 1.3 Error en el proceso de modificación de DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;		
		
	} // END OF METHOD MODIFY

	
	
	/**
	 * This method reads a product registered in the DDBB, identifies by
	 * a long ident.
	 * 
	 * @param identif - long number is the product id in ddbb.
	 * 
	 * @return String[] as the result of reading or null if any problem or reading doesn't exists.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public String[] read(long identif) {
		
		// getting a new connection
		em=connect.newConnection();
		
		String[] result=null;
		List<DietFoods> queryF=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("aFood");
			q.setParameter("ident", identif);
			tx.commit();
			queryF=(List<DietFoods>) q.getResultList();
			
		} catch (Exception ex) {
			
			System.err.println("Error 1.4 Error en el proceso de lectura de DDBB");
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
		if (queryF.isEmpty()) {
			return null;
		}
		
		result=new String[9];
		result[0]=String.valueOf(queryF.get(0).getId());
		result[1]=queryF.get(0).getFoodname();
		result[2]=String.valueOf(queryF.get(0).getQtt());
		result[3]=String.valueOf(queryF.get(0).getCal());
		result[4]=String.valueOf(queryF.get(0).getCarbohydrate());
		result[5]=String.valueOf(queryF.get(0).getProtein());
		result[6]=String.valueOf(queryF.get(0).getLipid());
		result[7]=String.valueOf(queryF.get(0).getCalcium());
		result[8]=String.valueOf(queryF.get(0).getIron());		
		
		return result;
		
	} // END OF METHOD READ

	
	
	/**
	 * This method shows all the foods registered in the database.
	 * 
	 * @param - keyUser: IGNORED in this implementation
	 * 
	 * @return a List String including all the foods in ddbb or null if
	 * anything is wrong.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> showAll(String keyUser) {

		// test warning: this list not use keyUser because the
		// food list is for all users !!
		// keyUser is required for overrides method
		
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		List<DietFoods> listFoods=null;
		List<String[]> lista=new ArrayList<String[]>();
		
		Query q=null;
		
		try {
			
			transac.begin();
			q=em.createNamedQuery("allFoods");
			
			listFoods=(List<DietFoods>)q.getResultList();
			
		} catch (Exception e) {
			System.err.println("Error 1.5 Error en el proceso de búsqueda en DDBB");
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
			em.close();
		}
		
		for (DietFoods n:listFoods) {
			String[] datos=new String[9];
			datos[0]=String.valueOf(n.getId());
			datos[1]=n.getFoodname();
			datos[2]=String.valueOf(n.getQtt());
			datos[3]=String.valueOf(n.getCal());
			datos[4]=String.valueOf(n.getCarbohydrate());
			datos[5]=String.valueOf(n.getProtein());
			datos[6]=String.valueOf(n.getLipid());
			datos[7]=String.valueOf(n.getCalcium());
			datos[8]=String.valueOf(n.getIron());
			lista.add(datos);
		}
		
		return lista;
		
	} // END OF METHOD SHOWALL



} // ********* END OF CLASS
