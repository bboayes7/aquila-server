package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("conflict_of_interest")
public class ConflictOfInterestForm extends Form implements Serializable {

	private static final long serialVersionUID = -8195316383450056859L;
	
	public enum CoiType {
		OIPHS, OINONPHS, PIPHS, PINONPHS
	}
	
	@Enumerated(EnumType.STRING)
	private CoiType type;

	// private User principleInvestigator;
	@Column(name="proposal_number")
	private Long proposalNumber;

	// OI: proposal information
	@Column(name="proposal_title")
	private String proposalTitle;

	// all: sponsor
	@Column
	private String sponsor;
	
	@Column(name="sponsor_type")
	private String sponsorType;
	
	@Column(name="sponsor_specification")
	private String sponsorSpecification;

	// all: reasons for disclosure
	@Column(name="new_proposal")
	private boolean newProposal;
	
	@Column(name="cont_add_funding")
	private boolean contAddFunding;
	
	@Column(name="new_change_investigator")
	private boolean newChangeInvestigator;
	
	@Column(name="new_interest_obtained")
	private boolean newInterestObtained;
	
	@Column(name="previous_proposal_number")
	private String previousProposalNumber;
	
	@Column(name="new_sponsor")
	private boolean newSponsor;
	
	@Column(name="previous_sponsor_name")
	private String previousSponsorName;
	
	@Column(name="request_from_irb")
	private boolean requestFromIrb;
	
	@Column(name="no_cost_time_extension")
	private boolean noCostTimeExtension;
	
	@Column
	private boolean other;
	
	@Column(name="other_specification")
	private String otherSpecification;
	
	@Column(name="budget_period_start")
	private Date budgetPeriodStart;
	
	@Column(name="budget_period_end")
	private Date budgetPeriodEnd;
	
	@Column(name="project_period_start")
	private Date projectPeriodStart;
	
	@Column(name="project_period_end")
	private Date projectPeriodEnd;
	
	@Column(name="amount_requested")
	private int amountRequested;
	
	@Column(name="irbacucibcno")
	private String iRBACUCIBCNo;
	// all: disclosure and certification
	
	@Column(name="significant_fin_interest")
	private boolean significantFinInterest;

	// private file addendum;

	// PI: listing other key personnel
	@Column(name="any_other_investigators")
	private boolean anyOtherInvestigators;
	
	@Column(name="other_investigators_value")
	private String otherInvestigatorsValue;

	// all: signatures
	@Column(name="pi_signature")
	private String piSignature;
	
	@Column(name="pi_date")
	private Date piDate;
	
	@Column(name="key_personnel_sign")
	private String keyPersonnelSign;
	
	@Column(name="key_personnel_date")
	private Date keyPersonnelDate;
	
	@Column(name="ari_official")
	private boolean aRIOfficial;
	
	@Column(name="ari_date")
	private Date aRIDate;

	@ManyToOne
	@JoinColumn(name = "prop_id", insertable=false, updatable=false)
	private Proposal proposal;
	
	public ConflictOfInterestForm() {
	}

	public ConflictOfInterestForm(CoiType type, Long proposalNumber, String proposalTitle, String sponsor,
			String sponsorType, String sponsorSpecification, boolean newProposal, boolean contAddFunding,
			boolean newChangeInvestigator, boolean newInterestObtained, String previousProposalNumber,
			boolean newSponsor, String previousSponsorName, boolean requestFromIrb, boolean noCostTimeExtension,
			boolean other, String otherSpecification, Date budgetPeriodStart, Date budgetPeriodEnd,
			Date projectPeriodStart, Date projectPeriodEnd, int amountRequested, String iRBACUCIBCNo,
			boolean significantFinInterest, boolean anyOtherInvestigators, String otherInvestigatorsValue,
			String piSignature, Date piDate, String keyPersonnelSign, Date keyPersonnelDate, boolean aRIOfficial,
			Date aRIDate, Proposal proposal) {
		this.type = type;
		this.proposalNumber = proposalNumber;
		this.proposalTitle = proposalTitle;
		this.sponsor = sponsor;
		this.sponsorType = sponsorType;
		this.sponsorSpecification = sponsorSpecification;
		this.newProposal = newProposal;
		this.contAddFunding = contAddFunding;
		this.newChangeInvestigator = newChangeInvestigator;
		this.newInterestObtained = newInterestObtained;
		this.previousProposalNumber = previousProposalNumber;
		this.newSponsor = newSponsor;
		this.previousSponsorName = previousSponsorName;
		this.requestFromIrb = requestFromIrb;
		this.noCostTimeExtension = noCostTimeExtension;
		this.other = other;
		this.otherSpecification = otherSpecification;
		this.budgetPeriodStart = budgetPeriodStart;
		this.budgetPeriodEnd = budgetPeriodEnd;
		this.projectPeriodStart = projectPeriodStart;
		this.projectPeriodEnd = projectPeriodEnd;
		this.amountRequested = amountRequested;
		this.iRBACUCIBCNo = iRBACUCIBCNo;
		this.significantFinInterest = significantFinInterest;
		this.anyOtherInvestigators = anyOtherInvestigators;
		this.otherInvestigatorsValue = otherInvestigatorsValue;
		this.piSignature = piSignature;
		this.piDate = piDate;
		this.keyPersonnelSign = keyPersonnelSign;
		this.keyPersonnelDate = keyPersonnelDate;
		this.aRIOfficial = aRIOfficial;
		this.aRIDate = aRIDate;
		this.proposal = proposal;
	}

