package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestFormDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.ConflictOfInterestForm;
import edu.csula.aquila.model.Proposal;

@RestController
public class ConflictOfInterestFormController {
	
	@Autowired
	private ConflictOfInterestFormDao conflictOfInterestFormDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/conflictofinterest/{id}", method = RequestMethod.GET)
	public ConflictOfInterestForm getConflictOfInterestFormById( @PathVariable Long id )
	{
		return conflictOfInterestFormDao.getConflictOfInterestFormById(id);
	}
	
	@RequestMapping(value = "/proposal/conflictofinterest/{id}", method = RequestMethod.PUT)
    public ConflictOfInterestForm updateConflictOfInterestForm( @RequestBody ConflictOfInterestForm coiForm )
    {
		//set the id
    	return conflictOfInterestFormDao.updateConflictOfInterestForm( coiForm );
    			
    }
	@RequestMapping(value = "/proposal/{proposalId}/conflictofinterest", method = RequestMethod.POST)
    public ConflictOfInterestForm saveConflictOfInterestForm( @PathVariable Long proposalId, @RequestBody ConflictOfInterestForm coiForm )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
//		proposal.setCoiForm(coiForm);
//		coiForm.setProposal(proposal);
    	return conflictOfInterestFormDao.saveConflictOfInterestForm( coiForm );
    			
    }
	

}
