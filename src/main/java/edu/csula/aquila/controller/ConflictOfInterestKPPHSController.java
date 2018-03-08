package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestKPPHSDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.ConflictOfInterestKPPHS;
import edu.csula.aquila.model.Proposal;

@RestController
public class ConflictOfInterestKPPHSController {
	
	@Autowired
	private ConflictOfInterestKPPHSDao conflictOfInterestKPPHSDao;
	
	@Autowired 
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/coikpphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestKPPHS getConflictOfInterestKPPHSById( @PathVariable Long id )
	{
		return conflictOfInterestKPPHSDao.getConflictOfInterestKPPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/{proposalId}/savecoikpphs", method = RequestMethod.POST)
    public ConflictOfInterestKPPHS saveConflictOfInterestKPPHS( @PathVariable Long proposalId, @RequestBody ConflictOfInterestKPPHS coiKPPHS )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
		proposal.setCoiKpPhs(coiKPPHS);
		coiKPPHS.setProposal(proposal);
    	return conflictOfInterestKPPHSDao.saveConflictOfInterestKPPHS( coiKPPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoikpphs", method = RequestMethod.PUT)
    public ConflictOfInterestKPPHS updateConflictOfInterestKPPHS( @RequestBody ConflictOfInterestKPPHS coiKPPHS )
    {
    	return conflictOfInterestKPPHSDao.updateConflictOfInterestKPPHS( coiKPPHS );
    			
    }

}
