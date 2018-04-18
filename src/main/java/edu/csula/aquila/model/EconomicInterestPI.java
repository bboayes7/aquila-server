package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;


import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("economic_interest_pi")
public class EconomicInterestPI extends Form implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int progress;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_initial")
	private String middleInitial;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private Department department;
	
	@Column(name="mail_code")
	private String mailCode; //int or string?
	
	private String email;
	
	@Column(name = "project_title")
	private String projectTitle;
	
	//Section 1 - Funding Entity
	
	@Column(name ="entity_name")
	private String entityName;
	
	@Column(name = "entity_address")
	private String entityAddress;
	
	@Column(name = "principal_business")
	private String principalBusiness; //??
	
	@Column(name = "funding_amount")
	private Integer fundingAmount;
	
	@Column(name = "actual_amount")
	private Boolean actualAmount;
	
	//Section 2 - Type of Statement
	
	@Column(name = "statement_type")
	private String statementType;
	
	@Column(name = "fund_date")
	private Date fundDate;
	
	//Section 3 - Filer info
	//a
	@Column(name = "position_held")
	private Boolean positionHeld;

	@Column(name = "position_title")
	private String positionTitle;
	
	//b
	@Column(name = "investment_greater_than")
	private Boolean investmentGreaterThan;
	
	@Column(name = "invest_amount")
	private Integer investAmount;
	
	//c
	@Column(name = "received_income")
	private Boolean receivedIncome;
	
	@Column(name = "received_amount")
	private Integer receivedAmount;
	
	@Column(name = "received_through_spouse")
	private Boolean receivedThroughSpouse;
	
	//d
	@Column(name = "received_through_entity")
	private Boolean receivedThroughEntity;
	
	@Column(name = "loan_amount")
	private Integer loanAmount;
	
	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "loan_interest")
	private Double loanInterest;
	
	@Column(name = "loan_paid_off")
	private Boolean loanPaidOff;
	
	//e
	@Column(name = "gifts_received")
	private Boolean giftsReceived;
	
	@Column(name = "gifts_description")
	private String giftsDescription;
	
	@Column(name = "gifts_value")
	private Integer giftsValue;
	
	@Column(name = "gifts_received_date")
	private Date giftsReceivedDate;
	
	//f
	@Column(name = "travel_through_entity")
	private Boolean travelThroughEntity;
	
	@Column(name = "travel_payment_type")
	private String travelPaymentType;
	
	@Column(name = "travel_payment_amount")
	private Integer atravelPaymentAmount;
	
	@ElementCollection
	@CollectionTable(name = "travel_payment_dates", joinColumns=@JoinColumn(name="tpd_id"))
	@Column(name = "dates")
	private List<Date> travelPaymentDates;
	
	@Column(name = "travel_description")
	private String travelDescription;
	
	//Section 4 - Verification
	 
	@Column(name = "date_signed")
	private Date dateSigned;
	
	private String signature;
	
	
	public EconomicInterestPI() {}

	public EconomicInterestPI(int progress, String lastName, String firstName, String middleInitial, String phoneNumber,
			Department department, String mailCode, String email, String projectTitle, String entityName,
			String entityAddress, String principalBusiness, Integer fundingAmount, Boolean actualAmount,
			String statementType, Date fundDate, Boolean positionHeld, String positionTitle,
			Boolean investmentGreaterThan, Integer investAmount, Boolean receivedIncome, Integer receivedAmount,
			Boolean receivedThroughSpouse, Boolean receivedThroughEntity, Integer loanAmount, String loanType,
			Double loanInterest, Boolean loanPaidOff, Boolean giftsReceived, String giftsDescription,
			Integer giftsValue, Date giftsReceivedDate, Boolean travelThroughEntity, String travelPaymentType,
			Integer atravelPaymentAmount, List<Date> travelPaymentDates, String travelDescription, Date dateSigned,
			String signature) {
		super();
		this.progress = progress;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.mailCode = mailCode;
		this.email = email;
		this.projectTitle = projectTitle;
		this.entityName = entityName;
		this.entityAddress = entityAddress;
		this.principalBusiness = principalBusiness;
		this.fundingAmount = fundingAmount;
		this.actualAmount = actualAmount;
		this.statementType = statementType;
		this.fundDate = fundDate;
		this.positionHeld = positionHeld;
		this.positionTitle = positionTitle;
		this.investmentGreaterThan = investmentGreaterThan;
		this.investAmount = investAmount;
		this.receivedIncome = receivedIncome;
		this.receivedAmount = receivedAmount;
		this.receivedThroughSpouse = receivedThroughSpouse;
		this.receivedThroughEntity = receivedThroughEntity;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.loanInterest = loanInterest;
		this.loanPaidOff = loanPaidOff;
		this.giftsReceived = giftsReceived;
		this.giftsDescription = giftsDescription;
		this.giftsValue = giftsValue;
		this.giftsReceivedDate = giftsReceivedDate;
		this.travelThroughEntity = travelThroughEntity;
		this.travelPaymentType = travelPaymentType;
		this.atravelPaymentAmount = atravelPaymentAmount;
		this.travelPaymentDates = travelPaymentDates;
		this.travelDescription = travelDescription;
		this.dateSigned = dateSigned;
		this.signature = signature;
	}


	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityAddress() {
		return entityAddress;
	}

	public void setEntityAddress(String entityAddress) {
		this.entityAddress = entityAddress;
	}

	public String getPrincipalBusiness() {
		return principalBusiness;
	}

	public void setPrincipalBusiness(String principalBusiness) {
		this.principalBusiness = principalBusiness;
	}

	public Integer getFundingAmount() {
		return fundingAmount;
	}

	public void setFundingAmount(Integer fundingAmount) {
		this.fundingAmount = fundingAmount;
	}

	public Boolean getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Boolean actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public Date getFundDate() {
		return fundDate;
	}

	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}

	public Boolean getPositionHeld() {
		return positionHeld;
	}

	public void setPositionHeld(Boolean positionHeld) {
		this.positionHeld = positionHeld;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public Boolean getInvestmentGreaterThan() {
		return investmentGreaterThan;
	}

	public void setInvestmentGreaterThan(Boolean investmentGreaterThan) {
		this.investmentGreaterThan = investmentGreaterThan;
	}

	public Integer getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Integer investAmount) {
		this.investAmount = investAmount;
	}

	public Boolean getReceivedIncome() {
		return receivedIncome;
	}

	public void setReceivedIncome(Boolean receivedIncome) {
		this.receivedIncome = receivedIncome;
	}

	public Integer getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(Integer receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Boolean getReceivedThroughSpouse() {
		return receivedThroughSpouse;
	}

	public void setReceivedThroughSpouse(Boolean receivedThroughSpouse) {
		this.receivedThroughSpouse = receivedThroughSpouse;
	}

	public Boolean getReceivedThroughEntity() {
		return receivedThroughEntity;
	}

	public void setReceivedThroughEntity(Boolean receivedThroughEntity) {
		this.receivedThroughEntity = receivedThroughEntity;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(Double loanInterest) {
		this.loanInterest = loanInterest;
	}

	public Boolean getLoanPaidOff() {
		return loanPaidOff;
	}

	public void setLoanPaidOff(Boolean loanPaidOff) {
		this.loanPaidOff = loanPaidOff;
	}

	public Boolean getGiftsReceived() {
		return giftsReceived;
	}

	public void setGiftsReceived(Boolean giftsReceived) {
		this.giftsReceived = giftsReceived;
	}

	public String getGiftsDescription() {
		return giftsDescription;
	}

	public void setGiftsDescription(String giftsDescription) {
		this.giftsDescription = giftsDescription;
	}

	public Integer getGiftsValue() {
		return giftsValue;
	}

	public void setGiftsValue(Integer giftsValue) {
		this.giftsValue = giftsValue;
	}

	public Date getGiftsReceivedDate() {
		return giftsReceivedDate;
	}

	public void setGiftsReceivedDate(Date giftsReceivedDate) {
		this.giftsReceivedDate = giftsReceivedDate;
	}

	public Boolean getTravelThroughEntity() {
		return travelThroughEntity;
	}

	public void setTravelThroughEntity(Boolean travelThroughEntity) {
		this.travelThroughEntity = travelThroughEntity;
	}

	public String getTravelPaymentType() {
		return travelPaymentType;
	}

	public void setTravelPaymentType(String travelPaymentType) {
		this.travelPaymentType = travelPaymentType;
	}

	public Integer getAtravelPaymentAmount() {
		return atravelPaymentAmount;
	}

	public void setAtravelPaymentAmount(Integer atravelPaymentAmount) {
		this.atravelPaymentAmount = atravelPaymentAmount;
	}

	public List<Date> getTravelPaymentDates() {
		return travelPaymentDates;
	}

	public void setTravelPaymentDates(List<Date> travelPaymentDates) {
		this.travelPaymentDates = travelPaymentDates;
	}

	public String getTravelDescription() {
		return travelDescription;
	}

	public void setTravelDescription(String travelDescription) {
		this.travelDescription = travelDescription;
	}

	public Date getDateSigned() {
		return dateSigned;
	}

	public void setDateSigned(Date dateSigned) {
		this.dateSigned = dateSigned;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

		
	
	
}
