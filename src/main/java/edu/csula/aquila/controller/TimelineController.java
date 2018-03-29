package edu.csula.aquila.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class TimelineController {

	@Autowired
	private TimelineDao timelineDao;

	@Autowired 
	ProposalDao proposalDao;
	
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Autowired 
	EquipmentDao equipmentDao;
	


	// create a new timeline
	@RequestMapping(value = "timeline", method = RequestMethod.POST)
	public Timeline newTimeline(@RequestBody Timeline timeline) 
	{
		return timelineDao.saveTimeline(timeline);
	}

	// update a timeline when UAS and PI make a timeline together
	@RequestMapping(value = "/proposal/{proposalId}/timeline/{id}", method = RequestMethod.PUT)
	public Timeline updateTimelineFirstMeeting(@RequestBody Timeline timeline, @PathVariable Long id,
			@PathVariable Long proposalId) 
	{
		Proposal proposal = proposalDao.getProposal(proposalId);
				
		if(timeline.getUasDueDate() != null && timeline.getStages().size() == 1)
		{
			Date dueDate = timeline.getUasDueDate();
			timeline = new Timeline(dueDate);
			
			//file maps and form maps for each stage
			Map<String,FileInfo> files1 = new HashMap<>();
			Map<String,FileInfo> files2 = new HashMap<>();
			Map<String,FileInfo> files3 = new HashMap<>();
			Map<String,Long> forms2 = new HashMap<>();
			Map<String,Long> forms3 = new HashMap<>();
			
						
				if(!forms2.containsKey("Equipment")) 
				{
					EquipmentForm equipmentForm = new EquipmentForm();
					equipmentForm = equipmentDao.saveEquipmentForm(equipmentForm);
					proposal.setEquipmentForm(equipmentForm);
				}
				
				//TODO approval form implementation
			/*	if(!forms3.containsKey("Approval Form")) 
				{
					ApprovalForm approvalForm = new ApprovalForm();
					approvalForm = approvalFormDao.saveApprovalForm(approvalForm);
					forms3.setValue(approvalForm.getId());
					proposal.setApprovalForm(approvalForm);
				}
			*/	
				
				
			
			forms2.put("Equipment", proposal.getEquipmentForm().getId());
			forms3.put("Intake Form", proposal.getIntakeForm().getId());
			//forms3.put("Approval", proposal.getApprovalForm().getId());
			
			FileInfo firstBudget = new FileInfo("First Budget", false);
			files1.put("First Budget", fileInfoDao.saveFile(firstBudget));
			
			FileInfo subContractDocs = new FileInfo("Sub Contract Documents", false);
			files2.put("Sub Contract Documents", fileInfoDao.saveFile(subContractDocs));
			
			FileInfo finalBudget = new FileInfo("Final Budget", false);
			files2.put("Final Budget", fileInfoDao.saveFile(finalBudget));
			
			FileInfo equipmentQuotesSpecs = new FileInfo("Equipment Quotes & Specs", false);
			files2.put("Equipment Quotes & Specs", fileInfoDao.saveFile(equipmentQuotesSpecs));
			
			FileInfo supportingLetters = new FileInfo("Supporting Letters", false);
			files3.put("Supporting Letters", fileInfoDao.saveFile(supportingLetters));
			
			FileInfo signaturesPDF = new FileInfo("Signatures PDF", false);
			files3.put("Signatures PDF", fileInfoDao.saveFile(signaturesPDF));
			
			//add forms and files to stages
			List<Stage> stages = timeline.getStages();
			stages.get(0).setRequiredFiles(files1);
			stages.get(1).setRequiredForms(forms2);
			stages.get(1).setRequiredFiles(files2);
			stages.get(2).setRequiredFiles(files3);
			stages.get(2).setRequiredForms(forms3);
			
			//set timeline values
			timeline.setProposalName(proposal.getProposalName());
			timeline.setPrincipalInvestigator(proposal.getUser().getFirstName() +" "+ 
													proposal.getUser().getLastName());
			timeline.setProposal(proposal);
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
