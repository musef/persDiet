package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dietPersonalData database table.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@Entity
@Table(name="dietPersonalData")
public class DietPersonalData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", nullable=false)
	private long id;

	@Column (name="keyuser", nullable=false, length=15)
	private String keyuser;

	@Column (name="name", nullable=false, length=50)
	private String name;
	
	@Column (name="weight", nullable=false)
	private double weight;
	
	@Column (name="height", nullable=false)
	private double height;	
	
	@Column (name="age", nullable=false)
	private int age;
	

	
	public DietPersonalData() {
		// CONSTRUCTOR
	}
	
	
	
	// GETTERS AND SETTERS 

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getKeyuser() {
		return this.keyuser;
	}

	public void setKeyuser(String keyuser) {
		this.keyuser = keyuser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}