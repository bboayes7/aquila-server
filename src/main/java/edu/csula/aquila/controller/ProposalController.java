package edu.csula.aquila.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.User;

@RestController
public class ProposalController {

	@Autowired
	private ProposalDao proposalDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IntakeDao intakeDao;
	
	@Autowired
	private TimelineDao timelineDao;
	
	//Get a proposal
	@RequestMapping(value = "proposal/{id}", method = RequestMethod.GET)
	public Proposal getProposal(@PathVariable Long id){
		return proposalDao.getProposal(id);
	}
	
	//Create a proposal
	@RequestMapping(value = "proposal/", method = RequestMethod.POST)
	public Proposal newProposal(@RequestBody ProposalInstantiate proposalInstantiate) {
		//create proposal and set the name
		Proposal proposal = new Proposal();
		
		
		proposal.setProposalName(proposalInstantiate.getProposalName());
		
		//set the user
		User user = userDao.getUser(proposalInstantiate.getUserId());
		proposal.setUser(user);
		
		//set the status
		proposal.setStatus("Draft");

		//set the date
		proposal.setDateCreated(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		
		//create a null intake form
		IntakeForm intakeForm = new IntakeForm();
		intakeForm.setProjectTitle(proposalInstantiate.getProposalName());
		intakeForm.setPrincipleInvestigator(user.getFirstName() + " " + user.getLastName());
		proposal.setIntakeForm(intakeForm);
		
		//create a null timeline
		Timeline timeline = new Timeline();
		timeline.setProposalName(proposal.getProposalName());
		timeline.setPrincipalInvestigator(user.getFirstName() + " " + user.getLastName());
		timeline.setProposal(proposal);
		proposal.setTimeline(timeline);
		
		
		return proposal = proposalDao.saveProposal(proposal);
	}
	
	//Get a list of proposals of a user
	@RequestMapping(value = "proposals/{id}", method = RequestMethod.GET)
	public List<Proposal> getProposalsOfUser(@PathVariable Long id){
		
		return proposalDao.getProposalsOfUser(id);
	}
	
	
}

class ProposalInstantiate{
	String proposalName;
	Long userId;
	
	ProposalInstantiate(){
		
	}
	
	public String getProposalName() {
		return proposalName;
	}
	
	public Long getUserId() {
		return userId;
	}
	
}

