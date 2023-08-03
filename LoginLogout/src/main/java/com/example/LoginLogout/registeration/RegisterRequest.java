package com.example.LoginLogout.registeration;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "RegisterRequest [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + "]";
	}
	
	

}
