package edu.csula.aquila.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.csula.aquila.model.Timeline.Stage;

@Entity
@Table(name = "form")
public class Form implements Serializable{

	private static final long serialVersionUID = 4420553464083646669L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="form_id")
	Long Id;
	
	@Column(name = "is_complete")
	boolean isComplete;
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "stage_id")
	Timeline.Stage stage;
	
	public Form() {}
	
	public Form(boolean isComplete, Stage stage) {
		this.isComplete = isComplete;
		this.stage = stage;
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

	public Timeline.Stage getStage() {
		return stage;
	}

	public void setStage(Timeline.Stage stage) {
		this.stage = stage;
	}
	
	
	
}
