package edu.csula.aquila.controller.jwt;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User.Type;

@RestController
public class IntakeController {

	@Autowired
	private IntakeDao intakeDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	//TimelineController timelineController;
	
	//create a new form
	@RequestMapping(value = "/proposal/{propId}/intake/", method = RequestMethod.POST)
	public IntakeForm newIntakeForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long propId, 
			@RequestBody IntakeForm intakeForm) 
	{
		
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		return intakeDao.saveIntakeForm(intakeForm);
	}

	//update a form
	@RequestMapping(value = "/proposal/{propId}/intake/{id}", method = RequestMethod.PUT)
	public IntakeForm updateIntakeForm(@ModelAttribute("currentUser") User currentUser, @RequestBody IntakeForm intakeForm, 
			@PathVariable Long propId, @PathVariable Long id) 
	{
		intakeForm.setId(id);
		
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.UAS_ANALYST && proposal.getStatus() != Status.MEETING ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getStatus() != Status.DRAFT)
			throw new RestException(403, "FORBIDDEN");
		
	
		return intakeDao.saveIntakeForm(intakeForm);
	}
	
	//return a form 
	@RequestMapping(value = "/proposal/{propId}/intake/{id}", method = RequestMethod.GET)
	public IntakeForm getIntakeForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long propId, @PathVariable Long id) 
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() )
			throw new RestException(401, "UNAUTHORIZED");
		
		return intakeDao.getIntakeForm(id);
	}
}
