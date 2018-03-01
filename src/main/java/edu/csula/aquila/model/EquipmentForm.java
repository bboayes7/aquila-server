package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "equipment_form")
public class EquipmentForm extends Form implements Serializable{

	private static final long serialVersionUID = -639475707169544047L;

	private int progress;

	@Column(name = "faculty_name")
	String facultyName;

	@Column
	String department;

	@Column(name = "proposal_title")
	String proposalTitle;

	@Column
	boolean extension;

	@Column(name = "extension_value")
	String extensionValue;

	@Column(name = "cost_share")
	boolean costShare;

	@Column
	boolean donation;

	@Column(name = "new_equipment")
	boolean newEquipment;

	// type of equipment First
	//this name, spec/quote
	//this needs string url
	
	@OneToMany(cascade = { CascadeType.ALL },mappedBy= "equipmentForm")
	List<TypeOfEquipment> typeOfEquipment; 

	// location
	@Column(name = "building_location")
	String buildingLocation;
	@Column(name = "room_location")
	String roomLocation;
	// donation
	@Column(name = "is_donation")
	boolean isDonation;
	@Column(name = "company_donating")
	String companyDonating;
	@Column(name = "previous_use")
	String previousUse;
	// please check all that apply
	@Column(name = "space_modification_requirement")
	boolean spaceModificationRequirement;
	// electrical modification
	@Column(name = "electrical_modification")
	boolean electricalModification;
	@Column
	boolean volts;
	@Column
	boolean amps;
	@Column
	boolean phase;

	@Column(name = "dedicated_power")
	boolean dedicatedPower;
	@Column(name = "circuit_breaker_specification")
	boolean circuitBreakerSpecification;
	@Column(name = "motor_compressor_specification")
	boolean motorCompressorSpecification;
	@Column(name = "special_needs")
	boolean specialNeeds;
	@Column
	boolean fwr;



	@Column(name = "special_needs_string")
	String specialNeedsString;
	@Column(name = "fwr_paid_by")
	String fwrPaidBy;
	// hvac
	@Column
	boolean hvac;
	@Column(name = "air_chilled_water_flow")
	boolean airChilledWaterFlow;
	@Column
	boolean temperature;
	@Column(name = "humidity_control")
	boolean humidityControl;
	@Column(name = "supply_pressure")
	boolean supplyPressure;
	@Column(name = "central_package_unit")
	boolean centralPackageUnit;
	@Column(name = "special_work")
	boolean specialWork;
	@Column(name = "noise_requirement")
	boolean noiseRequirement;

	// plumbing
	@Column
	boolean plumbing;
	@Column
	boolean fluid;
	@Column(name = "flow_rate")
	boolean flowRate;
	@Column
	boolean pressure;
	@Column(name = "fluid_temperature")
	boolean fluidTemperature;
	@Column(name = "pump_compressor_motor")
	boolean pumpCompressorMotor;
	// NetworkRequirements
	@Column
	boolean maintenance;
	@Column(name = "license_requirements")
	boolean licenseRequirements;
	@Column
	boolean hardware;

	// hazardous material
	@Column(name = "hazardous_material")
	boolean hazardousMaterial;

	@ElementCollection
	@MapKeyColumn(name="chemical_name")
	@Column(name = "amount")
	@CollectionTable(name="chemicals", joinColumns=@JoinColumn(name="chemicals_id"))
	Map<Integer, String> chemicals; // chem/quantity

	@ElementCollection
	@MapKeyColumn(name="radiation_name")
	@Column(name = "source")
	@CollectionTable(name="radiation", joinColumns=@JoinColumn(name="radiation_id"))
	Map<String, String> radiation; // radiation/source


	@Column(name = "maintenance_requirement")
	boolean maintenanceRequirement;

	@ElementCollection
	@CollectionTable(name = "list_of_requirements", joinColumns = @JoinColumn(name = "equipment_form_id"))
	@Column(name = "requirement")
	List<String> listOfRequirements;

