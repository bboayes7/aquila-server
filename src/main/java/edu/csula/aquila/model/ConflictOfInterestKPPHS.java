package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;


@Entity
@DiscriminatorValue("conflict_of_interest_kp_phs")
public class ConflictOfInterestKPPHS extends Form implements Serializable{

	//identical to Non PHS, excludes bool subaward, sponsor as Map<Boolean,String>
	

	private static final long serialVersionUID = 1630516463656275117L;

	private int progress;

	@Column(name = "pi")
	private String pi;

	@Column(name="proposal_number")
	private long proposalNumber;

	@Column(name="proposal_title")
	private String proposalTitle; //come from intake??

	@ElementCollection
	@MapKeyColumn(name="sponsor_type")
	@Column(name="sponsor_name")
	@CollectionTable(name = "kp_sponsor", joinColumns=@JoinColumn(name = "kp_sponsor_id"))
	private Map<Boolean,String> sponsor;
	
	@ElementCollection
	@MapKeyColumn(name="reasons")
	@Column(name="previous_info")
	@CollectionTable(name = "kp_disclosure_reasons", joinColumns=@JoinColumn(name = "kp_disclosure_reasons_id"))
	private Map<Boolean,String> disclosureReasons;

	@Column(name = "budget_period_start")
	private Date budgetPeriodStart;

	@Column(name = "budget_period_end")
	private Date budgetPeriodEnd;

	@Column(name = "project_period_start")
	private Date projectPeriodStart;

	@Column(name = "project_period_end")
	private Date projectPeriodEnd;

	@Column(name = "amount_requested")
	private Integer amountRequested;

	@Column
	private long iRBACUCIBCNo; //name unclear

	@Column(name = "significant_fin_interest")
	private boolean significantFinInterest;

	@Column(name = "key_personnel_sign")
	private Signature keyPersonnelSign; //needs signature and print, signature its own class?

	@Column(name = "key_personnel_date")
	private Date keyPersonnelDate;

	@Column(name = "ari_official")
	private boolean aRIOfficial;

	@Column(name = "ari_date")
	private Date aRIDate;
	
	public ConflictOfInterestKPPHS() {}

	public ConflictOfInterestKPPHS(int progress, String pi, long proposalNumber, String proposalTitle,
			Map<Boolean, String> sponsor, Map<Boolean, String> disclosureReasons, Date budgetPeriodStart,
			Date budgetPeriodEnd, Date projectPeriodStart, Date projectPeriodEnd, Integer amountRequested,
			long iRBACUCIBCNo, boolean significantFinInterest, Signature keyPersonnelSign, Date keyPersonnelDate,
			boolean aRIOfficial, Date aRIDate) {
		super();
		this.progress = progress;
		this.pi = pi;
		this.proposalNumber = proposalNumber;
		this.proposalTitle = proposalTitle;
		this.sponsor = sponsor;
		this.disclosureReasons = disclosureReasons;
		this.budgetPeriodStart = budgetPeriodStart;
		this.budgetPeriodEnd = budgetPeriodEnd;
		this.projectPeriodStart = projectPeriodStart;
		this.projectPeriodEnd = projectPeriodEnd;
		this.amountRequested = amountRequested;
		this.iRBACUCIBCNo = iRBACUCIBCNo;
		this.significantFinInterest = significantFinInterest;
		this.keyPersonnelSign = keyPersonnelSign;
		this.keyPersonnelDate = keyPersonnelDate;
		this.aRIOfficial = aRIOfficial;
		this.aRIDate = aRIDate;
	}


	public String getPi() {
		return pi;
	}


	public void setPi(String pi) {
		this.pi = pi;
	}


	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}


	public long getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(long proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getProposalTitle() {
		return proposalTitle;
	}

	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}
		

	public Map<Boolean, String> getSponsor() {
		return sponsor;
	}

	public void setSponsor(Map<Boolean, String> sponsor) {
		this.sponsor = sponsor;
	}

	public Map<Boolean, String> getDisclosureReasons() {
		return disclosureReasons;
	}

	public void setDisclosureReasons(Map<Boolean, String> disclosureReasons) {
		this.disclosureReasons = disclosureReasons;
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

	public Integer getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(Integer amountRequested) {
		this.amountRequested = amountRequested;
	}

	public long getiRBACUCIBCNo() {
		return iRBACUCIBCNo;
	}

	public void setiRBACUCIBCNo(long iRBACUCIBCNo) {
		this.iRBACUCIBCNo = iRBACUCIBCNo;
	}

	public boolean isSignificantFinInterest() {
		return significantFinInterest;
	}

	public void setSignificantFinInterest(boolean significantFinInterest) {
		this.significantFinInterest = significantFinInterest;
	}

	public Signature getKeyPersonnelSign() {
		return keyPersonnelSign;
	}

	public void setKeyPersonnelSign(Signature keyPersonnelSign) {
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
	
	
}