package control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import model.DietFoods;
import model.DietMeals;

/**
 * The java bean for the compEast.xhtml control.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@ManagedBean (name="producto")
@SessionScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String keyUser;
	
	// atributos food
	private String id;
	private String name;
	private String qtt;
	private String cal;
	private String carbohydrate;
	private String protein;
	private String lipid;
	private String calcium;
	private String iron;
	
	// atributos meal
	private String idMeal;
	private String nameMeal;
	private String qttMeal;
	private String calMeal;
	private String carbohydrateMeal;
	private String proteinMeal;
	private String lipidMeal;
	private String calciumMeal;
	private String ironMeal;
	
	private String messageRecord;
	private String messageDelete;
	
	private String[] datos;
	private List<String[]> listaDatos;
	private List<String[]> listMeals;
	
	// objetos bean
	private DietFoodsBean fBean;
	private DietMealsBean mBean;
	
	
	
	public ProductoBean() {
		// CONSTRUCTOR
		
		keyUser=IdentifyBean.getKeyUser();
		
		messageRecord="";
		messageDelete="";
		
		datos=new String[9];
		
		// creates a new food list
		fBean=new DietFoodsBean();
		listaDatos=fBean.showAll(keyUser);
		
		// creates a new meals list
		mBean=new DietMealsBean();
		listMeals=mBean.showAll(keyUser);
		
	}
	
	
	
	/**
	 * Este metodo selecciona de la lista de alimentos el valor elegido
	 * en la lista del formulario, y lo muestra
	 */
	
	public void changeValuesFood() {
		
		for (String[] n: listaDatos) {
			if (n[0].equals(id)) {
				name=n[1];
				qtt=n[2];
				cal=n[3];
				carbohydrate=n[4];
				protein=n[5];
				lipid=n[6];
				calcium=n[7];
				iron=n[8];
				System.out.println(id+"--"+name+"--"+cal);
			}
		}
		
	} // END OF METHOD CHANGEVALUESFOOD
	
	
	/**
	 * Este metodo selecciona de la lista de alimentos el valor elegido
	 * en la lista del formulario, y lo muestra
	 */
	
	public void changeValuesMeal() {
		
		for (String[] n: listMeals) {
			if (n[0].equals(idMeal)) {
				nameMeal=n[2];
				qttMeal=n[3];
				calMeal=n[4];
				carbohydrateMeal=n[5];
				proteinMeal=n[6];
				lipidMeal=n[7];
				calciumMeal=n[8];
				ironMeal=n[9];
				System.out.println(idMeal+"--"+nameMeal+"--"+calMeal);
			}
		}
		
	} // END OF METHOD CHANGEVALUESMEAL
	
	
	
	/**
	 * Este método realizar la grabación de los datos contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo record de DietFoodsBean.
	 */
	
	public void recordFood() {
		
		// Primero instancia un objeto DietFoods y almacena los datos del formulario
		if (readyToRecordFood()) {
			
			DietFoods newFood=new DietFoods();
			newFood.setFoodname(name);
			
			int qt=0;
			try {
				qt=(int)Integer.parseInt(qtt);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setQtt(qt);
			
			float ft=0;
			try {
				ft=(float)Float.parseFloat(cal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setCal(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(carbohydrate);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setCarbohydrate(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(protein);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setProtein(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(lipid);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setLipid(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(calcium);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setCalcium(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(iron);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newFood.setIron(ft);
			
			// Recording the new food
			DietFoodsBean recFood=new DietFoodsBean();
			recFood.record(newFood);
			
			// Reading the new list
			fBean=new DietFoodsBean();
			listaDatos=fBean.showAll(keyUser);
			
			// message
			messageRecord="GRABACIÓN REALIZADA";
			System.out.println("GRABACION REALIZADA: "+name);
			
			// cleaning the fields form
			name=" ";
			qtt=" ";
			cal=" ";
			carbohydrate=" ";
			protein=" ";
			lipid=" ";
			calcium=" ";
			iron=" ";
			
		} else {
			// message
			messageRecord="ERROR EN GRABACIÓN";
			System.out.println("NO HA SIDO GRABADO: "+name);
		}
			
	} // END OF METHOD RECORDFOOD
	
	
	/**
	 * Este metodo solamente checks el formulario para evitar valores nulos o vacios
	 * 
	 * @return
	 */
	
	private boolean readyToRecordFood() {
		
		
		if (name==null || name.trim().isEmpty() || name.length()<4 || name.length()>50) {
			return false;
		}
		
		if (qtt==null || qtt.trim().isEmpty()) {
			return false;
		}
		
		if (cal==null || cal.trim().isEmpty()) {
			return false;
		}
		
		if (carbohydrate==null || carbohydrate.trim().isEmpty()) {
			return false;
		}
		
		if (protein==null || protein.trim().isEmpty()) {
			return false;
		}
		
		if (lipid==null || lipid.trim().isEmpty()) {
			return false;
		}
		
		if (calcium==null || calcium.trim().isEmpty()) {
			return false;
		}
		
		if (iron==null || iron.trim().isEmpty()) {
			return false;
		}
		
		name=name.trim();
		qtt=qtt.trim();
		cal=cal.trim();
		carbohydrate=carbohydrate.trim();
		lipid=lipid.trim();
		calcium=calcium.trim();
		iron=iron.trim();
		
		return true;
		
	} // end of READYTORECORDFOOD
	
	
	
	/**
	 * Este método realizar la modificación de los datos contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo modify de DietFoodsBean.
	 */
	
	public void modifyFood() {
		
		// Primero instancia un objeto DietFoods y almacena los datos del formulario
		if (readyToRecordFood()) {
			
			DietFoods newFood=new DietFoods();
			
			long ident=0;
			try {
				ident=(long)Long.parseLong(id);
			} catch (NumberFormatException nf) {
				// do nothing
				ident=0;
			}
			
			if (ident>0) {
				
				newFood.setId(ident);
				
				newFood.setFoodname(name);
				
				int qt=0;
				try {
					qt=(int)Integer.parseInt(qtt);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setQtt(qt);
				
				float ft=0;
				try {
					ft=(float)Float.parseFloat(cal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setCal(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(carbohydrate);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setCarbohydrate(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(protein);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setProtein(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(lipid);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setLipid(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(calcium);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setCalcium(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(iron);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newFood.setIron(ft);
				
				// Modifying the food
				DietFoodsBean modFood=new DietFoodsBean();
				modFood.modify(ident, newFood);
				
				// Reading the new list
				fBean=new DietFoodsBean();
				listaDatos=fBean.showAll(keyUser);
				
				// message
				messageRecord="MODIFICACIÓN REALIZADA";
				System.out.println("MODIFICACIÓN REALIZADA: "+name);
				
				// cleaning the fields form
				name=" ";
				qtt=" ";
				cal=" ";
				carbohydrate=" ";
				protein=" ";
				lipid=" ";
				calcium=" ";
				iron=" ";
				
			} else {
				// message
				messageRecord="ERROR: ID NO LOCALIZADA";
				System.out.println("ERROR: ID NO LOCALIZADA, NO HA SIDO GRABADO: "+name);
			}
	
		} else {
			// message
			messageRecord="ERROR EN MODIFICACIÓN";
			System.out.println("NO HA SIDO MODIFICADO: "+name);
		}
			
	} // END OF METHOD MODIFYFOOD
	
	
	
	/**
	 * Este metodo realiza el borrado de los datos de un alimento mostrado en el formulario
	 */
	
	public void deleteFood() {
		
		// extracting long number
		long ident=0;
		try {
			ident=(long)Long.parseLong(id);
		} catch (NumberFormatException nf) {
			// do nothing
		}
		
		// instantiating delete method in fBean
		if (fBean.delete(ident)) {

			// Reading the new list
			fBean=new DietFoodsBean();
			listaDatos=fBean.showAll(keyUser);
			
			// message
			messageRecord="BORRADO REALIZADO";
			System.out.println("BORRADO REALIZADO: "+name);
			
			// cleaning the fields form
			name=" ";
			qtt=" ";
			cal=" ";
			carbohydrate=" ";
			protein=" ";
			lipid=" ";
			calcium=" ";
			iron=" ";
			
		} else {
			// message
			setMessageDelete("ERROR EN BORRADO");
			System.out.println("NO HA SIDO BORRADO: "+name);
		}
			
	} // END OF METHOD DELETEFOOD
	
	

	/**
	 * 
	 * @param e
	 */
	/*	
	public void muestraDatos(ValueChangeEvent e) {
		
		System.out.println("LLEGAMOS..."+e.getNewValue().toString());	

		try {
			id=e.getNewValue().toString();
			
		} catch (NumberFormatException nf ) {
			// DO NOTHING
			id="0";
		}
		
		for (String[] n: listaDatos) {
			if (n[0].equals(id)) {
				name=n[1];
				qtt=n[2];
				cal=n[3];
				carbohydrate=n[4];
				protein=n[5];
				lipid=n[6];
				calcium=n[7];
				iron=n[8];
				System.out.println(id+"-/"+name+"-/"+cal);
			}
		}

	} // END OF METHOD MUESTRADATOS
	*/
	
	
	
	/**
	 * Este método realizar la grabación de los datos de una nueva comida contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo record de DietMealsBean.
	 */
	
	public void recordMeal() {
		
		// Primero instancia un objeto DietFoods y almacena los datos del formulario
		if (readyToRecordMeal()) {
			
			DietMeals newMeal=new DietMeals();
			
			// tomamos el keyUser de la identificacion
			newMeal.setKeyuser(keyUser);
			
			newMeal.setMealname(nameMeal);
			
			int qt=0;
			try {
				qt=(int)Integer.parseInt(qttMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setQtt(qt);
			
			float ft=0;
			try {
				ft=(float)Float.parseFloat(calMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setCal(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(carbohydrateMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setCarbohydrate(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(proteinMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setProtein(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(lipidMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setLipid(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(calciumMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setCalcium(ft);
			
			ft=0;
			try {
				ft=(float)Float.parseFloat(ironMeal);
			} catch (NumberFormatException nf) {
				// do nothing
			}
			newMeal.setIron(ft);
			
			// Recording the new meal
			mBean.record(newMeal);
			
			// Reading the new list
			mBean=new DietMealsBean();
			listMeals=mBean.showAll(keyUser);
			
			// message
			messageRecord="GRABACIÓN REALIZADA";
			System.out.println("GRABACION REALIZADA: "+name);
			
			// cleaning the fields form
			nameMeal=" ";
			qttMeal=" ";
			calMeal=" ";
			carbohydrateMeal=" ";
			proteinMeal=" ";
			lipidMeal=" ";
			calciumMeal=" ";
			ironMeal=" ";
			
		} else {
			// message
			messageRecord="ERROR EN GRABACIÓN";
			System.out.println("NO HA SIDO GRABADO: "+name);
		}
			
	} // END OF METHOD RECORDMEAL
	
	
	
	/**
	 * Este metodo solamente checks el formulario para evitar valores nulos o vacios
	 * 
	 * @return
	 */
	
	private boolean readyToRecordMeal() {
		
		System.out.println("AQUI LLEGAMOS");
		if (keyUser==null || keyUser.isEmpty() || keyUser.length()>15) {
			System.out.println("NO KEYUSER FOUND");
			return false;
		}
		
		if (nameMeal==null || nameMeal.trim().isEmpty() || nameMeal.length()<4 || nameMeal.length()>50) {
			return false;
		}
		
		if (qttMeal==null || qttMeal.trim().isEmpty()) {
			return false;
		}
		
		if (calMeal==null || calMeal.trim().isEmpty()) {
			return false;
		}
		
		if (carbohydrateMeal==null || carbohydrateMeal.trim().isEmpty()) {
			return false;
		}
		
		if (proteinMeal==null || proteinMeal.trim().isEmpty()) {
			return false;
		}
		
		if (lipidMeal==null || lipidMeal.trim().isEmpty()) {
			return false;
		}
		
		if (calciumMeal==null || calciumMeal.trim().isEmpty()) {
			return false;
		}
		
		if (ironMeal==null || ironMeal.trim().isEmpty()) {
			return false;
		}
		
		nameMeal=nameMeal.trim();
		qttMeal=qttMeal.trim();
		calMeal=calMeal.trim();
		carbohydrateMeal=carbohydrateMeal.trim();
		lipidMeal=lipidMeal.trim();
		calciumMeal=calciumMeal.trim();
		ironMeal=ironMeal.trim();
		
		return true;
		
	} // END OF READYTORECORDMEAL
	
	
	
	/**
	 * Este método realizar la modificación de los datos contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo modify de DietFoodsBean.
	 */
	
	public void modifyMeal() {
		
		// Primero instancia un objeto DietFoods y almacena los datos del formulario
		if (readyToRecordMeal()) {
			
			long ident=0;
			try {
				ident=(long)Long.parseLong(idMeal);
			} catch (NumberFormatException nf) {
				// do nothing
				System.out.println("**"+ident+"\\"+idMeal);
				ident=0;
			}
			
			if (ident>0) {
			
				DietMeals newMeal=new DietMeals();
				
				// tomamos el keyUser de la identificacion
				newMeal.setKeyuser(keyUser);
				
				newMeal.setMealname(nameMeal);
				
				int qt=0;
				try {
					qt=(int)Integer.parseInt(qttMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setQtt(qt);
				
				float ft=0;
				try {
					ft=(float)Float.parseFloat(calMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setCal(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(carbohydrateMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setCarbohydrate(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(proteinMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setProtein(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(lipidMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setLipid(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(calciumMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setCalcium(ft);
				
				ft=0;
				try {
					ft=(float)Float.parseFloat(ironMeal);
				} catch (NumberFormatException nf) {
					// do nothing
				}
				newMeal.setIron(ft);
				
				// Modifying the food
				mBean.modify(ident, newMeal);
				
				// Reading the new list
				mBean=new DietMealsBean();
				listMeals=mBean.showAll(keyUser);
				
				// message
				messageRecord="MODIFICACIÓN REALIZADA";
				System.out.println("MODIFICACIÓN REALIZADA: "+name);
				
				// cleaning the fields form
				nameMeal=" ";
				qttMeal=" ";
				calMeal=" ";
				carbohydrateMeal=" ";
				proteinMeal=" ";
				lipidMeal=" ";
				calciumMeal=" ";
				ironMeal=" ";
				
			} else {
				// message
				messageRecord="ERROR: ID NO LOCALIZADA";
				System.out.println("ERROR: ID NO LOCALIZADA, NO HA SIDO GRABADO: "+nameMeal);
			}
	
		} else {
			// message
			messageRecord="ERROR EN MODIFICACIÓN";
			System.out.println("NO HA SIDO MODIFICADO: "+nameMeal);
		}
			
	} // END OF METHOD MODIFYMEAL
	
	
	
	
	/**
	 * Este metodo realiza el borrado de los datos de una comida mostrado en el formulario
	 */
	
	public void deleteMeal() {
		
		// extracting long number
		long ident=0;
		try {
			ident=(long)Long.parseLong(idMeal);
		} catch (NumberFormatException nf) {
			// do nothing
			System.out.println("**"+ident+"//"+idMeal);
		}
		
		// instantiating delete method in fBean
		if (mBean.delete(ident)) {

			// Reading the new list
			mBean=new DietMealsBean();
			listMeals=mBean.showAll(keyUser);
			
			// message
			messageRecord="BORRADO REALIZADO";
			System.out.println("BORRADO REALIZADO: "+name);
			
			// cleaning the fields form
			nameMeal=" ";
			qttMeal=" ";
			calMeal=" ";
			carbohydrateMeal=" ";
			proteinMeal=" ";
			lipidMeal=" ";
			calciumMeal=" ";
			ironMeal=" ";
			
		} else {
			// message
			setMessageDelete("ERROR EN BORRADO");
			System.out.println("NO HA SIDO BORRADO: "+name);
		}
			
	} // END OF METHOD DELETEMEAL
	
	
	
	// GETTERS AND SETTERS
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getQtt() {
		return qtt;
	}
	public void setQtt(String qtt) {
		this.qtt = qtt;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public String getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(String carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getLipid() {
		return lipid;
	}
	public void setLipid(String lipid) {
		this.lipid = lipid;
	}
	public String getCalcium() {
		return calcium;
	}
	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}
	public String getIron() {
		return iron;
	}
	public void setIron(String iron) {
		this.iron = iron;
	}

	public String[] getDatos() {
		return datos;
	}

	public void setDatos(String[] datos) {
		this.datos = datos;
	}

	public List<String[]> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(List<String[]> listaDatos) {
		this.listaDatos = listaDatos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageRecord() {
		return messageRecord;
	}

	public void setMessageRecord(String messageRecord) {
		this.messageRecord = messageRecord;
	}

	public String getMessageDelete() {
		return messageDelete;
	}

	public void setMessageDelete(String messageDelete) {
		this.messageDelete = messageDelete;
	}

	public String getIdMeal() {
		return idMeal;
	}

	public void setIdMeal(String idMeal) {
		this.idMeal = idMeal;
	}

	public String getNameMeal() {
		return nameMeal;
	}

	public void setNameMeal(String nameMeal) {
		this.nameMeal = nameMeal;
	}

	public String getQttMeal() {
		return qttMeal;
	}

	public void setQttMeal(String qttMeal) {
		this.qttMeal = qttMeal;
	}

	public String getCalMeal() {
		return calMeal;
	}

	public void setCalMeal(String calMeal) {
		this.calMeal = calMeal;
	}

	public String getCarbohydrateMeal() {
		return carbohydrateMeal;
	}

	public void setCarbohydrateMeal(String carbohydrateMeal) {
		this.carbohydrateMeal = carbohydrateMeal;
	}

	public String getProteinMeal() {
		return proteinMeal;
	}

	public void setProteinMeal(String proteinMeal) {
		this.proteinMeal = proteinMeal;
	}

	public String getLipidMeal() {
		return lipidMeal;
	}

	public void setLipidMeal(String lipidMeal) {
		this.lipidMeal = lipidMeal;
	}

	public String getCalciumMeal() {
		return calciumMeal;
	}

	public void setCalciumMeal(String calciumMeal) {
		this.calciumMeal = calciumMeal;
	}

	public String getIronMeal() {
		return ironMeal;
	}

	public void setIronMeal(String ironMeal) {
		this.ironMeal = ironMeal;
	}

	public List<String[]> getListMeals() {
		return listMeals;
	}

	public void setListMeals(List<String[]> listMeals) {
		this.listMeals = listMeals;
	}





} // ********** END OF CLASS
