package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.DietMeals;



@ManagedBean
@SessionScoped

public class ComidasBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private List<String[]> listaDesayunos;
	private List<String[]> listaTentempies;
	private List<String[]> listaComidas;
	private List<String[]> listaMeriendas;
	private List<String[]> listaCenas;
	private List<String[]> listaAlimentos;
	
	// zona comidas
	private String comida1;
	private String comida2;
	private String comida3;
	private String comida4;
	private String comida5;
	
	// zona calorias
	private double caloriasDia;
	private double caloriasSemana;
	private double caloriasMes;
	
	private double caloriasTotales;
	private double calorias1;
	private double calorias2;
	private double calorias3;
	private double calorias4;
	private double calorias5;	
	
	private final int udMedidaStandard=100;
	private int medida1;
	private int medida2;
	private int medida3;
	private int medida4;
	private int medida5;
	
	private double caloriasTotalesA;
	private double calorias11;
	private double calorias12;
	private double calorias13;
	private double calorias14;
	private double calorias15;
	private double calorias21;
	private double calorias22;
	private double calorias23;
	private double calorias24;
	private double calorias25;
	private double calorias31;
	private double calorias32;
	private double calorias33;
	private double calorias34;
	private double calorias35;	
	
	private double medida11;
	private double medida12;
	private double medida13;
	private double medida14;
	private double medida15;
	private double medida21;
	private double medida22;
	private double medida23;
	private double medida24;
	private double medida25;
	private double medida31;
	private double medida32;
	private double medida33;
	private double medida34;
	private double medida35;	
	
	// zona calorias por ingesta
	private double caloriasDes;
	private double caloriasTen;
	private double caloriasCom;
	private double caloriasMer;
	private double caloriasCen;
	
	// zona calcio
	private double calcioDia;
	private double calcioSemana;
	private double calcioMes;
	// zona hierro
	private double hierroDia;
	private double hierroSemana;
	private double hierroMes;
	
	// zona desviaciones
	private double desvCalDia;
	private double desvCalSem;
	private double desvCalMes;
	private double desvCacDia;
	private double desvCacSem;
	private double desvCacMes;
	private double desvFerDia;
	private double desvFerSem;
	private double desvFerMes;
	
	private String keyUser;
	
	
	
	public ComidasBean () {
		// CONSTRUCTOR
		
		listaDesayunos=new ArrayList<String[]>();
		listaTentempies=new ArrayList<String[]>();
		listaComidas=new ArrayList<String[]>();
		listaMeriendas=new ArrayList<String[]>();
		listaCenas=new ArrayList<String[]>();
		
		// inicializando resumen
		caloriasDia=0;
		caloriasSemana=0;
		caloriasMes=0;
		calcioDia=0;		
		calcioSemana=0;
		calcioMes=0;
		hierroDia=0;
		hierroSemana=0;
		hierroMes=0;
		
		calorias1=0;
		calorias2=0;
		calorias3=0;
		calorias4=0;
		calorias5=0;	
		medida1=100;
		medida2=100;
		medida3=100;
		medida4=100;
		medida5=100;
		
		calorias11=0;
		calorias12=0;
		calorias13=0;
		calorias14=0;
		calorias15=0;
		calorias21=0;
		calorias22=0;
		calorias23=0;
		calorias24=0;
		calorias25=0;
		calorias31=0;
		calorias32=0;
		calorias33=0;
		calorias34=0;
		calorias35=0;		
		medida11=100;
		medida12=100;
		medida13=100;
		medida14=100;
		medida15=100;
		medida21=100;
		medida22=100;
		medida23=100;
		medida24=100;
		medida25=100;
		medida31=100;
		medida32=100;
		medida33=100;
		medida34=100;
		medida35=100;		

		//recordMeal();
		
		DietMealsBean desayuno= new DietMealsBean();
		listaDesayunos=desayuno.showAll(IdentifyBean.getKeyUser());
		listaTentempies=listaDesayunos;
		listaComidas=listaDesayunos;
		listaMeriendas=listaDesayunos;
		listaCenas=listaDesayunos;
		
		DietFoodsBean foods=new DietFoodsBean();
		listaAlimentos=foods.showAll(IdentifyBean.getKeyUser());
		
	} // end of constructor


	
	
	/**
	 * Este metodo implementa los siguientes calculos:
	 * a) El consumo total de calorias diarias, tanto proviniendo de alimentos como de comidas
	 * b) El consumo total de calorias proviniendo exclusivamente de COMIDAS
	 * c) Las desviaciones de los parametros calorias, calcio y hierro respeto a los consumos
	 * 		calculados.
	 * 
	 * @return El total de calorias diarias ingeridas exclusivamente por comidas
	 */
	
	public void calcComida () {
		
		// primero calcula las calorias totales exclusivamente por comidas
		// IMPORTANTE: en el caso comidas no se pondera el valor, sino que se toma directamente
		// el valor de DDBB. Las comidas grabadas son una unidad no ponderable.
		DietMealsBean mB=new DietMealsBean();
		
		float cc1,cc2,cc3,cc4,cc5=0;
		float h1,h2,h3,h4,h5=0;
		
		try {
			String[] c1=mB.read((long)Long.parseLong(comida1));
			calorias1=(float)Float.parseFloat(c1[4])*medida1/(float)Float.parseFloat(c1[3]);
			calorias1=((double)(Math.round(calorias1*100)))/100;
			h1=(float)Float.parseFloat(c1[9])*medida1/(float)Float.parseFloat(c1[3]);
			cc1=(float)Float.parseFloat(c1[8])*medida1/(float)Float.parseFloat(c1[3]);
		} catch (NumberFormatException nf) {
			calorias1=0;
			h1=0;
			cc1=0;
		}
		
		try {
			String[] c2=mB.read((long)Long.parseLong(comida2));
			calorias2=(float)Float.parseFloat(c2[4])*medida2/(float)Float.parseFloat(c2[3]);
			calorias2=((double)(Math.round(calorias2*100)))/100;
			h2=(float)Float.parseFloat(c2[9])*medida2/(float)Float.parseFloat(c2[3]);
			cc2=(float)Float.parseFloat(c2[8])*medida2/(float)Float.parseFloat(c2[3]);
		} catch (NumberFormatException nf) {
			calorias2=0;
			h2=0;
			cc2=0;
		}		
		
		try {
			String[] c3=mB.read((long)Long.parseLong(comida3));
			calorias3=(float)Float.parseFloat(c3[4])*medida3/(float)Float.parseFloat(c3[3]);
			calorias3=((double)(Math.round(calorias3*100)))/100;
			h3=(float)Float.parseFloat(c3[9])*medida3/(float)Float.parseFloat(c3[3]);
			cc3=(float)Float.parseFloat(c3[8])*medida3/(float)Float.parseFloat(c3[3]);
		} catch (NumberFormatException nf) {
			calorias3=0;
			h3=0;
			cc3=0;
		}
		
		try {
			String[] c4=mB.read((long)Long.parseLong(comida4));
			calorias4=(float)Float.parseFloat(c4[4])*medida4/(float)Float.parseFloat(c4[3]);
			calorias4=((double)(Math.round(calorias4*100)))/100;
			h4=(float)Float.parseFloat(c4[9])*medida4/(float)Float.parseFloat(c4[3]);
			cc4=(float)Float.parseFloat(c4[8])*medida4/(float)Float.parseFloat(c4[3]);
		} catch (NumberFormatException nf) {
			calorias4=0;
			h4=0;
			cc4=0;
		}
		
		try {
			String[] c5=mB.read((long)Long.parseLong(comida5));
			calorias5=(float)Float.parseFloat(c5[4])*medida5/(float)Float.parseFloat(c5[3]);
			calorias5=((double)(Math.round(calorias5*100)))/100;
			cc5=(float)Float.parseFloat(c5[8])*medida5/(float)Float.parseFloat(c5[3]);
			h5=(float)Float.parseFloat(c5[9])*medida5/(float)Float.parseFloat(c5[3]);
		} catch (NumberFormatException nf) {
			calorias5=0;
			h5=0;
			cc5=0;
		}
		
		caloriasTotales=(calorias1)+(calorias2)+
				(calorias3)+(calorias4)+(calorias5);
		
		// segundo, se calcula el total de calorias diarias, teniendo en cuenta tambien las
		// que, si las hubiere, procedan exclusivamente de alimentos.
		caloriasDia=caloriasTotales+caloriasTotalesA;
		caloriasDia=((double)(Math.round(caloriasDia*100)))/100;
		
		// tercero, se calculan los consumos de calcio y hierro
		calcioDia=cc1+cc2+cc3+cc4+cc5;
		calcioDia=((double)(Math.round(calcioDia*100)))/100;
		hierroDia=h1+h2+h3+h4+h5;
		hierroDia=((double)(Math.round(hierroDia*100)))/100;
		
		// tercero, se calculan las desviaciones de los parametros calorias, calcio y hierro.
		calcDesv();
		
		//return caloriasTotales;
		
	} // end of caloriasTotales
	
	
	
	/**
	 * Este metodo implementa los siguientes calculos:
	 * a) El consumo total de calorias diarias, tanto proviniendo de alimentos como de comidas
	 * b) El consumo total de calorias proviniendo exclusivamente de ALIMENTOS
	 * c) Las desviaciones de los parametros calorias, calcio y hierro respeto a los consumos
	 * 		calculados.
	 * 
	 * @return El total de calorias diarias ingeridas exclusivamente por alimentos
	 */
	
	public double caloriasTotalA() {
		
		// primero calcula las calorias totales exclusivamente por comidas
		// IMPORTANTE: en el caso alimentos SI se pondera el valor, tomando el valor
		// grabado como ud, y se pondera en funcion de la cantidad introducida en el formulario
		
		// se calculan los 3 alimentos principales de cada ingesta introducidos en formulario
		caloriasDes=(calorias11*medida11/udMedidaStandard)+(calorias21*medida21/udMedidaStandard)+(calorias31*medida31/udMedidaStandard);
		caloriasTen=(calorias12*medida12/udMedidaStandard)+(calorias22*medida22/udMedidaStandard)+(calorias32*medida32/udMedidaStandard);
		caloriasCom=(calorias13*medida13/udMedidaStandard)+(calorias23*medida23/udMedidaStandard)+(calorias33*medida33/udMedidaStandard);
		caloriasMer=(calorias14*medida14/udMedidaStandard)+(calorias24*medida24/udMedidaStandard)+(calorias34*medida34/udMedidaStandard);
		caloriasCen=(calorias15*medida15/udMedidaStandard)+(calorias25*medida25/udMedidaStandard)+(calorias35*medida35/udMedidaStandard);
		
		caloriasTotalesA=caloriasDes+caloriasTen+caloriasCom+caloriasMer+caloriasCen;
		caloriasTotalesA=((double)(Math.round(caloriasTotalesA*100)))/100;
		
		// segundo, se calcula el total de calorias diarias, teniendo en cuenta tambien las
		// que, si las hubiere, procedan exclusivamente de alimentos.	
		caloriasDia=caloriasTotales+caloriasTotalesA;
		caloriasDia=((double)(Math.round(caloriasDia*100)))/100;
		
		// tercero, se calculan las desviaciones de los parametros calorias, calcio y hierro.
		calcDesv();
		
		return caloriasTotalesA;
		
	} // end of caloriastotalesA
	
