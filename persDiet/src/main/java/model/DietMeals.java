package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dietMeals database table.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@Entity
@Table(name="dietMeals")
@NamedQueries ( {
	@NamedQuery(name="allMeals",query="SELECT c FROM DietMeals c WHERE c.keyuser LIKE :keyUser ORDER BY c.mealname"),
	@NamedQuery(name="aMeal",query="SELECT c FROM DietMeals c WHERE c.id LIKE :ident")
} )

public class DietMeals implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", nullable=false)
	private long id;

	@Column (name="keyuser", nullable=false, length=15)
	private String keyuser;
	
	@Column (name="mealname", nullable=false, length=50)
	private String mealname;
	
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
	
	
	
	public DietMeals() {
		//CONSTRUCTOR
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

	public float getIron() {
		return this.iron;
	}

	public void setIron(float iron) {
		this.iron = iron;
	}

	public String getKeyuser() {
		return this.keyuser;
	}

	public void setKeyuser(String keyuser) {
		this.keyuser = keyuser;
	}

	public float getLipid() {
		return this.lipid;
	}

	public void setLipid(float lipid) {
		this.lipid = lipid;
	}

	public String getMealname() {
		return this.mealname;
	}

	public void setMealname(String mealname) {
		this.mealname = mealname;
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

} // *********** END OF CLASS