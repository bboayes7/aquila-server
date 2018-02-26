package edu.csula.aquila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Form;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;

@RestController
public class TimelineController {

	@Autowired
	private TimelineDao timelineDao;

	@Autowired
	private ProposalDao proposalDao;

	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private IntakeDao intakeDao;

	// create a new timeline
	@RequestMapping(value = "timeline", method = RequestMethod.POST)
	public Timeline newTimeline(@RequestBody Timeline timeline) {
		return timelineDao.saveTimelineForm(timeline);
	}

	// update a timeline
	@RequestMapping(value = "/proposal/{proposalId}/timeline/{id}", method = RequestMethod.PUT)
	public Timeline updateTimeline(@RequestBody Timeline timeline, @PathVariable Long id,
			@PathVariable Long proposalId) {

		// setting up proposal dao to avoid null pointer error when calling a user's
		// intake from the timeline
		Proposal proposal = proposalDao.getProposal(proposalId);

		List<Timeline.Stage> stages = timeline.getStages();

		// go through each stage
		for (int i = 0; i < stages.size(); i++) {
			// get the map from a stage
			Map<String, Form> forms = stages.get(i).getRequiredForms();
			// go through the map
			for (Map.Entry<String, Form> form : forms.entrySet()) {
				// get the form name
				String key = form.getKey();

				switch (key) {
				// if form name is intake form, add the form value
				case "Intake Form":
					IntakeForm intakeForm = proposal.getIntakeForm();
					Timeline.Stage stage = stages.get(i);
					intakeForm.setStage(stage);
					form.setValue(intakeForm);

					System.out.println("intake form linked!");
					break;

				// if form name is equipment form, add the form value
				case "Equipment Form":
					// needs to be related
					EquipmentForm equipmentForm = new EquipmentForm();
					Long equipmentId = equipmentDao.saveEquipmentForm(equipmentForm).getId();
					equipmentForm = equipmentDao.getEquipmentForm(equipmentId);
					form.setValue(equipmentForm);

					System.out.println("equipment form linked!");
					break;

				default:
					System.out.println("Invalid Form name");
				}

			}

			// set the map of forms for the stage
			stages.get(i).setRequiredForms(forms);
		}

		// set stages
		timeline.setStages(stages);

		// save timeline
		return timelineDao.saveTimelineForm(timeline);
	}

	// return a timeline
	@RequestMapping(value = "timeline/{id}", method = RequestMethod.GET)
	public Timeline getTimeline(@PathVariable Long id) {
		if (timelineDao.getTimelineForm(id).getProposalForm() == null) {
			System.out.println("no proposal");

		} else {
			System.out.println("There's a proposal form linked.");
			if (timelineDao.getTimelineForm(id).getProposalForm().getIntakeForm() != null) {
				System.out.println("there's also an intake form linked");
			}
		}
		return timelineDao.getTimelineForm(id);
	}



}
