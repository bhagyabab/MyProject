package com.update.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="Users")
public class Signup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String phNo;
	@Email
	@NotBlank
	@Column(unique=true)
	private String email;
	@JsonIgnore
	private String passWord;
	
	public Signup(String name, String phNo, String email, String passWord) {
		super();
		this.name = name;
		this.phNo = phNo;
		this.email = email;
		this.passWord = passWord;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
	    this.id = id;
	}

	
	public Signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
