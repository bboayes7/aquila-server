package edu.csula.aquila.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.ApprovalForm;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.Form;
import edu.csula.aquila.model.Timeline;

@RestController
public class TimelineController {

	@Autowired
	private TimelineDao timelineDao;
	

	//create a new timeline
	@RequestMapping(value = "timeline", method = RequestMethod.POST)
	public Timeline newTimeline(@RequestBody Timeline timeline) {
		return timelineDao.saveTimelineForm(timeline);
	}
	
	//update a timeline
	@RequestMapping(value = "timeline/{id}", method = RequestMethod.PUT)
	public Timeline updateTimeline(@RequestBody Timeline timeline, @PathVariable Long id) {
		timelineDao.saveTimelineForm(timeline);
		timeline = timelineDao.getTimelineForm(id);
		
		//get a list of stages
		List<Timeline.Stage> stages = timeline.getStages();
		//make a list of required forms
		List<String> requiredForms;
		
		for(int i = 0; i < stages.size(); i++) {
			//create a new list of forms
			List<Form> forms = new ArrayList<Form>();
			//grab the required forms string from a stage
			requiredForms = stages.get(i).getRequiredForms();
			
			//if a form contains the name of a model form, add it to the form list
			for(int j = 0; j < requiredForms.size(); j++) {
				
				//if the string is an intake form, get a user's intake form and add it to the form list
				if(requiredForms.get(j).equals("Intake Form")) {
					forms.add(timeline.getProposalForm().getIntakeForm());
				}
				
				//since these forms aren't mapped out yet, this creates a new form for now
				//will implement soon
				if(requiredForms.get(j).equals("Equipment Form")) {
					forms.add(new EquipmentForm());
				}
				if(requiredForms.get(j).equals("Approval Form")) {
					forms.add(new ApprovalForm());
				}
			}
			//set the list of forms for the stage
			stages.get(i).setForms(forms);
		}
		
		//update timeline
		timeline.setStages(stages);
		
		//save timeline to db
		return timelineDao.saveTimelineForm(timeline);
	}
	
	//return a timeline
	@RequestMapping(value = "timeline/{id}", method = RequestMethod.GET)
	public Timeline getTimeline(@PathVariable Long id) {
		return timelineDao.getTimelineForm(id);
	}
	
	
	//Get a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.GET)
	public Timeline.Stage getStage(@PathVariable Long id){
		return timelineDao.getStage(id);
	}
	
	//create a stage
	@RequestMapping(value = "timeline/stage/", method = RequestMethod.POST)
	public Timeline.Stage createStage(Timeline.Stage stage){
		return timelineDao.createStage(stage);
	}
	
	//update a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.PUT)
	public Timeline.Stage updateStage(Timeline.Stage stage, @PathVariable Long id){
		return timelineDao.updateStage(stage);
	}
	
	//delete a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DeleteResponse deleteStage(Timeline.Stage stage, @PathVariable Long id){
		stage = timelineDao.getStage(id);
		timelineDao.deleteStage(stage);
		return new DeleteResponse("Stage Deleted!");
	}
	
	
	//message to send when a stage is deleted
	public class DeleteResponse{
		private String message;
		
		public DeleteResponse(String message){
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}



