package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dietFoods database table.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */
@Entity
@Table(name="dietFoods")

@NamedQueries(value = { 
	@NamedQuery(name="allFoods", query="SELECT c FROM DietFoods c ORDER BY c.foodname"),
	@NamedQuery(name="aFood", query="SELECT c FROM DietFoods c WHERE c.id LIKE :ident")	
})



public class DietFoods implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", nullable=false)
	private long id;

	@Column (name="foodname", nullable=false, length=50)
	private String foodname;
	
	@Column (name="qtt", nullable=false)
	private int qtt;
	
	@Column (name="cal", nullable=false)
	private float cal;
	
	@Column (name="carbohydrate", nullable=false)
	private float carbohydrate;
	
	@Column (name="protein", nullable=false)
	private float protein;
	
	@Column (name="lipid", nullable=false)
	private float lipid;

	@Column (name="calcium")
	private float calcium;

	@Column (name="iron")
	private float iron;

	
	
	public DietFoods() {
		// CONSTRUCTOR
	}

	

	// GETTERS AND SETTERS 

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCalcium() {
		return this.calcium;
	}

	public void setCalcium(float calcium) {
		this.calcium = calcium;
	}

	public float getCarbohydrate() {
		return this.carbohydrate;
	}

	public void setCarbohydrate(float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public String getFoodname() {
		return this.foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public float getIron() {
		return this.iron;
	}

	public void setIron(float iron) {
		this.iron = iron;
	}

	public float getLipid() {
		return this.lipid;
	}

	public void setLipid(float lipid) {
		this.lipid = lipid;
	}

	public float getProtein() {
		return this.protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public int getQtt() {
		return this.qtt;
	}

	public void setQtt(int qtt) {
		this.qtt = qtt;
	}



	public float getCal() {
		return cal;
	}



	public void setCal(float cal) {
		this.cal = cal;
	}

}