	//List of size of Equipment
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "equipmentForm")
	List<SizeOfEquipment> sizeOfEquipment;



	@Column(name = "director_of_research_development_signature")
	Signature directorOfResearchDevelopmentSignature;

	@Column(name = "director_of_research_development_signature_date")
	Date directorOfResearchDevelopmentSignatureDate;

	@Column(name = "director_of_facilities_services_signature")
	Signature directorOfFacilitiesServicesSignature;

	@Column(name = "director_of_facilities_services_signature_date")
	Date directorOfFacilitiesServicesSignatureDate;


	
	public EquipmentForm() {

	}

	public EquipmentForm(int progress, String facultyName, String department, String proposalTitle, boolean extension,
			String extensionValue, boolean costShare, boolean donation, boolean newEquipment,
			List<TypeOfEquipment> typeOfEquipment, String buildingLocation, String roomLocation, boolean isDonation,
			String companyDonating, String previousUse, boolean spaceModificationRequirement,
			boolean electricalModification, boolean volts, boolean amps, boolean phase, boolean dedicatedPower,
			boolean circuitBreakerSpecification, boolean motorCompressorSpecification, boolean specialNeeds,
			boolean fwr, String specialNeedsString, String fwrPaidBy, boolean hvac, boolean airChilledWaterFlow,
			boolean temperature, boolean humidityControl, boolean supplyPressure, boolean centralPackageUnit,
			boolean specialWork, boolean noiseRequirement, boolean plumbing, boolean fluid, boolean flowRate,
			boolean pressure, boolean fluidTemperature, boolean pumpCompressorMotor, boolean maintenance,
			boolean licenseRequirements, boolean hardware, boolean hazardousMaterial, Map<Integer, String> chemicals,
			Map<String, String> radiation, boolean maintenanceRequirement,
			List<String> listOfRequirements, List<SizeOfEquipment> sizeOfEquipment,
			Signature directorOfResearchDevelopmentSignature, Date directorOfResearchDevelopmentSignatureDate,
			Signature directorOfFacilitiesServicesSignature, Date directorOfFacilitiesServicesSignatureDate) {
		this.progress = progress;
		this.facultyName = facultyName;
		this.department = department;
		this.proposalTitle = proposalTitle;
		this.extension = extension;
		this.extensionValue = extensionValue;
		this.costShare = costShare;
		this.donation = donation;
		this.newEquipment = newEquipment;
		this.typeOfEquipment = typeOfEquipment;
		this.buildingLocation = buildingLocation;
		this.roomLocation = roomLocation;
		this.isDonation = isDonation;
		this.companyDonating = companyDonating;
		this.previousUse = previousUse;
		this.spaceModificationRequirement = spaceModificationRequirement;
		this.electricalModification = electricalModification;
		this.volts = volts;
		this.amps = amps;
		this.phase = phase;
		this.dedicatedPower = dedicatedPower;
		this.circuitBreakerSpecification = circuitBreakerSpecification;
		this.motorCompressorSpecification = motorCompressorSpecification;
		this.specialNeeds = specialNeeds;
		this.fwr = fwr;
		this.specialNeedsString = specialNeedsString;
		this.fwrPaidBy = fwrPaidBy;
		this.hvac = hvac;
		this.airChilledWaterFlow = airChilledWaterFlow;
		this.temperature = temperature;
		this.humidityControl = humidityControl;
		this.supplyPressure = supplyPressure;
		this.centralPackageUnit = centralPackageUnit;
		this.specialWork = specialWork;
		this.noiseRequirement = noiseRequirement;
		this.plumbing = plumbing;
		this.fluid = fluid;
		this.flowRate = flowRate;
		this.pressure = pressure;
		this.fluidTemperature = fluidTemperature;
		this.pumpCompressorMotor = pumpCompressorMotor;
		this.maintenance = maintenance;
		this.licenseRequirements = licenseRequirements;
		this.hardware = hardware;
		this.hazardousMaterial = hazardousMaterial;
		this.chemicals = chemicals;
		this.radiation = radiation;
		this.maintenanceRequirement = maintenanceRequirement;
		this.listOfRequirements = listOfRequirements;
		this.sizeOfEquipment = sizeOfEquipment;
		this.directorOfResearchDevelopmentSignature = directorOfResearchDevelopmentSignature;
		this.directorOfResearchDevelopmentSignatureDate = directorOfResearchDevelopmentSignatureDate;
		this.directorOfFacilitiesServicesSignature = directorOfFacilitiesServicesSignature;
		this.directorOfFacilitiesServicesSignatureDate = directorOfFacilitiesServicesSignatureDate;
	}




	public List<SizeOfEquipment> getSizeOfEquipment() {
		return sizeOfEquipment;
	}

	public void setSizeOfEquipment(List<SizeOfEquipment> sizeOfEquipment) {
		this.sizeOfEquipment = sizeOfEquipment;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProposalTitle() {
		return proposalTitle;
	}

	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}

	public boolean isExtension() {
		return extension;
	}

	public void setExtension(boolean extension) {
		this.extension = extension;
	}

	public String getExtensionValue() {
		return extensionValue;
	}

	public void setExtensionValue(String extensionValue) {
		this.extensionValue = extensionValue;
	}
	public List<TypeOfEquipment> getTypeOfEquipment() {
		return typeOfEquipment;
	}

	public void setTypeOfEquipment(List<TypeOfEquipment> typeOfEquipment) {
		this.typeOfEquipment = typeOfEquipment;
	}
	// Section B needs an inner class of Personnel for each personnel in the list
	// This class contains the name, employer, position, and time of a person
	// working on the project
	@Entity
	@Table(name = "type_of_equipment")
	public static class TypeOfEquipment implements Serializable {

		private static final long serialVersionUID = -7320343762569192082L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "type_of_equipment_id")
		Long Id;

		@Column
		String name;
		@Column(name = "specification")
		String specification;
		@Column
		String url;

		// relation
		@JsonIgnore
		@ManyToOne(cascade = { CascadeType.ALL })
		@JoinColumn(name = "equipment_form_id")
		EquipmentForm equipmentForm;

		public TypeOfEquipment() {
		}

		public TypeOfEquipment(String name, String specification, String url) {
			this.name = name; 
			this.specification = specification;
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getSpecification() {
			return specification;
		}

		public void setSpecification(String specification) {
			this.specification= specification;
		}
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	public boolean isCostShare() {
		return costShare;
	}

	public void setCostShare(boolean costShare) {
		this.costShare = costShare;
	}

	public boolean isDonation() {
		return donation;
	}

	public void setDonation(boolean donation) {
		this.donation = donation;
	}

	public boolean isNewEquipment() {
		return newEquipment;
	}

	public void setNewEquipment(boolean newEquipment) {
		this.newEquipment = newEquipment;
	}

	public String getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(String buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}


	public String getCompanyDonating() {
		return companyDonating;
	}

	public void setCompanyDonating(String companyDonating) {
		this.companyDonating = companyDonating;
	}

	public String getPreviousUse() {
		return previousUse;
	}

	public void setPreviousUse(String previousUse) {
		this.previousUse = previousUse;
	}

	public boolean isSpaceModificationRequirement() {
		return spaceModificationRequirement;
	}

	public void setSpaceModificationRequirement(boolean spaceModificationRequirement) {
		this.spaceModificationRequirement = spaceModificationRequirement;
	}

	public boolean isElectricalModification() {
		return electricalModification;
	}

	public void setElectricalModification(boolean electricalModification) {
		this.electricalModification = electricalModification;
	}

	public boolean isVolts() {
		return volts;
	}

	public void setVolts(boolean volts) {
		this.volts = volts;
	}

	public boolean isAmps() {
		return amps;
	}

	public void setAmps(boolean amps) {
		this.amps = amps;
	}

	public boolean isPhase() {
		return phase;
	}

	public void setPhase(boolean phase) {
		this.phase = phase;
	}

	public boolean isDedicatedPower() {
		return dedicatedPower;
	}

	public void setDedicatedPower(boolean dedicatedPower) {
		this.dedicatedPower = dedicatedPower;
	}

	public boolean isCircuitBreakerSpecification() {
		return circuitBreakerSpecification;
	}

	public void setCircuitBreakerSpecification(boolean circuitBreakerSpecification) {
		this.circuitBreakerSpecification = circuitBreakerSpecification;
	}

	public boolean isMotorCompressorSpecification() {
		return motorCompressorSpecification;
	}

	public void setMotorCompressorSpecification(boolean motorCompressorSpecification) {
		this.motorCompressorSpecification = motorCompressorSpecification;
	}

	public boolean isSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(boolean specialNeeds) {
		this.specialNeeds = specialNeeds;
	}
	public boolean isFwr() {
		return fwr;
	}

	public void setFwr(boolean fwr) {
		this.fwr = fwr;
	}


	public String getSpecialNeedsString() {
		return specialNeedsString;
	}

	public void setSpecialNeedsString(String specialNeedsString) {
		this.specialNeedsString = specialNeedsString;
	}

	public String getFwrPaidBy() {
		return fwrPaidBy;
	}

	public void setFwrPaidBy(String fwrPaidBy) {
		this.fwrPaidBy = fwrPaidBy;
	}

	public boolean isHvac() {
		return hvac;
	}

	public void setHvac(boolean hvac) {
		this.hvac = hvac;
	}

	public boolean isAirChilledWaterFlow() {
		return airChilledWaterFlow;
	}

	public void setAirChilledWaterFlow(boolean airChilledWaterFlow) {
		this.airChilledWaterFlow = airChilledWaterFlow;
	}

	public boolean isTemperature() {
		return temperature;
	}

	public void setTemperature(boolean temperature) {
		this.temperature = temperature;
	}

	public boolean isHumidityControl() {
		return humidityControl;
	}

	public void setHumidityControl(boolean humidityControl) {
		this.humidityControl = humidityControl;
	}

	public boolean isSupplyPressure() {
		return supplyPressure;
	}

	public void setSupplyPressure(boolean supplyPressure) {
		this.supplyPressure = supplyPressure;
	}

	public boolean isCentralPackageUnit() {
		return centralPackageUnit;
	}

	public void setCentralPackageUnit(boolean centralPackageUnit) {
		this.centralPackageUnit = centralPackageUnit;
	}

	public boolean isSpecialWork() {
		return specialWork;
	}

	public void setSpecialWork(boolean specialWork) {
		this.specialWork = specialWork;
	}

	public boolean isNoiseRequirement() {
		return noiseRequirement;
	}

	public void setNoiseRequirement(boolean noiseRequirement) {
		this.noiseRequirement = noiseRequirement;
	}

	public boolean isPlumbing() {
		return plumbing;
	}

	public void setPlumbing(boolean plumbing) {
		this.plumbing = plumbing;
	}

	public boolean isFluid() {
		return fluid;
	}

	public void setFluid(boolean fluid) {
		this.fluid = fluid;
	}

	public boolean isFlowRate() {
		return flowRate;
	}

	public void setFlowRate(boolean flowRate) {
		this.flowRate = flowRate;
	}

	public boolean isPressure() {
		return pressure;
	}

	public void setPressure(boolean pressure) {
		this.pressure = pressure;
	}

	public boolean isFluidTemperature() {
		return fluidTemperature;
	}

	public void setFluidTemperature(boolean fluidTemperature) {
		this.fluidTemperature = fluidTemperature;
	}

	public boolean isPumpCompressorMotor() {
		return pumpCompressorMotor;
	}

	public void setPumpCompressorMotor(boolean pumpCompressorMotor) {
		this.pumpCompressorMotor = pumpCompressorMotor;
	}

	public boolean isMaintenance() {
		return maintenance;
	}

	public void setMaintenance(boolean maintenance) {
		this.maintenance = maintenance;
	}

	public boolean isLicenseRequirements() {
		return licenseRequirements;
	}

	public void setLicenseRequirements(boolean licenseRequirements) {
		this.licenseRequirements = licenseRequirements;
	}

	public boolean isHardware() {
		return hardware;
	}

	public void setHardware(boolean hardware) {
		this.hardware = hardware;
	}

	public boolean isHazardousMaterial() {
		return hazardousMaterial;
	}

	public void setHazardousMaterial(boolean hazardousMaterial) {
		this.hazardousMaterial = hazardousMaterial;
	}

	public boolean isMaintenanceRequirement() {
		return maintenanceRequirement;
	}

	public void setMaintenanceRequirement(boolean maintenanceRequirement) {
		this.maintenanceRequirement = maintenanceRequirement;
	}

	//SIZE OF EQUIPMENT LIST
	@Entity
	@Table(name = "size_of_equipment")
	public static class SizeOfEquipment  implements Serializable {

		private static final long serialVersionUID = 6L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "size_of_equipment_id")
		Long Id;

		@Column(name = "size_of_equipment")
		boolean sizeOfEquipment;

		@Column
		int height;
		@Column
		int width; 
		@Column
		int depth;

		@JsonIgnore
		@ManyToOne(cascade = { CascadeType.ALL })
		@JoinColumn(name = "equipment_form_id")
		EquipmentForm equipmentForm;

		public SizeOfEquipment() {

		}

		public SizeOfEquipment(boolean sizeOfEquipment, int height , int width, int depth ) {
			this.sizeOfEquipment = sizeOfEquipment;
			this.height = height;
			this.width = width;
			this.depth = depth; 	
		}
		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}
		public boolean isSizeOfEquipment() {
			return sizeOfEquipment;
		}

		public void setSizeOfEquipment(boolean sizeOfEquipment) {
			this.sizeOfEquipment = sizeOfEquipment;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}
		public EquipmentForm getEquipmentForm() {
			return equipmentForm;
		}

		public void setEquipmentForm(EquipmentForm EquipmentForm) {
			this.equipmentForm = equipmentForm;
		}
	}

	public Signature getDirectorOfResearchDevelopmentSignature() {
		return directorOfResearchDevelopmentSignature;
	}

	public void setDirectorOfResearchDevelopmentSignature(Signature directorOfResearchDevelopmentSignature) {
		this.directorOfResearchDevelopmentSignature = directorOfResearchDevelopmentSignature;
	}

	public Date getDirectorOfResearchDevelopmentSignatureDate() {
		return directorOfResearchDevelopmentSignatureDate;
	}

	public void setDirectorOfResearchDevelopmentSignatureDate(Date directorOfResearchDevelopmentSignatureDate) {
		this.directorOfResearchDevelopmentSignatureDate = directorOfResearchDevelopmentSignatureDate;
	}

	public Signature getDirectorOfFacilitiesServicesSignature() {
		return directorOfFacilitiesServicesSignature;
	}

	public void setDirectorOfFacilitiesServicesSignature(Signature directorOfFacilitiesServicesSignature) {
		this.directorOfFacilitiesServicesSignature = directorOfFacilitiesServicesSignature;
	}

	public Date getDirectorOfFacilitiesServicesSignatureDate() {
		return directorOfFacilitiesServicesSignatureDate;
	}

	public void setDirectorOfFacilitiesServicesSignatureDate(Date directorOfFacilitiesServicesSignatureDate) {
		this.directorOfFacilitiesServicesSignatureDate = directorOfFacilitiesServicesSignatureDate;
	}

	public Map<Integer, String> getChemicals() {
		return chemicals;
	}

	public void setChemicals(Map<Integer, String> chemicals) {
		this.chemicals = chemicals;
	}	
	public Map<String, String> getRadiation() {
		return radiation;
	}

	public void setRadiation(Map<String, String> radiation) {
		this.radiation = radiation;
	}


	public List<String> getListOfRequirements() {
		return listOfRequirements;
	}

	public void setListOfRequirements(List<String> listOfRequirements) {
		this.listOfRequirements = listOfRequirements;
	}	
}

