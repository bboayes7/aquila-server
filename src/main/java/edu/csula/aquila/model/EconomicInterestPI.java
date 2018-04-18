package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("economic_interest_pi")
public class EconomicInterestPI extends Form implements Serializable {

	private static final long serialVersionUID = 1L;

	private int progress;
	// general info
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_initial")
	private String middleInitial;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	// what is this
	private Department department;
	
	@Column(name="mail_code")
	private String mailCode;
	
	@Column
	private String email;
	
	@Column(name = "project_title")
	private String projectTitle;
	
	@Column
	private String campus;
	//Section 1 - Funding Entity
	
	@Column(name ="entity_name")
	private String entityName;
	
	@Column(name = "entity_address")
	private String entityAddress;
	
	@Column(name = "principal_business")
	private String principalBusiness; //??
	
	@Column(name = "funding_amount")
	private Integer fundingAmount;
	
	@Column(name = "estimate_amount")
	private Boolean estimateAmount;
	
	@Column(name = "actual_amount")
	private Boolean actualAmount;
	
	//Section 2 - Type of Statement
	@Column(name="initial_funding")
	private boolean initialFunding;
	
	@Column(name = "fund_date")
	private Date fundDate;
	
	@Column(name = "interim_fund")
	private boolean interimFund;
	
	@Column(name="interim_date")
	private Date interimDate;
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
	
	@Column(name="date_disposed")
	private Date dateDisposed;
	
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
	
	@Column(name = "loan_secured")
	private boolean loanSecured;
	
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
	
	@Column(name = "travel_payment_type_gift")
	private boolean travelPaymentTypeGift;
	
	@Column(name = "travel_payment_type_income")
	private boolean travelPaymentTypeIncome;
	
	@Column(name = "travel_amount")
	private int travelAmount;
	
	@Column(name = "travel_start_date")
	private Date travelStartDate;
	
	@Column(name = "travel_end_date")
	private Date travelEndDate;	
	
	@Column(name = "travel_description")
	private String travelDescription;
	
	//Section 4 - Verification
	 
	@Column(name = "date_signed")
	private Date dateSigned;
	
	private String signature;

	public EconomicInterestPI() {}

	public EconomicInterestPI(int progress, String lastName, String firstName, String middleInitial, String phoneNumber,
			Department department, String mailCode, String email, String projectTitle, String campus, String entityName,
			String entityAddress, String principalBusiness, Integer fundingAmount, Boolean estimateAmount,
			Boolean actualAmount, boolean initialFunding, Date fundDate, boolean interimFund, Date interimDate,
			Boolean positionHeld, String positionTitle, Boolean investmentGreaterThan, Integer investAmount,
			Date dateDisposed, Boolean receivedIncome, Integer receivedAmount, Boolean receivedThroughSpouse,
			Boolean receivedThroughEntity, Integer loanAmount, boolean loanSecured, Double loanInterest,
			Boolean loanPaidOff, Boolean giftsReceived, String giftsDescription, Integer giftsValue,
			Date giftsReceivedDate, Boolean travelThroughEntity, boolean travelPaymentTypeGift,
			boolean travelPaymentTypeIncome, int travelAmount, Date travelStartDate, Date travelEndDate,
			String travelDescription, Date dateSigned, String signature) {
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
		this.campus = campus;
		this.entityName = entityName;
		this.entityAddress = entityAddress;
		this.principalBusiness = principalBusiness;
		this.fundingAmount = fundingAmount;
		this.estimateAmount = estimateAmount;
		this.actualAmount = actualAmount;
		this.initialFunding = initialFunding;
		this.fundDate = fundDate;
		this.interimFund = interimFund;
		this.interimDate = interimDate;
		this.positionHeld = positionHeld;
		this.positionTitle = positionTitle;
		this.investmentGreaterThan = investmentGreaterThan;
		this.investAmount = investAmount;
		this.dateDisposed = dateDisposed;
		this.receivedIncome = receivedIncome;
		this.receivedAmount = receivedAmount;
		this.receivedThroughSpouse = receivedThroughSpouse;
		this.receivedThroughEntity = receivedThroughEntity;
		this.loanAmount = loanAmount;
		this.loanSecured = loanSecured;
		this.loanInterest = loanInterest;
		this.loanPaidOff = loanPaidOff;
		this.giftsReceived = giftsReceived;
		this.giftsDescription = giftsDescription;
		this.giftsValue = giftsValue;
		this.giftsReceivedDate = giftsReceivedDate;
		this.travelThroughEntity = travelThroughEntity;
		this.travelPaymentTypeGift = travelPaymentTypeGift;
		this.travelPaymentTypeIncome = travelPaymentTypeIncome;
		this.travelAmount = travelAmount;
		this.travelStartDate = travelStartDate;
		this.travelEndDate = travelEndDate;
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

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
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

	public Boolean getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(Boolean estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public Boolean getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Boolean actualAmount) {
		this.actualAmount = actualAmount;
	}

	public boolean isInitialFunding() {
		return initialFunding;
	}

	public void setInitialFunding(boolean initialFunding) {
		this.initialFunding = initialFunding;
	}

	public Date getFundDate() {
		return fundDate;
	}

	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}

	public boolean isInterimFund() {
		return interimFund;
	}

	public void setInterimFund(boolean interimFund) {
		this.interimFund = interimFund;
	}

	public Date getInterimDate() {
		return interimDate;
	}

	public void setInterimDate(Date interimDate) {
		this.interimDate = interimDate;
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

	public Date getDateDisposed() {
		return dateDisposed;
	}

	public void setDateDisposed(Date dateDisposed) {
		this.dateDisposed = dateDisposed;
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

	public boolean isLoanSecured() {
		return loanSecured;
	}

	public void setLoanSecured(boolean loanSecured) {
		this.loanSecured = loanSecured;
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

	public boolean isTravelPaymentTypeGift() {
		return travelPaymentTypeGift;
	}

	public void setTravelPaymentTypeGift(boolean travelPaymentTypeGift) {
		this.travelPaymentTypeGift = travelPaymentTypeGift;
	}

	public boolean isTravelPaymentTypeIncome() {
		return travelPaymentTypeIncome;
	}

	public void setTravelPaymentTypeIncome(boolean travelPaymentTypeIncome) {
		this.travelPaymentTypeIncome = travelPaymentTypeIncome;
	}

	public int getTravelAmount() {
		return travelAmount;
	}

	public void setTravelAmount(int travelAmount) {
		this.travelAmount = travelAmount;
	}

	public Date getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public Date getTravelEndDate() {
		return travelEndDate;
	}

	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
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
