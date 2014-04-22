package control;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.DietCalendar;

/**
 * The java bean for the dietCalendar class.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class DietCalendarBean implements Serializable, DbOperations {

	private static final long serialVersionUID = 1L;

	private EntityManager em;
	private GetConnection connect;
	
	
	
	public DietCalendarBean() {
		// CONSTRUCTOR
		connect=new GetConnection();
	}
	
	
	
	/**
	 * This method records a new food in calendar in the database.
	 * 
	 * @param obj - a DietCalendar object to recording.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a new food-calendar recorded.
	 */

	@Override
	public boolean record(Object obj) {
		
		DietCalendar cal=null;
		try {
			cal=(DietCalendar) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
			
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		try {
			
			transac.begin();
			em.persist(cal);
			transac.commit();
			
		} catch (Exception ex) {
			if (transac.isActive()) {transac.rollback();};
			System.err.println("Error 5.1 Error en el proceso de grabación en DDBB");
			ex.printStackTrace();
			return false;
			
		} finally {
			em.close();
		}
				
		return true;
		
	} // END OF METHOD RECORD

	
	
	/**
	 * This method delete a food-calendar registered in the DDBB, identifies by
	 * a Long ident.
	 * 
	 * @param ident - long number is the food-calendar id in ddbb.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a food-calendar deleted.
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
			DietCalendar fDel=em.find(DietCalendar.class, ident);
			tx.commit();
			
			// deleting
			tx.begin();
			em.remove(fDel);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 5.2 Error en el proceso de borrado en DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;
		
	} // END OF METHOD DELETE

	
	
	/**
	 * This method modifies a food-calendar registered in the DDBB, identifies by
	 * a Long ident.
	 * 
	 * @param ident - long number is the food-calendar id in ddbb.
	 * @param obj - a DietCalendar object including all the changes.
	 * 
	 * @return Return a boolean TRUE/FALSE confirming a food-calendar modified.
	 */
	
	@Override
	public boolean modify(long ident, Object obj) {
		
		// casting to DietFoods object
		DietCalendar calM=null;
		try {
			calM=(DietCalendar) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
		
		// reading DietFoods
		DietCalendar toMod=new DietCalendar();
		
		EntityTransaction tx=null;
		// getting a new connection
		em=connect.newConnection();
		
		try {

			tx=em.getTransaction();
			tx.begin();
			// getting the object to modify
			toMod=em.find(DietCalendar.class, ident);

			toMod.setDate(calM.getDate());
			toMod.setMoment(calM.getMoment());
			toMod.setType(calM.getType());
			toMod.setIdproduct(calM.getIdproduct());
			toMod.setQtt(calM.getQtt());
			
			tx.commit();
			tx.begin();
			em.merge(toMod);
			tx.commit();		
			
		} catch (Exception ex) {
		
			tx.rollback();
			System.err.println("Error 5.3 Error en el proceso de modificación de DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;		
		
	} // END OF METHOD MODIFY
	
	
	
	/**
	 * This method reads a food-calendar registered in the DDBB, identifies by
	 * a long ident.
	 * 
	 * @param identif - long number is the food-calendar id in ddbb.
	 * 
	 * @return String[] as the result of reading or null if any problem or reading doesn't exists.
	 */

	@Override
	public String[] read(long identif) {
		
		// getting a new connection
		em=connect.newConnection();
		
		String[] result=null;
		DietCalendar queryF=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("aFoodCal");
			q.setParameter("ident", identif);
			tx.commit();
			queryF=(DietCalendar) q.getSingleResult();
			
		} catch (Exception ex) {
			
			System.err.println("Error 5.4 Error en el proceso de lectura de DDBB");
			ex.printStackTrace();
			return null;
			
		} finally {
			try {
				em.close();
			} catch (IllegalStateException il) {
				// do nothing
			}
		}
		
		// caution: that's not be work here because queryF null gets a Exception
		if (queryF==null) {
			return null;
		}
		
		result=new String[7];
		result[0]=String.valueOf(queryF.getId());
		result[1]=queryF.getKeyuser();
		result[2]=String.valueOf(queryF.getDate());
		result[3]=String.valueOf(queryF.getMoment());
		result[4]=String.valueOf(queryF.getType());
		result[5]=String.valueOf(queryF.getIdproduct());
		result[6]=String.valueOf(queryF.getQtt());		
		
		return result;
		
	} // END OF METHOD READ

	
	
	/**
	 * This method shows all the foods-calendar registered in the database, for a
	 * user.
	 * 
	 * @param keyUser - String key that identifies a user
	 * 
	 * @return a List String including all the foods in ddbb or null if
	 * anything is wrong.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> showAll(String keyUser) {
		
		if (keyUser==null || keyUser.isEmpty()) {
			return null;
		}
		
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		List<DietCalendar> listCal=null;
		List<String[]> lista=new ArrayList<String[]>();
		
		Query q=null;
		
		try {
			
			transac.begin();
			q=em.createNamedQuery("allCal");
			q.setParameter("keyUs", keyUser);
			
			listCal=(List<DietCalendar>)q.getResultList();
			
		} catch (Exception e) {
			System.err.println("Error 5.5A Error en el proceso de búsqueda en DDBB");
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
			em.close();
		}
		
		if (listCal.isEmpty()) {
			return null;
		}
		
		for (DietCalendar n:listCal) {
			String[] datos=new String[7];
			datos[0]=String.valueOf(n.getId());
			datos[1]=n.getKeyuser();
			datos[2]=String.valueOf(n.getDate());
			datos[3]=String.valueOf(n.getMoment());
			datos[4]=String.valueOf(n.getType());
			datos[5]=String.valueOf(n.getIdproduct());
			datos[6]=String.valueOf(n.getQtt());
			lista.add(datos);
		}
		
		return lista;
		
	} // END OF METHOD SHOWALL
	
	
	
	/**
	 * This method shows all the foods-calendar registered in the database, for a
	 * user and between dates
	 * 
	 * @param keyUser - String key that identifies a user
	 * @param dateIni - Date to start the search in ddbb
	 * @param dateFin - Date to stop the search in ddbb
	 * 
	 * @return a List String including all the foods in ddbb or null if
	 * anything is wrong.
	 */
	
	@SuppressWarnings("unchecked")
	public List<String[]> showAll(String keyUser, String dateIni, String dateFin) {
		
		// checking parameters
		if (keyUser==null) {
			System.err.println("Error 5.5B keyUser null");
			return null;
		}	
		if (keyUser.isEmpty()) {
			return null;
		}
		Date d1=null;
		Date d2=null;
		try {
			d1=Date.valueOf(dateIni);
			d2=Date.valueOf(dateFin);	
		} catch (IllegalArgumentException ex) {
			System.err.println("Error 5.5B Fechas no validas:"+dateIni+"//"+dateFin);
			return null;
		}
		
		// getting a new connection
		em=connect.newConnection();
		EntityTransaction transac=em.getTransaction();
		
		List<DietCalendar> listCal=null;
		List<String[]> lista=new ArrayList<String[]>();
		
		Query q=null;
		
		try {
			
			transac.begin();
			q=em.createNamedQuery("dateCal");
			q.setParameter("keyUs", keyUser);
			q.setParameter("date1", d1);
			q.setParameter("date2", d2);
					
			listCal=(List<DietCalendar>)q.getResultList();
			
		} catch (Exception e) {
			System.err.println("Error 5.5B Error en el proceso de búsqueda en DDBB");
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
			em.close();
		}
		
		if (listCal.isEmpty()) {
			return null;
		}
		
		for (DietCalendar n:listCal) {
			String[] datos=new String[7];
			datos[0]=String.valueOf(n.getId());
			datos[1]=n.getKeyuser();
			datos[2]=String.valueOf(n.getDate());
			datos[3]=String.valueOf(n.getMoment());
			datos[4]=String.valueOf(n.getType());
			datos[5]=String.valueOf(n.getIdproduct());
			datos[6]=String.valueOf(n.getQtt());
			lista.add(datos);
		}
		
		return lista;
		
	} // END OF METHOD SHOWALL

} // ********** END OF CLASS
