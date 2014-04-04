package control;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class GetConnection implements Serializable{

	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory emf;
	private EntityManager em;
	private final String connect="persDiet";
	
	
	public GetConnection() {
		// CONSTRUCTOR
		emf=null;
		try {
			emf=Persistence.createEntityManagerFactory(connect);
		} catch (PersistenceException ps) {
			emf=null;
		}	
	}
	
	
	
	public EntityManager newConnection() {
		
		try {
			em=emf.createEntityManager();
		} catch (NullPointerException n) {
			em=null;
		}
		
		return em;
		
	} // END OF METHOD NEWCONNECTION



	public static EntityManagerFactory getEmf() {
		return emf;
	}



	public static void setEmf(EntityManagerFactory emf) {
		GetConnection.emf = emf;
	}



	public EntityManager getEm() {
		return em;
	}



	public void setEm(EntityManager em) {
		this.em = em;
	}

} // ********* END OF CLASS GETCONNECTION
