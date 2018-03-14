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
	private Date dateCreated;
	
	
	@Column(name="status")
	private String status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "intake_form_id")
	IntakeForm intakeForm;
	
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "timeline_id")
	Timeline timeline;

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "approval_form_id")
	ApprovalForm approvalForm;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "coi_kp_non_phs_id")
	ConflictOfInterestKPNonPHS coiKpNonPhs;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "coi_kp_phs_id")
	ConflictOfInterestKPPHS coiKpPhs;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "coi_phs_id")
	ConflictOfInterestPHS coiPhs;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "coi_pi_non_phs_id")
	ConflictOfInterestPINonPHS coiPiNonPhs;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "economic_interest_pi_id")
	EconomicInterestPI economicInterestPi;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "equipment_id")
	EquipmentForm equipmentForm;
	


	public Proposal(){}
	
	public Proposal(String proposalName, User user, String status, Date dateCreated) {
		this.proposalName = proposalName;
		this.status = status;
		this.user = user;
		this.dateCreated = dateCreated;
	}

	public Proposal(String proposalName, Date dateCreated, String status, User user, IntakeForm intakeForm,
			Timeline timeline, ApprovalForm approvalForm, ConflictOfInterestKPNonPHS coiKpNonPhs,
			ConflictOfInterestKPPHS coiKpPhs, ConflictOfInterestPHS coiPhs, ConflictOfInterestPINonPHS coiPiNonPhs,
			EconomicInterestPI economicInterestPi, EquipmentForm equipmentForm) {
		super();
		this.proposalName = proposalName;
		this.dateCreated = dateCreated;
		this.status = status;
		this.user = user;
		this.intakeForm = intakeForm;
		this.timeline = timeline;
		this.approvalForm = approvalForm;
		this.coiKpNonPhs = coiKpNonPhs;
		this.coiKpPhs = coiKpPhs;
		this.coiPhs = coiPhs;
		this.coiPiNonPhs = coiPiNonPhs;
		this.economicInterestPi = economicInterestPi;
		this.equipmentForm = equipmentForm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IntakeForm getIntakeForm() {
		return intakeForm;
	}

	public void setIntakeForm(IntakeForm intakeForm) {
		this.intakeForm = intakeForm;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public ApprovalForm getApprovalForm() {
		return approvalForm;
	}

	public void setApprovalForm(ApprovalForm approvalForm) {
		this.approvalForm = approvalForm;
	}

	public ConflictOfInterestKPNonPHS getCoiKpNonPhs() {
		return coiKpNonPhs;
	}

	public void setCoiKpNonPhs(ConflictOfInterestKPNonPHS coiKpNonPhs) {
		this.coiKpNonPhs = coiKpNonPhs;
	}

	public ConflictOfInterestKPPHS getCoiKpPhs() {
		return coiKpPhs;
	}

	public void setCoiKpPhs(ConflictOfInterestKPPHS coiKpPhs) {
		this.coiKpPhs = coiKpPhs;
	}

	public ConflictOfInterestPHS getCoiPhs() {
		return coiPhs;
	}

	public void setCoiPhs(ConflictOfInterestPHS coiPhs) {
		this.coiPhs = coiPhs;
	}

	public ConflictOfInterestPINonPHS getCoiPiNonPhs() {
		return coiPiNonPhs;
	}

	public void setCoiPiNonPhs(ConflictOfInterestPINonPHS coiPiNonPhs) {
		this.coiPiNonPhs = coiPiNonPhs;
	}

	public EconomicInterestPI getEconomicInterestPi() {
		return economicInterestPi;
	}

	public void setEconomicInterestPi(EconomicInterestPI economicInterestPi) {
		this.economicInterestPi = economicInterestPi;
	}

	public EquipmentForm getEquipmentForm() {
		return equipmentForm;
	}

	public void setEquipmentForm(EquipmentForm equipmentForm) {
		this.equipmentForm = equipmentForm;
	}
	
	


}
