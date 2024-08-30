package com.tr.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Table(name = "userdetails")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	@Past(message = "Birth date should be in the past")
	private LocalDateTime birthdate;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public User(int userid, String name, LocalDateTime birthdate) {
		super();
		this.userid = userid;
		this.name = name;
		this.birthdate = birthdate;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDateTime birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + userid + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	

}
