package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "proposal")
public class Proposal implements Serializable{
	
	private static final long serialVersionUID = -1631828272936137622L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="proposal_id")
	private Long id;

	@Column(name="proposal_name")
	private String proposalName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_created")
	Date dateCreated;
	
	@Column(name="status")
	String status;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="user_id")
	User user;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="intake_form_id")
	IntakeForm intakeForm;
	
//	@JsonIgnore
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="timeline_id")
	Timeline timeline;
//	
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="approval_form_id")
//	private ApprovalForm approvalForm;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="coi_kp_non_phs_id")
//	private ConflictOfInterestKPNonPHS coiKpNonPhs;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="coi_kp_phs_id")
//	private ConflictOfInterestKPPHS coiKpPhs;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="coi_pi_non_phs_id")
//	private ConflictOfInterestPINonPHS coiPiNonphs;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="coi_phs_id")
//	private ConflictOfInterestPHS coiPhs;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="equipment_form_id")
//	private EquipmentForm equipmentForm;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="timeline_id")
//	private Timeline timeline;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name="budget_form_id")
//	BudgetFile budgetForm;

	public Proposal(){}
	
	public Proposal(String proposalName, User user, String status, Date dateCreated) {
		this.proposalName = proposalName;
		this.status = status;
		this.user = user;
		this.dateCreated = dateCreated;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProposalName() {
		return proposalName;
	}



	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public IntakeForm getIntakeForm() {
		return intakeForm;
	}

	public void setIntakeForm(IntakeForm intakeForm) {
		this.intakeForm = intakeForm;
	}

//	public ApprovalForm getApprovalForm() {
//		return approvalForm;
//	}
//
//	public void setApprovalForm(ApprovalForm approvalForm) {
//		this.approvalForm = approvalForm;
//	}
//
//	public ConflictOfInterestKPNonPHS getCoiKpNonPhs() {
//		return coiKpNonPhs;
//	}
//
//	public void setCoiKpNonPhs(ConflictOfInterestKPNonPHS coiKpNonPhs) {
//		this.coiKpNonPhs = coiKpNonPhs;
//	}
//
//	public ConflictOfInterestKPPHS getCoiKpPhs() {
//		return coiKpPhs;
//	}
//
//	public void setCoiKpPhs(ConflictOfInterestKPPHS coiKpPhs) {
//		this.coiKpPhs = coiKpPhs;
//	}
//
//	public ConflictOfInterestPINonPHS getCoiPiNonphs() {
//		return coiPiNonphs;
//	}
//
//	public void setCoiPiNonphs(ConflictOfInterestPINonPHS coiPiNonphs) {
//		this.coiPiNonphs = coiPiNonphs;
//	}
//
//	public ConflictOfInterestPHS getCoiPhs() {
//		return coiPhs;
//	}
//
//	public void setCoiPhs(ConflictOfInterestPHS coiPhs) {
//		this.coiPhs = coiPhs;
//	}
//
//	public EquipmentForm getEquipmentForm() {
//		return equipmentForm;
//	}
//
//	public void setEquipmentForm(EquipmentForm equipmentForm) {
//		this.equipmentForm = equipmentForm;
//	}
//
	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
//
//	public BudgetFile getBudgetForm() {
//		return budgetForm;
//	}
//
//	public void setBudgetForm(BudgetFile budgetForm) {
//		this.budgetForm = budgetForm;
//	


}