/*
	private boolean recordMeal() {
		
		DietMeals meal=new DietMeals();
		
		meal.setMealname("COMIDA DE PRUEBA");
		meal.setCal(100);
		meal.setCalcium(0);
		meal.setCarbohydrate(0);
		meal.setIron(0);
		meal.setLipid(0);
		meal.setProtein(0);
		meal.setQtt(100);
		meal.setKeyuser(keyUser);
		
		DietMealsBean mealB=new DietMealsBean();
		if (!mealB.record(meal)) {
			System.err.println("ERROR GRABANDO COMIDA");
			return false;
		}
		
		return true;
	}
	*/
	
	
	/**
	 * Este metodo implementa el calculo de las desviaciones en el consumo
	 * de calorias, calcio y hierro respecto a las cantidades objetivos del
	 * usuario.
	 * 
	 * Almacena directamente el valor en las variables.
	 */
	private void calcDesv() {
		
		IdentifyBean idB=new IdentifyBean();
		
		desvCalDia=(caloriasDia-IdentifyBean.consum)*100/IdentifyBean.consum;
		desvCalDia=((double)(Math.round(desvCalDia*100)))/100;
		desvCalSem=(caloriasSemana-IdentifyBean.consum)*100/IdentifyBean.consum;
		desvCalSem=((double)(Math.round(desvCalSem*100)))/100;
		desvCalMes=(caloriasMes-IdentifyBean.consum)*100/IdentifyBean.consum;
		desvCalMes=((double)(Math.round(desvCalMes*100)))/100;
		
		desvCacDia=(calcioDia-idB.getCalcneed())*100/idB.getCalcneed();
		desvCacDia=((double)(Math.round(desvCacDia*100)))/100;
		desvCacSem=(calcioSemana-idB.getCalcneed())*100/idB.getCalcneed();
		desvCacSem=((double)(Math.round(desvCacSem*100)))/100;
		desvCacMes=(calcioMes-idB.getCalcneed())*100/idB.getCalcneed();
		desvCacMes=((double)(Math.round(desvCacMes*100)))/100;
		
		desvFerDia=(hierroDia-idB.getIronneed())*100/idB.getIronneed();
		desvFerDia=((double)(Math.round(desvFerDia*100)))/100;
		desvFerSem=(hierroSemana-idB.getIronneed())*100/idB.getIronneed();
		desvFerSem=((double)(Math.round(desvFerSem*100)))/100;
		desvFerMes=(hierroMes-idB.getIronneed())*100/idB.getIronneed();
		desvFerMes=((double)(Math.round(desvFerMes*100)))/100;
		
	} // END OF METHOD CALCDESV
	
	
	
	// GETTERS AND SETTERS

	public List<String[]> getListaDesayunos() {
		return listaDesayunos;
	}

	public void setListaDesayunos(List<String[]> listaDesayunos) {
		this.listaDesayunos = listaDesayunos;
	}

	public List<String[]> getListaTentempies() {
		return listaTentempies;
	}

	public void setListaTentempies(List<String[]> listaTentempies) {
		this.listaTentempies = listaTentempies;
	}

	public List<String[]> getListaComidas() {
		return listaComidas;
	}

	public void setListaComidas(List<String[]> listaComidas) {
		this.listaComidas = listaComidas;
	}

	public List<String[]> getListaMeriendas() {
		return listaMeriendas;
	}

	public void setListaMeriendas(List<String[]> listaMeriendas) {
		this.listaMeriendas = listaMeriendas;
	}

	public List<String[]> getListaCenas() {
		return listaCenas;
	}

	public void setListaCenas(List<String[]> listaCenas) {
		this.listaCenas = listaCenas;
	}

	public List<String[]> getListaAlimentos() {
		return listaAlimentos;
	}

	public void setListaAlimentos(List<String[]> listaAlimentos) {
		this.listaAlimentos = listaAlimentos;
	}

	public double getCaloriasTotales() {
		return caloriasTotales;
	}

	public void setCaloriasTotales(double caloriasTotales) {
		this.caloriasTotales = caloriasTotales;
	}

	public double getCalorias1() {
		return calorias1;
	}

	public void setCalorias1(double calorias1) {
		this.calorias1 = calorias1;
	}

	public double getCalorias2() {
		return calorias2;
	}

	public void setCalorias2(double calorias2) {
		this.calorias2 = calorias2;
	}

	public double getCalorias3() {
		return calorias3;
	}

	public void setCalorias3(double calorias3) {
		this.calorias3 = calorias3;
	}

	public double getCalorias4() {
		return calorias4;
	}

	public void setCalorias4(double calorias4) {
		this.calorias4 = calorias4;
	}

	public double getCalorias5() {
		return calorias5;
	}

	public void setCalorias5(double calorias5) {
		this.calorias5 = calorias5;
	}

	public int getMedida1() {
		return medida1;
	}

	public void setMedida1(int medida1) {
		this.medida1 = medida1;
	}

	public int getMedida2() {
		return medida2;
	}

	public void setMedida2(int medida2) {
		this.medida2 = medida2;
	}

	public int getMedida3() {
		return medida3;
	}

	public void setMedida3(int medida3) {
		this.medida3 = medida3;
	}

	public int getMedida4() {
		return medida4;
	}

	public void setMedida4(int medida4) {
		this.medida4 = medida4;
	}

	public int getMedida5() {
		return medida5;
	}

	public void setMedida5(int medida5) {
		this.medida5 = medida5;
	}

	public double getCaloriasTotalesA() {
		return caloriasTotalesA;
	}

	public void setCaloriasTotalesA(double caloriasTotalesA) {
		this.caloriasTotalesA = caloriasTotalesA;
	}

	public double getCalorias11() {
		return calorias11;
	}

	public void setCalorias11(double calorias11) {
		this.calorias11 = calorias11;
	}

	public double getCalorias12() {
		return calorias12;
	}

	public void setCalorias12(double calorias12) {
		this.calorias12 = calorias12;
	}

	public double getCalorias13() {
		return calorias13;
	}

	public void setCalorias13(double calorias13) {
		this.calorias13 = calorias13;
	}

	public double getCalorias14() {
		return calorias14;
	}

	public void setCalorias14(double calorias14) {
		this.calorias14 = calorias14;
	}

	public double getCalorias15() {
		return calorias15;
	}

	public void setCalorias15(double calorias15) {
		this.calorias15 = calorias15;
	}

	public double getCalorias21() {
		return calorias21;
	}

	public void setCalorias21(double calorias21) {
		this.calorias21 = calorias21;
	}

	public double getCalorias22() {
		return calorias22;
	}

	public void setCalorias22(double calorias22) {
		this.calorias22 = calorias22;
	}

	public double getCalorias23() {
		return calorias23;
	}

	public void setCalorias23(double calorias23) {
		this.calorias23 = calorias23;
	}

	public double getCalorias24() {
		return calorias24;
	}

	public void setCalorias24(double calorias24) {
		this.calorias24 = calorias24;
	}

	public double getCalorias25() {
		return calorias25;
	}

	public void setCalorias25(double calorias25) {
		this.calorias25 = calorias25;
	}

	public double getCalorias31() {
		return calorias31;
	}

	public void setCalorias31(double calorias31) {
		this.calorias31 = calorias31;
	}

	public double getCalorias32() {
		return calorias32;
	}

	public void setCalorias32(double calorias32) {
		this.calorias32 = calorias32;
	}

	public double getCalorias33() {
		return calorias33;
	}

	public void setCalorias33(double calorias33) {
		this.calorias33 = calorias33;
	}

	public double getCalorias34() {
		return calorias34;
	}

	public void setCalorias34(double calorias34) {
		this.calorias34 = calorias34;
	}

	public double getCalorias35() {
		return calorias35;
	}

	public void setCalorias35(double calorias35) {
		this.calorias35 = calorias35;
	}

	public double getMedida11() {
		return medida11;
	}

	public void setMedida11(double medida11) {
		this.medida11 = medida11;
	}

	public double getMedida12() {
		return medida12;
	}

	public void setMedida12(double medida12) {
		this.medida12 = medida12;
	}

	public double getMedida13() {
		return medida13;
	}

	public void setMedida13(double medida13) {
		this.medida13 = medida13;
	}

	public double getMedida14() {
		return medida14;
	}

	public void setMedida14(double medida14) {
		this.medida14 = medida14;
	}

	public double getMedida15() {
		return medida15;
	}

	public void setMedida15(double medida15) {
		this.medida15 = medida15;
	}

	public double getMedida21() {
		return medida21;
	}

	public void setMedida21(double medida21) {
		this.medida21 = medida21;
	}

	public double getMedida22() {
		return medida22;
	}

	public void setMedida22(double medida22) {
		this.medida22 = medida22;
	}

	public double getMedida23() {
		return medida23;
	}

	public void setMedida23(double medida23) {
		this.medida23 = medida23;
	}

	public double getMedida24() {
		return medida24;
	}

	public void setMedida24(double medida24) {
		this.medida24 = medida24;
	}

	public double getMedida25() {
		return medida25;
	}

	public void setMedida25(double medida25) {
		this.medida25 = medida25;
	}

	public double getMedida31() {
		return medida31;
	}

	public void setMedida31(double medida31) {
		this.medida31 = medida31;
	}

	public double getMedida32() {
		return medida32;
	}
	
	public void setMedida32(double medida32) {
		this.medida32 = medida32;
	}

	public double getMedida33() {
		return medida33;
	}

	public void setMedida33(double medida33) {
		this.medida33 = medida33;
	}

	public double getMedida34() {
		return medida34;
	}

	public void setMedida34(double medida34) {
		this.medida34 = medida34;
	}

	public double getMedida35() {
		return medida35;
	}

	public void setMedida35(double medida35) {
		this.medida35 = medida35;
	}

	public double getCaloriasDia() {
		return caloriasDia;
	}

	public void setCaloriasDia(double caloriasDia) {
		this.caloriasDia = caloriasDia;
	}

	public double getCaloriasSemana() {
		return caloriasSemana;
	}

	public void setCaloriasSemana(double caloriasSemana) {
		this.caloriasSemana = caloriasSemana;
	}

	public double getCaloriasMes() {
		return caloriasMes;
	}

	public void setCaloriasMes(double caloriasMes) {
		this.caloriasMes = caloriasMes;
	}

	public double getCalcioDia() {
		return calcioDia;
	}

	public void setCalcioDia(double calcioDia) {
		this.calcioDia = calcioDia;
	}

	public double getCalcioSemana() {
		return calcioSemana;
	}

	public void setCalcioSemana(double calcioSemana) {
		this.calcioSemana = calcioSemana;
	}

	public double getCalcioMes() {
		return calcioMes;
	}

	public void setCalcioMes(double calcioMes) {
		this.calcioMes = calcioMes;
	}

	public double getHierroDia() {
		return hierroDia;
	}

	public void setHierroDia(double hierroDia) {
		this.hierroDia = hierroDia;
	}


	public double getHierroSemana() {
		return hierroSemana;
	}

	public void setHierroSemana(double hierroSemana) {
		this.hierroSemana = hierroSemana;
	}


	public double getHierroMes() {
		return hierroMes;
	}

	public void setHierroMes(double hierroMes) {
		this.hierroMes = hierroMes;
	}

	public String getKeyUser() {
		return keyUser;
	}

	public void setKeyUser(String keyUser) {
		this.keyUser = keyUser;
	}

	public int getUdMedidaStandard() {
		return udMedidaStandard;
	}

	public double getDesvCalDia() {
		return desvCalDia;
	}
	
	public void setDesvCalDia(double desvCalDia) {
		this.desvCalDia = desvCalDia;
	}

	public double getDesvCalSem() {
		return desvCalSem;
	}

	public void setDesvCalSem(double desvCalSem) {
		this.desvCalSem = desvCalSem;
	}

	public double getDesvCalMes() {
		return desvCalMes;
	}

	public void setDesvCalMes(double desvCalMes) {
		this.desvCalMes = desvCalMes;
	}

	public double getDesvCacDia() {
		return desvCacDia;
	}

	public void setDesvCacDia(double desvCacDia) {
		this.desvCacDia = desvCacDia;
	}

	public double getDesvCacSem() {
		return desvCacSem;
	}

	public void setDesvCacSem(double desvCacSem) {
		this.desvCacSem = desvCacSem;
	}

	public double getDesvCacMes() {
		return desvCacMes;
	}

	public void setDesvCacMes(double desvCacMes) {
		this.desvCacMes = desvCacMes;
	}

	public double getDesvFerDia() {
		return desvFerDia;
	}

	public void setDesvFerDia(double desvFerDia) {
		this.desvFerDia = desvFerDia;
	}

	public double getDesvFerSem() {
		return desvFerSem;
	}

	public void setDesvFerSem(double desvFerSem) {
		this.desvFerSem = desvFerSem;
	}

	public double getDesvFerMes() {
		return desvFerMes;
	}

	public void setDesvFerMes(double desvFerMes) {
		this.desvFerMes = desvFerMes;
	}




	public double getCaloriasDes() {
		return caloriasDes;
	}




	public void setCaloriasDes(double caloriasDes) {
		this.caloriasDes = caloriasDes;
	}




	public double getCaloriasTen() {
		return caloriasTen;
	}




	public void setCaloriasTen(double caloriasTen) {
		this.caloriasTen = caloriasTen;
	}




	public double getCaloriasCom() {
		return caloriasCom;
	}




	public void setCaloriasCom(double caloriasCom) {
		this.caloriasCom = caloriasCom;
	}




	public double getCaloriasMer() {
		return caloriasMer;
	}




	public void setCaloriasMer(double caloriasMer) {
		this.caloriasMer = caloriasMer;
	}




	public double getCaloriasCen() {
		return caloriasCen;
	}




	public void setCaloriasCen(double caloriasCen) {
		this.caloriasCen = caloriasCen;
	}




	public String getComida1() {
		return comida1;
	}




	public void setComida1(String comida1) {
		this.comida1 = comida1;
	}




	public String getComida2() {
		return comida2;
	}




	public void setComida2(String comida2) {
		this.comida2 = comida2;
	}




	public String getComida3() {
		return comida3;
	}




	public void setComida3(String comida3) {
		this.comida3 = comida3;
	}




	public String getComida4() {
		return comida4;
	}




	public void setComida4(String comida4) {
		this.comida4 = comida4;
	}




	public String getComida5() {
		return comida5;
	}




	public void setComida5(String comida5) {
		this.comida5 = comida5;
	}




	
	
	
	

	
	
} // ***************** END OF CLASS