package control;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
public class MainDietBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String kUser;
	private String nameUser;
	private Date dateSelected;
	private String formatDate;
	
	
	public MainDietBean() {
		// CONSTRUCTOR
		dateSelected=new Date();
		
		formatDate=DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE).format(dateSelected);
	}
	
	
	
	public void changeDay() {
		
		System.out.println("Makes my day "+formatDate);
		
	} // end of method changeDay


	// GETTERS AND SETTERS

	public String getNameUser() {
		
		kUser=IdentifyBean.getKeyUser();
		DietUsersBean dBean=new DietUsersBean();
		String str[]=dBean.read(kUser);
		
		nameUser=str[5];
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public Date getDateSelected() {
		System.out.println("date read:"+formatDate.toString());
		return dateSelected;
	}

	public void setDateSelected(Date dateSelected) {
		this.dateSelected = dateSelected;
		System.out.println("date changed:"+formatDate.toString());
	}

	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

} // ************ END OF CLASS
