package com.killrvideo;


import org.apache.commons.codec.digest.DigestUtils;

public class User {

	String username;
	String password;
	String firstname;
	String lastname;

	public User() {
		super();
		
	}
	
	public User(String username, String password, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = DigestUtils.md5Hex(password);
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
