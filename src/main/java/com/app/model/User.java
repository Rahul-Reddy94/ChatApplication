package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.validation.PasswordMatch;


@Entity
@Table(name = "users")
@PasswordMatch(message = "{register.repeatPassword.mismatch}")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id; 
	
	@SuppressWarnings("deprecation")
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "{register.invalidEmail}")
	@NotEmpty(message = "Please provide an e-mail")
	private String email; 
	
	@Column(name = "password",length=60)
	@NotEmpty(message = "{register.enterEmail}")
	private String password;

	@Transient
	@Size(min=5,max = 15,message="{register.password.size}")
	private String plainPassword;
	
	@Transient
	private String repeatPassword;
	
	@Column(name = "role", length =20)
	private String role;

	@Column(name = "enabled")
	private Boolean enabled = false;
	
	public String getRole() { 
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}
	
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

	
}