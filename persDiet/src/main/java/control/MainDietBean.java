package control;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import model.DietCalendar;

/**
 * The java bean MAIN for the mainDiet.xhtml control.
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

	private List<String[]> listaDesayunos;
	private List<String[]> listaTentempies;
	private List<String[]> listaComidas;
	private List<String[]> listaMeriendas;
	private List<String[]> listaCenas;
	private List<String[]> listaAlimentos;
	
	private int activeTab;
	private String keyUser;
	
	private java.util.Date dateCalendar;
	private String dateSelected;
	private String dateToShow;
	private String today;
	
	
	
	// ********* meals zone
	
	// id's meals
	private String comida1;
	private String comida2;
	private String comida3;
	private String comida4;
	private String comida5;
	
	// qtt meals
	private final int udMedidaStandard=100;
	private int medida1;
	private int medida2;
	private int medida3;
	private int medida4;
	private int medida5;
	
	// calories meals
	private double calorias1;
	private double calorias2;
	private double calorias3;
	private double calorias4;
	private double calorias5;	
	

	// ********* foods zone

	// id's meals
	private String foods11;
	private String foods12;
	private String foods13;
	private String foods21;
	private String foods22;
	private String foods23;
	private String foods31;
	private String foods32;
	private String foods33;
	private String foods41;
	private String foods42;
	private String foods43;
	private String foods51;
	private String foods52;
	private String foods53;
	
	// qtt foods
	private int medida11;
	private int medida12;
	private int medida13;
	private int medida21;
	private int medida22;
	private int medida23;
	private int medida31;
	private int medida32;
	private int medida33;
	private int medida41;
	private int medida42;
	private int medida43;
	private int medida51;
	private int medida52;
	private int medida53;
	
	// calories foods
	private double calorias11;
	private double calorias12;
	private double calorias13;
	private double calorias21;
	private double calorias22;
	private double calorias23;
	private double calorias31;
	private double calorias32;
	private double calorias33;
	private double calorias41;
	private double calorias42;
	private double calorias43;
	private double calorias51;
	private double calorias52;
	private double calorias53;
	
	// ********* summary of variables zone
	private double caloriasDes;
	private double caloriasTen;
	private double caloriasCom;
	private double caloriasMer;
	private double caloriasCen;
	
	
	// ********** statistics west zone
	
	// zona calorias
	private double caloriasDia;
	private double caloriasDiaDish;
	private double caloriasDiaFood;
	private double caloriasSemana;
	private double caloriasMes;
	
	// zona calcio
	private double calcioDia;
	private double calcioDiaDish;
	private double calcioDiaFood;
	private double calcioSemana;
	private double calcioMes;
	// zona hierro
	private double hierroDia;
	private double hierroDiaDish;
	private double hierroDiaFood;
	private double hierroSemana;
	private double hierroMes;
	
	// desv zone
	private double desvCalDia;
	private double desvCalSem;
	private double desvCalMes;
	private double desvCacDia;
	private double desvCacSem;
	private double desvCacMes;
	private double desvFerDia;
	private double desvFerSem;
	private double desvFerMes;
	

	
	
	
	
	public MainDietBean () {
		// CONSTRUCTOR
	
		// get keyUser
		keyUser=IdentifyBean.getKeyUser();
		
		// tab donde se inicia
		activeTab=0;
		
		// inicializando resumen
		
		caloriasDia=0;
		caloriasDiaDish=0;
		caloriasDiaFood=0;
		caloriasSemana=0;
		caloriasMes=0;
		calcioDia=0;		
		calcioSemana=0;
		calcioMes=0;
		hierroDia=0;
		hierroSemana=0;
		hierroMes=0;
		

		// initializing variables
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
		calorias21=0;
		calorias22=0;
		calorias23=0;
		calorias31=0;
		calorias32=0;
		calorias33=0;
		
		medida11=100;
		medida12=100;
		medida13=100;
		medida21=100;
		medida22=100;
		medida23=100;
		medida31=100;
		medida32=100;
		medida33=100;
		medida41=100;
		medida42=100;
		medida43=100;
		medida51=100;
		medida52=100;
		medida53=100;		
		
		// get today
		today=getDateToday();
		
		listaDesayunos=new ArrayList<String[]>();
		listaTentempies=new ArrayList<String[]>();
		listaComidas=new ArrayList<String[]>();
		listaMeriendas=new ArrayList<String[]>();
		listaCenas=new ArrayList<String[]>();
		
		// getting values to start 
		// checking the date to read
		dateSelected=today;
		updateInfo();
		
	} // end of constructor
	
	

	/**
	 * This method changes the working day, taking the day selected in
	 * calendar. Then updates the info and statistics.
	 * 
	 * @return String "resultados" to navigation 
	 */
	
	public String changeDay() {
	
		// get the date selected and transform to Date.sql form
		// this form is for showing

		SimpleDateFormat dateToRet=new SimpleDateFormat("yyyy-MM-dd");
		// this form is for sql
		dateSelected=dateToRet.format(dateCalendar);
		
		// update list and statistics
		takeInfoDay();
		updateInfo();
		
		return "recalculos";
		
		
	} // end of method changeDay
	
	
	
	/**
	 * This method changes the working day and returns to Today.
	 * Then updates the info and statistics.
	 * 
	 * @return String "resultados" to navigation 
	 */
	
	public String changeToday() {
	
		// returns back today
		dateSelected=today;
		
		// update list and statistics
		takeInfoDay();
		updateInfo();
		
		return "recalculos";
		
		
	} // end of method changeToday
	
	
	
	/**
	 * Este metodo implementa los siguientes calculos:
	 * a) El consumo total de calorias diarias, tanto proviniendo de alimentos como de comidas
	 * b) El consumo total de calorias proviniendo exclusivamente de COMIDAS
	 * c) Las desviaciones de los parametros calorias, calcio y hierro respeto a los consumos
	 * 		calculados.
	 * 
	 * @return String con valor "recalculos" para navegacion faces-config
	 */
	
	public String calcDish () {
		
		// primero calcula las calorias, calcio y hierro totales exclusivamente por comidas
		// correspondientes a ese dia
		// IMPORTANTE: se toma el valor ponderado
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
		
			// valores diarios por comidas
		caloriasDiaDish=(calorias1)+(calorias2)+(calorias3)+(calorias4)+(calorias5);
		calcioDiaDish=cc1+cc2+cc3+cc4+cc5;
		hierroDiaDish=h1+h2+h3+h4+h5;
		
		// segundo, se calcula el total de calorias diarias, sumando platos mas alimentos
		caloriasDia=caloriasDiaDish+caloriasDiaFood;
		caloriasDia=((double)(Math.round(caloriasDia*100)))/100;
		
		// tercero, se calculan los consumos de calcio y hierro diario
		calcioDia=calcioDiaDish+calcioDiaFood;
		calcioDia=((double)(Math.round(calcioDia*100)))/100;
		hierroDia=hierroDiaDish+hierroDiaFood;
		hierroDia=((double)(Math.round(hierroDia*100)))/100;
		
		// cuarto se calculan las desviaciones de los parametros calorias, calcio y hierro.
		calcDesv();
		
		activeTab=4;
		
		return "recalculos";
		
	} // end of caloriasTotales
	
	

	/**
	 * Este metodo implementa los siguientes calculos:
	 * a) El consumo total de calorias diarias, tanto proviniendo de alimentos como de comidas
	 * b) El consumo total de calorias proviniendo exclusivamente de COMIDAS
	 * c) Las desviaciones de los parametros calorias, calcio y hierro respeto a los consumos
	 * 		calculados.
	 * 
	 * @return String con valor "recalculos" para navegacion faces-config
	 */
	
	public String calcFood() {

		// primero calcula las calorias totales exclusivamente por comidas
		// IMPORTANTE: en el caso alimentos SI se pondera el valor, tomando el valor
		// grabado como ud, y se pondera en funcion de la cantidad introducida en el formulario
		
		// se calculan los 3 alimentos principales de cada ingesta introducidos en formulario
		
		DietFoodsBean fB=new DietFoodsBean();
		
		float cc11,cc12,cc13,cc21,cc22,cc23,cc31,cc32,cc33,cc41,cc42,cc43,cc51,cc52,cc53=0;
		float h11,h12,h13,h21,h22,h23,h31,h32,h33,h41,h42,h43,h51,h52,h53=0;
		
		String[] c=null;
		
		// ********** DESAYUNO
		
		try {
			c=fB.read((long)Long.parseLong(foods11));
			calorias11=(float)Float.parseFloat(c[3])*medida11/(float)Float.parseFloat(c[2]);
			calorias11=((double)(Math.round(calorias11*100)))/100;
			cc11=(float)Float.parseFloat(c[7])*medida11/(float)Float.parseFloat(c[2]);
			h11=(float)Float.parseFloat(c[8])*medida11/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias11=0;
			h11=0;
			cc11=0;
		}

		try {
			c=fB.read((long)Long.parseLong(foods12));
			calorias12=(float)Float.parseFloat(c[3])*medida12/(float)Float.parseFloat(c[2]);
			calorias12=((double)(Math.round(calorias12*100)))/100;
			cc12=(float)Float.parseFloat(c[7])*medida12/(float)Float.parseFloat(c[2]);
			h12=(float)Float.parseFloat(c[8])*medida12/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias12=0;
			h12=0;
			cc12=0;
		}
		
		try {
			c=fB.read((long)Long.parseLong(foods13));
			calorias13=(float)Float.parseFloat(c[3])*medida13/(float)Float.parseFloat(c[2]);
			calorias13=((double)(Math.round(calorias13*100)))/100;
			cc13=(float)Float.parseFloat(c[7])*medida13/(float)Float.parseFloat(c[2]);
			h13=(float)Float.parseFloat(c[8])*medida13/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias13=0;
			h13=0;
			cc13=0;
		}

		// ********** TENTEMPIE
		
		try {
			c=fB.read((long)Long.parseLong(foods21));
			calorias21=(float)Float.parseFloat(c[3])*medida21/(float)Float.parseFloat(c[2]);
			calorias21=((double)(Math.round(calorias21*100)))/100;
			cc21=(float)Float.parseFloat(c[7])*medida21/(float)Float.parseFloat(c[2]);
			h21=(float)Float.parseFloat(c[8])*medida21/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias21=0;
			h21=0;
			cc21=0;
		}

		try {
			c=fB.read((long)Long.parseLong(foods22));
			calorias22=(float)Float.parseFloat(c[3])*medida22/(float)Float.parseFloat(c[2]);
			calorias22=((double)(Math.round(calorias22*100)))/100;
			cc22=(float)Float.parseFloat(c[7])*medida22/(float)Float.parseFloat(c[2]);
			h22=(float)Float.parseFloat(c[8])*medida22/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias22=0;
			h22=0;
			cc22=0;
		}
		
		try {
			c=fB.read((long)Long.parseLong(foods23));
			calorias23=(float)Float.parseFloat(c[3])*medida23/(float)Float.parseFloat(c[2]);
			calorias23=((double)(Math.round(calorias23*100)))/100;
			cc23=(float)Float.parseFloat(c[7])*medida23/(float)Float.parseFloat(c[2]);
			h23=(float)Float.parseFloat(c[8])*medida23/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias23=0;
			h23=0;
			cc23=0;
		}
		
		// ********** COMIDA
		
		try {
			c=fB.read((long)Long.parseLong(foods31));
			calorias31=(float)Float.parseFloat(c[3])*medida31/(float)Float.parseFloat(c[2]);
			calorias31=((double)(Math.round(calorias31*100)))/100;
			cc31=(float)Float.parseFloat(c[7])*medida31/(float)Float.parseFloat(c[2]);
			h31=(float)Float.parseFloat(c[8])*medida31/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias31=0;
			h31=0;
			cc31=0;
		}

		try {
			c=fB.read((long)Long.parseLong(foods32));
			calorias32=(float)Float.parseFloat(c[3])*medida32/(float)Float.parseFloat(c[2]);
			calorias32=((double)(Math.round(calorias32*100)))/100;
			cc32=(float)Float.parseFloat(c[7])*medida32/(float)Float.parseFloat(c[2]);
			h32=(float)Float.parseFloat(c[8])*medida32/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias32=0;
			h32=0;
			cc32=0;
		}
		
		try {
			c=fB.read((long)Long.parseLong(foods33));
			calorias33=(float)Float.parseFloat(c[3])*medida33/(float)Float.parseFloat(c[2]);
			calorias33=((double)(Math.round(calorias33*100)))/100;
			cc33=(float)Float.parseFloat(c[7])*medida33/(float)Float.parseFloat(c[2]);
			h33=(float)Float.parseFloat(c[8])*medida33/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias33=0;
			h33=0;
			cc33=0;
		}
		
		// ********** MERIENDA

		try {
			c=fB.read((long)Long.parseLong(foods41));
			calorias41=(float)Float.parseFloat(c[3])*medida41/(float)Float.parseFloat(c[2]);
			calorias41=((double)(Math.round(calorias41*100)))/100;
			cc41=(float)Float.parseFloat(c[7])*medida41/(float)Float.parseFloat(c[2]);
			h41=(float)Float.parseFloat(c[8])*medida41/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias41=0;
			h41=0;
			cc41=0;
		}

		try {
			c=fB.read((long)Long.parseLong(foods42));
			calorias42=(float)Float.parseFloat(c[3])*medida42/(float)Float.parseFloat(c[2]);
			calorias42=((double)(Math.round(calorias42*100)))/100;
			cc42=(float)Float.parseFloat(c[7])*medida42/(float)Float.parseFloat(c[2]);
			h42=(float)Float.parseFloat(c[8])*medida42/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias42=0;
			h42=0;
			cc42=0;
		}
		
		try {
			c=fB.read((long)Long.parseLong(foods43));
			calorias43=(float)Float.parseFloat(c[3])*medida43/(float)Float.parseFloat(c[2]);
			calorias43=((double)(Math.round(calorias43*100)))/100;
			cc43=(float)Float.parseFloat(c[7])*medida43/(float)Float.parseFloat(c[2]);
			h43=(float)Float.parseFloat(c[8])*medida43/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias43=0;
			h43=0;
			cc43=0;
		}
		
		// ********** CENA
		
		try {
			c=fB.read((long)Long.parseLong(foods51));
			calorias51=(float)Float.parseFloat(c[3])*medida51/(float)Float.parseFloat(c[2]);
			calorias51=((double)(Math.round(calorias51*100)))/100;
			cc51=(float)Float.parseFloat(c[7])*medida51/(float)Float.parseFloat(c[2]);
			h51=(float)Float.parseFloat(c[8])*medida51/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias51=0;
			h51=0;
			cc51=0;
		}

		try {
			c=fB.read((long)Long.parseLong(foods52));
			calorias52=(float)Float.parseFloat(c[3])*medida52/(float)Float.parseFloat(c[2]);
			calorias52=((double)(Math.round(calorias52*100)))/100;
			cc52=(float)Float.parseFloat(c[7])*medida52/(float)Float.parseFloat(c[2]);
			h52=(float)Float.parseFloat(c[8])*medida52/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias52=0;
			h52=0;
			cc52=0;
		}
		
		try {
			c=fB.read((long)Long.parseLong(foods53));
			calorias53=(float)Float.parseFloat(c[3])*medida53/(float)Float.parseFloat(c[2]);
			calorias53=((double)(Math.round(calorias53*100)))/100;
			cc53=(float)Float.parseFloat(c[7])*medida53/(float)Float.parseFloat(c[2]);
			h53=(float)Float.parseFloat(c[8])*medida53/(float)Float.parseFloat(c[2]);
		} catch (NumberFormatException nf) {
			calorias53=0;
			h53=0;
			cc53=0;
		}
		
			// valores
		caloriasDes=(calorias11)+(calorias12)+(calorias13);
		caloriasTen=(calorias21)+(calorias22)+(calorias23);
		caloriasCom=(calorias31)+(calorias32)+(calorias33);
		caloriasMer=(calorias41)+(calorias42)+(calorias43);
		caloriasCen=(calorias51)+(calorias52)+(calorias53);
		
		caloriasDiaFood=caloriasDes+caloriasTen+caloriasCom+caloriasMer+caloriasCen;
		caloriasDiaFood=((double)(Math.round(caloriasDiaFood*100)))/100;
		
		calcioDiaFood=cc11+cc12+cc13+cc21+cc22+cc23+cc31+cc32+cc33+cc41+cc42+cc43+cc51+cc52+cc53;
		hierroDiaFood=h11+h12+h13+h21+h22+h23+h31+h32+h33+h41+h42+h43+h51+h52+h53;
		
		// segundo, se calcula el total de calorias diarias, sumando platos mas alimentos
		caloriasDia=caloriasDiaDish+caloriasDiaFood;
		caloriasDia=((double)(Math.round(caloriasDia*100)))/100;
		
		// tercero, se calculan los consumos de calcio y hierro diario
		calcioDia=calcioDiaDish+calcioDiaFood;
		calcioDia=((double)(Math.round(calcioDia*100)))/100;
		hierroDia=hierroDiaDish+hierroDiaFood;
		hierroDia=((double)(Math.round(hierroDia*100)))/100;
		
		// cuarto se calculan las desviaciones de los parametros calorias, calcio y hierro.
		calcDesv();
		
		activeTab=4;
		
		return "recalculos";
		
	} // end of calcFood
	
	
	
	/**
	 * This method updates the list of meals and products, and
	 * gets statistics values.
	 * 
	 * @return String "recalculos" to navigation
	 */
	
	public String updateInfo() {
			
		// updating lists
		DietMealsBean desayuno= new DietMealsBean();
		listaDesayunos=desayuno.showAll(keyUser);
		listaTentempies=listaDesayunos;
		listaComidas=listaDesayunos;
		listaMeriendas=listaDesayunos;
		listaCenas=listaDesayunos;
		
		DietFoodsBean foods=new DietFoodsBean();
		listaAlimentos=foods.showAll(keyUser);
		
		// updating statistics
		// checking the date to read
		
		getDay(dateSelected);
		getWeek(dateSelected);
		getMonth(dateSelected);
		calcDesv();
		
		return "recalculos";
		
	} // end of method updateInfo
	
	
	
	/**
	 * Este metodo graba en DDBB los datos del dIa
	 * SOLO COMIDAS
	 * 
	 * @return String "recalculos" to navigation
	 */
	
	public String recordDay () {
		
		// reseting variable caloriasDia
		caloriasDia=0;
		// recalculating variables and statistics
		calcDish();
		calcFood();
		
		if (caloriasDia!=0) { 
			// if caloriasDia has not zero then there are many values to record
			DietCalendarBean calend=new DietCalendarBean();
			DietCalendar data=new DietCalendar();
			
			// getting the date to record
			String date=dateSelected;
			Date thisDate=null;
			try {
				thisDate=Date.valueOf(date);
			} catch (IllegalArgumentException il) {
				// date is null but...
				thisDate=null;
			}
				
			if (thisDate!=null) {
				// date is correct
				
				// first, it is needed to erase old daily records
				eraseRecords(thisDate, calend);
				
				// then, we have to record the dishes (if exists)
				recordDish(data, calend, thisDate);
				// finally, we have to record the foods (if exists)	
				recordFoods(data, calend, thisDate);
				
			} else {
				// any message
				System.err.println ("Error 2.1 FECHA ERRONEA");
			}

		} else {
			// any message
		}
		
		// finally, updates the statistics
		updateInfo();
		
		return "recalculos";
		
	} // end of method recordDay
	
	
	
	/**
	 * Este metodo obtiene la informacion referente al día.
	 */
	
	private void getDay(String date) {
		

		Date thisDate=null;
		try {
			thisDate=Date.valueOf(date);
		} catch (IllegalArgumentException il) {
			// date is null then...
			thisDate=null;
		}				

		if (thisDate!=null) {
			// date is correct
			DietCalendarBean calend=new DietCalendarBean();
			// we get the list
			List<String[]> getRec=calend.showAll(keyUser, date, date);
			
			caloriasDia=0;
			calcioDia=0;
			hierroDia=0;
			
			if (getRec!=null) {

				DietMealsBean dMeal=new DietMealsBean();
				DietFoodsBean dFood=new DietFoodsBean();
				
				// reading the list
				for (String[]n:getRec) {
					
					try {
						// getting the id
						long idProd=(long)Long.parseLong(n[5]);

						if (n[4].equals("2")){
							// if meals
							
							// first - it stores atributes to statistics
							String[] data=dMeal.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[3]);
								float cal=(float)Float.parseFloat(data[4]);
								float cac=(float)Float.parseFloat(data[8]);
								float fer=(float)Float.parseFloat(data[9]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasDia+=resultCal;
								calcioDia+=resultCac;
								hierroDia+=resultFer;
							}																			
							
						} else {
							// if foods
							String[] data=dFood.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[2]);
								float cal=(float)Float.parseFloat(data[3]);
								float cac=(float)Float.parseFloat(data[7]);
								float fer=(float)Float.parseFloat(data[8]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasDia+=resultCal;
								calcioDia+=resultCac;
								hierroDia+=resultFer;
							}
						}
						
					} catch (NumberFormatException nf) {
						// do nothing
					}
					
				} // end of for
				
			} else {
				// there are not data
			}
		} else {
			System.err.println("Error 2.2 Error en la fecha");
		}

		// rounding data to show
		caloriasDia=((double)(Math.round(caloriasDia*100)))/100;
		calcioDia=((double)(Math.round(calcioDia*100)))/100;
		hierroDia=((double)(Math.round(hierroDia*100)))/100;				
		
	} // end of method getDay	
	
	
	
	/**
	 * Este metodo obtiene los valores de los ultimos 7 dias, los suma,
	 * y los utiliza para estadisticas
	 */
	
	private void getWeek (String date) {
		
		// number of days that contains data
		int num=0;

		Date thisDate=null;
		//Date toWeek=null;
		try {
			thisDate=Date.valueOf(date);
			//Calendar calendar=new GregorianCalendar();
			//calendar.setTime(date)
		} catch (IllegalArgumentException il) {
			// date is null then...
			thisDate=null;
		}

		
		if (thisDate!=null) {
			// date is correct
			
			// now, get the date -7
			String initDate=initDate(date,7);
			
			DietCalendarBean calend=new DietCalendarBean();
			// we get the list
			List<String[]> getRec=calend.showAll(keyUser,initDate, date);
			
			// reseting values 
			caloriasSemana=0;
			calcioSemana=0;
			hierroSemana=0;
			
			if (getRec!=null) {
				
				// counting the days
				String dday="";
				num=0;
				for (String[] c:getRec) {
					if (!dday.equals(c[2])) {
						dday=c[2];
						num++;
					}
				}
				
				DietMealsBean dMeal=new DietMealsBean();
				DietFoodsBean dFood=new DietFoodsBean();	
				
				// reading the list
				for (String[]n:getRec) {			
							
					try {
						long idProd=(long)Long.parseLong(n[5]);
						
						if (n[4].equals("2")){
							// this is a meal
							String[] data=dMeal.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[3]);
								float cal=(float)Float.parseFloat(data[4]);
								float cac=(float)Float.parseFloat(data[8]);
								float fer=(float)Float.parseFloat(data[9]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasSemana+=resultCal;
								calcioSemana+=resultCac;
								hierroSemana+=resultFer;
							}
							
						} else {
							// this is a food
							String[] data=dFood.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[2]);
								float cal=(float)Float.parseFloat(data[3]);
								float cac=(float)Float.parseFloat(data[7]);
								float fer=(float)Float.parseFloat(data[8]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasSemana+=resultCal;
								calcioSemana+=resultCac;
								hierroSemana+=resultFer;
							}
						}
						
					} catch (NumberFormatException nf) {
						// do nothing
					}
									
				}
			} else {
				// there are not data
			}
		} else {
			System.err.println("Error 2.3 Error en la fecha");
		}
		
		caloriasSemana=((double)(Math.round(caloriasSemana*100/num)))/100;
		calcioSemana=((double)(Math.round(calcioSemana*100/num)))/100;
		hierroSemana=((double)(Math.round(hierroSemana*100/num)))/100;
		
	} // end of method getWeek
	
	
	
	/**
	 * Este metodo obtiene los valores de los ultimos 30 dias, los suma,
	 * y los utiliza para estadisticas
	 */
	
	private void getMonth (String date) {

		// number of days that contains data
		int num=0;
		
		Date thisDate=null;
		//Date toWeek=null;
		try {
			thisDate=Date.valueOf(date);
			//Calendar calendar=new GregorianCalendar();
			//calendar.setTime(date)
		} catch (IllegalArgumentException il) {
			// date is null then...
			thisDate=null;
		}

		
		if (thisDate!=null) {
			// date is correct
			
			// now, get the date -30
			String initDate=initDate(date,30);
			initDate=initDate.replaceAll("/", "-");
			
			DietCalendarBean calend=new DietCalendarBean();
			// we get the list
			List<String[]> getRec=calend.showAll(keyUser,initDate, date);
			
			// reseting values 
			caloriasMes=0;
			calcioMes=0;
			hierroMes=0;
			
			if (getRec!=null) {

				// counting the days
				String dday="";
				num=0;
				for (String[] c:getRec) {
					if (!dday.equals(c[2])) {
						dday=c[2];
						num++;
					}
				}
				
				DietMealsBean dMeal=new DietMealsBean();
				DietFoodsBean dFood=new DietFoodsBean();
				
				// reading the list
				for (String[]n:getRec) {
					
					try {
						long idProd=(long)Long.parseLong(n[5]);
						
						if (n[4].equals("2")){
							// this is a meal
							String[] data=dMeal.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[3]);
								float cal=(float)Float.parseFloat(data[4]);
								float cac=(float)Float.parseFloat(data[8]);
								float fer=(float)Float.parseFloat(data[9]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasMes+=resultCal;
								calcioMes+=resultCac;
								hierroMes+=resultFer;
							}
							
						} else {
							// this is a food
							String[] data=dFood.read(idProd);
							if (data!=null) {
								// reading the data
								int qttMed=(int)Integer.parseInt(data[2]);
								float cal=(float)Float.parseFloat(data[3]);
								float cac=(float)Float.parseFloat(data[7]);
								float fer=(float)Float.parseFloat(data[8]);
								// making operations
								float qtt=(float)Float.parseFloat(n[6]);
								float resultCal=(cal*qtt/qttMed);
								float resultCac=(cac*qtt/qttMed);
								float resultFer=(fer*qtt/qttMed);
								// storing
								caloriasMes+=resultCal;
								calcioMes+=resultCac;
								hierroMes+=resultFer;	
							}
						}
						
					} catch (NumberFormatException nf) {
						// do nothing
					}
									
				} // end of for
				
			} else {
				// there are not data
			}
		} else {
			System.err.println("Error 2.4 Error en la fecha");
		}
		
		caloriasMes=((double)(Math.round(caloriasMes*100/num)))/100;
		calcioMes=((double)(Math.round(calcioMes*100/num)))/100;
		hierroMes=((double)(Math.round(hierroMes*100/num)))/100;
		
	} // end of method getMOnth
	
	
	
	/**
	 * This method implements the calculus of consumption deviation of calories, 
	 * calcium and iron about user's objective values and stores it.
	 * 
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
	
	
	
	/**
	 * este metodo toma los valores del dia grabados en DDBB y los
	 * coloca en las variables que se muestran por pantalla 
	 */
	
	private void takeInfoDay() {
		
		// checking the date to read
		String date=dateSelected;
		Date thisDate=null;
		try {
			thisDate=Date.valueOf(date);
		} catch (IllegalArgumentException il) {
			// date is null then...
			thisDate=null;
		}				

		if (thisDate!=null) {
			// date is correct
			DietCalendarBean calend=new DietCalendarBean();
			// we get the list
			List<String[]> getRec=calend.showAll(keyUser, date, date);
			
			// reseting dishes values
			comida1=comida2=comida3=comida4=comida5=null;
			medida1=medida2=medida3=medida4=medida5=0;
			
			// reseting foods values
			foods11=foods12=foods13=null;
			foods21=foods22=foods23=null;
			foods31=foods32=foods33=null;
			foods41=foods42=foods43=null;
			foods51=foods52=foods53=null;
			
			medida11=medida12=medida13=0;
			medida21=medida22=medida23=0;
			medida31=medida32=medida33=0;
			medida41=medida42=medida43=0;
			medida51=medida52=medida53=0;
			
			
			if (getRec!=null) {				
				
				// reading the list
				for (String[]n:getRec) {
					
					try {

						if (n[4].equals("2")){
							// if meals			
							
							// SECOND - get the data and show it in panel
							
							// if there is a dish recorded
							if (n[3].equals("1")){
								// if there is a meal desayuno
								comida1=n[5];
								medida1=(int)Math.round((float)Float.parseFloat(n[6]));
							}
							if (n[3].equals("2")){
								// if there is a meal tentempie
								comida2=n[5];
								medida2=(int)Math.round((float)Float.parseFloat(n[6])); 
							}
							if (n[3].equals("3")){
								// if there is a meal comida
								comida3=n[5];
								medida3=(int)Math.round((float)Float.parseFloat(n[6])); 
							}
							if (n[3].equals("4")){
								// if there is a meal merienda
								comida4=n[5];
								medida4=(int)Math.round((float)Float.parseFloat(n[6])); 
							}
							if (n[3].equals("5")){
								// if there is a meal cena
								comida5=n[5];
								medida5=(int)Math.round((float)Float.parseFloat(n[6]));
							}
							
						} else {
							
							// if there is a food recorded
							if (n[3].equals("1")){
								// if there is a food desayuno
								if (foods11==null || foods11.isEmpty()) {
									// if the first position is fill
									foods11=n[5];
									medida11=(int)Math.round((float)Float.parseFloat(n[6]));
								} else 	if (foods12==null || foods12.isEmpty()) {
									// if the second position is fill
									foods12=n[5];
									medida12=(int)Math.round((float)Float.parseFloat(n[6]));
								} else {
									foods13=n[5];
									medida13=(int)Math.round((float)Float.parseFloat(n[6]));
								}
							}
							if (n[3].equals("2")){
								// if there is a food tentempie
								if (foods21==null || foods21.isEmpty()) {
									// if the first position is fill
									foods21=n[5];
									medida21=(int)Math.round((float)Float.parseFloat(n[6]));
								} else 	if (foods22==null || foods22.isEmpty()) {
									// if the second position is fill
									foods22=n[5];
									medida22=(int)Math.round((float)Float.parseFloat(n[6]));
								} else {
									foods23=n[5];
									medida23=(int)Math.round((float)Float.parseFloat(n[6]));
								} 
							}
							if (n[3].equals("3")){
								// if there is a food comida
								if (foods31==null || foods31.isEmpty()) {
									// if the first position is fill
									foods31=n[5];
									medida31=(int)Math.round((float)Float.parseFloat(n[6]));
								} else 	if (foods32==null || foods32.isEmpty()) {
									// if the second position is fill
									foods32=n[5];
									medida32=(int)Math.round((float)Float.parseFloat(n[6]));
								} else {
									foods33=n[5];
									medida33=(int)Math.round((float)Float.parseFloat(n[6]));
								} 
							}
							if (n[3].equals("4")){
								// if there is a food merienda
								if (foods41==null || foods41.isEmpty()) {
									// if the first position is fill
									foods41=n[5];
									medida41=(int)Math.round((float)Float.parseFloat(n[6]));
								} else 	if (foods42==null || foods42.isEmpty()) {
									// if the second position is fill
									foods42=n[5];
									medida42=(int)Math.round((float)Float.parseFloat(n[6]));
								} else {
									foods43=n[5];
									medida43=(int)Math.round((float)Float.parseFloat(n[6]));
								}
							}
							if (n[3].equals("5")){
								// if there is a food cena
								if (foods51==null || foods51.isEmpty()) {
									// if the first position is fill
									foods51=n[5];
									medida51=(int)Math.round((float)Float.parseFloat(n[6]));
								} else 	if (foods52==null || foods52.isEmpty()) {
									// if the second position is fill
									foods52=n[5];
									medida52=(int)Math.round((float)Float.parseFloat(n[6]));
								} else {
									foods53=n[5];
									medida53=(int)Math.round((float)Float.parseFloat(n[6]));
								}
							}
						}
						
					} catch (NumberFormatException nf) {
						// do nothing
					}
					
				} // end of for
				
				// recalculates data about all consumption
				calcDish();
				calcFood();
				
			} else {
				// there are not data
				// recalculates data about all consumption
				calcDish();
				calcFood();
			}
			
		} else {
			System.err.println("Error 2.5 Error en la fecha");
		}
			
	} // end of method takeInfoDay
	
	
	
	/**
	 * 
	 */
	
	private void recordDish(DietCalendar data, DietCalendarBean calend, Date thisDate) {
		
		// procedemos a comprobar desde desayuno a cena
		if (comida1!=null && !(comida1.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(comida1));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(1); // desayuno
			data.setQtt(medida1);
			data.setType(2);	// comida
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.6.1 error en grabacion");
			}
		}

		if (comida2!=null && !(comida2.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(comida2));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(2); // tentempie
			data.setQtt(medida2);
			data.setType(2);	// comida
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.6.2 error en grabacion");
			}
		}

		
		if (comida3!=null && !(comida3.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(comida3));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(3); // comida
			data.setQtt(medida3);
			data.setType(2);	// comida
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.6.3 error en grabacion");
			}
		}
		
		if (comida4!=null && !(comida4.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(comida4));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(4); // merienda
			data.setQtt(medida4);
			data.setType(2);	// comida
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.6.4 error en grabacion");
			}
		}
		
		if (comida5!=null && !(comida5.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(comida5));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(5); // cena
			data.setQtt(medida5);
			data.setType(2);	// comida
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.6.5 error en grabacion");
			}
		}
		
	} // end of method recordDish
	
	
	
	private void recordFoods(DietCalendar data, DietCalendarBean calend, Date thisDate) {
		
		// procedemos a comprobar desde desayuno a cena
		
		// *** desayunos
		if (foods11!=null && !(foods11.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods11));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(1); // desayuno
			data.setQtt(medida11);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.1 error en grabacion");
			}
		}
		if (foods12!=null && !(foods12.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods12));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(1); // desayuno
			data.setQtt(medida12);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.2 error en grabacion");
			}
		}
		if (foods13!=null && !(foods13.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods13));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(1); // desayuno
			data.setQtt(medida13);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.3 error en grabacion");
			}
		}
		
		// *** tentempie
		if (foods21!=null && !(foods21.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods21));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(2); // tentempie
			data.setQtt(medida21);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.4 error en grabacion");
			}
		}
		if (foods22!=null && !(foods22.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods22));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(2); // tentempie
			data.setQtt(medida22);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.5 error en grabacion");
			}
		}
		if (foods23!=null && !(foods23.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods23));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(2); // tentempie
			data.setQtt(medida23);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.6 error en grabacion");
			}
		}
		
		// *** comida
		if (foods31!=null && !(foods31.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods31));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(3); // comida
			data.setQtt(medida31);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.7 error en grabacion");
			}
		}
		if (foods32!=null && !(foods32.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods32));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(3); // comida
			data.setQtt(medida32);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.8 error en grabacion");
			}
		}
		if (foods33!=null && !(foods33.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods33));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(3); // comida
			data.setQtt(medida33);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.9 error en grabacion");
			}
		}
		
		// *** merienda
		if (foods41!=null && !(foods41.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods41));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(4); // merienda
			data.setQtt(medida41);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.10 error en grabacion");
			}
		}
		if (foods42!=null && !(foods42.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods42));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(4); // merienda
			data.setQtt(medida42);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.11 error en grabacion");
			}
		}
		if (foods43!=null && !(foods43.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods43));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(4); // merienda
			data.setQtt(medida43);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.12 error en grabacion");
			}
		}
		
		// *** cena
		if (foods51!=null && !(foods51.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods51));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(5); // cena
			data.setQtt(medida51);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.13 error en grabacion");
			}
		}
		if (foods52!=null && !(foods52.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods52));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(5); // cena
			data.setQtt(medida52);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.14 error en grabacion");
			}
		}
		if (foods53!=null && !(foods53.trim().isEmpty())) {
			data.setDate(thisDate);
			data.setIdproduct((long)Long.parseLong(foods53));
			data.setKeyuser(IdentifyBean.getKeyUser());
			data.setMoment(5); // cena
			data.setQtt(medida53);
			data.setType(1);	// food
			if (!calend.record(data)) {
				// wrong record
				System.err.println ("Error 2.7.15 error en grabacion");
			}
		}

	} // end of method recordFoods
	
	
	
	/**
	 * This method delete all the records that belongs to user, for 
	 * a determinate day 
	 * 
	 * @return String "recalculos" to navigation
	 */
	
	public String deleteDay() {
		
		// getting the date to record
		String date=dateSelected;
		Date thisDate=null;
		try {
			thisDate=Date.valueOf(date);
		} catch (IllegalArgumentException il) {
			// date is null then... nothing happens
			thisDate=null;
		}
			
		if (thisDate!=null) {
			//  the date is correct
			DietCalendarBean calend=new DietCalendarBean();
			// deleting records
			eraseRecords(thisDate, calend);
		}
		
		// first delete form then update
		clearForm();
		
		// updates statistics
		updateInfo();
		
		return "recalculos";
		
	} // end of method deleteDay
	
	
	
	/**
	 * This method erases all daily records belongs to a user.
	 * 
	 * @param dateToErase - Date, the date to delete records
	 * @param calendar - Object DietCalendarBean to instantiate method deleteDay
	 */
	
	private void eraseRecords(Date dateToErase, DietCalendarBean calendar) {
		
		// deleting
		if (!calendar.deleteDay(keyUser, dateToErase)) {
			System.err.println("Error 2.8 Error en borrado de datos diarios");
		}
		
	} // end of method eraseRecords
	
	
	
	/**
	 * This method erases the form fields and their variables that
	 * belongs to a determinate deleted day.
	 * 
	 */
	
	private void clearForm() {
		
		// reseting dishes values
		comida1=comida2=comida3=comida4=comida5=null;
		medida1=medida2=medida3=medida4=medida5=0;
		calorias1=calorias2=calorias3=calorias4=calorias5=0;
		
		caloriasDiaDish=0;
		calcioDiaDish=0;
		hierroDiaDish=0;
		
		// reseting foods values
		foods11=foods12=foods13=null;
		foods21=foods22=foods23=null;
		foods31=foods32=foods33=null;
		foods41=foods42=foods43=null;
		foods51=foods52=foods53=null;
		
		medida11=medida12=medida13=0;
		medida21=medida22=medida23=0;
		medida31=medida32=medida33=0;
		medida41=medida42=medida43=0;
		medida51=medida52=medida53=0;
		
		calorias11=calorias12=calorias13=0;
		calorias21=calorias22=calorias23=0;
		calorias31=calorias32=calorias33=0;
		calorias41=calorias42=calorias43=0;
		calorias51=calorias52=calorias53=0;
		
		caloriasDiaFood=0;
		calcioDiaFood=0;
		hierroDiaFood=0;
		
		// reseting statistics values
		caloriasDia=0;
		calcioDia=0;
		hierroDia=0;
		
		
	} // end of method clearForm
	
	
	
	/**
	 * This method returns the date today in String format sql
	 * 
	 * @return a String containing a date in sql format yyyy-mm-dd
	 */
	
	private String getDateToday() {
		
		
		SimpleDateFormat dateToRet=new SimpleDateFormat("yyyy-MM-dd");
		
		GregorianCalendar calendar=new GregorianCalendar();
	
		return dateToRet.format(calendar.getTime());
		
	} // end of method getDateToday
	
	
	
	/**
	 * THis method convert a String in sql.Date format (YYYY-MM-DD), subtracts some
	 * dif days and returns a new String in sqlDate format.
	 * 
	 * @param date the date to subtracts dif days
	 * @param dif days to subtracts
	 * @return a new String in sqlDate format or null if any error
	 */
	private String initDate(String date, int dif) {

		SimpleDateFormat dateToRet=new SimpleDateFormat("yyyy-MM-dd");
		
		GregorianCalendar calendar=new GregorianCalendar();
		java.util.Date dateIni=null;
		try {
			dateIni=dateToRet.parse(date);
		} catch (ParseException ps) {
			System.err.println("Error 2.9 ERROR PARSEANDO FECHA");
			return null;
		}
		
		calendar.setTime(dateIni);
		calendar.add(Calendar.DAY_OF_YEAR, -dif);

		
		return dateToRet.format(calendar.getTime());

	} // end of method initDate
	
	
	
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

	public int getMedida11() {
		return medida11;
	}

	public void setMedida11(int medida11) {
		this.medida11 = medida11;
	}

	public int getMedida12() {
		return medida12;
	}

	public void setMedida12(int medida12) {
		this.medida12 = medida12;
	}

	public int getMedida13() {
		return medida13;
	}

	public void setMedida13(int medida13) {
		this.medida13 = medida13;
	}

	public int getMedida21() {
		return medida21;
	}

	public void setMedida21(int medida21) {
		this.medida21 = medida21;
	}

	public int getMedida22() {
		return medida22;
	}

	public void setMedida22(int medida22) {
		this.medida22 = medida22;
	}

	public int getMedida23() {
		return medida23;
	}

	public void setMedida23(int medida23) {
		this.medida23 = medida23;
	}

	public int getMedida31() {
		return medida31;
	}

	public void setMedida31(int medida31) {
		this.medida31 = medida31;
	}

	public int getMedida32() {
		return medida32;
	}
	
	public void setMedida32(int medida32) {
		this.medida32 = medida32;
	}

	public int getMedida33() {
		return medida33;
	}

	public void setMedida33(int medida33) {
		this.medida33 = medida33;
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

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public java.util.Date getDateCalendar() {
		return dateCalendar;
	}

	public void setDateCalendar(java.util.Date dateCalendar) {
		this.dateCalendar = dateCalendar;
	}

	public String getDateSelected() {
		return dateSelected;
	}

	public void setDateSelected(String dateSelected) {
		this.dateSelected = dateSelected;
	}

	public String getDateToShow() {
		dateToShow=dateSelected.substring(8)+dateSelected.substring(4, 8)+dateSelected.substring(0, 4);
		return dateToShow;
	}

	public void setDateToShow(String dateToShow) {
		this.dateToShow = dateToShow;
	}

	public String getFoods11() {
		return foods11;
	}

	public void setFoods11(String foods11) {
		this.foods11 = foods11;
	}
	
	public String getFoods12() {
		return foods12;
	}

	public void setFoods12(String foods12) {
		this.foods12 = foods12;
	}

	public String getFoods13() {
		return foods13;
	}

	public void setFoods13(String foods13) {
		this.foods13 = foods13;
	}

	public String getFoods21() {
		return foods21;
	}

	public void setFoods21(String foods21) {
		this.foods21 = foods21;
	}

	public String getFoods22() {
		return foods22;
	}

	public void setFoods22(String foods22) {
		this.foods22 = foods22;
	}

	public String getFoods23() {
		return foods23;
	}

	public void setFoods23(String foods23) {
		this.foods23 = foods23;
	}

	public String getFoods31() {
		return foods31;
	}

	public void setFoods31(String foods31) {
		this.foods31 = foods31;
	}

	public String getFoods32() {
		return foods32;
	}

	public void setFoods32(String foods32) {
		this.foods32 = foods32;
	}

	public String getFoods33() {
		return foods33;
	}

	public void setFoods33(String foods33) {
		this.foods33 = foods33;
	}

	public String getFoods41() {
		return foods41;
	}

	public void setFoods41(String foods41) {
		this.foods41 = foods41;
	}

	public String getFoods42() {
		return foods42;
	}

	public void setFoods42(String foods42) {
		this.foods42 = foods42;
	}

	public String getFoods43() {
		return foods43;
	}

	public void setFoods43(String foods43) {
		this.foods43 = foods43;
	}

	public String getFoods51() {
		return foods51;
	}

	public void setFoods51(String foods51) {
		this.foods51 = foods51;
	}

	public String getFoods52() {
		return foods52;
	}

	public void setFoods52(String foods52) {
		this.foods52 = foods52;
	}

	public String getFoods53() {
		return foods53;
	}

	public void setFoods53(String foods53) {
		this.foods53 = foods53;
	}

	public int getMedida41() {
		return medida41;
	}

	public void setMedida41(int medida41) {
		this.medida41 = medida41;
	}

	public int getMedida42() {
		return medida42;
	}

	public void setMedida42(int medida42) {
		this.medida42 = medida42;
	}

	public int getMedida43() {
		return medida43;
	}

	public void setMedida43(int medida43) {
		this.medida43 = medida43;
	}

	public int getMedida51() {
		return medida51;
	}

	public void setMedida51(int medida51) {
		this.medida51 = medida51;
	}

	public int getMedida52() {
		return medida52;
	}

	public void setMedida52(int medida52) {
		this.medida52 = medida52;
	}

	public int getMedida53() {
		return medida53;
	}

	public void setMedida53(int medida53) {
		this.medida53 = medida53;
	}

	public double getCalorias41() {
		return calorias41;
	}

	public void setCalorias41(double calorias41) {
		this.calorias41 = calorias41;
	}

	public double getCalorias42() {
		return calorias42;
	}

	public void setCalorias42(double calorias42) {
		this.calorias42 = calorias42;
	}

	public double getCalorias43() {
		return calorias43;
	}

	public void setCalorias43(double calorias43) {
		this.calorias43 = calorias43;
	}

	public double getCalorias51() {
		return calorias51;
	}

	public void setCalorias51(double calorias51) {
		this.calorias51 = calorias51;
	}

	public double getCalorias52() {
		return calorias52;
	}

	public void setCalorias52(double calorias52) {
		this.calorias52 = calorias52;
	}

	public double getCalorias53() {
		return calorias53;
	}

	public void setCalorias53(double calorias53) {
		this.calorias53 = calorias53;
	}

	
	
} // ***************** END OF CLASS