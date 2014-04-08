package control;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.DietPersonalData;
import model.DietUsers;


/**
 * The java bean for the creation.xhtml control.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean(name="creacion")
public class CreacionBean {

	private String login;
	private String password;
	private String name;
	private String keyUser;
	private int height;
	private int weight;
	private int age;
	
	private FacesMessage message;
	private String buttonState;
	
	
	public CreacionBean () {
		// CONSTRUCTOR
		message=null;
		buttonState="enabled";
	}


	/**
	 * This method records a new user. First check the form fields and if all is 
	 * correct then records in ddbb.
	 * 
	 * @return a boolean TRUE/FALSE record ok
	 */
	
	public boolean recordUser() {
		
		keyUser=getKeyUser(login,password);
		
		FacesContext fc=FacesContext.getCurrentInstance();
		
		if (checkForm()) {
			
			DietUsers newUser=new DietUsers();
			DietPersonalData persData=new DietPersonalData();
			
			
			persData.setName(name);
			persData.setWeight(weight);
			persData.setHeight(height);
			persData.setAge(age);
			persData.setKeyuser(keyUser);
			
			newUser.setLogin(login);
			newUser.setPass(password);
			newUser.setKeyuser(keyUser);
			newUser.setPdata(persData);
			
			DietUsersBean newRec=new DietUsersBean();
			if (newRec.record(newUser)) {
				// record OK
				//message.setDetail("Grabación efectuada correctamente");
				System.out.println("Grabación OK");
				buttonState="disabled";			
				
			} else {
				//message.setDetail("Error en grabación");
				System.out.println("Grabación ERROR");
				return false;
			}
			
		} else {
			//message.setDetail("Error en el formulario");
			System.out.println("wrong form");
			return false;
		}
		
		fc.addMessage("result", message);
		
		return true;
		
	} // END OF METHOD RECORDUSER
	
	
	
	/**
	 * This method checks the form fields.
	 * 
	 * @return a boolean TRUE/FALSE if form fields are OK/WRONG.
	 */
	
	public boolean checkForm() {
		
		if (login==null || login.length()<6 || login.length()>15) {
			return false;
		}
		
		if (password==null || password.length()<6 || password.length()>15) {
			return false;
		}
		
		if (keyUser==null || keyUser.length()!=15) {
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
		
		return true;
		
	} // END OF METHOD CHECKFORM
	
	
	
	/**
	 * This method builds a string keyuser.
	 * 
	 * @param log - a string login
	 * @param pass - a string password
	 * @return a String keyuser with 15 chars length, mixing login-password-random number.
	 */
	
	public String getKeyUser(String log, String pass) {
		
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
	 * This method return back the navigation.
	 * 
	 * @return - String, the page navigation
	 */
	
	public String returnBack() {
		
		return "index.xhtml";
		
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


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
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


	public String getButtonState() {
		return buttonState;
	}


	public void setButtonState(String buttonState) {
		this.buttonState = buttonState;
	}
	
	
	
	
}
