package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("approval")
public class ApprovalForm extends Form implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
//	@Id
//	@GeneratedValue
//	@Column(name="approval_form_id")
//	private Long Id;

	private int progress;
	
	// 1-4
	@Column(name = "project_title")
	private String projectTitle;

	@Column(name = "pi_name")
	private User pIName;// ?

	@Column(name = "email")
	private String email;

	@Column(name = "college")
	private College college;// ? annotations for college and department ?

	@Column(name = "department")
	private Department department;// ?

	@Column(name = "deadline_date")
	private Date deadlineDate;

	@Column(name = "prepared_date")
	private Date preparedDate;

	@Column(name = "prepared_by")
	private String preparedBy;// ?


	// 5
	// need help
	// 6
	@Column(name = "additional_space")
	private boolean additionalSpace;

	@Column(name = "vertebrate_animal")
	private boolean vertebrateAnimal;

	@Column(name = "biological_hazards")
	private boolean biologicalHazards;

	@Column(name ="conflict_of_interest_statement")
	private boolean conflictOfInterestStatement;

	@Column(name = "human_subjects")
	private boolean humanSubjects;

	@Column(name = "radiological_hazards")
	private boolean radiologicalHazards;

	@Column(name = "computer_equipment")
	private boolean computerEquipment;

	@Column(name = "recombinant_dna")
	private boolean recombinantDNA;

	@Column(name = "additional_space_approved")
	private String additionalSpaceApproved;

	@Column(name = "vertebrate_animal_approved")
	private String vertebrateAnimalApproved;

	@Column(name = "biological_hazards_approved")
	private String biologicalHazardsApproved;

	@Column(name = "conflict_of_interest_statement_approved")
	private String conflictOfInterestStatementApproved;

	@Column(name = "human_subjects_approved")
	private String humanSubjectsApproved;

	@Column(name = "radiological_hazards_approved")
	private String radiologicalHazardsApproved;

	@Column(name = "computer_equipment_approved")
	private String computerEquipmentApproved;

	@Column(name = "recombinant_dna_approved")
	private String recombinantDNAApproved;



	// 7

	@Column(name = "pi_signature")
	private Signature piSignature;

	@Column(name = "pi_signature_date")
	private Date piSignatureDate;

	@Column(name = "chair_signature")
	private Signature chairSignature;

	@Column(name = "chair_signature_date")
	private Date chairSignatureDate;

	@Column(name = "college_dean_signature")
	private Signature collegeDeanSignature;

	@Column(name = "college_signature_date")
	private Date collegeSignatureDate;

	// UCS

	@Column(name = "university_cost_sharing")
	private boolean universityCostSharing;

	@Column(name = "provost_vp_academic_affairs")
	private Signature provostAndVPForAcademicAffairs;

	@Column(name = "provost_vp_academic_affairs_signature")
	private Date provostAndVPForAcademicAffairsSignature;

	@Column(name = "college_dean_designee")
	private Signature deanOfCollegeOrDesignee;

	@Column(name = "college_dean_designee_signature")
	private Date deanOfCollegeOrDesigneeSignature;


	// 8

	@Column(name = "director_signature")
	private Signature directorSignature;

	@Column(name = "director_signature_date")
	private Date directorSignatureDate;

	@Column(name = "avp_signature")
	private Signature avpSignature;

	@Column(name = "avp_signature_date")
	private Date avpSignatureDate;

	@Column(name = "uas_exec_director_signature")
	private Signature UASExecDirectorSignature;

	@Column(name = "uas_exec_director_signature_date")
	private Date UASExecDirectorSignatureDate;

	@Column(name = "chief_financial_officer_signature")
	private Signature chiefFinancialOfficerSignature;

	@Column(name = "chief_financial_officer_signature_date")
	private Date chiefFinancialOfficerSignatureDate;

	// 9

	@Column(name = "cost_sharing_required")
	private boolean costSharingRequired;

	@OneToMany(mappedBy="form")
	@Column(name = "cost_sharing_colleges")
	private List<ApprovalCollege> costSharingColleges;

	@Column(name = "total_of_college")
	private Integer totalOfColleges;

	@Column(name = "csula_cost_sharing")
	private Integer calStateLACostSharing;

	@Column(name = "unrecovered_fa_cost_sharing")
	private Integer unRecoveredFACostSharing;

	@Column(name = "unrecovered_famtdc")
	private double unRecoveredFAMTDC;

	@Column(name = "third_party_cost_share")
	private Integer thirdPartyCostShare;

	@Column(name = "total_csula_cost_sharing")
	private Integer totalCalStateLACostSharing;

	@Column(name = "total_proposal_cost_sharing")
	private Integer totalProposalCostSharing;

	@ElementCollection
	@Column(name = "internal_notes")
	private List<String> internalNotes;

	// 10
	@Column(name = "proposal_personnel_signature")
	private Signature proposalPersonnelSignature;

	@Column(name = "dep_chair_signature")
	private Signature departmentChairSignature;

	@Column(name = "dep_chair_signature_date")
	private Date departmentChairSignatureDate;

	@Column(name = "dean_signature")
	private Signature deanSignature;

	@Column(name = "dean_signature_date")
	private Date deanSignatureDate;

	// 11


	@Column(name = "uas_project_id")
	private String uasProjectId;

	@Column(name = "proposal_code")
	private float proposalCode;

	@ElementCollection
	@Column(name = "co_pis")
	private List<String> coPis;

	@Column(name = "proposal_type")
	private String typeOfProposal;

	@Column(name = "grant_contract_type")
	private String typeOfGrantContracts;

	@Column(name = "project_purpose")
	private String purposeOfProject;

	@Column(name = "cfda_number")
	private int cfdaNumber;

	public ApprovalForm(){}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public User getpIName() {
		return pIName;
	}

	public void setpIName(User pIName) {
		this.pIName = pIName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Date getPreparedDate() {
		return preparedDate;
	}

	public void setPreparedDate(Date preparedDate) {
		this.preparedDate = preparedDate;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public boolean isAdditionalSpace() {
		return additionalSpace;
	}

	public void setAdditionalSpace(boolean additionalSpace) {
		this.additionalSpace = additionalSpace;
	}

	public boolean isVertebrateAnimal() {
		return vertebrateAnimal;
	}

	public void setVertebrateAnimal(boolean vertebrateAnimal) {
		this.vertebrateAnimal = vertebrateAnimal;
	}

	public boolean isBiologicalHazards() {
		return biologicalHazards;
	}

	public void setBiologicalHazards(boolean biologicalHazards) {
		this.biologicalHazards = biologicalHazards;
	}

	public boolean isConflictOfInterestStatement() {
		return conflictOfInterestStatement;
	}

	public void setConflictOfInterestStatement(boolean conflictOfInterestStatement) {
		this.conflictOfInterestStatement = conflictOfInterestStatement;
	}

	public boolean isHumanSubjects() {
		return humanSubjects;
	}

	public void setHumanSubjects(boolean humanSubjects) {
		this.humanSubjects = humanSubjects;
	}

	public boolean isRadiologicalHazards() {
		return radiologicalHazards;
	}

	public void setRadiologicalHazards(boolean radiologicalHazards) {
		this.radiologicalHazards = radiologicalHazards;
	}

	public boolean isComputerEquipment() {
		return computerEquipment;
	}

	public void setComputerEquipment(boolean computerEquipment) {
		this.computerEquipment = computerEquipment;
	}

	public boolean isRecombinantDNA() {
		return recombinantDNA;
	}

	public void setRecombinantDNA(boolean recombinantDNA) {
		this.recombinantDNA = recombinantDNA;
	}

	public String getAdditionalSpaceApproved() {
		return additionalSpaceApproved;
	}

	public void setAdditionalSpaceApproved(String additionalSpaceApproved) {
		this.additionalSpaceApproved = additionalSpaceApproved;
	}

	public String getVertebrateAnimalApproved() {
		return vertebrateAnimalApproved;
	}

	public void setVertebrateAnimalApproved(String vertebrateAnimalApproved) {
		this.vertebrateAnimalApproved = vertebrateAnimalApproved;
	}

	public String getBiologicalHazardsApproved() {
		return biologicalHazardsApproved;
	}

	public void setBiologicalHazardsApproved(String biologicalHazardsApproved) {
		this.biologicalHazardsApproved = biologicalHazardsApproved;
	}

	public String getConflictOfInterestStatementApproved() {
		return conflictOfInterestStatementApproved;
	}

	public void setConflictOfInterestStatementApproved(String conflictOfInterestStatementApproved) {
		this.conflictOfInterestStatementApproved = conflictOfInterestStatementApproved;
	}

	public String getHumanSubjectsApproved() {
		return humanSubjectsApproved;
	}

	public void setHumanSubjectsApproved(String humanSubjectsApproved) {
		this.humanSubjectsApproved = humanSubjectsApproved;
	}

	public String getRadiologicalHazardsApproved() {
		return radiologicalHazardsApproved;
	}

	public void setRadiologicalHazardsApproved(String radiologicalHazardsApproved) {
		this.radiologicalHazardsApproved = radiologicalHazardsApproved;
	}

	public String getComputerEquipmentApproved() {
		return computerEquipmentApproved;
	}

	public void setComputerEquipmentApproved(String computerEquipmentApproved) {
		this.computerEquipmentApproved = computerEquipmentApproved;
	}

	public String getRecombinantDNAApproved() {
		return recombinantDNAApproved;
	}

	public void setRecombinantDNAApproved(String recombinantDNAApproved) {
		this.recombinantDNAApproved = recombinantDNAApproved;
	}

	public Signature getPiSignature() {
		return piSignature;
	}

	public void setPiSignature(Signature piSignature) {
		this.piSignature = piSignature;
	}

	public Date getPiSignatureDate() {
		return piSignatureDate;
	}

	public void setPiSignatureDate(Date piSignatureDate) {
		this.piSignatureDate = piSignatureDate;
	}

	public Signature getChairSignature() {
		return chairSignature;
	}

	public void setChairSignature(Signature chairSignature) {
		this.chairSignature = chairSignature;
	}

	public Date getChairSignatureDate() {
		return chairSignatureDate;
	}

	public void setChairSignatureDate(Date chairSignatureDate) {
		this.chairSignatureDate = chairSignatureDate;
	}

	public Signature getCollegeDeanSignature() {
		return collegeDeanSignature;
	}

	public void setCollegeDeanSignature(Signature collegeDeanSignature) {
		this.collegeDeanSignature = collegeDeanSignature;
	}

	public Date getCollegeSignatureDate() {
		return collegeSignatureDate;
	}

	public void setCollegeSignatureDate(Date collegeSignatureDate) {
		this.collegeSignatureDate = collegeSignatureDate;
	}

	public boolean isUniversityCostSharing() {
		return universityCostSharing;
	}

	public void setUniversityCostSharing(boolean universityCostSharing) {
		this.universityCostSharing = universityCostSharing;
	}

	public Signature getProvostAndVPForAcademicAffairs() {
		return provostAndVPForAcademicAffairs;
	}

	public void setProvostAndVPForAcademicAffairs(Signature provostAndVPForAcademicAffairs) {
		this.provostAndVPForAcademicAffairs = provostAndVPForAcademicAffairs;
	}

	public Date getProvostAndVPForAcademicAffairsSignature() {
		return provostAndVPForAcademicAffairsSignature;
	}

	public void setProvostAndVPForAcademicAffairsSignature(Date provostAndVPForAcademicAffairsSignature) {
		this.provostAndVPForAcademicAffairsSignature = provostAndVPForAcademicAffairsSignature;
	}

	public Signature getDeanOfCollegeOrDesignee() {
		return deanOfCollegeOrDesignee;
	}

	public void setDeanOfCollegeOrDesignee(Signature deanOfCollegeOrDesignee) {
		this.deanOfCollegeOrDesignee = deanOfCollegeOrDesignee;
	}

	public Date getDeanOfCollegeOrDesigneeSignature() {
		return deanOfCollegeOrDesigneeSignature;
	}

	public void setDeanOfCollegeOrDesigneeSignature(Date deanOfCollegeOrDesigneeSignature) {
		this.deanOfCollegeOrDesigneeSignature = deanOfCollegeOrDesigneeSignature;
	}

	public Signature getDirectorSignature() {
		return directorSignature;
	}

	public void setDirectorSignature(Signature directorSignature) {
		this.directorSignature = directorSignature;
	}

	public Date getDirectorSignatureDate() {
		return directorSignatureDate;
	}

	public void setDirectorSignatureDate(Date directorSignatureDate) {
		this.directorSignatureDate = directorSignatureDate;
	}

	public Signature getAvpSignature() {
		return avpSignature;
	}

	public void setAvpSignature(Signature avpSignature) {
		this.avpSignature = avpSignature;
	}

	public Date getAvpSignatureDate() {
		return avpSignatureDate;
	}

	public void setAvpSignatureDate(Date avpSignatureDate) {
		this.avpSignatureDate = avpSignatureDate;
	}

	public Signature getUASExecDirectorSignature() {
		return UASExecDirectorSignature;
	}

	public void setUASExecDirectorSignature(Signature uASExecDirectorSignature) {
		UASExecDirectorSignature = uASExecDirectorSignature;
	}

	public Date getUASExecDirectorSignatureDate() {
		return UASExecDirectorSignatureDate;
	}

	public void setUASExecDirectorSignatureDate(Date uASExecDirectorSignatureDate) {
		UASExecDirectorSignatureDate = uASExecDirectorSignatureDate;
	}

	public Signature getChiefFinancialOfficerSignature() {
		return chiefFinancialOfficerSignature;
	}

	public void setChiefFinancialOfficerSignature(Signature chiefFinancialOfficerSignature) {
		this.chiefFinancialOfficerSignature = chiefFinancialOfficerSignature;
	}

	public Date getChiefFinancialOfficerSignatureDate() {
		return chiefFinancialOfficerSignatureDate;
	}

	public void setChiefFinancialOfficerSignatureDate(Date chiefFinancialOfficerSignatureDate) {
		this.chiefFinancialOfficerSignatureDate = chiefFinancialOfficerSignatureDate;
	}

	public boolean isCostSharingRequired() {
		return costSharingRequired;
	}

	public void setCostSharingRequired(boolean costSharingRequired) {
		this.costSharingRequired = costSharingRequired;
	}

	public Integer getTotalOfColleges() {
		return totalOfColleges;
	}

	public void setTotalOfColleges(Integer totalOfColleges) {
		this.totalOfColleges = totalOfColleges;
	}

	public Integer getCalStateLACostSharing() {
		return calStateLACostSharing;
	}

	public void setCalStateLACostSharing(Integer calStateLACostSharing) {
		this.calStateLACostSharing = calStateLACostSharing;
	}

	public Integer getUnRecoveredFACostSharing() {
		return unRecoveredFACostSharing;
	}

	public void setUnRecoveredFACostSharing(Integer unRecoveredFACostSharing) {
		this.unRecoveredFACostSharing = unRecoveredFACostSharing;
	}

	public double getUnRecoveredFAMTDC() {
		return unRecoveredFAMTDC;
	}

	public void setUnRecoveredFAMTDC(double unRecoveredFAMTDC) {
		this.unRecoveredFAMTDC = unRecoveredFAMTDC;
	}

	public Integer getThirdPartyCostShare() {
		return thirdPartyCostShare;
	}

	public void setThirdPartyCostShare(Integer thirdPartyCostShare) {
		this.thirdPartyCostShare = thirdPartyCostShare;
	}

	public Integer getTotalCalStateLACostSharing() {
		return totalCalStateLACostSharing;
	}

	public void setTotalCalStateLACostSharing(Integer totalCalStateLACostSharing) {
		this.totalCalStateLACostSharing = totalCalStateLACostSharing;
	}

	public Integer getTotalProposalCostSharing() {
		return totalProposalCostSharing;
	}

	public void setTotalProposalCostSharing(Integer totalProposalCostSharing) {
		this.totalProposalCostSharing = totalProposalCostSharing;
	}

	public List<String> getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(List<String> internalNotes) {
		this.internalNotes = internalNotes;
	}

	public Signature getProposalPersonnelSignature() {
		return proposalPersonnelSignature;
	}

	public void setProposalPersonnelSignature(Signature proposalPersonnelSignature) {
		this.proposalPersonnelSignature = proposalPersonnelSignature;
	}

	public Signature getDepartmentChairSignature() {
		return departmentChairSignature;
	}

	public void setDepartmentChairSignature(Signature departmentChairSignature) {
		this.departmentChairSignature = departmentChairSignature;
	}

	public Date getDepartmentChairSignatureDate() {
		return departmentChairSignatureDate;
	}

	public void setDepartmentChairSignatureDate(Date departmentChairSignatureDate) {
		this.departmentChairSignatureDate = departmentChairSignatureDate;
	}

	public Signature getDeanSignature() {
		return deanSignature;
	}

	public void setDeanSignature(Signature deanSignature) {
		this.deanSignature = deanSignature;
	}

	public Date getDeanSignatureDate() {
		return deanSignatureDate;
	}

	public void setDeanSignatureDate(Date deanSignatureDate) {
		this.deanSignatureDate = deanSignatureDate;
	}

	public String getUasProjectId() {
		return uasProjectId;
	}

	public void setUasProjectId(String uasProjectId) {
		this.uasProjectId = uasProjectId;
	}

	public float getProposalCode() {
		return proposalCode;
	}

	public void setProposalCode(float proposalCode) {
		this.proposalCode = proposalCode;
	}

	public List<String> getCoPis() {
		return coPis;
	}

	public void setCoPis(List<String> coPis) {
		this.coPis = coPis;
	}

	public String getTypeOfProposal() {
		return typeOfProposal;
	}

	public void setTypeOfProposal(String typeOfProposal) {
		this.typeOfProposal = typeOfProposal;
	}

	public String getTypeOfGrantContracts() {
		return typeOfGrantContracts;
	}

	public void setTypeOfGrantContracts(String typeOfGrantContracts) {
		this.typeOfGrantContracts = typeOfGrantContracts;
	}

	public String getPurposeOfProject() {
		return purposeOfProject;
	}

	public void setPurposeOfProject(String purposeOfProject) {
		this.purposeOfProject = purposeOfProject;
	}

	public int getCfdaNumber() {
		return cfdaNumber;
	}

	public void setCfdaNumber(int cfdaNumber) {
		this.cfdaNumber = cfdaNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ApprovalCollege> getCostSharingColleges() {
		return costSharingColleges;
	}

	public void setCostSharingColleges(List<ApprovalCollege> costSharingColleges) {
		this.costSharingColleges = costSharingColleges;
	}


	@Entity
	@Table(name="approval_colleges")
	public class ApprovalCollege {
	
		@ManyToOne
		ApprovalForm form;
		
		@Id
		@GeneratedValue
		@Column(name="approval_college_id")
		private Long id;
	
		@Column(name="personnel_cost")
		Integer personnelCost;
		
		@Column(name="fringe_cost")
		Integer fringeCost;
		
		@Column(name="meeting_expenses")
		Integer meetingExpenses;
		
		@Column(name="total_cost")
		Integer totalCost;
	}


}
