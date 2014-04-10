package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the dietCalendar database table.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@Entity
@Table(name="dietCalendar")

@NamedQueries ({
	@NamedQuery (name="allCal", query="SELECT c FROM DietCalendar c WHERE c.keyuser LIKE :keyUs ORDER BY c.date"),
	@NamedQuery (name="dateCal", query="SELECT c FROM DietCalendar c WHERE c.keyuser LIKE :keyUs AND c.date >= :date1 AND c.date<= :date2 ORDER BY c.date"),
	@NamedQuery (name="aFoodCal", query="SELECT c FROM DietCalendar c WHERE c.id LIKE :ident")	
})
public class DietCalendar implements Serializable {
	
	/*
	 * id --------> long - primary key
	 * keyuser ---> String - the user key that identifies the user
	 * date ------> Date - the date of the record
	 * moment ----> int - the eating moment (1=desayuno, 2=tentempie,
	 * 				      3=comida, 4=merienda, 5=cena)
	 * type ------> int - (1=food, 2=meal)
	 * idfood ----> long - the id of the food or meal recorded
	 * qtt -------> float - the qtt recorded
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", nullable=false)
	private long id;
	
	@Column(name="keyuser", nullable=false, length=15)
	private String keyuser;
	
	@Column(name="date", nullable=false)
	private Date date;
	
	@Column (name="moment", nullable=false)
	private int moment;
	
	@Column (name="type", nullable=false)
	private int type;
	
	@Column (name="idproduct", nullable=false)
	private long idproduct;
	
	@Column (name="qtt", nullable=false)
	private float qtt;

	
	// GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyuser() {
		return keyuser;
	}

	public void setKeyuser(String keyuser) {
		this.keyuser = keyuser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMoment() {
		return moment;
	}

	public void setMoment(int moment) {
		this.moment = moment;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}

	public float getQtt() {
		return qtt;
	}

	public void setQtt(float qtt) {
		this.qtt = qtt;
	}

}
