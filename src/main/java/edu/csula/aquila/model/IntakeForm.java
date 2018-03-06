package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("intake")
public class IntakeForm extends Form implements Serializable {

	private static final long serialVersionUID = 6478586527102789226L;

	// A
	@Column(name = "pi")
	private String principleInvestigator;

	@Column
	private String department;

	@Column
	private String college;

	@Column(name = "project_title")
	private String projectTitle;

	@Column(name = "proposed_funding_amount")
	private int proposedFundingAmount;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	// B
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "intakeForm")
	private List<Personnel> personnel;

	// C
	// Question 1:
	// Do you anticipate any participation stipends as a part
	// of your proposal?
	@Column(name = "anticipate_stipend")
	private boolean anticipateStipend;

	// Question 2:
	// If Yes, please identitify type of Stipend:
	// Study Participant, Student Tuition, Books, Travel, etc.
	// and provide your Participation Stipend eligiblity criteria
	// and selection process below
	@Column(name = "stipend")
	private String stipend;

	// Question 3:
	// Faculty-Student Research/Creative Activities
	@Column(name = "faculty_student_research_creative_activities")
	private boolean facultyStudentResearchCreativeActivities;

	// If Yes, please answer the following two sections:
	// Will this project actively involve students in
	// Research, Scholarly, or Creative Activities (RSCA)
	// including non-paid activities?
	@Column(name = "students_involved")
	private boolean studentsInvolved;

	// How many Graduate Students?
	@Column(name = "number_of_graduate_students")
	private int noOfGradStudents;

	// How many Undergrad Students?
	@Column(name = "number_of_undergrad_students")
	private int noOfUndergradStudents;

	// What Types of RSCA activities will be incorporated?
	@Column(name = "lab_assistance")
	private boolean laboratoryAssistance;

	@Column(name = "data_collection")
	private boolean dataCollection;

	@Column(name = "report_writing")
	private boolean reportWriting;

	@Column(name = "literature_review")
	private boolean literatureReview;

	@Column(name = "coding_or_data_entry")
	private boolean codingOrDataEntry;

	@Column
	private boolean presentations;

	@Column(name = "archival_research")
	private boolean archivalResearch;

	@Column(name = "data_analysis")
	private boolean dataAnalysis;

	@Column(name = "other_activities")
	private boolean otherActivities;

	@ElementCollection
	@CollectionTable(name = "other_activities", joinColumns = @JoinColumn(name = "intake_form_id"))
	@Column(name = "other_activity")
	private List<String> otherActivitiesList;

	// D
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "intakeForm")
	private List<SubgrantsOrSubcontracts> subgrantsOrSubcontracts;

	// E
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "intakeForm")
	private List<ProjectLocations> projectLocations;

	// F
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "intakeForm")
	private List<AdditionalPartiesInvolved> additionalPartiesInvolved;

	// G
	// Agency's F&A (Indirect) Cost Rate
	// IF THIS ATTRIBUTE IS LESS THAN 46
	// INFORM THE USER(PI) TO CONTACT
	// THE ASSOCIATE VICE PRESIDENT FOR RESEARCH
	@Column(name = "agency_cost_rate_percentage")
	private int agencyCostRatePercentage;

	// IF THE NEXT TWO ATTRIBUTES ARE TRUE INFORM USER(PI)
	// TO INFORM DEPT CHAIR, COLLEGE DEAN/ASSOCIATE DEAN
	// ASSOCIATE VICE PRESIDENT FOR RESEARCH

	// Is costsharing required by the agency?
	@Column(name = "agency_cost_sharing")
	private boolean agencyCostSharing;

	// Is costsharing requested by the PI?
	@Column(name = "pi_cost_sharing")
	private boolean piCostSharing;

	// Computers to be requested. How Many?
	@Column(name = "computers_requested")
	private int computersRequested;

	// Equipment to be requested. How Many?
	@ElementCollection // fixed you can follow this example for all other maps
	@MapKeyColumn(name = "equipment_name")
	@Column(name = "amount")
	@CollectionTable(name = "requested_equipment", joinColumns = @JoinColumn(name = "requested_equipment_id"))
	private Map<String, Integer> requestedEquipment;

	// H
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy ="intakeForm")
	private List<Space> space;

	// I
	@ElementCollection
	@MapKeyColumn(name = "name_of_agent")
	@Column(name = "substance_type")
	@CollectionTable(name = "hazardous_substances", joinColumns = @JoinColumn(name = "hazardous_substances_id"))
	private Map<String, String> hazardousSubstances;

	// J
	// Human Subjects?
	// If true, inform User(PI) to consult with IRB Compliance Specialist
	@Column(name = "human_subjects")
	private boolean humanSubjects;

	// Vertebrate Animals?
	// If true, inform User(PI) to consult with IACUC Compliance Specialist
	@Column(name = "vertebrate_animals")
	private boolean vertebrateAnimals;

	// National Center for Education Statistics (NCES)
	// Classification of Instructional Programs (CIP)
	// *see separate "Crosswalk" handout for Science, Engineering,
	// and non-Science, non-Engineering Fields
	@Column(name = "questionnaire")
	private String questionnaireField;
	@Column(name = "cip_category_title")
	private String cipCategoryTitle;
	@Column(name = "cip_classification")
	private String cipClassification;

	// Assistance with proposal development(e.g. conceptualization, writing,
	// editing)
	// If true, inform User(PI) to contact UAS Grants & Contracts
	// and/or the Associate Vice President for Research at least
	// one month in advance
	@Column(name = "assistance_with_proposal_development")
	private boolean assistanceWithProposalDevelopment;

	// Technical Assistance
	@Column(name = "technical_assistance")
	private boolean technicalAssistance;

	// Letters of Support
	// President
	@Column(name = "letter_of_support_president")
	private boolean letterOfSupportPresident;

	// Provost
	@Column(name = "letter_of_support_provost")
	private boolean letterOfSupportProvost;

	// Associate Vice President for Research
	@Column(name = "letter_of_support_assoc_vp_of_research")
	private boolean letterOfSupportAssocVPOfResearch;

	// Duplication of final document package
	@Column(name = "duplication_of_final_document_package")
	private boolean duplicationfFinalDocumentPackage;

	// # of copies needed for your files
	@Column(name = "number_of_copies")
	private int noOfCopies;

	// L
	@Column(name = "project_summary")
	private String projectSummary;

	public IntakeForm() {
	}

	public IntakeForm(String principleInvestigator, String department, String college, String projectTitle,
			int proposedFundingAmount, Date startDate, Date endDate, List<Personnel> personnel,
			boolean anticipateStipend, String stipend, boolean facultyStudentResearchCreativeActivities,
			boolean studentsInvolved, int noOfGradStudents, int noOfUndergradStudents, boolean laboratoryAssistance,
			boolean dataCollection, boolean reportWriting, boolean literatureReview, boolean codingOrDataEntry,
			boolean presentations, boolean archivalResearch, boolean dataAnalysis, boolean otherActivities,
			List<String> otherActivitiesList, List<SubgrantsOrSubcontracts> subgrantsOrSubcontracts,
			List<ProjectLocations> projectLocations, List<AdditionalPartiesInvolved> additionalPartiesInvolved,
			int agencyCostRatePercentage, boolean agencyCostSharing, boolean piCostSharing, int computersRequested,
			Map<String, Integer> requestedEquipment, List<Space> space, Map<String, String> hazardousSubstances,
			boolean humanSubjects, boolean vertebrateAnimals, String questionnaireField, String cipCategoryTitle,
			String cipClassification, boolean assistanceWithProposalDevelopment, boolean technicalAssistance,
			boolean letterOfSupportPresident, boolean letterOfSupportProvost, boolean letterOfSupportAssocVPOfResearch,
			boolean duplicationfFinalDocumentPackage, int noOfCopies, String projectSummary) {
		super();
		this.principleInvestigator = principleInvestigator;
		this.department = department;
		this.college = college;
		this.projectTitle = projectTitle;
		this.proposedFundingAmount = proposedFundingAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.personnel = personnel;
		this.anticipateStipend = anticipateStipend;
		this.stipend = stipend;
		this.facultyStudentResearchCreativeActivities = facultyStudentResearchCreativeActivities;
		this.studentsInvolved = studentsInvolved;
		this.noOfGradStudents = noOfGradStudents;
		this.noOfUndergradStudents = noOfUndergradStudents;
		this.laboratoryAssistance = laboratoryAssistance;
		this.dataCollection = dataCollection;
		this.reportWriting = reportWriting;
		this.literatureReview = literatureReview;
		this.codingOrDataEntry = codingOrDataEntry;
		this.presentations = presentations;
		this.archivalResearch = archivalResearch;
		this.dataAnalysis = dataAnalysis;
		this.otherActivities = otherActivities;
		this.otherActivitiesList = otherActivitiesList;
		this.subgrantsOrSubcontracts = subgrantsOrSubcontracts;
		this.projectLocations = projectLocations;
		this.additionalPartiesInvolved = additionalPartiesInvolved;
		this.agencyCostRatePercentage = agencyCostRatePercentage;
		this.agencyCostSharing = agencyCostSharing;
		this.piCostSharing = piCostSharing;
		this.computersRequested = computersRequested;
		this.requestedEquipment = requestedEquipment;
		this.space = space;
		this.hazardousSubstances = hazardousSubstances;
		this.humanSubjects = humanSubjects;
		this.vertebrateAnimals = vertebrateAnimals;
		this.questionnaireField = questionnaireField;
		this.cipCategoryTitle = cipCategoryTitle;
		this.cipClassification = cipClassification;
		this.assistanceWithProposalDevelopment = assistanceWithProposalDevelopment;
		this.technicalAssistance = technicalAssistance;
		this.letterOfSupportPresident = letterOfSupportPresident;
		this.letterOfSupportProvost = letterOfSupportProvost;
		this.letterOfSupportAssocVPOfResearch = letterOfSupportAssocVPOfResearch;
		this.duplicationfFinalDocumentPackage = duplicationfFinalDocumentPackage;
		this.noOfCopies = noOfCopies;
		this.projectSummary = projectSummary;
	}


	public String getPrincipleInvestigator() {
		return principleInvestigator;
	}

	public void setPrincipleInvestigator(String principleInvestigator) {
		this.principleInvestigator = principleInvestigator;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public int getProposedFundingAmount() {
		return proposedFundingAmount;
	}

	public void setProposedFundingAmount(int proposedFundingAmount) {
		this.proposedFundingAmount = proposedFundingAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Personnel> getPersonnel() {
		return personnel;
	}

	public void setPersonnel(List<Personnel> personnel) {
		this.personnel = personnel;
	}

	public boolean isAnticipateStipend() {
		return anticipateStipend;
	}

	public void setAnticipateStipend(boolean anticipateStipend) {
		this.anticipateStipend = anticipateStipend;
	}

	public String getStipend() {
		return stipend;
	}

	public void setStipend(String stipend) {
		this.stipend = stipend;
	}

	public boolean isFacultyStudentResearchCreativeActivities() {
		return facultyStudentResearchCreativeActivities;
	}

	public void setFacultyStudentResearchCreativeActivities(boolean facultyStudentResearchCreativeActivities) {
		this.facultyStudentResearchCreativeActivities = facultyStudentResearchCreativeActivities;
	}

	public boolean isStudentsInvolved() {
		return studentsInvolved;
	}

	public void setStudentsInvolved(boolean studentsInvolved) {
		this.studentsInvolved = studentsInvolved;
	}

	public int getNoOfGradStudents() {
		return noOfGradStudents;
	}

	public void setNoOfGradStudents(int noOfGradStudents) {
		this.noOfGradStudents = noOfGradStudents;
	}

	public int getNoOfUndergradStudents() {
		return noOfUndergradStudents;
	}

	public void setNoOfUndergradStudents(int noOfUndergradStudents) {
		this.noOfUndergradStudents = noOfUndergradStudents;
	}

	public boolean isLaboratoryAssistance() {
		return laboratoryAssistance;
	}

	public void setLaboratoryAssistance(boolean laboratoryAssistance) {
		this.laboratoryAssistance = laboratoryAssistance;
	}

	public boolean isDataCollection() {
		return dataCollection;
	}

	public void setDataCollection(boolean dataCollection) {
		this.dataCollection = dataCollection;
	}

	public boolean isReportWriting() {
		return reportWriting;
	}

	public void setReportWriting(boolean reportWriting) {
		this.reportWriting = reportWriting;
	}

	public boolean isLiteratureReview() {
		return literatureReview;
	}

	public void setLiteratureReview(boolean literatureReview) {
		this.literatureReview = literatureReview;
	}

	public boolean isCodingOrDataEntry() {
		return codingOrDataEntry;
	}

	public void setCodingOrDataEntry(boolean codingOrDataEntry) {
		this.codingOrDataEntry = codingOrDataEntry;
	}

	public boolean isPresentations() {
		return presentations;
	}

	public void setPresentations(boolean presentations) {
		this.presentations = presentations;
	}

	public boolean isArchivalResearch() {
		return archivalResearch;
	}

	public void setArchivalResearch(boolean archivalResearch) {
		this.archivalResearch = archivalResearch;
	}

	public boolean isDataAnalysis() {
		return dataAnalysis;
	}

	public void setDataAnalysis(boolean dataAnalysis) {
		this.dataAnalysis = dataAnalysis;
	}

	public boolean isOtherActivities() {
		return otherActivities;
	}

	public void setOtherActivities(boolean otherActivities) {
		this.otherActivities = otherActivities;
	}

	public List<String> getOtherActivitiesList() {
		return otherActivitiesList;
	}

	public void setOtherActivitiesList(List<String> otherActivitiesList) {
		this.otherActivitiesList = otherActivitiesList;
	}

	public List<SubgrantsOrSubcontracts> getSubgrantsOrSubcontracts() {
		return subgrantsOrSubcontracts;
	}

	public void setSubgrantsOrSubcontracts(List<SubgrantsOrSubcontracts> subgrantsOrSubcontracts) {
		this.subgrantsOrSubcontracts = subgrantsOrSubcontracts;
	}

	public List<ProjectLocations> getProjectLocations() {
		return projectLocations;
	}

	public void setProjectLocations(List<ProjectLocations> projectLocations) {
		this.projectLocations = projectLocations;
	}

	public List<AdditionalPartiesInvolved> getAdditionalPartiesInvolved() {
		return additionalPartiesInvolved;
	}

	public void setAdditionalPartiesInvolved(List<AdditionalPartiesInvolved> additionalPartiesInvolved) {
		this.additionalPartiesInvolved = additionalPartiesInvolved;
	}

	public int getAgencyCostRatePercentage() {
		return agencyCostRatePercentage;
	}

	public void setAgencyCostRatePercentage(int agencyCostRatePercentage) {
		this.agencyCostRatePercentage = agencyCostRatePercentage;
	}

	public boolean isAgencyCostSharing() {
		return agencyCostSharing;
	}

	public void setAgencyCostSharing(boolean agencyCostSharing) {
		this.agencyCostSharing = agencyCostSharing;
	}

	public boolean isPiCostSharing() {
		return piCostSharing;
	}

	public void setPiCostSharing(boolean piCostSharing) {
		this.piCostSharing = piCostSharing;
	}

	public int getComputersRequested() {
		return computersRequested;
	}

	public void setComputersRequested(int computersRequested) {
		this.computersRequested = computersRequested;
	}

	public Map<String, Integer> getRequestedEquipment() {
		return requestedEquipment;
	}

	public void setRequestedEquipment(Map<String, Integer> requestedEquipment) {
		this.requestedEquipment = requestedEquipment;
	}

	public List<Space> getSpace() {
		return space;
	}

	public void setSpace(List<Space> space) {
		this.space = space;
	}

	public Map<String, String> getHazardousSubstances() {
		return hazardousSubstances;
	}

	public void setHazardousSubstances(Map<String, String> hazardousSubstances) {
		this.hazardousSubstances = hazardousSubstances;
	}

	public boolean isHumanSubjects() {
		return humanSubjects;
	}

	public void setHumanSubjects(boolean humanSubjects) {
		this.humanSubjects = humanSubjects;
	}

	public boolean isVertebrateAnimals() {
		return vertebrateAnimals;
	}

	public void setVertebrateAnimals(boolean vertebrateAnimals) {
		this.vertebrateAnimals = vertebrateAnimals;
	}

	public String getQuestionnaireField() {
		return questionnaireField;
	}

	public void setQuestionnaireField(String questionnaireField) {
		this.questionnaireField = questionnaireField;
	}

	public String getCipCategoryTitle() {
		return cipCategoryTitle;
	}

	public void setCipCategoryTitle(String cipCategoryTitle) {
		this.cipCategoryTitle = cipCategoryTitle;
	}

	public String getCipClassification() {
		return cipClassification;
	}

	public void setCipClassification(String cipClassification) {
		this.cipClassification = cipClassification;
	}

	public boolean isAssistanceWithProposalDevelopment() {
		return assistanceWithProposalDevelopment;
	}

	public void setAssistanceWithProposalDevelopment(boolean assistanceWithProposalDevelopment) {
		this.assistanceWithProposalDevelopment = assistanceWithProposalDevelopment;
	}

	public boolean isTechnicalAssistance() {
		return technicalAssistance;
	}

	public void setTechnicalAssistance(boolean technicalAssistance) {
		this.technicalAssistance = technicalAssistance;
	}

	public boolean isLetterOfSupportPresident() {
		return letterOfSupportPresident;
	}

	public void setLetterOfSupportPresident(boolean letterOfSupportPresident) {
		this.letterOfSupportPresident = letterOfSupportPresident;
	}

	public boolean isLetterOfSupportProvost() {
		return letterOfSupportProvost;
	}

	public void setLetterOfSupportProvost(boolean letterOfSupportProvost) {
		this.letterOfSupportProvost = letterOfSupportProvost;
	}

	public boolean isLetterOfSupportAssocVPOfResearch() {
		return letterOfSupportAssocVPOfResearch;
	}

	public void setLetterOfSupportAssocVPOfResearch(boolean letterOfSupportAssocVPOfResearch) {
		this.letterOfSupportAssocVPOfResearch = letterOfSupportAssocVPOfResearch;
	}

	public boolean isDuplicationfFinalDocumentPackage() {
		return duplicationfFinalDocumentPackage;
	}

	public void setDuplicationfFinalDocumentPackage(boolean duplicationfFinalDocumentPackage) {
		this.duplicationfFinalDocumentPackage = duplicationfFinalDocumentPackage;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}


	// Section B needs an inner class of Personnel for each personnel in the list
	// This class contains the name, employer, position, and time of a person
	// working on the project
	@Entity
	@Table(name = "personnel")
	public static class Personnel implements Serializable {

		private static final long serialVersionUID = 4L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "personnel_id")
		Long Id;

		@Column
		String name;
		@Column
		String employer;
		@Column(name = "position_title_on_grant")
		String positionTitleOnGrant;
		@Column
		int units;
		@Column(name = "percent_of_time_proposed")
		int percentOfTimeProposed;

		// relation
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "intake_form_id", nullable = true)
		IntakeForm intakeForm;

		public Personnel() {
		}

		public Personnel(String name, String employer, String positionTitleOnGrant, int units,
				int percentOfTimeProposed) {
			this.name = name;
			this.employer = employer;
			this.positionTitleOnGrant = positionTitleOnGrant;
			this.units = units;
			this.percentOfTimeProposed = percentOfTimeProposed;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmployer() {
			return employer;
		}

		public void setEmployer(String employer) {
			this.employer = employer;
		}

		public String getPositionTitleOnGrant() {
			return positionTitleOnGrant;
		}

		public void setPositionTitleOnGrant(String positionTitleOnGrant) {
			this.positionTitleOnGrant = positionTitleOnGrant;
		}

		public int getUnits() {
			return units;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public void setUnits(int units) {
			this.units = units;
		}

		public int getPercentOfTimeProposed() {
			return percentOfTimeProposed;
		}

		public void setPercentOfTimeProposed(int percentOfTimeProposed) {
			this.percentOfTimeProposed = percentOfTimeProposed;
		}

		public IntakeForm getIntakeForm() {
			return intakeForm;
		}

		public void setIntakeForm(IntakeForm intakeForm) {
			this.intakeForm = intakeForm;
		}

	}

	// Section D needs an inner class of Subgrants or Subcontracts
	// This class contains the Subgrant/Subcontract institution,
	// address, proposed funding amount, contact person name,
	// contact person email, and contact person phone number
	@Entity
	@Table(name = "subgrants_or_subcontracts")
	public static class SubgrantsOrSubcontracts implements Serializable {

		private static final long serialVersionUID = 5L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "subgrant_or_subcontract_id")
		Long Id;

		@Column
		String institution;

		@Column
		String address;

		@Column(name = "proposed_funding_amount")
		int proposedFundingAmount;

		@Column(name = "contact_person_name")
		String contactPersonName;

		@Column(name = "contact_person_email")
		String contactPersonEmail;

		@Column(name = "contact_person_phone")
		long contactPersonPhone;

		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "intake_form_id")
		IntakeForm intakeForm;

		public SubgrantsOrSubcontracts() {
		}

		public SubgrantsOrSubcontracts(String institution, String address, int proposedFundingAmount,
				String contactPersonName, String contactPersonEmail, long contactPersonPhone) {
			this.institution = institution;
			this.address = address;
			this.proposedFundingAmount = proposedFundingAmount;
			this.contactPersonName = contactPersonName;
			this.contactPersonEmail = contactPersonEmail;
			this.contactPersonPhone = contactPersonPhone;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getInstitution() {
			return institution;
		}

		public void setInstitution(String institution) {
			this.institution = institution;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getProposedFundingAmount() {
			return proposedFundingAmount;
		}

		public void setProposedFundingAmount(int proposedFundingAmount) {
			this.proposedFundingAmount = proposedFundingAmount;
		}

		public String getContactPersonName() {
			return contactPersonName;
		}

		public void setContactPersonName(String contactPersonName) {
			this.contactPersonName = contactPersonName;
		}

		public String getContactPersonEmail() {
			return contactPersonEmail;
		}

		public void setContactPersonEmail(String contactPersonEmail) {
			this.contactPersonEmail = contactPersonEmail;
		}

		public long getContactPersonPhone() {
			return contactPersonPhone;
		}

		public void setContactPersonPhone(long contactPersonPhone) {
			this.contactPersonPhone = contactPersonPhone;
		}

		public IntakeForm getIntakeForm() {
			return intakeForm;
		}

		public void setIntakeForm(IntakeForm intakeForm) {
			this.intakeForm = intakeForm;
		}

	}

	// Section E needs an inner class of Project Locations
	// This class contains the site name, the address,
	// the projected percent of time at site and
	// will have a boolean to ask the user if on-campus space or off-campus rental
	// agreement need to be arranged
	@Entity
	@Table(name = "project_locations")
	public static class ProjectLocations implements Serializable {

		private static final long serialVersionUID = 6L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "project_locations_id")
		Long Id;

		@Column(name = "site_name")
		String siteName;

		@Column(name = "site_address")
		String siteAddress;

		@Column(name = "time_on_site")
		int projectedPercentOfTimeAtSite;

		// Will on-campus space or off-campus rental
		// agreement need to be arranged?
		@Column(name = "agreement_arranged")
		boolean agreementArranged;

		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "intake_form_id", nullable = true)
		IntakeForm intakeForm;

		public ProjectLocations() {
		}

		public ProjectLocations(String siteName, String siteAddress, int projectedPercentOfTimeAtSite,
				boolean agreementArranged) {
			this.siteName = siteName;
			this.siteAddress = siteAddress;
			this.projectedPercentOfTimeAtSite = projectedPercentOfTimeAtSite;
			this.agreementArranged = agreementArranged;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getSiteAddress() {
			return siteAddress;
		}

		public void setSiteAddress(String siteAddress) {
			this.siteAddress = siteAddress;
		}

		public int getProjectedPercentOfTimeAtSite() {
			return projectedPercentOfTimeAtSite;
		}

		public void setProjectedPercentOfTimeAtSite(int projectedPercentOfTimeAtSite) {
			this.projectedPercentOfTimeAtSite = projectedPercentOfTimeAtSite;
		}

		public boolean isAgreementArranged() {
			return agreementArranged;
		}

		public void setAgreementArranged(boolean agreementArranged) {
			this.agreementArranged = agreementArranged;
		}

		public IntakeForm getIntakeForm() {
			return intakeForm;
		}

		public void setIntakeForm(IntakeForm intakeForm) {
			this.intakeForm = intakeForm;
		}

	}

	// Section F needs an inner class of Additional Parties Involved
	// This class contains the party name, the supervisor name
	// and explanation of involvement
	@Entity
	@Table(name = "additional_parties_involved")
	public static class AdditionalPartiesInvolved implements Serializable {

		private static final long serialVersionUID = 7L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "additional_parties_involved_id")
		Long Id;

		@Column(name = "party_name")
		String partyName;

		@Column
		String supervisor;

		@Column(name = "explanation_of_involvement")
		String explanationOfInvolvement;

		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "intake_form_id", nullable = true)
		IntakeForm intakeForm;

		public AdditionalPartiesInvolved() {
		}

		public AdditionalPartiesInvolved(String partyName, String supervisor, String explanationOfInvolvement) {
			this.partyName = partyName;
			this.supervisor = supervisor;
			this.explanationOfInvolvement = explanationOfInvolvement;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getPartyName() {
			return partyName;
		}

		public void setPartyName(String partyName) {
			this.partyName = partyName;
		}

		public String getSupervisor() {
			return supervisor;
		}

		public void setSupervisor(String supervisor) {
			this.supervisor = supervisor;
		}

		public String getExplanationOfInvolvement() {
			return explanationOfInvolvement;
		}

		public void setExplanationOfInvolvement(String explanationOfInvolvement) {
			this.explanationOfInvolvement = explanationOfInvolvement;
		}

		public IntakeForm getIntakeForm() {
			return intakeForm;
		}

		public void setIntakeForm(IntakeForm intakeForm) {
			this.intakeForm = intakeForm;
		}

	}

	// Section H needs an inner class for space
	// This inner class contains the item name
	// the type of use (new space, change of use, or renovation)
	// and the source of funds
	@Entity
	@Table(name = "space")
	public static class Space implements Serializable {

		private static final long serialVersionUID = 8L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "space_id")
		Long Id;

		@Column
		String item;

		@Column(name = "type_of_use")
		String typeOfUse;

		@Column(name = "source_of_funds")
		String sourceOfFunds;

		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "intake_form_id", nullable = true)
		IntakeForm intakeForm;

		public Space() {
		}

		public Space(String item, String typeOfUse, String sourceOfFunds) {
			this.item = item;
			this.typeOfUse = typeOfUse;
			this.sourceOfFunds = sourceOfFunds;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public String getTypeOfUse() {
			return typeOfUse;
		}

		public void setTypeOfUse(String typeOfUse) {
			this.typeOfUse = typeOfUse;
		}

		public String getSourceOfFunds() {
			return sourceOfFunds;
		}

		public void setSourceOfFunds(String sourceOfFunds) {
			this.sourceOfFunds = sourceOfFunds;
		}

		public IntakeForm getIntakeForm() {
			return intakeForm;
		}

		public void setIntakeForm(IntakeForm intakeForm) {
			this.intakeForm = intakeForm;
		}

	}

}