	public CoiType getType() {
		return type;
	}

	public void setType(CoiType type) {
		this.type = type;
	}

	public Long getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Long proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getProposalTitle() {
		return proposalTitle;
	}

	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getSponsorType() {
		return sponsorType;
	}

	public void setSponsorType(String sponsorType) {
		this.sponsorType = sponsorType;
	}

	public String getSponsorSpecification() {
		return sponsorSpecification;
	}

	public void setSponsorSpecification(String sponsorSpecification) {
		this.sponsorSpecification = sponsorSpecification;
	}

	public boolean isNewProposal() {
		return newProposal;
	}

	public void setNewProposal(boolean newProposal) {
		this.newProposal = newProposal;
	}

	public boolean isContAddFunding() {
		return contAddFunding;
	}

	public void setContAddFunding(boolean contAddFunding) {
		this.contAddFunding = contAddFunding;
	}

	public boolean isNewChangeInvestigator() {
		return newChangeInvestigator;
	}

	public void setNewChangeInvestigator(boolean newChangeInvestigator) {
		this.newChangeInvestigator = newChangeInvestigator;
	}

	public boolean isNewInterestObtained() {
		return newInterestObtained;
	}

	public void setNewInterestObtained(boolean newInterestObtained) {
		this.newInterestObtained = newInterestObtained;
	}

	public String getPreviousProposalNumber() {
		return previousProposalNumber;
	}

	public void setPreviousProposalNumber(String previousProposalNumber) {
		this.previousProposalNumber = previousProposalNumber;
	}

	public boolean isNewSponsor() {
		return newSponsor;
	}

	public void setNewSponsor(boolean newSponsor) {
		this.newSponsor = newSponsor;
	}

	public String getPreviousSponsorName() {
		return previousSponsorName;
	}

	public void setPreviousSponsorName(String previousSponsorName) {
		this.previousSponsorName = previousSponsorName;
	}

	public boolean isRequestFromIrb() {
		return requestFromIrb;
	}

	public void setRequestFromIrb(boolean requestFromIrb) {
		this.requestFromIrb = requestFromIrb;
	}

	public boolean isNoCostTimeExtension() {
		return noCostTimeExtension;
	}

	public void setNoCostTimeExtension(boolean noCostTimeExtension) {
		this.noCostTimeExtension = noCostTimeExtension;
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public String getOtherSpecification() {
		return otherSpecification;
	}

	public void setOtherSpecification(String otherSpecification) {
		this.otherSpecification = otherSpecification;
	}

	public Date getBudgetPeriodStart() {
		return budgetPeriodStart;
	}

	public void setBudgetPeriodStart(Date budgetPeriodStart) {
		this.budgetPeriodStart = budgetPeriodStart;
	}

	public Date getBudgetPeriodEnd() {
		return budgetPeriodEnd;
	}

	public void setBudgetPeriodEnd(Date budgetPeriodEnd) {
		this.budgetPeriodEnd = budgetPeriodEnd;
	}

	public Date getProjectPeriodStart() {
		return projectPeriodStart;
	}

	public void setProjectPeriodStart(Date projectPeriodStart) {
		this.projectPeriodStart = projectPeriodStart;
	}

	public Date getProjectPeriodEnd() {
		return projectPeriodEnd;
	}

	public void setProjectPeriodEnd(Date projectPeriodEnd) {
		this.projectPeriodEnd = projectPeriodEnd;
	}

	public int getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(int amountRequested) {
		this.amountRequested = amountRequested;
	}

	public String getiRBACUCIBCNo() {
		return iRBACUCIBCNo;
	}

	public void setiRBACUCIBCNo(String iRBACUCIBCNo) {
		this.iRBACUCIBCNo = iRBACUCIBCNo;
	}

	public boolean isSignificantFinInterest() {
		return significantFinInterest;
	}

	public void setSignificantFinInterest(boolean significantFinInterest) {
		this.significantFinInterest = significantFinInterest;
	}

	public boolean isAnyOtherInvestigators() {
		return anyOtherInvestigators;
	}

	public void setAnyOtherInvestigators(boolean anyOtherInvestigators) {
		this.anyOtherInvestigators = anyOtherInvestigators;
	}

	public String getOtherInvestigatorsValue() {
		return otherInvestigatorsValue;
	}

	public void setOtherInvestigatorsValue(String otherInvestigatorsValue) {
		this.otherInvestigatorsValue = otherInvestigatorsValue;
	}

	public String getPiSignature() {
		return piSignature;
	}

	public void setPiSignature(String piSignature) {
		this.piSignature = piSignature;
	}

	public Date getPiDate() {
		return piDate;
	}

	public void setPiDate(Date piDate) {
		this.piDate = piDate;
	}

	public String getKeyPersonnelSign() {
		return keyPersonnelSign;
	}

	public void setKeyPersonnelSign(String keyPersonnelSign) {
		this.keyPersonnelSign = keyPersonnelSign;
	}

	public Date getKeyPersonnelDate() {
		return keyPersonnelDate;
	}

	public void setKeyPersonnelDate(Date keyPersonnelDate) {
		this.keyPersonnelDate = keyPersonnelDate;
	}

	public boolean isaRIOfficial() {
		return aRIOfficial;
	}

	public void setaRIOfficial(boolean aRIOfficial) {
		this.aRIOfficial = aRIOfficial;
	}

	public Date getaRIDate() {
		return aRIDate;
	}

	public void setaRIDate(Date aRIDate) {
		this.aRIDate = aRIDate;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}