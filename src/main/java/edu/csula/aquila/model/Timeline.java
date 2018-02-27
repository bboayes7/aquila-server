package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
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
	@CollectionTable(name = "timeline_co_pis", joinColumns=@JoinColumn(name = "timeline_id"))
	List<String> coPI;

	@Column(name = "proposal_name")
	String proposalName;// unclear if proposal name or code

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
	@OneToOne
	@JoinColumn(name="proposal_id", nullable = false)
	Proposal proposal;

	public Timeline() {
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

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
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
		@MapKeyColumn(name = "form_id")
		@Column(name = "form_name")
		@CollectionTable(name = "required_forms", joinColumns = @JoinColumn(name = "required_form_id"))
		Map<String, Long> requiredForms;

		//Files as a map (Test)
		@ElementCollection
		@MapKeyColumn(name = "file_name")
		@Column(name = "file")
		@JoinTable(
		            name = "required_files",
		            joinColumns = @JoinColumn(name = "file_info_id"),
		            inverseJoinColumns = @JoinColumn(name = "stage_id"))
		Map<String, FileInfo> requiredFiles;

		// allows uas member to add comments to a stage if needed
		@ElementCollection
		@CollectionTable(name = "add_columns", joinColumns = @JoinColumn(name = "timeline_id"))
		@Column(name = "comment")
		List<String> addComments;
		
		@JsonIgnore
		@ManyToOne(cascade = { CascadeType.ALL })
		@JoinColumn(name = "timeline_id")
		Timeline timeline;

		public Stage() {
		}

		public Stage(String name, Date expectedDate, Date completedDate, boolean uasReviewRequired, boolean uasReviewed,
				String deadlineType, Map<String, Long> requiredForms, Map<String, FileInfo> requiredFiles,
				List<String> addComments) {
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


		public List<String> getAddComments() {
			return addComments;
		}

		public void setAddComments(List<String> addComments) {
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
		

//		public void stageCheck() {
//			// check if all forms are completed through the isComplete boolean
//			boolean formsComplete = false;
//			boolean filesUploaded = false; // consider if stages dont have required files or required forms
//			
//			System.out.println("HEYYYY ");
//
//			List<Long> forms = (List<Long>) getRequiredForms().keySet();
//			List<FileInfo> files = (List<FileInfo>) getRequiredFiles().values(); // get a list of budgets too
//
//			// check if all forms are complete (maybe add a condition if form list is 0 then
//			// dont call this?)
//			if (forms.size() != 0) {
//				for (Form form : forms) {
//					if (!form.isComplete()) {
//						break;
//					} else {
//						formsComplete = true; // if all forms are complete, have a boolean called formsCompleted and set it to true
//
//					}
//				}
//			}
//
//			// check if all files are uploaded
//			if (files.size() != 0) {
//				for (FileInfo file : files) {
//					if (!file.isUploaded()) {
//						break;
//					} else {
//						filesUploaded = true; // if all files are uploaded, have a boolean called filesUploaded and set it to true
//
//					}
//				}
//			}
//
//			// find out how to implement budget checking
//
//			// when formsCompleted && filesUploaded is true
//			if (formsComplete && filesUploaded) {
//				// set uasReviewRequired to true
//				setUasReviewRequired(true);
//				// send an email to UAS
//				// for now just print email sent
//				System.out.println("Email 'sent'");
//			}
//		}

	}

}