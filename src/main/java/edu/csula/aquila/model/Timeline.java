package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name = "timeline")
public class Timeline implements Serializable {

	private static final long serialVersionUID = -3646369830165962564L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeline_id")
	private Long Id;

	@Column(name = "principal_investigator")
	private String principalInvestigator;

	@ElementCollection
	@CollectionTable(name = "timeline_co_pis", joinColumns=@JoinColumn(name = "timeline_id"))
	private List<String> coPI;

	@Column(name = "proposal_name")
	private String proposalName;

	@Column(name = "funding_agency")
	private String fundingAgency;

	@Column(name = "uas_date")
	private Date uasDueDate;

	@Column(name = "sponsor_date")
	private Date sponsorDueDate;

	@Column(name = "final_sign_date")
	private Date finalSign;

	@JsonIgnore
	@JsonProperty(access=Access.READ_ONLY)	
	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "timeline")
	private List<Stage> stages;
	

	// proposal relationship
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "timeline")
	Proposal proposal;


	public Timeline()
	{
			
	}
	
	//default timeline , still in progress
	public Timeline(Date uasDueDate) 
	{		
		this.uasDueDate = uasDueDate;
		
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
		
		//2 days before shipping date
		deadline.setTime(dueDate);
		deadline.add(Calendar.DATE, -2);
		Date deadline4 = deadline.getTime();
		
		//instantiate list of default stages
		List<Stage> defaultStages = new ArrayList<Stage>();
		
		//create default stages
		Stage stage1 = new Stage(1, "First Budget Due", deadline1, "Principal Investigator", null, null);
		Stage stage2 = new Stage(2, "Final Budget Due", deadline2, "Principal Investigator", null, null);
		Stage stage3 = new Stage(3, "Print Forms/ Project Summary", deadline3, "Principal Investigator", null, null);
		Stage stage4 = new Stage(4, "Final Proposal", deadline4, "Principal Investigator", null, null);
		
		//set timeline to stage and add default stages
		stage1.setTimeline(this);
		defaultStages.add(stage1);
		stage2.setTimeline(this);
		defaultStages.add(stage2);
		stage3.setTimeline(this);
		defaultStages.add(stage3);
		stage4.setTimeline(this);
		defaultStages.add(stage4);
		this.stages = defaultStages;

				
	}
	

	public Timeline(String principalInvestigator, List<String> coPI, String proposalName, String fundingAgency,
			Date uasDueDate, Date sponsorDueDate, Date finalSign, List<Stage> stages) {
		this.principalInvestigator = principalInvestigator;
		this.coPI = coPI;
		this.proposalName = proposalName;
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

	public String getProposalName() {
		return proposalName;
	}

	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
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

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
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
		@ManyToMany
		@MapKeyColumn(name = "file_name")
		@Column(name = "file")
		@JoinTable(
		            name = "required_files",
		            joinColumns = @JoinColumn(name = "required_file_id"),
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

}