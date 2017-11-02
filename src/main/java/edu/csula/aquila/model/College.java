package edu.csula.aquila.model;
<<<<<<< HEAD

import java.io.Serializable;

=======
import java.util.*;

>>>>>>> upstream/master
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "college")

public class College implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false; unique = true)
	private String name;

	@Column(name="dean")
	private User dean;

	public College()
	{
=======
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colleges")
public class College {
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column(name = "college_name")
	String name;
	
	@OneToOne
	User dean;

	College(long id, String name, User dean){
	this.id = id;
	this.name = name;
	this.dean = dean;
>>>>>>> upstream/master
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id)
	{
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User getDean(){
		return dean;
	}

	public void setDean(User dean)
	{
		this.dean = dean;
	}

	
}