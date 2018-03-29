package edu.csula.aquila.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;

@RestController
public class TimelineController {

	@Autowired
	private TimelineDao timelineDao;

	@Autowired 
	ProposalDao proposalDao;
	


	// create a new timeline
	@RequestMapping(value = "timeline", method = RequestMethod.POST)
	public Timeline newTimeline(@RequestBody Timeline timeline) 
	{
		return timelineDao.saveTimeline(timeline);
	}

	// update a timeline when UAS and PI make a timeline together
	@RequestMapping(value = "/proposal/{proposalId}/timeline/{id}", method = RequestMethod.PUT)
	public Timeline updateTimelineFirstMeeting(@RequestBody Timeline timeline, @PathVariable Long id, @PathVariable Long proposalId) 
	{
		Proposal proposal = proposalDao.getProposal(proposalId);
				
		if(timeline.getUasDueDate() != null && timeline.getStages().size() == 1)
		{
			Date dueDate = timeline.getUasDueDate();
			timeline = new Timeline(dueDate);
			timeline.setProposalName(proposal.getProposalName());
			timeline.setPrincipalInvestigator(proposal.getUser().getFirstName() +" "+ 
													proposal.getUser().getLastName());
			timeline.setProposal(proposal);
			System.out.println("timeline stage size: " + timeline.getStages().size());
			proposal.setTimeline(timeline);
		}
		

		timeline.setId(id);
		return timelineDao.saveTimeline(timeline);
	}
	
	//update timeline simple types when there are changes happening along the meeting
	@RequestMapping(value = "/proposal/{proposalId}/timeline/{id}", method = RequestMethod.PATCH)
	public Timeline updateTimeline(@RequestBody Timeline timeline, @PathVariable Long id, @PathVariable Long proposalId) {
		
		timeline.setId(id);
		return timelineDao.saveTimeline(timeline);
	}
	

	// return a timeline
	@RequestMapping(value = "timeline/{id}", method = RequestMethod.GET)
	public Timeline getTimeline(@PathVariable Long id) 
	{
		return timelineDao.getTimeline(id);
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
