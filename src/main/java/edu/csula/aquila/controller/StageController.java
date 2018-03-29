package edu.csula.aquila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestKPNonPHSDao;
import edu.csula.aquila.daos.ConflictOfInterestKPPHSDao;
import edu.csula.aquila.daos.ConflictOfInterestPHSDao;
import edu.csula.aquila.daos.ConflictOfInterestPINonPHSDao;
import edu.csula.aquila.daos.EconomicInterestPIDao;
import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.ConflictOfInterestKPNonPHS;
import edu.csula.aquila.model.ConflictOfInterestKPPHS;
import edu.csula.aquila.model.ConflictOfInterestPHS;
import edu.csula.aquila.model.ConflictOfInterestPINonPHS;
import edu.csula.aquila.model.EconomicInterestPI;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Stage;
import edu.csula.aquila.model.Timeline;

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

	@Autowired
	private ConflictOfInterestKPPHSDao coiKpPhsDao;

	@Autowired
	private ConflictOfInterestKPNonPHSDao coiKpNonPhsDao;

	@Autowired
	private ConflictOfInterestPHSDao coiPhsDao;

	@Autowired
	private ConflictOfInterestPINonPHSDao coiPiNonPhsDao;

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
		stage.setStageOrder(timeline.getStages().size());
		return stageDao.saveStage(stage);
	}

	// update a stage

	@RequestMapping(value = "timeline/{timelineId}/stage/update/{id}", method = RequestMethod.PUT)
	public Stage updateStage(@RequestBody Stage stage, @PathVariable Long timelineId, @PathVariable Long id) {
		Timeline timeline = timelineDao.getTimeline(timelineId);
		stage.setTimeline(timeline);
		stage.setId(id);
		
		int currentOrder = stage.getStageOrder();

		Proposal proposal = timeline.getProposal();

		// update forms
		formUpdate(stage, proposal);

		// update files
		fileUpdate(stage, proposal);

		proposalDao.saveProposal(proposal);

		// stage check
		stageCheck(stage, proposal);

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

	@RequestMapping(value = "timeline/stage/{stageId}/order/{indexToPush}", method = RequestMethod.GET)
	public ResponseEntity<Object> reorderStages(@PathVariable Long stageId, @PathVariable int indexToPush) {
		// get the timeline to get all stages
		Stage stage = stageDao.getStage(stageId);
		int currentStageIndex = stage.getStageOrder();
		System.out.println(currentStageIndex);
		Timeline timeline = stage.getTimeline();
		List<Stage> stages = timeline.getStages();
		
		if(indexToPush > stages.size()) {
			return new ResponseEntity("Out Of Bounds", HttpStatus.BAD_REQUEST);
		}

		System.out.println("------------------------------------------------------------");
		// test out stageOrders
		for (int i = 0; i < stages.size(); i++) {
			System.out.println(stages.get(i).getName() + " ORDER : #" + stages.get(i).getStageOrder());
		}

		// log what we're testing
		System.out.println("switching stage #" + currentStageIndex +" with stage #" + indexToPush + "..");

		// try it out




		// 3 cases

		// deleting stage
		// delete the stage>grab order values after that stage and decrement by 1

		// stage moved up the list
		if(currentStageIndex > indexToPush) {
			for (int i = indexToPush; i <= currentStageIndex; i++) {
				stages.get(i).setStageOrder(stages.get(i).getStageOrder() + 1);
				stageDao.saveStage(stages.get(i));
			}
			stage.setStageOrder(indexToPush);
			stageDao.saveStage(stage);
			
		}


		// stage moved down the list
		if (currentStageIndex < indexToPush) {
			// reorder stages in between
			for (int i = currentStageIndex; i <= indexToPush; i++) {
				stages.get(i).setStageOrder(stages.get(i).getStageOrder() - 1);
				stageDao.saveStage(stages.get(i));
			}
			stage.setStageOrder(indexToPush);
			stageDao.saveStage(stage);
			
		}
		
//		timeline.setStages(stageDao.getStages(timeline.getId()));
		List<Stage> reorderedStages = stageDao.getStages(timeline.getId());
		System.out.println("new stages");
		for(int i = 0; i < reorderedStages.size(); i++) {
			System.out.println(reorderedStages.get(i).getName() + " ORDER : #" + reorderedStages.get(i).getStageOrder());
		}
		
		timeline.setStages(reorderedStages);
		timelineDao.saveTimeline(timeline);

		return new ResponseEntity<Object>("Stages Reordered", HttpStatus.ACCEPTED);
	}

	// This method updates the required file's map value from null to a fileInfo
	// object
	public void fileUpdate(Stage stage, Proposal proposal) {
		Map<String, edu.csula.aquila.model.FileInfo> files = stage.getRequiredFiles();

		for (Map.Entry<String, FileInfo> file : files.entrySet()) {
			String key = file.getKey();
			FileInfo fileValue = new FileInfo(key, false);

			file.setValue(fileInfoDao.saveFile(fileValue));
		}
	}

	// This method updates the required form's map value from null to a form in the
	// proposal
	public void formUpdate(Stage stage, Proposal proposal) {

		Map<String, Long> forms = stage.getRequiredForms();

		for (Map.Entry<String, Long> form : forms.entrySet()) {
			String key = form.getKey();

			switch (key) {
			// this case is easy since there's already an intake form set
			case "Intake":
				IntakeForm intakeForm = proposal.getIntakeForm();
				form.setValue(intakeForm.getId());

				break;
			// this case is tricky since there isn't an equipment form created yet and
			// there's no id
			// we also have to consider if a stage is getting updated again and there's
			// already a form linked
			// if that's the case then we would have to check if a value is null to create a
			// new form for the link
			case "Equipment":
				if (form.getValue() == null) {
					EquipmentForm equipmentForm = new EquipmentForm();
					equipmentForm = equipmentDao.saveEquipmentForm(equipmentForm);
					form.setValue(equipmentForm.getId());
					proposal.setEquipmentForm(equipmentForm);

					break;
				}
				break;
			case "Statement Of Economic Interest":
				if (form.getValue() == null) {
					EconomicInterestPI economicInt = new EconomicInterestPI();
					economicInt = economicDao.saveEconomicInterestPI(economicInt);
					form.setValue(economicInt.getId());
					proposal.setEconomicInterestPi(economicInt);

					break;
				}
				break;
			case "COI Other Investigator/Key Personnel PHS":
				if (form.getValue() == null) {
					ConflictOfInterestKPPHS coiKpPhs = new ConflictOfInterestKPPHS();
					coiKpPhs = coiKpPhsDao.saveConflictOfInterestKPPHS(coiKpPhs);
					form.setValue(coiKpPhs.getId());
					proposal.setCoiKpPhs(coiKpPhs);

					break;
				}
				break;
			case "COI Other Investigator/Key Personnel NONPHS":
				if (form.getValue() == null) {
					ConflictOfInterestKPNonPHS coiKpNonPhs = new ConflictOfInterestKPNonPHS();
					coiKpNonPhs = coiKpNonPhsDao.saveConflictOfInterestKPNonPHS(coiKpNonPhs);
					form.setValue(coiKpNonPhs.getId());
					proposal.setCoiKpNonPhs(coiKpNonPhs);

					break;
				}
				break;
			case "COI Principal Investigator PHS":
				if (form.getValue() == null) {
					ConflictOfInterestPHS coiPhs = new ConflictOfInterestPHS();
					coiPhs = coiPhsDao.saveConflictOfInterestPHS(coiPhs);
					form.setValue(coiPhs.getId());
					proposal.setCoiPhs(coiPhs);

					break;
				}
				break;
			case "COI Principal Investigator NONPHS":
				if (form.getValue() == null) {
					ConflictOfInterestPINonPHS coiPiNonPhs = new ConflictOfInterestPINonPHS();
					coiPiNonPhs = coiPiNonPhsDao.saveConflictOfInterestPINonPHS(coiPiNonPhs);
					form.setValue(coiPiNonPhs.getId());
					proposal.setCoiPiNonPhs(coiPiNonPhs);

					break;
				}
				break;
			// make cases for keys {"Approval"}
			}

		}

	}

	// checks if a stage is complete

	public void stageCheck(Stage stage, Proposal proposal) {
		// check if all forms are completed through the isComplete boolean
		boolean formsComplete = false;
		boolean filesUploaded = true; // consider if stages dont have required files or required forms

		Map<String, Long> forms = stage.getRequiredForms();
		// List<FileInfo> files = (List<FileInfo>) stage.getRequiredFiles().values();

		// check if all forms are complete (maybe add a condition if form list is 0 then
		// dont call this?)
		boolean complete = true;
		if (forms.size() != 0) {
			for (Map.Entry<String, Long> form : forms.entrySet()) {
				String key = form.getKey();

				switch (key) {
				case "Intake":
					IntakeForm intakeForm = proposal.getIntakeForm();
					complete = intakeForm.isComplete();
					break;
				case "Equipment":
					EquipmentForm equipmentForm = proposal.getEquipmentForm();
					complete = equipmentForm.isComplete();
					break;
				case "Statement Of Economic Interest":
					EconomicInterestPI economicInterest = proposal.getEconomicInterestPi();
					complete = economicInterest.isComplete();
					break;
				case "COI Other Investigator/Key Personnel PHS":
					ConflictOfInterestKPPHS coiKpPhs = proposal.getCoiKpPhs();
					complete = coiKpPhs.isComplete();
					break;
				case "COI Other Investigator/Key Personnel NONPHS":
					ConflictOfInterestKPNonPHS coiKpNonPhs = proposal.getCoiKpNonPhs();
					complete = coiKpNonPhs.isComplete();
					break;
				case "COI Principal Investigator PHS":
					ConflictOfInterestPHS coiPhs = proposal.getCoiPhs();
					complete = coiPhs.isComplete();
					break;
				case "COI Principal Investigator NONPHS":
					ConflictOfInterestPINonPHS coiPiNonPhs = proposal.getCoiPiNonPhs();
					complete = coiPiNonPhs.isComplete();
					break;
				// make cases for key {"Approval"}
				}

				// if any of the forms aren't complete, break the loop
				if (complete == false) {
					break;
				}
			}
		}

		if (complete) {
			formsComplete = true;
		}

		// check if all files are uploaded
		// if (files.size() != 0) {
		// for (FileInfo file : files) {
		// if (!file.isUploaded()) {
		// break;
		// } else {
		// filesUploaded = true; // if all files are uploaded, have a boolean called
		// filesUploaded and set it to true
		//
		// }
		// }
		// } else {
		// filesUploaded = true;
		// }

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
