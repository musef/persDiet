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
	
	// dates variables
	private final String today;
	private Date dateSelected;
	private String formatDate;
	private String dateToSearch;
	
	
	public MainDietBean() {
		// CONSTRUCTOR
		
		// get Date today
		dateSelected=new Date();
		// store today value to show
		today=DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE).format(dateSelected);
		// date to read and record data in calendar
		formatDate=DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(dateSelected);
		dateToSearch="20"+formatDate.substring(6, 8)+formatDate.substring(2, 6)+formatDate.substring(0, 2);

	}
	
	
	
	public String changeDay() {
		
		// get the date selected and transform to Date.sql form
		// this form is for showing
		formatDate=DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(dateSelected);
		// this form is for sql
		dateToSearch="20"+formatDate.substring(6, 8)+formatDate.substring(2, 6)+formatDate.substring(0, 2);
		// get the info about day and week
		ComidasBean comidas=new ComidasBean();
		comidas.getDay();
		comidas.getWeek();
		comidas.getMonth();
		
		return "recalculos";
		
		
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
		System.out.println("date read:"+this.dateSelected.toString());
		return dateSelected;
	}

	public void setDateSelected(Date dateSelected) {
		this.dateSelected = dateSelected;
		System.out.println("date changed:"+this.dateSelected.toString());
	}

	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getToday() {
		return today;
	}

	public String getDateToSearch() {
		return dateToSearch;
	}

	public void setDateToSearch(String dateToSearch) {
		this.dateToSearch = dateToSearch;
	}

} // ************ END OF CLASS
