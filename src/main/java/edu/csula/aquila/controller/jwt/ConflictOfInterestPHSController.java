package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestPHSDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.ConflictOfInterestPHS;
import edu.csula.aquila.model.Proposal;

@RestController
public class ConflictOfInterestPHSController {
	
	@Autowired
	private ConflictOfInterestPHSDao conflictOfInterestPHSDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/coiphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestPHS getConflictOfInterestPHSById( @PathVariable Long id )
	{
		return conflictOfInterestPHSDao.getConflictOfInterestPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/{proposalId}/savecoiphs", method = RequestMethod.POST)
    public ConflictOfInterestPHS saveConflictOfInterestPHS( @PathVariable Long proposalId, @RequestBody ConflictOfInterestPHS coiPHS )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
		proposal.setCoiPhs(coiPHS);
		coiPHS.setProposal(proposal);
    	return conflictOfInterestPHSDao.saveConflictOfInterestPHS( coiPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoiphs", method = RequestMethod.PUT)
    public ConflictOfInterestPHS updateConflictOfInterestPHS( @RequestBody ConflictOfInterestPHS coiPHS )
    {
    	return conflictOfInterestPHSDao.updateConflictOfInterestPHS( coiPHS );
    			
    }

}
