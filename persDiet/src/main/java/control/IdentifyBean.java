package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * The java bean for the index.xhtml control, and westPanel.xhtml
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean
@SessionScoped
public class IdentifyBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// navigation pages
	private final String pageToGo="mainDiet";
	private final String pageBadLogin="badLogin";
	private final String pageToCreate="newUser";
	private final String pageIndex="index";
	private final String returnMainPage="main";
	
	// user info
	private static String keyUser;
	private static String[] userData;
	private String logUser;
	private String pasUser;
	private String name;
	private float height;
	private float weight;
	private int age;
	private int sexRec;
	private String sex;
	private float imc;
	
	private int lifeStyle;
	
	protected static double consum;
	protected static double calcneed;
	protected static double ironneed;
	protected static double protneed;
	
	// identification
	private String loginUser;
	private String passUser;
	

	
	public IdentifyBean () {
		//CONSTRUCTOR
		
		loginUser="";
		passUser="";
		
	}
	
	
	
	public String newUser() {
		return pageToCreate;
		
	}
	
	
	
	public String goToIndex() {
		return pageIndex;
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
		logUser=userData[2];
		pasUser=userData[3];
		name=userData[5];
		weight=(float)Float.parseFloat(userData[6]);
		height=(float)Float.parseFloat(userData[7]);
		age=(int)Integer.parseInt(userData[8]);
		imc=(float)((Math.round(weight*1000000/height/height))/100);
		// starting with active life style
		lifeStyle=1;
		
		sexRec=0;
		try {
			sexRec=(int)Integer.parseInt(userData[9]);
		} catch (NumberFormatException nf) {
			// do nothing
		}
		
		if (sexRec==1) {
			sex="Masculino";
		} else {
			sex="Femenino";
		}

		// get targets
		consum=getCalories(age, weight, sexRec, lifeStyle );
		calcneed=getCalcium(age, sexRec);
		ironneed=getIron(age, sexRec);
		protneed=getProtein(age, weight, sexRec);
		
		// continues to main page
		return pageToGo;
				
	} // end of method identification
	
	
	
	public String modifyUser() {
		
		CreationBean cB=new CreationBean();
		cB.setName(name);
		cB.setLogin(logUser);
		cB.setPassword(pasUser);
		cB.setWeight(weight);
		cB.setHeight(height);
		cB.setAge(age);
		cB.setSex(sexRec);
		
		if (!cB.changeUser()) {
			System.err.println("Error 4.1 Error al modificar el usuario");
			return "userData.xhtml";
		}
		
		// updating changes
		loginUser=logUser;
		passUser=pasUser;
		checkIdentification();
		
		return returnMainPage;
		
	} // end of method modifyUser
	
	
	/**
	 * This method change personal data when the user changes the life style.
	 * 
	 * @return
	 */
	
	public String changeLifeStyle() {
		
		int sx=0;
		try {
			sx=(int)Integer.parseInt(userData[9]);
		} catch (NumberFormatException nf) {
			// do nothing
		}
		
		if (sx==1) {
			sex="Masculino";
		} else {
			sex="Femenino";
		}

		// get targets
		consum=getCalories(age, weight, sx, lifeStyle );
		calcneed=getCalcium(age, sx);
		ironneed=getIron(age, sx);
		protneed=getProtein(age, weight, sx);
			
		return "recalculos";
		
	} // end of method changeLifeStyle
	
	
	
	
	private double getCalories(int age, float weight, int sex, int lifeStyle) {
		// RECOMENDACIONES OMS
		double ret1=0;
		// men
		if (age<18) {
			ret1=(17.5*weight)+651;
		} else 	if (age<30) {
			ret1=(15.3*weight)+679;
		} else 	if (age<60) {
			ret1=(11.6*weight)+879;
		} else {
			ret1=(13.5*weight)+487;
		}
		
		if (lifeStyle==0) {
			ret1=ret1*1.6; // sedentario
		} else if (lifeStyle==1) {
			ret1=ret1*1.78; // moderado
		} else {
			ret1=ret1*2.1;	// activo
		}
		
		
		double ret=0;
		// women
		if (age<18) {
			ret=(12.2*weight)+746;
		} else 	if (age<30) {
			ret=(14.7*weight)+496;
		} else 	if (age<60) {
			ret=(8.7*weight)+829;
		} else {
			ret=(10.5*weight)+596;
		}
		
		if (lifeStyle==0) {
			ret=ret*1.5; // sedentario
		} else if (lifeStyle==1) {
			ret=ret*1.64; // moderado
		} else {
			ret=ret*1.9;	// activo
		}
		
		if (sex==1) {
			// si es hombre
			return ret1;
		}
		return ret;
		
	} // end of method getCalories
	
	
	
	private double getCalcium(int age, int sex) {
		
		// RECOMENDACIONES USDA
		double ret1=0;
		// men
		if (age<18) {
			ret1=1300;
		} else 	if (age<50) {
			ret1=800;
		} else 	if (age<70) {
			ret1=1000;
		} else {
			ret1=1200;
		}
		
		double ret=0;
		// women
		if (age<18) {
			ret=1300;
		} else 	if (age<50) {
			ret=800;
		} else 	if (age<70) {
			ret=1200;
		} else {
			ret=1200;
		}
		
		if (sex==1) {
			// si es hombre
			return ret1;
		}
		return ret;
		
	} // end of method getCalcium
	
	
	
	private double getIron(int age, int sex) {
		
		// RECOMENDACIONES US NATIONAL OF HEALTH
		double ret1=0;
		// men
		if (age<18) {
			ret1=11;
		} else 	if (age<60) {
			ret1=8;
		} else {
			ret1=8;
		}
		
		double ret=0;
		// women
		if (age<18) {
			ret=15;
		} else 	if (age<50) {
			ret=18;
		} else {
			ret=8;
		}

		if (sex==1) {
			// si es hombre
			return ret1;
		}
		return ret;
		
	} // end of method getIron
	


	private double getProtein(int age, float weight, int sex) {
		
		// RECOMENDACIONES US NATIONAL ACADEMIC OF SCIENCE
		double ret1=0;
		// men
		if (age<18) {
			ret1=weight*0.9;
		} else 	if (age<50) {
			ret1=weight*0.8;
		} else {
			ret1=weight*0.8;
		}
		
		double ret=0;
		// women
		if (age<18) {
			ret=weight*0.8;
		} else 	if (age<60) {
			ret=weight*0.8;
		} else {
			ret=weight*0.8;
		}
		
		
		if (sex==1) {
			// si es hombre
			return ret1;
		}
		return ret;
		
	} // end of method getProtein

	
	/**
	 * This method return back the navigation to main page.
	 * 
	 * @return - String, the page navigation
	 */
	
	public String returnMain() {
		
		return returnMainPage;
		
	} // END OF METHOD RETURNBACK
	
	
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
		IdentifyBean.keyUser = keyUser;
	}

	public static String[] getUserData() {
		String[]data=userData;
		return data;
	}

	public static void setUserData(String[] userData) {
		IdentifyBean.userData = userData;
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

	public float getImc() {
		imc=(float)(Math.round(imc*100)/100);
		return imc;
	}

	public void setImc(float imc) {
		this.imc = imc;
	}

	public double getConsum() {
		consum=(double)(Math.round(consum*100)/100);
		return consum;
	}

	public double getCalcneed() {
		return calcneed;
	}

	public double getIronneed() {
		return ironneed;
	}

	public double getProtneed() {
		return protneed;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPageBadLogin() {
		return pageBadLogin;
	}

	public String getPageToCreate() {
		return pageToCreate;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public int getLifeStyle() {
		return lifeStyle;
	}

	public void setLifeStyle(int lifeStyle) {
		this.lifeStyle = lifeStyle;
	}



	public String getLogUser() {
		return logUser;
	}



	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}



	public String getPasUser() {
		return pasUser;
	}



	public void setPasUser(String pasUser) {
		this.pasUser = pasUser;
	}



	public int getSexRec() {
		return sexRec;
	}



	public void setSexRec(int sexRec) {
		this.sexRec = sexRec;
	}




} // *************** END OF CLASS
