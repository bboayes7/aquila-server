package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
    public enum Status {
    	DRAFT, CANCELLED, MEETING, POSTMEETING, FINAL
    }

    @Enumerated(EnumType.STRING)
    private Status status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "intake_form_id")
	IntakeForm intakeForm;


	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "timeline_id")
	Timeline timeline;

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "approval_form_id")
	ApprovalForm approvalForm;
	
	// has to be one to many
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "coi_id")
	ConflictOfInterestForm conflictOfInterestForm;
	// has to bw one to many
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "economic_interest_pi_id")
	EconomicInterestPI economicInterestPi;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "equipment_id")
	EquipmentForm equipmentForm;
	


	public Proposal(){}
	
	public Proposal(String proposalName, User user, Date dateCreated) {
		this.proposalName = proposalName;
		this.user = user;
		this.dateCreated = dateCreated;
	}
	
	

	public Proposal(Long id, String proposalName, Date dateCreated, Status status, User user, IntakeForm intakeForm,
			Timeline timeline, ApprovalForm approvalForm, ConflictOfInterestForm conflictOfInterestForm,
			EconomicInterestPI economicInterestPi, EquipmentForm equipmentForm) {
		super();
		this.id = id;
		this.proposalName = proposalName;
		this.dateCreated = dateCreated;
		this.status = status;
		this.user = user;
		this.intakeForm = intakeForm;
		this.timeline = timeline;
		this.approvalForm = approvalForm;
		this.conflictOfInterestForm = conflictOfInterestForm;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public ConflictOfInterestForm getConflictOfInterestForm() {
		return conflictOfInterestForm;
	}

	public void setConflictOfInterestForm(ConflictOfInterestForm conflictOfInterestForm) {
		this.conflictOfInterestForm = conflictOfInterestForm;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
