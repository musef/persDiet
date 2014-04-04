package model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the dietUsers database table.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

@Entity
@Table(name="dietUsers")
public class DietUsers implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", nullable=false)
	private long id;

	@Column (name="keyuser", nullable=false, length=15)
	private String keyuser;

	@Column (name="login", nullable=false, length=15)
	private String login;

	@Column (name="pass", nullable=false, length=15)
	private String pass;

	
	
	public DietUsers() {
		// CONSTRUCTOR
	}

	
	
	// GETTERS AND SETTERS 
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyuser() {
		return this.keyuser;
	}

	public void setKeyuser(String keyuser) {
		this.keyuser = keyuser;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}