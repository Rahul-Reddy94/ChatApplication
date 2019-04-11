package com.app.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="verification_token")
public class VerificationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;	
	
	@Column(name="expiry_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	@Column(name="token")
	private String token;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@Column(name="token_type")
	@Enumerated(EnumType.STRING)
	private Token_Type type;
	
	@PrePersist
	private void setDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 24);
		expiryDate = c.getTime();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Token_Type getType() {
		return type;
	}

	public void setType(Token_Type type) {
		this.type = type;
	}

	public VerificationToken() {
		
	}
	
	public VerificationToken(String token, User user, Token_Type type) {
		this.token = token;
		this.user = user;
		this.type = type;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
}
