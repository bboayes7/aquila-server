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
import javax.persistence.FetchType;
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
		
		//instantiate list of default stages
		List<Stage> defaultStages = new ArrayList<Stage>();
		
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
		
		
		//file lists and form lists for each stage
		Map<String,FileInfo> files1 = new HashMap<>();
		Map<String,FileInfo> files2 = new HashMap<>();
		Map<String,FileInfo> files3 = new HashMap<>();
		Map<String,FileInfo> files4 = new HashMap<>();
		Map<String,Long> forms2 = new HashMap<>();
		Map<String,Long> forms3 = new HashMap<>();
		Map<String,Long> forms4 = new HashMap<>();
		
		
		//put filename as key in maps
		files1.put("First Budget", null);
		files2.put("Sub Contract Documents", null);
		files2.put("Final Budget", null);
		files2.put("Equipment Quotes & Specs", null);
		files3.put("Supporting Letters", null);
		files3.put("Signatures PDF", null);
		
		//put forms into map, still implementing
		/*forms2.put("Budget");
		forms2.put("Equipment");
		forms3.put("Intake Form");
		forms3.put("Conflict of Interest");
		forms3.put("Approval");
		*/
		
		//create default stages
		Stage stage1 = new Stage("First Budget Due", deadline1, "Principal Investigator", null, files1);
		Stage stage2 = new Stage("Final Budget Due", deadline2, "Principal Investigator", forms2, files2);
		Stage stage3 = new Stage("Print Forms/ Project Summary", deadline3, "Principal Investigator", forms3, files3);
		Stage stage4 = new Stage("Final Proposal", deadline4, "Principal Investigator", forms4, files4);
		
		
		//set timeline to stage
		//add default stages
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


}