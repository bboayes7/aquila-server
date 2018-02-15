package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "timeline")
public class Timeline implements Serializable {

	private static final long serialVersionUID = -3646369830165962564L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeline_id")
	private Long Id;

	@Column(name = "principal_investigator")
	String principalInvestigator;

	@ElementCollection
	@Column(name = "co_pis")
	List<String> coPI;

	@Column(name = "proposal")
	String proposal;// unclear if proposal name or code

	@Column(name = "funding_agency")
	String fundingAgency;

	@Column(name = "uas_date")
	Date uasDueDate;

	@Column(name = "sponsor_date")
	Date sponsorDueDate;

	@Column(name = "final_sign_date")
	Date finalSign;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "timeline_id", nullable = true)
	List<Stage> stages;
	

	// proposal relationship
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.ALL}, mappedBy="timeline")
	@JoinColumn(name="proposal_id", nullable = false)
	Proposal proposalForm;
	

	public Timeline()
	{
	 
	}
	
	public Timeline(Date uasDueDate) 
	{
		//list of default stages
		List<Timeline.Stage> preFill = new ArrayList<Timeline.Stage>();
		
		//create instance of date
		Calendar deadline = Calendar.getInstance();
		Date dueDate = uasDueDate;
		
		//15 days before shipping date
		deadline.setTime(dueDate);
		deadline.add(Calendar.DATE, -15);
		Date deadline1 = deadline.getTime();
		
		//11 days before shipping date
		deadline.setTime(dueDate);
		deadline.add(Calendar.DATE, -11);
		Date deadline2 = deadline.getTime();
		
		//7 days before shipping date
		deadline.setTime(dueDate);
		deadline.add(Calendar.DATE, -7);
		Date deadline3 = deadline.getTime();
		/*
		deadline.setTime(dueDate);
		deadline.add(Calendar.DATE, -2);
		Date deadline4 = deadline.getTime();
		*/
		
		//file lists and form lists for each stage
		Map<String,FileInfo> files1 = new HashMap<>();
		Map<String,FileInfo> files2 = new HashMap<>();
		Map<String,FileInfo> files3 = new HashMap<>();
		Map<String,Form> forms2 = new HashMap<>();
		Map<String,Form> forms3 = new HashMap<>();
		
		/*files1.put("Budget");
		files2.put("Sub Contract Documents");
		files2.put("Equipment Quotes & Specs");
		files3.put("Supporting Letters");
		files3.put("Signatures PDF");
		
		forms2.put("Budget");
		forms2.put("Equipment");
		forms3.put("Intake Form");
		forms3.put("Conflict of Interest");
		forms3.put("Approval");
		*/
		//create default stages
		Timeline.Stage stage1 = new Timeline.Stage("First Budget Due", deadline1, "Principal Investigator", null, files1);
		Timeline.Stage stage2 = new Timeline.Stage("Final Budget Due", deadline2, "Principal Investigator", forms2, files2);
		Timeline.Stage stage3 = new Timeline.Stage("Print Forms/ Project Summary", deadline3, "Principal Investigator", forms3, files3);
		
		
		//add default stages
		preFill.add(stage1);
		preFill.add(stage2);
		preFill.add(stage3);
		this.setStages(preFill);
		
	}
	

	public Timeline(String principalInvestigator, List<String> coPI, String proposal, String fundingAgency,
			Date uasDueDate, Date sponsorDueDate, Date finalSign, List<Stage> stages) {
		this.principalInvestigator = principalInvestigator;
		this.coPI = coPI;
		this.proposal = proposal;
		this.fundingAgency = fundingAgency;
		this.uasDueDate = uasDueDate;
		this.sponsorDueDate = sponsorDueDate;
		this.finalSign = finalSign;
		this.stages = stages;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}

	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	public List<String> getCoPI() {
		return coPI;
	}

	public void setCoPI(List<String> coPI) {
		this.coPI = coPI;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getFundingAgency() {
		return fundingAgency;
	}

	public void setFundingAgency(String fundingAgency) {
		this.fundingAgency = fundingAgency;
	}

	public Date getUasDueDate() {
		return uasDueDate;
	}

	public void setUasDueDate(Date uasDueDate) {
		this.uasDueDate = uasDueDate;
	}

	public Date getSponsorDueDate() {
		return sponsorDueDate;
	}

	public void setSponsorDueDate(Date sponsorDueDate) {
		this.sponsorDueDate = sponsorDueDate;
	}

	public Date getFinalSign() {
		return finalSign;
	}

	public void setFinalSign(Date finalSign) {
		this.finalSign = finalSign;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	public Proposal getProposalForm() {
		return proposalForm;
	}

	public void setProposalForm(Proposal proposalForm) {
		this.proposalForm = proposalForm;
	}

	// Timeline contains a list of stages
	// This is the innerclass of stage to help a uas member
	// manage the timeline
	@Entity
	@Table(name = "stage")
	public static class Stage implements Serializable {

		private static final long serialVersionUID = 4793986574923358796L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "stage_id")
		Long Id;

		@Column
		String name;

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
	    @JoinTable(
	            name = "required_forms",
	            joinColumns = @JoinColumn(name = "form_id"),
	            inverseJoinColumns = @JoinColumn(name = "timeline_id"))
		Map<String, Form> requiredForms;

		//Files as a map 
		@ElementCollection
		@MapKeyColumn(name = "file_name")
		@Column(name = "file")
		@JoinTable(
		            name = "required_files",
		            joinColumns = @JoinColumn(name = "file_info_id"),
		            inverseJoinColumns = @JoinColumn(name = "timeline_id"))
		Map<String, FileInfo> requiredFiles;

		// allows uas member to add comments to a stage if needed
		@Column(name = "comments")
		String addComments;
		
		@JsonIgnore
		@ManyToOne(cascade = { CascadeType.ALL })
		@JoinColumn(name = "timeline_id")
		Timeline timeline;

		public Stage() {
		}
		
		public Stage(String name, Date expectedDate, String deadlineType, Map<String,Form> requiredForms, Map<String,FileInfo> requiredFiles) 
		{
			this.name = name;
			this.expectedDate = expectedDate;
			this.deadlineType = deadlineType;
			this.requiredForms = requiredForms;
			this.requiredFiles = requiredFiles;
		}


		public Stage(String name, Date expectedDate, Date completedDate, boolean uasReviewRequired, boolean uasReviewed,
				String deadlineType, Map<String, Form> requiredForms, Map<String, FileInfo> requiredFiles,
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


		public String getAddComments() {
			return addComments;
		}

		public void setAddComments(String addComments) {
			this.addComments = addComments;
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

		public Timeline getTimeline() {
			return timeline;
		}

		public void setTimeline(Timeline timeline) {
			this.timeline = timeline;
		}

		public Map<String, Form> getRequiredForms() {
			return requiredForms;
		}

		public void setRequiredForms(Map<String, Form> requiredForms) {
			this.requiredForms = requiredForms;
		}

		public Map<String, FileInfo> getRequiredFiles() {
			return requiredFiles;
		}

		public void setRequiredFiles(Map<String, FileInfo> requiredFiles) {
			this.requiredFiles = requiredFiles;
		}

	}

}