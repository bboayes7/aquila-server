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
@DiscriminatorValue("coi_kp_non_phs")
public class ConflictOfInterestKPNonPHS extends Form implements Serializable{
	
	private static final long serialVersionUID = -8903143544660218794L;

	private int progress;
	
	@Column(name = "pi")
	private String pi;

	@Column(name = "proposal_number")
	private long proposalNumber;

	@Column(name="proposal_title")
	private	String proposalTitle; //come from intake??

	@Column(name="sponsor")
	private String sponsor;

	@Column(name="sub_award")
	private boolean subAward; 
	//strings for boolean subaward if true
	
	@Column(name = "subaward_sponsor")
	private String subAwardSponsor;
	
	@Column(name = "subaward_agency")
	private String subAwardAgency;

	@ElementCollection
	@MapKeyColumn(name="reasons")
	@Column(name="previous_info")
	@CollectionTable(name = "kp_nonphs_disclosure_reasons", joinColumns=@JoinColumn(name = "kp_nophs_disclosure_reasons_id"))
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
	private double amountRequested;

	@Column
	private long iRBACUCIBCNo; //name unclear

	@Column(name = "significat_fin_interest")
	private boolean significantFinInterest;

	@Column(name = "key_personnel_sign")
	private Signature keyPersonnelSign; //needs signature and print, signature its own class?

	@Column(name = "key_personnel_date")
	private Date keyPersonnelDate;

	@Column(name = "ari_official")
	private boolean aRIOfficial;

	@Column(name = "ari_date")
	private Date aRIDate;
	

	public ConflictOfInterestKPNonPHS(){}
	
	public ConflictOfInterestKPNonPHS(int progress, String pi, long proposalNumber, String proposalTitle,
			String sponsor, boolean subAward, String subAwardSponsor, String subAwardAgency,
			Map<Boolean, String> disclosureReasons, Date budgetPeriodStart, Date budgetPeriodEnd,
			Date projectPeriodStart, Date projectPeriodEnd, double amountRequested, long iRBACUCIBCNo,
			boolean significantFinInterest, Signature keyPersonnelSign, Date keyPersonnelDate, boolean aRIOfficial,
			Date aRIDate) {
		super();
		this.progress = progress;
		this.pi = pi;
		this.proposalNumber = proposalNumber;
		this.proposalTitle = proposalTitle;
		this.sponsor = sponsor;
		this.subAward = subAward;
		this.subAwardSponsor = subAwardSponsor;
		this.subAwardAgency = subAwardAgency;
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

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getPi() {
		return pi;
	}

	public void setPi(String pi) {
		this.pi = pi;
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

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public boolean isSubAward() {
		return subAward;
	}

	public void setSubAward(boolean subAward) {
		this.subAward = subAward;
	}

	public String getSubAwardSponsor() {
		return subAwardSponsor;
	}

	public void setSubAwardSponsor(String subAwardSponsor) {
		this.subAwardSponsor = subAwardSponsor;
	}

	public String getSubAwardAgency() {
		return subAwardAgency;
	}

	public void setSubAwardAgency(String subAwardAgency) {
		this.subAwardAgency = subAwardAgency;
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

	public double getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(double amountRequested) {
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