package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.DietFoods;
import model.DietMeals;



@ManagedBean (name="comidas", eager=true)
@SessionScoped

public class Comidas implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private List<String[]> listaDesayunos;
	private List<String[]> listaTentempies;
	private List<String[]> listaComidas;
	private List<String[]> listaMeriendas;
	private List<String[]> listaCenas;
	private List<String[]> listaAlimentos;
	
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
	
	private String keyUser="MUSABCDEF123456";
	
	
	
	public Comidas () {
		// CONSTRUCTOR
		
		listaDesayunos=new ArrayList<String[]>();
		listaTentempies=new ArrayList<String[]>();
		listaComidas=new ArrayList<String[]>();
		listaMeriendas=new ArrayList<String[]>();
		listaCenas=new ArrayList<String[]>();
		
		
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

		recordMeal();
		
		DietMealsBean desayuno= new DietMealsBean();
		listaDesayunos=desayuno.showAll(keyUser);
		listaTentempies=listaDesayunos;
		listaComidas=listaDesayunos;
		listaMeriendas=listaDesayunos;
		listaCenas=listaDesayunos;
		
		DietFoodsBean foods=new DietFoodsBean();
		listaAlimentos=foods.showAll(keyUser);
		
	}


	
	

	
	public double caloriasTotal () {
		
		double total=0;
		
		total=(calorias1*medida1/udMedidaStandard)+(calorias2*medida2/udMedidaStandard)+
				(calorias3*medida3/udMedidaStandard)+(calorias4*medida4/udMedidaStandard)+(calorias5*medida5/udMedidaStandard);
		
		caloriasTotales=total;
		System.out.println("**"+caloriasTotales);
		
		return total;
	}
	
	
	
	public double caloriasTotalA() {
		
		double total=0;
		
		total=(calorias11*medida11/udMedidaStandard)+(calorias12*medida12/udMedidaStandard)+
				(calorias13*medida13/udMedidaStandard)+(calorias14*medida14/udMedidaStandard)+(calorias15*medida15/udMedidaStandard);
		total+=(calorias21*medida21/udMedidaStandard)+(calorias22*medida22/udMedidaStandard)+
				(calorias23*medida23/udMedidaStandard)+(calorias24*medida24/udMedidaStandard)+(calorias25*medida25/udMedidaStandard);
		total+=(calorias31*medida31/udMedidaStandard)+(calorias32*medida32/udMedidaStandard)+
				(calorias33*medida33/udMedidaStandard)+(calorias34*medida34/udMedidaStandard)+(calorias35*medida35/udMedidaStandard);
		
		caloriasTotalesA=total;
		
		return total;
	}
	
	/*
	
	private boolean recordFood() {
		
		DietFoods food=new DietFoods();
		
		food.setFoodname("NOMBRE DE PRUEBA");
		food.setCal(100);
		food.setCalcium(0);
		food.setCarbohydrate(0);
		food.setIron(0);
		food.setLipid(0);
		food.setProtein(0);
		food.setQtt(100);
		
		FoodsBean foodB=new FoodsBean();
		if (!foodB.record(food)) {
			System.err.println("ERROR GRABANDO COMIDA");
			return false;
		}
		
		return true;
	}
	
	*/
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




	
	
	
	

	
	
} // ***************** END OF CLASS