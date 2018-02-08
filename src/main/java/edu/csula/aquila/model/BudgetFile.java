package edu.csula.aquila.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "budget")
public class BudgetFile extends FileInfo implements Serializable{

	private static final long serialVersionUID = 1L;


	
	@Column(name = "status")
	private String status;
	
	@Column(name = "uploader")
	private String nameOfUploader;
	
	@Column(name="is_completed")
	private boolean isCompleted;
	

	@ElementCollection
	@MapKeyColumn(name="file_path")
	@Column(name = "file_date")
	@CollectionTable(name = "file_description", joinColumns=@JoinColumn(name = "budget_id"))
	private Map<String,Date> filePaths;
	
	//proposal relationship
	@JsonIgnore
	@OneToOne(mappedBy="budget")
	Proposal proposal;

	
	
	public BudgetFile() {}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNameOfUploader() {
		return nameOfUploader;
	}

	public void setNameOfUploader(String nameOfUploader) {
		this.nameOfUploader = nameOfUploader;
	}


	public boolean isCompleted() {
		return isCompleted;
	}


	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Map<String, Date> getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(Map<String, Date> filePaths) {
		this.filePaths = filePaths;
	}


	public Proposal getProposal() {
		return proposal;
	}


	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	
	
}
