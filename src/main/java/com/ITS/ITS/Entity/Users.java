package com.ITS.ITS.Entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user_auth")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "Email")
	private String email;
	@Column(name = "Username")
	private String username;
	
	
	@Column(name = "Password_hash")
	private String password_hash;

	@Column(name = "uniqueCode", columnDefinition = "int default 0")
	private int  uniqueCode = 0;
	
	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	@Column(name = "created_on")
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	
	@Column(name = "updated_on")
	@Temporal(TemporalType.DATE)
	private Date updatedOn;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(int uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", username=" + username + ", password_hash="
				+ password_hash + ", uniqueCode=" + uniqueCode + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ "]";
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	
}