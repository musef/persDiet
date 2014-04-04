package control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;


import model.DietFoods;

@ManagedBean (name="producto")
@SessionScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String keyUser="MUSABCDEF123456";
	
	private String id;
	private String name;
	private String qtt;
	private String cal;
	private String carbohydrate;
	private String protein;
	private String lipid;
	private String calcium;
	private String iron;
	private String messageRecord;
	private String messageDelete;
	
	private String[] datos;
	private List<String[]> listaDatos;
	private DietFoodsBean fBean;
	
	public ProductoBean() {
		// CONSTRUCTOR
		messageRecord="";
		messageDelete="";
		datos=new String[9];
		// creates a new food list
		fBean=new DietFoodsBean();
		listaDatos=fBean.showAll(keyUser);
		
	}
	
	
	public void changeValues() {
		
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
		
		
	}
	
	
	/**
	 * Este método realizar la grabación de los datos contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo record de DietFoodsBean.
	 */
	
	public void grabar() {
		
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
			
	} // END OF METHOD GRABAR
	
	
	private boolean readyToRecordFood() {
		return true;
	}
	
	
	
	/**
	 * Este método realizar la modificarción de los datos contenidos en el formulario,
	 * grabándolos en la ddbb. Para ello instancia el metodo modify de DietFoodsBean.
	 */
	
	public void modificar() {
		
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
			
	} // END OF METHOD MODIFICAR
	
	
	
	public void borrar() {
		
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
			
	} // END OF METHOD BORRAR
	
	
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





} // ********** END OF CLASS
