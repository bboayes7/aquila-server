package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestPINonPHSDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.ConflictOfInterestPINonPHS;
import edu.csula.aquila.model.Proposal;

@RestController
public class ConflictOfInterestPINonPHSController {

	@Autowired
	private ConflictOfInterestPINonPHSDao conflictOfInterestPINonPHSDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/coipinonphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestPINonPHS getConflictOfInterestPINonPHSById( @PathVariable Long id )
	{
		return conflictOfInterestPINonPHSDao.getConflictOfInterestPINonPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/{proposalId}/savecoipinonphs", method = RequestMethod.POST)
    public ConflictOfInterestPINonPHS saveConflictOfInterestPINonPHS( @PathVariable Long proposalId, @RequestBody ConflictOfInterestPINonPHS coiPINonPHS )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
		proposal.setCoiPiNonPhs(coiPINonPHS);
		coiPINonPHS.setProposal(proposal);
    	return conflictOfInterestPINonPHSDao.saveConflictOfInterestPINonPHS( coiPINonPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoipinonphs", method = RequestMethod.PUT)
    public ConflictOfInterestPINonPHS updateConflictOfInterestPINonPHS( @RequestBody ConflictOfInterestPINonPHS coiPINonPHS )
    {
    	return conflictOfInterestPINonPHSDao.updateConflictOfInterestPINonPHS( coiPINonPHS );
    			
    }
	
}
