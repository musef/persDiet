package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;


@ManagedBean (name="identify")
public class Identify implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pageToGo;
	private String loginUser;
	private String passUser;

	
	public Identify () {
		//CONSTRUCTOR
		
		loginUser="";
		passUser="";
		
		pageToGo="producto.xhtml";
	}
	
	
	
	public void identification() {
		
	} // end of method identification
	
	
	
	public String goToPage() {

		return pageToGo;
	}
	
	public String navigationPage() {
		return pageToGo;
	}
	

	public String getPageToGo() {
		return pageToGo;
	}

	public void setPageToGo(String pageToGo) {
		this.pageToGo = pageToGo;
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


	
	

}
