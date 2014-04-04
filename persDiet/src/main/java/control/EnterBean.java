package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name="enter")
@SessionScoped
public class EnterBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String goToPage="producto.xhtml";
	
	public EnterBean() {
		// CONSTRUCTOR
	}
	
	
	public String navigationPage() {
		
	
		return goToPage;
	}
	
	// GETTERS AND SETTERS

	public String getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(String goToPage) {
		this.goToPage = goToPage;
	}
	
	
	
	
	
}
