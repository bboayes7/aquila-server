package edu.csula.aquila.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;



@Entity
@Table(name = "form")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="form_type")
@DiscriminatorValue("form")
public class Form implements Serializable{

	private static final long serialVersionUID = 4420553464083646669L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="form_id")
	private Long Id;
	
	@Column(name = "is_complete")
	private boolean isComplete;
//	
//	@Column(name = "progress")
//	private int progress;
	
//	@JsonIgnore
//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "stage_id")
//	Timeline.Stage stage;
	
	public Form() {}
	
	public Form(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

//	public int getProgress() {
//		return progress;
//	}
//
//	public void setProgress(int progress) {
//		this.progress = progress;
//	}

	
	
}
