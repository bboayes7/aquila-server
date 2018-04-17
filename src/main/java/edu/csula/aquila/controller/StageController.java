package edu.csula.aquila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestFormDao;
import edu.csula.aquila.daos.EconomicInterestPIDao;
import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.ConflictOfInterestForm;
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
	private ConflictOfInterestFormDao coiFormDao;

	// @Autowired
	// MailSender mailSender;

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

		Proposal proposal = timeline.getProposal();

		// update forms
		formUpdate(stage, proposal);

		// update files
		fileUpdate(stage, proposal);

		proposalDao.saveProposal(proposal);

		// stage check
//		stageCheck(stage, proposal);

		return stageDao.saveStage(stage);
	}

	// delete a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Message deleteStage(@PathVariable Long id) {

		Stage stage = stageDao.getStage(id);
		int order = stage.getStageOrder();
		Timeline timeline = stage.getTimeline();
		List<Stage> stages = stageDao.getStages(timeline.getId());

		if (order == stages.size()) {
			stageDao.deleteStage(id);
			return new Message("Stage Deleted!");
		} else {
			stages.remove(order);
			order--;
			for (int i = order; i < stages.size(); i++) {
				stages.get(i).setStageOrder(order);
				order++;
				stageDao.saveStage(stages.get(i));

			}

			stageDao.deleteStage(id);
			List<Stage> reorderedStages = stageDao.getStages(timeline.getId());
			timeline.setStages(reorderedStages);
			timelineDao.saveTimeline(timeline);

		}

		return new Message("Stage Deleted!");
	}

	@RequestMapping(value = "timeline/stage/{stageId}/order/{indexToPush}", method = RequestMethod.GET)
	public @ResponseBody Message reorderStages(@PathVariable Long stageId, @PathVariable int indexToPush) {

		Stage stage = stageDao.getStage(stageId);
		int currentStageIndex = stage.getStageOrder();
		Timeline timeline = stage.getTimeline();
		List<Stage> stages = stageDao.getStages(timeline.getId());

		if (indexToPush > stages.size()) {
			return new Message("Out of Bounds");
		}

		// stage moved down the list
		if (currentStageIndex > indexToPush) {
			for (int i = indexToPush; i <= currentStageIndex; i++) {
				stages.get(i).setStageOrder(stages.get(i).getStageOrder() + 1);
				stageDao.saveStage(stages.get(i));
			}
			stage.setStageOrder(indexToPush);
			stageDao.saveStage(stage);

		}

		// stage moved up the list
		if (currentStageIndex < indexToPush) {
			// reorder stages in between
			for (int i = currentStageIndex; i <= indexToPush; i++) {
				stages.get(i).setStageOrder(stages.get(i).getStageOrder() - 1);
				stageDao.saveStage(stages.get(i));
			}
			stage.setStageOrder(indexToPush);
			stageDao.saveStage(stage);

		}
		
		List<Stage> reorderedStages = stageDao.getStages(timeline.getId());
		timeline.setStages(reorderedStages);
		timelineDao.saveTimeline(timeline);

		return new Message("Stages Reordered");
	}

	public class Message {
		String message;

		public Message(String message) {
			this.message = message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
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

	// This method updates the required form's map value from null to a form in
	// the
	// proposal
	public void formUpdate(Stage stage, Proposal proposal) {

		Map<String, Long> forms = stage.getRequiredForms();
		// form name and form id;
		for (Map.Entry<String, Long> form : forms.entrySet()) {
			String key = form.getKey();

			switch (key) {
			// this case is easy since there's already an intake form set
			case "Intake Form":
				IntakeForm intakeForm = proposal.getIntakeForm();
				form.setValue(intakeForm.getId());

				break;
			// this case is tricky since there isn't an equipment form created yet and there's no id
			// we also have to consider if a stage is getting updated again and there's already a form linked
			// if that's the case then we would have to check if a value is null to create a new form for the link
			case "Equipment Form":
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
					ConflictOfInterestForm coiForm = new ConflictOfInterestForm();
					coiForm.setType("OIPHS"); // make this into an enum
					coiForm = coiFormDao.saveConflictOfInterestForm(coiForm);
					form.setValue(coiForm.getId());
					List<ConflictOfInterestForm> coiForms = proposal.getConflictOfInterestForms();
					coiForms.add(coiForm);
					proposal.setConflictOfInterestForms(coiForms);
					break;
				}
				break;
			case "COI Other Investigator/Key Personnel NONPHS":
				if (form.getValue() == null) {
					ConflictOfInterestForm coiForm = new ConflictOfInterestForm();
					coiForm.setType("OINONPHS"); // make this into an enum
					coiForm = coiFormDao.saveConflictOfInterestForm(coiForm);
					form.setValue(coiForm.getId());
					List<ConflictOfInterestForm> coiForms = proposal.getConflictOfInterestForms();
					coiForms.add(coiForm);
					proposal.setConflictOfInterestForms(coiForms);					
					break;
				}
				break;
			case "COI Principal Investigator PHS":
				if (form.getValue() == null) {
					ConflictOfInterestForm coiForm = new ConflictOfInterestForm();
					coiForm.setType("PIPHS"); // make this into an enum
					coiForm = coiFormDao.saveConflictOfInterestForm(coiForm);
					form.setValue(coiForm.getId());
					List<ConflictOfInterestForm> coiForms = proposal.getConflictOfInterestForms();
					coiForms.add(coiForm);
					proposal.setConflictOfInterestForms(coiForms);
					break;
				}
				break;
			case "COI Principal Investigator NONPHS":
				if (form.getValue() == null) {
					ConflictOfInterestForm coiForm = new ConflictOfInterestForm();
					coiForm.setType("PINONPHS"); // make this into an enum
					coiForm = coiFormDao.saveConflictOfInterestForm(coiForm);
					form.setValue(coiForm.getId());
					List<ConflictOfInterestForm> coiForms = proposal.getConflictOfInterestForms();
					coiForms.add(coiForm);
					proposal.setConflictOfInterestForms(coiForms);
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
		boolean filesUploaded = true; // consider if stages dont have required
										// files or required forms

		// HANDLE FORMS
		Map<String, Long> forms = stage.getRequiredForms();

		// check if all forms are complete (maybe add a condition if form list
		// is 0 then
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
//				case "COI Other Investigator/Key Personnel PHS":
//					ConflictOfInterestKPPHS coiKpPhs = proposal.getCoiKpPhs();
//					complete = coiKpPhs.isComplete();
//					break;
//				case "COI Other Investigator/Key Personnel NONPHS":
//					ConflictOfInterestKPNonPHS coiKpNonPhs = proposal.getCoiKpNonPhs();
//					complete = coiKpNonPhs.isComplete();
//					break;
//				case "COI Principal Investigator PHS":
//					ConflictOfInterestPHS coiPhs = proposal.getCoiPhs();
//					complete = coiPhs.isComplete();
//					break;
//				case "COI Principal Investigator NONPHS":
//					ConflictOfInterestPINonPHS coiPiNonPhs = proposal.getCoiPiNonPhs();
//					complete = coiPiNonPhs.isComplete();
//					break;
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

		// HANDLE FILES
		Map<String, FileInfo> files = stage.getRequiredFiles();
		if (files.size() != 0) {
			for (Map.Entry<String, FileInfo> file : files.entrySet()) {
				if (file.getValue().isUploaded() == true) {
					complete = true;
				} else {
					complete = false;
					break;
				}
			}
		}

		if (complete) {
			filesUploaded = true;
		}

		// when formsCompleted && filesUploaded is true
		if (formsComplete && filesUploaded) {
			// set uasReviewRequired to true
			stage.setUasReviewRequired(true);
			// send an email to UAS
			// for now just print email sent
			// SimpleMailMessage msg = new SimpleMailMessage();
			// msg.setFrom( "aquila@csula.com" );
			// msg.setTo( "barryboayes17@gmail.com" );
			// msg.setSubject( "There Is A Stage That Needs To Be Reviewed");
			// msg.setText("A user has completed a stage, please go to our
			// website and review this stage");
			// mailSender.send(msg);
			System.out.println("Email 'sent'");
		}
	}

}
