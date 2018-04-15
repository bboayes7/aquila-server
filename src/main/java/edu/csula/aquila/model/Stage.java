package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stage")
public class Stage implements Serializable {

	private static final long serialVersionUID = 4793986574923358796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stage_id")
	Long Id;

	@Column
	String name;
	
	@Column(name = "stage_order")
	int stageOrder;

	@Column(name = "expected_date")
	Date expectedDate;

	@Column(name = "completed_date")
	Date completedDate;
	
	//these two booleans allow a PI to move on to the next stage
	//a PI can move on to the next stage when the first one is false and the second one is true
	
	//when PI completes a stage turn to true
	@Column(name = "uas_review_required")
	boolean uasReviewRequired;
	
	//When uas completes a review for a stage turn to true
	@Column(name ="uas_reviewed")
	boolean uasReviewed;
	
	//Deadline Type (for PI or ORSP)
	@Column(name = "deadline_type")
	String deadlineType;
	
	//Forms as a map (Test)
	@ElementCollection
	@MapKeyColumn(name = "form_name")
	@Column(name = "form_id")
	@CollectionTable(name = "required_forms", joinColumns = @JoinColumn(name = "required_form_id"))
	Map<String, Long> requiredForms;

	//Files as a map 
	@ElementCollection
	@MapKeyColumn(name = "file_name")
	@Column(name = "file")
	@JoinTable(
	            name = "required_files",
	            joinColumns = @JoinColumn(name = "file_info_id"),
	            inverseJoinColumns = @JoinColumn(name = "stage_id"))
	Map<String, FileInfo> requiredFiles;

	// allows uas member to add comments to a stage if needed
	@Column(name = "comments")
	String addComments;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "timeline_id")
	Timeline timeline;
	
	

	public Stage() {
	}
	
	public Stage( int stageOrder, String name, Date expectedDate, String deadlineType, Map<String, Long> requiredForms, Map<String, FileInfo> requiredFiles ) 
	{
		this.stageOrder = stageOrder;
		this.name = name;
		this.expectedDate = expectedDate;
		this.deadlineType = deadlineType;
		this.requiredFiles = requiredFiles;
		this.requiredForms = requiredForms;
		
	}




	public Stage(String name, Date expectedDate, Date completedDate, boolean uasReviewRequired, boolean uasReviewed,
			String deadlineType, Map<String, Long> requiredForms, Map<String, FileInfo> requiredFiles,
			String addComments) {
		this.name = name;
		this.expectedDate = expectedDate;
		this.completedDate = completedDate;
		this.uasReviewRequired = uasReviewRequired;
		this.uasReviewed = uasReviewed;
		this.deadlineType = deadlineType;
		this.requiredForms = requiredForms;
		this.requiredFiles = requiredFiles;
		this.addComments = addComments;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public boolean isUasReviewRequired() {
		return uasReviewRequired;
	}

	public void setUasReviewRequired(boolean uasReviewRequired) {
		this.uasReviewRequired = uasReviewRequired;
	}

	public boolean isUasReviewed() {
		return uasReviewed;
	}

	public void setUasReviewed(boolean uasReviewed) {
		this.uasReviewed = uasReviewed;
	}

	public String getDeadlineType() {
		return deadlineType;
	}

	public void setDeadlineType(String deadlineType) {
		this.deadlineType = deadlineType;
	}

	public Map<String, Long> getRequiredForms() {
		return requiredForms;
	}

	public void setRequiredForms(Map<String, Long> requiredForms) {
		this.requiredForms = requiredForms;
	}

	public Map<String, FileInfo> getRequiredFiles() {
		return requiredFiles;
	}

	public void setRequiredFiles(Map<String, FileInfo> requiredFiles) {
		this.requiredFiles = requiredFiles;
	}

	public String getAddComments() {
		return addComments;
	}

	public void setAddComments(String addComments) {
		this.addComments = addComments;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public int getStageOrder() {
		return stageOrder;
	}

	public void setStageOrder(int stageOrder) {
		this.stageOrder = stageOrder;
	}




}