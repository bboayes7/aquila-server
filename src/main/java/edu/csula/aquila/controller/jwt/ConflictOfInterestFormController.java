package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestFormDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.ConflictOfInterestForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User.Type;

@RestController
public class ConflictOfInterestFormController {
	
	@Autowired
	private ConflictOfInterestFormDao conflictOfInterestFormDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/{proposalId}/conflictofinterest/{coiId}", method = RequestMethod.GET)
	public ConflictOfInterestForm getConflictOfInterestFormById( @ModelAttribute("currentUser") User currentUser, @PathVariable Long coiId,
			@PathVariable Long proposalId)
	{
		Proposal proposal = proposalDao.getProposal(proposalId);
		Long userId = proposal.getUser().getId();
		
		if(currentUser.getType() == Type.INVESTIGATOR && !currentUser.getId().equals(userId) )
			throw new RestException(401, "UNAUTHORIZED");
		
		return conflictOfInterestFormDao.getConflictOfInterestFormById(coiId);
	}
	
	
	@RequestMapping(value = "/proposal/{proposalId}/conflictofinterest/{coiId}", method = RequestMethod.PUT)
    public ConflictOfInterestForm updateConflictOfInterestForm( @ModelAttribute("currentUser") User currentUser, @PathVariable Long coiId, 
    		@RequestBody ConflictOfInterestForm coiForm, @PathVariable Long proposalId )
    {
		coiForm.setId(coiId);
		
		Proposal proposal = proposalDao.getProposal(proposalId);
		Long userId = proposal.getUser().getId();
		
		proposal.setStatus(Status.FINAL);
		switch(currentUser.getType()) {
		
			case INVESTIGATOR:
				switch(proposal.getStatus()) {
				
					case POSTMEETING:{
						if(currentUser.getId().equals(userId))
						{
							coiForm = conflictOfInterestFormDao.saveConflictOfInterestForm(coiForm);
							break;
						}
						else
						{
							throw new RestException(401, "UNAUTHORIZED");
						}
					}
					
					case DRAFT:
					case MEETING:
					case FINAL:
					case CANCELLED:
						throw new RestException(401, "UNAUTHORIZED");
					
					}
				
			case UAS_ANALYST:
				switch(proposal.getStatus()) {
				
					case MEETING:
					case POSTMEETING:
						coiForm = conflictOfInterestFormDao.saveConflictOfInterestForm(coiForm);
						break;
					
					
					case DRAFT:
					case FINAL:
					case CANCELLED:
						throw new RestException( 403, "FORBIDDEN");
					
					}
				
			case SYSADMIN:
				coiForm = conflictOfInterestFormDao.saveConflictOfInterestForm(coiForm);
				break;
		}
			
		return coiForm ;
	}
   
	
	@RequestMapping(value = "/proposal/{proposalId}/conflictofinterest", method = RequestMethod.POST) 
    public ConflictOfInterestForm newConflictOfInterestForm( @ModelAttribute("currentUser") User currentUser, @PathVariable Long proposalId, 
    		@RequestBody ConflictOfInterestForm coiForm )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
		
		coiForm = conflictOfInterestFormDao.saveConflictOfInterestForm( coiForm );
		proposal.getConflictOfInterestForms().add(coiForm);
		proposal = proposalDao.saveProposal(proposal);
		
    	return conflictOfInterestFormDao.saveConflictOfInterestForm( coiForm );
    			
    }
	

}
