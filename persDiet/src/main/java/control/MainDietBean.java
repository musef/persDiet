package control;

import java.io.Serializable;
import java.util.Date;

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

@ManagedBean(name="mainDiet")
@SessionScoped
public class MainDietBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String kUser;
	private String nameUser;
	private Date date;
	
	
	public MainDietBean() {
		// CONSTRUCTOR
	}
	
	



	public String getNameUser() {
		
		kUser=IdentifyBean.getKeyUser();
		System.out.println(kUser+"//");
		DietUsersBean dBean=new DietUsersBean();
		String str[]=dBean.read(kUser);
		
		nameUser=str[5];
		return nameUser;
	}


	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}





	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}

} // ************ END OF CLASS
