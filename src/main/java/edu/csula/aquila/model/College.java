package edu.csula.aquila.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colleges")
public class College implements Serializable{
	
	private static final long serialVersionUID = 1916096303254438095L;

	@Id
	@GeneratedValue
	@Column(name ="college_id")
	private Long id;

	@Column(name = "college_name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "dean_id")
	private User dean;

	@OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
	private List<Department> departments;

	College(){}

	public College(Long id, String name, User dean, List<Department> departments) {
		this.id = id;
		this.name = name;
		this.dean = dean;
		this.departments = departments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getDean() {
		return dean;
	}

	public void setDean(User dean) {
		this.dean = dean;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	

	
}
