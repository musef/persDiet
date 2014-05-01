package control;

//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;

import model.DietPersonalData;
import model.DietUsers;


/**
 * The java bean for the newUser.xhtml control.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean
@SessionScoped
public class CreationBean {

	// navigation pages defined in faces-config.xml
	private final String returnBack="inicio";
	private final String stayHere="stay";
	
	private String login;
	private String password;
	private String name;
	private float height;
	private float weight;
	private int age;
	private int sex;
	
	private String keyUser;
	//private FacesMessage message;
	
	
	public CreationBean () {
		// CONSTRUCTOR
		//message=null;

	}


	
	/**
	 * This method records a new user. First check the form fields and if all is 
	 * correct then records in ddbb.
	 * 
	 * @return a boolean TRUE/FALSE record ok
	 */
	
	public String recordUser() {
		
		DietUsersBean usBean=new DietUsersBean();
		
		if (checkForm()) {
		
			keyUser=getKeyUser(login,password);
			
			DietUsers newUser=new DietUsers();
			DietPersonalData persData=new DietPersonalData();
			
			
			persData.setName(name);
			persData.setWeight(weight);
			persData.setHeight(height);
			persData.setAge(age);
			persData.setSex(sex);
			persData.setKeyuser(keyUser);
			
			newUser.setLogin(login);
			newUser.setPass(password);
			newUser.setKeyuser(keyUser);
			newUser.setPdata(persData);
			
			
			if (!usBean.record(newUser)) {
				System.err.println("Error 1.1 en grabación al crear usuario");
				return null;
			}
			
		} else {
				// wrong form
			return null;
		}
		
		// getting the user Data to static variables
		IdentifyBean.setUserData(usBean.read(keyUser));
		IdentifyBean.setKeyUser(keyUser);
		
		return stayHere;
		
	} // END OF METHOD RECORDUSER
	
	
	
	/**
	 * This method modifies a user. First check the form fields and if all is 
	 * correct then records in ddbb.
	 * 
	 * @return a boolean TRUE/FALSE record ok
	 */
	
	public boolean changeUser() {
		
		// all personal data will be changed except id and keyUser
		
		DietUsersBean usBean=new DietUsersBean();
		
		if (checkForm()) {
			
			DietUsers newUser=new DietUsers();
			DietPersonalData persData=new DietPersonalData();
			
			persData.setName(name);
			persData.setWeight(weight);
			persData.setHeight(height);
			persData.setAge(age);
			persData.setSex(sex);
			persData.setKeyuser(IdentifyBean.getKeyUser());
			
			newUser.setLogin(login);
			newUser.setPass(password);
			newUser.setKeyuser(IdentifyBean.getKeyUser());
			newUser.setPdata(persData);
			
			if (!usBean.modify(IdentifyBean.getKeyUser(),newUser)) {

				System.err.println("Error 1.2 modificando el usuario");
				return false;
			}
			
		} else {
			// wrong form
			return false;
		}
		
		// getting the user Data to static variables
		IdentifyBean.setUserData(usBean.read(keyUser));
		
		return true;
		
	} // END OF METHOD RECORDUSER	
	
	
	
	/**
	 * This method checks the form fields.
	 * 
	 * @return a boolean TRUE/FALSE if form fields are OK/WRONG.
	 */
	
	private boolean checkForm() {
		
		if (login==null || login.length()<6 || login.length()>15) {
			return false;
		}
		
		if (password==null || password.length()<6 || password.length()>15) {
			return false;
		}
		
		if (name==null || name.length()<3 || name.length()>50) {
			return false;
		}
		
		if (weight<0 || weight>250) {
			return false;
		}
		
		if (height<50 || height>250) {
			return false;
		}
		
		if (age<0 || age>100) {
			return false;
		}
		
		if (sex<1 || sex>2) {
			return false;
		}
		
		return true;
		
	} // END OF METHOD CHECKFORM
	
	
	
	/**
	 * This method builds a string keyuser.
	 * 
	 * @param log - a string login
	 * @param pass - a string password
	 * @return a String keyuser with 15 chars length, mixing login-password-random number.
	 */
	
	private String getKeyUser(String log, String pass) {
		
		String keyUser=null;
		
		// getting a random number
		int rand=0;
		try {
			rand=(int) (10000+Math.random()*89999);
		} catch (Exception ex) {
			// fixed value when occurs an error
			rand=99999;
		}

		// composing an aleatory keyUser
		keyUser=log.substring(0, 3)+pass.substring(0, 3)+String.valueOf(rand)+log.substring(3, 5)+pass.substring(3, 5);
		
		return keyUser;
		
	} // END OF METHOD GETKEYUSER

	
	
	/**
	 * This method return back the navigation to index page.
	 * 
	 * @return - String, the page navigation
	 */
	
	public String returnIndex() {
		
		return returnBack;
		
	} // END OF METHOD RETURNBACK
	
	

	
	
	// GETTERS AND SETTERS
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getKeyUser() {
		return keyUser;
	}
	public void setKeyUser(String keyUser) {
		this.keyUser = keyUser;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	
	
	
} // ********** END OF CLASS
