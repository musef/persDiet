package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 * The java bean for the mainDiet.xhtml control.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean
@SessionScoped
public class NorthBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// dates variables
	private String nameUser;
	private String today;
	
	private MainDietBean mainDiet;
	private DietUsersBean dBean;

	
	
	
	public NorthBean() {
		// CONSTRUCTOR
		
		mainDiet=new MainDietBean();
		dBean=new DietUsersBean();
		
	}
	
	/**
	 * This method stop the application, erasing surely data and connection,
	 * and then going out to index page.
	 * 
	 * @return String to navigation
	 */
	
	public String exit() {
		
		// erasing data user
		IdentifyBean.setKeyUser(null);
		IdentifyBean.setUserData(null);
		// erasing connection
		GetConnection.setEmf(null);
		
		return "index.xhtml";
	}
	
	
	
	/**
	 * This method is used to go to user modify page.
	 * 
	 * @return String to navigation
	 */
	
	public String modifyUser() {
		
		return "moduser";
	}
	
	
	
	// GETTERS AND SETTERS

	public String getNameUser() {
		
		String[] us=dBean.read(IdentifyBean.getKeyUser());
		nameUser=us[5];
		
		return nameUser;
	}

	
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	
	public String getToday() {
		
		String date=mainDiet.getToday();
		today=date.substring(8)+date.substring(4, 8)+date.substring(0, 4);
		
		return today;
	}

	
	public void setToday(String today) {
		this.today = today;
	}

	
} // ************ END OF CLASS
