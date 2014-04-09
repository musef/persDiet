package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * The java bean for the index.xhtml control.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean (name="identify")
@SessionScoped
public class Identify implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// navigation pages
	private final String pageToGo="producto.xhtml";
	private final String pageBadLogin="badLogin.xhtml";
	private final String pageToCreate="creacion.xhtml";
	
	private static String keyUser;
	private static String[] userData;
	
	private String loginUser;
	private String passUser;
	

	
	public Identify () {
		//CONSTRUCTOR
		
		loginUser="";
		passUser="";
		
	}
	
	
	public String newUser() {
		
		return pageToCreate;
		
	}
	
	
	/**
	 * This method check login-password to identify the user and
	 * go to page.
	 * 
	 * @return - a string containing the destination page
	 */
	public String checkIdentification() {
		
		
		if (loginUser==null || passUser==null) {
			return pageBadLogin;
		}
		
		String log=loginUser.trim();
		String pas=passUser.trim();
		
		// pre-validating login and password
		if (log==null || log.length()<6 || log.length()>15) {
			return pageBadLogin;
		}
		if (pas==null || pas.length()<6 || pas.length()>15) {
			return pageBadLogin;
		}
		
		// verifying user
		DietUsersBean user=new DietUsersBean();
		userData=user.read(log, pas);
		
		if (userData==null) {
			// bad identification
			return pageBadLogin;
		}
		
		// check user correct. Now we get the keyUser
		keyUser=userData[1];
		
		// continues to main page
		return pageToGo;
				
	} // end of method identification
	
	
	
	// GETTERS AND SETTERS
	public String goToPage() {

		return pageToGo;
	}
	
	public String navigationPage() {
		return pageToGo;
	}
	
	public String getPageToGo() {
		return pageToGo;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPassUser() {
		return passUser;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}


	public static String getKeyUser() {
		return keyUser;
	}


	public static void setKeyUser(String keyUser) {
		Identify.keyUser = keyUser;
	}


	public static String[] getUserData() {
		return userData;
	}


	public static void setUserData(String[] userData) {
		Identify.userData = userData;
	}

	

} // *************** END OF CLASS
