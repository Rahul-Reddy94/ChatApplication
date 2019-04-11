package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.owasp.html.PolicyFactory;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@Column(name="about", length=5000)
	@Size(max=5000,message="{editprofile.about.size}")
	private String about;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String text) {
		this.about = text;
	}
	
	public void safeCopyFrom(Profile other) {

		if (other.about != null) {
			this.about = other.about;
		}
	}
	
	public Profile(String about) {
		this.about = about;
	}

	public Profile() {
		// TODO Auto-generated constructor stub
	}

	public void safeMergeFrom(@Valid Profile webProfile, PolicyFactory htmlPolicyFactory) {
		if (webProfile.about != null) {
			this.about = htmlPolicyFactory.sanitize(webProfile.about);
			//this.about = webProfile.about;
		}		
	}
	
	
}
