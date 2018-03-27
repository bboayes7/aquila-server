package edu.csula.aquila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EconomicInterestPIDao;
import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.EconomicInterestPI;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class StageController {
	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private TimelineDao timelineDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private EconomicInterestPIDao economicDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	// Get a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.GET)
	public Stage getStage(@PathVariable Long id) {
		return stageDao.getStage(id);
	}

	// create a stage
	@RequestMapping(value = "proposal/timeline/{timelineId}/stage/", method = RequestMethod.POST)
	public Stage createStage(@RequestBody Stage stage, @PathVariable Long timelineId) {
		Timeline timeline = timelineDao.getTimeline(timelineId);
		stage.setName("New Stage");
		stage.setTimeline(timeline);
	
		return stageDao.saveStage(stage);
	}

	// update a stage

	@RequestMapping(value = "timeline/{timelineId}/stage/update/{id}", method = RequestMethod.PUT)
	public Stage updateStage( @RequestBody Stage stage, @PathVariable Long timelineId, @PathVariable Long id ) 
	{
		Timeline timeline = timelineDao.getTimeline(timelineId);
		stage.setTimeline(timeline);

		stage.setId(id);
		
		Proposal proposal = timeline.getProposal();
		
		//update forms
		formUpdate(stage, proposal);
		
		//update files
		fileUpdate(stage, proposal);
		
		proposalDao.saveProposal(proposal);
		
		
		//stage check
		stageCheck(stage);
		
		return stageDao.saveStage(stage);
	}

	// delete a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DeleteResponse deleteStage(@PathVariable Long id) {
		
		stageDao.deleteStage(id);
		return new DeleteResponse("Stage Deleted!");
	}

	// message to send when a stage is deleted
	public class DeleteResponse {
		private String message;

		public DeleteResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
	
	//This method updates the required file's map value from null to a fileInfo object
	public void fileUpdate(Stage stage, Proposal proposal) {
		Map<String, edu.csula.aquila.model.FileInfo> files = stage.getRequiredFiles();
		
		for(Map.Entry<String, FileInfo> file : files.entrySet()) {
			String key = file.getKey();
			FileInfo fileValue = new FileInfo(key, false);
			
			file.setValue(fileInfoDao.saveFile(fileValue));
		}
	}
	
	//This method updates the required form's map value from null to a form in the proposal
	public void formUpdate(Stage stage, Proposal proposal) {
		
		Map<String, Long> forms = stage.getRequiredForms();
		
		
		for(Map.Entry<String, Long> form : forms.entrySet()) {
			String key = form.getKey();
			
			switch(key) {
			//this case is easy since there's already an intake form set
			case "Intake":
				IntakeForm intakeForm = proposal.getIntakeForm();
				form.setValue(intakeForm.getId());
				
				break;
			//this case is tricky since there isn't an equipment form created yet and there's no id
			//we also have to consider if a stage is getting updated again and there's already a form linked
			//if that's the case then we would have to check if a value is null to create a new form for the link
			case "Equipment":
				if(form.getValue() == null) {
					EquipmentForm equipmentForm = new EquipmentForm();
					equipmentForm = equipmentDao.saveEquipmentForm(equipmentForm);
					form.setValue(equipmentForm.getId());
					proposal.setEquipmentForm(equipmentForm);
					
					break;
				}
				break;
			case "Statement Of Economic Interest":
				if(form.getValue() == null) {
					EconomicInterestPI economicInt = new EconomicInterestPI();
					economicInt = economicDao.saveEconomicInterestPI(economicInt);
					form.setValue(economicInt.getId());
					proposal.setEconomicInterestPi(economicInt);
					
					break;
				}
				break;
			//make cases for keys {"Approval", "COI Other Investigator/Key Personnel PHS", "COI Other Investigator/Key Personnel PHS", "COI Principal Investigator PHS", COI Principal Investigator NONPHS  
			}
			
		}
		
		
	}
	
	//checks if a stage is complete

	public void stageCheck(Stage stage) {
		
		Proposal proposal = proposalDao.getProposal(1L);
		// check if all forms are completed through the isComplete boolean
		boolean formsComplete = false;
		boolean filesUploaded = true; // consider if stages dont have required files or required forms

		Map<String, Long> forms = stage.getRequiredForms();
//		List<FileInfo> files = (List<FileInfo>) stage.getRequiredFiles().values(); 

		// check if all forms are complete (maybe add a condition if form list is 0 then
		// dont call this?)
		boolean complete = true;
		if (forms.size() != 0) {
			for(Map.Entry<String, Long> form : forms.entrySet()) {
				String key = form.getKey();
				
				if(key == "Intake Form") {
					IntakeForm intakeForm = proposal.getIntakeForm();
					complete = intakeForm.isComplete();
				}
				
				if(key == "Equipment Form") {
					EquipmentForm equipmentForm = proposal.getEquipmentForm();
					complete = equipmentForm.isComplete();
				}
				
				
				
				
				if(complete == false) {
					break;
				}
			}
		}
		
		if(complete) {
			formsComplete = true;
		}

		// check if all files are uploaded
//		if (files.size() != 0) {
//			for (FileInfo file : files) {
//				if (!file.isUploaded()) {
//					break;
//				} else {
//					filesUploaded = true; // if all files are uploaded, have a boolean called filesUploaded and set it to true
//
//				}
//			}
//		} else {
//			filesUploaded = true;
//		}

		// find out how to implement budget checking

		// when formsCompleted && filesUploaded is true
		if (formsComplete && filesUploaded) {
			// set uasReviewRequired to true
			stage.setUasReviewRequired(true);
			// send an email to UAS
			// for now just print email sent
			System.out.println("Email 'sent'");
		}
	}
	
	
}
