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
@DiscriminatorValue("conflict_of_interest_pi_phs")
public class ConflictOfInterestPHS extends Form implements Serializable{

	//identical to Non PHS, excludes bool subaward, sponsor as Map<Boolean,String>
	
	private static final long serialVersionUID = -8195316383450056859L;
	
	private int progress;
	
	@ElementCollection
	@MapKeyColumn(name="sponsor_name")
	@Column(name="is_sponsor")
	@CollectionTable(name = "phs_sponsor", joinColumns=@JoinColumn(name = "phs_sponsor_id"))
	private Map<Boolean,String> sponsor;
	
	@ElementCollection
	@MapKeyColumn(name="reasons")
	@Column(name="previous_info")
	@CollectionTable(name = "pi_phs_disclosure_reasons", joinColumns=@JoinColumn(name = "pi_phs_disclosure_reasons_id"))
	private Map<Boolean,String> disclosureReasons;
	
	@Column(name = "proposal_title")
	private String proposalTitle; 
	
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
	
	@Column(name="irb_iacuc_ibc_no")
	private long iRBACUCIBCNo; //name unclear
	
	@Column(name = "siginificant_financial_interest")
	private boolean significantFinInterest;
	
	@Column(name = "pi_sign")
	private Signature piSign; 
	
	@Column(name = "pi_date")
	private Date piDate;
	
	@Column(name = "ari_official")
	private boolean aRIOfficial;
	
	@Column(name = "ari_date")
	private Date aRIDate;
	
	public ConflictOfInterestPHS() {}
	
	public ConflictOfInterestPHS(int progress, Map<Boolean, String> sponsor, Map<Boolean, String> disclosureReasons,
			String proposalTitle, Date budgetPeriodStart, Date budgetPeriodEnd, Date projectPeriodStart,
			Date projectPeriodEnd, double amountRequested, long iRBACUCIBCNo, boolean significantFinInterest,
			Signature piSign, Date piDate, boolean aRIOfficial, Date aRIDate) {
		super();
		this.progress = progress;
		this.sponsor = sponsor;
		this.disclosureReasons = disclosureReasons;
		this.proposalTitle = proposalTitle;
		this.budgetPeriodStart = budgetPeriodStart;
		this.budgetPeriodEnd = budgetPeriodEnd;
		this.projectPeriodStart = projectPeriodStart;
		this.projectPeriodEnd = projectPeriodEnd;
		this.amountRequested = amountRequested;
		this.iRBACUCIBCNo = iRBACUCIBCNo;
		this.significantFinInterest = significantFinInterest;
		this.piSign = piSign;
		this.piDate = piDate;
		this.aRIOfficial = aRIOfficial;
		this.aRIDate = aRIDate;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
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

	public Signature getPiSign() {
		return piSign;
	}

	public void setPiSign(Signature piSign) {
		this.piSign = piSign;
	}

	public Date getPiDate() {
		return piDate;
	}

	public void setPiDate(Date piDate) {
		this.piDate = piDate;
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