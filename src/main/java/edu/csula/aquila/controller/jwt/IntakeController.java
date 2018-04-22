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
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;

@RestController
public class IntakeController {

	@Autowired
	private IntakeDao intakeDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	
	//create a new form
	@RequestMapping(value = "/intake", method = RequestMethod.POST)
	public IntakeForm newIntakeForm( @RequestBody IntakeForm intakeForm) 
	{
		
		return intakeDao.saveIntakeForm(intakeForm);
	}

	//update a form
	@RequestMapping(value = "/proposal/{propId}/intake/{intakeId}", method = RequestMethod.PUT)
	public IntakeForm updateIntakeForm(@ModelAttribute("currentUser") User currentUser, @RequestBody IntakeForm intakeForm,
			@PathVariable Long propId, @PathVariable Long intakeId) 
	{
		intakeForm.setId(intakeId);
		
		Proposal proposal = proposalDao.getProposal(propId);
		Long userId = proposal.getUser().getId();
		System.out.println(userId);
		System.out.println(currentUser.getType());
		
		
		
		switch(currentUser.getType()) {
		
			case INVESTIGATOR:
				switch(proposal.getStatus()) {
				
				case DRAFT:
				case POSTMEETING:{
					if(currentUser.getId().equals(userId))
					{
						intakeForm = intakeDao.saveIntakeForm(intakeForm);
						break;
					}
					else
					{
						throw new RestException(401, "UNAUTHORIZED");
					}
				}
				
				case MEETING:
				case FINAL:
				case CANCELLED:
					throw new RestException(401, "UNAUTHORIZED");
				
				}
				
			case UAS_ANALYST:
				switch(proposal.getStatus()) {
				
				case MEETING:
				case POSTMEETING:
					intakeForm = intakeDao.saveIntakeForm(intakeForm);
					break;
				
				
				case DRAFT:
				case FINAL:
				case CANCELLED:
					throw new RestException( 403, "FORBIDDEN");
				
				}
				
			case SYSADMIN:
				intakeForm = intakeDao.saveIntakeForm(intakeForm);
				break;
				
		}
		
		return intakeDao.saveIntakeForm(intakeForm);
	}
	
	//return a form 
	@RequestMapping(value = "/proposal/{propId}/intake/{intakeId}", method = RequestMethod.GET)
	public IntakeForm getIntakeForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long propId, @PathVariable Long intakeId) 
	{
		Proposal proposal = proposalDao.getProposal(propId);
		Long userId = proposal.getUser().getId();
		
		if(currentUser.getType() == Type.INVESTIGATOR && !currentUser.getId().equals(userId) )
			throw new RestException(401, "UNAUTHORIZED");
		
		return intakeDao.getIntakeForm(intakeId);
	}
}
