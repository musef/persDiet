package control;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import model.DietUsers;


/**
 * The java bean for the DietUsers class.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public class DietUsersBean implements Serializable, DbOperations {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	private GetConnection gc;
	
	
	
	public DietUsersBean () {
		// CONSTRUCTOR
		gc=new GetConnection();
	}

	
	
	/**
	 * This method records a DietUsers object in the database.
	 * 
	 * @param - the DietUsers object to record
	 * 
	 * @return - a boolean TRUE/FALSE with with recording result
	 */
	
	@Override
	public boolean record (Object obj) {

		// casting object
		DietUsers newUser;
		try {
			newUser=(DietUsers) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
		
		EntityTransaction tx=null;
		// getting connection
		em=gc.newConnection();
		
		// recording process
		try {
			tx=em.getTransaction();
			tx.begin();
			em.persist(newUser);
			tx.commit();
		} catch (Exception ex) {
			if (tx.isActive()) {tx.rollback();}
			System.err.println("Error 4.1 Error en el proceso de grabación en DDBB");
			ex.printStackTrace();
			return false;
		} finally {
			em.close();
		}
		
		return true;	
		
	} // END OF METHOD RECORD

	
	
	/**
	 * This method deletes a DietUsers object in the database.
	 * 
	 * @param - the object id to delete
	 * 
	 * @return - a boolean TRUE/FALSE with with deleting result
	 */
	
	@Override
	public boolean delete(long ident) {
		
		EntityTransaction tx=null;
		// getting a new connection
		em=gc.newConnection();
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			// getting the object
			DietUsers uDel=em.find(DietUsers.class, ident);
			tx.commit();
			
			// deleting
			tx.begin();
			em.remove(uDel);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 4.2 Error en el proceso de borrado en DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;		
		
	} // END OF METHOD DELETE

	
	
	/**
	 * This method modifies a DietUsers object in the database.
	 * 
	 * @param - the object id to modify in ddbb
	 * @param - the DietUsers object modified to recording in ddbb
	 * 
	 * @return - a boolean TRUE/FALSE with with recording result
	 */	
	
	@Override
	public boolean modify(long ident, Object obj) {
		
		// casting object
		DietUsers newUser;
		try {
			newUser=(DietUsers) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}
		
		if (newUser==null) {
			return false;
		}	
		if (newUser.getKeyuser()==null || newUser.getKeyuser().isEmpty()) {
			return false;
		}
		if (newUser.getLogin()==null || newUser.getLogin().isEmpty()) {
			return false;
		}
		if (newUser.getPass()==null || newUser.getPass().isEmpty()) {
			return false;
		}
		if (newUser.getPdata()==null) {
			return false;
		}
		if (newUser.getPdata().getKeyuser()==null || newUser.getPdata().getKeyuser().isEmpty() ) {
			return false;
		}
		if (newUser.getPdata().getName()==null || newUser.getPdata().getName().isEmpty() ) {
			return false;
		}
		
		// to reading DietMeals
		DietUsers toMod=new DietUsers();
		
		EntityTransaction tx=null;
		// getting a new connection
		em=gc.newConnection();
		
		try {

			tx=em.getTransaction();
			tx.begin();
			// getting the object to modify
			toMod=em.find(DietUsers.class, ident);
			tx.commit();

			toMod.setLogin(newUser.getLogin());
			toMod.setPass(newUser.getPass());
			toMod.setPdata(newUser.getPdata());
			
			tx.begin();
			em.merge(toMod);
			tx.commit();		
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 4.3 Error en el proceso de modificación de DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;
		
	} // END OF METHOD MODIFY


	
	/**
	 * This method modifies a DietUsers object in the database.
	 * 
	 * @param - the string with the user key to modify in ddbb
	 * @param - the DietUsers object modified to recording in ddbb
	 * 
	 * @return - a boolean TRUE/FALSE with with recording result
	 */	
	
	public boolean modify(String keyUser, Object obj) {
		
		// casting object
		DietUsers newUser;
		try {
			newUser=(DietUsers) obj;
		} catch(ClassCastException cl) {
			// the object is wrong
			return false;
		}

		if (newUser==null) {
			return false;
		}
		if (newUser.getKeyuser()==null || newUser.getKeyuser().isEmpty()) {
			return false;
		}
		if (newUser.getLogin()==null || newUser.getLogin().isEmpty()) {
			return false;
		}
		if (newUser.getPass()==null || newUser.getPass().isEmpty()) {
			return false;
		}
		if (newUser.getPdata()==null) {
			return false;
		}
		if (newUser.getPdata().getKeyuser()==null || newUser.getPdata().getKeyuser().isEmpty() ) {
			return false;
		}
		if (newUser.getPdata().getName()==null || newUser.getPdata().getName().isEmpty() ) {
			return false;
		}
		
		// to reading DietMeals
		DietUsers toMod=new DietUsers();
		
		EntityTransaction tx=null;
		Query q=null;
		// getting a new connection
		em=gc.newConnection();
		
		try {

			tx=em.getTransaction();
			tx.begin();
			// getting the object to modify
			q=em.createNamedQuery("userByKey");
			q.setParameter("key", keyUser);
			tx.commit();
			toMod=(DietUsers) q.getSingleResult();			

			toMod.setLogin(newUser.getLogin());
			toMod.setPass(newUser.getPass());
			toMod.setPdata(newUser.getPdata());
			
			tx.begin();
			em.merge(toMod);
			tx.commit();		
			
		} catch (NonUniqueResultException ex) {
			
			@SuppressWarnings("unchecked")
			List<DietUsers> list=(List<DietUsers>)q.getResultList();
			toMod=list.get(0);
			toMod.setLogin(newUser.getLogin());
			toMod.setPass(newUser.getPass());
			toMod.setPdata(newUser.getPdata());
			
			tx.begin();
			em.merge(toMod);
			tx.commit();
			
		} catch (Exception ex) {
		
			if (tx.isActive()) {tx.rollback();};
			System.err.println("Error 4.3B Error en el proceso de modificación de DDBB");
			ex.printStackTrace();
			return false;			
			
		} finally {
			em.close();
		}
		
		return true;
		
	} // END OF METHOD MODIFY
	
	
	
	/**
	 * This method is used to find a user searching by id.
	 * 
	 * @param identif - the object id
	 * 
	 * @return - a String[] containing user data or null
	 */	
	
	@SuppressWarnings("unchecked")
	@Override
	public String[] read(long identif) {
		
		// getting a new connection
		em=gc.newConnection();
		
		String[] result=null;
		List<DietUsers> listU=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("userById");
			q.setParameter("ident", identif);
			tx.commit();
			listU=(List<DietUsers>) q.getResultList();
			
		} catch (Exception ex) {
			
			System.err.println("Error 4.4 Error en el proceso de lectura de DDBB");
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
		if (listU.isEmpty()) {
			return null;
		}
		
		result=new String[10];
		result[0]=String.valueOf(listU.get(0).getId());
		result[1]=listU.get(0).getKeyuser();
		result[2]=listU.get(0).getLogin();
		result[3]=listU.get(0).getPass();
		result[4]=String.valueOf(listU.get(0).getPdata().getId());
		result[5]=listU.get(0).getPdata().getName();
		result[6]=String.valueOf(listU.get(0).getPdata().getWeight());
		result[7]=String.valueOf(listU.get(0).getPdata().getHeight());
		result[8]=String.valueOf(listU.get(0).getPdata().getAge());	
		result[9]=String.valueOf(listU.get(0).getPdata().getSex());
		
		return result;
		
	} // END OF METHOD READ
	
	
	
	/**
	 * This method is used to find a user searching by user key.
	 * 
	 * @param keyUs - the user key
	 * 
	 * @return - a String[] containing user data or null
	 */
	
	@SuppressWarnings("unchecked")
	public String[] read(String keyUs) {
		
		// getting a new connection
		em=gc.newConnection();
		
		String[] result=null;
		List<DietUsers> listU=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("userByKey");
			q.setParameter("key", keyUs);
			tx.commit();
			listU=(List<DietUsers>) q.getResultList();
			
		} catch (Exception ex) {
			
			System.err.println("Error 4.5 Error en el proceso de lectura de DDBB");
			ex.printStackTrace();
			return null;
			
		} finally {
			try {
				em.close();
			} catch (IllegalStateException il) {
				// do nothing
			}

		}
		
		// when the method try to read a unknown value then gets a empty list
		if (listU.isEmpty()) {
			return null;
		}
		
		result=new String[10];
		result[0]=String.valueOf(listU.get(0).getId());
		result[1]=listU.get(0).getKeyuser();
		result[2]=listU.get(0).getLogin();
		result[3]=listU.get(0).getPass();
		result[4]=String.valueOf(listU.get(0).getPdata().getId());
		result[5]=listU.get(0).getPdata().getName();
		result[6]=String.valueOf(listU.get(0).getPdata().getWeight());
		result[7]=String.valueOf(listU.get(0).getPdata().getHeight());
		result[8]=String.valueOf(listU.get(0).getPdata().getAge());
		result[9]=String.valueOf(listU.get(0).getPdata().getSex());
		
		return result;
		
	} // END OF METHOD READ
	

	
	/**
	 * This method is used to find a user searching by login-password.
	 * 
	 * @param user - the user login
	 * @param pass - the user password
	 * 
	 * @return - a String[] containing user data or null
	 */
	
	public String[] read(String user, String pass) {
		
		// getting a new connection
		em=gc.newConnection();
		
		String[] result=null;
		//List<DietUsers> listU=null;
		DietUsers listU=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("identUser");
			q.setParameter("log", user);
			q.setParameter("pss", pass);
			tx.commit();
			//listU=(List<DietUsers>) q.getResultList();
			listU=(DietUsers) q.getSingleResult();
			
		} catch (NonUniqueResultException re) {
			@SuppressWarnings("unchecked")
			List<DietUsers> list=(List<DietUsers>)q.getResultList();
			listU=list.get(0);
			
		} catch (NoResultException nr) {
			// identification fails
			// do nothing
			System.err.println("Bad identification: no existe en DDBB");
			return null;
			
		} catch (Exception ex) {
			
			System.err.println("Error 4.6 Error en el proceso de lectura de DDBB");
			ex.printStackTrace();
			return null;
			
		} finally {
			try {
				em.close();
			} catch (IllegalStateException il) {
				// do nothing
			}

		}
		
		// when the method try to read a unknown value then gets a empty read
		if (listU==null) {
			return null;
		}
		
		result=new String[10];

		result[0]=String.valueOf(listU.getId());
		result[1]=listU.getKeyuser();
		result[2]=listU.getLogin();
		result[3]=listU.getPass();
		result[4]=String.valueOf(listU.getPdata().getId());
		result[5]=listU.getPdata().getName();
		result[6]=String.valueOf(listU.getPdata().getWeight());
		result[7]=String.valueOf(listU.getPdata().getHeight());
		result[8]=String.valueOf(listU.getPdata().getAge());	
		result[9]=String.valueOf(listU.getPdata().getSex());
		
		return result;
		
	} // END OF METHOD READ
	
	
	
	/**
	 * This method check the login in the database before creating a new user to
	 * avoid to repeat the login.
	 * 
	 * @param login - the login to verify if it's repeated
	 * 
	 * @return a boolean TRUE/FALSE means TRUE the login exists and FALSE
	 * the login doesn't exist and you could use it to create a new user.
	 */
	
	public boolean checkLogin(String login) {
		
		if (login==null || login.isEmpty()) {
			// refusing a wrong parameter
			return true;
		}
		
		// getting a new connection
		em=gc.newConnection();
		
		@SuppressWarnings("unused")
		DietUsers userS=null;
		
		Query q=null;
		EntityTransaction tx=null;
		
		try {
			
			tx=em.getTransaction();
			tx.begin();
			q=em.createNamedQuery("userByLogin");
			q.setParameter("yourLogin", login);
			tx.commit();
			userS=(DietUsers) q.getSingleResult();
			
		} catch (NoResultException no) {
			// this is the usual way to return this method:
			// this means that the login is not repeat and
			// you could use it to create a new user

			return false;
			
		} catch (Exception ex) {
			
			System.err.println("Error 4.7 Error en el proceso de check login en DDBB");
			ex.printStackTrace();
			
			return true;
			
		} finally {
			try {
				em.close();
			} catch (IllegalStateException il) {
				// do nothing
			}

		}	
		
		return true;
		
	} // END OF METHOD READ
	
	
	
	/**
	 * This method is not implemented yet
	 */
	
	@Override
	public List<String[]> showAll(String keyUser) {
		
		return null;
	}

}
