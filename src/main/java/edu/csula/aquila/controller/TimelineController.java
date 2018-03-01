package edu.csula.aquila.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;

@RestController
public class TimelineController {

	@Autowired
	private TimelineDao timelineDao;

	@Autowired 
	ProposalDao proposalDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	

	@RequestMapping(value="/proposal/{proposalId}/timelinedefault/{uasDueDate}", method = RequestMethod.POST)
	public Timeline defaultTimeline(@PathVariable Long proposalId, @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") Date uasDueDate)
	{
		Timeline defaultTimeline = new Timeline(proposalId, uasDueDate);
		return timelineDao.saveTimelineForm(defaultTimeline);
	}


	// create a new timeline
	@RequestMapping(value = "timeline", method = RequestMethod.POST)
	public Timeline newTimeline(@RequestBody Timeline timeline) {
		return timelineDao.saveTimelineForm(timeline);
	}

	// update a timeline
	@RequestMapping(value = "/proposal/{proposalId}/timeline/{id}", method = RequestMethod.PUT)
	public Timeline updateTimeline(@RequestBody Timeline timeline, @PathVariable Long id,
			@PathVariable Long proposalId) {
		//get proposal
		Proposal proposal = proposalDao.getProposal(proposalId);
		
		timeline.setId(id);
		//json doesnt return proposal relation, have to set proposal to avoid 'cannot be null' error
		timeline.setProposal(proposal);

		
		List<Timeline.Stage> stages = timeline.getStages();

		
		Timeline.Stage stage;
		
		// go through each stage
				for (int i = 0; i < stages.size(); i++) {
					//initialize stage
					stage = stages.get(i);
					
					// get the map from a stage
					Map<String, Long> forms = stage.getRequiredForms();
					// go through the map
					for (Map.Entry<String, Long> form : forms.entrySet()) {
						// get the form name
						String key = form.getKey();

						switch (key) {
						// if form name is intake form, add the form value
						case "Intake Form":
							IntakeForm intakeForm = proposal.getIntakeForm();
							stage = stages.get(i);
							intakeForm.setStage(stage);
							form.setValue(intakeForm.getId());

							System.out.println("intake form linked!");
							break;

						// if form name is equipment form, add the form value
						case "Equipment Form":
							if(form.getValue() == null) {
							EquipmentForm equipmentForm = new EquipmentForm();
							equipmentForm.setStage(stage);
							proposal.setEquipmentForm(equipmentForm);
							proposalDao.saveProposal(proposal);
							form.setValue(proposal.getEquipmentForm().getId());
							break;
							} else {
								break;
							}

						default:
							System.out.println("Invalid Form name");
						}

					}

					// update the map of forms for the stage
					stage.setRequiredForms(forms);
					stage.setTimeline(timeline);
					//update the current stage in the loop
					stages.set(i, stage);
					
				}

				// set stages
				timeline.setStages(stages);

		// save timeline
		return timelineDao.saveTimelineForm(timeline);
	}

	// return a timeline
	@RequestMapping(value = "timeline/{id}", method = RequestMethod.GET)
	public Timeline getTimeline(@PathVariable Long id) {

		return timelineDao.getTimelineForm(id);
	}

	
	
	//message to send when a stage is deleted
	public class DeleteResponse{
		private String message;
		
		public DeleteResponse(String message){
			this.message = message;
		}
	}




	
	//public void stageCheck(Timeline.Stage stage) {
		//pseudo code
		//get the stage
		//check if all forms are completed through the isComplete boolean
		//if all forms are complete, have a boolean called formsCompleted and set it to true
		//check if all the files are uploaded through the isUploaded boolean
		//if all files are uploaded, have a boolean called filesUploaded and set it to true
		//when formsCompleted && filesUploaded is true
		//set uasReviewRequired to true
		//send an email to UAS
	//}


}
