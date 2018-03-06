package edu.csula.aquila.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "departments")
public class Department implements Serializable{

	private static final long serialVersionUID = 3067866448841490265L;

	@Id
	@GeneratedValue
	@Column(name="department_id")
	private Long id;
	
	@Column(nullable = false, unique = true, name = "department_name")
	private String name;
	
	@OneToMany(mappedBy ="department", cascade = CascadeType.ALL)
	private List<User> faculty;
	
	@OneToOne
	@JoinColumn(name ="dept_chair_id")
	private User depChair;
	
	@ManyToOne
	@JoinColumn(name ="college_id")
	private College college;

	public Department() {}

	public Department(String name, List<User> faculty, User depChair, College college) {
		this.name = name;
		this.faculty = faculty;
		this.depChair = depChair;
		this.college = college;
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

	public List<User> getFaculty() {
		return faculty;
	}

	public void setFaculty(List<User> faculty) {
		this.faculty = faculty;
	}

	public User getDepChair() {
		return depChair;
	}

	public void setDepChair(User depChair) {
		this.depChair = depChair;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
	
}